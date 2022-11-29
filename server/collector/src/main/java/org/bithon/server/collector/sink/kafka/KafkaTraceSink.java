/*
 *    Copyright 2020 bithon.org
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package org.bithon.server.collector.sink.kafka;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.OptBoolean;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.record.AbstractRecords;
import org.apache.kafka.common.record.CompressionType;
import org.apache.kafka.common.record.RecordBatch;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.bithon.component.commons.utils.CollectionUtils;
import org.bithon.component.commons.utils.Preconditions;
import org.bithon.server.sink.tracing.ITraceMessageSink;
import org.bithon.server.storage.tracing.TraceSpan;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/3/15
 */
@Slf4j
@JsonTypeName("kafka")
public class KafkaTraceSink implements ITraceMessageSink {

    private final KafkaTemplate<byte[], byte[]> producer;
    private final ObjectMapper objectMapper;
    private final String topic;
    private final CompressionType compressionType;
    private final int maxSizePerMessage;
    private final Header header;

    private final ThreadLocal<Buffer> bufferThreadLocal;

    @JsonCreator
    public KafkaTraceSink(@JsonProperty("props") Map<String, Object> props,
                          @JacksonInject(useInput = OptBoolean.FALSE) ObjectMapper objectMapper) {
        this.topic = (String) props.remove("topic");
        Preconditions.checkNotNull(topic, "topic is not configured for tracing sink");

        this.maxSizePerMessage = (int) props.getOrDefault(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, 1024 * 1024);
        this.compressionType = CompressionType.forName((String) props.getOrDefault(ProducerConfig.COMPRESSION_TYPE_CONFIG, "none"));
        this.header = new RecordHeader("type", "tracing".getBytes(StandardCharsets.UTF_8));
        this.bufferThreadLocal = ThreadLocal.withInitial(() -> new Buffer(this.maxSizePerMessage));

        this.producer = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(props, new ByteArraySerializer(), new ByteArraySerializer()),
                                            ImmutableMap.of(ProducerConfig.CLIENT_ID_CONFIG, "trace"));
        this.objectMapper = objectMapper;
    }

    private static class Buffer {
        private byte[] buf;
        private int size;

        Buffer(int initCapacity) {
            this.buf = new byte[initCapacity];
            this.size = 0;
        }

        int capacity() {
            return buf.length;
        }

        boolean isEmpty() {
            return size == 0;
        }

        void write(byte[] buf) {
            ensureCapacity(buf.length);
            System.arraycopy(buf, 0, this.buf, this.size, buf.length);
            this.size += buf.length;
        }

        void write(char chr) {
            ensureCapacity(1);
            this.buf[this.size++] = (byte) chr;
        }

        public void deleteFromEnd(int length) {
            if (size >= length) {
                size -= length;
            }
        }

        byte[] toBytes() {
            if (size == this.buf.length) {
                return this.buf;
            } else {
                byte[] d = new byte[this.size];
                System.arraycopy(this.buf, 0, d, 0, this.size);
                return d;
            }
        }

        ByteBuffer toByteBuffer() {
            return ByteBuffer.wrap(this.buf, 0, this.size);
        }

        public void reset() {
            this.size = 0;
        }

        private void ensureCapacity(int extraSize) {
            int newSize = buf.length;
            while (this.size + extraSize > newSize) {
                newSize *= 2;
            }
            if (newSize != buf.length) {
                byte[] newBuff = new byte[newSize];
                System.arraycopy(this.buf, 0, newBuff, 0, this.size);
                this.buf = newBuff;
            }
        }
    }

    @SneakyThrows
    @Override
    public void process(String messageType, List<TraceSpan> spans) {
        if (CollectionUtils.isEmpty(spans)) {
            return;
        }

        byte[] key = null;

        //
        // A batch message in written into a single kafka message in which each text line is a single metric message.
        //
        // Of course, we could also send messages in this batch one by one to Kafka,
        // but I don't think it has advantages over the way below.
        //
        // But since Producer/Broker has size limitation on each message, we also limit the size in case of failure on send.
        //
        Buffer messageBuffer = this.bufferThreadLocal.get();
        messageBuffer.reset();
        messageBuffer.write('[');
        for (TraceSpan span : spans) {
            if (key == null) {
                key = (span.getAppName() + "/" + span.getInstanceName()).getBytes(StandardCharsets.UTF_8);
            }

            byte[] serializedSpan;
            try {
                serializedSpan = objectMapper.writeValueAsBytes(span);
            } catch (JsonProcessingException ignored) {
                continue;
            }

            int currentSize = AbstractRecords.estimateSizeInBytesUpperBound(RecordBatch.CURRENT_MAGIC_VALUE,
                                                                            this.compressionType,
                                                                            ByteBuffer.wrap(key),
                                                                            messageBuffer.toByteBuffer(),
                                                                            new Header[]{header});

            // plus 2 to leave 2 bytes as margin
            if (currentSize + serializedSpan.length + 2 > messageBuffer.capacity()) {
                send(key, messageBuffer);

                messageBuffer.reset();
                messageBuffer.write('[');
            }

            messageBuffer.write(serializedSpan);
            messageBuffer.write(',');
        }
        send(key, messageBuffer);
    }

    private void send(byte[] key, Buffer buffer) {
        if (buffer.isEmpty()) {
            return;
        }

        // Remove last separator
        buffer.deleteFromEnd(1);
        buffer.write(']');

        ProducerRecord<byte[], byte[]> record = new ProducerRecord<>(topic, key, buffer.toBytes());
        record.headers().add(header);
        try {
            producer.send(record);
        } catch (Exception e) {
            log.error("Error to send trace from {}, message: {}", key, e.getMessage());
        }
    }

    @Override
    public void close() {
        producer.destroy();
    }
}

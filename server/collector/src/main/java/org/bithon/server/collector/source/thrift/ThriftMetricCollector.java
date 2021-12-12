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

package org.bithon.server.collector.source.thrift;

import lombok.extern.slf4j.Slf4j;
import org.bithon.agent.rpc.thrift.service.MessageHeader;
import org.bithon.agent.rpc.thrift.service.metric.IMetricCollector;
import org.bithon.agent.rpc.thrift.service.metric.message.ExceptionMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.HttpIncomingMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.HttpOutgoingMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.JdbcPoolMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.JvmGcMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.JvmMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.MongoDbMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.RedisMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.SqlMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.ThreadPoolMetricMessage;
import org.bithon.agent.rpc.thrift.service.metric.message.WebServerMetricMessage;
import org.bithon.server.common.utils.ReflectionUtils;
import org.bithon.server.common.utils.collection.CloseableIterator;
import org.bithon.server.common.utils.collection.IteratorableCollection;
import org.bithon.server.metric.sink.IMetricMessageSink;
import org.bithon.server.metric.sink.MetricMessage;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/10 2:37 下午
 */
@Slf4j
public class ThriftMetricCollector implements IMetricCollector.Iface {

    private final IMetricMessageSink metricSink;

    public ThriftMetricCollector(IMetricMessageSink metricSink) {
        this.metricSink = metricSink;
    }

    @Override
    public void sendIncomingHttp(MessageHeader header, List<HttpIncomingMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("http-incoming-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendJvm(MessageHeader header, List<JvmMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("jvm-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendJvmGc(MessageHeader header, List<JvmGcMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("jvm-gc-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendWebServer(MessageHeader header, List<WebServerMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("web-server-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendException(MessageHeader header, List<ExceptionMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("exception-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendOutgoingHttp(MessageHeader header, List<HttpOutgoingMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("http-outgoing-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendThreadPool(MessageHeader header, List<ThreadPoolMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("thread-pool-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendJdbc(MessageHeader header, List<JdbcPoolMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("jdbc-pool-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendRedis(MessageHeader header, List<RedisMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("redis-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendSql(MessageHeader header, List<SqlMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("sql-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    @Override
    public void sendMongoDb(MessageHeader header, List<MongoDbMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("mongodb-metrics",
                           IteratorableCollection.of(new GenericMetricMessageIterator(header, messages)));
    }

    private static class GenericMetricMessageIterator implements CloseableIterator<MetricMessage> {
        private final Iterator<?> iterator;
        private final MessageHeader header;

        public GenericMetricMessageIterator(MessageHeader header, List<?> messages) {
            this.header = header;
            this.iterator = messages.iterator();
        }

        @Override
        public void close() {
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public MetricMessage next() {
            return toMetricMessage(header, iterator.next());
        }

        private MetricMessage toMetricMessage(MessageHeader header, Object message) {
            MetricMessage metricMessage = new MetricMessage();
            ReflectionUtils.getFields(header, metricMessage);
            ReflectionUtils.getFields(message, metricMessage);
            return metricMessage;
        }
    }
}

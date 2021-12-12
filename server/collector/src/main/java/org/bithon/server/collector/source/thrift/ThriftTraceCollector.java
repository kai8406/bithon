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
import org.bithon.agent.rpc.thrift.service.trace.ITraceCollector;
import org.bithon.agent.rpc.thrift.service.trace.TraceSpanMessage;
import org.bithon.server.common.utils.collection.IteratorableCollection;
import org.bithon.server.tracing.sink.ITraceMessageSink;
import org.bithon.server.tracing.sink.TraceSpan;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/23 11:19 下午
 */
@Slf4j
public class ThriftTraceCollector implements ITraceCollector.Iface {

    private final ITraceMessageSink traceSink;

    public ThriftTraceCollector(ITraceMessageSink traceSink) {
        this.traceSink = traceSink;
    }

    @Override
    public void sendTrace(MessageHeader header, List<TraceSpanMessage> spans) {
        if (CollectionUtils.isEmpty(spans)) {
            return;
        }

        log.info("Receiving trace message:{}", spans);
        traceSink.process("trace", from(header, spans));
    }

    private IteratorableCollection<TraceSpan> from(MessageHeader header, List<TraceSpanMessage> messages) {

        Iterator<TraceSpanMessage> delegate = messages.iterator();
        return IteratorableCollection.of(new Iterator<TraceSpan>() {
            @Override
            public boolean hasNext() {
                return delegate.hasNext();
            }

            @Override
            public TraceSpan next() {
                TraceSpanMessage spanMessage = delegate.next();

                TraceSpan traceSpan = new TraceSpan();
                traceSpan.appName = header.appName;
                traceSpan.instanceName = header.instanceName;
                traceSpan.kind = spanMessage.kind;
                traceSpan.name = spanMessage.name;
                traceSpan.traceId = spanMessage.traceId;
                traceSpan.spanId = spanMessage.spanId;
                traceSpan.parentSpanId = spanMessage.parentSpanId == null ? "" : spanMessage.parentSpanId;
                traceSpan.parentApplication = spanMessage.parentAppName;
                traceSpan.startTime = spanMessage.startTime;
                traceSpan.endTime = spanMessage.endTime;
                traceSpan.costTime = spanMessage.endTime - spanMessage.startTime;
                traceSpan.tags = spanMessage.tags;
                traceSpan.clazz = spanMessage.clazz;
                traceSpan.method = spanMessage.method;

                return traceSpan;
            }
        });
    }
}

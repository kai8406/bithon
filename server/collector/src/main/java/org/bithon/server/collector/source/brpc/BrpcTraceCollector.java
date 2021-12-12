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

package org.bithon.server.collector.source.brpc;

import lombok.extern.slf4j.Slf4j;
import org.bithon.agent.rpc.brpc.BrpcMessageHeader;
import org.bithon.agent.rpc.brpc.tracing.BrpcTraceSpanMessage;
import org.bithon.agent.rpc.brpc.tracing.ITraceCollector;
import org.bithon.server.common.utils.collection.IteratorableCollection;
import org.bithon.server.tracing.sink.ITraceMessageSink;
import org.bithon.server.tracing.sink.TraceSpan;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/23 11:19 下午
 */
@Slf4j
public class BrpcTraceCollector implements ITraceCollector, AutoCloseable {

    private final ITraceMessageSink traceSink;

    public BrpcTraceCollector(ITraceMessageSink traceSink) {
        this.traceSink = traceSink;
    }

    @Override
    public void sendTrace(BrpcMessageHeader header,
                          List<BrpcTraceSpanMessage> spans) {
        if (CollectionUtils.isEmpty(spans)) {
            return;
        }

        log.debug("Receiving trace message:{}", spans);
        traceSink.process("trace", toSpan(header, spans));
    }

    private IteratorableCollection<TraceSpan> toSpan(BrpcMessageHeader header, List<BrpcTraceSpanMessage> messages) {

        Iterator<BrpcTraceSpanMessage> delegate = messages.iterator();
        return IteratorableCollection.of(new Iterator<TraceSpan>() {
            @Override
            public boolean hasNext() {
                return delegate.hasNext();
            }

            @Override
            public TraceSpan next() {
                BrpcTraceSpanMessage spanMessage = delegate.next();

                TraceSpan traceSpan = new TraceSpan();
                traceSpan.appName = header.getAppName();
                traceSpan.instanceName = header.getInstanceName();
                traceSpan.kind = spanMessage.getKind();
                traceSpan.name = spanMessage.getName();
                traceSpan.traceId = spanMessage.getTraceId();
                traceSpan.spanId = spanMessage.getSpanId();
                traceSpan.parentSpanId = StringUtils.isEmpty(spanMessage.getParentSpanId())
                                         ? ""
                                         : spanMessage.getParentSpanId();
                traceSpan.parentApplication = spanMessage.getParentAppName();
                traceSpan.startTime = spanMessage.getStartTime();
                traceSpan.endTime = spanMessage.getEndTime();
                traceSpan.costTime = spanMessage.getEndTime() - spanMessage.getStartTime();
                traceSpan.tags = spanMessage.getTagsMap();
                traceSpan.clazz = spanMessage.getClazz();
                traceSpan.method = spanMessage.getMethod();

                return traceSpan;
            }
        });
    }

    @Override
    public void close() throws Exception {
        traceSink.close();
    }
}

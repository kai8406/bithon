package com.sbss.bithon.agent.core.tracing.propagation.extractor;

import com.sbss.bithon.agent.core.tracing.Tracer;
import com.sbss.bithon.agent.core.tracing.context.TraceContext;

/*
 * Transplanted from brave to support trace propagation from systems such as zipkin
 *
 * @author frank.chen021@outlook.com
 * @date 2021/2/6 9:38 上午
 */
class B3Extractor implements ITraceContextExtractor {
    static final String SAMPLED = "X-B3-Sampled";
    /**
     * 128 or 64-bit trace ID lower-hex encoded into 32 or 16 characters (required)
     */
    static final String TRACE_ID = "X-B3-TraceId";
    /**
     * 64-bit span ID lower-hex encoded into 16 characters (required)
     */
    static final String SPAN_ID = "X-B3-SpanId";
    /**
     * 64-bit parent span ID lower-hex encoded into 16 characters (absent on root span)
     */
    static final String PARENT_SPAN_ID = "X-B3-ParentSpanId";

    @Override
    public <R> TraceContext extract(R request, PropagationGetter<R> getter) {
        if (request == null) {
            return null;
        }

        String traceId = getter.get(request, TRACE_ID);
        if (traceId == null) {
            return null;
        }

        String spanId = getter.get(request, SPAN_ID);
        if (spanId == null) {
            return null;
        }

        String parentSpanId = getter.get(request, PARENT_SPAN_ID);
        if (parentSpanId == null) {
            return null;
        }

        return new TraceContext(traceId,
                                spanId,
                                parentSpanId,
                                Tracer.get().reporter(),
                                Tracer.get().traceIdGenerator());
    }
}

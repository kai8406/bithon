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

package org.bithon.agent.core.tracing.context;

import org.bithon.agent.core.tracing.Tracer;
import org.bithon.agent.core.tracing.id.ISpanIdGenerator;
import org.bithon.agent.core.tracing.propagation.TraceMode;
import org.bithon.agent.core.tracing.propagation.injector.PropagationSetter;
import org.bithon.agent.core.tracing.reporter.ITraceReporter;
import org.bithon.agent.core.tracing.sampler.SamplingMode;
import org.bithon.agent.core.utils.time.Clock;
import shaded.org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/5 8:48 下午
 */
class TraceContext implements ITraceContext {

    private final Stack<ITraceSpan> spanStack = new Stack<>();
    private final List<ITraceSpan> spans = new ArrayList<>();
    private final Clock clock = new Clock();
    private final String traceId;
    private final ISpanIdGenerator spanIdGenerator;
    private ITraceReporter reporter;
    private SamplingMode samplingMode;
    private TraceMode traceMode;

    public TraceContext(String traceId,
                        ISpanIdGenerator spanIdGenerator) {
        this.traceId = traceId;
        this.spanIdGenerator = spanIdGenerator;
    }

    @Override
    public TraceMode traceMode() {
        return TraceMode.TRACE;
    }

    @Override
    public String traceId() {
        return traceId;
    }

    @Override
    public ITraceSpan currentSpan() {
        return spanStack.isEmpty() ? null : spanStack.peek();
    }

    @Override
    public Clock clock() {
        return clock;
    }

    @Override
    public ITraceReporter reporter() {
        return reporter;
    }

    @Override
    public ITraceContext reporter(ITraceReporter reporter) {
        this.reporter = reporter;
        return this;
    }

    @Override
    public ISpanIdGenerator spanIdGenerator() {
        return spanIdGenerator;
    }

    @Override
    public ITraceSpan newSpan(String parentSpanId, String spanId) {
        ITraceSpan span = new TraceSpan(spanId, parentSpanId, this);
        this.onSpanCreated(span);
        return span;
    }

    @Override
    public void finish() {
        if (!spanStack.isEmpty()) {
            // TODO: ERROR
            return;
        }

        try {
            this.reporter.report(this.spans);
        } catch (Throwable e) {
            LoggerFactory.getLogger(TraceContext.class).error("Exception occured when finish a context", e);
        }
    }

    private void onSpanCreated(ITraceSpan span) {
        spanStack.push(span);
        spans.add(span);

        TraceContextListener.getInstance().onSpanStarted(span);
    }

    void onSpanFinished(TraceSpan span) {

        if (spanStack.isEmpty()) {
            // TODO: internal error
            TraceContextListener.getInstance().onSpanFinished(span);
            return;
        }

        if (!spanStack.peek().equals(span)) {
            // TODO: internal error
            TraceContextListener.getInstance().onSpanFinished(span);
            return;
        }

        spanStack.pop();
        if (spanStack.isEmpty()) {
            // TODO: report span
            TraceContextListener.getInstance().onSpanFinished(span);
            return;
        }

        TraceContextListener.getInstance().onSpanFinished(span);
    }

    @Override
    public TraceContext samplingMode(SamplingMode mode) {
        this.samplingMode = mode;
        return this;
    }

    @Override
    public <T> void propagate(T injectedTo, PropagationSetter<T> setter) {
        Tracer.get()
              .propagator()
              .inject(this, injectedTo, setter);
    }
}
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

package org.bithon.agent.observability.tracing.context.impl;

import org.bithon.agent.observability.tracing.Tracer;
import org.bithon.agent.observability.tracing.context.ITraceContext;
import org.bithon.agent.observability.tracing.context.ITraceSpan;
import org.bithon.agent.observability.tracing.context.TraceContextListener;
import org.bithon.agent.observability.tracing.context.TraceMode;
import org.bithon.agent.observability.tracing.context.propagation.PropagationSetter;
import org.bithon.agent.observability.tracing.id.ISpanIdGenerator;
import org.bithon.agent.observability.tracing.reporter.ITraceReporter;
import org.bithon.component.commons.logging.LoggerFactory;
import org.bithon.component.commons.time.Clock;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/5 8:48 下午
 */
public class TracingContext implements ITraceContext {

    private final Stack<ITraceSpan> spanStack = new Stack<>();
    private final List<ITraceSpan> spans = new ArrayList<>();
    private final Clock clock = new Clock();
    private final String traceId;
    private final ISpanIdGenerator spanIdGenerator;
    private ITraceReporter reporter;

    private boolean finished = false;

    public TracingContext(String traceId,
                          ISpanIdGenerator spanIdGenerator) {
        this.traceId = traceId;
        this.spanIdGenerator = spanIdGenerator;
    }

    @Override
    public TraceMode traceMode() {
        return TraceMode.TRACING;
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
        ITraceSpan span = new TracingSpan(spanId, parentSpanId, this);
        this.onSpanCreated(span);
        return span;
    }

    @Override
    public void finish() {
        if (!spanStack.isEmpty()) {
            LoggerFactory.getLogger(TracingContext.class).warn("TraceContext does not finish correctly. "
                                                                       + "[{}] spans are still remained in the stack. "
                                                                       + "Please adding -Dbithon.tracing.debug=true parameter to your application to turn on the span life time message to debug. Remained spans: \n{}",
                                                               spans.size(),
                                                               spans);
            return;
        }

        // Mark the context as FINISHED first to prevent user code to access spans in the implementation of 'report' below
        this.finished = true;

        try {
            this.reporter.report(this.spans);
        } catch (Throwable e) {
            LoggerFactory.getLogger(TracingContext.class).error("Exception occurred when finish a context", e);
        } finally {
            // Clear to allow this method to re-enter
            this.spans.clear();
        }
    }

    private void onSpanCreated(ITraceSpan span) {
        spanStack.push(span);
        spans.add(span);
    }

    void onSpanStarted(TracingSpan span) {
        TraceContextListener.getInstance().onSpanStarted(span);
    }

    void onSpanFinished(TracingSpan span) {

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
    public <T> void propagate(T injectedTo, PropagationSetter<T> setter) {
        Tracer.get()
              .propagator()
              .inject(this, injectedTo, setter);
    }

    @Override
    public ITraceContext copy() {
        return new TracingContext(this.traceId, this.spanIdGenerator).reporter(this.reporter);
    }

    @Override
    public boolean finished() {
        return this.finished;
    }
}

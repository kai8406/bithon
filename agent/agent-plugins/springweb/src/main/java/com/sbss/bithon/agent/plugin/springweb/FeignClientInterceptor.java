package com.sbss.bithon.agent.plugin.springweb;


import com.sbss.bithon.agent.boot.aop.AbstractInterceptor;
import com.sbss.bithon.agent.boot.aop.AopContext;
import com.sbss.bithon.agent.boot.aop.InterceptionDecision;
import com.sbss.bithon.agent.core.tracing.context.SpanKind;
import com.sbss.bithon.agent.core.tracing.context.TraceContext;
import com.sbss.bithon.agent.core.tracing.context.TraceContextHolder;
import com.sbss.bithon.agent.core.tracing.context.TraceSpan;
import feign.Request;
import feign.Response;

/**
 * @author frankchen
 * @date 2021-02-16 14:41
 */
public class FeignClientInterceptor extends AbstractInterceptor {

    @Override
    public InterceptionDecision onMethodEnter(AopContext aopContext) {
        TraceContext traceContext = TraceContextHolder.get();
        if (traceContext == null) {
            return InterceptionDecision.SKIP_LEAVE;
        }
        TraceSpan span = traceContext.currentSpan();
        if (span == null) {
            return InterceptionDecision.SKIP_LEAVE;
        }

        Request request = (Request) aopContext.getArgs()[0];
        aopContext.setUserContext(span.newChildSpan("feignClient")
                                      .kind(SpanKind.CLIENT)
                                      .tag("uri", request.url())
                                      .start());

        return InterceptionDecision.CONTINUE;
    }

    @Override
    public void onMethodLeave(AopContext aopContext) {
        TraceSpan span = (TraceSpan) aopContext.getUserContext();
        if (span == null) {
            return;
        }

        Response response = (Response) aopContext.castReturningAs();
        span.tag("status", String.valueOf(response.status()));
        span.finish();
    }
}

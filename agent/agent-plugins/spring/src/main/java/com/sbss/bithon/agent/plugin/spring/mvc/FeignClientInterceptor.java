/*
 *    Copyright 2020 bithon.cn
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

package com.sbss.bithon.agent.plugin.spring.mvc;


import com.sbss.bithon.agent.bootstrap.aop.AbstractInterceptor;
import com.sbss.bithon.agent.bootstrap.aop.AopContext;
import com.sbss.bithon.agent.bootstrap.aop.InterceptionDecision;
import com.sbss.bithon.agent.core.tracing.context.ITraceSpan;
import com.sbss.bithon.agent.core.tracing.context.SpanKind;
import com.sbss.bithon.agent.core.tracing.context.TraceSpanFactory;
import feign.Request;
import feign.Response;

/**
 * @author frankchen
 * @date 2021-02-16 14:41
 */
public class FeignClientInterceptor extends AbstractInterceptor {

    @Override
    public InterceptionDecision onMethodEnter(AopContext aopContext) {
        ITraceSpan span = TraceSpanFactory.newSpan("feignClient");
        if (span == null) {
            return InterceptionDecision.SKIP_LEAVE;
        }

        Request request = (Request) aopContext.getArgs()[0];
        aopContext.setUserContext(span.kind(SpanKind.CLIENT)
                                      .tag("uri", request.url())
                                      .start());

        return InterceptionDecision.CONTINUE;
    }

    @Override
    public void onMethodLeave(AopContext aopContext) {
        ITraceSpan span = aopContext.castUserContextAs();

        Response response = aopContext.castReturningAs();
        span.tag("status", String.valueOf(response.status()));
        span.finish();
    }
}
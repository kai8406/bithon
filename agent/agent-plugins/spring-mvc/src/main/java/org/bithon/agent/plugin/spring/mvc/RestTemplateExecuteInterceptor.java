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

package org.bithon.agent.plugin.spring.mvc;

import org.bithon.agent.instrumentation.aop.context.AopContext;
import org.bithon.agent.instrumentation.aop.interceptor.InterceptionDecision;
import org.bithon.agent.instrumentation.aop.interceptor.declaration.AroundInterceptor;
import org.bithon.agent.observability.tracing.context.ITraceSpan;
import org.bithon.agent.observability.tracing.context.TraceSpanFactory;
import org.bithon.component.commons.tracing.Tags;

import java.net.URI;

/**
 * @author frankchen
 * @date 2021-02-16 14:36
 */
public class RestTemplateExecuteInterceptor extends AroundInterceptor {
    @Override
    public InterceptionDecision before(AopContext aopContext) {
        ITraceSpan span = TraceSpanFactory.newSpan("restTemplate");
        if (span == null) {
            return InterceptionDecision.SKIP_LEAVE;
        }

        String uri = null;
        Object obj = aopContext.getArgs()[0];
        if (obj instanceof String) {
            uri = (String) obj;
        } else if (obj instanceof URI) {
            uri = obj.toString();
        }

        aopContext.setUserContext(span.method(aopContext.getTargetClass(), aopContext.getMethod())
                                      .tag(Tags.HTTP_URI, uri)
                                      .start());

        return InterceptionDecision.CONTINUE;
    }


    @Override
    public void after(AopContext aopContext) {
        ITraceSpan span = aopContext.getUserContextAs();
        span.finish();
    }
}

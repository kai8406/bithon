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

package com.sbss.bithon.agent.plugin.httpclient.jdk;

import com.sbss.bithon.agent.bootstrap.aop.AbstractInterceptor;
import com.sbss.bithon.agent.bootstrap.aop.AopContext;
import com.sbss.bithon.agent.bootstrap.aop.IBithonObject;
import com.sbss.bithon.agent.bootstrap.aop.InterceptionDecision;
import com.sbss.bithon.agent.core.context.AgentContext;
import com.sbss.bithon.agent.core.context.InterceptorContext;
import com.sbss.bithon.agent.core.tracing.context.SpanKind;
import com.sbss.bithon.agent.core.tracing.context.TraceContext;
import com.sbss.bithon.agent.core.tracing.context.TraceContextHolder;
import com.sbss.bithon.agent.core.tracing.context.TraceSpan;
import sun.net.www.MessageHeader;

import java.net.HttpURLConnection;

/**
 * @author frankchen
 */
public class HttpClientWriteRequestInterceptor extends AbstractInterceptor {

    private String thisApplication;

    @Override
    public boolean initialize() throws Exception {
        thisApplication = AgentContext.getInstance().getAppInstance().getAppName();
        return super.initialize();
    }

    @Override
    public InterceptionDecision onMethodEnter(AopContext aopContext) {
        //
        // propagate source application
        //
        MessageHeader headers = (MessageHeader) aopContext.getArgs()[0];
        headers.set(InterceptorContext.HEADER_SRC_APPLICATION_NAME, thisApplication);

        TraceContext traceContext = TraceContextHolder.get();
        if (traceContext == null) {
            return InterceptionDecision.SKIP_LEAVE;
        }
        TraceSpan span = traceContext.currentSpan();
        if (span == null) {
            return InterceptionDecision.SKIP_LEAVE;
        }

        IBithonObject injectedObject = aopContext.castTargetAs();
        HttpURLConnection connection = (HttpURLConnection) injectedObject.getInjectedObject();

        //
        // starts a span which will be finished after HttpClient.parseHttp
        //
        aopContext.setUserContext(span.newChildSpan("httpClient-jdk")
                                      .clazz(aopContext.getTargetClass())
                                      .method(aopContext.getMethod())
                                      .kind(SpanKind.CLIENT)
                                      .tag("uri", connection.getURL().toString())
                                      .start());

        //
        // propagate tracing after span creation
        //
        traceContext.propagate(headers, (headersArgs, key, value) -> {
            headersArgs.set(key, value);
        });

        return InterceptionDecision.CONTINUE;
    }
}

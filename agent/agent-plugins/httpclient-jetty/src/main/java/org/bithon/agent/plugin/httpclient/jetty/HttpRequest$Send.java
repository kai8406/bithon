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

package org.bithon.agent.plugin.httpclient.jetty;

import org.bithon.agent.bootstrap.aop.AbstractInterceptor;
import org.bithon.agent.bootstrap.aop.AopContext;
import org.bithon.agent.bootstrap.aop.InterceptionDecision;
import org.bithon.agent.core.metric.collector.MetricCollectorManager;
import org.bithon.agent.core.metric.domain.http.HttpOutgoingMetricsCollector;
import org.bithon.agent.core.tracing.context.ITraceSpan;
import org.bithon.agent.core.tracing.context.SpanKind;
import org.bithon.agent.core.tracing.context.Tags;
import org.bithon.agent.core.tracing.context.TraceSpanFactory;
import org.eclipse.jetty.client.HttpRequest;
import org.eclipse.jetty.client.api.Response;
import org.eclipse.jetty.client.api.Result;
import org.eclipse.jetty.http.HttpField;
import org.eclipse.jetty.util.Callback;

import java.nio.ByteBuffer;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/5/13 7:56 下午
 */
public class HttpRequest$Send extends AbstractInterceptor {

    private HttpOutgoingMetricsCollector metricCollector;

    @Override
    public boolean initialize() {
        metricCollector = MetricCollectorManager.getInstance()
                                                .getOrRegister("httpClient-jetty", HttpOutgoingMetricsCollector.class);
        return true;
    }

    /**
     * {@link org.eclipse.jetty.client.HttpRequest#send(Response.CompleteListener)}
     */
    @Override
    public InterceptionDecision onMethodEnter(AopContext aopContext) throws Exception {
        HttpRequest httpRequest = aopContext.castTargetAs();

        final ITraceSpan span = TraceSpanFactory.newAsyncSpan("httpClient-jetty")
                                                .method(aopContext.getMethod())
                                                .kind(SpanKind.CLIENT)
                                                .tag(Tags.URI, httpRequest.getURI().toString())
                                                .tag(Tags.HTTP_METHOD, httpRequest.getMethod())
                                                .propagate(httpRequest.getHeaders(), (headersArgs, key, value) -> headersArgs.put(key, value))
                                                .start();

        if (span.isNull()) {
            return InterceptionDecision.SKIP_LEAVE;
        }

        final long startAt = System.nanoTime();

        // replace listener to record metrics
        final Object rawListener = aopContext.getArgs()[0];
        aopContext.getArgs()[0] = new Response.Listener() {
            @Override
            public void onSuccess(Response response) {
                if (rawListener instanceof Response.SuccessListener) {
                    ((Response.SuccessListener) rawListener).onSuccess(response);
                }
            }

            @Override
            public void onHeaders(Response response) {
                if (rawListener instanceof Response.HeadersListener) {
                    ((Response.HeadersListener) rawListener).onHeaders(response);
                }
            }

            @Override
            public boolean onHeader(Response response, HttpField httpField) {
                if (rawListener instanceof Response.HeaderListener) {
                    return ((Response.HeaderListener) rawListener).onHeader(response, httpField);
                }
                return true;
            }

            @Override
            public void onFailure(Response response, Throwable throwable) {
                if (rawListener instanceof Response.FailureListener) {
                    ((Response.FailureListener) rawListener).onFailure(response, throwable);
                }
            }

            @Override
            public void onContent(Response response, ByteBuffer byteBuffer) {
                if (rawListener instanceof Response.ContentListener) {
                    ((Response.ContentListener) rawListener).onContent(response, byteBuffer);
                }
            }

            @Override
            public void onComplete(Result result) {
                //
                // metrics
                //
                if (result.isFailed()) {
                    metricCollector.addExceptionRequest(result.getRequest().getURI().toString(),
                                                        result.getRequest().getMethod(),
                                                        System.nanoTime() - startAt);
                } else {
                    metricCollector.addRequest(result.getRequest().getURI().toString(),
                                               result.getRequest().getMethod(),
                                               result.getResponse().getStatus(),
                                               System.nanoTime() - startAt);
                }

                //
                // trace
                //
                try {
                    span.tag(result.getFailure())
                        .tag("status", String.valueOf(result.getResponse().getStatus()))
                        .finish();
                    span.context().finish();
                } catch (Throwable ignored) {
                }

                if (rawListener instanceof Response.CompleteListener) {
                    ((Response.CompleteListener) rawListener).onComplete(result);
                }
            }

            @Override
            public void onBegin(Response response) {
                if (rawListener instanceof Response.BeginListener) {
                    ((Response.BeginListener) rawListener).onBegin(response);
                }
            }

            @Override
            public void onContent(Response response, ByteBuffer byteBuffer, Callback callback) {
                if (rawListener instanceof Response.AsyncContentListener) {
                    ((Response.AsyncContentListener) rawListener).onContent(response, byteBuffer, callback);
                }
            }
        };

        return super.onMethodEnter(aopContext);
    }

}

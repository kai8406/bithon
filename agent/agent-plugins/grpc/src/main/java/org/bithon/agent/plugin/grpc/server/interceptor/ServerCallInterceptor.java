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

package org.bithon.agent.plugin.grpc.server.interceptor;

import io.grpc.Context;
import io.grpc.Contexts;
import io.grpc.ForwardingServerCall;
import io.grpc.ForwardingServerCallListener;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.grpc.Status;
import org.bithon.agent.core.tracing.Tracer;
import org.bithon.agent.core.tracing.context.ITraceContext;
import org.bithon.agent.core.tracing.context.ITraceSpan;
import org.bithon.agent.core.tracing.context.TraceContextHolder;
import org.bithon.agent.core.tracing.propagation.extractor.ITraceContextExtractor;
import org.bithon.component.commons.tracing.SpanKind;

/**
 * @author Frank Chen
 */
public class ServerCallInterceptor implements ServerInterceptor {

    private final ITraceContextExtractor contextExtractor;

    /**
     * Declare the ctor as public to allow reflection without setting extra flags to access it
     */
    public ServerCallInterceptor(ITraceContextExtractor contextExtractor) {
        this.contextExtractor = contextExtractor;
    }

    @Override
    public <REQ, RSP> ServerCall.Listener<REQ> interceptCall(ServerCall<REQ, RSP> call,
                                                             Metadata headers,
                                                             ServerCallHandler<REQ, RSP> next) {
        ITraceContext context = this.contextExtractor.extract(headers,
                                                              (request, key) -> request.get(Metadata.Key.of(key, Metadata.ASCII_STRING_MARSHALLER)));
        if (context == null) {
            return next.startCall(call, headers);
        }

        ITraceSpan rootSpan = context.reporter(Tracer.get().reporter())
                                     .currentSpan()
                                     .component("grpc-server")
                                     .kind(SpanKind.SERVER)
                                     .clazz(call.getMethodDescriptor().getServiceName())
                                     .method(call.getMethodDescriptor().getBareMethodName())
                                     .tag("uri", "grpc://" + call.getMethodDescriptor().getFullMethodName())
                                     .start();

        return new TracedServerCall<>(call, rootSpan).start(headers, next);
    }

    static class TracedServerCall<REQ, RSP> extends ForwardingServerCall.SimpleForwardingServerCall<REQ, RSP> {
        private final ITraceSpan rootSpan;

        TracedServerCall(ServerCall<REQ, RSP> delegate, ITraceSpan rootSpan) {
            super(delegate);
            this.rootSpan = rootSpan;
        }

        ServerCall.Listener<REQ> start(Metadata headers, ServerCallHandler<REQ, RSP> next) {
            return new TracedServerCallListener<>(rootSpan,
                                                  Contexts.interceptCall(Context.current(), this, headers, next));
        }

        @Override
        public void sendMessage(RSP message) {
            super.sendMessage(message);
        }

        @Override
        public void close(Status status, Metadata trailers) {
            try {
                delegate().close(status, trailers);
            } catch (Throwable e) {
                rootSpan.tag(e);
                throw e;
            }
            String code = status.isOk() ? "200" : status.getCode().toString();
            rootSpan.tag("status", code).tag(status.getCause());
        }
    }

    static class TracedServerCallListener<REQ> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<REQ> {
        private final ITraceSpan rootSpan;

        TracedServerCallListener(ITraceSpan rootSpan, ServerCall.Listener<REQ> delegate) {
            super(delegate);
            this.rootSpan = rootSpan;
        }

        @Override
        public void onMessage(REQ message) {
            try {
                delegate().onMessage(message);
            } catch (Throwable t) {
                rootSpan.tag(t);
                throw t;
            }
        }

        @Override
        public void onHalfClose() {
            TraceContextHolder.set(rootSpan.context());

            // Overwrite the default thread name initialized in TraceContextFactory when its context is set up
            rootSpan.tag("thread", Thread.currentThread().getName());
            try {
                delegate().onHalfClose();
            } catch (Throwable t) {
                rootSpan.tag(t);
                // If exception occurs, the onComplete will be called at last
                throw t;
            } finally {
                TraceContextHolder.remove();
            }
        }

        @Override
        public void onCancel() {
            try {
                delegate().onCancel();
            } catch (Throwable t) {
                rootSpan.tag(t);
                throw t;
            } finally {
                rootSpan.finish();
                rootSpan.context().finish();
            }
        }

        @Override
        public void onComplete() {
            try {
                delegate().onComplete();
            } catch (Throwable t) {
                rootSpan.tag(t);
                throw t;
            } finally {
                rootSpan.finish();
                rootSpan.context().finish();
            }
        }

        @Override
        public void onReady() {
            try {
                delegate().onReady();
            } catch (Throwable t) {
                rootSpan.tag(t).finish();
                rootSpan.context().finish();
                throw t;
            }
        }
    }
}

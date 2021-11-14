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

package org.bithon.agent.plugin.undertow;

import org.bithon.agent.core.aop.descriptor.InterceptorDescriptor;
import org.bithon.agent.core.aop.descriptor.MethodPointCutDescriptorBuilder;
import org.bithon.agent.core.plugin.IPlugin;

import java.util.Arrays;
import java.util.List;

import static org.bithon.agent.core.aop.descriptor.InterceptorDescriptorBuilder.forClass;

/**
 * @author frankchen
 */
public class UndertowPlugin implements IPlugin {

    @Override
    public List<InterceptorDescriptor> getInterceptors() {

        return Arrays.asList(
            forClass("io.undertow.Undertow")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("start")
                                                   .noArgs()
                                                   .to("org.bithon.agent.plugin.undertow.interceptor.UndertowStart")
                ),

            forClass("io.undertow.server.protocol.http.HttpOpenListener")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("setRootHandler")
                                                   .onArgs("io.undertow.server.HttpHandler")
                                                   .to("org.bithon.agent.plugin.undertow.interceptor.HttpOpenListenerSetRootHandler")
                ),

            forClass("io.undertow.server.HttpServerExchange")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("dispatch")
                                                   .onArgs("java.util.concurrent.Executor",
                                                           "io.undertow.server.HttpHandler")
                                                   .to("org.bithon.agent.plugin.undertow.interceptor.HttpServerExchangeDispatch")
                ),

            forClass("io.undertow.servlet.api.LoggingExceptionHandlerHandleThrowable")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("handleThrowable")
                                                   .onArgs("io.undertow.server.HttpServerExchange",
                                                           "javax.servlet.ServletRequest",
                                                           "javax.servlet.ServletResponse",
                                                           "java.lang.Throwable")
                                                   .to("org.bithon.agent.plugin.undertow.interceptor.LoggingExceptionHandlerHandleThrowable")
                )
        );
    }
}
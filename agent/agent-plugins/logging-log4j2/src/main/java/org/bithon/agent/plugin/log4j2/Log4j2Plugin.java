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

package org.bithon.agent.plugin.log4j2;

import org.bithon.agent.instrumentation.aop.interceptor.descriptor.InterceptorDescriptor;
import org.bithon.agent.instrumentation.aop.interceptor.plugin.IPlugin;

import java.util.Arrays;
import java.util.List;

import static org.bithon.agent.instrumentation.aop.interceptor.descriptor.InterceptorDescriptorBuilder.forClass;

/**
 * @author frankchen
 */
public class Log4j2Plugin implements IPlugin {

    @Override
    public List<InterceptorDescriptor> getInterceptors() {
        return Arrays.asList(
            forClass("org.apache.logging.log4j.core.Logger")
                .onMethod("logMessage")
                .andArgs(1, "org.apache.logging.log4j.Level")
                .andArgs(4, "java.lang.Throwable")
                .interceptedBy("org.bithon.agent.plugin.log4j2.interceptor.Logger$LogMessage")
                .build(),

            forClass("org.apache.logging.log4j.core.pattern.PatternParser")
                /**
                 * {@link org.apache.logging.log4j.core.pattern.PatternParser#PatternParser(org.apache.logging.log4j.core.config.Configuration, String, Class, Class)}
                 */
                .onConstructor()
                .andArgsSize(4)
                .interceptedBy("org.bithon.agent.plugin.log4j2.interceptor.PatternParser$Ctor")

                /**
                 * {@link org.apache.logging.log4j.core.pattern.PatternParser#parse(String, List, List, boolean, boolean, boolean)}
                 */
                .onMethod("parse")
                .andArgsSize(6)
                .andArgs(0, "java.lang.String")
                .interceptedBy("org.bithon.agent.plugin.log4j2.interceptor.PatternParser$Parse")
                .build()
        );
    }
}

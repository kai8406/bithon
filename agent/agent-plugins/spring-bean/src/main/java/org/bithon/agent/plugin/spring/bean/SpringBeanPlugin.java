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

package org.bithon.agent.plugin.spring.bean;

import org.bithon.agent.core.interceptor.descriptor.InterceptorDescriptor;
import org.bithon.agent.core.interceptor.descriptor.MethodPointCutDescriptorBuilder;
import org.bithon.agent.core.interceptor.plugin.IPlugin;

import java.util.Collections;
import java.util.List;

import static org.bithon.agent.core.interceptor.descriptor.InterceptorDescriptorBuilder.forClass;

/**
 * @author frankchen
 */
public class SpringBeanPlugin implements IPlugin {

    @Override
    public List<InterceptorDescriptor> getInterceptors() {
        return Collections.singletonList(
            forClass("org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory")
                .methods(
                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("createBeanInstance")
                                                   .to("org.bithon.agent.plugin.spring.bean.interceptor.AbstractAutowireCapableBeanFactory$CreateInstance"),

                    MethodPointCutDescriptorBuilder.build()
                                                   .onAllMethods("applyBeanPostProcessorsBeforeInstantiation")
                                                   .to("org.bithon.agent.plugin.spring.bean.interceptor.AbstractAutowireCapableBeanFactory$ApplyBeanPostProcessor")
                )
        );
    }
}

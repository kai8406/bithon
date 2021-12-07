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

package org.bithon.agent.plugin.guice.interceptor;

import org.bithon.agent.bootstrap.aop.AbstractInterceptor;
import org.bithon.agent.bootstrap.aop.AopContext;
import org.bithon.agent.plugin.guice.installer.BeanMethodAopInstallerHelper;

/**
 * {@link com.google.inject.internal.BindingBuilder#to(Class)}
 *
 * @author frank.chen021@outlook.com
 * @date 2021/12/06 22:48
 */
public class BindingBuilder$To extends AbstractInterceptor {

    @Override
    public boolean initialize() {
        BeanMethodAopInstallerHelper.initialize();
        return true;
    }

    /**
     * Re-transform the class of the bean
     */
    @Override
    public void onMethodLeave(AopContext aopContext) {
        Class<?> clazz = aopContext.getArgAs(0);
        if (clazz != null) {
            BeanMethodAopInstallerHelper.install(clazz);
        }
    }
}

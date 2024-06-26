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

package org.bithon.agent.plugin.redis.lettuce.interceptor;


import org.bithon.agent.instrumentation.aop.IBithonObject;
import org.bithon.agent.instrumentation.aop.context.AopContext;
import org.bithon.agent.instrumentation.aop.interceptor.declaration.AfterInterceptor;

/**
 * {@link io.lettuce.core.DefaultConnectionFuture#get}
 *
 * Copy the target information to the returned object
 *
 * @author frankchen
 */
public class DefaultConnectionFuture$Get extends AfterInterceptor {

    @Override
    public void after(AopContext aopContext) {
        Object result = aopContext.getReturning();
        if (result instanceof IBithonObject && aopContext.getTarget() instanceof IBithonObject) {
            ((IBithonObject) result).setInjectedObject(((IBithonObject) aopContext.getTarget()).getInjectedObject());
        }
    }
}

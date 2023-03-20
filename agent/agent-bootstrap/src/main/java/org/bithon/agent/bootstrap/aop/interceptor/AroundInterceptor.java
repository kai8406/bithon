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

package org.bithon.agent.bootstrap.aop.interceptor;

import org.bithon.agent.bootstrap.aop.context.AopContext;

/**
 * @author frank.chen021@outlook.com
 * @date 2023/3/18 23:25
 */
public abstract class AroundInterceptor implements IInterceptor {

    public InterceptionDecision before(AopContext aopContext) throws Exception {
        return InterceptionDecision.CONTINUE;
    }

    /**
     * Called after execution of target intercepted method
     * If {@link #before(AopContext)} returns {@link InterceptionDecision#SKIP_LEAVE}, call of this method will be skipped
     */
    public void after(AopContext aopContext) throws Exception {
    }
}

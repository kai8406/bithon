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

package org.bithon.agent.instrumentation.aop.interceptor;

/**
 * @author frank.chen021@outlook.com
 * @date 2023/3/18 23:26
 */
public abstract class ReplaceInterceptor implements IInterceptor {
    /**
     * Replacement of a target method.
     * Will be executed only when the interceptor is defined as replacement
     *
     * @param returning the returning object of target method
     */
    public Object execute(Object[] args, Object returning) {
        return returning;
    }
}

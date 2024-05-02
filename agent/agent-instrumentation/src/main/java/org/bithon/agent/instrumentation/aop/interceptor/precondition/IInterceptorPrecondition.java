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

package org.bithon.agent.instrumentation.aop.interceptor.precondition;

import org.bithon.shaded.net.bytebuddy.description.type.TypeDescription;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/17 8:13 下午
 */
public interface IInterceptorPrecondition {

    /**
     * Helper method
     */
    static IInterceptorPrecondition isClassDefined(String className) {
        return new IsClassDefinedPrecondition(className);
    }

    static IInterceptorPrecondition or(IInterceptorPrecondition... conditions) {
        return new OrPrecondition(conditions);
    }

    static IInterceptorPrecondition and(IInterceptorPrecondition... conditions) {
        return new AndPrecondition(conditions);
    }

    static IInterceptorPrecondition not(IInterceptorPrecondition condition) {
        return new NotPrecondition(condition);
    }

    /**
     * returns true if interceptors in this plugin can be installed
     *
     * @param classLoader     Current class loader that is loading the target {@param typeDescription}
     * @param typeDescription The type that is being instrumented
     */
    boolean matches(ClassLoader classLoader, TypeDescription typeDescription);
}

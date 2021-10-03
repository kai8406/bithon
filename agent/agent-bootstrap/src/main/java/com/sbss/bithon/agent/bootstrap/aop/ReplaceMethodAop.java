/*
 *    Copyright 2020 bithon.cn
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

package com.sbss.bithon.agent.bootstrap.aop;

import shaded.net.bytebuddy.implementation.bind.annotation.AllArguments;
import shaded.net.bytebuddy.implementation.bind.annotation.RuntimeType;

/**
 * @author Frank Chen
 * @date 3/10/21 16:01
 */
public class ReplaceMethodAop {

    private final IReplacementInterceptor interceptor;

    public ReplaceMethodAop(IReplacementInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @RuntimeType
    public Object intercept(@AllArguments Object[] args) throws Exception {
        return interceptor.onExecute(args);
    }
}

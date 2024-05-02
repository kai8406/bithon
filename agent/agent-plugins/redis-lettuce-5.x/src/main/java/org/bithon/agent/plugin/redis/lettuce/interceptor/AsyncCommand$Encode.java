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

import io.lettuce.core.protocol.AsyncCommand;
import io.netty.buffer.ByteBuf;
import org.bithon.agent.instrumentation.aop.IBithonObject;
import org.bithon.agent.instrumentation.aop.context.AopContext;
import org.bithon.agent.instrumentation.aop.interceptor.InterceptionDecision;
import org.bithon.agent.instrumentation.aop.interceptor.declaration.AroundInterceptor;
import org.bithon.agent.plugin.redis.lettuce.LettuceAsyncContext;

/**
 * {@link io.lettuce.core.protocol.AsyncCommand#encode(ByteBuf)}
 *
 * @author frank.chen021@outlook.com
 * @date 30/4/24 2:07 pm
 */
public class AsyncCommand$Encode extends AroundInterceptor {

    @Override
    public InterceptionDecision before(AopContext aopContext) {
        AsyncCommand<?, ?, ?> command = aopContext.getTargetAs();
        if (!(command instanceof IBithonObject)) {
            return InterceptionDecision.SKIP_LEAVE;
        }
        if (((IBithonObject) command).getInjectedObject() == null) {
            // the Command is not initialized in AbstractRedisAsyncCommands.dispatch
            return InterceptionDecision.SKIP_LEAVE;
        }

        ByteBuf buf = aopContext.getArgAs(0);
        aopContext.setUserContext(buf.writerIndex());
        return InterceptionDecision.CONTINUE;
    }

    @Override
    public void after(AopContext aopContext) {
        int before = aopContext.getUserContext();
        ByteBuf after = aopContext.getArgAs(0);
        int size = after.writerIndex() - before;
        if (size <= 0) {
            return;
        }

        AsyncCommand<?, ?, ?> command = aopContext.getTargetAs();
        LettuceAsyncContext asyncContext = (LettuceAsyncContext) ((IBithonObject) command).getInjectedObject();
        asyncContext.setRequestSize(size);
    }
}

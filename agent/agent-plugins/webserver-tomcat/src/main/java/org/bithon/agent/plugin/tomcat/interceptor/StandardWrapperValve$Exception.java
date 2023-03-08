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

package org.bithon.agent.plugin.tomcat.interceptor;

import org.bithon.agent.bootstrap.aop.AbstractInterceptor;
import org.bithon.agent.bootstrap.aop.AopContext;
import org.bithon.agent.bootstrap.aop.InterceptionDecision;
import org.bithon.agent.core.context.InterceptorContext;
import org.bithon.agent.core.event.ExceptionCollector;

import java.util.Collections;

/**
 * handle exception thrown by tomcat service, not by the tomcat itself
 *
 * @author frankchen
 */
public class StandardWrapperValve$Exception extends AbstractInterceptor {

    @Override
    public InterceptionDecision onMethodEnter(AopContext context) {
        String uri = (String) InterceptorContext.get(InterceptorContext.KEY_URI);
        if (uri == null) {
            ExceptionCollector.collect((Throwable) context.getArgs()[2]);
        } else {
            ExceptionCollector.collect((Throwable) context.getArgs()[2],
                                       Collections.singletonMap("uri", uri));
        }

        return InterceptionDecision.SKIP_LEAVE;
    }
}

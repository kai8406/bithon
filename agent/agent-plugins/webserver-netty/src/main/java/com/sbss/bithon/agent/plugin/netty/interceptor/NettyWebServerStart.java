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

package com.sbss.bithon.agent.plugin.netty.interceptor;

import com.sbss.bithon.agent.bootstrap.aop.AbstractInterceptor;
import com.sbss.bithon.agent.bootstrap.aop.AopContext;
import com.sbss.bithon.agent.core.context.AgentContext;
import org.springframework.boot.web.embedded.netty.NettyWebServer;

/**
 * @author frankchen
 * @date 2021-09-22 23:36
 */
public class NettyWebServerStart extends AbstractInterceptor {

    @Override
    public void onMethodLeave(AopContext aopContext) {
        NettyWebServer webServer = (NettyWebServer) aopContext.getTarget();

        AgentContext.getInstance().getAppInstance().setPort(webServer.getPort());
    }
}

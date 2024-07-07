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

package org.bithon.agent.plugin.apache.kafka.admin.interceptor;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.bithon.agent.instrumentation.aop.IBithonObject;
import org.bithon.agent.instrumentation.aop.context.AopContext;
import org.bithon.agent.instrumentation.aop.interceptor.declaration.AfterInterceptor;
import org.bithon.agent.plugin.apache.kafka.KafkaPluginContext;
import org.bithon.component.commons.utils.ReflectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * {@link org.apache.kafka.clients.admin.AdminClient#create(Properties)}
 * {@link org.apache.kafka.clients.admin.AdminClient#create(Map)}
 *
 * @author Frank Chen
 * @date 2/1/24 11:13 am
 */
public class AdminClient$Create extends AfterInterceptor {
    @Override
    public void after(AopContext aopContext) throws Exception {
        if (aopContext.hasException()) {
            return;
        }

        AdminClientConfig clientProperties = new AdminClientConfig(aopContext.getArgAs(0));
        List<String> bootstrapServers = clientProperties.getList(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG);

        KafkaPluginContext ctx = new KafkaPluginContext();
        ctx.clientId = (String) ReflectionUtils.getFieldValue(aopContext.getReturning(), "clientId");
        ctx.broker = bootstrapServers.stream()
                                     .sorted()
                                     .findFirst()
                                     .get();

        IBithonObject bithonObject = aopContext.getReturningAs();
        bithonObject.setInjectedObject(ctx);

        Object networkClient = ReflectionUtils.getFieldValue(aopContext.getReturning(), "client");
        if (networkClient instanceof IBithonObject) {
            ((IBithonObject) networkClient).setInjectedObject(ctx);
        }
    }
}

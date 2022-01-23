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

package org.bithon.agent.plugin.mongodb38.interceptor;

import com.mongodb.async.SingleResultCallback;
import com.mongodb.internal.connection.CommandProtocol;
import com.mongodb.internal.connection.DefaultServerConnection;
import com.mongodb.internal.connection.LegacyProtocol;
import com.mongodb.session.SessionContext;
import org.bithon.agent.bootstrap.aop.AbstractInterceptor;
import org.bithon.agent.bootstrap.aop.AopContext;
import org.bithon.agent.bootstrap.aop.InterceptionDecision;
import org.bithon.agent.core.metric.domain.mongo.MongoDbMetricRegistry;

/**
 * @author frankchen
 */
public class DefaultServerConnectionExecuteProtocolAsync extends AbstractInterceptor {
    private final MongoDbMetricRegistry metricRegistry = MongoDbMetricRegistry.get();

    /**
     * {@link DefaultServerConnection#executeProtocolAsync(LegacyProtocol, SingleResultCallback)}
     * {@link DefaultServerConnection#executeProtocolAsync(CommandProtocol, SessionContext, SingleResultCallback)}
     */
    @Override
    public InterceptionDecision onMethodEnter(AopContext aopContext) throws Exception {
        // TODO: WRAP callback
        return super.onMethodEnter(aopContext);
    }
}

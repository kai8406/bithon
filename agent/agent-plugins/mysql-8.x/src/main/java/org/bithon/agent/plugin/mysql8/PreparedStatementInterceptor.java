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

package org.bithon.agent.plugin.mysql8;

import org.bithon.agent.bootstrap.aop.AbstractInterceptor;
import org.bithon.agent.bootstrap.aop.AopContext;
import org.bithon.agent.bootstrap.aop.InterceptionDecision;
import org.bithon.agent.core.metric.collector.MetricCollectorManager;
import org.bithon.agent.core.metric.domain.sql.SQLMetrics;
import org.bithon.agent.core.metric.domain.sql.SqlMetricCollector;
import org.bithon.agent.core.utils.MiscUtils;

import java.sql.Statement;

/**
 * @author frankchen
 */
public class PreparedStatementInterceptor extends AbstractInterceptor {
    private SqlMetricCollector sqlMetricCollector;
    private SqlStatementMetricCollector statementCollector;

    @Override
    public boolean initialize() {
        sqlMetricCollector = MetricCollectorManager.getInstance()
                                                   .getOrRegister("mysql8-metrics", SqlMetricCollector.class);
        statementCollector = SqlStatementMetricCollector.getInstance();
        return true;
    }

    @Override
    public InterceptionDecision onMethodEnter(AopContext aopContext) throws Exception {
        Statement statement = (Statement) aopContext.getTarget();

        // get the connection info before execution since the connection might be closed during execution
        aopContext.setUserContext(MiscUtils.cleanupConnectionString(statement.getConnection()
                                                                             .getMetaData()
                                                                             .getURL()));
        return InterceptionDecision.CONTINUE;
    }

    @Override
    public void onMethodLeave(AopContext aopContext) {
        String methodName = aopContext.getMethod().getName();
        String connectionString = aopContext.castUserContextAs();

        SQLMetrics metric = sqlMetricCollector.getOrCreateMetrics(connectionString);
        boolean isQuery = true;
        if (MySql8Plugin.METHOD_EXECUTE_UPDATE.equals(methodName)
            || MySql8Plugin.METHOD_EXECUTE_UPDATE_INTERNAL.equals(methodName)) {
            isQuery = false;
        } else if ((MySql8Plugin.METHOD_EXECUTE.equals(methodName) || MySql8Plugin.METHOD_EXECUTE_INTERNAL.equals(
            methodName))) {
            Object result = aopContext.getReturning();
            if (result instanceof Boolean && !(boolean) result) {
                isQuery = false;
            }
        }
        metric.update(isQuery, aopContext.hasException(), aopContext.getCostTime());


        statementCollector.update(aopContext);
    }
}
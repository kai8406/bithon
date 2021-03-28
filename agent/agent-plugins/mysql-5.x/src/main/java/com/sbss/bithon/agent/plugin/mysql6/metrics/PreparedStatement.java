package com.sbss.bithon.agent.plugin.mysql6.metrics;

import com.sbss.bithon.agent.core.metric.collector.MetricCollectorManager;
import com.sbss.bithon.agent.core.metric.domain.sql.SqlMetricCollector;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AbstractInterceptor;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AopContext;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.InterceptionDecision;
import com.sbss.bithon.agent.core.utils.MiscUtils;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author frankchen
 */
public class PreparedStatement {

    static abstract class AbstractExecute extends AbstractInterceptor {
        private SqlMetricCollector sqlMetricCollector;
        private StatementMetricCollector statementMetricCollector;

        @Override
        public boolean initialize() {
            sqlMetricCollector = MetricCollectorManager.getInstance()
                                                       .getOrRegister("mysql-metrics", SqlMetricCollector.class);
            statementMetricCollector = StatementMetricCollector.getInstance();
            return true;
        }

        @Override
        public InterceptionDecision onMethodEnter(AopContext aopContext) {
            try {
                Statement statement = (Statement) aopContext.getTarget();
                String connectionString = statement.getConnection().getMetaData().getURL();

                aopContext.setUserContext(MiscUtils.cleanupConnectionString(connectionString));
            } catch (SQLException ignored) {
                return InterceptionDecision.SKIP_LEAVE;
            }
            return InterceptionDecision.CONTINUE;
        }

        @Override
        public void onMethodLeave(AopContext aopContext) {
            String connectionString = aopContext.castUserContextAs();
            if (connectionString == null) {
                return;
            }

            sqlMetricCollector.getOrCreateMetric(connectionString)
                              .update(isQuery(aopContext), aopContext.hasException(), aopContext.getCostTime());


            statementMetricCollector.sqlStats(aopContext, (String) aopContext.getUserContext());
        }

        protected abstract boolean isQuery(AopContext aopContext);
    }

    /**
     * {@link com.mysql.jdbc.PreparedStatement#execute()}
     */
    public static class Execute extends AbstractExecute {
        @Override
        protected boolean isQuery(AopContext aopContext) {
            Object result = aopContext.castReturningAs();
            if (result instanceof Boolean && !(boolean) result) {
                return false;
            } else {
                return true;
            }
        }
    }

    /**
     * {@link com.mysql.jdbc.PreparedStatement#executeQuery()}
     */
    public static class ExecuteQuery extends AbstractExecute {
        @Override
        protected boolean isQuery(AopContext aopContext) {
            return true;
        }
    }

    /**
     * {@link com.mysql.jdbc.PreparedStatement#executeUpdate()}
     */
    public static class ExecuteUpdate extends AbstractExecute {
        @Override
        protected boolean isQuery(AopContext aopContext) {
            return false;
        }
    }
}

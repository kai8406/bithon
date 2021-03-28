package com.sbss.bithon.agent.plugin.mysql6.metrics;

import com.sbss.bithon.agent.core.metric.collector.MetricCollectorManager;
import com.sbss.bithon.agent.core.metric.domain.sql.SqlMetricCollector;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AbstractInterceptor;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AopContext;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.InterceptionDecision;
import com.sbss.bithon.agent.core.utils.MiscUtils;

import java.sql.SQLException;

/**
 * @author frankchen
 */
public class Statement {

    static abstract class AbstractExecute extends AbstractInterceptor {
        private SqlMetricCollector sqlMetricCollector;

        @Override
        public boolean initialize() {
            sqlMetricCollector = MetricCollectorManager.getInstance()
                                                       .getOrRegister("mysql-metrics", SqlMetricCollector.class);
            return true;
        }

        @Override
        public InterceptionDecision onMethodEnter(AopContext aopContext) {
            try {
                java.sql.Statement statement = (java.sql.Statement) aopContext.getTarget();
                String connectionString = MiscUtils.cleanupConnectionString(statement.getConnection()
                                                                                     .getMetaData()
                                                                                     .getURL());
                aopContext.setUserContext(connectionString);
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
        }

        protected abstract boolean isQuery(AopContext aopContext);
    }

    /**
     * {@link com.mysql.cj.jdbc.StatementImpl#executeInternal(String, boolean)}
     */
    public static class ExecuteInternal extends AbstractExecute {
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
     * {@link com.mysql.cj.jdbc.StatementImpl#executeQuery(String)}
     */
    public static class ExecuteQuery extends AbstractExecute {
        @Override
        protected boolean isQuery(AopContext aopContext) {
            return true;
        }
    }

    /**
     * {@link com.mysql.cj.jdbc.StatementImpl#executeUpdate(String)}
     */
    public static class ExecuteUpdate extends AbstractExecute {
        @Override
        protected boolean isQuery(AopContext aopContext) {
            return false;
        }
    }

    /**
     * {@link com.mysql.cj.jdbc.StatementImpl#executeUpdateInternal(String, boolean, boolean)}
     */
    public static class ExecuteUpdateInternal extends AbstractExecute {
        @Override
        protected boolean isQuery(AopContext aopContext) {
            return false;
        }
    }
}

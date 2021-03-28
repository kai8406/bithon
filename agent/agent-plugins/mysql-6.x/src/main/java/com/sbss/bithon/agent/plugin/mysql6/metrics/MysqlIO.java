package com.sbss.bithon.agent.plugin.mysql6.metrics;

import com.mysql.cj.core.result.Field;
import com.mysql.cj.jdbc.StatementImpl;
import com.mysql.cj.mysqla.io.Buffer;
import com.sbss.bithon.agent.core.metric.collector.MetricCollectorManager;
import com.sbss.bithon.agent.core.metric.domain.sql.SqlMetricCollector;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AbstractInterceptor;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AopContext;

import java.sql.SQLException;

/**
 * MySQL IO
 *
 * @author frankchen
 */
public class MysqlIO {

    /**
     * {@link com.mysql.cj.jdbc.MysqlIO#readAllResults(StatementImpl, int, int, int, boolean, String, Buffer, boolean, long, Field[])}
     */
    public static class ReadAllResults extends AbstractInterceptor {

        SqlMetricCollector metricCollector;

        @Override
        public boolean initialize() throws Exception {
            metricCollector = MetricCollectorManager.getInstance()
                                                    .getOrRegister("mysql-metrics", SqlMetricCollector.class);
            return super.initialize();
        }

        @Override
        public void onMethodLeave(AopContext aopContext) throws SQLException {
//            com.mysql.cj.jdbc.MysqlIO mysqlIO = aopContext.castTargetAs();
//
//            JdbcConnection connection = (JdbcConnection) ReflectionUtils.getFieldValue(mysqlIO, "connection");
//
//            SqlCompositeMetric metric = metricCollector.getOrCreateMetric(MiscUtils.cleanupConnectionString(connection.getURL()));
//
//            ResultSetImpl resultSet = aopContext.castReturningAs();
//
            //TODO: getBytesSize not provided
//            int bytesIn = resultSet.getBytesSize();
//            if (bytesIn > 0) {
//                metric.updateBytesIn(bytesIn);
//            }
        }
    }
}

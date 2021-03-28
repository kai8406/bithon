package com.sbss.bithon.agent.plugin.mysql.metrics;

import com.mysql.jdbc.Buffer;
import com.mysql.jdbc.Field;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.ResultSetImpl;
import com.mysql.jdbc.StatementImpl;
import com.sbss.bithon.agent.core.metric.collector.MetricCollectorManager;
import com.sbss.bithon.agent.core.metric.domain.sql.SqlCompositeMetric;
import com.sbss.bithon.agent.core.metric.domain.sql.SqlMetricCollector;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AbstractInterceptor;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AopContext;
import com.sbss.bithon.agent.core.utils.MiscUtils;
import com.sbss.bithon.agent.core.utils.ReflectionUtils;

import java.sql.SQLException;

/**
 * MySQL IO
 *
 * @author frankchen
 */
public class MysqlIO {

    /**
     * {@link com.mysql.jdbc.MysqlIO#sendCommand(int, String, Buffer, boolean, String, int)}
     */
    public static class SendCommand extends AbstractInterceptor {

        SqlMetricCollector metricCollector;

        @Override
        public boolean initialize() throws Exception {
            metricCollector = MetricCollectorManager.getInstance()
                                                    .getOrRegister("mysql-metrics", SqlMetricCollector.class);
            return super.initialize();
        }

        @Override
        public void onMethodLeave(AopContext aopContext) {

            com.mysql.jdbc.MysqlIO mysqlIO = aopContext.castTargetAs();

            MySQLConnection connection = (MySQLConnection) ReflectionUtils.getFieldValue(mysqlIO, "connection");

            SqlCompositeMetric metric = metricCollector.getOrCreateMetric(MiscUtils.cleanupConnectionString(connection.getURL()));

            Buffer queryPacket = (Buffer) aopContext.getArgs()[2];
            if (queryPacket != null) {
                metric.updateBytesOut(queryPacket.getPosition());
            }
        }
    }

    /**
     * {@link com.mysql.jdbc.MysqlIO#readAllResults(StatementImpl, int, int, int, boolean, String, Buffer, boolean, long, Field[])}
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

            com.mysql.jdbc.MysqlIO mysqlIO = aopContext.castTargetAs();

            MySQLConnection connection = (MySQLConnection) ReflectionUtils.getFieldValue(mysqlIO, "connection");

            SqlCompositeMetric metric = metricCollector.getOrCreateMetric(MiscUtils.cleanupConnectionString(connection.getURL()));

            ResultSetImpl resultSet = aopContext.castReturningAs();
            int bytesIn = resultSet.getBytesSize();
            if (bytesIn > 0) {
                metric.updateBytesIn(bytesIn);
            }
        }
    }
}

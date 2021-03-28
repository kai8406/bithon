package com.sbss.bithon.agent.plugin.mysql6.metrics;

import com.mysql.cj.api.jdbc.JdbcConnection;
import com.mysql.cj.mysqla.io.Buffer;
import com.sbss.bithon.agent.core.metric.collector.MetricCollectorManager;
import com.sbss.bithon.agent.core.metric.domain.sql.SqlCompositeMetric;
import com.sbss.bithon.agent.core.metric.domain.sql.SqlMetricCollector;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AbstractInterceptor;
import com.sbss.bithon.agent.core.plugin.aop.bootstrap.AopContext;
import com.sbss.bithon.agent.core.utils.MiscUtils;
import com.sbss.bithon.agent.core.utils.ReflectionUtils;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/3/28 23:10
 */
public class MysqlaProtocol {
    /**
     * {@link com.mysql.cj.mysqla.io.MysqlaProtocol#sendCommand(int, String, Buffer, boolean, String, int)}
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

            com.mysql.cj.jdbc.MysqlIO mysqlIO = aopContext.castTargetAs();

            JdbcConnection connection = (JdbcConnection) ReflectionUtils.getFieldValue(mysqlIO, "connection");

            SqlCompositeMetric metric = metricCollector.getOrCreateMetric(MiscUtils.cleanupConnectionString(connection.getURL()));

            Buffer queryPacket = (Buffer) aopContext.getArgs()[2];
            if (queryPacket != null) {
                metric.updateBytesOut(queryPacket.getPosition());
            }
        }
    }
}

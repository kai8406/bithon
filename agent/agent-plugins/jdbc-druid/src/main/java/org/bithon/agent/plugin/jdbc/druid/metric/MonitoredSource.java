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

package org.bithon.agent.plugin.jdbc.druid.metric;

import com.alibaba.druid.pool.DruidDataSource;
import org.bithon.agent.core.metric.domain.jdbc.JdbcPoolMetrics;
import org.bithon.agent.core.metric.domain.sql.SQLMetrics;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/27 11:32 上午
 */
public class MonitoredSource {
    private final DruidDataSource dataSource;
    private final String driverClass;

    // dimension
    private final String connectionString;

    // metrics
    private final JdbcPoolMetrics jdbcPoolMetrics;
    private final SQLMetrics sqlMetrics;

    MonitoredSource(String driverClass,
                    String connectionString,
                    DruidDataSource dataSource) {
        this.dataSource = dataSource;
        this.driverClass = driverClass;
        this.connectionString = connectionString;
        this.jdbcPoolMetrics = new JdbcPoolMetrics(connectionString, driverClass);
        this.sqlMetrics = new SQLMetrics();
    }

    public DruidDataSource getDataSource() {
        return dataSource;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getConnectionString() {
        return connectionString;
    }

    public JdbcPoolMetrics getJdbcMetric() {
        return jdbcPoolMetrics;
    }

    public SQLMetrics getSqlMetric() {
        return sqlMetrics;
    }
}
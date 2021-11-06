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

package org.bithon.agent.core.dispatcher;

import org.bithon.agent.core.event.EventMessage;
import org.bithon.agent.core.metric.collector.IMetricSet;
import org.bithon.agent.core.metric.domain.exception.ExceptionMetricSet;
import org.bithon.agent.core.metric.domain.jdbc.JdbcPoolMetricSet;
import org.bithon.agent.core.metric.domain.jvm.GcCompositeMetric;
import org.bithon.agent.core.metric.domain.jvm.JvmMetricSet;
import org.bithon.agent.core.metric.domain.mongo.MongoDbCompositeMetric;
import org.bithon.agent.core.metric.domain.redis.RedisClientCompositeMetric;
import org.bithon.agent.core.metric.domain.sql.SqlCompositeMetric;
import org.bithon.agent.core.metric.domain.sql.SqlStatementCompositeMetric;
import org.bithon.agent.core.metric.domain.thread.ThreadPoolCompositeMetric;
import org.bithon.agent.core.metric.domain.web.WebServerMetricSet;
import org.bithon.agent.core.metric.model.schema.Schema;
import org.bithon.agent.core.metric.model.schema.Schema2;
import org.bithon.agent.core.tracing.context.ITraceSpan;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/4 10:46 下午
 */
public interface IMessageConverter {

    Object from(long timestamp, int interval, JdbcPoolMetricSet metric);

    Object from(long timestamp, int interval, List<String> dimensions, SqlCompositeMetric metric);

    Object from(long timestamp,
                int interval,
                List<String> dimensions,
                MongoDbCompositeMetric counter);

    Object from(long timestamp, int interval, JvmMetricSet metric);

    Object from(long timestamp, int interval, WebServerMetricSet metric);

    Object from(long timestamp, int interval, SqlStatementCompositeMetric counter);

    Object from(long timestamp, int interval, List<String> dimensions, RedisClientCompositeMetric metric);

    Object from(long timestamp, int interval, ExceptionMetricSet metric);

    Object from(long timestamp, int interval, ThreadPoolCompositeMetric metric);

    // tracing span message
    Object from(ITraceSpan span);

    Object from(EventMessage event);

    Object from(Map<String, String> log);

    Object from(long timestamp, int interval, GcCompositeMetric gcMetricSet);

    Object from(Schema schema, Collection<IMetricSet> metricCollection, long timestamp, int interval);

    Object from(Schema2 schema, Collection<IMetricSet> metricCollection, long timestamp, int interval);
}

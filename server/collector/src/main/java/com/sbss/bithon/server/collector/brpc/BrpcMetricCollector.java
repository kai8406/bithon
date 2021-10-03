/*
 *    Copyright 2020 bithon.cn
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

package com.sbss.bithon.server.collector.brpc;


import com.sbss.bithon.agent.rpc.brpc.BrpcMessageHeader;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcExceptionMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcGenericMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcGenericMetricSet;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcHttpIncomingMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcHttpOutgoingMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcJdbcPoolMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcJvmGcMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcJvmMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcMongoDbMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcRedisMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcSqlMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcThreadPoolMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.BrpcWebServerMetricMessage;
import com.sbss.bithon.agent.rpc.brpc.metrics.IMetricCollector;
import com.sbss.bithon.server.collector.sink.IMessageSink;
import com.sbss.bithon.server.common.utils.ReflectionUtils;
import com.sbss.bithon.server.common.utils.collection.CloseableIterator;
import com.sbss.bithon.server.metric.DataSourceSchema;
import com.sbss.bithon.server.metric.TimestampSpec;
import com.sbss.bithon.server.metric.aggregator.spec.IMetricSpec;
import com.sbss.bithon.server.metric.aggregator.spec.LongMaxMetricSpec;
import com.sbss.bithon.server.metric.aggregator.spec.LongMinMetricSpec;
import com.sbss.bithon.server.metric.aggregator.spec.LongSumMetricSpec;
import com.sbss.bithon.server.metric.dimension.IDimensionSpec;
import com.sbss.bithon.server.metric.dimension.StringDimensionSpec;
import com.sbss.bithon.server.metric.handler.MetricMessage;
import com.sbss.bithon.server.metric.handler.SchemaMetricMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/10 2:37 下午
 */
@Slf4j
public class BrpcMetricCollector implements IMetricCollector {

    private final IMessageSink<SchemaMetricMessage> schemaMetricSink;
    private final IMessageSink<CloseableIterator<MetricMessage>> metricSink;

    public BrpcMetricCollector(IMessageSink<SchemaMetricMessage> schemaMetricSink,
                               IMessageSink<CloseableIterator<MetricMessage>> metricSink) {
        this.schemaMetricSink = schemaMetricSink;
        this.metricSink = metricSink;
    }

    @Override
    public void sendIncomingHttp(BrpcMessageHeader header, List<BrpcHttpIncomingMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("http-incoming-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendJvm(BrpcMessageHeader header, List<BrpcJvmMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("jvm-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendJvmGc(BrpcMessageHeader header, List<BrpcJvmGcMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("jvm-gc-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendWebServer(BrpcMessageHeader header, List<BrpcWebServerMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("web-server-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendException(BrpcMessageHeader header, List<BrpcExceptionMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("exception-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendOutgoingHttp(BrpcMessageHeader header, List<BrpcHttpOutgoingMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("http-outgoing-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendThreadPool(BrpcMessageHeader header, List<BrpcThreadPoolMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("thread-pool-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendJdbc(BrpcMessageHeader header, List<BrpcJdbcPoolMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("jdbc-pool-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendRedis(BrpcMessageHeader header, List<BrpcRedisMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("redis-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendSql(BrpcMessageHeader header, List<BrpcSqlMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("sql-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendMongoDb(BrpcMessageHeader header, List<BrpcMongoDbMetricMessage> messages) {
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }

        metricSink.process("mongodb-metrics", new GenericMetricMessageIterator(header, messages));
    }

    @Override
    public void sendGenericMetrics(BrpcMessageHeader header, BrpcGenericMetricMessage message) {
        SchemaMetricMessage schemaMetricMessage = new SchemaMetricMessage();

        DataSourceSchema schema = new DataSourceSchema(message.getSchema().getName(),
                                                       message.getSchema().getName(),
                                                       new TimestampSpec("timestamp", "auto", null),
                                                       message.getSchema().getDimensionsSpecList()
                                                              .stream()
                                                              .map(dimSpec -> new StringDimensionSpec(dimSpec.getName(),
                                                                                                      dimSpec.getName(),
                                                                                                      true,
                                                                                                      true,
                                                                                                      null))
                                                              .collect(Collectors.toList()),
                                                       message.getSchema().getMetricsSpecList()
                                                              .stream()
                                                              .map(metricSpec -> {
                                                                  if (metricSpec.getType().equals("longMax")) {
                                                                      return new LongMaxMetricSpec(metricSpec.getName(),
                                                                                                   metricSpec.getName(),
                                                                                                   "",
                                                                                                   true);
                                                                  }
                                                                  if (metricSpec.getType().equals("longMin")) {
                                                                      return new LongMinMetricSpec(metricSpec.getName(),
                                                                                                   metricSpec.getName(),
                                                                                                   "",
                                                                                                   true);
                                                                  }
                                                                  if (metricSpec.getType().equals("longSum")) {
                                                                      return new LongSumMetricSpec(metricSpec.getName(),
                                                                                                   metricSpec.getName(),
                                                                                                   "",
                                                                                                   true);
                                                                  }

                                                                  return null;
                                                              }).collect(Collectors.toList()),
                                                       null,
                                                       null,
                                                       null);

        Iterator<BrpcGenericMetricSet> iterator = message.getMetricSetList().iterator();
        schemaMetricMessage.setSchema(schema);
        schemaMetricMessage.setMetrics(new CloseableIterator<MetricMessage>() {
            @Override
            public void close() {
            }

            @Override
            public boolean hasNext() {
                return iterator.hasNext();
            }

            @Override
            public MetricMessage next() {
                MetricMessage metricMessage = new MetricMessage();
                BrpcGenericMetricSet metricSet = iterator.next();

                int i = 0;
                for (String dimension : metricSet.getDimensionList()) {
                    IDimensionSpec dimensionSpec = schema.getDimensionsSpec().get(i++);
                    metricMessage.put(dimensionSpec.getName(), dimension);
                }

                i = 0;
                for (long metric : metricSet.getMetricList()) {
                    IMetricSpec metricSpec = schema.getMetricsSpec().get(i++);
                    metricMessage.put(metricSpec.getName(), metric);
                }

                metricMessage.put("interval", message.getInterval());
                metricMessage.put("timestamp", message.getTimestamp());
                ReflectionUtils.getFields(header, metricMessage);
                return metricMessage;
            }
        });
        schemaMetricSink.process(message.getSchema().getName(), schemaMetricMessage);
    }

    private static class GenericMetricMessageIterator implements CloseableIterator<MetricMessage> {
        private final Iterator<?> iterator;
        private final BrpcMessageHeader header;

        public GenericMetricMessageIterator(BrpcMessageHeader header, List<?> messages) {
            this.header = header;
            this.iterator = messages.iterator();
        }

        @Override
        public void close() {
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public MetricMessage next() {
            return MetricMessage.of(header, iterator.next());
        }
    }
}

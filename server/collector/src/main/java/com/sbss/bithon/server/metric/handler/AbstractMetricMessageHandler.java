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

package com.sbss.bithon.server.metric.handler;

import com.sbss.bithon.server.common.handler.AbstractThreadPoolMessageHandler;
import com.sbss.bithon.server.common.utils.collection.CloseableIterator;
import com.sbss.bithon.server.meta.MetadataType;
import com.sbss.bithon.server.meta.storage.IMetaStorage;
import com.sbss.bithon.server.metric.DataSourceSchema;
import com.sbss.bithon.server.metric.DataSourceSchemaManager;
import com.sbss.bithon.server.metric.aggregator.NumberAggregator;
import com.sbss.bithon.server.metric.aggregator.spec.IMetricSpec;
import com.sbss.bithon.server.metric.input.InputRow;
import com.sbss.bithon.server.metric.input.MetricSet;
import com.sbss.bithon.server.metric.storage.IMetricStorage;
import com.sbss.bithon.server.metric.storage.IMetricWriter;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/3/3 11:17 下午
 */
@Slf4j
@Getter
public abstract class AbstractMetricMessageHandler
    extends AbstractThreadPoolMessageHandler<CloseableIterator<MetricMessage>> {

    private final DataSourceSchema schema;
    private final DataSourceSchema endpointSchema;
    private final IMetaStorage metaStorage;
    private final IMetricWriter metricStorageWriter;
    private final IMetricWriter endpointMetricStorageWriter;

    public AbstractMetricMessageHandler(String dataSourceName,
                                        IMetaStorage metaStorage,
                                        IMetricStorage metricStorage,
                                        DataSourceSchemaManager dataSourceSchemaManager,
                                        int corePoolSize,
                                        int maxPoolSize,
                                        Duration keepAliveTime,
                                        int queueSize) throws IOException {
        super(dataSourceName, corePoolSize, maxPoolSize, keepAliveTime, queueSize);

        this.schema = dataSourceSchemaManager.getDataSourceSchema(dataSourceName);
        this.metaStorage = metaStorage;
        this.metricStorageWriter = metricStorage.createMetricWriter(schema);

        this.endpointSchema = dataSourceSchemaManager.getDataSourceSchema("topo-metrics");
        this.endpointMetricStorageWriter = metricStorage.createMetricWriter(endpointSchema);
    }

    @Override
    public String getType() {
        return this.schema.getName();
    }

    protected boolean beforeProcess(MetricMessage message) throws Exception {
        return true;
    }

    @Override
    protected final void onMessage(CloseableIterator<MetricMessage> metricMessages) {
        if (!metricMessages.hasNext()) {
            return;
        }

        LocalDataSource endpointDataSource = new LocalDataSource(this.endpointSchema, 30);

        //
        // convert
        //
        List<InputRow> inputRowList = new ArrayList<>(8);
        while (metricMessages.hasNext()) {
            MetricMessage metricMessage = metricMessages.next();

            try {
                if (!beforeProcess(metricMessage)) {
                    continue;
                }

                // extract endpoint
                endpointDataSource.aggregate(extractEndpointLink(metricMessage));

                processMeta(metricMessage);

                inputRowList.add(new InputRow(metricMessage));
            } catch (Exception e) {
                log.error("Failed to process metric object. dataSource=[{}], message=[{}] due to {}",
                          this.schema.getName(),
                          metricMessage,
                          e);
            }
        }

        //
        // save endpoint metrics in batch
        //
        try {
            this.endpointMetricStorageWriter.write(endpointDataSource.toMetricSetList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //
        // save metrics in batch
        //
        try {
            this.metricStorageWriter.write(inputRowList);
        } catch (IOException e) {
            log.error("Failed to save metrics [dataSource={}] due to: {}",
                      this.schema.getName(),
                      e);
        }
    }

    protected MetricSet extractEndpointLink(MetricMessage message) {
        return null;
    }

    static class TimeSlot extends HashMap<Map<String, String>, Map<String, NumberAggregator>> {
        @Getter
        private final long timestamp;

        TimeSlot(long timestamp) {
            this.timestamp = timestamp;
        }
    }

    static class LocalDataSource {
        private final DataSourceSchema schema;
        private final TimeSlot[] timeSlot;

        public LocalDataSource(DataSourceSchema schema, int minutes) {
            this.schema = schema;
            this.timeSlot = new TimeSlot[minutes];
        }

        public void aggregate(MetricSet metricSet) {
            if (metricSet == null) {
                return;
            }

            TimeSlot slotStorage = getSlot(metricSet.getTimestamp());

            // get or create metrics
            Map<String, NumberAggregator> metrics = slotStorage.computeIfAbsent(metricSet.getDimensions(), dim -> {
                Map<String, NumberAggregator> metricMap = new HashMap<>();
                schema.getMetricsSpec()
                      .forEach((metricSpec) -> {
                          NumberAggregator aggregator = metricSpec.createAggregator();
                          if (aggregator != null) {
                              metricMap.put(metricSpec.getName(), aggregator);
                          }
                      });
                return metricMap;
            });

            Map<String, ? extends Number> inputMetrics = metricSet.getMetrics();
            inputMetrics.forEach((metricName, metricValue) -> {
                NumberAggregator aggregator = metrics.computeIfAbsent(metricName,
                                                                      m -> {
                                                                          IMetricSpec spec = schema.getMetricSpecByName(
                                                                              m);
                                                                          if (spec != null) {
                                                                              return spec.createAggregator();
                                                                          }
                                                                          return null;
                                                                      });
                if (aggregator != null) {
                    aggregator.aggregate(metricSet.getTimestamp(), metricValue);
                }
            });
        }

        private TimeSlot getSlot(long timestamp) {
            int slotIndex = (int) ((timestamp / 1000 / 60) % timeSlot.length);

            if (timeSlot[slotIndex] == null) {
                timeSlot[slotIndex] = new TimeSlot(timestamp);
            }
            return timeSlot[slotIndex];
        }

        public List<MetricSet> toMetricSetList() {
            List<MetricSet> metricSetList = new ArrayList<>(8);
            for (TimeSlot slot : timeSlot) {
                if (slot != null) {
                    slot.forEach((dimensions, metrics) -> metricSetList.add(new MetricSet(slot.getTimestamp(),
                                                                                          dimensions,
                                                                                          metrics)));
                }
            }
            return metricSetList;
        }
    }

    private void processMeta(MetricMessage metric) {
        String appName = metric.getApplicationName();
        String instanceName = metric.getInstanceName();
        try {
            long appId = metaStorage.getOrCreateMetadataId(appName, MetadataType.APPLICATION, 0L);
            metaStorage.getOrCreateMetadataId(instanceName, MetadataType.APP_INSTANCE, appId);
        } catch (Exception e) {
            log.error("Failed to save app info[appName={}, instance={}] due to: {}",
                      appName,
                      instanceName,
                      e);
        }
    }
}

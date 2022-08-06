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

package org.bithon.server.sink.metrics;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.bithon.component.commons.concurrency.NamedThreadFactory;
import org.bithon.component.commons.utils.CollectionUtils;
import org.bithon.server.storage.datasource.DataSourceSchema;
import org.bithon.server.storage.datasource.DataSourceSchemaManager;
import org.bithon.server.storage.datasource.input.IInputRow;
import org.bithon.server.storage.datasource.input.Measurement;
import org.bithon.server.storage.meta.IMetaStorage;
import org.bithon.server.storage.metrics.IMetricStorage;
import org.bithon.server.storage.metrics.IMetricWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/3/3 11:17 下午
 */
@Slf4j
@Getter
public abstract class AbstractMetricMessageHandler {

    private final ThreadPoolExecutor executor;

    private final DataSourceSchema schema;
    private final DataSourceSchema endpointSchema;
    private final IMetaStorage metaStorage;
    private final IMetricWriter metricStorageWriter;
    private final IMetricWriter endpointMetricStorageWriter;

    public AbstractMetricMessageHandler(String dataSourceName,
                                        IMetaStorage metaStorage,
                                        IMetricStorage metricStorage,
                                        DataSourceSchemaManager dataSourceSchemaManager) throws IOException {

        this.schema = dataSourceSchemaManager.getDataSourceSchema(dataSourceName);
        this.metaStorage = metaStorage;
        this.metricStorageWriter = metricStorage.createMetricWriter(schema);

        this.endpointSchema = dataSourceSchemaManager.getDataSourceSchema("topo-metrics");
        this.endpointSchema.setEnforceDuplicationCheck(false);
        this.endpointMetricStorageWriter = metricStorage.createMetricWriter(endpointSchema);

        this.executor = new ThreadPoolExecutor(1,
                                               4,
                                               1,
                                               TimeUnit.MINUTES,
                                               new LinkedBlockingQueue<>(1024),
                                               NamedThreadFactory.of(dataSourceName + "-handler"),
                                               new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    public String getType() {
        return this.schema.getName();
    }

    protected boolean beforeProcess(IInputRow message) throws Exception {
        return true;
    }

    public final void process(List<IInputRow> metricMessages) {
        if (CollectionUtils.isEmpty(metricMessages)) {
            return;
        }
        executor.execute(new MetricSinkRunnable(metricMessages));
    }

    class MetricSinkRunnable implements Runnable {
        private final List<IInputRow> metricMessages;

        MetricSinkRunnable(List<IInputRow> metricMessages) {
            this.metricMessages = metricMessages;
        }

        @Override
        public void run() {
            MetricsAggregator endpointDataSource = new MetricsAggregator(endpointSchema, 60);

            //
            // convert
            //
            List<IInputRow> inputRowList = new ArrayList<>(8);
            for (IInputRow metricMessage : metricMessages) {
                try {
                    if (!beforeProcess(metricMessage)) {
                        continue;
                    }

                    // extract endpoint
                    endpointDataSource.aggregate(extractEndpointLink(metricMessage));

                    processMeta(metricMessage);

                    inputRowList.add(metricMessage);
                } catch (Exception e) {
                    log.error("Failed to process metric object. dataSource=[{}], message=[{}] due to {}",
                              schema.getName(),
                              metricMessage,
                              e);
                }
            }

            //
            // save endpoint metrics in batch
            //
            try {
                endpointMetricStorageWriter.write(endpointDataSource.getRows());
            } catch (IOException e) {
                log.error("save metrics", e);
            }

            //
            // save metrics in batch
            //
            try {
                metricStorageWriter.write(inputRowList);
            } catch (IOException e) {
                log.error("Failed to save metrics [dataSource={}] due to: {}",
                          schema.getName(),
                          e);
            }
        }
    }

    protected Measurement extractEndpointLink(IInputRow message) {
        return null;
    }

    private void processMeta(IInputRow metric) {
        Object appType = metric.getCol("appType");
        if (appType == null) {
            log.warn("Saving meta for [{}] ignored due to lack of appType", this.schema.getName());
            return;
        }

        String appName = metric.getColAsString("appName");
        String instanceName = metric.getColAsString("instanceName");
        try {
            metaStorage.saveApplicationInstance(appName,
                                                appType.toString(),
                                                instanceName);
        } catch (Exception e) {
            log.error("Failed to save app info[appName={}, instance={}] due to: {}",
                      appName,
                      instanceName,
                      e);
        }
    }

    public void close() {
        if (executor.isShutdown() || executor.isTerminated() || executor.isTerminating()) {
            return;
        }

        log.info("Shutting down executor [{}]", schema.getName() + "-handler");
        executor.shutdown();
        try {
            executor.awaitTermination(30, TimeUnit.SECONDS);
        } catch (InterruptedException ignored) {
        }
    }
}

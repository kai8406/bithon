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

package org.bithon.server.sink.event;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.bithon.server.sink.metrics.AbstractMetricMessageHandler;
import org.bithon.server.storage.datasource.DataSourceSchemaManager;
import org.bithon.server.storage.datasource.input.IInputRow;
import org.bithon.server.storage.datasource.input.InputRow;
import org.bithon.server.storage.event.EventMessage;
import org.bithon.server.storage.meta.IMetaStorage;
import org.bithon.server.storage.metrics.IMetricStorage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frank.chen
 * @date 2022/8/2 22:30
 */
@Slf4j
public class MetricOverEventHandler extends AbstractMetricMessageHandler implements EventMessageHandler {

    private final String eventType;
    private final ObjectMapper objectMapper;
    private List<IInputRow> metrics;

    public MetricOverEventHandler(String eventType,
                                  String dataSourceName,
                                  ObjectMapper objectMapper,
                                  IMetaStorage metaStorage,
                                  IMetricStorage metricStorage,
                                  DataSourceSchemaManager dataSourceSchemaManager) throws IOException {
        super(dataSourceName,
              metaStorage,
              metricStorage,
              dataSourceSchemaManager);
        this.eventType = eventType;
        this.objectMapper = objectMapper;
    }

    @Override
    public String getEventType() {
        return eventType;
    }

    @Override
    public void startProcessing() {
        this.metrics = new ArrayList<>();
    }

    @Override
    public void transform(EventMessage eventMessage) {
        try {
            IInputRow row = new InputRow(objectMapper, objectMapper.readTree(eventMessage.getJsonArgs()));

            row.updateColumn("appName", eventMessage.getAppName());
            row.updateColumn("instanceName", eventMessage.getInstanceName());

            if (row.getCol("timestamp") == null) {
                row.updateColumn("timestamp", eventMessage.getTimestamp());
            }

            row.updateColumn("eventCount", 1);

            metrics.add(row);
        } catch (IOException e) {
            log.error("Exception when parsing event[{}]: {}", eventMessage, e.getMessage());
        }
    }

    @Override
    public void finalizeProcessing() {
        if (metrics.isEmpty()) {
            return;
        }

        try {
            // following process is executed asynchronously
            // we can't clear the metrics object
            this.process(metrics);
        } finally {
            // deference this object
            metrics = null;
        }
    }
}

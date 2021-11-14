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

package org.bithon.server.event.handler;

import lombok.extern.slf4j.Slf4j;
import org.bithon.server.common.handler.AbstractThreadPoolMessageHandler;
import org.bithon.server.event.storage.IEventStorage;
import org.bithon.server.event.storage.IEventWriter;
import org.bithon.server.metric.DataSourceSchema;
import org.bithon.server.metric.DataSourceSchemaManager;
import org.bithon.server.metric.input.InputRow;
import org.bithon.server.metric.storage.IMetricStorage;
import org.bithon.server.metric.storage.IMetricWriter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/14 4:01 下午
 */
@Slf4j
@Component
public class EventsMessageHandler extends AbstractThreadPoolMessageHandler<EventMessage> {

    final IEventWriter eventWriter;
    final IMetricWriter exceptionMetricWriter;

    public EventsMessageHandler(IEventStorage eventStorage,
                                IMetricStorage metricStorage,
                                DataSourceSchemaManager schemaManager) throws IOException {
        super("event", 1, 5, Duration.ofMinutes(3), 1024);
        this.eventWriter = eventStorage.createWriter();

        DataSourceSchema schema = schemaManager.getDataSourceSchema("exception-metrics");
        schema.setEnforceDuplicationCheck(false);
        this.exceptionMetricWriter = metricStorage.createMetricWriter(schema);
    }

    @Override
    protected void onMessage(EventMessage body) throws IOException {
        if (body.getType().equals("exception")) {
            // generate a metric

            InputRow row = new InputRow(new HashMap<>(body.getArgs()));
            row.updateColumn("appName", body.getAppName());
            row.updateColumn("instanceName", body.getInstanceName());
            row.updateColumn("timestamp", body.getTimestamp());
            row.updateColumn("exceptionCount", 1);
            exceptionMetricWriter.write(Collections.singletonList(row));
        }
        eventWriter.write(body);
    }

    @Override
    public String getType() {
        return "event";
    }
}
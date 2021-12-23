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

package org.bithon.server.event.sink;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.bithon.server.common.handler.AbstractThreadPoolMessageHandler;
import org.bithon.server.common.utils.collection.IteratorableCollection;
import org.bithon.server.event.storage.IEventStorage;
import org.bithon.server.event.storage.IEventWriter;
import org.bithon.server.metric.DataSourceSchema;
import org.bithon.server.metric.DataSourceSchemaManager;
import org.bithon.server.metric.input.InputRow;
import org.bithon.server.metric.storage.IMetricStorage;
import org.bithon.server.metric.storage.IMetricWriter;
import org.springframework.context.ApplicationContext;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/2/14 4:01 下午
 */
@Slf4j
public class EventsMessageHandler extends AbstractThreadPoolMessageHandler<IteratorableCollection<EventMessage>> {

    final IEventWriter eventWriter;
    final IMetricWriter exceptionMetricWriter;
    final ObjectMapper objectMapper;

    public EventsMessageHandler(ApplicationContext applicationContext) throws IOException {
        super("event", 1, 5, Duration.ofMinutes(3), 1024);
        this.eventWriter = applicationContext.getBean(IEventStorage.class).createWriter();
        this.objectMapper = applicationContext.getBean(ObjectMapper.class);

        DataSourceSchema schema = applicationContext.getBean(DataSourceSchemaManager.class).getDataSourceSchema("exception-metrics");
        schema.setEnforceDuplicationCheck(false);
        this.exceptionMetricWriter = applicationContext.getBean(IMetricStorage.class).createMetricWriter(schema);
    }

    @Data
    static class ExceptionEventArgs {
        private String exceptionClass;
        private String message;
        private String stack;
    }

    @Override
    protected void onMessage(IteratorableCollection<EventMessage> iterator) throws IOException {
        List<InputRow> exceptionEvents = new ArrayList<>();

        List<EventMessage> genericEvents = new ArrayList<>();
        while (iterator.hasNext()) {
            EventMessage message = iterator.next();
            if ("exception".equals(message.getType())) {
                InputRow row = new InputRow(new HashMap<>());

                ExceptionEventArgs args = objectMapper.readValue(message.getJsonArgs(), ExceptionEventArgs.class);
                row.updateColumn("appName", message.getAppName());
                row.updateColumn("instanceName", message.getInstanceName());
                row.updateColumn("timestamp", message.getTimestamp());
                row.updateColumn("exceptionClass", args.getExceptionClass());
                row.updateColumn("message", args.getMessage());
                row.updateColumn("stack", args.getStack());
                row.updateColumn("exceptionCount", 1);
                exceptionEvents.add(row);
            } else {
                genericEvents.add(message);
            }
        }
        if (!exceptionEvents.isEmpty()) {
            exceptionMetricWriter.write(exceptionEvents);
        }
        if (!genericEvents.isEmpty()) {
            eventWriter.write(genericEvents);
        }
    }

    @Override
    public String getType() {
        return "event";
    }
}

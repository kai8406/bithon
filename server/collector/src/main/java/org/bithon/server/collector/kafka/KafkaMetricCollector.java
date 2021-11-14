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

package org.bithon.server.collector.kafka;

import org.bithon.server.collector.sink.kafka.KafkaMetricSink;
import org.bithon.server.common.utils.collection.CloseableIterator;
import org.bithon.server.metric.handler.AbstractMetricMessageHandler;
import org.bithon.server.metric.handler.MetricMessage;

/**
 * Kafka collector that is connecting to {@link KafkaMetricSink}
 *
 * @author frank.chen021@outlook.com
 * @date 2021/3/18
 */
public class KafkaMetricCollector extends AbstractKafkaCollector<MetricMessage> {

    private final AbstractMetricMessageHandler messageHandler;

    public KafkaMetricCollector(AbstractMetricMessageHandler messageHandler) {
        super(MetricMessage.class);
        this.messageHandler = messageHandler;
    }

    @Override
    protected String getGroupId() {
        return "bithon-metric-consumer-" + this.messageHandler.getType();
    }

    @Override
    protected String getTopic() {
        return this.messageHandler.getType();
    }

    @Override
    protected void onMessage(CloseableIterator<MetricMessage> msg) {
        messageHandler.process(msg);
    }
}
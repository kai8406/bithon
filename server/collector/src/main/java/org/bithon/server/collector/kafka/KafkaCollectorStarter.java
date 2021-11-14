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

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.bithon.server.event.handler.EventsMessageHandler;
import org.bithon.server.metric.handler.AbstractMetricMessageHandler;
import org.bithon.server.tracing.handler.TraceMessageHandler;
import org.springframework.beans.BeansException;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/3/18
 */
@Component
@ConditionalOnProperty(value = "collector-kafka.enabled", havingValue = "true", matchIfMissing = false)
public class KafkaCollectorStarter implements SmartLifecycle, ApplicationContextAware {
    ApplicationContext context;

    private final List<IKafkaCollector> collectors = new ArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Override
    public void start() {
        KafkaCollectorConfig config = this.context.getBean(KafkaCollectorConfig.class);
        Map<String, Object> consumerProps = config.getConsumer();
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());

        collectors.add(new KafkaTraceCollector(context.getBean(TraceMessageHandler.class)).start(consumerProps));
        collectors.add(new KafkaEventCollector(context.getBean(EventsMessageHandler.class)).start(consumerProps));

        context.getBeansOfType(AbstractMetricMessageHandler.class).values().forEach(metricHandler -> {
            collectors.add(new KafkaMetricCollector(metricHandler).start(consumerProps));
        });
    }

    @Override
    public void stop() {
        for (IKafkaCollector collector : collectors) {
            collector.stop();
        }
    }

    @Override
    public boolean isRunning() {
        return collectors.stream().anyMatch(IKafkaCollector::isRunning);
    }
}
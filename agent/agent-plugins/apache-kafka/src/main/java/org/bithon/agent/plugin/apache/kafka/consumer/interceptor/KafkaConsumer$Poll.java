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

package org.bithon.agent.plugin.apache.kafka.consumer.interceptor;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.bithon.agent.instrumentation.aop.context.AopContext;
import org.bithon.agent.instrumentation.aop.interceptor.InterceptionDecision;
import org.bithon.agent.instrumentation.aop.interceptor.declaration.AroundInterceptor;
import org.bithon.agent.observability.tracing.context.ITraceSpan;
import org.bithon.agent.observability.tracing.context.TraceSpanFactory;
import org.bithon.agent.plugin.apache.kafka.KafkaPluginContext;
import org.bithon.component.commons.tracing.Tags;

import java.time.Duration;

/**
 * {@link org.apache.kafka.clients.consumer.KafkaConsumer#poll(Duration)}
 *
 * @author Frank Chen
 * @date 28/11/22 8:39 pm
 */
public class KafkaConsumer$Poll extends AroundInterceptor {

    @Override
    public InterceptionDecision before(AopContext aopContext) {
        ITraceSpan span = TraceSpanFactory.newSpan("kafka");
        if (span == null) {
            return InterceptionDecision.SKIP_LEAVE;
        }

        aopContext.setUserContext(span.method(aopContext.getTargetClass(), aopContext.getMethod())
                                      .start());
        return InterceptionDecision.CONTINUE;
    }

    @Override
    public void after(AopContext aopContext) {
        KafkaPluginContext kafkaPluginContext = aopContext.getInjectedOnTargetAs();

        KafkaConsumer<?, ?> consumer = aopContext.getTargetAs();
        String topics = null;
        try {
            topics = String.join(",", consumer.subscription());
        } catch (Exception ignored) {
        }

        ConsumerRecords<?, ?> records = aopContext.getReturningAs();
        ITraceSpan span = aopContext.getUserContextAs();
        span.tag(aopContext.getException())
            .tag(Tags.Messaging.KAFKA_CONSUMER_GROUP, kafkaPluginContext.groupId)
            .tag(Tags.Messaging.KAFKA_CLIENT_ID, kafkaPluginContext.clientId)
            .tag(Tags.Messaging.KAFKA_TOPIC, topics)
            .tag(Tags.Messaging.COUNT, records == null ? 0 : records.count())
            .finish();
    }
}

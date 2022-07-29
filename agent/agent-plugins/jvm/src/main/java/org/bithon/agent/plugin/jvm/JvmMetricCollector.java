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

package org.bithon.agent.plugin.jvm;

import org.bithon.agent.core.dispatcher.IMessageConverter;
import org.bithon.agent.core.metric.collector.IMetricCollector;
import org.bithon.agent.core.metric.collector.MetricCollectorManager;
import org.bithon.agent.core.metric.domain.jvm.GcMetrics;
import org.bithon.agent.core.metric.domain.jvm.JvmMetrics;
import org.bithon.agent.plugin.jvm.gc.GcMetricCollector;
import org.bithon.agent.plugin.jvm.mem.ClassMetricCollector;
import org.bithon.agent.plugin.jvm.mem.MemoryMetricCollector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A facade collector which collects jvm related metrics from other collectors
 *
 * @author frankchen
 */
public class JvmMetricCollector {

    private CpuMetricCollector cpuMetricCollector;


    public void start() {
        MemoryMetricCollector.initDirectMemoryCollector();

        cpuMetricCollector = new CpuMetricCollector();

        //
        // register collector
        //
        MetricCollectorManager.getInstance().register("jvm-metrics", new IMetricCollector() {
            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public List<Object> collect(IMessageConverter messageConverter,
                                        int interval,
                                        long timestamp) {
                return Collections.singletonList(messageConverter.from(timestamp,
                                                                       interval,
                                                                       buildJvmMetrics()));
            }
        });

        MetricCollectorManager.getInstance().register("jvm-gc-metrics", new IMetricCollector() {
            private final GcMetricCollector gcCollector = new GcMetricCollector();

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public List<Object> collect(IMessageConverter messageConverter, int interval, long timestamp) {
                List<Object> metricMessages = new ArrayList<>(2);
                for (GcMetrics gcMetricSet : gcCollector.collect()) {
                    metricMessages.add(messageConverter.from(timestamp,
                                                             interval,
                                                             gcMetricSet));
                }
                return metricMessages;
            }
        });
    }

    private JvmMetrics buildJvmMetrics() {
        JvmMetrics jvmMetrics = new JvmMetrics(JmxBeans.RUNTIME_BEAN.getUptime(),
                                               JmxBeans.RUNTIME_BEAN.getStartTime());
        jvmMetrics.cpu = cpuMetricCollector.collect();
        jvmMetrics.memory = MemoryMetricCollector.collectTotal();
        jvmMetrics.heap = MemoryMetricCollector.collectHeap();
        jvmMetrics.nonHeap = MemoryMetricCollector.collectNonHeap();
        jvmMetrics.metaspace = MemoryMetricCollector.collectMetaSpace();
        jvmMetrics.directMemory = MemoryMetricCollector.collectDirectMemory();
        jvmMetrics.thread = ThreadMetricCollector.collect();
        jvmMetrics.clazz = ClassMetricCollector.collect();
        return jvmMetrics;
    }
}

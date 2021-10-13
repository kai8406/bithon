package org.bithon.agent.core.metric.domain.jvm;

/**
 * @author Frank Chen
 * @date 18/10/21 9:29 am
 */
public interface IDirectMemoryMetricProvider {

    /**
     * get the used direct memory
     */
    long getUsed();
}

package com.sbss.bithon.agent.core.tracing.sampling;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * A global sampling decision maker based on requests
 *
 * @author frank.chen021@outlook.com
 * @date 2021/2/9 10:55 下午
 */
public class RatioSamplingDecisionMaker implements ISamplingDecisionMaker {
    /**
     * [1 - 100]
     */
    private final int samplingRatio;
    private final AtomicInteger counter = new AtomicInteger();

    /**
     * @param samplingRatio [0, 100] 0 means NO-SAMPLING
     */
    public RatioSamplingDecisionMaker(int samplingRatio) {
        this.samplingRatio = 100 - samplingRatio;
    }

    @Override
    public SamplingMode decideSamplingMode(Object request) {
        if (this.samplingRatio >= 100) {
            return SamplingMode.NONE;
        }
        if (this.samplingRatio <= 0) {
            return SamplingMode.FULL;
        }

        int samplingCount = Math.abs(counter.getAndIncrement());
        return (samplingCount % samplingRatio == 0) ? SamplingMode.FULL : SamplingMode.NONE;
    }
}

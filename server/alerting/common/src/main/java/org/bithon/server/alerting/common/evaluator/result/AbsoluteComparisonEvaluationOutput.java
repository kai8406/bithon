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

package org.bithon.server.alerting.common.evaluator.result;

import lombok.Getter;
import org.bithon.server.alerting.common.evaluator.metric.IMetricEvaluator;

/**
 * @author frankchen
 * @date 2020-08-27 18:35:19
 */
public class AbsoluteComparisonEvaluationOutput implements IEvaluationOutput {

    @Getter
    private final boolean isMatches;

    /**
     * The value at the moment when the metric is retrieved
     */
    @Getter
    private final String now;

    @Getter
    private final String threshold;

    @Getter
    private final String delta;

    @Getter
    private final IMetricEvaluator metric;

    public AbsoluteComparisonEvaluationOutput(IMetricEvaluator metric,
                                              String now,
                                              String threshold,
                                              String delta,
                                              boolean isMatches) {
        this.isMatches = isMatches;
        this.now = now;
        this.threshold = threshold;
        this.delta = delta;
        this.metric = metric;
    }

    @Override
    public String getThresholdText() {
        return threshold;
    }

    @Override
    public String getCurrentText() {
        return now;
    }

    @Override
    public String getDeltaText() {
        return delta;
    }

}

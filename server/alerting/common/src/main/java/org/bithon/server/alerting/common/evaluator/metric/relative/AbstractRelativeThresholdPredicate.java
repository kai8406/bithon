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

package org.bithon.server.alerting.common.evaluator.metric.relative;

import lombok.Getter;
import org.bithon.component.commons.utils.CollectionUtils;
import org.bithon.component.commons.utils.HumanReadablePercentage;
import org.bithon.component.commons.utils.NumberUtils;
import org.bithon.component.commons.utils.Preconditions;
import org.bithon.server.alerting.common.evaluator.EvaluationContext;
import org.bithon.server.alerting.common.evaluator.metric.IMetricEvaluator;
import org.bithon.server.alerting.common.evaluator.result.IEvaluationOutput;
import org.bithon.server.alerting.common.evaluator.result.RelativeComparisonEvaluationOutput;
import org.bithon.server.alerting.common.model.AlertExpression;
import org.bithon.server.commons.time.TimeSpan;
import org.bithon.server.web.service.datasource.api.GeneralQueryRequest;
import org.bithon.server.web.service.datasource.api.GeneralQueryResponse;
import org.bithon.server.web.service.datasource.api.IDataSourceApi;
import org.bithon.server.web.service.datasource.api.IntervalRequest;
import org.bithon.server.web.service.datasource.api.QueryField;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author frankchen
 * @date 2020-08-21 17:06:34
 */
public class AbstractRelativeThresholdPredicate implements IMetricEvaluator {

    private static final BigDecimal ZERO = BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP);

    @Getter
    private final AlertExpression.WindowExpression offset;

    private final Number threshold;

    @Getter
    private final boolean isUp;

    public AbstractRelativeThresholdPredicate(Number threshold,
                                              AlertExpression.WindowExpression offset,
                                              boolean isUp) {
        this.offset = Preconditions.checkArgumentNotNull("offset", offset);
        this.threshold = Preconditions.checkArgumentNotNull("threshold", threshold);
        this.isUp = isUp;
    }

    @Override
    public IEvaluationOutput evaluate(IDataSourceApi dataSourceApi,
                                      String dataSource,
                                      QueryField metric, TimeSpan start,
                                      TimeSpan end,
                                      String filterExpression,
                                      List<String> groupBy, EvaluationContext context) throws IOException {

        GeneralQueryResponse response = dataSourceApi.groupBy(GeneralQueryRequest.builder()
                                                                                 .dataSource(dataSource)
                                                                                 .interval(IntervalRequest.builder()
                                                                                                          .startISO8601(start.toISO8601())
                                                                                                          .endISO8601(end.toISO8601())
                                                                                                          .build())
                                                                                 .filterExpression(filterExpression)
                                                                                 .fields(Collections.singletonList(metric))
                                                                                 .build());

        //noinspection unchecked
        List<Map<String, Object>> currWindow = (List<Map<String, Object>>) response.getData();
        if (CollectionUtils.isEmpty(currWindow) || !currWindow.get(0).containsKey(metric.getName())) {
            return null;
        }
        Number currValue = (Number) currWindow.get(0).get(metric.getName());
        if (currValue == null) {
            return null;
        }
        BigDecimal currWindowValue = NumberUtils.scaleTo(currValue.doubleValue(), 2);

        response = dataSourceApi.groupBy(GeneralQueryRequest.builder()
                                                            .dataSource(dataSource)
                                                            .interval(IntervalRequest.builder()
                                                                                     // The offset is a negative value,
                                                                                     // turn it into a positive one
                                                                                     .startISO8601(start.before(-this.offset.getDuration(), this.offset.getUnit().toTimeUnit()).toISO8601())
                                                                                     .endISO8601(end.before(-this.offset.getDuration(), this.offset.getUnit().toTimeUnit()).toISO8601())
                                                                                     .build())
                                                            .filterExpression(filterExpression)
                                                            .fields(Collections.singletonList(metric))
                                                            .groupBy(groupBy)
                                                            .build());

        //noinspection unchecked
        List<Map<String, Object>> base = (List<Map<String, Object>>) response.getData();
        if (CollectionUtils.isEmpty(base) || !base.get(0).containsKey(metric.getName())) {
            return null;
        }
        Number val = (Number) base.get(0).get(metric.getName());
        if (val == null) {
            // No data in given time window, treat it as zero
            val = 0;
        }
        BigDecimal baseValue = NumberUtils.scaleTo(val.doubleValue(), 2);

        double delta;
        if (isUp) {
            if (threshold instanceof HumanReadablePercentage) {
                delta = ZERO.equals(baseValue)
                    ? currWindowValue.subtract(baseValue).doubleValue()
                    : currWindowValue.subtract(baseValue).divide(baseValue, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).doubleValue();
            } else {
                delta = currWindowValue.subtract(baseValue).doubleValue();
            }
        } else {
            if (threshold instanceof HumanReadablePercentage) {
                delta = ZERO.equals(baseValue)
                    ? currWindowValue.subtract(baseValue).doubleValue()
                    : baseValue.subtract(currWindowValue).divide(baseValue, 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100)).doubleValue();

            } else {
                delta = currWindowValue.subtract(baseValue).doubleValue();
            }
        }

        RelativeComparisonEvaluationOutput output = new RelativeComparisonEvaluationOutput();
        output.setDelta(delta);
        output.setNow(currWindowValue);
        output.setBase(baseValue);
        output.setThreshold(threshold);
        output.setMatches(delta > threshold.doubleValue());
        output.setMetric(this);
        return output;
    }

    @Override
    public String toString() {
        return (isUp ? ">" : "<") + " " + threshold.toString();
    }
}

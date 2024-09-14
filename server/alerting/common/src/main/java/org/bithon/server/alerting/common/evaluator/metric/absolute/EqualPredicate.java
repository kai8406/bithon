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

package org.bithon.server.alerting.common.evaluator.metric.absolute;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bithon.component.commons.expression.IDataType;

/**
 * @author frank.chen021@outlook.com
 * @date 2024/1/7 19:07
 */
public class EqualPredicate extends AbstractAbsoluteThresholdPredicate {

    @JsonCreator
    public EqualPredicate(@JsonProperty("expected") Object expected) {
        super("=", expected);
    }

    @Override
    protected boolean matches(IDataType valueType, Number threshold, Number now) {
        return valueType.isEqual(threshold, now);
    }
}

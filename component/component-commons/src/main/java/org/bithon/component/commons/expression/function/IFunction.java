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

package org.bithon.component.commons.expression.function;

import org.bithon.component.commons.expression.IDataType;
import org.bithon.component.commons.expression.IExpression;

import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2022/11/2 17:31
 */
public interface IFunction {

    String getName();

    List<Parameter> getParameters();

    void validateParameter(List<IExpression> parameters);

    Object evaluate(List<Object> parameters);

    IDataType getReturnType();

    /**
     * If the function is an aggregate function
     */
    default boolean isAggregator() {
        return false;
    }
}

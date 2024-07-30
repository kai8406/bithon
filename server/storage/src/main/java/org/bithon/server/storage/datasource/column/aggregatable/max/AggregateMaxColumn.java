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

package org.bithon.server.storage.datasource.column.aggregatable.max;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import org.bithon.component.commons.expression.FunctionExpression;
import org.bithon.component.commons.expression.function.builtin.AggregateFunction;
import org.bithon.server.storage.datasource.column.aggregatable.IAggregatableColumn;

/**
 * @author frank.chen021@outlook.com
 * @date 2022/9/4 20:37
 */
public abstract class AggregateMaxColumn implements IAggregatableColumn {

    @Getter
    protected final String name;

    @Getter
    private final String alias;

    protected final FunctionExpression aggregateFunctionExpression;

    public AggregateMaxColumn(String name,
                              String alias) {
        this.name = name;
        this.alias = alias == null ? name : alias;
        this.aggregateFunctionExpression = FunctionExpression.create(AggregateFunction.Max.NAME, name);
    }

    @JsonIgnore
    @Override
    public FunctionExpression getAggregateFunctionExpression() {
        return aggregateFunctionExpression;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}

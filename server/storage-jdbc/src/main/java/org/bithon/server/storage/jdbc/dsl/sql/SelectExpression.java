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

package org.bithon.server.storage.jdbc.dsl.sql;

import lombok.Data;
import lombok.Getter;

/**
 * @author frank.chen021@outlook.com
 * @date 2022/9/4 14:55
 */
@Data
public class SelectExpression implements IExpression {
    @Getter
    private final FieldsExpression fieldsExpression = new FieldsExpression();

    private final FromExpression from = new FromExpression();
    private WhereExpression where;
    private GroupByExpression groupBy;
    private OrderByExpression orderBy;

    @Override
    public void accept(IExpressionVisitor visitor) {
        visitor.before(this);
        {
            fieldsExpression.accept(visitor);
            from.accept(visitor);
            if (where != null) {
                where.accept(visitor);
            }
            if (groupBy != null) {
                groupBy.accept(visitor);
            }
            if (orderBy != null) {
                orderBy.accept(visitor);
            }
        }
        visitor.after(this);
    }
}

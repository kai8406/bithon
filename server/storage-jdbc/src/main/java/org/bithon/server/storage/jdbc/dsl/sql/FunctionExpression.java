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

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2022/9/4 16:40
 */
@Getter
public class FunctionExpression implements IExpression {
    private final String fnName;
    private final List<IExpression> arguments = new ArrayList<>();

    public FunctionExpression(String fnName) {
        this.fnName = fnName;
    }

    @Override
    public void accept(IExpressionVisitor visitor) {
        visitor.before(this);
        for (IExpression arg : arguments) {
            arg.accept(visitor);
        }
        visitor.after(this);
    }
}

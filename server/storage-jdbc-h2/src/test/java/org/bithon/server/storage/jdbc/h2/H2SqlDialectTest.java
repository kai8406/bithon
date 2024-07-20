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

package org.bithon.server.storage.jdbc.h2;

import org.bithon.component.commons.expression.FunctionExpression;
import org.bithon.component.commons.expression.IExpression;
import org.bithon.component.commons.expression.IdentifierExpression;
import org.bithon.component.commons.expression.LiteralExpression;
import org.bithon.component.commons.expression.function.Functions;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Frank Chen
 * @date 20/1/24 9:03 pm
 */
public class H2SqlDialectTest {

    @Test
    public void testTransformStartsWith() {
        IExpression expr = new H2SqlDialect().transform(new FunctionExpression(Functions.getInstance().getFunction("startsWith"),
                                                                               new IdentifierExpression("a"),
                                                                               LiteralExpression.create("1231")));
        Assert.assertEquals("a like '1231%'", expr.serializeToText(null));
    }

    @Test
    public void testTransformEndsWith() {
        IExpression expr = new H2SqlDialect().transform(new FunctionExpression(Functions.getInstance().getFunction("endsWith"),
                                                                               new IdentifierExpression("a"),
                                                                               LiteralExpression.create("1231")));
        Assert.assertEquals("a like '%1231'", expr.serializeToText(null));
    }

    @Test
    public void testHasToken() {
        IExpression expr = new H2SqlDialect().transform(new FunctionExpression(Functions.getInstance().getFunction("hasToken"),
                                                                               new IdentifierExpression("a"),
                                                                               LiteralExpression.create("1231")));
        Assert.assertEquals("a like '%1231%'", expr.serializeToText(null));
    }
}

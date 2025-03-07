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

package org.bithon.server.storage.common.expression;

import org.bithon.component.commons.expression.IExpression;
import org.bithon.component.commons.expression.expt.InvalidExpressionException;
import org.bithon.component.commons.expression.function.Functions;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Frank Chen
 * @date 30/8/23 9:40 am
 */
public class FunctionsTest {

    private final ExpressionASTBuilder builder = ExpressionASTBuilder.builder().functions(Functions.getInstance());

    @Test
    public void test_round() {
        Assert.assertEquals("5.00",
                            builder.build("round(5, 2)").serializeToText());

        Assert.assertEquals("100.000",
                            builder.build("round(100,3)").serializeToText());
    }

    @Test(expected = InvalidExpressionException.class)
    public void test_round_InvalidParameterCount() {
        builder.build("round(100,99,98)");
    }

    @Test
    public void test_round_ExpressionAsParameter() {
        String expression = "round(a*b/c+d,2)";
        Assert.assertEquals("round(((a * b) / c) + d, 2)", builder.build(expression).serializeToText(null));
    }

    @Test
    public void test_startsWith() {
        Assert.assertEquals(true,
                            builder.build("startsWith('bithon', 'b')").evaluate(null));

        Assert.assertEquals(true,
                            builder.build("startsWith('bithon', '')").evaluate(null));

        Assert.assertEquals(false,
                            builder.build("startsWith('bithon', 'x')").evaluate(null));

        // Check if the given is null
        Assert.assertEquals(false,
                            builder.build("startsWith(a, 'x')").evaluate(name -> null));

        Assert.assertEquals(false,
                            builder.build("startsWith('bithon', a)").evaluate(name -> null));

        // Check if the givens are all variables
        Assert.assertEquals(true,
                            builder.build("startsWith(a, b)").evaluate(name -> {
                                if ("a".equals(name)) {
                                    return "bithon";
                                }
                                if ("b".equals(name)) {
                                    return "b";
                                }
                                return null;
                            }));
    }

    @Test
    public void test_endsWith() {
        Assert.assertEquals(true,
                            builder.build("endsWith('bithon', 'on')").evaluate(null));

        Assert.assertEquals(true,
                            builder.build("endsWith('bithon', '')").evaluate(null));

        Assert.assertEquals(false,
                            builder.build("endsWith('bithon', 'x')").evaluate(null));

        // Check if the given is null
        Assert.assertEquals(false,
                            builder.build("endsWith(a, 'x')").evaluate(name -> null));

        Assert.assertEquals(false,
                            builder.build("endsWith('bithon', a)").evaluate(name -> null));

        // Check if the givens are all variables
        Assert.assertEquals(true,
                            builder.build("endsWith(a, b)").evaluate(name -> {
                                if ("a".equals(name)) {
                                    return "bithon";
                                } else if ("b".equals(name)) {
                                    return "on";
                                } else {
                                    return null;
                                }
                            }));
    }

    @Test
    public void test_hasToken() {
        Assert.assertEquals(true,
                            builder.build("hasToken('bithon', 'th')").evaluate(null));

        Assert.assertEquals(true,
                            builder.build("hasToken('bithon', '')").evaluate(null));

        Assert.assertEquals(false,
                            builder.build("hasToken('bithon', 'x')").evaluate(null));

        // Check if the given is null
        Assert.assertEquals(false,
                            builder.build("hasToken(a, 'x')").evaluate(name -> null));

        // The 2nd parameter must be a constant
        Assert.assertThrows(InvalidExpressionException.class,
                            () -> builder.build("hasToken('bithon', a)").evaluate(name -> null));

        // The 2nd parameter must be type of string
        Assert.assertThrows(InvalidExpressionException.class,
                            () -> builder.build("hasToken('bithon', 1)").evaluate(name -> null));

        // Check if the givens are all variables
        Assert.assertEquals(true,
                            builder.build("hasToken(a, 'b')").evaluate(name -> {
                                if ("a".equals(name)) {
                                    return "bithon";
                                }
                                return null;
                            }));
    }

    @Test
    public void test_lower() {
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("lower()"));

        Assert.assertEquals("bithon",
                            builder.build("lower('bithon')").evaluate(null));

        Assert.assertEquals("bithon",
                            builder.build("lower('Bithon')").evaluate(null));

        Assert.assertEquals("bithon",
                            builder.build("lower('BITHON')").evaluate(null));

        // Check if the given is null
        Assert.assertNull(builder.build("lower(a)").evaluate(name -> null));
    }

    @Test
    public void test_upper() {
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("upper()"));

        Assert.assertEquals("BITHON",
                            builder.build("upper('bithon')").evaluate(null));

        Assert.assertEquals("BITHON",
                            builder.build("upper('BITHON')").evaluate(null));

        Assert.assertEquals("BITHON",
                            builder.build("upper('BithON')").evaluate(null));

        // Check if the given is null
        Assert.assertNull(builder.build("upper(a)").evaluate(name -> null));
    }

    @Test
    public void test_substring() {
        // invalid parameter number
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("substring()"));
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("substring(a)"));
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("substring(a,b)"));

        //
        // invalid parameter type
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("substring(1,2,3)"));
        // 2nd or 3rd should be integer
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("substring('a','a',3)"));
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("substring('a','a','a')"));

        Assert.assertEquals("bi",
                            builder.build("substring('bithon', 0, 2)").evaluate(null));

        Assert.assertEquals("it",
                            builder.build("substring('bithon', 1, 2)").evaluate(null));

        // Check if the given is null
        Assert.assertNull(builder.build("substring(a, 1, 2)").evaluate(name -> null));
    }

    @Test
    public void test_trim() {
        // invalid parameter number
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trim()"));
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trim(a, b)"));

        //
        // invalid parameter type
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trim(1)"));

        Assert.assertEquals("a",
                            builder.build("trim(' a ')").evaluate(null));

        // Check if the given is null
        Assert.assertNull(builder.build("trim(a)").evaluate(name -> null));
    }

    @Test
    public void test_trimLeft() {
        // invalid parameter number
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trimLeft()"));
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trimLeft(a, b)"));

        //
        // invalid parameter type
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trimLeft(1)"));

        Assert.assertEquals("a ",
                            builder.build("trimLeft(' a ')").evaluate(null));

        // Check if the given is null
        Assert.assertNull(builder.build("trimLeft(a)").evaluate(name -> null));
    }

    @Test
    public void test_trimRight() {
        // invalid parameter number
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trimRight()"));
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trimRight(a, b)"));

        //
        // invalid parameter type
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("trimRight(1)"));

        Assert.assertEquals(" a",
                            builder.build("trimRight(' a ')").evaluate(null));

        // Check if the given is null
        Assert.assertNull(builder.build("trimRight(a)").evaluate(name -> null));
    }

    @Test
    public void test_length() {
        // invalid parameter number
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("length()"));
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("length(a, b)"));

        //
        // invalid parameter type
        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("length(1)"));

        Assert.assertEquals(" a",
                            builder.build("trimRight(' a ')").evaluate(null));

        // Check if the given is null
        Assert.assertNull(builder.build("trimRight(a)").evaluate(name -> null));
    }

    @Test
    public void test_count() {
        IExpression expr = ExpressionASTBuilder.builder().functions(Functions.getInstance()).build("count(1)");

        Assert.assertEquals("count(1)", expr.serializeToText(null));
    }

    @Test
    public void test_toMilliseconds() {
        Assert.assertEquals(1000L,
                            builder.build("toMilliseconds(1)").evaluate(null));

        Assert.assertEquals(2000L,
                            builder.build("toMilliseconds(2)").evaluate(null));
    }

    @Test
    public void test_toMicroseconds() {
        Assert.assertEquals(1000000L,
                            builder.build("toMicroseconds(1)").evaluate(null));

        Assert.assertEquals(2000000L,
                            builder.build("toMicroseconds(2s)").evaluate(null));
    }

    @Test
    public void test_toNanoseconds() {
        Assert.assertEquals(1000000000L,
                            builder.build("toNanoseconds(1)").evaluate(null));

        Assert.assertEquals(2000000000L,
                            builder.build("toNanoseconds(2)").evaluate(null));
    }

    @Test
    public void test_concat() {
        Assert.assertEquals("hello world",
                            builder.build("concat('hello',' world')").evaluate(null));

        Assert.assertEquals("hello ",
                            builder.build("concat('hello',' ')").evaluate(null));

        Assert.assertThrows(InvalidExpressionException.class, () -> builder.build("concat('hello',1"));
    }
}

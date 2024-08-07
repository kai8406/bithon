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

package org.bithon.component.commons.expression.function.builtin;

import org.bithon.component.commons.expression.IDataType;
import org.bithon.component.commons.expression.IExpression;
import org.bithon.component.commons.expression.LiteralExpression;
import org.bithon.component.commons.expression.expt.InvalidExpressionException;
import org.bithon.component.commons.expression.function.AbstractFunction;
import org.bithon.component.commons.expression.function.Parameter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author frank.chen021@outlook.com
 * @date 2024/7/18 21:29
 */
public class StringFunction {
    public static class Concat extends AbstractFunction {
        public Concat() {
            super("concat", Arrays.asList(new Parameter(IDataType.STRING), new Parameter(IDataType.STRING)), IDataType.STRING);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String v1 = parameters.get(0).toString();
            String v2 = parameters.get(0).toString();
            return v1 + v2;
        }
    }

    public static class HasToken extends AbstractFunction {

        public HasToken() {
            super("hasToken", Arrays.asList(new Parameter(IDataType.STRING), new Parameter(IDataType.STRING)), IDataType.BOOLEAN);
        }

        @Override
        protected void validateParameter(IExpression parameter, int index) {
            if (index == 1) {
                if (!(parameter instanceof LiteralExpression)) {
                    throw new InvalidExpressionException("The 2nd parameter of hasToken must be a constant");
                }
            }
            super.validateParameter(parameter, index);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String haystack = (String) parameters.get(0);
            String needle = (String) parameters.get(1);
            return haystack != null && needle != null && haystack.contains(needle);
        }
    }

    public static class Length extends AbstractFunction {
        public Length() {
            super("length", Collections.singletonList(new Parameter(IDataType.STRING)), IDataType.LONG);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            return str == null ? 0 : str.length();
        }
    }

    public static class Lower extends AbstractFunction {
        public Lower() {
            super("lower", Collections.singletonList(new Parameter(IDataType.STRING)), IDataType.STRING);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            return str != null ? str.toLowerCase(Locale.ENGLISH) : null;
        }
    }

    public static class Upper extends AbstractFunction {
        public Upper() {
            super("upper", Collections.singletonList(new Parameter(IDataType.STRING)), IDataType.STRING);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            return str != null ? str.toUpperCase(Locale.ENGLISH) : null;
        }
    }


    public static class StartsWith extends AbstractFunction {
        public StartsWith() {
            super("startsWith", Arrays.asList(new Parameter(IDataType.STRING), new Parameter(IDataType.STRING)), IDataType.BOOLEAN);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            String prefix = (String) parameters.get(1);
            return str != null && prefix != null && str.startsWith(prefix);
        }
    }

    public static class EndsWith extends AbstractFunction {
        public EndsWith() {
            super("endsWith",
                  Arrays.asList(new Parameter(IDataType.STRING), new Parameter(IDataType.STRING)),
                  IDataType.BOOLEAN);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            String suffix = (String) parameters.get(1);
            return str != null && suffix != null && str.endsWith(suffix);
        }
    }

    public static class Substring extends AbstractFunction {

        public Substring() {
            super("substring", Arrays.asList(new Parameter(IDataType.STRING),
                                             new Parameter(IDataType.LONG),
                                             new Parameter(IDataType.LONG)), IDataType.STRING);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            Number offset = (Number) parameters.get(1);
            Number length = (Number) parameters.get(2);
            return str == null ? null : str.substring(offset.intValue(), offset.intValue() + length.intValue());
        }
    }

    public static class Trim extends AbstractFunction {
        public Trim() {
            super("trim", Collections.singletonList(new Parameter(IDataType.STRING)), IDataType.STRING);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            return str == null ? null : str.trim();
        }
    }

    public static class TrimLeft extends AbstractFunction {
        public TrimLeft() {
            super("trimLeft", Collections.singletonList(new Parameter(IDataType.STRING)), IDataType.STRING);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            if (str == null) {
                return null;
            }

            int index = 0;

            //noinspection StatementWithEmptyBody
            for (int size = str.length(); index < size && Character.isWhitespace(str.charAt(index)); index++) {
            }

            return index == 0 ? str : str.substring(index);
        }
    }

    public static class TrimRight extends AbstractFunction {
        public TrimRight() {
            super("trimRight", Collections.singletonList(new Parameter(IDataType.STRING)), IDataType.STRING);
        }

        @Override
        public Object evaluate(List<Object> parameters) {
            String str = (String) parameters.get(0);
            if (str == null) {
                return null;
            }

            int index = str.length() - 1;

            //noinspection StatementWithEmptyBody
            for (; index >= 0 && Character.isWhitespace(str.charAt(index)); index--) {
            }

            return index < 0 ? "" : str.substring(0, index + 1);
        }
    }


}

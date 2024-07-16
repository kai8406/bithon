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

package org.bithon.component.commons.expression;

import org.bithon.component.commons.utils.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * AND/OR
 *
 * @author frankchen
 */
public abstract class LogicalExpression implements IExpression {

    public static final String AND = "AND";
    public static final String OR = "OR";
    public static final String NOT = "NOT";

    public static LogicalExpression create(String operator, List<IExpression> expressions) {
        switch (operator) {
            case AND:
                return new AND(expressions);
            case OR:
                return new OR(expressions);
            case NOT:
                return new NOT(expressions);
            default:
                throw new UnsupportedOperationException("Unsupported operator " + operator);
        }
    }

    protected final String operator;
    protected List<IExpression> operands;

    protected LogicalExpression(String operator, List<IExpression> operands) {
        this.operator = operator.toUpperCase(Locale.ENGLISH);
        this.operands = operands;
    }

    public String getOperator() {
        return operator;
    }

    public List<IExpression> getOperands() {
        return operands;
    }

    /**
     * Allow to be modified so that the expressions can be optimized
     */
    public void setOperands(List<IExpression> operands) {
        this.operands = operands;
    }

    @Override
    public IDataType getDataType() {
        return IDataType.BOOLEAN;
    }

    @Override
    public String getType() {
        return "logical";
    }

    @Override
    public void accept(IExpressionVisitor visitor) {
        if (visitor.visit(this)) {
            for (IExpression operand : operands) {
                operand.accept(visitor);
            }
        }
    }

    @Override
    public <T> T accept(IExpressionVisitor2<T> visitor) {
        return visitor.visit(this);
    }

    public abstract LogicalExpression copy(List<IExpression> expressionList);

    public static boolean toBoolean(Object value) {
        if (value == null) {
            throw new RuntimeException("value is null");
        }
        if (value instanceof Boolean) {
            return (boolean) value;
        }
        if (value instanceof Long) {
            return ((long) value) != 0;
        }
        if (value instanceof Integer) {
            return ((int) value) != 0;
        }
        if (value instanceof Short) {
            return ((short) value) != 0;
        }
        if (value instanceof Byte) {
            return ((byte) value) != 0;
        }
        if (value instanceof Float) {
            return (float) value != 0;
        }
        if (value instanceof Double) {
            return ((double) value) != 0;
        }
        if (value instanceof String) {
            return "true".equals(value);
        }
        throw new RuntimeException(StringUtils.format("value [%s] can not be cast to type of Boolean.", value));
    }

    public static class AND extends LogicalExpression {
        public AND(List<IExpression> operands) {
            super(AND, operands);
        }

        public AND(IExpression... expressions) {
            super(AND, new ArrayList<>(Arrays.asList(expressions)));
        }

        @Override
        public Object evaluate(IEvaluationContext context) {
            for (IExpression expression : this.operands) {
                boolean v = toBoolean(expression.evaluate(context));
                if (!v) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public LogicalExpression copy(List<IExpression> expressionList) {
            return new AND(expressionList);
        }
    }

    public static class OR extends LogicalExpression {

        public OR(List<IExpression> operands) {
            super(OR, operands);
        }

        public OR(IExpression... operands) {
            // Arrays.asList returns unmodified copy,
            // but we need a modifiable one so that further optimizer can be applied on this expression
            super(OR, new ArrayList<>(Arrays.asList(operands)));
        }

        @Override
        public Object evaluate(IEvaluationContext context) {
            for (IExpression expression : this.operands) {
                boolean v = toBoolean(expression.evaluate(context));
                if (v) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public LogicalExpression copy(List<IExpression> expressionList) {
            return new OR(expressionList);
        }
    }

    public static class NOT extends LogicalExpression {

        public NOT(List<IExpression> operands) {
            super(NOT, operands);
        }

        public NOT(IExpression... operands) {
            // Arrays.asList returns unmodified copy,
            // but we need a modifiable one so that further optimizer can be applied on this expression
            super(NOT, new ArrayList<>(Arrays.asList(operands)));
        }

        public NOT(IExpression expression) {
            super(NOT, new ArrayList<>(Collections.singletonList(expression)));
        }

        @Override
        public Object evaluate(IEvaluationContext context) {
            IExpression expression = operands.get(0);
            return !toBoolean(expression.evaluate(context));
        }

        @Override
        public LogicalExpression copy(List<IExpression> expressionList) {
            return new NOT(expressionList);
        }
    }
}

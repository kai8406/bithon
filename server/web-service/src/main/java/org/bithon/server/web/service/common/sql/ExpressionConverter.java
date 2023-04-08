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

package org.bithon.server.web.service.common.sql;

import org.apache.calcite.sql.SqlCall;
import org.apache.calcite.sql.SqlIdentifier;
import org.apache.calcite.sql.SqlLiteral;
import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.SqlOperator;
import org.apache.calcite.sql.type.SqlTypeName;
import org.apache.calcite.sql.util.SqlBasicVisitor;
import org.apache.calcite.util.NlsString;
import org.bithon.component.commons.expression.BinaryExpression;
import org.bithon.component.commons.expression.IExpression;
import org.bithon.component.commons.expression.IdentifierExpression;
import org.bithon.component.commons.expression.LiteralExpression;
import org.bithon.component.commons.expression.LogicalExpression;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * @author frank.chen021@outlook.com
 * @date 2023/4/8 11:33
 */
public class ExpressionConverter extends SqlBasicVisitor<IExpression> {

    public static IExpression toExpression(SqlNode node) {
        IExpression expression = node.accept(new ExpressionConverter());
        return optimize(expression);
    }

    private static IExpression optimize(IExpression expression) {
        if (isAlwaysTrue(expression)) {
            return null;
        }

        if (!(expression instanceof LogicalExpression)) {
            return expression;
        }
        LogicalExpression logicalExpression = (LogicalExpression) expression;
        if (!"AND".equals(logicalExpression.getOperator())) {
            return expression;
        }

        List<IExpression> newOperands = new ArrayList<>(logicalExpression.getOperands().size());
        for (IExpression operand : logicalExpression.getOperands()) {
            if (operand instanceof LogicalExpression) {
                // Recursive
                operand = optimize(operand);
            }
            if (!isAlwaysTrue(operand)) {
                newOperands.add(operand);
            }
        }
        if (newOperands.size() < logicalExpression.getOperands().size()) {
            if (newOperands.size() == 1) {
                return newOperands.get(0);
            } else {
                return new LogicalExpression(logicalExpression.getOperator(), newOperands);
            }
        }
        return logicalExpression;
    }

    private static boolean isAlwaysTrue(IExpression expression) {
        if (!(expression instanceof BinaryExpression)) {
            return false;
        }
        BinaryExpression binaryExpression = (BinaryExpression) expression;
        IExpression left = binaryExpression.getLeft();
        IExpression right = binaryExpression.getRight();
        if (left instanceof LiteralExpression && right instanceof LiteralExpression) {
            return ((LiteralExpression) left).getValue().equals(((LiteralExpression) right).getValue());
        }
        return false;
    }

    @Override
    public IExpression visit(SqlCall call) {
        SqlOperator operator = call.getOperator();
        List<IExpression> operands = new ArrayList<>();
        for (SqlNode operand : call.getOperandList()) {
            IExpression expression = operand.accept(this);
            operands.add(expression);
        }
        switch (operator.getKind()) {
            case AND:
            case OR:
                return new LogicalExpression(operator.getName().toUpperCase(Locale.ENGLISH), operands);
            case NOT:
                if (operands.size() != 1) {
                    throw new IllegalArgumentException("NOT operator should have exactly one operand");
                }
                return new LogicalExpression(operator.getName(), Collections.singletonList(operands.get(0)));
            case EQUALS:
            case NOT_EQUALS:
            case GREATER_THAN:
            case GREATER_THAN_OR_EQUAL:
            case LESS_THAN:
            case LESS_THAN_OR_EQUAL:
                if (operands.size() != 2) {
                    throw new IllegalArgumentException("Comparison operator should have exactly two operands");
                }
                String operatorName = operator.getName();
                IExpression left = operands.get(0);
                IExpression right = operands.get(1);
                return new BinaryExpression(operatorName, left, right);
            default:
                throw new IllegalArgumentException("Unknown operator: " + operator);
        }
    }

    @Override
    public IExpression visit(SqlIdentifier identifier) {
        String columnName = identifier.toString();
        return new IdentifierExpression(columnName);
    }

    @Override
    public IExpression visit(SqlLiteral literal) {
        SqlTypeName typeName = literal.getTypeName();
        switch (typeName) {
            case CHAR:
            case VARCHAR:
                NlsString nlsString = (NlsString) literal.getValue();
                String stringValue = nlsString.getValue();
                return new LiteralExpression(stringValue);
            case INTEGER:
            case BIGINT:
            case FLOAT:
            case DOUBLE:
            case DECIMAL:
                Number numericValue = (Number) literal.getValue();
                return new LiteralExpression(numericValue);
            case BOOLEAN:
                Boolean booleanValue = (Boolean) literal.getValue();
                return new LiteralExpression(booleanValue);
            default:
                throw new IllegalArgumentException("Unsupported literal type: " + typeName + ", literal: " + literal.toValue());
        }
    }
}

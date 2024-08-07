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

package org.bithon.server.storage.datasource.query.ast;

/**
 * @author frank.chen021@outlook.com
 * @date 2022/9/4 15:05
 */
public interface IASTNodeVisitor {

    void before(QueryExpression queryExpression);

    void visit(QueryExpression queryExpression);

    void after(QueryExpression queryExpression);

    void visit(OrderBy orderBy);

    void visit(Table table);

    void visit(Where where);

    void visit(GroupBy groupBy);

    void visit(From from);

    void before(Function function);

    void after(Function function);

    void visit(StringNode stringNode);

    void visit(int index, int count, SelectColumn selectColumn);

    void visit(Column column);

    void visit(ColumnAlias alias);

    void visit(Limit limit);

    void visit(Expression expression);
}

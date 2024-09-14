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

    void visit(OrderByClause orderBy);

    void visit(TableIdentifier table);

    void visit(WhereClause where);

    void visit(GroupByClause groupBy);

    void visit(HavingClause having);

    void visit(FromClause from);

    void visit(TextNode textNode);

    void visit(int index, int count, Selector selector);

    void visit(Column column);

    void visit(Alias alias);

    void visit(LimitClause limit);

    void visit(Expression expression);
}

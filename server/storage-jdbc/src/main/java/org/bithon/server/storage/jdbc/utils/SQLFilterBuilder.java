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

package org.bithon.server.storage.jdbc.utils;

import org.bithon.component.commons.utils.Preconditions;
import org.bithon.server.common.matcher.BetweenMatcher;
import org.bithon.server.common.matcher.IMatcherVisitor;
import org.bithon.server.common.matcher.StringAntPathMatcher;
import org.bithon.server.common.matcher.StringContainsMatcher;
import org.bithon.server.common.matcher.StringEndWithMatcher;
import org.bithon.server.common.matcher.StringEqualMatcher;
import org.bithon.server.common.matcher.StringIContainsMatcher;
import org.bithon.server.common.matcher.StringNotEqualMatcher;
import org.bithon.server.common.matcher.StringRegexMatcher;
import org.bithon.server.common.matcher.StringStartsWithMatcher;
import org.bithon.server.metric.DataSourceSchema;
import org.bithon.server.metric.dimension.IDimensionSpec;
import org.bithon.server.metric.storage.DimensionFilter;
import org.bithon.server.metric.storage.IFilter;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * build SQL where clause
 */
public class SQLFilterBuilder implements IMatcherVisitor<String> {
    private final String fieldName;

    public SQLFilterBuilder(String fieldName) {
        this.fieldName = fieldName;
    }

    public SQLFilterBuilder(DataSourceSchema schema, IFilter filter) {
        IDimensionSpec dimSpec = null;
        if (IFilter.TYPE_DIMENSION.equals(filter.getType())) {
            String nameType = ((DimensionFilter) filter).getNameType();
            if ("name".equals(nameType)) {
                dimSpec = schema.getDimensionSpecByName(filter.getName());
            } else if ("alias".equals(nameType)) {
                dimSpec = schema.getDimensionSpecByAlias(filter.getName());
            }
            Preconditions.checkNotNull(dimSpec, "dimension [%s] is not defined in data source [%s]", filter.getName(), schema.getName());

            this.fieldName = dimSpec.getName();
        } else {
            Preconditions.checkNotNull(schema.getMetricSpecByName(filter.getName()),
                                       "metric [%s] is not defined in data source [%s]", filter.getName(), schema.getName());

            this.fieldName = filter.getName();
        }
    }

    public static String build(DataSourceSchema schema, Collection<IFilter> filters) {
        return build(schema, filters.stream());
    }

    public static String build(DataSourceSchema schema, Stream<IFilter> stream) {
        return stream.map(cond -> cond.getMatcher()
                                      .accept(new SQLFilterBuilder(schema, cond)))
                     .collect(Collectors.joining(" AND "));
    }

    @Override
    public String visit(StringEqualMatcher matcher) {
        StringBuilder sb = new StringBuilder(64);
        sb.append("\"");
        sb.append(fieldName);
        sb.append("\"");
        sb.append("=");
        sb.append('\'');
        sb.append(matcher.getPattern());
        sb.append('\'');
        return sb.toString();
    }

    @Override
    public String visit(StringNotEqualMatcher matcher) {
        StringBuilder sb = new StringBuilder(64);
        sb.append("\"");
        sb.append(fieldName);
        sb.append("\"");
        sb.append("<>");
        sb.append('\'');
        sb.append(matcher.getPattern());
        sb.append('\'');
        return sb.toString();
    }

    @Override
    public String visit(StringAntPathMatcher matcher) {
        throw new RuntimeException("Not Supported Now");
    }

    @Override
    public String visit(StringContainsMatcher matcher) {
        throw new RuntimeException("Not Supported Now");
    }

    @Override
    public String visit(StringEndWithMatcher matcher) {
        throw new RuntimeException("Not Supported Now");
    }

    @Override
    public String visit(StringIContainsMatcher matcher) {
        throw new RuntimeException("Not Supported Now");
    }

    @Override
    public String visit(StringRegexMatcher matcher) {
        throw new RuntimeException("Not Supported Now");
    }

    @Override
    public String visit(StringStartsWithMatcher matcher) {
        throw new RuntimeException("Not Supported Now");
    }

    @Override
    public String visit(BetweenMatcher matcher) {
        StringBuilder sb = new StringBuilder(32);
        sb.append("\"");
        sb.append(fieldName);
        sb.append("\"");
        sb.append(" BETWEEN ");
        sb.append(matcher.getLower());
        sb.append(" AND ");
        sb.append(matcher.getUpper());
        return sb.toString();
    }
}

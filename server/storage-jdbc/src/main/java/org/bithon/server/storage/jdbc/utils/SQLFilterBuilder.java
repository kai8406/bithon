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

import org.bithon.server.common.matcher.AntPathMatcher;
import org.bithon.server.common.matcher.ContainsMatcher;
import org.bithon.server.common.matcher.EndwithMatcher;
import org.bithon.server.common.matcher.EqualMatcher;
import org.bithon.server.common.matcher.IContainsMatcher;
import org.bithon.server.common.matcher.IMatcherVisitor;
import org.bithon.server.common.matcher.NotEqualMatcher;
import org.bithon.server.common.matcher.RegexMatcher;
import org.bithon.server.common.matcher.StartwithMatcher;
import org.bithon.server.metric.storage.DimensionCondition;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * build SQL where clause
 */
public class SQLFilterBuilder implements IMatcherVisitor<String> {
    private final String name;

    public SQLFilterBuilder(String name) {
        this.name = name;
    }

    public static String build(Collection<DimensionCondition> filters) {
        return filters.stream()
                      .map(dimension -> dimension.getMatcher()
                                                 .accept(new SQLFilterBuilder(dimension.getDimension())))
                      .collect(Collectors.joining(" AND "));
    }

    @Override
    public String visit(EqualMatcher matcher) {
        StringBuilder sb = new StringBuilder(64);
        sb.append("\"");
        sb.append(name);
        sb.append("\"");
        sb.append("=");
        sb.append('\'');
        sb.append(matcher.getPattern());
        sb.append('\'');
        return sb.toString();
    }

    @Override
    public String visit(NotEqualMatcher matcher) {
        StringBuilder sb = new StringBuilder(64);
        sb.append("\"");
        sb.append(name);
        sb.append("\"");
        sb.append("<>");
        sb.append('\'');
        sb.append(matcher.getPattern());
        sb.append('\'');
        return sb.toString();
    }

    @Override
    public String visit(AntPathMatcher antPathMatcher) {
        return null;
    }

    @Override
    public String visit(ContainsMatcher containsMatcher) {
        return null;
    }

    @Override
    public String visit(EndwithMatcher endwithMatcher) {
        return null;
    }

    @Override
    public String visit(IContainsMatcher iContainsMatcher) {
        return null;
    }

    @Override
    public String visit(RegexMatcher regexMatcher) {
        return null;
    }

    @Override
    public String visit(StartwithMatcher startwithMatcher) {
        return null;
    }
}
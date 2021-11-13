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

package org.bithon.server.common.matcher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.Locale;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/13 9:44 下午
 */
public class IContainsMatcher implements IStringMatcher {

    @Getter
    private final String pattern;

    @JsonCreator
    public IContainsMatcher(@JsonProperty("pattern") String pattern) {
        this.pattern = pattern.toLowerCase(Locale.ENGLISH);
    }

    @Override
    public boolean matches(String input) {
        return input.toLowerCase(Locale.ENGLISH).contains(pattern);
    }

    @Override
    public <T> T accept(IMatcherVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

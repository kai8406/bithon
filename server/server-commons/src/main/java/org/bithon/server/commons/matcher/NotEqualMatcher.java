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

package org.bithon.server.commons.matcher;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

/**
 * @author frank.chen021@outlook.com
 * @date 8/1/22 2:41 PM
 */
public class NotEqualMatcher implements IMatcher {
    @Getter
    @NotNull
    private final Object pattern;

    @JsonCreator
    public NotEqualMatcher(@JsonProperty("pattern") @NotNull Object pattern) {
        this.pattern = pattern;
    }

    @Override
    public boolean matches(Object input) {
        if (input instanceof Number) {
            if (input instanceof Integer || input instanceof Long) {
                return ((Number) input).longValue() != ((Number) pattern).longValue();
            }
            return ((Number) input).doubleValue() != ((Number) pattern).doubleValue();
        }
        return input.toString().compareTo(pattern.toString()) != 0;
    }

    @Override
    public <T> T accept(IMatcherVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

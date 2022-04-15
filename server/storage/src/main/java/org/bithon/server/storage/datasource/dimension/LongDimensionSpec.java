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

package org.bithon.server.storage.datasource.dimension;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.bithon.server.storage.datasource.typing.IValueType;
import org.bithon.server.storage.datasource.typing.LongValueType;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author frank.chen021@outlook.com
 * @date 2020/12/11 10:16 上午
 */
public class LongDimensionSpec extends AbstractDimensionSpec {

    @JsonCreator
    public LongDimensionSpec(@JsonProperty("name") @NotNull String name,
                             @JsonProperty("alias") @Nullable String alias,
                             @JsonProperty("displayText") @NotNull String displayText,
                             @JsonProperty("required") @Nullable Boolean required,
                             @JsonProperty("visible") @Nullable Boolean visible) {
        super(name, alias, displayText, required, visible);
    }

    @Override
    public int getLength() {
        return 8;
    }

    @Override
    public IValueType getValueType() {
        return LongValueType.INSTANCE;
    }

    @Override
    public <T> T accept(IDimensionSpecVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

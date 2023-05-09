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

import lombok.Getter;

import javax.annotation.Nullable;

/**
 * @author frank.chen021@outlook.com
 * @date 2022/10/30 15:08
 */
public class Limit implements IASTNode {

    @Getter
    private final int limit;

    @Getter
    private final int offset;

    public Limit(int limit, int offset) {
        this.limit = limit;
        this.offset = offset;
    }

    @Override
    public void accept(IASTNodeVisitor visitor) {
        visitor.visit(this);
    }
}

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

package org.bithon.server.storage.datasource.input.transformer;

import lombok.Getter;
import org.bithon.server.storage.datasource.input.IInputRow;

/**
 * @author frank.chen021@outlook.com
 * @date 11/4/22 11:54 PM
 */
public abstract class AbstractSimpleTransformer implements ITransformer {

    /**
     * the field name generated by this transformer
     */
    @Getter
    protected final String field;

    protected AbstractSimpleTransformer(String field) {
        this.field = field;
    }

    @Override
    public boolean transform(IInputRow inputRow) {
        Object obj = this.transformInternal(inputRow);
        if (obj != null) {
            inputRow.updateColumn(field, obj);
        }
        return true;
    }

    protected abstract Object transformInternal(IInputRow row);
}

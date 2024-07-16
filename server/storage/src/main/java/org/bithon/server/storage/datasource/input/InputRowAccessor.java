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

package org.bithon.server.storage.datasource.input;

import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 2023/4/26 22:09
 */
public class InputRowAccessor {

    @FunctionalInterface
    public interface ISetter {
        void set(IInputRow inputRow, Object val);
    }

    @SuppressWarnings("rawtypes")
    public static ISetter createSetter(String path) {
        if (path.indexOf('.') <= 0) {
            // Not a tree style path
            return (inputRow, v) -> inputRow.updateColumn(path, v);
        }

        final String[] parts = path.split("\\.");
        return (inputRow, v) -> {
            Object obj = inputRow.getCol(parts[0]);
            if (!(obj instanceof Map map)) {
                return;
            }

            int i = 1;
            while (i < parts.length - 1) {
                obj = map.get(parts[i]);
                if (!(obj instanceof Map)) {
                    return;
                }
                map = (Map) obj;
                i++;
            }

            // noinspection unchecked
            map.put(parts[i], v);
        };
    }
}

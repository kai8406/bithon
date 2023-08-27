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

package org.bithon.server.storage.datasource.builtin;

import org.bithon.component.commons.expression.function.IDataType;
import org.bithon.component.commons.expression.function.IFunction;
import org.bithon.component.commons.expression.function.Parameter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Manage the defs of all the supported functions
 *
 * @author frank.chen021@outlook.com
 * @date 2022/11/2 17:34
 */
public class Functions implements IFunctionProvider {
    private static final Functions INSTANCE = new Functions();

    public static Functions getInstance() {
        return INSTANCE;
    }

    private final Map<String, IFunction> functionMap = new HashMap<>(17);

    public Functions() {
        register(new Function("round", Arrays.asList(new Parameter(IDataType.DOUBLE), new Parameter(IDataType.LONG))));
    }

    private void register(Function function) {
        functionMap.put(function.getName().toLowerCase(Locale.ENGLISH), function);
    }

    @Override
    public IFunction getFunction(String name) {
        return functionMap.get(name.toLowerCase(Locale.ENGLISH));
    }
}

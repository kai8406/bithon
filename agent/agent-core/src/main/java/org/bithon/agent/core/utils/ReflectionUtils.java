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

package org.bithon.agent.core.utils;

import java.lang.reflect.Field;

/**
 * @author frankchen
 */
public class ReflectionUtils {

    public static Object getFieldValue(Object obj,
                                       String fieldName) {
        Object result = null;
        Field field;
        try {
            field = getField(obj.getClass(), fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }
        field.setAccessible(true);
        try {
            result = field.get(obj);
        } catch (IllegalAccessException ignored) {
        }
        return result;
    }

    public static Field getField(Class<?> clazz,
                                 String fieldName) throws NoSuchFieldException {
        while (clazz != null) {
            try {
                return clazz.getDeclaredField(fieldName);
            } catch (NoSuchFieldException ignored) {
                clazz = clazz.getSuperclass();
            }
        }
        throw new NoSuchFieldException(fieldName);
    }
}

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

package org.bithon.component.commons.expression.validation;

import java.util.Locale;

/**
 * @author frank.chen021@outlook.com
 * @date 2024/1/20 14:07
 */
public class ExpressionValidationException extends RuntimeException {

    public ExpressionValidationException(String format, Object... args) {
        super(String.format(Locale.ENGLISH, format, args));
    }
}

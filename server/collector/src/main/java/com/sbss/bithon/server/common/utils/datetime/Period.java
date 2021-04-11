/*
 *    Copyright 2020 bithon.cn
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

package com.sbss.bithon.server.common.utils.datetime;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/4/11 18:33
 */
public class Period {
    @Getter
    private final long milliseconds;

    @JsonCreator
    public Period(String period) {
        milliseconds = org.joda.time.Period.parse(period).toStandardDuration().getMillis();
    }

    public static Period years(int i) {
        return new Period(String.format("P%dY", i));
    }
}

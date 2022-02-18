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

package org.bithon.server.metric.storage;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bithon.server.metric.DataSourceSchema;

import java.util.List;

/**
 * @author Frank Chen
 * @date 2/2/22 11:27 AM
 */
@Data
@AllArgsConstructor
public class ListQuery {
    private final DataSourceSchema schema;
    private final List<String> columns;
    private final List<DimensionCondition> filters;
    private final Interval interval;
    private final String order;
    private final String orderBy;
    private final int pageNumber;
    private final int pageSize;
}
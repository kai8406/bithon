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

package org.bithon.server.alerting.manager.api.parameter;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bithon.server.storage.alerting.pojo.AlertRecordObject;

import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2021/1/11
 */
@Data
@AllArgsConstructor
public class GetAlertRecordListResponse {
    private Integer total;
    private List<AlertRecordObject> rows;
}

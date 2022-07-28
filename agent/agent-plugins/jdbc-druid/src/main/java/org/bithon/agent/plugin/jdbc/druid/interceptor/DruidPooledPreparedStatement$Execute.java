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

package org.bithon.agent.plugin.jdbc.druid.interceptor;

import com.alibaba.druid.pool.DruidPooledPreparedStatement;
import org.bithon.agent.bootstrap.aop.AopContext;

/**
 * @author frankchen
 * @date 2022-07-27
 */
public class DruidPooledPreparedStatement$Execute extends DruidStatementAbstractExecute {
    @Override
    protected String getExecutingSql(AopContext aopContext) {
        return ((DruidPooledPreparedStatement) aopContext.getTarget()).getSql();
    }
}

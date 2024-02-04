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

package org.bithon.server.storage.jdbc.h2;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.OptBoolean;
import org.bithon.server.storage.datasource.query.IDataSourceReader;
import org.bithon.server.storage.datasource.store.ExternalDataStoreSpec;
import org.bithon.server.storage.datasource.store.IDataStoreSpec;
import org.bithon.server.storage.jdbc.common.dialect.SqlDialectManager;
import org.bithon.server.storage.jdbc.metric.MetricJdbcReader;

import java.util.Map;

/**
 * @author Frank Chen
 * @date 29/1/24 11:49 am
 */
public class ExternalH2DataStoreSpec extends ExternalDataStoreSpec {

    private final SqlDialectManager sqlDialectManager;

    public ExternalH2DataStoreSpec(@JsonProperty("properties") Map<String, Object> properties,
                                   @JsonProperty("store") String store,
                                   @JacksonInject(useInput = OptBoolean.FALSE) SqlDialectManager sqlDialectManager) {
        super(properties, store);
        this.sqlDialectManager = sqlDialectManager;
    }

    @Override
    public IDataStoreSpec hideSensitiveInformation() {
        return new ExternalH2DataStoreSpec(this.getSensitiveHiddenProps(), this.store, this.sqlDialectManager);
    }

    @Override
    public IDataSourceReader createReader() {
        return new MetricJdbcReader(store, this.properties, sqlDialectManager.getSqlDialect("h2"));
    }
}

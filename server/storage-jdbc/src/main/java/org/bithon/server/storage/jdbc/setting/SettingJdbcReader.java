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

package org.bithon.server.storage.jdbc.setting;

import lombok.extern.slf4j.Slf4j;
import org.bithon.server.storage.jdbc.common.jooq.Tables;
import org.bithon.server.storage.setting.ISettingReader;
import org.jooq.DSLContext;
import org.jooq.Record;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 4/11/21 3:18 pm
 */
@Slf4j
public class SettingJdbcReader implements ISettingReader {

    private final DSLContext dslContext;

    public SettingJdbcReader(DSLContext dslContext) {
        this.dslContext = dslContext;
    }

    @Override
    public List<SettingEntry> getSettings(String appName, long since) {
        return dslContext.selectFrom(Tables.BITHON_AGENT_SETTING)
                         .where(Tables.BITHON_AGENT_SETTING.APPNAME.eq(appName))
                         .and(Tables.BITHON_AGENT_SETTING.UPDATEDAT.gt(new Timestamp(since).toLocalDateTime()))
                         .fetch()
                         .map(this::toSettingEntry);
    }

    protected SettingEntry toSettingEntry(Record record) {
        SettingEntry entry = new SettingEntry();
        entry.setName(record.get(Tables.BITHON_AGENT_SETTING.SETTINGNAME));
        entry.setValue(record.get(Tables.BITHON_AGENT_SETTING.SETTING));
        entry.setFormat(record.get(Tables.BITHON_AGENT_SETTING.FORMAT));
        entry.setCreatedAt(Timestamp.valueOf(record.get(Tables.BITHON_AGENT_SETTING.TIMESTAMP)));
        entry.setUpdatedAt(Timestamp.valueOf(record.get(Tables.BITHON_AGENT_SETTING.UPDATEDAT)));
        return entry;
    }
}

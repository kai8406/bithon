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

package org.bithon.server.storage.jdbc.event;

import org.bithon.component.commons.expression.IExpression;
import org.bithon.server.commons.time.TimeSpan;
import org.bithon.server.storage.datasource.DataSourceSchema;
import org.bithon.server.storage.event.Event;
import org.bithon.server.storage.event.IEventReader;
import org.bithon.server.storage.jdbc.common.dialect.Expression2Sql;
import org.bithon.server.storage.jdbc.common.dialect.ISqlDialect;
import org.bithon.server.storage.jdbc.common.jooq.Tables;
import org.bithon.server.storage.jdbc.common.jooq.tables.records.BithonEventRecord;
import org.jooq.DSLContext;
import org.jooq.SelectConditionStep;
import org.jooq.impl.DSL;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author frank.chen021@outlook.com
 * @date 2022/11/29 21:08
 */
public class EventJdbcReader implements IEventReader {
    private final DSLContext dslContext;
    private final DataSourceSchema eventTableSchema;
    private final ISqlDialect sqlDialect;

    EventJdbcReader(DSLContext dslContext, ISqlDialect sqlDialect, DataSourceSchema eventTableSchema) {
        this.dslContext = dslContext;
        this.sqlDialect = sqlDialect;
        this.eventTableSchema = eventTableSchema;
    }

    @Override
    public void close() {
        dslContext.close();
    }

    @Override
    public List<Event> getEventList(IExpression filter, TimeSpan start, TimeSpan end, int pageNumber, int pageSize) {
        SelectConditionStep<BithonEventRecord> step = dslContext.selectFrom(Tables.BITHON_EVENT)
                                                                .where(Tables.BITHON_EVENT.TIMESTAMP.ge(start.toTimestamp().toLocalDateTime()))
                                                                .and(Tables.BITHON_EVENT.TIMESTAMP.lt(end.toTimestamp().toLocalDateTime()));

        if (filter != null) {
            step = step.and(Expression2Sql.from(eventTableSchema, sqlDialect, filter));
        }

        return step.orderBy(Tables.BITHON_EVENT.TIMESTAMP.desc())
                   .offset(pageNumber * pageSize)
                   .limit(pageSize)
                   .fetch((r) -> {
                       Event e = new Event();
                       e.setApplication(r.getAppname());
                       e.setArgs(r.getArguments());
                       e.setInstance(r.getInstancename());
                       e.setType(r.getType());
                       e.setTimestamp(Timestamp.valueOf(r.getTimestamp()).getTime());
                       return e;
                   });
    }

    @Override
    public int getEventListSize(IExpression filter, TimeSpan start, TimeSpan end) {
        SelectConditionStep<?> step = dslContext.select(DSL.count())
                                                .from(Tables.BITHON_EVENT)
                                                .where(Tables.BITHON_EVENT.TIMESTAMP.ge(start.toTimestamp().toLocalDateTime()))
                                                .and(Tables.BITHON_EVENT.TIMESTAMP.lt(end.toTimestamp().toLocalDateTime()));

        if (filter != null) {
            step = step.and(Expression2Sql.from(eventTableSchema, sqlDialect, filter));
        }

        return (int) step.fetchOne(0);
    }
}

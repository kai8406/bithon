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

package org.bithon.server.storage.jdbc.tracing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.bithon.component.commons.exception.HttpMappableException;
import org.bithon.component.commons.expression.ComparisonExpression;
import org.bithon.component.commons.expression.ExpressionList;
import org.bithon.component.commons.expression.IExpression;
import org.bithon.component.commons.expression.IExpressionVisitor;
import org.bithon.component.commons.expression.IdentifierExpression;
import org.bithon.component.commons.expression.LiteralExpression;
import org.bithon.component.commons.expression.LogicalExpression;
import org.bithon.component.commons.time.DateTime;
import org.bithon.component.commons.tracing.SpanKind;
import org.bithon.component.commons.utils.CollectionUtils;
import org.bithon.component.commons.utils.StringUtils;
import org.bithon.server.commons.time.TimeSpan;
import org.bithon.server.storage.datasource.DataSourceSchema;
import org.bithon.server.storage.jdbc.jooq.Tables;
import org.bithon.server.storage.jdbc.utils.ISqlDialect;
import org.bithon.server.storage.jdbc.utils.SqlFilterStatement;
import org.bithon.server.storage.tracing.ITraceReader;
import org.bithon.server.storage.tracing.TraceSpan;
import org.bithon.server.storage.tracing.TraceStorageConfig;
import org.bithon.server.storage.tracing.mapping.TraceIdMapping;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.SelectConditionStep;
import org.jooq.SelectSeekStep1;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author frank.chen021@outlook.com
 * @date 30/12/20
 */
@Slf4j
public class TraceJdbcReader implements ITraceReader {

    public static final String SPAN_TAGS_PREFIX = "tags.";
    protected final DSLContext dslContext;
    protected final ObjectMapper objectMapper;
    protected final TraceStorageConfig traceStorageConfig;
    protected final DataSourceSchema traceSpanSchema;
    protected final DataSourceSchema traceTagIndexSchema;
    protected final ISqlDialect sqlDialect;

    public TraceJdbcReader(DSLContext dslContext,
                           ObjectMapper objectMapper,
                           DataSourceSchema traceSpanSchema,
                           DataSourceSchema traceTagIndexSchema,
                           TraceStorageConfig traceStorageConfig,
                           ISqlDialect sqlDialect) {
        this.dslContext = dslContext;
        this.objectMapper = objectMapper;
        this.traceStorageConfig = traceStorageConfig;
        this.traceSpanSchema = traceSpanSchema;
        this.traceTagIndexSchema = traceTagIndexSchema;
        this.sqlDialect = sqlDialect;
    }

    @Override
    public List<TraceSpan> getTraceByTraceId(String traceId, TimeSpan start, TimeSpan end) {
        SelectConditionStep<Record> sql = dslContext.selectFrom(Tables.BITHON_TRACE_SPAN.getUnqualifiedName().quotedName())
                                                    .where(Tables.BITHON_TRACE_SPAN.TRACEID.eq(traceId));
        if (start != null) {
            sql = sql.and(Tables.BITHON_TRACE_SPAN.TIMESTAMP.ge(start.toTimestamp().toLocalDateTime()));
        }
        if (end != null) {
            sql = sql.and(Tables.BITHON_TRACE_SPAN.TIMESTAMP.lt(end.toTimestamp().toLocalDateTime()));
        }

        // For spans coming from the same application instance, sort them by the start time
        return sql.orderBy(Tables.BITHON_TRACE_SPAN.TIMESTAMP.asc(),
                           Tables.BITHON_TRACE_SPAN.INSTANCENAME,
                           Tables.BITHON_TRACE_SPAN.STARTTIMEUS)
                  .fetch(this::toTraceSpan);
    }

    @Override
    public List<TraceSpan> getTraceList(IExpression filter,
                                        List<IExpression> nonIndexedTagFilters,
                                        Map<Integer, IExpression> indexedTagFilter,
                                        Timestamp start,
                                        Timestamp end,
                                        String orderBy,
                                        String order,
                                        int pageNumber,
                                        int pageSize) {
        boolean isOnSummaryTable = isFilterOnRootSpanOnly(filter);

        Field<LocalDateTime> timestampField = isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY.TIMESTAMP : Tables.BITHON_TRACE_SPAN.TIMESTAMP;

        // NOTE:
        // 1. Here use selectFrom(String) instead of use selectFrom(table) because we want to use the raw objects returned by underlying JDBC
        // 2. If the filters contain a filter that matches the ROOT kind, then the search is built upon the summary table
        SelectConditionStep<Record> listQuery = dslContext.selectFrom(isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY.getUnqualifiedName().quotedName() : Tables.BITHON_TRACE_SPAN.getUnqualifiedName().quotedName())
                                                          .where(timestampField.greaterOrEqual(start.toLocalDateTime()).and(timestampField.le(end.toLocalDateTime())));

        if (filter != null) {
            listQuery = listQuery.and(SqlFilterStatement.from(traceSpanSchema, filter));
        }

        if (CollectionUtils.isNotEmpty(nonIndexedTagFilters)) {
            listQuery = listQuery.and(nonIndexedTagFilters.stream()
                                                          .map(this::getTagPredicate)
                                                          .collect(Collectors.joining(" AND ")));
        }

        // Build the tag query
        if (CollectionUtils.isNotEmpty(indexedTagFilter)) {
            SelectConditionStep<Record1<String>> indexedTagQuery = new IndexedTagQueryBuilder(this.traceTagIndexSchema)
                .dslContext(this.dslContext)
                .start(start.toLocalDateTime())
                .end(end.toLocalDateTime())
                .build(indexedTagFilter);

            if (isOnSummaryTable) {
                listQuery = listQuery.and(Tables.BITHON_TRACE_SPAN_SUMMARY.TRACEID.in(indexedTagQuery));
            } else {
                listQuery = listQuery.and(Tables.BITHON_TRACE_SPAN.TRACEID.in(indexedTagQuery));
            }
        }

        Field<?> orderField;
        if ("costTime".equals(orderBy)) {
            orderField = isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY.COSTTIMEMS : Tables.BITHON_TRACE_SPAN.COSTTIMEMS;
        } else if ("startTime".equals(orderBy)) {
            orderField = isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY.STARTTIMEUS : Tables.BITHON_TRACE_SPAN.COSTTIMEMS;
        } else {
            orderField = isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY.TIMESTAMP : Tables.BITHON_TRACE_SPAN.COSTTIMEMS;
        }
        SelectSeekStep1<?, ?> orderedListQuery;
        if ("desc".equals(order)) {
            orderedListQuery = listQuery.orderBy(orderField.desc());
        } else {
            orderedListQuery = listQuery.orderBy(orderField.asc());
        }

        return orderedListQuery.offset(pageNumber * pageSize)
                               .limit(pageSize)
                               .fetch(this::toTraceSpan);
    }

    @Override
    public List<Map<String, Object>> getTraceDistribution(IExpression filter,
                                                          List<IExpression> nonIndexedTagFilters, Map<Integer, IExpression> indexedTagFilter,
                                                          Timestamp start,
                                                          Timestamp end,
                                                          int interval) {
        boolean isOnSummaryTable = isFilterOnRootSpanOnly(filter);

        String timeBucket = sqlDialect.timeFloorExpression("timestamp", interval);
        StringBuilder sqlBuilder = new StringBuilder(StringUtils.format("SELECT %s AS \"_timestamp\", count(1) AS \"count\", min(\"%s\") AS \"minResponse\", avg(\"%s\") AS \"avgResponse\", max(\"%s\") AS \"maxResponse\" FROM %s",
                                                                        timeBucket,
                                                                        Tables.BITHON_TRACE_SPAN_SUMMARY.COSTTIMEMS.getName(),
                                                                        Tables.BITHON_TRACE_SPAN_SUMMARY.COSTTIMEMS.getName(),
                                                                        Tables.BITHON_TRACE_SPAN_SUMMARY.COSTTIMEMS.getName(),
                                                                        isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY.getUnqualifiedName().quotedName() : Tables.BITHON_TRACE_SPAN.getUnqualifiedName().quotedName()));
        sqlBuilder.append(StringUtils.format(" WHERE \"%s\" >= '%s' AND \"%s\" < '%s'",
                                             Tables.BITHON_TRACE_SPAN_SUMMARY.TIMESTAMP.getName(),
                                             DateTime.toYYYYMMDDhhmmss(start.getTime()),
                                             Tables.BITHON_TRACE_SPAN_SUMMARY.TIMESTAMP.getName(),
                                             DateTime.toYYYYMMDDhhmmss(end.getTime())));

        if (filter != null) {
            sqlBuilder.append(" AND ");
            sqlBuilder.append(SqlFilterStatement.from(traceSpanSchema, filter));
        }

        if (CollectionUtils.isNotEmpty(nonIndexedTagFilters)) {
            sqlBuilder.append(" AND ");
            sqlBuilder.append(nonIndexedTagFilters.stream()
                                                  .map(this::getTagPredicate)
                                                  .collect(Collectors.joining(" AND ")));
        }

        // Build the indexed tag sub query
        if (CollectionUtils.isNotEmpty(indexedTagFilter)) {
            SelectConditionStep<Record1<String>> indexedTagQuery = new IndexedTagQueryBuilder(this.traceTagIndexSchema)
                .dslContext(this.dslContext)
                .start(start.toLocalDateTime())
                .end(end.toLocalDateTime())
                .build(indexedTagFilter);
            Condition subQuery = isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY.TRACEID.in(indexedTagQuery) :
                Tables.BITHON_TRACE_SPAN.TRACEID.in(indexedTagQuery);

            sqlBuilder.append(" AND ");
            sqlBuilder.append(dslContext.renderInlined(subQuery));
        }

        sqlBuilder.append(StringUtils.format(" GROUP BY \"_timestamp\" ORDER BY \"_timestamp\"", timeBucket));

        String sql = sqlBuilder.toString();
        log.info("Get trace distribution: {}", sql);
        return dslContext.fetch(sql).intoMaps();
    }

    @Override
    public int getTraceListSize(IExpression filter,
                                List<IExpression> nonIndexedTagFilters, Map<Integer, IExpression> indexedTagFilters,
                                Timestamp start,
                                Timestamp end) {
        boolean isOnSummaryTable = isFilterOnRootSpanOnly(filter);

        Field<LocalDateTime> timestampField = isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY.TIMESTAMP : Tables.BITHON_TRACE_SPAN.TIMESTAMP;

        // NOTE:
        // 1. the query is performed on summary table or detail table based on input filters
        // 2. the WHERE clause is built on raw SQL string
        // because the jOOQ DSL expression, where(summary.TIMESTAMP.lt(xxx)), might translate the TIMESTAMP as a full qualified name,
        // but the query might be performed on the detailed table
        SelectConditionStep<Record1<Integer>> countQuery = dslContext.selectCount()
                                                                     .from(isOnSummaryTable ? Tables.BITHON_TRACE_SPAN_SUMMARY : Tables.BITHON_TRACE_SPAN)
                                                                     .where(timestampField.ge(start.toLocalDateTime()).and(timestampField.lt(end.toLocalDateTime())));

        if (filter != null) {
            countQuery = countQuery.and(SqlFilterStatement.from(traceSpanSchema, filter));
        }

        if (CollectionUtils.isNotEmpty(nonIndexedTagFilters)) {
            countQuery = countQuery.and(nonIndexedTagFilters.stream()
                                                            .map(this::getTagPredicate)
                                                            .collect(Collectors.joining(" AND ")));
        }

        // Build the indexed tag query
        SelectConditionStep<Record1<String>> indexedTagQuery = new IndexedTagQueryBuilder(this.traceTagIndexSchema)
            .dslContext(this.dslContext)
            .start(start.toLocalDateTime())
            .end(end.toLocalDateTime())
            .build(indexedTagFilters);
        if (indexedTagQuery != null) {
            if (isOnSummaryTable) {
                countQuery = countQuery.and(Tables.BITHON_TRACE_SPAN_SUMMARY.TRACEID.in(indexedTagQuery));
            } else {
                countQuery = countQuery.and(Tables.BITHON_TRACE_SPAN.TRACEID.in(indexedTagQuery));
            }
        }

        return (int) countQuery.fetchOne(0);
    }

    @Override
    public List<TraceSpan> getTraceByParentSpanId(String parentSpanId) {
        return dslContext.selectFrom(Tables.BITHON_TRACE_SPAN.getUnqualifiedName().quotedName())
                         .where(Tables.BITHON_TRACE_SPAN.PARENTSPANID.eq(parentSpanId))
                         // For spans coming from the same application instance, sort them by the start time
                         .orderBy(Tables.BITHON_TRACE_SPAN.TIMESTAMP.asc(),
                                  Tables.BITHON_TRACE_SPAN.INSTANCENAME,
                                  Tables.BITHON_TRACE_SPAN.STARTTIMEUS)
                         .fetch(this::toTraceSpan);
    }

    @Override
    public TraceIdMapping getTraceIdByMapping(String userId) {
        return dslContext.select(Tables.BITHON_TRACE_MAPPING.TRACE_ID, Tables.BITHON_TRACE_MAPPING.TIMESTAMP)
                         .from(Tables.BITHON_TRACE_MAPPING)
                         .where(Tables.BITHON_TRACE_MAPPING.USER_TX_ID.eq(userId))
                         .orderBy(Tables.BITHON_TRACE_MAPPING.TIMESTAMP.desc())
                         .limit(1)
                         .fetchOne((v) -> {
                             TraceIdMapping mapping = new TraceIdMapping();
                             mapping.setTraceId(v.getValue(0, String.class));
                             mapping.setTimestamp(v.get(1, Timestamp.class).getTime());
                             mapping.setUserId(userId);
                             return mapping;
                         });
    }

    private TraceSpan toTraceSpan(Record record) {
        TraceSpan span = new TraceSpan();
        span.appName = TraceSpanRecordAccessor.getAppName(record);
        span.instanceName = TraceSpanRecordAccessor.getInstanceName(record);
        span.traceId = TraceSpanRecordAccessor.getTraceId(record);
        span.spanId = TraceSpanRecordAccessor.getSpanId(record);
        span.parentSpanId = TraceSpanRecordAccessor.getParentSpanId(record);
        span.startTime = TraceSpanRecordAccessor.getStartTime(record);
        span.costTime = TraceSpanRecordAccessor.getCostTime(record);
        span.endTime = TraceSpanRecordAccessor.getEndTime(record);
        span.name = TraceSpanRecordAccessor.getName(record);
        span.kind = TraceSpanRecordAccessor.getKind(record);
        span.method = TraceSpanRecordAccessor.getMethod(record);
        span.clazz = TraceSpanRecordAccessor.getClazz(record);
        span.status = TraceSpanRecordAccessor.getStatus(record);
        span.normalizedUri = TraceSpanRecordAccessor.getNormalizedUrl(record);
        if (StringUtils.hasText(TraceSpanRecordAccessor.getTags(record))) {
            // Compatible with old data
            try {
                span.tags = objectMapper.readValue(TraceSpanRecordAccessor.getTags(record), TraceSpan.TagDeserializer.TYPE);
            } catch (JsonProcessingException ignored) {
            }
        } else {
            span.tags = toTagMap(TraceSpanRecordAccessor.getAttributes(record));
        }
        return span;
    }

    /**
     * RootSpan has been extracted into trace_span_summary table during ingestion.
     * If the filter on the 'kind'
     * column selects those rows extracted into the summary table, later we only query the summary table.
     */
    private boolean isFilterOnRootSpanOnly(IExpression expression) {
        if (expression == null) {
            return true;
        }

        final String kindFieldName = Tables.BITHON_TRACE_SPAN_SUMMARY.KIND.getName();
        SpanKindIsRootDetector detector = new SpanKindIsRootDetector(kindFieldName);
        expression.accept(detector);
        return detector.isTrue;
    }

    /**
     * Get the SQL predicate expression for give tag filter.
     * For the default implementation, ONLY the 'equal' filter is supported, and it's turned into a LIKE search.
     */
    protected String getTagPredicate(IExpression tagFilter) {
        if (!(tagFilter instanceof ComparisonExpression.EQ)) {
            throw new UnsupportedOperationException(StringUtils.format("[%s] matcher on tag field is not supported on this database.",
                                                                       tagFilter.getClass().getSimpleName()));
        }

        IExpression left = ((ComparisonExpression.EQ) tagFilter).getLeft();
        IExpression right = ((ComparisonExpression.EQ) tagFilter).getRight();
        if (!(left instanceof IdentifierExpression)) {
            throw new UnsupportedOperationException(StringUtils.format("The left operator in expression [%s] should be identifier only.",
                                                                       tagFilter.serializeToText()));
        }
        if (!(right instanceof LiteralExpression)) {
            throw new UnsupportedOperationException(StringUtils.format("The right operator in expression [%s] should be literal only.",
                                                                       tagFilter.serializeToText()));
        }

        return StringUtils.format("\"%s\" LIKE '%%\"%s\":\"%s\"%%'",
                                  Tables.BITHON_TRACE_SPAN.ATTRIBUTES.getName(),
                                  ((IdentifierExpression) left).getIdentifier().substring(SPAN_TAGS_PREFIX.length()),
                                  ((LiteralExpression) right).getValue());
    }

    protected Map<String, String> toTagMap(Object attributes) {
        try {
            return objectMapper.readValue((String) attributes, TraceSpan.TagDeserializer.TYPE);
        } catch (JsonProcessingException ignored) {
            return Collections.emptyMap();
        }
    }

    static class SpanKindIsRootDetector implements IExpressionVisitor {
        private boolean isTrue = false;

        private final String kindFieldName;

        SpanKindIsRootDetector(String kindFieldName) {
            this.kindFieldName = kindFieldName;
        }

        @Override
        public boolean visit(ComparisonExpression expression) {
            if (!(expression.getLeft() instanceof IdentifierExpression)) {
                throw new HttpMappableException(HttpStatus.BAD_REQUEST.value(),
                                                "The left operator in the expression [%s] is type of %s, however only Identifier expression is supported now.",
                                                expression.serializeToText(),
                                                expression.getLeft().getClass().getSimpleName());
            }

            if (expression instanceof ComparisonExpression.EQ) {
                IExpression left = expression.getLeft();
                IExpression right = expression.getRight();

                if (((IdentifierExpression) left).getIdentifier().equals(kindFieldName)) {
                    if (right instanceof LiteralExpression) {
                        String kindValue = (String) ((LiteralExpression) right).getValue();
                        isTrue = SpanKind.isRootSpan(kindValue);
                    }
                }
                return false;
            }

            if (expression instanceof ComparisonExpression.IN) {
                IExpression left = expression.getLeft();
                IExpression right = expression.getRight();

                if (((IdentifierExpression) left).getIdentifier().equals(kindFieldName)) {
                    isTrue = ((ExpressionList) right).getExpressions()
                                                     .stream()
                                                     .allMatch((s) -> (s instanceof LiteralExpression) && SpanKind.isRootSpan(((LiteralExpression) s).getValue()));

                    // TODO: Apply more optimization here is the collection size equals to the size of all root spans
                    // We can remove such filter
                }
            }

            return true;
        }

        @Override
        public boolean visit(LogicalExpression expression) {
            if (expression instanceof LogicalExpression.NOT) {
                // A little complicated to apply the optimization, ignore
                return false;
            }

            return true;
        }
    }
}

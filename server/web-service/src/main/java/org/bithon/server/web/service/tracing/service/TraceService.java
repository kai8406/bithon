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

package org.bithon.server.web.service.tracing.service;

import org.bithon.component.commons.expression.ConditionalExpression;
import org.bithon.component.commons.expression.IExpression;
import org.bithon.component.commons.expression.IExpressionInDepthVisitor;
import org.bithon.component.commons.expression.IdentifierExpression;
import org.bithon.component.commons.expression.LogicalExpression;
import org.bithon.component.commons.expression.expt.InvalidExpressionException;
import org.bithon.component.commons.utils.CloseableIterator;
import org.bithon.component.commons.utils.StringUtils;
import org.bithon.server.commons.time.TimeSpan;
import org.bithon.server.datasource.ISchema;
import org.bithon.server.datasource.column.IColumn;
import org.bithon.server.datasource.expression.ExpressionASTBuilder;
import org.bithon.server.datasource.query.Limit;
import org.bithon.server.datasource.query.OrderBy;
import org.bithon.server.storage.datasource.SchemaManager;
import org.bithon.server.storage.tracing.ITraceReader;
import org.bithon.server.storage.tracing.ITraceStorage;
import org.bithon.server.storage.tracing.TraceSpan;
import org.bithon.server.storage.tracing.mapping.TraceIdMapping;
import org.bithon.server.storage.tracing.reader.TraceFilterSplitter;
import org.bithon.server.web.service.WebServiceModuleEnabler;
import org.bithon.server.web.service.datasource.api.impl.QueryFilter;
import org.bithon.server.web.service.tracing.api.GetTraceSpanDistributionResponse;
import org.bithon.server.web.service.tracing.api.TraceSpanBo;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author frank.chen021@outlook.com
 * @date 24/11/21 7:11 pm
 */
@Service
@Conditional(value = WebServiceModuleEnabler.class)
public class TraceService {

    private final ITraceReader traceReader;
    private final ISchema summaryTableSchema;
    private final ISchema indexTableSchema;
    private final SchemaManager schemaManager;

    public TraceService(ITraceStorage traceStorage, SchemaManager schemaManager) {
        this.traceReader = traceStorage.createReader();

        this.summaryTableSchema = schemaManager.getSchema("trace_span_summary");
        this.indexTableSchema = schemaManager.getSchema("trace_span_tag_index");
        this.schemaManager = schemaManager;
    }

    public List<TraceSpan> getTraceByParentSpanId(String parentSpanId) {
        return traceReader.getTraceByParentSpanId(parentSpanId);
    }

    public CloseableIterator<TraceSpan> getTraceByTraceId(String txId,
                                                          String type,
                                                          String filterExpression,
                                                          String startTimeISO8601,
                                                          String endTimeISO8601) {
        IExpression filter = null;
        if (StringUtils.hasText(filterExpression)) {
            filter = ExpressionASTBuilder.builder().schema(schemaManager.getSchema("trace_span"))
                                         .build(filterExpression);
        }

        TimeSpan start = StringUtils.hasText(startTimeISO8601) ? TimeSpan.fromISO8601(startTimeISO8601) : null;
        TimeSpan end = StringUtils.hasText(endTimeISO8601) ? TimeSpan.fromISO8601(endTimeISO8601) : null;

        if (!"trace".equals(type)) {
            // check if the id has a user mapping
            TraceIdMapping mapping = traceReader.getTraceIdByMapping(txId);
            if (mapping != null) {
                txId = mapping.getTraceId();

                // Set the time range to narrow down the search range
                if (start == null) {
                    start = TimeSpan.fromMilliseconds(mapping.getTimestamp() - 2 * 3600 * 1000L);
                }
                if (end == null) {
                    end = TimeSpan.fromMilliseconds(mapping.getTimestamp() + 2 * 3600 * 1000L);
                }
            }
            // if there's no mapping, try to search this id as trace id
        }

        return traceReader.getTraceByTraceId(txId, filter, start, end);
    }

    public List<TraceSpanBo> transformSpanList(List<TraceSpan> spans, boolean returnTree) {
        List<TraceSpanBo> spanList = new ArrayList<>();
        Map<String, TraceSpanBo> spanMap = new HashMap<>();
        for (int i = 0; i < spans.size(); i++) {
            TraceSpan span = spans.get(i);
            TraceSpanBo bo = new TraceSpanBo();
            BeanUtils.copyProperties(span, bo);

            // Calculate unqualified class name
            if (bo.clazz != null) {
                int idx = bo.clazz.lastIndexOf('.');
                if (idx > 0) {
                    bo.unQualifiedClassName = bo.clazz.substring(idx + 1);
                } else {
                    bo.unQualifiedClassName = bo.clazz;
                }
            } else {
                bo.unQualifiedClassName = "";
            }

            spanList.add(bo);
            spanMap.put(span.spanId, bo);
        }

        List<TraceSpanBo> rootSpans = new ArrayList<>();
        for (int i = 0, size = spanList.size(); i < size; i++) {
            TraceSpanBo span = spanList.get(i);
            span.index = i;

            if (StringUtils.isEmpty(span.parentSpanId)) {
                rootSpans.add(span);
            } else {
                TraceSpanBo parentSpan = spanMap.get(span.parentSpanId);
                if (parentSpan == null) {
                    // For example, two applications: A --> B
                    // if span logs of A are not stored in Bithon,
                    // the root span of B has parentSpanId, but apparently the parent span can't be found
                    rootSpans.add(span);
                } else {
                    if (returnTree) {
                        parentSpan.children.add(span);
                    } else {
                        parentSpan.childRefs.add(i);
                    }
                }
            }
        }
        return returnTree ? rootSpans : spanList;
    }

    public int getTraceListSize(String filterExpression,
                                Timestamp start,
                                Timestamp end) {
        TraceFilterSplitter splitter = new TraceFilterSplitter(this.summaryTableSchema, this.indexTableSchema);
        splitter.split(QueryFilter.build(this.summaryTableSchema, filterExpression));

        return traceReader.getTraceListSize(splitter.getExpression(),
                                            splitter.getIndexedTagFilters(),
                                            start,
                                            end);
    }

    public List<TraceSpan> getTraceList(String filterExpression,
                                        Timestamp start,
                                        Timestamp end,
                                        OrderBy orderBy,
                                        Limit limit) {
        TraceFilterSplitter splitter = new TraceFilterSplitter(this.summaryTableSchema, this.indexTableSchema);
        splitter.split(QueryFilter.build(this.summaryTableSchema, filterExpression));

        return traceReader.getTraceList(splitter.getExpression(),
                                        splitter.getIndexedTagFilters(),
                                        start,
                                        end,
                                        orderBy,
                                        limit);
    }

    public int getTraceSpanCount(String txId,
                                 String type,
                                 String filterExpression,
                                 String startTimeISO8601,
                                 String endTimeISO8601) {
        IExpression filter = null;
        if (StringUtils.hasText(filterExpression)) {
            filter = ExpressionASTBuilder.builder()
                                         .schema(schemaManager.getSchema("trace_span"))
                                         .build(filterExpression);
        }

        TimeSpan start = StringUtils.hasText(startTimeISO8601) ? TimeSpan.fromISO8601(startTimeISO8601) : null;
        TimeSpan end = StringUtils.hasText(endTimeISO8601) ? TimeSpan.fromISO8601(endTimeISO8601) : null;

        if (!"trace".equals(type)) {
            // check if the id has a user mapping
            TraceIdMapping mapping = traceReader.getTraceIdByMapping(txId);
            if (mapping != null) {
                txId = mapping.getTraceId();

                // Set the time range to narrow down the search range
                if (start == null) {
                    start = TimeSpan.fromMilliseconds(mapping.getTimestamp() - 2 * 3600 * 1000L);
                }
                if (end == null) {
                    end = TimeSpan.fromMilliseconds(mapping.getTimestamp() + 2 * 3600 * 1000L);
                }
            }
            // if there's no mapping, try to search this id as trace id
        }

        return traceReader.getTraceSpanCount(txId, filter, start, end);
    }

    public void validSpanFilterExpression(String filterExpression) {
        IExpression expr = ExpressionASTBuilder.builder()
                                               .build(filterExpression);

        // Check if the expression is a conditional or logical expression first
        if (!(expr instanceof ConditionalExpression) && !(expr instanceof LogicalExpression)) {
            throw new InvalidExpressionException("The filter expression must be a conditional/logical expression: " +
                                                 "e.g. `appName = 'bithon'");
        }

        ISchema schema = schemaManager.getSchema("trace_span");
        expr.accept(new IExpressionInDepthVisitor() {
            @Override
            public boolean visit(IdentifierExpression expression) {
                IColumn column = schema.getColumnByName(expression.getIdentifier());
                if (column == null) {
                    throw new InvalidExpressionException("Identifier [%s] not found", expression.getIdentifier());
                }
                return true;
            }
        });
    }

    public GetTraceSpanDistributionResponse getTraceSpanDistribution(String txId,
                                                                     String type,
                                                                     String filterExpression,
                                                                     String startTimeISO8601,
                                                                     String endTimeISO8601,
                                                                     Collection<String> groups) {
        IExpression filter = null;
        if (StringUtils.hasText(filterExpression)) {
            filter = ExpressionASTBuilder.builder()
                                         .schema(schemaManager.getSchema("trace_span"))
                                         .build(filterExpression);
        }

        TimeSpan start = StringUtils.hasText(startTimeISO8601) ? TimeSpan.fromISO8601(startTimeISO8601) : null;
        TimeSpan end = StringUtils.hasText(endTimeISO8601) ? TimeSpan.fromISO8601(endTimeISO8601) : null;

        if (!"trace".equals(type)) {
            // check if the id has a user mapping
            TraceIdMapping mapping = traceReader.getTraceIdByMapping(txId);
            if (mapping != null) {
                txId = mapping.getTraceId();

                // Set the time range to narrow down the search range
                if (start == null) {
                    start = TimeSpan.fromMilliseconds(mapping.getTimestamp() - 2 * 3600 * 1000L);
                }
                if (end == null) {
                    end = TimeSpan.fromMilliseconds(mapping.getTimestamp() + 2 * 3600 * 1000L);
                }
            }
            // if there's no mapping, try to search this id as trace id
        }

        List<Map<String, Object>> records = traceReader.getTraceSpanDistribution(txId, filter, start, end, groups);
        return GetTraceSpanDistributionResponse.builder()
                                               .distribution(records)
                                               .build();
    }
}

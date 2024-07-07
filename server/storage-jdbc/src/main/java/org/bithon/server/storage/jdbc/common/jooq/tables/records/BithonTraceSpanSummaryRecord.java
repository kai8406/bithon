/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables.records;


import java.time.LocalDateTime;

import org.bithon.server.storage.jdbc.common.jooq.tables.BithonTraceSpanSummary;
import org.jooq.Field;
import org.jooq.Record17;
import org.jooq.Row17;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BithonTraceSpanSummaryRecord extends TableRecordImpl<BithonTraceSpanSummaryRecord> implements Record17<LocalDateTime, String, String, String, String, String, String, String, String, String, Long, Long, Long, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>bithon_trace_span_summary.timestamp</code>. Milli
     * Seconds
     */
    public void setTimestamp(LocalDateTime value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.timestamp</code>. Milli
     * Seconds
     */
    public LocalDateTime getTimestamp() {
        return (LocalDateTime) get(0);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.appName</code>.
     */
    public void setAppname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.appName</code>.
     */
    public String getAppname() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.instanceName</code>.
     */
    public void setInstancename(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.instanceName</code>.
     */
    public String getInstancename() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.name</code>.
     */
    public void setName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.clazz</code>.
     */
    public void setClazz(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.clazz</code>.
     */
    public String getClazz() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.method</code>.
     */
    public void setMethod(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.method</code>.
     */
    public String getMethod() {
        return (String) get(5);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.traceId</code>.
     */
    public void setTraceid(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.traceId</code>.
     */
    public String getTraceid() {
        return (String) get(6);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.spanId</code>.
     */
    public void setSpanid(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.spanId</code>.
     */
    public String getSpanid() {
        return (String) get(7);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.parentSpanId</code>.
     */
    public void setParentspanid(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.parentSpanId</code>.
     */
    public String getParentspanid() {
        return (String) get(8);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.kind</code>.
     */
    public void setKind(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.kind</code>.
     */
    public String getKind() {
        return (String) get(9);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.costTimeMs</code>. Milli
     * Second
     */
    public void setCosttimems(Long value) {
        set(10, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.costTimeMs</code>. Milli
     * Second
     */
    public Long getCosttimems() {
        return (Long) get(10);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.startTimeUs</code>. Micro
     * Second
     */
    public void setStarttimeus(Long value) {
        set(11, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.startTimeUs</code>. Micro
     * Second
     */
    public Long getStarttimeus() {
        return (Long) get(11);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.endTimeUs</code>. Micro Second
     */
    public void setEndtimeus(Long value) {
        set(12, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.endTimeUs</code>. Micro Second
     */
    public Long getEndtimeus() {
        return (Long) get(12);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.tags</code>. Kept for
     * compatibility
     */
    public void setTags(String value) {
        set(13, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.tags</code>. Kept for
     * compatibility
     */
    public String getTags() {
        return (String) get(13);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.attributes</code>.
     */
    public void setAttributes(String value) {
        set(14, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.attributes</code>.
     */
    public String getAttributes() {
        return (String) get(14);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.normalizedUrl</code>.
     */
    public void setNormalizedurl(String value) {
        set(15, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.normalizedUrl</code>.
     */
    public String getNormalizedurl() {
        return (String) get(15);
    }

    /**
     * Setter for <code>bithon_trace_span_summary.status</code>.
     */
    public void setStatus(String value) {
        set(16, value);
    }

    /**
     * Getter for <code>bithon_trace_span_summary.status</code>.
     */
    public String getStatus() {
        return (String) get(16);
    }

    // -------------------------------------------------------------------------
    // Record17 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row17<LocalDateTime, String, String, String, String, String, String, String, String, String, Long, Long, Long, String, String, String, String> fieldsRow() {
        return (Row17) super.fieldsRow();
    }

    @Override
    public Row17<LocalDateTime, String, String, String, String, String, String, String, String, String, Long, Long, Long, String, String, String, String> valuesRow() {
        return (Row17) super.valuesRow();
    }

    @Override
    public Field<LocalDateTime> field1() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.TIMESTAMP;
    }

    @Override
    public Field<String> field2() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.APPNAME;
    }

    @Override
    public Field<String> field3() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.INSTANCENAME;
    }

    @Override
    public Field<String> field4() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.NAME;
    }

    @Override
    public Field<String> field5() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.CLAZZ;
    }

    @Override
    public Field<String> field6() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.METHOD;
    }

    @Override
    public Field<String> field7() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.TRACEID;
    }

    @Override
    public Field<String> field8() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.SPANID;
    }

    @Override
    public Field<String> field9() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.PARENTSPANID;
    }

    @Override
    public Field<String> field10() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.KIND;
    }

    @Override
    public Field<Long> field11() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.COSTTIMEMS;
    }

    @Override
    public Field<Long> field12() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.STARTTIMEUS;
    }

    @Override
    public Field<Long> field13() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.ENDTIMEUS;
    }

    @Override
    public Field<String> field14() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.TAGS;
    }

    @Override
    public Field<String> field15() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.ATTRIBUTES;
    }

    @Override
    public Field<String> field16() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.NORMALIZEDURL;
    }

    @Override
    public Field<String> field17() {
        return BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY.STATUS;
    }

    @Override
    public LocalDateTime component1() {
        return getTimestamp();
    }

    @Override
    public String component2() {
        return getAppname();
    }

    @Override
    public String component3() {
        return getInstancename();
    }

    @Override
    public String component4() {
        return getName();
    }

    @Override
    public String component5() {
        return getClazz();
    }

    @Override
    public String component6() {
        return getMethod();
    }

    @Override
    public String component7() {
        return getTraceid();
    }

    @Override
    public String component8() {
        return getSpanid();
    }

    @Override
    public String component9() {
        return getParentspanid();
    }

    @Override
    public String component10() {
        return getKind();
    }

    @Override
    public Long component11() {
        return getCosttimems();
    }

    @Override
    public Long component12() {
        return getStarttimeus();
    }

    @Override
    public Long component13() {
        return getEndtimeus();
    }

    @Override
    public String component14() {
        return getTags();
    }

    @Override
    public String component15() {
        return getAttributes();
    }

    @Override
    public String component16() {
        return getNormalizedurl();
    }

    @Override
    public String component17() {
        return getStatus();
    }

    @Override
    public LocalDateTime value1() {
        return getTimestamp();
    }

    @Override
    public String value2() {
        return getAppname();
    }

    @Override
    public String value3() {
        return getInstancename();
    }

    @Override
    public String value4() {
        return getName();
    }

    @Override
    public String value5() {
        return getClazz();
    }

    @Override
    public String value6() {
        return getMethod();
    }

    @Override
    public String value7() {
        return getTraceid();
    }

    @Override
    public String value8() {
        return getSpanid();
    }

    @Override
    public String value9() {
        return getParentspanid();
    }

    @Override
    public String value10() {
        return getKind();
    }

    @Override
    public Long value11() {
        return getCosttimems();
    }

    @Override
    public Long value12() {
        return getStarttimeus();
    }

    @Override
    public Long value13() {
        return getEndtimeus();
    }

    @Override
    public String value14() {
        return getTags();
    }

    @Override
    public String value15() {
        return getAttributes();
    }

    @Override
    public String value16() {
        return getNormalizedurl();
    }

    @Override
    public String value17() {
        return getStatus();
    }

    @Override
    public BithonTraceSpanSummaryRecord value1(LocalDateTime value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value2(String value) {
        setAppname(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value3(String value) {
        setInstancename(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value4(String value) {
        setName(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value5(String value) {
        setClazz(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value6(String value) {
        setMethod(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value7(String value) {
        setTraceid(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value8(String value) {
        setSpanid(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value9(String value) {
        setParentspanid(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value10(String value) {
        setKind(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value11(Long value) {
        setCosttimems(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value12(Long value) {
        setStarttimeus(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value13(Long value) {
        setEndtimeus(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value14(String value) {
        setTags(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value15(String value) {
        setAttributes(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value16(String value) {
        setNormalizedurl(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord value17(String value) {
        setStatus(value);
        return this;
    }

    @Override
    public BithonTraceSpanSummaryRecord values(LocalDateTime value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, Long value11, Long value12, Long value13, String value14, String value15, String value16, String value17) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        value11(value11);
        value12(value12);
        value13(value13);
        value14(value14);
        value15(value15);
        value16(value16);
        value17(value17);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BithonTraceSpanSummaryRecord
     */
    public BithonTraceSpanSummaryRecord() {
        super(BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY);
    }

    /**
     * Create a detached, initialised BithonTraceSpanSummaryRecord
     */
    public BithonTraceSpanSummaryRecord(LocalDateTime timestamp, String appname, String instancename, String name, String clazz, String method, String traceid, String spanid, String parentspanid, String kind, Long costtimems, Long starttimeus, Long endtimeus, String tags, String attributes, String normalizedurl, String status) {
        super(BithonTraceSpanSummary.BITHON_TRACE_SPAN_SUMMARY);

        setTimestamp(timestamp);
        setAppname(appname);
        setInstancename(instancename);
        setName(name);
        setClazz(clazz);
        setMethod(method);
        setTraceid(traceid);
        setSpanid(spanid);
        setParentspanid(parentspanid);
        setKind(kind);
        setCosttimems(costtimems);
        setStarttimeus(starttimeus);
        setEndtimeus(endtimeus);
        setTags(tags);
        setAttributes(attributes);
        setNormalizedurl(normalizedurl);
        setStatus(status);
    }
}

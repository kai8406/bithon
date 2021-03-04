/*
 * This file is generated by jOOQ.
 */
package com.sbss.bithon.component.db.jooq.tables.records;


import com.sbss.bithon.component.db.jooq.tables.BithonTraceSpan;

import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record13;
import org.jooq.Row13;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonTraceSpanRecord extends UpdatableRecordImpl<BithonTraceSpanRecord> implements Record13<Long, String, String, String, String, String, String, String, String, String, Long, String, Timestamp> {

    private static final long serialVersionUID = 1612999961;

    /**
     * Setter for <code>bithon_trace_span.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_trace_span.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>bithon_trace_span.app_name</code>.
     */
    public void setAppName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_trace_span.app_name</code>.
     */
    public String getAppName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_trace_span.instance_name</code>.
     */
    public void setInstanceName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_trace_span.instance_name</code>.
     */
    public String getInstanceName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bithon_trace_span.name</code>.
     */
    public void setName(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_trace_span.name</code>.
     */
    public String getName() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bithon_trace_span.clazz</code>.
     */
    public void setClazz(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bithon_trace_span.clazz</code>.
     */
    public String getClazz() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bithon_trace_span.method</code>.
     */
    public void setMethod(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>bithon_trace_span.method</code>.
     */
    public String getMethod() {
        return (String) get(5);
    }

    /**
     * Setter for <code>bithon_trace_span.traceId</code>.
     */
    public void setTraceid(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>bithon_trace_span.traceId</code>.
     */
    public String getTraceid() {
        return (String) get(6);
    }

    /**
     * Setter for <code>bithon_trace_span.spanId</code>.
     */
    public void setSpanid(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>bithon_trace_span.spanId</code>.
     */
    public String getSpanid() {
        return (String) get(7);
    }

    /**
     * Setter for <code>bithon_trace_span.parentSpanId</code>.
     */
    public void setParentspanid(String value) {
        set(8, value);
    }

    /**
     * Getter for <code>bithon_trace_span.parentSpanId</code>.
     */
    public String getParentspanid() {
        return (String) get(8);
    }

    /**
     * Setter for <code>bithon_trace_span.kind</code>.
     */
    public void setKind(String value) {
        set(9, value);
    }

    /**
     * Getter for <code>bithon_trace_span.kind</code>.
     */
    public String getKind() {
        return (String) get(9);
    }

    /**
     * Setter for <code>bithon_trace_span.costTime</code>.
     */
    public void setCosttime(Long value) {
        set(10, value);
    }

    /**
     * Getter for <code>bithon_trace_span.costTime</code>.
     */
    public Long getCosttime() {
        return (Long) get(10);
    }

    /**
     * Setter for <code>bithon_trace_span.tags</code>.
     */
    public void setTags(String value) {
        set(11, value);
    }

    /**
     * Getter for <code>bithon_trace_span.tags</code>.
     */
    public String getTags() {
        return (String) get(11);
    }

    /**
     * Setter for <code>bithon_trace_span.timestamp</code>. Milli Seconds
     */
    public void setTimestamp(Timestamp value) {
        set(12, value);
    }

    /**
     * Getter for <code>bithon_trace_span.timestamp</code>. Milli Seconds
     */
    public Timestamp getTimestamp() {
        return (Timestamp) get(12);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record13 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row13<Long, String, String, String, String, String, String, String, String, String, Long, String, Timestamp> fieldsRow() {
        return (Row13) super.fieldsRow();
    }

    @Override
    public Row13<Long, String, String, String, String, String, String, String, String, String, Long, String, Timestamp> valuesRow() {
        return (Row13) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.ID;
    }

    @Override
    public Field<String> field2() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.APP_NAME;
    }

    @Override
    public Field<String> field3() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.INSTANCE_NAME;
    }

    @Override
    public Field<String> field4() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.NAME;
    }

    @Override
    public Field<String> field5() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.CLAZZ;
    }

    @Override
    public Field<String> field6() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.METHOD;
    }

    @Override
    public Field<String> field7() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.TRACEID;
    }

    @Override
    public Field<String> field8() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.SPANID;
    }

    @Override
    public Field<String> field9() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.PARENTSPANID;
    }

    @Override
    public Field<String> field10() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.KIND;
    }

    @Override
    public Field<Long> field11() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.COSTTIME;
    }

    @Override
    public Field<String> field12() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.TAGS;
    }

    @Override
    public Field<Timestamp> field13() {
        return BithonTraceSpan.BITHON_TRACE_SPAN.TIMESTAMP;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getAppName();
    }

    @Override
    public String component3() {
        return getInstanceName();
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
        return getCosttime();
    }

    @Override
    public String component12() {
        return getTags();
    }

    @Override
    public Timestamp component13() {
        return getTimestamp();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getAppName();
    }

    @Override
    public String value3() {
        return getInstanceName();
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
        return getCosttime();
    }

    @Override
    public String value12() {
        return getTags();
    }

    @Override
    public Timestamp value13() {
        return getTimestamp();
    }

    @Override
    public BithonTraceSpanRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value2(String value) {
        setAppName(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value3(String value) {
        setInstanceName(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value4(String value) {
        setName(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value5(String value) {
        setClazz(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value6(String value) {
        setMethod(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value7(String value) {
        setTraceid(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value8(String value) {
        setSpanid(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value9(String value) {
        setParentspanid(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value10(String value) {
        setKind(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value11(Long value) {
        setCosttime(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value12(String value) {
        setTags(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord value13(Timestamp value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public BithonTraceSpanRecord values(Long value1, String value2, String value3, String value4, String value5, String value6, String value7, String value8, String value9, String value10, Long value11, String value12, Timestamp value13) {
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
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BithonTraceSpanRecord
     */
    public BithonTraceSpanRecord() {
        super(BithonTraceSpan.BITHON_TRACE_SPAN);
    }

    /**
     * Create a detached, initialised BithonTraceSpanRecord
     */
    public BithonTraceSpanRecord(Long id, String appName, String instanceName, String name, String clazz, String method, String traceid, String spanid, String parentspanid, String kind, Long costtime, String tags, Timestamp timestamp) {
        super(BithonTraceSpan.BITHON_TRACE_SPAN);

        set(0, id);
        set(1, appName);
        set(2, instanceName);
        set(3, name);
        set(4, clazz);
        set(5, method);
        set(6, traceid);
        set(7, spanid);
        set(8, parentspanid);
        set(9, kind);
        set(10, costtime);
        set(11, tags);
        set(12, timestamp);
    }
}

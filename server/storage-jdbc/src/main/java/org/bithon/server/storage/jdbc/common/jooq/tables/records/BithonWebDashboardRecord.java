/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables.records;


import java.time.LocalDateTime;

import org.bithon.server.storage.jdbc.common.jooq.tables.BithonWebDashboard;
import org.jooq.Field;
import org.jooq.Record5;
import org.jooq.Row5;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BithonWebDashboardRecord extends TableRecordImpl<BithonWebDashboardRecord> implements Record5<LocalDateTime, String, String, String, Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>bithon_web_dashboard.timestamp</code>. Created Timestamp
     */
    public void setTimestamp(LocalDateTime value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_web_dashboard.timestamp</code>. Created Timestamp
     */
    public LocalDateTime getTimestamp() {
        return (LocalDateTime) get(0);
    }

    /**
     * Setter for <code>bithon_web_dashboard.name</code>. Name
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_web_dashboard.name</code>. Name
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_web_dashboard.payload</code>. Schema in JSON
     */
    public void setPayload(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_web_dashboard.payload</code>. Schema in JSON
     */
    public String getPayload() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bithon_web_dashboard.signature</code>. Signature of
     * payload field, currently SHA256 is applied
     */
    public void setSignature(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_web_dashboard.signature</code>. Signature of
     * payload field, currently SHA256 is applied
     */
    public String getSignature() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bithon_web_dashboard.deleted</code>.
     */
    public void setDeleted(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>bithon_web_dashboard.deleted</code>.
     */
    public Integer getDeleted() {
        return (Integer) get(4);
    }

    // -------------------------------------------------------------------------
    // Record5 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row5<LocalDateTime, String, String, String, Integer> fieldsRow() {
        return (Row5) super.fieldsRow();
    }

    @Override
    public Row5<LocalDateTime, String, String, String, Integer> valuesRow() {
        return (Row5) super.valuesRow();
    }

    @Override
    public Field<LocalDateTime> field1() {
        return BithonWebDashboard.BITHON_WEB_DASHBOARD.TIMESTAMP;
    }

    @Override
    public Field<String> field2() {
        return BithonWebDashboard.BITHON_WEB_DASHBOARD.NAME;
    }

    @Override
    public Field<String> field3() {
        return BithonWebDashboard.BITHON_WEB_DASHBOARD.PAYLOAD;
    }

    @Override
    public Field<String> field4() {
        return BithonWebDashboard.BITHON_WEB_DASHBOARD.SIGNATURE;
    }

    @Override
    public Field<Integer> field5() {
        return BithonWebDashboard.BITHON_WEB_DASHBOARD.DELETED;
    }

    @Override
    public LocalDateTime component1() {
        return getTimestamp();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getPayload();
    }

    @Override
    public String component4() {
        return getSignature();
    }

    @Override
    public Integer component5() {
        return getDeleted();
    }

    @Override
    public LocalDateTime value1() {
        return getTimestamp();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getPayload();
    }

    @Override
    public String value4() {
        return getSignature();
    }

    @Override
    public Integer value5() {
        return getDeleted();
    }

    @Override
    public BithonWebDashboardRecord value1(LocalDateTime value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public BithonWebDashboardRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public BithonWebDashboardRecord value3(String value) {
        setPayload(value);
        return this;
    }

    @Override
    public BithonWebDashboardRecord value4(String value) {
        setSignature(value);
        return this;
    }

    @Override
    public BithonWebDashboardRecord value5(Integer value) {
        setDeleted(value);
        return this;
    }

    @Override
    public BithonWebDashboardRecord values(LocalDateTime value1, String value2, String value3, String value4, Integer value5) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BithonWebDashboardRecord
     */
    public BithonWebDashboardRecord() {
        super(BithonWebDashboard.BITHON_WEB_DASHBOARD);
    }

    /**
     * Create a detached, initialised BithonWebDashboardRecord
     */
    public BithonWebDashboardRecord(LocalDateTime timestamp, String name, String payload, String signature, Integer deleted) {
        super(BithonWebDashboard.BITHON_WEB_DASHBOARD);

        setTimestamp(timestamp);
        setName(name);
        setPayload(payload);
        setSignature(signature);
        setDeleted(deleted);
    }
}

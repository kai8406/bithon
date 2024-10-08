/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables.records;


import java.time.LocalDateTime;

import org.bithon.server.storage.jdbc.common.jooq.tables.BithonAlertChangeLog;
import org.jooq.Field;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.TableRecordImpl;


/**
 * Change logs of alert
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonAlertChangeLogRecord extends TableRecordImpl<BithonAlertChangeLogRecord> implements Record6<String, String, String, String, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>bithon_alert_change_log.alert_id</code>. ID of Alert
     * Object
     */
    public void setAlertId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_alert_change_log.alert_id</code>. ID of Alert
     * Object
     */
    public String getAlertId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>bithon_alert_change_log.action</code>.
     */
    public void setAction(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_alert_change_log.action</code>.
     */
    public String getAction() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_alert_change_log.payload_before</code>. JSON
     * formatted
     */
    public void setPayloadBefore(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_alert_change_log.payload_before</code>. JSON
     * formatted
     */
    public String getPayloadBefore() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bithon_alert_change_log.payload_after</code>. JSON
     * formatted
     */
    public void setPayloadAfter(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_alert_change_log.payload_after</code>. JSON
     * formatted
     */
    public String getPayloadAfter() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bithon_alert_change_log.editor</code>.
     */
    public void setEditor(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bithon_alert_change_log.editor</code>.
     */
    public String getEditor() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bithon_alert_change_log.created_at</code>. Create
     * timestamp
     */
    public void setCreatedAt(LocalDateTime value) {
        set(5, value);
    }

    /**
     * Getter for <code>bithon_alert_change_log.created_at</code>. Create
     * timestamp
     */
    public LocalDateTime getCreatedAt() {
        return (LocalDateTime) get(5);
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, String, String, String, LocalDateTime> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<String, String, String, String, String, LocalDateTime> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return BithonAlertChangeLog.BITHON_ALERT_CHANGE_LOG.ALERT_ID;
    }

    @Override
    public Field<String> field2() {
        return BithonAlertChangeLog.BITHON_ALERT_CHANGE_LOG.ACTION;
    }

    @Override
    public Field<String> field3() {
        return BithonAlertChangeLog.BITHON_ALERT_CHANGE_LOG.PAYLOAD_BEFORE;
    }

    @Override
    public Field<String> field4() {
        return BithonAlertChangeLog.BITHON_ALERT_CHANGE_LOG.PAYLOAD_AFTER;
    }

    @Override
    public Field<String> field5() {
        return BithonAlertChangeLog.BITHON_ALERT_CHANGE_LOG.EDITOR;
    }

    @Override
    public Field<LocalDateTime> field6() {
        return BithonAlertChangeLog.BITHON_ALERT_CHANGE_LOG.CREATED_AT;
    }

    @Override
    public String component1() {
        return getAlertId();
    }

    @Override
    public String component2() {
        return getAction();
    }

    @Override
    public String component3() {
        return getPayloadBefore();
    }

    @Override
    public String component4() {
        return getPayloadAfter();
    }

    @Override
    public String component5() {
        return getEditor();
    }

    @Override
    public LocalDateTime component6() {
        return getCreatedAt();
    }

    @Override
    public String value1() {
        return getAlertId();
    }

    @Override
    public String value2() {
        return getAction();
    }

    @Override
    public String value3() {
        return getPayloadBefore();
    }

    @Override
    public String value4() {
        return getPayloadAfter();
    }

    @Override
    public String value5() {
        return getEditor();
    }

    @Override
    public LocalDateTime value6() {
        return getCreatedAt();
    }

    @Override
    public BithonAlertChangeLogRecord value1(String value) {
        setAlertId(value);
        return this;
    }

    @Override
    public BithonAlertChangeLogRecord value2(String value) {
        setAction(value);
        return this;
    }

    @Override
    public BithonAlertChangeLogRecord value3(String value) {
        setPayloadBefore(value);
        return this;
    }

    @Override
    public BithonAlertChangeLogRecord value4(String value) {
        setPayloadAfter(value);
        return this;
    }

    @Override
    public BithonAlertChangeLogRecord value5(String value) {
        setEditor(value);
        return this;
    }

    @Override
    public BithonAlertChangeLogRecord value6(LocalDateTime value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public BithonAlertChangeLogRecord values(String value1, String value2, String value3, String value4, String value5, LocalDateTime value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BithonAlertChangeLogRecord
     */
    public BithonAlertChangeLogRecord() {
        super(BithonAlertChangeLog.BITHON_ALERT_CHANGE_LOG);
    }

    /**
     * Create a detached, initialised BithonAlertChangeLogRecord
     */
    public BithonAlertChangeLogRecord(String alertId, String action, String payloadBefore, String payloadAfter, String editor, LocalDateTime createdAt) {
        super(BithonAlertChangeLog.BITHON_ALERT_CHANGE_LOG);

        setAlertId(alertId);
        setAction(action);
        setPayloadBefore(payloadBefore);
        setPayloadAfter(payloadAfter);
        setEditor(editor);
        setCreatedAt(createdAt);
    }
}

/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables.records;


import java.time.LocalDateTime;

import org.bithon.server.storage.jdbc.common.jooq.tables.BithonAlertEvaluationLog;
import org.jooq.Field;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.TableRecordImpl;


/**
 * Evaluation logs of alert
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonAlertEvaluationLogRecord extends TableRecordImpl<BithonAlertEvaluationLogRecord> implements Record6<LocalDateTime, String, Long, String, String, String> {

    private static final long serialVersionUID = -1531642153;

    /**
     * Setter for <code>bithon_alert_evaluation_log.timestamp</code>.
     */
    public void setTimestamp(LocalDateTime value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_alert_evaluation_log.timestamp</code>.
     */
    public LocalDateTime getTimestamp() {
        return (LocalDateTime) get(0);
    }

    /**
     * Setter for <code>bithon_alert_evaluation_log.alert_id</code>. Alert ID
     */
    public void setAlertId(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_alert_evaluation_log.alert_id</code>. Alert ID
     */
    public String getAlertId() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_alert_evaluation_log.sequence</code>. Used for ordering
     */
    public void setSequence(Long value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_alert_evaluation_log.sequence</code>. Used for ordering
     */
    public Long getSequence() {
        return (Long) get(2);
    }

    /**
     * Setter for <code>bithon_alert_evaluation_log.instance</code>. The instance that runs the evaluation
     */
    public void setInstance(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_alert_evaluation_log.instance</code>. The instance that runs the evaluation
     */
    public String getInstance() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bithon_alert_evaluation_log.clazz</code>. Logger Class
     */
    public void setClazz(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bithon_alert_evaluation_log.clazz</code>. Logger Class
     */
    public String getClazz() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bithon_alert_evaluation_log.message</code>.
     */
    public void setMessage(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>bithon_alert_evaluation_log.message</code>.
     */
    public String getMessage() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<LocalDateTime, String, Long, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<LocalDateTime, String, Long, String, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<LocalDateTime> field1() {
        return BithonAlertEvaluationLog.BITHON_ALERT_EVALUATION_LOG.TIMESTAMP;
    }

    @Override
    public Field<String> field2() {
        return BithonAlertEvaluationLog.BITHON_ALERT_EVALUATION_LOG.ALERT_ID;
    }

    @Override
    public Field<Long> field3() {
        return BithonAlertEvaluationLog.BITHON_ALERT_EVALUATION_LOG.SEQUENCE;
    }

    @Override
    public Field<String> field4() {
        return BithonAlertEvaluationLog.BITHON_ALERT_EVALUATION_LOG.INSTANCE;
    }

    @Override
    public Field<String> field5() {
        return BithonAlertEvaluationLog.BITHON_ALERT_EVALUATION_LOG.CLAZZ;
    }

    @Override
    public Field<String> field6() {
        return BithonAlertEvaluationLog.BITHON_ALERT_EVALUATION_LOG.MESSAGE;
    }

    @Override
    public LocalDateTime component1() {
        return getTimestamp();
    }

    @Override
    public String component2() {
        return getAlertId();
    }

    @Override
    public Long component3() {
        return getSequence();
    }

    @Override
    public String component4() {
        return getInstance();
    }

    @Override
    public String component5() {
        return getClazz();
    }

    @Override
    public String component6() {
        return getMessage();
    }

    @Override
    public LocalDateTime value1() {
        return getTimestamp();
    }

    @Override
    public String value2() {
        return getAlertId();
    }

    @Override
    public Long value3() {
        return getSequence();
    }

    @Override
    public String value4() {
        return getInstance();
    }

    @Override
    public String value5() {
        return getClazz();
    }

    @Override
    public String value6() {
        return getMessage();
    }

    @Override
    public BithonAlertEvaluationLogRecord value1(LocalDateTime value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public BithonAlertEvaluationLogRecord value2(String value) {
        setAlertId(value);
        return this;
    }

    @Override
    public BithonAlertEvaluationLogRecord value3(Long value) {
        setSequence(value);
        return this;
    }

    @Override
    public BithonAlertEvaluationLogRecord value4(String value) {
        setInstance(value);
        return this;
    }

    @Override
    public BithonAlertEvaluationLogRecord value5(String value) {
        setClazz(value);
        return this;
    }

    @Override
    public BithonAlertEvaluationLogRecord value6(String value) {
        setMessage(value);
        return this;
    }

    @Override
    public BithonAlertEvaluationLogRecord values(LocalDateTime value1, String value2, Long value3, String value4, String value5, String value6) {
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
     * Create a detached BithonAlertEvaluationLogRecord
     */
    public BithonAlertEvaluationLogRecord() {
        super(BithonAlertEvaluationLog.BITHON_ALERT_EVALUATION_LOG);
    }

    /**
     * Create a detached, initialised BithonAlertEvaluationLogRecord
     */
    public BithonAlertEvaluationLogRecord(LocalDateTime timestamp, String alertId, Long sequence, String instance, String clazz, String message) {
        super(BithonAlertEvaluationLog.BITHON_ALERT_EVALUATION_LOG);

        set(0, timestamp);
        set(1, alertId);
        set(2, sequence);
        set(3, instance);
        set(4, clazz);
        set(5, message);
    }
}

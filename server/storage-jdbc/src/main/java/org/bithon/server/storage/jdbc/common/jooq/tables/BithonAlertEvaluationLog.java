/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.bithon.server.storage.jdbc.common.jooq.DefaultSchema;
import org.bithon.server.storage.jdbc.common.jooq.Indexes;
import org.bithon.server.storage.jdbc.common.jooq.tables.records.BithonAlertEvaluationLogRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row5;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * Running logs of alert
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonAlertEvaluationLog extends TableImpl<BithonAlertEvaluationLogRecord> {

    private static final long serialVersionUID = -496868789;

    /**
     * The reference instance of <code>bithon_alert_evaluation_log</code>
     */
    public static final BithonAlertEvaluationLog BITHON_ALERT_EVALUATION_LOG = new BithonAlertEvaluationLog();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BithonAlertEvaluationLogRecord> getRecordType() {
        return BithonAlertEvaluationLogRecord.class;
    }

    /**
     * The column <code>bithon_alert_evaluation_log.timestamp</code>.
     */
    public final TableField<BithonAlertEvaluationLogRecord, LocalDateTime> TIMESTAMP = createField(DSL.name("timestamp"), org.jooq.impl.SQLDataType.LOCALDATETIME.nullable(false), this, "");

    /**
     * The column <code>bithon_alert_evaluation_log.alert_id</code>. Alert ID
     */
    public final TableField<BithonAlertEvaluationLogRecord, String> ALERT_ID = createField(DSL.name("alert_id"), org.jooq.impl.SQLDataType.VARCHAR(32).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "Alert ID");

    /**
     * The column <code>bithon_alert_evaluation_log.sequence</code>. Used for ordering
     */
    public final TableField<BithonAlertEvaluationLogRecord, Long> SEQUENCE = createField(DSL.name("sequence"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "Used for ordering");

    /**
     * The column <code>bithon_alert_evaluation_log.clazz</code>. Logger Class
     */
    public final TableField<BithonAlertEvaluationLogRecord, String> CLAZZ = createField(DSL.name("clazz"), org.jooq.impl.SQLDataType.VARCHAR(128).nullable(false).defaultValue(org.jooq.impl.DSL.inline("", org.jooq.impl.SQLDataType.VARCHAR)), this, "Logger Class");

    /**
     * The column <code>bithon_alert_evaluation_log.message</code>.
     */
    public final TableField<BithonAlertEvaluationLogRecord, String> MESSAGE = createField(DSL.name("message"), org.jooq.impl.SQLDataType.CLOB, this, "");

    /**
     * Create a <code>bithon_alert_evaluation_log</code> table reference
     */
    public BithonAlertEvaluationLog() {
        this(DSL.name("bithon_alert_evaluation_log"), null);
    }

    /**
     * Create an aliased <code>bithon_alert_evaluation_log</code> table reference
     */
    public BithonAlertEvaluationLog(String alias) {
        this(DSL.name(alias), BITHON_ALERT_EVALUATION_LOG);
    }

    /**
     * Create an aliased <code>bithon_alert_evaluation_log</code> table reference
     */
    public BithonAlertEvaluationLog(Name alias) {
        this(alias, BITHON_ALERT_EVALUATION_LOG);
    }

    private BithonAlertEvaluationLog(Name alias, Table<BithonAlertEvaluationLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private BithonAlertEvaluationLog(Name alias, Table<BithonAlertEvaluationLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Running logs of alert"));
    }

    public <O extends Record> BithonAlertEvaluationLog(Table<O> child, ForeignKey<O, BithonAlertEvaluationLogRecord> key) {
        super(child, key, BITHON_ALERT_EVALUATION_LOG);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.BITHON_ALERT_EVALUATION_LOG_BITHON_ALERT_EVALUATION_LOG_TIMESTAMP, Indexes.BITHON_ALERT_EVALUATION_LOG_BITHON_ALERT_EVALUATION_LOG_TIMESTAMP_ID);
    }

    @Override
    public BithonAlertEvaluationLog as(String alias) {
        return new BithonAlertEvaluationLog(DSL.name(alias), this);
    }

    @Override
    public BithonAlertEvaluationLog as(Name alias) {
        return new BithonAlertEvaluationLog(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonAlertEvaluationLog rename(String name) {
        return new BithonAlertEvaluationLog(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonAlertEvaluationLog rename(Name name) {
        return new BithonAlertEvaluationLog(name, null);
    }

    // -------------------------------------------------------------------------
    // Row5 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row5<LocalDateTime, String, Long, String, String> fieldsRow() {
        return (Row5) super.fieldsRow();
    }
}

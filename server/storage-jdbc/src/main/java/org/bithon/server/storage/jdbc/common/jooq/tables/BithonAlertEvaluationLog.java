/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.bithon.server.storage.jdbc.common.jooq.DefaultSchema;
import org.bithon.server.storage.jdbc.common.jooq.Indexes;
import org.bithon.server.storage.jdbc.common.jooq.tables.records.BithonAlertEvaluationLogRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function7;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * Evaluation logs of alert
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonAlertEvaluationLog extends TableImpl<BithonAlertEvaluationLogRecord> {

    private static final long serialVersionUID = 1L;

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
    public final TableField<BithonAlertEvaluationLogRecord, LocalDateTime> TIMESTAMP = createField(DSL.name("timestamp"), SQLDataType.LOCALDATETIME(3).nullable(false), this, "");

    /**
     * The column <code>bithon_alert_evaluation_log.alert_id</code>. Alert ID
     */
    public final TableField<BithonAlertEvaluationLogRecord, String> ALERT_ID = createField(DSL.name("alert_id"), SQLDataType.VARCHAR(32).nullable(false).defaultValue(DSL.inline("", SQLDataType.VARCHAR)), this, "Alert ID");

    /**
     * The column <code>bithon_alert_evaluation_log.sequence</code>. Used for
     * ordering
     */
    public final TableField<BithonAlertEvaluationLogRecord, Long> SEQUENCE = createField(DSL.name("sequence"), SQLDataType.BIGINT.nullable(false).defaultValue(DSL.inline("0", SQLDataType.BIGINT)), this, "Used for ordering");

    /**
     * The column <code>bithon_alert_evaluation_log.instance</code>. The
     * instance that runs the evaluation
     */
    public final TableField<BithonAlertEvaluationLogRecord, String> INSTANCE = createField(DSL.name("instance"), SQLDataType.VARCHAR(32).nullable(false), this, "The instance that runs the evaluation");

    /**
     * The column <code>bithon_alert_evaluation_log.level</code>. Logger Level:
     * INFO, WARN, ERROR
     */
    public final TableField<BithonAlertEvaluationLogRecord, String> LEVEL = createField(DSL.name("level"), SQLDataType.VARCHAR(16).nullable(false).defaultValue(DSL.inline("", SQLDataType.VARCHAR)), this, "Logger Level: INFO, WARN, ERROR");

    /**
     * The column <code>bithon_alert_evaluation_log.clazz</code>. Logger Class
     */
    public final TableField<BithonAlertEvaluationLogRecord, String> CLAZZ = createField(DSL.name("clazz"), SQLDataType.VARCHAR(128).nullable(false).defaultValue(DSL.inline("", SQLDataType.VARCHAR)), this, "Logger Class");

    /**
     * The column <code>bithon_alert_evaluation_log.message</code>.
     */
    public final TableField<BithonAlertEvaluationLogRecord, String> MESSAGE = createField(DSL.name("message"), SQLDataType.CLOB, this, "");

    private BithonAlertEvaluationLog(Name alias, Table<BithonAlertEvaluationLogRecord> aliased) {
        this(alias, aliased, null);
    }

    private BithonAlertEvaluationLog(Name alias, Table<BithonAlertEvaluationLogRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Evaluation logs of alert"), TableOptions.table());
    }

    /**
     * Create an aliased <code>bithon_alert_evaluation_log</code> table
     * reference
     */
    public BithonAlertEvaluationLog(String alias) {
        this(DSL.name(alias), BITHON_ALERT_EVALUATION_LOG);
    }

    /**
     * Create an aliased <code>bithon_alert_evaluation_log</code> table
     * reference
     */
    public BithonAlertEvaluationLog(Name alias) {
        this(alias, BITHON_ALERT_EVALUATION_LOG);
    }

    /**
     * Create a <code>bithon_alert_evaluation_log</code> table reference
     */
    public BithonAlertEvaluationLog() {
        this(DSL.name("bithon_alert_evaluation_log"), null);
    }

    public <O extends Record> BithonAlertEvaluationLog(Table<O> child, ForeignKey<O, BithonAlertEvaluationLogRecord> key) {
        super(child, key, BITHON_ALERT_EVALUATION_LOG);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.asList(Indexes.BITHON_ALERT_EVALUATION_LOG_BITHON_ALERT_EVALUATION_LOG_TIMESTAMP, Indexes.BITHON_ALERT_EVALUATION_LOG_BITHON_ALERT_EVALUATION_LOG_TIMESTAMP_ID);
    }

    @Override
    public BithonAlertEvaluationLog as(String alias) {
        return new BithonAlertEvaluationLog(DSL.name(alias), this);
    }

    @Override
    public BithonAlertEvaluationLog as(Name alias) {
        return new BithonAlertEvaluationLog(alias, this);
    }

    @Override
    public BithonAlertEvaluationLog as(Table<?> alias) {
        return new BithonAlertEvaluationLog(alias.getQualifiedName(), this);
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

    /**
     * Rename this table
     */
    @Override
    public BithonAlertEvaluationLog rename(Table<?> name) {
        return new BithonAlertEvaluationLog(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<LocalDateTime, String, Long, String, String, String, String> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function7<? super LocalDateTime, ? super String, ? super Long, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function7<? super LocalDateTime, ? super String, ? super Long, ? super String, ? super String, ? super String, ? super String, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}

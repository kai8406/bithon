/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables;


import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import org.bithon.server.storage.jdbc.common.jooq.DefaultSchema;
import org.bithon.server.storage.jdbc.common.jooq.Keys;
import org.bithon.server.storage.jdbc.common.jooq.tables.records.BithonMetricsBaselineRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function3;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row3;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;


/**
 * This table keeps the date when the metrics will be kept for ever
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BithonMetricsBaseline extends TableImpl<BithonMetricsBaselineRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>bithon_metrics_baseline</code>
     */
    public static final BithonMetricsBaseline BITHON_METRICS_BASELINE = new BithonMetricsBaseline();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BithonMetricsBaselineRecord> getRecordType() {
        return BithonMetricsBaselineRecord.class;
    }

    /**
     * The column <code>bithon_metrics_baseline.date</code>. On which day the
     * metrics will be kept.In the format of yyyy-MM-dd
     */
    public final TableField<BithonMetricsBaselineRecord, String> DATE = createField(DSL.name("date"), SQLDataType.VARCHAR(10).nullable(false), this, "On which day the metrics will be kept.In the format of yyyy-MM-dd");

    /**
     * The column <code>bithon_metrics_baseline.keep_days</code>. How many days
     * the metrics will be kept. If 0, the metrics will be kept forever 
     */
    public final TableField<BithonMetricsBaselineRecord, Integer> KEEP_DAYS = createField(DSL.name("keep_days"), SQLDataType.INTEGER.nullable(false), this, "How many days the metrics will be kept. If 0, the metrics will be kept forever ");

    /**
     * The column <code>bithon_metrics_baseline.create_time</code>. Created
     * Timestamp
     */
    public final TableField<BithonMetricsBaselineRecord, LocalDateTime> CREATE_TIME = createField(DSL.name("create_time"), SQLDataType.LOCALDATETIME(3).nullable(false), this, "Created Timestamp");

    private BithonMetricsBaseline(Name alias, Table<BithonMetricsBaselineRecord> aliased) {
        this(alias, aliased, null);
    }

    private BithonMetricsBaseline(Name alias, Table<BithonMetricsBaselineRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("This table keeps the date when the metrics will be kept for ever"), TableOptions.table());
    }

    /**
     * Create an aliased <code>bithon_metrics_baseline</code> table reference
     */
    public BithonMetricsBaseline(String alias) {
        this(DSL.name(alias), BITHON_METRICS_BASELINE);
    }

    /**
     * Create an aliased <code>bithon_metrics_baseline</code> table reference
     */
    public BithonMetricsBaseline(Name alias) {
        this(alias, BITHON_METRICS_BASELINE);
    }

    /**
     * Create a <code>bithon_metrics_baseline</code> table reference
     */
    public BithonMetricsBaseline() {
        this(DSL.name("bithon_metrics_baseline"), null);
    }

    public <O extends Record> BithonMetricsBaseline(Table<O> child, ForeignKey<O, BithonMetricsBaselineRecord> key) {
        super(child, key, BITHON_METRICS_BASELINE);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<UniqueKey<BithonMetricsBaselineRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_BITHON_METRICS_BASELINE_BITHON_METRICS_BASELINE_DATE);
    }

    @Override
    public BithonMetricsBaseline as(String alias) {
        return new BithonMetricsBaseline(DSL.name(alias), this);
    }

    @Override
    public BithonMetricsBaseline as(Name alias) {
        return new BithonMetricsBaseline(alias, this);
    }

    @Override
    public BithonMetricsBaseline as(Table<?> alias) {
        return new BithonMetricsBaseline(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonMetricsBaseline rename(String name) {
        return new BithonMetricsBaseline(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonMetricsBaseline rename(Name name) {
        return new BithonMetricsBaseline(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonMetricsBaseline rename(Table<?> name) {
        return new BithonMetricsBaseline(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row3 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row3<String, Integer, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function3<? super String, ? super Integer, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function3<? super String, ? super Integer, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}

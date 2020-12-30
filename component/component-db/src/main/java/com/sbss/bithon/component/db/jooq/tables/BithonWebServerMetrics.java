/*
 * This file is generated by jOOQ.
 */
package com.sbss.bithon.component.db.jooq.tables;


import com.sbss.bithon.component.db.jooq.DefaultSchema;
import com.sbss.bithon.component.db.jooq.Indexes;
import com.sbss.bithon.component.db.jooq.Keys;
import com.sbss.bithon.component.db.jooq.tables.records.BithonWebServerMetricsRecord;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row7;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonWebServerMetrics extends TableImpl<BithonWebServerMetricsRecord> {

    private static final long serialVersionUID = 2072545985;

    /**
     * The reference instance of <code>bithon_web_server_metrics</code>
     */
    public static final BithonWebServerMetrics BITHON_WEB_SERVER_METRICS = new BithonWebServerMetrics();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BithonWebServerMetricsRecord> getRecordType() {
        return BithonWebServerMetricsRecord.class;
    }

    /**
     * The column <code>bithon_web_server_metrics.timestamp</code>.
     */
    public final TableField<BithonWebServerMetricsRecord, Timestamp> TIMESTAMP = createField(DSL.name("timestamp"), org.jooq.impl.SQLDataType.TIMESTAMP.nullable(false), this, "");

    /**
     * The column <code>bithon_web_server_metrics.appName</code>.
     */
    public final TableField<BithonWebServerMetricsRecord, String> APPNAME = createField(DSL.name("appName"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>bithon_web_server_metrics.instanceName</code>.
     */
    public final TableField<BithonWebServerMetricsRecord, String> INSTANCENAME = createField(DSL.name("instanceName"), org.jooq.impl.SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>bithon_web_server_metrics.connectionCount</code>.
     */
    public final TableField<BithonWebServerMetricsRecord, Long> CONNECTIONCOUNT = createField(DSL.name("connectionCount"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>bithon_web_server_metrics.maxConnections</code>.
     */
    public final TableField<BithonWebServerMetricsRecord, Long> MAXCONNECTIONS = createField(DSL.name("maxConnections"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>bithon_web_server_metrics.activeThreads</code>.
     */
    public final TableField<BithonWebServerMetricsRecord, Long> ACTIVETHREADS = createField(DSL.name("activeThreads"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * The column <code>bithon_web_server_metrics.maxThreads</code>.
     */
    public final TableField<BithonWebServerMetricsRecord, Long> MAXTHREADS = createField(DSL.name("maxThreads"), org.jooq.impl.SQLDataType.BIGINT.nullable(false).defaultValue(org.jooq.impl.DSL.inline("0", org.jooq.impl.SQLDataType.BIGINT)), this, "");

    /**
     * Create a <code>bithon_web_server_metrics</code> table reference
     */
    public BithonWebServerMetrics() {
        this(DSL.name("bithon_web_server_metrics"), null);
    }

    /**
     * Create an aliased <code>bithon_web_server_metrics</code> table reference
     */
    public BithonWebServerMetrics(String alias) {
        this(DSL.name(alias), BITHON_WEB_SERVER_METRICS);
    }

    /**
     * Create an aliased <code>bithon_web_server_metrics</code> table reference
     */
    public BithonWebServerMetrics(Name alias) {
        this(alias, BITHON_WEB_SERVER_METRICS);
    }

    private BithonWebServerMetrics(Name alias, Table<BithonWebServerMetricsRecord> aliased) {
        this(alias, aliased, null);
    }

    private BithonWebServerMetrics(Name alias, Table<BithonWebServerMetricsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""));
    }

    public <O extends Record> BithonWebServerMetrics(Table<O> child, ForeignKey<O, BithonWebServerMetricsRecord> key) {
        super(child, key, BITHON_WEB_SERVER_METRICS);
    }

    @Override
    public Schema getSchema() {
        return DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.BITHON_WEB_SERVER_METRICS_IDX_KEY);
    }

    @Override
    public List<UniqueKey<BithonWebServerMetricsRecord>> getKeys() {
        return Arrays.<UniqueKey<BithonWebServerMetricsRecord>>asList(Keys.KEY_BITHON_WEB_SERVER_METRICS_IDX_KEY);
    }

    @Override
    public BithonWebServerMetrics as(String alias) {
        return new BithonWebServerMetrics(DSL.name(alias), this);
    }

    @Override
    public BithonWebServerMetrics as(Name alias) {
        return new BithonWebServerMetrics(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonWebServerMetrics rename(String name) {
        return new BithonWebServerMetrics(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonWebServerMetrics rename(Name name) {
        return new BithonWebServerMetrics(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<Timestamp, String, String, Long, Long, Long, Long> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}

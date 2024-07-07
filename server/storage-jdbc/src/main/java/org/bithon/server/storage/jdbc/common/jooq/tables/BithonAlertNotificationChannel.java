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
import org.bithon.server.storage.jdbc.common.jooq.tables.records.BithonAlertNotificationChannelRecord;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function4;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row4;
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
 * Alert Notification channels
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BithonAlertNotificationChannel extends TableImpl<BithonAlertNotificationChannelRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>bithon_alert_notification_channel</code>
     */
    public static final BithonAlertNotificationChannel BITHON_ALERT_NOTIFICATION_CHANNEL = new BithonAlertNotificationChannel();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<BithonAlertNotificationChannelRecord> getRecordType() {
        return BithonAlertNotificationChannelRecord.class;
    }

    /**
     * The column <code>bithon_alert_notification_channel.name</code>.
     */
    public final TableField<BithonAlertNotificationChannelRecord, String> NAME = createField(DSL.name("name"), SQLDataType.VARCHAR(64).nullable(false), this, "");

    /**
     * The column <code>bithon_alert_notification_channel.type</code>.
     */
    public final TableField<BithonAlertNotificationChannelRecord, String> TYPE = createField(DSL.name("type"), SQLDataType.VARCHAR(16).nullable(false), this, "");

    /**
     * The column <code>bithon_alert_notification_channel.payload</code>.
     * channel payload
     */
    public final TableField<BithonAlertNotificationChannelRecord, String> PAYLOAD = createField(DSL.name("payload"), SQLDataType.CLOB.nullable(false), this, "channel payload");

    /**
     * The column <code>bithon_alert_notification_channel.created_at</code>.
     * create time
     */
    public final TableField<BithonAlertNotificationChannelRecord, LocalDateTime> CREATED_AT = createField(DSL.name("created_at"), SQLDataType.LOCALDATETIME(3).nullable(false), this, "create time");

    private BithonAlertNotificationChannel(Name alias, Table<BithonAlertNotificationChannelRecord> aliased) {
        this(alias, aliased, null);
    }

    private BithonAlertNotificationChannel(Name alias, Table<BithonAlertNotificationChannelRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment("Alert Notification channels"), TableOptions.table());
    }

    /**
     * Create an aliased <code>bithon_alert_notification_channel</code> table
     * reference
     */
    public BithonAlertNotificationChannel(String alias) {
        this(DSL.name(alias), BITHON_ALERT_NOTIFICATION_CHANNEL);
    }

    /**
     * Create an aliased <code>bithon_alert_notification_channel</code> table
     * reference
     */
    public BithonAlertNotificationChannel(Name alias) {
        this(alias, BITHON_ALERT_NOTIFICATION_CHANNEL);
    }

    /**
     * Create a <code>bithon_alert_notification_channel</code> table reference
     */
    public BithonAlertNotificationChannel() {
        this(DSL.name("bithon_alert_notification_channel"), null);
    }

    public <O extends Record> BithonAlertNotificationChannel(Table<O> child, ForeignKey<O, BithonAlertNotificationChannelRecord> key) {
        super(child, key, BITHON_ALERT_NOTIFICATION_CHANNEL);
    }

    @Override
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    public List<UniqueKey<BithonAlertNotificationChannelRecord>> getUniqueKeys() {
        return Arrays.asList(Keys.KEY_BITHON_ALERT_NOTIFICATION_CHANNEL_ALERT_NOTIFICATION_CHANNEL_NAME);
    }

    @Override
    public BithonAlertNotificationChannel as(String alias) {
        return new BithonAlertNotificationChannel(DSL.name(alias), this);
    }

    @Override
    public BithonAlertNotificationChannel as(Name alias) {
        return new BithonAlertNotificationChannel(alias, this);
    }

    @Override
    public BithonAlertNotificationChannel as(Table<?> alias) {
        return new BithonAlertNotificationChannel(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonAlertNotificationChannel rename(String name) {
        return new BithonAlertNotificationChannel(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonAlertNotificationChannel rename(Name name) {
        return new BithonAlertNotificationChannel(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    public BithonAlertNotificationChannel rename(Table<?> name) {
        return new BithonAlertNotificationChannel(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<String, String, String, LocalDateTime> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function4<? super String, ? super String, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function4<? super String, ? super String, ? super String, ? super LocalDateTime, ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}

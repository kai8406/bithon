/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables.records;


import java.time.LocalDateTime;

import org.bithon.server.storage.jdbc.common.jooq.tables.BithonMetaSchema;
import org.jooq.Field;
import org.jooq.Record4;
import org.jooq.Row4;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes", "this-escape" })
public class BithonMetaSchemaRecord extends TableRecordImpl<BithonMetaSchemaRecord> implements Record4<LocalDateTime, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>bithon_meta_schema.timestamp</code>. Created Timestamp
     */
    public void setTimestamp(LocalDateTime value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_meta_schema.timestamp</code>. Created Timestamp
     */
    public LocalDateTime getTimestamp() {
        return (LocalDateTime) get(0);
    }

    /**
     * Setter for <code>bithon_meta_schema.name</code>. Schema Name
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_meta_schema.name</code>. Schema Name
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_meta_schema.schema</code>. Schema in JSON
     */
    public void setSchema(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_meta_schema.schema</code>. Schema in JSON
     */
    public String getSchema() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bithon_meta_schema.signature</code>. Signature of schema
     * field, currently SHA256 is applied
     */
    public void setSignature(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_meta_schema.signature</code>. Signature of schema
     * field, currently SHA256 is applied
     */
    public String getSignature() {
        return (String) get(3);
    }

    // -------------------------------------------------------------------------
    // Record4 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row4<LocalDateTime, String, String, String> fieldsRow() {
        return (Row4) super.fieldsRow();
    }

    @Override
    public Row4<LocalDateTime, String, String, String> valuesRow() {
        return (Row4) super.valuesRow();
    }

    @Override
    public Field<LocalDateTime> field1() {
        return BithonMetaSchema.BITHON_META_SCHEMA.TIMESTAMP;
    }

    @Override
    public Field<String> field2() {
        return BithonMetaSchema.BITHON_META_SCHEMA.NAME;
    }

    @Override
    public Field<String> field3() {
        return BithonMetaSchema.BITHON_META_SCHEMA.SCHEMA;
    }

    @Override
    public Field<String> field4() {
        return BithonMetaSchema.BITHON_META_SCHEMA.SIGNATURE;
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
        return getSchema();
    }

    @Override
    public String component4() {
        return getSignature();
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
        return getSchema();
    }

    @Override
    public String value4() {
        return getSignature();
    }

    @Override
    public BithonMetaSchemaRecord value1(LocalDateTime value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public BithonMetaSchemaRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public BithonMetaSchemaRecord value3(String value) {
        setSchema(value);
        return this;
    }

    @Override
    public BithonMetaSchemaRecord value4(String value) {
        setSignature(value);
        return this;
    }

    @Override
    public BithonMetaSchemaRecord values(LocalDateTime value1, String value2, String value3, String value4) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached BithonMetaSchemaRecord
     */
    public BithonMetaSchemaRecord() {
        super(BithonMetaSchema.BITHON_META_SCHEMA);
    }

    /**
     * Create a detached, initialised BithonMetaSchemaRecord
     */
    public BithonMetaSchemaRecord(LocalDateTime timestamp, String name, String schema, String signature) {
        super(BithonMetaSchema.BITHON_META_SCHEMA);

        setTimestamp(timestamp);
        setName(name);
        setSchema(schema);
        setSignature(signature);
    }
}

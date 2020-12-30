/*
 * This file is generated by jOOQ.
 */
package com.sbss.bithon.component.db.jooq.tables.records;


import com.sbss.bithon.component.db.jooq.tables.BithonMetadata;

import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 应用
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonMetadataRecord extends UpdatableRecordImpl<BithonMetadataRecord> implements Record6<Long, String, String, Long, Timestamp, Timestamp> {

    private static final long serialVersionUID = 1035324416;

    /**
     * Setter for <code>bithon_metadata.id</code>. 唯一编号
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_metadata.id</code>. 唯一编号
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>bithon_metadata.name</code>. 名称
     */
    public void setName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_metadata.name</code>. 名称
     */
    public String getName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_metadata.type</code>. 环境
     */
    public void setType(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_metadata.type</code>. 环境
     */
    public String getType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bithon_metadata.parent_id</code>. 父
     */
    public void setParentId(Long value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_metadata.parent_id</code>. 父
     */
    public Long getParentId() {
        return (Long) get(3);
    }

    /**
     * Setter for <code>bithon_metadata.created_at</code>. 创建时间
     */
    public void setCreatedAt(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>bithon_metadata.created_at</code>. 创建时间
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>bithon_metadata.updated_at</code>. 更新时间
     */
    public void setUpdatedAt(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>bithon_metadata.updated_at</code>. 更新时间
     */
    public Timestamp getUpdatedAt() {
        return (Timestamp) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<Long, String, String, Long, Timestamp, Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Long, String, String, Long, Timestamp, Timestamp> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return BithonMetadata.BITHON_METADATA.ID;
    }

    @Override
    public Field<String> field2() {
        return BithonMetadata.BITHON_METADATA.NAME;
    }

    @Override
    public Field<String> field3() {
        return BithonMetadata.BITHON_METADATA.TYPE;
    }

    @Override
    public Field<Long> field4() {
        return BithonMetadata.BITHON_METADATA.PARENT_ID;
    }

    @Override
    public Field<Timestamp> field5() {
        return BithonMetadata.BITHON_METADATA.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field6() {
        return BithonMetadata.BITHON_METADATA.UPDATED_AT;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getName();
    }

    @Override
    public String component3() {
        return getType();
    }

    @Override
    public Long component4() {
        return getParentId();
    }

    @Override
    public Timestamp component5() {
        return getCreatedAt();
    }

    @Override
    public Timestamp component6() {
        return getUpdatedAt();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getName();
    }

    @Override
    public String value3() {
        return getType();
    }

    @Override
    public Long value4() {
        return getParentId();
    }

    @Override
    public Timestamp value5() {
        return getCreatedAt();
    }

    @Override
    public Timestamp value6() {
        return getUpdatedAt();
    }

    @Override
    public BithonMetadataRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public BithonMetadataRecord value2(String value) {
        setName(value);
        return this;
    }

    @Override
    public BithonMetadataRecord value3(String value) {
        setType(value);
        return this;
    }

    @Override
    public BithonMetadataRecord value4(Long value) {
        setParentId(value);
        return this;
    }

    @Override
    public BithonMetadataRecord value5(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public BithonMetadataRecord value6(Timestamp value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public BithonMetadataRecord values(Long value1, String value2, String value3, Long value4, Timestamp value5, Timestamp value6) {
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
     * Create a detached BithonMetadataRecord
     */
    public BithonMetadataRecord() {
        super(BithonMetadata.BITHON_METADATA);
    }

    /**
     * Create a detached, initialised BithonMetadataRecord
     */
    public BithonMetadataRecord(Long id, String name, String type, Long parentId, Timestamp createdAt, Timestamp updatedAt) {
        super(BithonMetadata.BITHON_METADATA);

        set(0, id);
        set(1, name);
        set(2, type);
        set(3, parentId);
        set(4, createdAt);
        set(5, updatedAt);
    }
}

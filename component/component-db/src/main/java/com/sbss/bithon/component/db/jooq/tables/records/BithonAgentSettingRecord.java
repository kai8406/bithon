/*
 * This file is generated by jOOQ.
 */
package com.sbss.bithon.component.db.jooq.tables.records;


import com.sbss.bithon.component.db.jooq.tables.BithonAgentSetting;

import java.sql.Timestamp;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;


/**
 * 配置
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonAgentSettingRecord extends UpdatableRecordImpl<BithonAgentSettingRecord> implements Record6<Long, String, String, String, Timestamp, Timestamp> {

    private static final long serialVersionUID = -806698378;

    /**
     * Setter for <code>bithon_agent_setting.id</code>. 唯一编号
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.id</code>. 唯一编号
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>bithon_agent_setting.app_name</code>. 名称
     */
    public void setAppName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.app_name</code>. 名称
     */
    public String getAppName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_agent_setting.setting_name</code>. 配置名称
     */
    public void setSettingName(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.setting_name</code>. 配置名称
     */
    public String getSettingName() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bithon_agent_setting.setting</code>. 设置
     */
    public void setSetting(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.setting</code>. 设置
     */
    public String getSetting() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bithon_agent_setting.created_at</code>. 创建时间
     */
    public void setCreatedAt(Timestamp value) {
        set(4, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.created_at</code>. 创建时间
     */
    public Timestamp getCreatedAt() {
        return (Timestamp) get(4);
    }

    /**
     * Setter for <code>bithon_agent_setting.updated_at</code>. 更新时间
     */
    public void setUpdatedAt(Timestamp value) {
        set(5, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.updated_at</code>. 更新时间
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
    public Row6<Long, String, String, String, Timestamp, Timestamp> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<Long, String, String, String, Timestamp, Timestamp> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.ID;
    }

    @Override
    public Field<String> field2() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.APP_NAME;
    }

    @Override
    public Field<String> field3() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.SETTING_NAME;
    }

    @Override
    public Field<String> field4() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.SETTING;
    }

    @Override
    public Field<Timestamp> field5() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.CREATED_AT;
    }

    @Override
    public Field<Timestamp> field6() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.UPDATED_AT;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getAppName();
    }

    @Override
    public String component3() {
        return getSettingName();
    }

    @Override
    public String component4() {
        return getSetting();
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
        return getAppName();
    }

    @Override
    public String value3() {
        return getSettingName();
    }

    @Override
    public String value4() {
        return getSetting();
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
    public BithonAgentSettingRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value2(String value) {
        setAppName(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value3(String value) {
        setSettingName(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value4(String value) {
        setSetting(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value5(Timestamp value) {
        setCreatedAt(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value6(Timestamp value) {
        setUpdatedAt(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord values(Long value1, String value2, String value3, String value4, Timestamp value5, Timestamp value6) {
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
     * Create a detached BithonAgentSettingRecord
     */
    public BithonAgentSettingRecord() {
        super(BithonAgentSetting.BITHON_AGENT_SETTING);
    }

    /**
     * Create a detached, initialised BithonAgentSettingRecord
     */
    public BithonAgentSettingRecord(Long id, String appName, String settingName, String setting, Timestamp createdAt, Timestamp updatedAt) {
        super(BithonAgentSetting.BITHON_AGENT_SETTING);

        set(0, id);
        set(1, appName);
        set(2, settingName);
        set(3, setting);
        set(4, createdAt);
        set(5, updatedAt);
    }
}

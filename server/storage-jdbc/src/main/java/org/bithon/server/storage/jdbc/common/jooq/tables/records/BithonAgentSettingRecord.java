/*
 * This file is generated by jOOQ.
 */
package org.bithon.server.storage.jdbc.common.jooq.tables.records;


import java.time.LocalDateTime;

import org.bithon.server.storage.jdbc.common.jooq.tables.BithonAgentSetting;
import org.jooq.Field;
import org.jooq.Record7;
import org.jooq.Row7;
import org.jooq.impl.TableRecordImpl;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonAgentSettingRecord extends TableRecordImpl<BithonAgentSettingRecord> implements Record7<LocalDateTime, String, String, String, String, String, LocalDateTime> {

    private static final long serialVersionUID = -233445553;

    /**
     * Setter for <code>bithon_agent_setting.timestamp</code>. Created Timestamp
     */
    public void setTimestamp(LocalDateTime value) {
        set(0, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.timestamp</code>. Created Timestamp
     */
    public LocalDateTime getTimestamp() {
        return (LocalDateTime) get(0);
    }

    /**
     * Setter for <code>bithon_agent_setting.appName</code>.
     */
    public void setAppname(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.appName</code>.
     */
    public String getAppname() {
        return (String) get(1);
    }

    /**
     * Setter for <code>bithon_agent_setting.environment</code>.
     */
    public void setEnvironment(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.environment</code>.
     */
    public String getEnvironment() {
        return (String) get(2);
    }

    /**
     * Setter for <code>bithon_agent_setting.settingName</code>.
     */
    public void setSettingname(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.settingName</code>.
     */
    public String getSettingname() {
        return (String) get(3);
    }

    /**
     * Setter for <code>bithon_agent_setting.setting</code>. Setting text
     */
    public void setSetting(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.setting</code>. Setting text
     */
    public String getSetting() {
        return (String) get(4);
    }

    /**
     * Setter for <code>bithon_agent_setting.format</code>. Format of the Setting, can be either "json" or "yaml"
     */
    public void setFormat(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.format</code>. Format of the Setting, can be either "json" or "yaml"
     */
    public String getFormat() {
        return (String) get(5);
    }

    /**
     * Setter for <code>bithon_agent_setting.updatedAt</code>.
     */
    public void setUpdatedat(LocalDateTime value) {
        set(6, value);
    }

    /**
     * Getter for <code>bithon_agent_setting.updatedAt</code>.
     */
    public LocalDateTime getUpdatedat() {
        return (LocalDateTime) get(6);
    }

    // -------------------------------------------------------------------------
    // Record7 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row7<LocalDateTime, String, String, String, String, String, LocalDateTime> fieldsRow() {
        return (Row7) super.fieldsRow();
    }

    @Override
    public Row7<LocalDateTime, String, String, String, String, String, LocalDateTime> valuesRow() {
        return (Row7) super.valuesRow();
    }

    @Override
    public Field<LocalDateTime> field1() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.TIMESTAMP;
    }

    @Override
    public Field<String> field2() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.APPNAME;
    }

    @Override
    public Field<String> field3() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.ENVIRONMENT;
    }

    @Override
    public Field<String> field4() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.SETTINGNAME;
    }

    @Override
    public Field<String> field5() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.SETTING;
    }

    @Override
    public Field<String> field6() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.FORMAT;
    }

    @Override
    public Field<LocalDateTime> field7() {
        return BithonAgentSetting.BITHON_AGENT_SETTING.UPDATEDAT;
    }

    @Override
    public LocalDateTime component1() {
        return getTimestamp();
    }

    @Override
    public String component2() {
        return getAppname();
    }

    @Override
    public String component3() {
        return getEnvironment();
    }

    @Override
    public String component4() {
        return getSettingname();
    }

    @Override
    public String component5() {
        return getSetting();
    }

    @Override
    public String component6() {
        return getFormat();
    }

    @Override
    public LocalDateTime component7() {
        return getUpdatedat();
    }

    @Override
    public LocalDateTime value1() {
        return getTimestamp();
    }

    @Override
    public String value2() {
        return getAppname();
    }

    @Override
    public String value3() {
        return getEnvironment();
    }

    @Override
    public String value4() {
        return getSettingname();
    }

    @Override
    public String value5() {
        return getSetting();
    }

    @Override
    public String value6() {
        return getFormat();
    }

    @Override
    public LocalDateTime value7() {
        return getUpdatedat();
    }

    @Override
    public BithonAgentSettingRecord value1(LocalDateTime value) {
        setTimestamp(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value2(String value) {
        setAppname(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value3(String value) {
        setEnvironment(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value4(String value) {
        setSettingname(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value5(String value) {
        setSetting(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value6(String value) {
        setFormat(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord value7(LocalDateTime value) {
        setUpdatedat(value);
        return this;
    }

    @Override
    public BithonAgentSettingRecord values(LocalDateTime value1, String value2, String value3, String value4, String value5, String value6, LocalDateTime value7) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
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
    public BithonAgentSettingRecord(LocalDateTime timestamp, String appname, String environment, String settingname, String setting, String format, LocalDateTime updatedat) {
        super(BithonAgentSetting.BITHON_AGENT_SETTING);

        set(0, timestamp);
        set(1, appname);
        set(2, environment);
        set(3, settingname);
        set(4, setting);
        set(5, format);
        set(6, updatedat);
    }
}

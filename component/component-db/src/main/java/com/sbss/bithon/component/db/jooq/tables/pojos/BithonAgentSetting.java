/*
 * This file is generated by jOOQ.
 */
package com.sbss.bithon.component.db.jooq.tables.pojos;


import java.io.Serializable;
import java.sql.Timestamp;


/**
 * 配置
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class BithonAgentSetting implements Serializable {

    private static final long serialVersionUID = 1928920851;

    private Long      id;
    private String    appName;
    private String    settingName;
    private String    setting;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    public BithonAgentSetting() {}

    public BithonAgentSetting(BithonAgentSetting value) {
        this.id = value.id;
        this.appName = value.appName;
        this.settingName = value.settingName;
        this.setting = value.setting;
        this.createdAt = value.createdAt;
        this.updatedAt = value.updatedAt;
    }

    public BithonAgentSetting(
        Long      id,
        String    appName,
        String    settingName,
        String    setting,
        Timestamp createdAt,
        Timestamp updatedAt
    ) {
        this.id = id;
        this.appName = appName;
        this.settingName = settingName;
        this.setting = setting;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getSettingName() {
        return this.settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    public String getSetting() {
        return this.setting;
    }

    public void setSetting(String setting) {
        this.setting = setting;
    }

    public Timestamp getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BithonAgentSetting (");

        sb.append(id);
        sb.append(", ").append(appName);
        sb.append(", ").append(settingName);
        sb.append(", ").append(setting);
        sb.append(", ").append(createdAt);
        sb.append(", ").append(updatedAt);

        sb.append(")");
        return sb.toString();
    }
}

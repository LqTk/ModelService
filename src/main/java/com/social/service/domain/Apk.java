package com.social.service.domain;

import java.util.Date;

public class Apk {
    private String id;

    private Integer appCode;

    private String appVersion;

    private String appType;

    private Integer updateType;

    private Date time;

    public Apk(String id, Integer appCode, String appVersion, String appType, Integer updateType, Date time) {
        this.id = id;
        this.appCode = appCode;
        this.appVersion = appVersion;
        this.appType = appType;
        this.updateType = updateType;
        this.time = time;
    }

    public Apk(Integer appCode, String appVersion, String appType, Integer updateType, Date time) {
        this.appCode = appCode;
        this.appVersion = appVersion;
        this.appType = appType;
        this.updateType = updateType;
        this.time = time;
    }

    public Apk() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getAppCode() {
        return appCode;
    }

    public void setAppCode(Integer appCode) {
        this.appCode = appCode;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion == null ? null : appVersion.trim();
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType == null ? null : appType.trim();
    }

    public Integer getUpdateType() {
        return updateType;
    }

    public void setUpdateType(Integer updateType) {
        this.updateType = updateType;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

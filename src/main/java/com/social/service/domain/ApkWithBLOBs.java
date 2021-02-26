package com.social.service.domain;

import java.util.Date;

public class ApkWithBLOBs extends Apk {
    private String appUrl;

    private String appDes;

    public ApkWithBLOBs(String id, Integer appCode, String appVersion, String appType, Integer updateType, Date time, String appUrl, String appDes) {
        super(id, appCode, appVersion, appType, updateType, time);
        this.appUrl = appUrl;
        this.appDes = appDes;
    }

    public ApkWithBLOBs(Integer appCode, String appVersion, String appType, Integer updateType, Date time, String appUrl, String appDes) {
        super(appCode, appVersion, appType, updateType, time);
        this.appUrl = appUrl;
        this.appDes = appDes;
    }

    public ApkWithBLOBs() {
        super();
    }

    public String getAppUrl() {
        return appUrl;
    }

    public void setAppUrl(String appUrl) {
        this.appUrl = appUrl == null ? null : appUrl.trim();
    }

    public String getAppDes() {
        return appDes;
    }

    public void setAppDes(String appDes) {
        this.appDes = appDes == null ? null : appDes.trim();
    }
}

package com.social.service.domain;

public class ReportPublish {
    private String reportid;

    private String publishid;

    private String reportuserid;

    public ReportPublish(String reportid, String publishid, String reportuserid) {
        this.reportid = reportid;
        this.publishid = publishid;
        this.reportuserid = reportuserid;
    }

    public ReportPublish() {
        super();
    }

    public String getReportid() {
        return reportid;
    }

    public void setReportid(String reportid) {
        this.reportid = reportid == null ? null : reportid.trim();
    }

    public String getPublishid() {
        return publishid;
    }

    public void setPublishid(String publishid) {
        this.publishid = publishid == null ? null : publishid.trim();
    }

    public String getReportuserid() {
        return reportuserid;
    }

    public void setReportuserid(String reportuserid) {
        this.reportuserid = reportuserid == null ? null : reportuserid.trim();
    }
}
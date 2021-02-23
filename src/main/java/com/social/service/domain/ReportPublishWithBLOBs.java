package com.social.service.domain;

public class ReportPublishWithBLOBs extends ReportPublish {
    private String text;

    private String img;

    public ReportPublishWithBLOBs(String reportid, String publishid, String reportuserid, String text, String img) {
        super(reportid, publishid, reportuserid);
        this.text = text;
        this.img = img;
    }

    public ReportPublishWithBLOBs() {
        super();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }
}
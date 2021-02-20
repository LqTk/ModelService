package com.social.service.domain;

import java.util.Date;

public class SPublic {
    private String shareId;

    private String userId;

    private String shareName;

    private String shareUrl;

    private String shareText;

    private Date createtime;

    private Integer isPublic;

    private String type;

    private String location;

    private Double latitude;

    private Double longitude;

    private Integer reportCount;

    public SPublic(String shareId, String userId, String shareName, String shareUrl, String shareText, Date createtime,
                   Integer isPublic, String type, String location, Double latitude, Double longitude, Integer reportCount) {
        this.shareId = shareId;
        this.userId = userId;
        this.shareName = shareName;
        this.shareUrl = shareUrl;
        this.shareText = shareText;
        this.createtime = createtime;
        this.isPublic = isPublic;
        this.type = type;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reportCount = reportCount;
    }

    public SPublic() {
        super();
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId == null ? null : shareId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName == null ? null : shareName.trim();
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl == null ? null : shareUrl.trim();
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText == null ? null : shareText.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public double getLatitude() {
        return latitude==null?0:latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude==null?0:longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getReportCount() {
        return reportCount;
    }

    public void setReportCount(Integer reportCount) {
        this.reportCount = reportCount;
    }
}

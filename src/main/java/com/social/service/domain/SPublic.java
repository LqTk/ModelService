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

    public SPublic(String shareId, String userId, String shareName, String shareUrl, String shareText, Date createtime, Integer isPublic) {
        this.shareId = shareId;
        this.userId = userId;
        this.shareName = shareName;
        this.shareUrl = shareUrl;
        this.shareText = shareText;
        this.createtime = createtime;
        this.isPublic = isPublic;
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

}

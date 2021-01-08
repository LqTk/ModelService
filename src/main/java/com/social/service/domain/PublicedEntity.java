package com.social.service.domain;

import java.util.Date;
import java.util.List;

public class PublicedEntity {
    private String shareId;

    private String userId;

    private String userName;

    private String userHead;

    private Integer userSex;

    private String shareName;

    private String shareUrl;

    private String shareText;

    private Date createTime;

    private Integer isPublic;

    private List<Goods> goodsList;

    private List<ReviewEntity> reviewEntities;

    public PublicedEntity(String shareId, String userId, String userName, String userHead, Integer userSex, String shareName, String shareUrl, String shareText, Date createTime, Integer isPublic, List<Goods> goodsList, List<ReviewEntity> reviewEntities) {
        this.shareId = shareId;
        this.userId = userId;
        this.userName = userName;
        this.userHead = userHead;
        this.userSex = userSex;
        this.shareName = shareName;
        this.shareUrl = shareUrl;
        this.shareText = shareText;
        this.createTime = createTime;
        this.isPublic = isPublic;
        this.goodsList = goodsList;
        this.reviewEntities = reviewEntities;
    }

    public String getShareId() {
        return shareId;
    }

    public void setShareId(String shareId) {
        this.shareId = shareId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getShareName() {
        return shareName;
    }

    public void setShareName(String shareName) {
        this.shareName = shareName;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public List<Goods> getGoodsList() {
        return goodsList;
    }

    public void setGoodsList(List<Goods> goodsList) {
        this.goodsList = goodsList;
    }

    public List<ReviewEntity> getReviewEntities() {
        return reviewEntities;
    }

    public void setReviewEntities(List<ReviewEntity> reviewEntities) {
        this.reviewEntities = reviewEntities;
    }
}
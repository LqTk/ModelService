package com.social.service.domain;

import java.util.Date;

public class MsgPublishEntity {
    private String msgId;

    private String msgType;

    private String publishId;
    private String publishTitle;
    private String publishText;

    private String peopleName;
    private String peopleHead;
    private String peopleId;

    private String reviewId;
    private String reviewText;

    private String chatReviewId;
    private String chatText;

    private Integer isReaded;
    private String goodsId;

    private Date msgTime;

    public MsgPublishEntity(String msgId, String msgType, String publishId, String publishTitle, String publishText,
                            String peopleName, String peopleHead, String peopleId, Integer isReaded, String goodsId, Date msgTime) {
        this.msgId = msgId;
        this.msgType = msgType;
        this.publishId = publishId;
        this.publishTitle = publishTitle;
        this.publishText = publishText;
        this.peopleName = peopleName;
        this.peopleHead = peopleHead;
        this.peopleId = peopleId;
        this.isReaded = isReaded;
        this.goodsId = goodsId;
        this.msgTime = msgTime;
    }

    public MsgPublishEntity(String msgId, String msgType, String publishId, String publishTitle, String publishText,
                            String peopleName, String peopleHead, String peopleId, String reviewId, String reviewText, Integer isReaded, Date msgTime) {
        this.msgId = msgId;
        this.msgType = msgType;
        this.publishId = publishId;
        this.publishTitle = publishTitle;
        this.publishText = publishText;
        this.peopleName = peopleName;
        this.peopleHead = peopleHead;
        this.peopleId = peopleId;
        this.reviewId = reviewId;
        this.reviewText = reviewText;
        this.isReaded = isReaded;
        this.msgTime = msgTime;
    }

    public MsgPublishEntity(String msgId, String msgType, String publishId, String publishTitle, String publishText,
                            String peopleName, String peopleHead, String peopleId, String reviewId,
                            String chatReviewId, String chatText, Integer isReaded, Date msgTime) {
        this.msgId = msgId;
        this.msgType = msgType;
        this.publishId = publishId;
        this.publishTitle = publishTitle;
        this.publishText = publishText;
        this.peopleName = peopleName;
        this.peopleHead = peopleHead;
        this.peopleId = peopleId;
        this.reviewId = reviewId;
        this.chatReviewId = chatReviewId;
        this.chatText = chatText;
        this.isReaded = isReaded;
        this.msgTime = msgTime;
    }

    public int getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Integer isReaded) {
        this.isReaded = isReaded;
    }

    public String getPublishTitle() {
        return publishTitle;
    }

    public void setPublishTitle(String publishTitle) {
        this.publishTitle = publishTitle;
    }

    public String getPublishText() {
        return publishText;
    }

    public void setPublishText(String publishText) {
        this.publishText = publishText;
    }

    public String getMsgId() {
        return msgId;
    }

    public void setMsgId(String msgId) {
        this.msgId = msgId;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleHead() {
        return peopleHead;
    }

    public void setPeopleHead(String peopleHead) {
        this.peopleHead = peopleHead;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getChatReviewId() {
        return chatReviewId;
    }

    public void setChatReviewId(String chatReviewId) {
        this.chatReviewId = chatReviewId;
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public Date getMsgTime() {
        return msgTime;
    }

    public void setMsgTime(Date msgTime) {
        this.msgTime = msgTime;
    }
}

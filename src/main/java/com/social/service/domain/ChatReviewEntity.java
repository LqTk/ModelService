package com.social.service.domain;

import java.util.Date;

public class ChatReviewEntity {
    private String reviewChatId;

    private String reviewId;

    private String talkId;

    private String toId;

    private String talkName;
    private String talkHead;

    private String toName;
    private String toHead;

    private String chatText;

    private Date chatTime;

    public ChatReviewEntity(String reviewChatId, String reviewId, String talkId, String toId, String talkName, String talkHead, String toName, String toHead, String chatText, Date chatTime) {
        this.reviewChatId = reviewChatId;
        this.reviewId = reviewId;
        this.talkId = talkId;
        this.toId = toId;
        this.talkName = talkName;
        this.talkHead = talkHead;
        this.toName = toName;
        this.toHead = toHead;
        this.chatText = chatText;
        this.chatTime = chatTime;
    }

    public String getTalkHead() {
        return talkHead;
    }

    public void setTalkHead(String talkHead) {
        this.talkHead = talkHead;
    }

    public String getToHead() {
        return toHead;
    }

    public void setToHead(String toHead) {
        this.toHead = toHead;
    }

    public String getTalkName() {
        return talkName;
    }

    public void setTalkName(String talkName) {
        this.talkName = talkName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
    }

    public ChatReviewEntity() {
        super();
    }

    public String getReviewChatId() {
        return reviewChatId;
    }

    public void setReviewChatId(String reviewChatId) {
        this.reviewChatId = reviewChatId == null ? null : reviewChatId.trim();
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId == null ? null : reviewId.trim();
    }

    public String getTalkId() {
        return talkId;
    }

    public void setTalkId(String talkId) {
        this.talkId = talkId == null ? null : talkId.trim();
    }

    public String getToId() {
        return toId;
    }

    public void setToId(String toId) {
        this.toId = toId == null ? null : toId.trim();
    }

    public String getChatText() {
        return chatText;
    }

    public void setChatText(String chatText) {
        this.chatText = chatText == null ? null : chatText.trim();
    }

    public Date getChatTime() {
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }
}

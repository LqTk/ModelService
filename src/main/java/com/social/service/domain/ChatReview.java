package com.social.service.domain;

import java.util.Date;

public class ChatReview {
    private String reviewChatId;

    private String reviewId;

    private String talkId;

    private String toId;

    private String chatText;

    private Date chatTime;

    public ChatReview(String reviewchatId, String reviewId, String talkId, String toId, String chatText, Date chatTime) {
        this.reviewChatId = reviewchatId;
        this.reviewId = reviewId;
        this.talkId = talkId;
        this.toId = toId;
        this.chatText = chatText;
        this.chatTime = chatTime;
    }

    public ChatReview() {
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

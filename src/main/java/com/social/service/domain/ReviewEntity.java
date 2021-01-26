package com.social.service.domain;

import java.util.Date;
import java.util.List;

public class ReviewEntity {
    private String reviewId;
    private String peopleId;
    private String publishId;
    private String reviewText;
    private String peopleName;
    private String peopleHead;
    private Date reviewTime;
    private List<ChatReview> chatReviewList;

    public ReviewEntity(String reviewId, String peopleId, String publishId, String reviewText, String peopleName, String peopleHead, Date reviewTime, List<ChatReview> chatReviewList) {
        this.reviewId = reviewId;
        this.peopleId = peopleId;
        this.publishId = publishId;
        this.reviewText = reviewText;
        this.peopleName = peopleName;
        this.peopleHead = peopleHead;
        this.reviewTime = reviewTime;
        this.chatReviewList = chatReviewList;
    }

    public ReviewEntity(String reviewId, String peopleId, String publishId, String reviewText, String peopleName, String peopleHead, Date reviewTime) {
        this.reviewId = reviewId;
        this.peopleId = peopleId;
        this.publishId = publishId;
        this.reviewText = reviewText;
        this.peopleName = peopleName;
        this.peopleHead = peopleHead;
        this.reviewTime = reviewTime;
    }

    public String getPeopleHead() {
        return peopleHead;
    }

    public void setPeopleHead(String peopleHead) {
        this.peopleHead = peopleHead;
    }

    public List<ChatReview> getChatReviewList() {
        return chatReviewList;
    }

    public void setChatReviewList(List<ChatReview> chatReviewList) {
        this.chatReviewList = chatReviewList;
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPublishId() {
        return publishId;
    }

    public void setPublishId(String publishId) {
        this.publishId = publishId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }
}

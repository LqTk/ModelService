package com.social.service.domain;

import java.util.Date;

public class Review {
    private String reviewId;

    private String peopleId;

    private String publicId;

    private String reviewText;

    private Date reviewTime;

    public Review(String reviewId, String peopleId, String publicId, String reviewText, Date reviewTime) {
        this.reviewId = reviewId;
        this.peopleId = peopleId;
        this.publicId = publicId;
        this.reviewText = reviewText;
        this.reviewTime = reviewTime;
    }

    public Review() {
        super();
    }

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewid) {
        this.reviewId = reviewid == null ? null : reviewid.trim();
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleid) {
        this.peopleId = peopleid == null ? null : peopleid.trim();
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicid) {
        this.publicId = publicid == null ? null : publicid.trim();
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewtext) {
        this.reviewText = reviewtext == null ? null : reviewtext.trim();
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }
}

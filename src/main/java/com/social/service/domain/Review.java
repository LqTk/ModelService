package com.social.service.domain;

public class Review {
    private String reviewid;

    private String peopleid;

    private String publicid;

    private String reviewtext;

    public Review(String reviewid, String peopleid, String publicid, String reviewtext) {
        this.reviewid = reviewid;
        this.peopleid = peopleid;
        this.publicid = publicid;
        this.reviewtext = reviewtext;
    }

    public Review() {
        super();
    }

    public String getReviewid() {
        return reviewid;
    }

    public void setReviewid(String reviewid) {
        this.reviewid = reviewid == null ? null : reviewid.trim();
    }

    public String getPeopleid() {
        return peopleid;
    }

    public void setPeopleid(String peopleid) {
        this.peopleid = peopleid == null ? null : peopleid.trim();
    }

    public String getPublicid() {
        return publicid;
    }

    public void setPublicid(String publicid) {
        this.publicid = publicid == null ? null : publicid.trim();
    }

    public String getReviewtext() {
        return reviewtext;
    }

    public void setReviewtext(String reviewtext) {
        this.reviewtext = reviewtext == null ? null : reviewtext.trim();
    }
}
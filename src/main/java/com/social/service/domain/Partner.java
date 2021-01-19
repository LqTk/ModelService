package com.social.service.domain;

import java.util.Date;

public class Partner {
    private String id;

    private String userId;

    private String partnerId;

    private Date time;

    public Partner(String id, String userId, String partnerId, Date time) {
        this.id = id;
        this.userId = userId;
        this.partnerId = partnerId;
        this.time = time;
    }

    public Partner() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId == null ? null : partnerId.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

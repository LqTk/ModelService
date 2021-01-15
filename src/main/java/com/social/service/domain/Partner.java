package com.social.service.domain;

import java.util.Date;

public class Partner {
    private String id;

    private String userid;

    private String partnerid;

    private Date time;

    public Partner(String id, String userid, String partnerid, Date time) {
        this.id = id;
        this.userid = userid;
        this.partnerid = partnerid;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPartnerid() {
        return partnerid;
    }

    public void setPartnerid(String partnerid) {
        this.partnerid = partnerid == null ? null : partnerid.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}

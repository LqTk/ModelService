package com.social.service.domain;

public class Goods {
    private String goodsid;

    private String userid;

    private String publicid;

    private String peoplename;

    private String peoplehead;

    public Goods(String goodsid, String userid, String publicid, String peoplename, String peoplehead) {
        this.goodsid = goodsid;
        this.userid = userid;
        this.publicid = publicid;
        this.peoplename = peoplename;
        this.peoplehead = peoplehead;
    }

    public Goods() {
        super();
    }

    public String getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(String goodsid) {
        this.goodsid = goodsid == null ? null : goodsid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPublicid() {
        return publicid;
    }

    public void setPublicid(String publicid) {
        this.publicid = publicid == null ? null : publicid.trim();
    }

    public String getPeoplename() {
        return peoplename;
    }

    public void setPeoplename(String peoplename) {
        this.peoplename = peoplename == null ? null : peoplename.trim();
    }

    public String getPeoplehead() {
        return peoplehead;
    }

    public void setPeoplehead(String peoplehead) {
        this.peoplehead = peoplehead == null ? null : peoplehead.trim();
    }
}
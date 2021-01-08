package com.social.service.domain;

public class Goods {
    private String goodsId;

    private String peopleId;

    private String publicId;

    private String peopleName;

    private String peopleHead;

    public Goods(String goodsId, String peopleId, String publicId, String peopleName, String peopleHead) {
        this.goodsId = goodsId;
        this.peopleId = peopleId;
        this.publicId = publicId;
        this.peopleName = peopleName;
        this.peopleHead = peopleHead;
    }

    public Goods() {
        super();
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId == null ? null : goodsId.trim();
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId == null ? null : peopleId.trim();
    }

    public String getPublicId() {
        return publicId;
    }

    public void setPublicId(String publicId) {
        this.publicId = publicId == null ? null : publicId.trim();
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName == null ? null : peopleName.trim();
    }

    public String getPeopleHead() {
        return peopleHead;
    }

    public void setPeopleHead(String peopleHead) {
        this.peopleHead = peopleHead == null ? null : peopleHead.trim();
    }
}

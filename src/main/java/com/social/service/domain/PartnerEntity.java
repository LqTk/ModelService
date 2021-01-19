package com.social.service.domain;

import java.util.Date;

public class PartnerEntity {
    String peopleName;
    String peopleId;
    String peopleHead;
    Integer peopleSex;
    String peopleDes;
    Date time;

    public PartnerEntity(String peopleName, String peopleId, String peopleHead, Integer peopleSex, String peopleDes, Date time) {
        this.peopleName = peopleName;
        this.peopleId = peopleId;
        this.peopleHead = peopleHead;
        this.peopleSex = peopleSex;
        this.peopleDes = peopleDes;
        this.time = time;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getPeopleHead() {
        return peopleHead;
    }

    public void setPeopleHead(String peopleHead) {
        this.peopleHead = peopleHead;
    }

    public Integer getPeopleSex() {
        return peopleSex;
    }

    public void setPeopleSex(Integer peopleSex) {
        this.peopleSex = peopleSex;
    }

    public String getPeopleDes() {
        return peopleDes;
    }

    public void setPeopleDes(String peopleDes) {
        this.peopleDes = peopleDes;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public int hashCode() {
        return peopleId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        PartnerEntity partnerEntity = (PartnerEntity) obj;
        return this.peopleId.equals(partnerEntity.getPeopleId());
    }

}

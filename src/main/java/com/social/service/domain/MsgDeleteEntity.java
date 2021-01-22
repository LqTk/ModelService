package com.social.service.domain;

public class MsgDeleteEntity {
    public String type;
    public String path;

    public MsgDeleteEntity(String type, String path) {
        this.type = type;
        this.path = path;
    }

    public MsgDeleteEntity() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

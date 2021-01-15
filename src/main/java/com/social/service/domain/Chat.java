package com.social.service.domain;

import java.util.Date;

public class Chat {
    private String chatId;

    private String talkId;

    private String toId;

    private String msgType;

    private String msgContent;

    private String voiceTime;

    private String filepath;

    private Date chatTime;

    public Chat(String chatId, String talkId, String toId, String msgType, String msgContent, String voiceTime, String filepath, Date chatTime) {
        this.chatId = chatId;
        this.talkId = talkId;
        this.toId = toId;
        this.msgType = msgType;
        this.msgContent = msgContent;
        this.voiceTime = voiceTime;
        this.filepath = filepath;
        this.chatTime = chatTime;
    }

    public Chat() {
        super();
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId == null ? null : chatId.trim();
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

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public String getVoiceTime() {
        return voiceTime;
    }

    public void setVoiceTime(String voiceTime) {
        this.voiceTime = voiceTime;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath == null ? null : filepath.trim();
    }

    public Date getChatTime() {
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }
}

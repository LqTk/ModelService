package com.social.service.domain;

import java.util.Date;

public class ChatEntity {


    public String chatId;

    public String msgContent;

    public String msgType; //

    public String voiceTime;
    public String senderId;
    public String senderAvatar;
    public String senderName;
    public Date chatTime;

    public ChatEntity() {
    }

    public ChatEntity(String chatId, String msgContent, String msgType, String voiceTime, String senderId, String senderAvatar, String senderName, Date chatTime) {
        this.chatId = chatId;
        this.msgContent = msgContent;
        this.msgType = msgType;
        this.voiceTime = voiceTime;
        this.senderId = senderId;
        this.senderAvatar = senderAvatar;
        this.senderName = senderName;
        this.chatTime = chatTime;
    }

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getVoiceTime() {
        return voiceTime;
    }

    public void setVoiceTime(String voiceTime) {
        this.voiceTime = voiceTime;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getSenderAvatar() {
        return senderAvatar;
    }

    public void setSenderAvatar(String senderAvatar) {
        this.senderAvatar = senderAvatar;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public Date getChatTime() {
        return chatTime;
    }

    public void setChatTime(Date chatTime) {
        this.chatTime = chatTime;
    }
}

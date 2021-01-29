package com.social.service.common;

public class Const {
    /**
     * 校验类型
     */
    public static final String PHONE = "phone";
    public static final String USERNAME = "name";

    /**
     * 聊天消息类型
     */
    public static final String MODE_TEXT = "TEXT";
    public static final String MODE_VOICE = "VOICE";
    public static final String MODE_IMAGE = "PIC";
    public static final String MODE_VIDEO = "VIDEO";

    /**
     * 未读消息类型
     */
    public static final String MSG_REVIEW = "review";
    public static final String MSG_REVIEW_CHAT = "review_chat";
    public static final String MSG_GOODS = "goods";

    /**
     * 聊天图片语音保存路径
     */
    public static String uploadDir = "D:\\tomact\\apache-tomcat-9.0.21\\proDir\\";
    public static String upLoadHead = uploadDir+"social/headImg";
    public static String upLoadImg = "social/chatImg";
    public static String upLoadVideo = "social/chatVideo";
    public static String upLoadVoice = "social/chatVoice";
}

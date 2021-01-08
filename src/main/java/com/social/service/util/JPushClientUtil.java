package com.social.service.util;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JPushClientUtil {
    private final static String appKey = "a84a9b231dc2696f5eeff4ad";
    private final static String masterSecret = "3d3377976950c5a8b3fba3f9";

    private static JPushClient jPushClient = new JPushClient(masterSecret,appKey);

    /**
     * 推送给指定设备标识的用户
     * @param registrationId 设备标识
     * @param notification_title 通知内容标题
     * @param msg_title 消息内容标题
     * @param msg_content 消息内容
     * @param extrasparam 扩展字段
     * @return 0失败 1成功
     */
    public static int sendToRegistrationId(String registrationId, String notification_title, String msg_title, String msg_content, String extrasparam){
        int result = 0;
        try {
            PushPayload pushPayload = JPushClientUtil.buildPushObject_all_registrationId_alterWithTitle(registrationId,notification_title,msg_title,msg_content,extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode()==200){
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送给所有Android用户
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @return 0失败 1成功
     */
    public static int sendToAllAndroid(String notification_title, String msg_title, String msg_content, String extrasparam){
        int result = 0;
        try {
            PushPayload pushPayload = JPushClientUtil.buildPushObject_android_all_alertWithTitle(notification_title,msg_title,msg_content,extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode()==200){
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送给所有Ios用户
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @return 0失败 1成功
     */
    public static int sendToAllIos(String notification_title, String msg_title, String msg_content, String extrasparam){
        int result = 0;
        try {
            PushPayload pushPayload = JPushClientUtil.buildPushObject_ios_all_alterWithTitle(notification_title,msg_title,msg_content,extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode()==200){
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送给所有用户
     * @param notification_title
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @return 0失败 1成功
     */
    public static int sendToAll(String notification_title, String msg_title, String msg_content, String extrasparam){
        int result = 0;
        try {
            PushPayload pushPayload = JPushClientUtil.buildPushObject_android_and_ios(notification_title,msg_title,msg_content,extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode()==200){
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送给所有用户
     * @param registerId
     * @param msg_title
     * @param msg_content
     * @param extrasparam
     * @return 0失败 1成功
     */
    public static int sendMessageToAll(String registerId, String msg_title, String msg_content, String extraKey, String extrasparam){
        int result = 0;
        try {
            PushPayload pushPayload = JPushClientUtil.buildPushMessage_all_registrationId_alterWithTitle(registerId,msg_title,msg_content,extraKey,extrasparam);
            System.out.println(pushPayload);
            PushResult pushResult = jPushClient.sendPush(pushPayload);
            System.out.println(pushResult);
            if (pushResult.getResponseCode()==200){
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static PushPayload buildPushObject_android_and_ios(String notification_title, String msg_title, String msg_content, String extrasparam){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .setAlert(notification_title)
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(notification_title)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("android Key",extrasparam)
                                .build()
                        ).addPlatformNotification(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(notification_title)
                                //直接传alert
                                //此项是指定此推送的badge自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf")
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("Ios key",extrasparam)
                                //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
//                                .setContentAvailable(true)
                                .build())
                        .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("message extra key", extrasparam)
                        .build())
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    private static PushPayload buildPushObject_all_registrationId_alterWithTitle(String registrationId,String notification_title, String msg_title, String msg_content, String extrasparam){
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(registrationId))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(msg_content)
                                .setTitle(notification_title)
                                //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                .addExtra("extra key",extrasparam)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .setAlert(notification_title)
                                .incrBadge(1)
                                .setSound("sound.caf")
                                .addExtra("extra Key",extrasparam)
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("extra key",extrasparam)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setSendno(1)
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    private static PushPayload buildPushMessage_all_registrationId_alterWithTitle(String registrationId, String msg_title, String msg_content,String extraKey, String extrasparam){
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.registrationId(registrationId))
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra(extraKey,extrasparam)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setSendno(1)
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    private static PushPayload buildPushObject_android_all_alertWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam){
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .setAlert(notification_title)
                                .setTitle(notification_title)
                                .addExtra("extra key",extrasparam)
                                .build())
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("extra key",extrasparam)
                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(false)
                        .setSendno(1)
                        .setTimeToLive(86400)
                        .build())
                .build();
    }

    private static PushPayload buildPushObject_ios_all_alterWithTitle(String notification_title, String msg_title, String msg_content, String extrasparam){
        return PushPayload.newBuilder()
                .setPlatform(Platform.ios())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                                .addPlatformNotification(IosNotification.newBuilder()
                                        //传一个IosAlert对象，指定apns title、title、subtitle等
                                        .setAlert(notification_title)
                                        //直接传alert
                                        //此项是指定此推送的badge自动加1
                                        .incrBadge(1)
                                        //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                        // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                        .setSound("sound.caf")
                                        //此字段为透传字段，不会显示在通知栏。用户可以通过此字段来做一些定制需求，如特定的key传要指定跳转的页面（value）
                                        .addExtra("Ios key",extrasparam)
                                        //此项说明此推送是一个background推送，想了解background看：http://docs.jpush.io/client/ios_tutorials/#ios-7-background-remote-notification
//                                .setContentAvailable(true)
                                        .build())
                                .build()
                )
                //Platform指定了哪些平台就会像指定平台中符合推送条件的设备进行推送。 jpush的自定义消息，
                // sdk默认不做任何处理，不会有通知提示。建议看文档http://docs.jpush.io/guideline/faq/的
                // [通知与自定义消息有什么区别？]了解通知和自定义消息的区别
                .setMessage(Message.newBuilder()
                        .setMsgContent(msg_content)
                        .setTitle(msg_title)
                        .addExtra("message extra key", extrasparam)
                        .build())
                .setOptions(Options.newBuilder()
                        //此字段的值是用来指定本推送要推送的apns环境，false表示开发，true表示生产；对android和自定义消息无意义
                        .setApnsProduction(false)
                        //此字段是给开发者自己给推送编号，方便推送者分辨推送记录
                        .setSendno(1)
                        //此字段的值是用来指定本推送的离线保存时长，如果不传此字段则默认保存一天，最多指定保留十天，单位为秒
                        .setTimeToLive(86400)
                        .build())
                .build();
    }
}

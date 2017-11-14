package com.tuodao.bp.common.util;


import com.alibaba.fastjson.JSON;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.AppMessage;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.LinkTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.TransmissionTemplate;
import com.gexin.rp.sdk.template.style.Style0;
import com.gexin.rp.sdk.template.style.Style1;
import com.tuodao.bp.common.config.GetuiProperties;
import com.tuodao.bp.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * Description: 个推消息推送
 * User: zkai
 * Date: 2017/8/18
 * Time: 14:59
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class GetuiPushUtil {

    @Autowired
    private GetuiProperties getuiProperties;
    private static Logger log = LoggerFactory.getLogger(GetuiPushUtil.class);
    /**
     * 初始个推连接
     */
    private static IGtPush iGtPush = null;
    void initIGtPush() {
        if(iGtPush == null){
            iGtPush = new IGtPush(getuiProperties.getHost(), getuiProperties.getAppKey(), getuiProperties.getMasterSecret());
        }

    }

    /**
     * 发送消息并重试
     * @param appMessage
     * @return
     */
    public Callable<Boolean> pushMessageToApp(AppMessage appMessage ) {
        Callable<Boolean> pushMessageToApp = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                initIGtPush();
                IPushResult ret = iGtPush.pushMessageToApp(appMessage);
                log.info("个推推送返回信息"+ret.getResponse().toString());
                return checkIPushResult(ret);
            }
        };
        return pushMessageToApp;
    }

    /**
     * 发送消息并重试
     * @param targets
     * @param listMessage
     * @return
     */
    public Callable<Boolean> pushMessageToList(List targets,ListMessage listMessage) {
        Callable<Boolean> pushMessageToApp = new Callable<Boolean>() {
            @Override
            public Boolean call() throws Exception {
                initIGtPush();
                // taskId用于在推送时去查找对应的message
                String taskId = iGtPush.getContentId(listMessage);
                IPushResult ret = iGtPush.pushMessageToList(taskId, targets);
                return checkIPushResult(ret);
            }
        };
        return pushMessageToApp;
    }

    private boolean checkIPushResult(IPushResult ret){
        if (ret == null || !ret.getResponse().get("result").equals("ok")) {
            return false;
        }
        return true;
    }


    /**
     * 点击通知打开网页模板
     * @param
     * @param
     * @return
     */
    public LinkTemplate linkTemplate(String title,String content,String url) {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(getuiProperties.getAppId());
        template.setAppkey(getuiProperties.getAppKey());

        Style1 style = new Style1();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl(url);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");
        return template;
    }

    /**
     * 点击通知打开网页模板
     * @param title 标题
     * @param content 内容
     * @param url 跳转地址
     * @param pushTime 推送时间
     * @return
     */
    public LinkTemplate linkTemplate(String title,String content,String url,String pushTime) {
        LinkTemplate template = new LinkTemplate();
        // 设置APPID与APPKEY
        template.setAppId(getuiProperties.getAppId());
        template.setAppkey(getuiProperties.getAppKey());

        Style1 style = new Style1();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        // 设置打开的网址地址
        template.setUrl(url);
        // 设置定时展示时间
        if(!StringUtils.isBlank(pushTime)){
            Date endTime = TimeUtils.addMinute(TimeUtils.format(pushTime,"yyyy-MM-dd HH:mm:ss"),6);
            try {
                template.setDuration(pushTime, TimeUtils.format(endTime,"yyyy-MM-dd HH:mm:ss"));
            } catch (Exception e) {
                log.error("消息推送定时展示时间失败",e.getMessage());
            }
        }

        return template;
    }

    /**
     * 点击通知打开应用模板
     */
    public NotificationTemplate notificationTemplate(String title, String content, Map<String,String> extras) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(getuiProperties.getAppId());
        template.setAppkey(getuiProperties.getAppKey());
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(getuiProperties.getTransmissionType());
        template.setTransmissionContent(JSON.toJSONString(extras));
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        return template;
    }


    /**
     * 点击通知打开应用模板
     */
    public NotificationTemplate notificationTemplate(String title, String content, Map<String,String> extras,String pushTime) {
        NotificationTemplate template = new NotificationTemplate();
        // 设置APPID与APPKEY
        template.setAppId(getuiProperties.getAppId());
        template.setAppkey(getuiProperties.getAppKey());
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(getuiProperties.getTransmissionType());
        template.setTransmissionContent(JSON.toJSONString(extras));
        if(!StringUtils.isBlank(pushTime)){
            Date endTime = TimeUtils.addMinute(TimeUtils.format(pushTime,"yyyy-MM-dd HH:mm:ss"),6);
            try {
                template.setDuration(pushTime, TimeUtils.format(endTime,"yyyy-MM-dd HH:mm:ss"));
            } catch (Exception e) {
                log.error("消息推送定时展示时间失败",e.getMessage());
            }
        }

        Style0 style = new Style0();
        // 设置通知栏标题与内容
        style.setTitle(title);
        style.setText(content);
        // 配置通知栏图标
        style.setLogo("icon.png");
        // 配置通知栏网络图标
        style.setLogoUrl("");
        // 设置通知是否响铃，震动，或者可清除
        style.setRing(true);
        style.setVibrate(true);
        style.setClearable(true);
        template.setStyle(style);

        return template;
    }

    /**
     * 透传消息模版
     * @param pushContent 透传内容
     * @return
     */
    public TransmissionTemplate transmissionTemplate(String pushContent) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(getuiProperties.getAppId());
        template.setAppkey(getuiProperties.getAppKey());
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(getuiProperties.getTransmissionType());
        template.setTransmissionContent(pushContent);
        // 设置定时展示时间
        // template.setDuration("2015-01-16 11:40:00", "2015-01-16 12:24:00");

        /**
         * ios 特有
         */
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge(getuiProperties.getAutoBadge());
        // 推送直接带有透传数据
        payload.setContentAvailable(1);
        // 通知铃声文件名，无声设置为"com.gexin.ios.silence"
        payload.setSound(getuiProperties.getSound());
        // 在客户端通知栏触发特定的action和button显示
//        payload.setCategory("$由客户端定义");
        template.setAPNInfo(payload);

        return template;
    }

    /**
     * 透传消息模版
     * @param pushContent 透传内容
     * @return
     */
    public TransmissionTemplate transmissionTemplate(String pushContent,String pushTime) {
        TransmissionTemplate template = new TransmissionTemplate();
        template.setAppId(getuiProperties.getAppId());
        template.setAppkey(getuiProperties.getAppKey());
        // 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
        template.setTransmissionType(getuiProperties.getTransmissionType());
        template.setTransmissionContent(pushContent);
        // 设置定时展示时间
        if(!StringUtils.isBlank(pushTime)){
            Date endTime = TimeUtils.addMinute(TimeUtils.format(pushTime,"yyyy-MM-dd HH:mm:ss"),6);
            try {
                template.setDuration(pushTime, TimeUtils.format(endTime,"yyyy-MM-dd HH:mm:ss"));
            } catch (Exception e) {
                log.error("消息推送定时展示时间失败",e.getMessage());
            }
        }

        /**
         * ios 特有
         */
        APNPayload payload = new APNPayload();
        //在已有数字基础上加1显示，设置为-1时，在已有数字上减1显示，设置为数字时，显示指定数字
        payload.setAutoBadge(getuiProperties.getAutoBadge());
        // 推送直接带有透传数据
        payload.setContentAvailable(1);
        // 通知铃声文件名，无声设置为"com.gexin.ios.silence"
        payload.setSound(getuiProperties.getSound());
        // 在客户端通知栏触发特定的action和button显示
//        payload.setCategory("$由客户端定义");
        template.setAPNInfo(payload);

        return template;
    }

}


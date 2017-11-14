package com.tuodao.bp.model.constant.push;

/**
 * Description: 推送常量
 * User: zkai
 * Date: 2017/8/17
 * Time: 15:10
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class PushConstants {

    /**
     * 分割类型
     */
    public static final String SPILE_TYPE = ",";

    /**
     * 推送工具（1:极光推送 2:个推消息推送）
     */
    public static final String PUSH_TOOL_JIGUANG = "1";
    public static final String PUSH_TOOL_GETUI = "2";
    public static final String PUSH_TOOL_ALL = "1|2";

    /**
     * 推送对象 1:所有平台 2: 推送到对应别名的设备上 3:推送给安卓 4:推送给ios
     */
    public static final String PUSH_OJECT_ALL = "1";
    public static final String PUSH_OJECT_ALIA = "2";
    public static final String PUSH_OJECT_ANDROID = "3";
    public static final String PUSH_OJECT_IOS = "4";
    public static final String PUSH_OJECT_ALLS = "1|2|3|4";

    /**
     * 通知模版类型（个推特有）
     * 1: 点击通知打开应用模板(ps:针对沉默用户，发送推送消息，点击消息栏的通知可直接激活启动应用，提升应用的转化率。)Notification Template
     * 2: 点击通知打开网页模板(ps:推送广促销活动，用户点击通知栏信息，直接打开到指定的促销活动页面，推送直接到达指定页面，免去了中间过程的用户流失。)LinkTemplate
     */
    public static final String PUSH_TEMPLATE_TYPE_NOTICE = "1";
    public static final String PUSH_TEMPLATE_TYPE_LINK = "2";
    public static final String PUSH_TEMPLATE_TYPE_ALL = "1|2";
}

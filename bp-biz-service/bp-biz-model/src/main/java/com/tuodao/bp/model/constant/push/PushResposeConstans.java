package com.tuodao.bp.model.constant.push;

/**
 * Description: 推送异常常量
 * User: zkai
 * Date: 2017/8/17
 * Time: 15:10
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class PushResposeConstans {

    /** 推送工具不符合 */
    public static final int PUSH_TOOL_IS_ERROR = 400001;
    /** 推送标题不能为空 */
    public static final int PUSH_TITLE_NOT_NULL = 400002;
    /** 推送内容不能为空 */
    public static final int PUSH_CONTENT_NOT_NULL = 400003;
    /** 推送对象不符合 */
    public static final int PUSH_OBJECT_IS_ERROR = 400004;
    /** 推送别名不能为空 */
    public static final int PUSH_ALIA_NOT_NULL = 400005;
    /** 推送组不能为空 */
    public static final int PUSH_TAG_NOT_NULL = 400006;
    /** 推送类型不符合 */
    public static final int PUSH_TYPE_IS_ERROR = 400007;

    /** 消息推送额外参数传入异常 */
    public static final int PUSH_EXTARS_IS_ERROR = 400008;

    /** 推送通知模版类型不符合 */
    public static final int PUSH_TEMPLATE_TYPE_IS_ERROR = 400009;

    /** 推送消息打开url地址不能为空 */
    public static final int PUSH_URL_NOT_NULL = 400010;
    /** 消息推送失败 */
    public static final int PUSH_ERROR = 400011;
    /** 消息推送指定时间异常 */
    public static final int PUSH_TIME_ERROR = 400012;
}

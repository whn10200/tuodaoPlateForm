package com.tuodao.bp.model.constant.common;

/**
 * @description: 基础服务异常代码常量
 * @author: mif
 * @date: 2017/8/23
 * @time: 14:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CommonExceptionConstant {

    /** 参数超出长度限制*/
    public static final int PARAM_BEYOND_THE_LENGTH = 119001;
    /** 参数传值范围错误*/
    public static final int PARAM_VALUE_ERROR = 119002;
    /** 发送短信失败：功能未启用 */
    public static final int SMS_SEND_UNABLE = 119003;
    /** 短信发送太频繁，请稍候*/
    public static final int SMS_SEND_TOO_FREQUENTLY = 119004;
    /** 本号码当天短信发送量达到上限 */
    public static final int MOBILE_COUNT_TOP_LIMIT = 119005;
    /** 本IP当天短信发送量达到上限 */
    public static final int IP_COUNT_TOP_LIMIT = 119006;
    /** 本号码1天内最多可绑定的IP发送短信达到上限*/
    public static final int MOBILE_BAND_IP_COUNT_TOP_LIMIT = 119007;
    /** 互亿短信未配置或已禁用 */
    public static final int HUYI_ACCOUT_UNCONFIG = 119008;
    /** 短信合作平台异常*/
    public static final int COOPERATION_PLATFORM_EXCEPTION = 119009;
    /**返回结果解析异常*/
    public static final int ANALYTIC_RESULT_EXCEPTION = 119010;
    /**电话号码不能为空*/
    public static final int MOBILE_CAN_NOT_BE_NULL = 119011;
    /**电话号码格式错误*/
    public static final int MOBILE_FORMAT_ERROR = 119012;
    /** 短信内容不能为空*/
    public static final int CONTENT_CAN_NOT_BE_NULL = 119013;
    /** 短信内容最大300字*/
    public static final int CONTENT_LENGTH_MAX_300 = 119014;
    /** 客户端IP不能为空*/
    public static final int CUSTOMER_IP_CAN_NOT_BE_NULL = 119015;
    /** 客户端IP格式错误*/
    public static final int CUSTOMER_IP_FORMAT_ERROR = 119016;
    /**邮件收件人地址不能为空 */
    public static final int EMAIL_TO_CAN_NOT_BE_BLANK = 119017;
    /** 邮件内容不能为空*/
    public static final int EMAIL_CONTENT_CAN_NOT_BE_BLANK = 119018;
    /** 手机号码数量超过50*/
    public static final int MOBILES_COUNT_BEYOND_50 = 119020;
    /** 发送失败，本时段内容将延迟发送 */
    public static final int CONTENT_WILL_BE_DELAY_SEND = 119021;


}

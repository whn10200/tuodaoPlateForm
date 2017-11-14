package com.tuodao.bp.model.constant.facade;

/**
 * @description: 聚合常量
 * @author: mif
 * @date: 2017/9/12
 * @time: 14:41
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeConstants {

    /**
     * 短信类型（register:注册；forgetLoginPw：忘记登录密码：forgetPayPw：忘记支付密码；feedback:意见反馈）
     */
    public static final String SMS_TYPE_REGISTER = "register";
    public static final String SMS_TYPE_FORGET_LOGIN_PW = "findLoginPw";
    public static final String SMS_TYPE_FORGET_PAY_PW = "findPayPw";
    public static final String SMS_TYPE_FEEDBACK = "feedback";
    public static final String SMS_TYPE_ALL = "register|findLoginPw|findPayPw|feedback";

}

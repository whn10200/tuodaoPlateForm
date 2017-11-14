package com.tuodao.bp.model.constant;

/**
 * @description: 正则表达式
 * @author: mif
 * @date: 2017/9/15
 * @time: 11:50
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class RegexpConstant {
    /** IP正则表达式*/
    public static final String PATTERN_IP_REGEXP = "[0-9]+(?:\\.[0-9]+){0,3}";
    /**银行卡号正则*/
    public static final String PATTERN_BANK_NUM_REGEXP = "\\d{16}|\\d{19}";
    /**支付密码正则（6位正整数）*/
    public static final String PATTERN_PAY_PW_REGEXP = "\\d{6}";
    /**手机号码 */
    public static final String PATTERN_MOBILE_REGEXP = "^(((1([3,4,5,7,8][0-9]))\\d{8})|(0\\d{2}-?\\d{8})|(0\\d{3}-?\\d{7,8}))$";
    /**邀请码匹配 */
    public static final String PATTERN_INVIT_CODE = "^(((1([3,4,5,7,8][0-9]))\\d{8})|(0\\d{2}-?\\d{8})|(0\\d{3}-?\\d{7,8}))$";

}

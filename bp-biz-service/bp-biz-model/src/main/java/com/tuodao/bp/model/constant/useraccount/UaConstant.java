package com.tuodao.bp.model.constant.useraccount;

/**
 * @description: 参数静态常量
 * @author: mif
 * @date: 2017/9/4
 * @time: 15:35
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UaConstant {

    /** 密码安级别（1：弱；2：强；3：最高）*/
    public final static String PW_SECURITY_LEVEL_ALL = "1|2|3";

    /** 用户类型（1：投资用户：2：融资用户；） */
    public final static String USER_TYPE_ALL = "1|2";

    /** 投资用户类型(1：普通用户；2：内部用户) */
    public final static String INVEST_USER_TYPE_ALL = "1|2";

    /**注册来源（0：后台；1：WEB；2：IOS；3：ANDROID；4：H5） */
    public final static String REGISTER_SOURCE_ALL = "0|1|2|3|4|5";

    /**
     * 邀请类型（1：专属链接；2：社交平台；3：邀请码）
     */
    public final static String INVITE_TYPE_ALL = "1|2|3";
    /**
     * 首次邀请投资奖励返现比利率
     */
    public final static Double FIRST_TIME_INVITE_RATE = 0.25d;

}

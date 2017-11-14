package com.tuodao.bp.model.constant.traningcenter;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/26 0026.
 * @time: 10:43
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TraningCenterExceptionConstant {

    //唯一id为空
    public static  final int JUST_ID_IS_NULL=149000;

    //投资金额为空
    public static final int  PRE_ACCOUNT_IS_NULL = 149001;

    //用户id为空
    public static final int  USER_ID_IS_NULL = 149002;

    //产品id为空
    public static final int  BORROW_ID_IS_NULL = 149003;

    //渠道为空
    public static final int  CHANNEL_IS_NULL = 149004;

    //总利率为空
    public static final int  ALL_APR_IS_NULL = 149005;

    //加息利率为空
    public static final int  VOUCHER_APR_IS_NULL = 149006;

    //平台奖励利率为空
    public static final int  AWARD_APR_IS_NULL = 149007;

    //0天标1月标为空
    public static final int  BORROW_TYPE_IS_NULL = 149008;

    //还款方式为空
    public static final int  RECOVER_TYPE_IS_NULL = 149009;

    //期限为空
    public static final  int PERIOD_IS_NULL=149010;

    //投资id为空
    public static final  int TENDER_ID_IS_NULL=149011;

    //基本利息为空
    public static final  int APR_IS_NULL=149012;

    //剩余期数为空
    public static final  int LEFT_PERIOD_IS_NULL=149013;

    //开始期数为空
    public static final  int START_PERIOD_IS_NULL=149014;

    //上期还款时间为空
    public static final  int REPAY_TIME_IS_NULL=149015;


    /**
     * 自动投标金额在100-10万之间
     */
    public static final int MIN_MAX_AUTO_TENDER_MONEY = 149200;

    /**
     * 自动投标期限在1-36月之间
     */
    public static final int MIN_MAX_AUTO_TENDER_PERIOD = 149201;

    /**
     * 单笔提现范围100-1000万元之间
     */
    public static final int MIN_MAX_CASH_MONEY = 149202;

    /**
     * 支付密码长度错误
     */
    public static final int PAY_PASSWORD = 149203;

    /**
     * 自动投标编号不能为空
     */
    public static final int AUTO_TENDER_ID_NOT_NULL = 149204;

    /**
     * 投标金额不能小于100元
     */
    public static final int TENDER_MONEY_UNDERSIZE = 149205;

    /**
     * 约标密码长度错误
     */
    public static final int ORDER_TENDER_PASSWORD = 149206;

    /**
     * 投标金额不能为空
     */
    public static final int TENDER_MONEY_IS_NULL = 149207;

    /**
     * 查询日期不能为空
     */
    public static final int TENDER_CALENDAR_IS_NULL = 149208;

    /**
     * 投标状态输入错误
     */
    public static final int TENDER_STATUS_ERROR = 149209;

    /**
     * 图形验证码输入错误
     */
    public static final int TENDER_CODE_ERROR = 149210;

    /**
     * 资金记录状态输入错误
     */
    public static final int ACCOUNT_LOG_STATUS_ERROR = 149211;
}

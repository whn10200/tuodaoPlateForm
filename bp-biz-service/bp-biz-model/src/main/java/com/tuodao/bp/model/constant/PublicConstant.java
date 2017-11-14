package com.tuodao.bp.model.constant;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: 公共常量
 * User: zkai
 * Date: 2017/9/21
 * Time: 10:29
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class PublicConstant {

    /**
     * 积分任务ID常量
     * 1:激活存管;2:关注微信公众号;3:首次成功充值;4:首次成功投标（非债权转让标）;5:首次投资等额本息散标
     * 6:首次设置自动投标,7:首次邀请好友注册成功;8:首次登录APP;9：首次积分兑换加息券；10:第二次投资（非债权转让标)
     * 11:签到积分;12:APP端手动投资;13:投资12个月以下标的;14:投资12个月及以上标的;15:投资天标;
     * 16:投资最多;17:扫尾;18:投资最多和扫尾
     */
    public static final Long TASK_ID_OPEN_DEPOSIT_ = 1L;
    public static final Long TASK_ID_FOLLOW_WECHAT = 2L;
    public static final Long TASK_ID_FIRST_RECHARGE = 3L;
    public static final Long TASK_ID_FIRST_INVEST = 4L;
    public static final Long TASK_ID_FIRST_FIXED_PAYMENT_MORTGAGE = 5L;
    public static final Long TASK_ID_FIRST_SET_AUTO_INVEST = 6L;
    public static final Long TASK_ID_FIRST_INVITE_REGISTER = 7L;
    public static final Long TASK_ID_FIRST_APP_LOGIN = 8L;
    public static final Long TASK_ID_FIRST_EXCHANGE_RATE_COUPON = 9L;
    public static final Long TASK_ID_SECOND_INVEST = 10L;
    public static final Long TASK_ID_CHECK_IN = 11L;
    public static final Long TASK_ID_APP_INVEST = 12L;
    public static final Long TASK_ID_INVEST_UNDER_12_MONTH = 13L;
    public static final Long TASK_ID_INVEST_ABOVE_12_MONTH = 14L;
    public static final Long TASK_ID_INVEST_DAILY = 15L;
    public static final Long TASK_ID_MOST_INVEST = 16L;
    public static final Long TASK_ID_ROUND_OFF = 17L;
    public static final Long TASK_ID_MOST_AND_ROUND_OFF = 18L;

    /**
     * 活动福利CODE
     * REGISTER:注册大礼包；V1_BIRTHDAY：V1生日礼包；V2_BIRTHDAY：V2生日礼包；V3_BIRTHDAY；V3生日礼包；V4_BIRTHDAY：V4生日礼包；
     * V5_BIRTHDAY：V5生日礼包；V6_BIRTHDAY：V6生日礼包；
     * V1_UP：V1升级大礼包；V2_UP；V2升级大礼包；V3_UP：V3升级大礼包；V4_UP：V4升级大礼包
     * V5_UP：V5升级大礼包；V6_UP：V6升级大礼包；V0_GIFT：V0福利
     * FRIEND_FIRST_INVEST:邀请好友首投奖励
     */
    public static final String WELFARE_ACTIVITY_CODE_REGISTER = "REGISTER";
    public static final String WELFARE_ACTIVITY_CODE_V1_BIRTHDAY = "V1_BIRTHDAY";
    public static final String WELFARE_ACTIVITY_CODE_V2_BIRTHDAY = "V2_BIRTHDAY";
    public static final String WELFARE_ACTIVITY_CODE_V3_BIRTHDAY = "V3_BIRTHDAY";
    public static final String WELFARE_ACTIVITY_CODE_V4_BIRTHDAY = "V4_BIRTHDAY";
    public static final String WELFARE_ACTIVITY_CODE_V5_BIRTHDAY = "V5_BIRTHDAY";
    public static final String WELFARE_ACTIVITY_CODE_V6_BIRTHDAY = "V6_BIRTHDAY";
    public static final String WELFARE_ACTIVITY_CODE_V1_UP = "V1_UP";
    public static final String WELFARE_ACTIVITY_CODE_V2_UP = "V2_UP";
    public static final String WELFARE_ACTIVITY_CODE_V3_UP = "V3_UP";
    public static final String WELFARE_ACTIVITY_CODE_V4_UP = "V4_UP";
    public static final String WELFARE_ACTIVITY_CODE_V5_UP = "V5_UP";
    public static final String WELFARE_ACTIVITY_CODE_V6_UP = "V6_UP";
    public static final String WELFARE_ACTIVITY_CODE_V0_GIFT = "V0_GIFT";
    public static final String WELFARE_ACTIVITY_CODE_FRIEND_FIRST_INVEST = "FRIEND_FIRST_INVEST";

    /**
     * VIP升级对应奖励ID
     */
    public static final Map<Integer, String> VIP_UP_MAP =
            Collections.unmodifiableMap(new HashMap<Integer, String>() {
                private static final long serialVersionUID = -2024310271491613599L;

                {
                    put(USER_VIP_LEVEL_0, WELFARE_ACTIVITY_CODE_V0_GIFT);
                    put(USER_VIP_LEVEL_1, WELFARE_ACTIVITY_CODE_V1_UP);
                    put(USER_VIP_LEVEL_2, WELFARE_ACTIVITY_CODE_V2_UP);
                    put(USER_VIP_LEVEL_3, WELFARE_ACTIVITY_CODE_V3_UP);
                    put(USER_VIP_LEVEL_4, WELFARE_ACTIVITY_CODE_V4_UP);
                    put(USER_VIP_LEVEL_5, WELFARE_ACTIVITY_CODE_V5_UP);
                    put(USER_VIP_LEVEL_6, WELFARE_ACTIVITY_CODE_V6_UP);
                }
            });

    /**
     * VIP等级对应生日奖励ID
     */
    public static final Map<Integer, String> VIP_BIRTHDAY_MAP =
            Collections.unmodifiableMap(new HashMap<Integer, String>() {
                private static final long serialVersionUID = -8336588426611402209L;

                {
                    put(USER_VIP_LEVEL_0, "");
                    put(USER_VIP_LEVEL_1, WELFARE_ACTIVITY_CODE_V1_BIRTHDAY);
                    put(USER_VIP_LEVEL_2, WELFARE_ACTIVITY_CODE_V2_BIRTHDAY);
                    put(USER_VIP_LEVEL_3, WELFARE_ACTIVITY_CODE_V3_BIRTHDAY);
                    put(USER_VIP_LEVEL_4, WELFARE_ACTIVITY_CODE_V4_BIRTHDAY);
                    put(USER_VIP_LEVEL_5, WELFARE_ACTIVITY_CODE_V5_BIRTHDAY);
                    put(USER_VIP_LEVEL_6, WELFARE_ACTIVITY_CODE_V6_BIRTHDAY);
                }
            });

    /**
     * VIP等级（0~6）
     */
    public static final int USER_VIP_LEVEL_0 = 0;
    public static final int USER_VIP_LEVEL_1 = 1;
    public static final int USER_VIP_LEVEL_2 = 2;
    public static final int USER_VIP_LEVEL_3 = 3;
    public static final int USER_VIP_LEVEL_4 = 4;
    public static final int USER_VIP_LEVEL_5 = 5;
    public static final int USER_VIP_LEVEL_6 = 6;

    /**
     * MQ 账户-交易资金变动类型
     */

    public static final String MQ_ACCOUNT_DEAL_TYPE_NORMAL_INVEST = "NORMAL_INVEST"; //投资
    public static final String MQ_ACCOUNT_DEAL_TYPE_WITH_THE_REVIEW = "WITH_THE_REVIEW"; //满标复审
    public static final String MQ_ACCOUNT_DEAL_TYPE_BACK_TO_ARTICLE = "BACK_TO_ARTICLE"; //回款
    public static final String MQ_ACCOUNT_DEAL_TYPE_RECHARGE = "RECHARGE"; //充值
    public static final String MQ_ACCOUNT_DEAL_TYPE_WITHDRAW = "WITHDRAW"; //提现
    public static final String MQ_ACCOUNT_DEAL_TYPE_REVOKE = "REVOKE"; //撤标
    public static final String MQ_ACCOUNT_DEAL_TYPE_BALANCE = "BALANCE"; //理财计划平账

    /**
     * 邀请奖励类型
     */
    public static final int INVITE_AWARD_TYPE_COUPON = 1; //优惠券
    public static final int INVITE_AWARD_TYPE_FIRST_TIME_INVEST = 2; //首投返现
    public static final int INVITE_AWARD_TYPE_RETURN_CASH = 3; //回款返现

    /**
     * 二元选择项 （1：是；0：否; 2:处理中 3:处理失败）
     */
    public final static int IF_YES = 1;
    public final static int IF_NO = 0;
    public final static int IF_ING = 2;
    public final static int IF_ERROR = 3;


    /**
     * 是否删除(0：已删除；1:正常)
     */
    public final static int DEL_YES = 0;
    public final static int DEL_NO = 1;

    /**
     * 推送对象 1:所有平台 2: 推送到对应别名的设备上 3:推送给安卓 4:推送给ios 5:推送到对应的组
     */
    public static final String PUSH_OJECT_ALL = "1";
    public static final String PUSH_OJECT_ALIA = "2";
    public static final String PUSH_OJECT_ANDROID = "3";
    public static final String PUSH_OJECT_IOS = "4";
    public static final String PUSH_OJECT_TAG = "5";

    /**
     * 推送方式（1：通知 2：透传）
     */
    public static final String PUSH_TYPE_NOTICE = "1";
    public static final String PUSH_TYPE_PASSTHROUGH = "2";

    /**
     * 通知模版类型（个推特有）
     * 1: 点击通知打开应用模板(ps:针对沉默用户，发送推送消息，点击消息栏的通知可直接激活启动应用，提升应用的转化率。)Notification Template
     * 2: 点击通知打开网页模板(ps:推送广促销活动，用户点击通知栏信息，直接打开到指定的促销活动页面，推送直接到达指定页面，免去了中间过程的用户流失。)LinkTemplate
     */
    public static final String PUSH_TEMPLATE_TYPE_NOTICE = "1";
    public static final String PUSH_TEMPLATE_TYPE_LINK = "2";


    /**
     * 短信/推送模版CODE
     */
    public static final String SMS_TEMP_REGISTER_CODE = "register_code";        //注册验证码
    public static final String SMS_TEMP_REGISTER_SUCCESS = "register_success";  //注册成功
    public static final String SMS_TEMP_FORGET_LOGIN_PW = "forget_login_pw";    //忘记登录密码
    public static final String SMS_TEMP_FORGET_PAY_PW = "forget_pay_pw";        //忘记支付密码
    public static final String SMS_TEMP_FEEDBACK = "feedback";                  //用户反馈短信验证
    public static final String SMS_TEMP_BIRTHDAY = "birthday";                  //生日祝福


    /**
     * 系统默认的creator,modifier
     */
    public static final String SYSTEM_CREATOR = "system";
    public static final String SYSTEM_MODIFIER = SYSTEM_CREATOR;
}

package com.tuodao.bp.operation.constant;

/**
 * @description: 运营中心相关常量
 * @author: zkai
 * @date: 2017/9/20
 * @time: 15:50
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class OperationBizConstant {
    /**
     * 业务代码(1：互金平台)
     */
    public final static int BUSSINESS_CODE_FINANCE = 1;


    /**
     * 性别（1：男；2：女）
     */
    public final static int MAN = 1;
    public final static int WOMAN = 2;

    /**
     * 积分类型(1:收入,2:消耗)
     */
    public final static int ACORE_TYPE_ADD = 1;
    public final static int ACORE_TYPE_SUBTRACT = 2;

    /**
     * 任务类型类型(1:日常任务,2:新手任务)
     */
    public final static int SCORE_TASK_DAILY_WORK = 1;
    public final static int SCORE_TYPE_NEWBIE_TASK = 2;

    /**
     * 是否连续签到 1:否 2:是
     */
    public final static String IS_CONTINUOUS_NO = "1";
    public final static String IS_CONTINUOUS_YES = "2";

    /**
     * 优惠券发放方式(1:后台主动发,2:前台事件触发,3:主动领)
     */
    public static final Integer DISCOUNT_STYLE_SEND = 1;
    public static final Integer DISCOUNT_STYLE_EVENT = 2;
    public static final Integer DISCOUNT_STYLE_ACTIVE = 3;

    /**
     * 类型(1:抵用券,2:加息券;3:提现次数)
     */
    public static final Integer DISCOUNT_TYPE_VOUCHER = 1;
    public static final Integer DISCOUNT_TYPE_COUPON = 2;
    public static final Integer DISCOUNT_TYPE_WITHDRAW = 3;

    /**
     * 优惠券状态（1：可使用，2：已使用，3：已过期）
     */
    public static final Integer DISCOUNT_STATUS_USABLE = 1;
    public static final Integer DISCOUNT_STATUS_USED = 2;
    public static final Integer DISCOUNT_STATUS_EXPIRED = 3;

    /**
     * 是否锁定(1:正常，2：锁定)
     */
    public static final Integer DISCOUNT_LOCK_1 = 1;
    public static final Integer DISCOUNT_LOCK_2 = 2;

    /**
     * 发放方式(1:后台主动发,2:前台事件触发,3:主动领)
     */
    public static final Integer DISCOUNT_STYLE_ADMIN = 1;
    public static final Integer DISCOUNT_LOCK_FROUNT = 2;
    public static final Integer DISCOUNT_LOCK_DRAW = 3;

    /**
     * 用户标签类型
     */
    public static final String USER_LABEL_A_1 = "A-1";
    public static final String USER_LABEL_A_2 = "A-2";
    public static final String USER_LABEL_A_3 = "A-3";
    public static final String USER_LABEL_B_1 = "B-1";
    public static final String USER_LABEL_B_2 = "B-2";
    public static final String USER_LABEL_B_3 = "B-3";
    public static final String USER_LABEL_C_1 = "C-1";
    public static final String USER_LABEL_C_2 = "C-2";
    public static final String USER_LABEL_C_3 = "C-3";

    /**
     * 用户标签任务编号
     */
    // 任务1 新手任务T+1天(认证 -- 激活存管)
    public static final Integer USER_LABEL_TASK_1 = 1;
    // 任务2 新手任务T+3天(首投)
    public static final Integer USER_LABEL_TASK_2 = 2;

    // 任务3 新手任务T+6天(下载APP（有过APP端启动或操作行为的）；关注微信；)
    // 首次登录APP
    public static final Integer USER_LABEL_TASK_3_1 = 3;
    // 关注微信公众号
    public static final Integer USER_LABEL_TASK_3_2 = 4;

    // 任务4 新手任务T+14(复投)
    public static final Integer USER_LABEL_TASK_4 = 5;
    // 任务6 新手任务T+19天(自动投标)
    public static final Integer USER_LABEL_TASK_6 = 6;
    // 任务7 新手任务T+24天(邀请好友)
    public static final Integer USER_LABEL_TASK_7 = 7;
    // 任务8 新手任务T+28天(首次投资等额本息散标)
    public static final Integer USER_LABEL_TASK_8 = 8;

    /**
     * 任务完成状态
     */
    public static final String TASK_IS_COMPLETE_YES = "yes";
    public static final String TASK_IS_COMPLETE_NO = "no";

    /**
     * 任务类型(1:日常任务,2:新手任务)
     */
    public static final int SCORE_TASK_TYPE_DAILY = 1;
    public static final int SCORE_TASK_TYPE_NEWBIE = 2;

    // 奖励类型：1  积分
    public static final int PRIZE_TYPE_JIFENG = 1;
    // 奖励类型：2  抵用券
    public static final int PRIZE_TYPE_DIYONG = 2;
    // 奖励类型：3  加息券
    public static final int PRIZE_TYPE_JIAXI = 3;
    // 奖励类型：4  实物
    public static final int PRIZE_TYPE_SHIWU = 4;


    // 礼品发放状态 1:未发放 2:已发放
    public static final String PRIZE_RESULT_STATUS_WEIFAFANG = "1";

    // 礼品发放状态 1:未发放 2:已发放
    public static final String PRIZE_RESULT_STATUS_YIFAFANG = "2";

    // 发放方式(1:后台主动发,2:前台事件触发,3:主动领)
    public static final int DISCOUNT_STYLE_HOUTAI = 1;
    // 发放方式(1:后台主动发,2:前台事件触发,3:主动领)
    public static final int DISCOUNT_STYLE_QIANTAI = 2;
    // 发放方式(1:后台主动发,2:前台事件触发,3:主动领)
    public static final int DISCOUNT_STYLE_ZHUDONG = 3;
    // 积分抽奖时间限制只有7天有效
    public static final int INVERSTMENT_DISCOUNT_EFFECT_DAY = 7;
    // 积分抽奖获取的券来源：来自积分抽奖
    public static final String INVERSTMENT_DISCOUNT_SOURCE = "来自积分抽奖";
}

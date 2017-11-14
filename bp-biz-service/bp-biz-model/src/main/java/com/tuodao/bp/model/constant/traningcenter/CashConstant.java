package com.tuodao.bp.model.constant.traningcenter;

/**
 * @description: 提现常量
 * @author: 王艳兵
 * @date: 2017/10/16
 * @time: 10:48
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CashConstant {

    /**
     * 提现申请中
     */
    public static final int CASH_STATUS_APPLY = 0;

    /**
     * 提现成功
     */
    public static final int CASH_STATUS_SUCCESS = 1;

    /**
     * 提现申请失败
     */
    public static final int CASH_STATUS_FAIL = 2;

    /**
     * 存管开通状态 0:未开通
     */
    public static final int DEPOSIT_STATUS_0 = 0;

    /**
     * 存管开通状态 1:已开通
     */
    public static final int DEPOSIT_STATUS_1 = 1;

    /**
     * 单次最大提现金额
     */
    public static final double MAX_CASH = 1000000000;

    /**
     * 默认单笔提现手续费 为3元 单位分
     */
    public static final double CASH_FEE = 300D;

    /**
     * 对于提现中未投资的 默认按0.5%收取
     */
    public static final double CASH_FEE_PERCENT = 0.005D;

    /**
     * 是否使用免费提现次数 1:使用  0:未使用
     */
    public static final int USE_FREE = 1;
}

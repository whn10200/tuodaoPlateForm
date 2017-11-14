package com.tuodao.bp.operation.constant;

/**
 * @description: 运营中心异常相关常量
 * @author: zkai
 * @date: 2017/9/20
 * @time: 15:50
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class OperationRespExceptionConstant {

	/** 记录条数不唯一 */
	public static final int DATA_NOT_ONLY = 160001;

	/** 事物异常 */
	public static final int TRANSACTION_EXCEPTION = 160002;

    /** 数据添加异常*/
    public static final int ADD_ERROR = 160003;

    /** 数据不存在*/
    public static final int DATA_NOT_EXIT = 160004;

    /** 数据修改异常*/
    public static final int UPDATE_ERROR = 160005;

    /** 积分任务被删除 */
    public static final int SCORE_TASK_IS_DEL = 160006;

    /** 此新手任务已完成 */
    public static final int NOVICE_TASK_BEEN_COMPLETED = 160007;

    /** 用户今日已签到 */
    public static final int USER_HAS_SIGNED_IN = 160008;

    /** 积分兑换传入积分数与计算不相匹配 */
    public static final int SCORE_EXCHANGE_SCORE_ERROR = 160009;

    /** 用户积分总数-数据有问题 */
    public static final int SCORE_EXCHANGE_USER_SCORE_ERROR = 160010;

    /** 用户积分可用积分小于兑换所需积分 */
    public static final int SCORE_EXCHANGE_USER_SCORE_LESS_THAN_CHANGE_ERROR = 160011;

    /** 用户运营数据-数据有问题 */
    public static final int SCORE_EXCHANGE_USER_OPERATION_DATA_ERROR = 160012;

    /** 用户积分可用积分小于抽奖所需积分 */
    public static final int INVERST_USER_SCORE_LESS_THAN_CHANGE_ERROR = 160013;

    /** 优惠券不存在 */
    public static final int USER_DISCOUNT_NOT_EXIST = 160014;

}

package com.tuodao.bp.activemq.constant;

/**
 * @description: MQ常量
 * @author: mif
 * @date: 2017/9/14
 * @time: 10:08
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class MqContstant {

     /*--------------------------- 用户账户中心消费队列----------------------------------*/
    /** 账户中心-交易中心资金队列 */
    public static final String ACCOUNT_DEAL_FINANCE_QUEUE = "ACCOUNT_DEAL_FINANCE_QUEUE";
    /** 存管信息*/
    public static final String ACCOUNT_DEPOSIT_QUEUE = "ACCOUNT_DEPOSIT_QUEUE";
    /** 累计收益明细*/
    public static final String ACCOUNT_INCOME_DETAIL_QUEUE = "ACCOUNT_INCOME_DETAIL_QUEUE";
    /** 首投、返现MQ*/
    public static final String ACCOUNT_FIRST_INVEST_OR_CASH_QUEUE = "ACCOUNT_FIRST_INVEST_OR_CASH_QUEUE";

    /*--------------------------- 用户账户中心消费队列----------------------------------*/

    /*--------------------------- 运营中心消费队列----------------------------------*/
    /** 邀请记录奖励队列*/
    public static final String OP_PAYBACK_INVITE_AWARD_QUEUE = "OP_PAYBACK_INVITE_AWARD_QUEUE";
    /** 首投奖励队列*/
    public static final String OP_FIRST_TIME_INVEST_AWARD_QUEUE = "OP_FIRST_TIME_INVEST_AWARD_QUEUE";
    /** 运营中心优惠券发放队列 */
    public static final String OP_COUPON_GRANT_QUEUE = "OP_COUPON_GRANT_QUEUE";
    /** 用户积分任务 */
    public static final String OP_SCORE_TASK_QUEUE = "OP_SCORE_TASK_QUEUE";
    /** 用户VIP升级队列 */
    public static final String OP_VIP_UP_MQ_QUEUE = "OP_VIP_UP_MQ_QUEUE";
    /** 优惠券状态变更队列 */
    public static final String OP_DISCOUNT_CHANGE_MQ_QUEUE = "OP_DISCOUNT_CHANGE_MQ_QUEUE";
    /** 优惠券状态变更成功失败异步通知队列 */
    public static final String OP_DISCOUNT_ASYNC_MQ_QUEUE = "OP_DISCOUNT_ASYNC_MQ_QUEUE";
    /** 免费提现次数变更队列 */
    public static final String OP_WITH_DRAW_TIMES_CHANGE_MQ_QUEUE = "OP_WITH_DRAW_TIMES_CHANGE_MQ_QUEUE";
    /*--------------------------- 运营中心消费队列----------------------------------*/

     /*--------------------------- 运营中心队列----------------------------------*/
    /** 定时任务钟发送消息队列*/
    public static final String TASK_TIMER_SEND_QUEUE = "TASK_TIMER_SEND_QUEUE_HC";
    /** 定时任务钟接收消息队列*/
    public static final String TASK_TIMER_RECEIVE_QUEUE = "TASK_TIMER_RECEIVE_QUEUE";
   /*--------------------------- 运营中心队列---------------------------------*/



   /*---------------------------  产品中心投资队列----------------------------------*/
    /** 产品中心-交易中心投资队列 */
    public static final String PRODUCT_TENDER_QUEUE = "PRODUCT_TENDER_QUEUE";
    /** 交易中心-产品中心投资响应队列 */
    public static final String PRODUCT_TENDER_RESPONSE_QUEUE = "PRODUCT_TENDER_RESPONSE_QUEUE";
    /** 产品中心 更新产品队列 */
    public static final String PRODUCT_UPDATE_QUEUE = "PRODUCT_UPDATE_QUEUE";
    /** 产品中心 释放理财计划债权队列 */
    public static final String RELEASE_DEBTS_QUEUE = "RELEASE_DEBTS_QUEUE";

     /*---------------------------  交易中心消费队列----------------------------------*/
    /** 资金记录消息队列*/
     public static final String TRANING_ACCOUNT_LOG_QUEUE = "TRANING_ACCOUNT_LOG_QUEUE";

    /** 加入理财计划消息队列*/
    public static final String TRANING_JOIN_CHOISE_QUEUE = "TRANING_JOIN_CHOISE_QUEUE";

    /** 理财计划下普通标的投标队列*/
    public static final String TRANING_ORGINAL_TENDER_QUEUE = "TRANING_ORGINAL_TENDER_QUEUE";

    /** 理财计划下转让标的投标队列*/
    public static final String TRANING_DEVELOP_TENDER_QUEUE = "TRANING_DEVELOP_TENDER_QUEUE";

    /** 理财计划复审队列*/
    public static final String TRANING_REVERIFY_PLAN_QUEUE = "TRANING_REVERIFY_PLAN_QUEUE";

    /** 理财计划下普通标的复审队列*/
    public static final String TRANING_REVERIFY_ORGINAL_QUEUE = "TRANING_REVERIFY_ORGINAL_QUEUE1";

    /** 理财计划下转让标的复审队列*/
    public static final String TRANING_REVERIFY_DEVELOP_QUEUE = "TRANING_REVERIFY_DEVELOP_QUEUE";

    /** 理财计划到期的操作队列*/
    public static final String TRANING_EXPIRED_QUEUE = "TRANING_EXPIRED_QUEUE";

    /**
     * 创建资金账户队列
     */
    public static final String TRANING_CREATE_ACCOUNT_QUEUE = "TRANING_CREATE_ACCOUNT_QUEUE";


    /** 网关充值队列*/
    public static final String TRANING_RECHARGE_GATE_QUEUE = "TRANING_RECHARGE_GATE_QUEUE";

    /** 快捷充值发送队列*/
    public static final String TRANING_RECHARGE_SMS_QUEUE = "TRANING_RECHARGE_SMS_QUEUE";

    /** 快捷充值队列*/
    public static final String TRANING_RECHARGE_FAST_QUEUE = "TRANING_RECHARGE_FAST_QUEUE";

     /*---------------------------  交易中心消费队列----------------------------------*/



}

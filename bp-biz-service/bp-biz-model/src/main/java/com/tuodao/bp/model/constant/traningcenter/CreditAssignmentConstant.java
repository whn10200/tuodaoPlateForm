package com.tuodao.bp.model.constant.traningcenter;

/**
 * @author qingli.chen
 * @date 2017/9/21
 * @description
 */
public class CreditAssignmentConstant {

    //无法查找到投资记录
    public static final int TENDER_IS_NULL = 142000;
    //该笔已经转让过
    public static final int TRANSFER_IS_READY = 142001;
    //金额需大于等于100元
    public static final int TRANSFER_MONEY_ERROR = 142002;
    //标的名称不能为空
    public static final int BORROW_NAME_IS_NULL = 142003;
    //状态不能为空
    public static final int STATUS_IS_NULL = 142004;
    //金额不能为空
    public static final int ACCOUNT_IS_NULL = 142005;
    //借款期限不能为空
    public static final int PERIOD_IS_NULL = 142006;
    //期限类型不能为空
    public static final int PERIOD_TYPE_IS_NULL = 142007;
    //年化率不能为空
    public static final int APR_IS_NULL = 142008;
    //还款方式不能为空
    public static final int RAYMENT_TYPE = 142009;
    //转让申请时间不能为空
    public static final int ADD_TIME_IS_NULL = 142010;
    //转让手续费不能为空
    public static final int FEE_IS_NULL = 142011;
    //支付密码错误
    public static final int PAY_PWD_ERROR = 142012;
    //请输入支付密码
    public static final int PAY_PWD_IS_NULL = 142013;
    //无效的债权
    public static final int TRANSFER_IS_NULL = 142014;
    //请选择开始时间
    public static final int BEGIN_TIME_IS_NULL = 142015;
    //请选择结束时间
    public static final int END_TIME_IS_NULL = 142016;
    //优惠券已被使用
    public static final int COUPONS_IS_USED = 142017;
    //该债权转让是你自己的，亲~
    public static final int TRANSFER_IS_OWNER = 142018;
    //未开通存管服务
    public static final int DEPOSITORY_IS_NOT_OPEN = 142019;
    //当前状态不可投标
    public static final int TRANSFER_CAN_NOT_TENDER = 142020;
    //当前债转已过期
    public static final int TRANSFER_IS_END = 142021;
    //该债权已满标
    public static final int TRANSFER_IS_FULL = 142022;
    //账户余额不足，请先充值
    public static final int INSUFFICIENT_BALANCE = 142023;
    //密码错误
    public static final int PWD_IS_ERROR = 142024;
    //已回款计划为空
    public static final int COLLECTION_SUCCESS_LIST_IS_EMPTY = 142025;
    public static final int COLLECTION_WAIT_LIST_IS_EMPTY = 142026;
    //回款计划为空
    public static final int COLLECTION_LIST_IS_EMPTY = 142027;
    //投资金额少于最少可投金额
    public static final int TENDER_TRANSFER_MONEY_ERROR = 142028;
    //投资金额大于可投金额
    public static final int TENDER_MORE_THAN_BALANCE = 142029;
    //投资失败
    public static final int TENDER_TRANSFER_FAILED = 142030;

}

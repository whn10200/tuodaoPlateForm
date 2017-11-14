package com.tuodao.bp.result.error;

/**
 * @description: 交易中心错误
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 10:09
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public enum TransactError {

    /**
     * 您未开通存管账户
     */
    BANK_STATUS_ERROR(140001,"您未开通存管账户"),
    /**
     * 资金账户不存在
     */
    ACCOUNT_NOT_FOUND(140002,"资金账户不存在"),
    /**
     * 可提现金额不足
     */
    CASH_MONEY_ERROR(140003,"可提现金额不足"),
    /**
     * 请求银行接口失败
     */
    BANK_REQUEST_ERROR(140004,"请求银行接口失败"),
    /**
     *
     */
    USER_LOCK_FROST(140005,"用户已被冻结或注销"),
    /**
     *
     */
    USER_TYPE_ERROR(140006,"用户不允许提现"),
    /**
     * 交易密码校验失败
     */
    USER_PAY_ERROR(140007,"交易密码校验失败"),
    /**
     * 更新提现记录失败
     */
    UPDATE_CASH_ERROR(140008,"更新提现记录失败"),
    /**
     * 投标验证码输入错误
     */
    TENDER_CODE_ERROR(140009,"投标验证码输入错误"),
    /**
     * 约标密码校验失败
     */
    ORDER_PASSWORD_ERROR(140011,"约标密码校验失败"),
    /**
     *
     */
    ORDER_APP_ERROR(140012,"此标仅限于APP端投资"),
    /**
     * 非募集中的标,无法投标
     */
    BORROW_STATUS_ERROR(140013,"非募集中的标,无法投标"),
    /**
     * 投标金额大于标的最大投标金额
     */
    MAX_TENDER_ERROR(140014,"投标金额大于标的最大投标金额"),
    /**
     * 投标金额小于标的最小投标金额
     */
    MIN_TENDER_ERROR(140015,"投标金额小于标的最小投标金额"),
    /**
     * 当前时间为预售时间
     */
    PRE_TENDER_ERROR(140016,"当前时间为预售时间"),
    /**
     *系统清算时间禁止投标
     */
    TENDER_TIME_ERROR(140017,"系统清算时间禁止投标"),
    /**
     * 标的信息不存在
     */
    BORROW_NOT_FOUND(140018,"标的信息不存在"),
    /**
     * 非投资用户
     */
    BORROWER_TENDER_ERROR(140019,"非投资用户"),
    /**
     * 账户可用余额不足
     */
    ACCOUNT_BALANCE_ERROR(140020,"账户可用余额不足"),
    /**
     * 该标为新手标
     */
    NOVICE_BORROW_ERROR(140021,"该标为新手标"),
    /**
     * 该标的已满标
     */
    TENDER_FULL_ERROR(140022,"该标的已满标"),
    /**
     * 用户vip等级不满足标的条件
     */
    VIP_TENDER_ERROR(140023,"用户vip等级不满足标的条件"),
    /**
     *天标不支持加息券
     */
    DAY_BORROW_ERROR(140024,"天标不支持加息券"),
    /**
     * 优惠券不存在
     */
    VOUCHER_NOT_FOUND(140025,"优惠券不存在"),
    /**
     * 优惠券无效
     */
    VOUCHER_USE_ERROR(140026,"优惠券无效"),
    /**
     * 优惠券不在有效期
     */
    VOUCHER_VALID_ERROR(140027,"优惠券不在有效期"),
    /**
     * 投标金额不满足优惠券使用额度
     */
    VOUCHER_MONEY_ERROR(140028,"投标金额不满足优惠券使用额度"),
    /**
     * 暂不支持该还款方式
     */
    SUPPORT_RECOVERTYPE(140029,"暂不支持该还款方式"),
    /**
     * 投标请求银行失败
     */
    TENDER_BANK_ERROR(140200,"投标请求银行失败"),
    /**
     * 投标信息获取失败
     */
    TENDER_NOT_FOUND(140201,"投标信息获取失败"),
    /**
     * 提现异常,请稍后再试
     */
    CASH_LOCK_ERROR(140202,"提现异常,请稍后再试"),
    /**
     * 标的还款请求银行失败
     */
    COLLECTION_ERROR(140203,"标的还款请求银行失败"),
    /**
     * 提现订单未查询到
     */
    CASH_ORDER_NOT_FOUND(140204,"提现订单未查询到"),
    /**
     * 回款信息未查询到
     */
    RECOVER_LIST_NOT_FOUND(140205,"回款信息未查询到"),
    /**
     * 交易中心未知异常
     */
    SYSTEM_ERROR(140206,"交易中心未知异常"),
    /**
     * 自动投标最小期限不能大于最大期限
     */
    AUTO_TENDER_PERIOD_ERROR(140207,"自动投标最小期限不能大于最大期限"),
    /**
     * 自动投标最小金额不能大于最大金额
     */
    AUTO_TENDER_MONEY_ERROR(140208,"自动投标最小金额不能大于最大金额"),
    /**
     * 自动投标设置异常
     */
    AUTO_TENDER_REDIS_ERROR(140209,"自动投标设置异常"),
    /**
     * 标的剩余可投金额不足
     */
    TENDER_NOT_ENOUGH(140210,"标的剩余可投金额不足"),
    /**
     * 单个标的只能投资一次
     */
    TENDER_MORE_ONE(140211,"单个标的只能投资一次"),
    /**
     * 标的满标或已被撤回
     */
    BORROW_FULL_OR_CANCEL(140212,"标的满标或已被撤回"),
    /**
     * 用户信息未查询到
     */
    USER_NOT_FOUND(140213,"用户信息未查询到"),
    /**
     * 投标撤回错误
     */
    TENDER_CANCEL_ERROR(140214,"投标撤回错误"),
    /**
     * 优惠券使用期限不匹配
     */
    VOUCHER_LIMIT_ERROR(140215,"优惠券使用期限不匹配"),
    /**
     * 借款人存管信息不存在
     */
    BORROW_DEPOSIT_NOT_FOUND(140216,"借款人存管信息不存在"),

    /**
     * 借款人信息未查询到
     */
    BORROWER_NOT_FOUND(140217,"借款人信息未查询到"),

    /**
     * 获取用户vip等级失败
     */
    USER_VIP_NOT_FOUND(140218,"获取用户vip等级失败"),

    /**
     * 精选计划投标未查询到
     */
    PLAN_TENDER_NOT_FOUND(140219,"精选计划投标未查询到"),

    /**
     * 资金账户充值金额错误
     */
    ACCOUNT_RECHARGE_ERROR(140220,"资金账户充值金额错误"),

    /**
     * 资金账户投标金额错误
     */
    ACCOUNT_TENDER_ERROR(140221,"资金账户投标金额错误"),

    /**
     * 资金账户提现金额错误
     */
    ACCOUNT_CASH_ERROR(140222,"资金账户提现金额错误"),

    /**
     * 资金账户待收本金错误
     */
    ACCOUNT_AWAIT_CAPITAL_ERROR(140223,"资金账户待收本金错误"),

    /**
     * 资金账户待收利息错误
     */
    ACCOUNT_AWAIT_INTEREST_ERROR(140224,"资金账户待收利息错误"),


    /**
     * 自动投标尚未开启,无需关闭
     */
    AUTO_TENDER_CLOSE_ERROR(140225,"自动投标尚未开启,无需关闭"),

    /**
     * 剩余可投金额小于最小投标金额时,需全部投出
     */
    TENDER_MONEY_MIN_ERROR(140226,"剩余可投金额小于最小投标金额时,需全部投出"),

    /**
     * 资金统计出错
     */
    ACCOUNT_COUNT_ERROR(140227,"资金统计出错"),

    /**
     * 满标复审调取北京银行接口失败
     */
    REVERIFY_RETRIEVE_ERROR(140228,"满标复审调取北京银行接口失败"),

    /**
     * 单次提现金额不能大于1000万
     */
    CASH_MAX_ERROR(140229,"单次提现金额不能大于1000万"),

    /**
     * 用户登陆超时
     */
    USER_TIME_OUT_ERROR(140230,"用户登陆超时"),

    /**
     * 资金账户可提现金额错误
     */
    ACCOUNT_BALANCE_CASH_ERROR(140231,"资金账户可提现金额错误"),

    /**
     * 资金账户可用金额错误
     */
    ACCOUNT_BALANCE_MINUS_ERROR(140232,"资金账户可用金额错误");


    private int code;

    private String msg;

    TransactError(int code , String msg){
        this.code = code;
        this.msg = msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}

package com.tuodao.bp.model.constant.traningcenter;

/**
 * @author qingli.chen
 * @date 2017/9/13
 * @description 充值常量
 */
public class RechargeConstant {
    //金额
    public static final int MONEY_IS_NULL = 141000;
    //充值渠道
    public static final int CHANNEL_IS_NULL = 141001;
    //订单号
    public static final int ORDER_NO_IS_NULL = 141002;
    //未查询到银行列表
    public static final int BANK_LIST_IS_NULL = 141003;
    //系统暂不支持该银行
    public static final int BANK_IS_NULL = 141004;
    //没有绑定银行卡
    public static final int BANK_IS_NOT_BIND = 141005;
    //请选择银行
    public static final int BANK_ID_IS_NULL = 141006;
    //没有可以充值的银行卡
    public static final int RECHARGE_CARD_IS_NOT_FOUND = 141007;
    //没有充值的渠道
    public static final int CHANNEL_NOT_FOUND = 141008;
    //请输入短信验证码
    public static final int SMS_CODE_IS_NULL = 141009;
    //验证码不合法
    public static final int SMS_CODE_IS_ERROR = 141010;
    //请勿重复提交订单
    public static final int COMMIT_ORDER = 141011;
    //未开通存管
    public static final int DEPOSIT_NOT_OPEN = 141012;
    //存管清算时间
    public static final int DEPOSIT_CLEAN_TIME = 141013;
    //请60秒后再发送验证码
    public static final int RECHARGE_TIME_ERROR = 141014;
}

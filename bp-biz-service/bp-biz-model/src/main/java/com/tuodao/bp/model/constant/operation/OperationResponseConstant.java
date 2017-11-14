package com.tuodao.bp.model.constant.operation;

/**
 * 运营中心返回错误代码
 * author hechuan
 * <p>
 * created on 2017/9/21
 * <p>
 * since V1.0.0
 */
public class OperationResponseConstant {

    /** 参数检查返回 - 开始 */

    // 运营中心用户优惠券类型不匹配(1:抵用券,2:加息券)
    public static final int PARAM_USER_DISCOUNT_TYPE_ERROR = 169001;

    // 运营中心用户优惠券状态不匹配（1：可使用，2：已使用，3：已过期）
    public static final int PARAM_USER_DISCOUNT_STATUS_ERROR = 169002;

    // 运营中心用户优惠券否锁定(1:正常，2：锁定)
    public static final int PARAM_USER_DISCOUNT_LOCK_ERROR = 169003;

    // 用户积分兑换数量不对为空
    public static final int PARAM_SCORE_EXCHANGE_NUM_ERROR = 169004;

    // 用户积分兑换积分不对为空
    public static final int PARAM_SCORE_EXCHANGE_SCORE_ERROR = 169005;

    // 用户积分兑换ID不对为空
    public static final int PARAM_SCORE_EXCHANGE_ID_ERROR = 169006;

    // 用户积分兑换类型不对为空
    public static final int PARAM_SCORE_EXCHANGE_TYPE_ERROR = 169007;

    // 用户积分兑换类型不匹配(1:加息券,2:免费提现次数)
    public static final int PARAM_SCORE_CHANGE_TYPE_MATCH_ERROR = 169008;

    // 抽奖种类不匹配（1：10积分，2：100积分）
    public static final int PARAM_SCORE_INVERST_TYPE_ERROR = 169009;

    // 抽奖积分不能为空
    public static final int PARAM_SCORE_INVERST_SCORE_ERROR = 169010;

    /** 参数检查返回 - 结束 */

    /** 业务异常 - 开始 */
    /** 业务异常 - 结束 */
}

package com.tuodao.bp.model.constant.operation;

/**
 * 用户优惠券常量类
 * author hechuan
 * <p>
 * created on 2017/9/20
 * <p>
 * since V1.0.0
 */
public class UserDiscountConstant {
    /**
     * 类型(1:抵用券,2:加息券)
     */
    public static final String DISCOUNT_TYPE_ALL="1|2";

    /**
     * 优惠券状态（1：可使用，2：已使用，3：已过期）
     */
    public static final String DISCOUNT_STATUS_ALL="1|2|3";

    /**
     * 是否锁定(1:正常，2：锁定)
     */
    public static final String DISCOUNT_LOCK_ALL="1|2";


    /**
     * 加息券状态 1:未使用
     */
    public static final int VOUCHER_STATUS_UNUSED = 1;

    /**
     * 加息券状态 2:已使用
     */
    public static final int VOUCHER_STATUS_USED = 2;

    /**
     * 券类型 使用抵用券
     */
    public static final int VOUCHER_TYPE = 1;

    /**
     * 券类型 使用加息券
     */
    public static final int COUPON_TYPE = 2;

    /**
     * 券类型 不使用券
     */
    public static final int DEFAULT_TYPE = 0;
}

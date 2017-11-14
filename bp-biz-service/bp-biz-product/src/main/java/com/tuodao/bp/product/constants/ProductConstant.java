package com.tuodao.bp.product.constants;

public class ProductConstant {
	/**
	 * 0：初始
	 */
	public static final Integer PRODUCT_STATUS_0 = 0;
	/**
	 * 1：被打回
	 */
	public static final Integer PRODUCT_STATUS_1 = 1;
	/**
	 * 2：被撤回
	 */
	public static final Integer PRODUCT_STATUS_2 = 2;
	/**
	 * 3：待审核
	 */
	public static final Integer PRODUCT_STATUS_3 = 3;
	/**
	 * 4：待发布
	 */
	public static final Integer PRODUCT_STATUS_4 = 4;
	/**
	 * 5：5：已发布（募集中或者定时）
	 */
	public static final Integer PRODUCT_STATUS_5 = 5;
	/**
	 * 6：满标待审
	 */
	public static final Integer PRODUCT_STATUS_6 = 6;
	/**
	 * 7：还款中
	 */
	public static final Integer PRODUCT_STATUS_7 = 7;
	/**
	 * 8：已还款
	 */
	public static final Integer PRODUCT_STATUS_8 = 8;
	/**
	 * 9：已还款（提前还款）
	 */
	public static final Integer PRODUCT_STATUS_9 = 9;
	/**
	 * 10：标的发布处理中 
	 */
	public static final Integer PRODUCT_STATUS_10 = 10;
	/**
	 * 11：标的撤销处理中
	 */
	public static final Integer PRODUCT_STATUS_11 = 11;

	/**
	 * 是否还有债权 可以匹配
	 */
	public static Boolean NO_DEBTS = false;

	/**
	 * 存管订单号前缀
	 */
	public static final String ORDER_NO = "fs_bidPub";

}

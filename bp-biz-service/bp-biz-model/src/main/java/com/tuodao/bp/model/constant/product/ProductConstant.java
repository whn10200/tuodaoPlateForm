package com.tuodao.bp.model.constant.product;

/**
 * @description: 产品参数静态常量
 * @author: wcj
 * @date: 2017/9/4
 * @time: 15:35
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ProductConstant {

	/**
	 * 标的字段中 否
	 */
	public final static Integer BORROW_NO = 0;
	/**
	 * 标的字段中 是
	 */
	public final static Integer BORROW_YES = 1;

	/**
	 * 标的类型 0：天标
	 */
	public final static int BORROW_TYPE_DAY = 0;
	/**
	 * 标的类型 0：月标
	 */
	public final static int BORROW_TYPE_MONTH = 1;

	/**
	 * 还款方式 0：等额本息
	 */
	public final static Integer AVERAGE_CAPITAL_PLUS_INTEREST = 0;
	/**
	 * 还款方式 1：按月付息
	 */
	public final static Integer MONTH_INTEREST = 1;

	/**
	 * 期限单位 0：天（day）
	 */
	public final static int PERIOD_UNIT_DAY = 0;
	/**
	 * 1：月（month）
	 */
	public final static int PERIOD_UNIT_MONTH = 1;
	/**
	 * 2：年（year）
	 */
	public final static int PERIOD_UNIT_YEAR = 2;

	/**
	 * 开启验证码
	 */
	public static final int IS_AUTH_CODE = 1;

	/**
	 * 开启app专享
	 */
	public static final int IS_APP = 1;

	/**
	 * 标的可投状态
	 */
	public static final int TENDER_STATUS = 5;

	/**
	 * 新手标: 0:非新手 1:新手
	 */
	public static final int NOVICE = 1;

	/**
	 * 标的收益计算方式:0:等额本息 1:按月付息
	 */
	public static final int REFUND_WAY = 0;

	/**
	 * 标的收益计算方式:0:等额本息 1:按月付息
	 */
	public static final int REFUND_WAY_MONTH = 1;

	/**
	 * 产品表中
	 */
	public static final String PRODUCT_BASE = "0|1|2";

	/**
	 * 产品类型 0:散标
	 */
	public static final int PRODUCT_TYPE_0 = 0;

	/**
	 * 产品类型 1:精选计划
	 */
	public static final int PRODUCT_TYPE_1 = 1;

	/**
	 * 标的类型 0:散标
	 */
	public static final int PRODUCT_TYPE_COMMON = 0;

	/**
	 * 1:精选计划
	 */
	public final static int PRODUCT_TYPE_PLAN = 1;

	/**
	 * 标的转让间隔 默认30天后能转让
	 */
	public static final int TRANSFER_INTERVAL = 30;

	/**
	 * 还款状态
	 **/
	public enum RepaymentStatus {
		Status1("未还", 0), Status2("已还", 1), Status3("提前还款", 2);
		public final int code;
		public final String value;

		private RepaymentStatus(String value, int code) {
			this.code = code;
			this.value = value;
		}

		public static String getValue(int code) {
			for (RepaymentStatus repaymentStatus : RepaymentStatus.values()) {
				if (repaymentStatus.code == code) {
					return repaymentStatus.value;
				}
			}
			return null;
		}
	}
	/**
	 * 存管订单号前缀
	 */
	public static final String ORDER_NO = "fs_bidPub";
}

package com.tuodao.bp.model.constant.depository;

/** order no. part */
public class ONP {

	/** 订单号部分，表示平台的通用头部 finance service */
	public static final String common = "fs";
	/** 订单号部分，四要素注册 */
	public static final String registe4Element = common + "_reg4Ele";
	/** 订单号部分，实名注册 */
	public static final String registeRealName = common + "_regRn";
	/** 订单号部分，绑卡 */
	public static final String boundCard = common + "_bandCard";
	/** 订单号部分，短验绑卡请求 */
	public static final String messageBoundCardApply = common + "_mesBcApp";
	/** 订单号部分，短验绑卡验证 */
	public static final String messageBoundCardValidate = common + "_mesBcVal";
	/** 订单号部分，用户信息修改 */
	public static final String userInfoChange = common + "_mesChg";
	/** 订单号部分，换卡 */
	public static final String changeCard = common + "_chgCard";

	/** 订单号部分，订单状态 */
	public static final String orderStatus = common + "_orderStatus";

	/** 订单号部分，平台转个人 */
	public static final String platformTrans = common + "_pltTrans";
	/** 订单号部分，平台子转 */
	public static final String platformConvers = common + "_pltCnvs";
	/** 订单号部分，平台充值 */
	public static final String platformRecharge = common + "_pltRchg";
	/** 订单号部分，平台提现 */
	public static final String platformWithdraw = common + "_pltWthdw";

	public static final String seekAccountBalanceDetail = common + "_seek_abd";
	public static final String seekBiddingBalance = common + "_seek_bb";
	public static final String seekBiddingBidDetail = common + "_seek_bbd";
	public static final String seekBiddingInfo = common + "_seek_bi";
	public static final String seekFundBalance = common + "_seek_fb";
	public static final String seekFundChangeDetail = common + "_seek_fcd";
	public static final String seekPlatBalanceDetail = common + "_seek_pbd";
	public static final String seekRechargeOrderStatus = common + "_seek_ros";
	public static final String seekRepayDetail = common + "_seek_rd";
}

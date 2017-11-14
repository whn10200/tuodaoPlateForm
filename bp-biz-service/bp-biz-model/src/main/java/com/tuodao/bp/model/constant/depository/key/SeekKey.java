package com.tuodao.bp.model.constant.depository.key;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.tuodao.bp.model.constant.depository.BJFN;

public class SeekKey {
	/**
	 * 4.6.1.资金变动明细查询
	 */
	public static List<String> fundChangeDetailRequest = Collections.unmodifiableList(Arrays.asList(BJFN.endDate,
			BJFN.orderNo, BJFN.pagenum, BJFN.pagesize, BJFN.platNo, BJFN.platCust, BJFN.startDate, BJFN.transName));
	/**
	 * 4.6.2.资金余额查询
	 */
	public static List<String> fundBalanceRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.platNo, BJFN.account));
	/**
	 * 4.6.3.还款明细查询
	 */
	public static List<String> repayDetailRequest = Collections.unmodifiableList(Arrays.asList(BJFN.endDate,
			BJFN.pagenum, BJFN.pagesize, BJFN.platNo, BJFN.platCust, BJFN.prodId, BJFN.startDate, BJFN.type));
	/**
	 * 4.6.4.标的投资明细查询
	 */
	public static List<String> biddingBidDetailRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.pagenum, BJFN.pagesize, BJFN.platNo, BJFN.prodId));
	/**
	 * 4.6.5.标的信息查询
	 */
	public static List<String> biddingInfoRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.platNo, BJFN.prodId));
	/**
	 * 4.6.6.账户余额明细查询
	 */
	public static List<String> accountBalanceDetailRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.account, BJFN.acctType, BJFN.fundType, BJFN.platNo));
	/**
	 * 4.6.8.订单状态查询
	 */
	public static List<String> orderStatusRequest = Collections.unmodifiableList(
			Arrays.asList(BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.queryOrderNo));
	/**
	 * 4.6.7.标的账户余额查询
	 */
	public static List<String> biddingBalanceRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.platNo, BJFN.prodId, BJFN.type));
	/**
	 *
	 */
	public static List<String> platBalanceDetailRequest = Collections.unmodifiableList(Arrays.asList());
	/**
	 * 4.6.9充值订单状态查询
	 */
	public static List<String> rechargeOrderStatusRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.occurBalance, BJFN.originalSerialNo, BJFN.platNo));

}

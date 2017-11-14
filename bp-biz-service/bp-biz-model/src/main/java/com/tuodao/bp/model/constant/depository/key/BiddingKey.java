package com.tuodao.bp.model.constant.depository.key;

import com.tuodao.bp.model.constant.depository.BJFN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BiddingKey {
	/**
	 * 4.3.1.标的发布
	 */
	public static List<String> publishRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.buyerNumLimit, BJFN.chargeoffAuto, BJFN.compensation, BJFN.createType,
					BJFN.cycle, BJFN.cycleUnit, BJFN.expireDate, BJFN.financingInfoList, BJFN.istYear, BJFN.orderNo,
					BJFN.overlimit, BJFN.overTotalLimit, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo,
					BJFN.prodId, BJFN.prodInfo, BJFN.prodName, BJFN.prodType, BJFN.remark, BJFN.repayType, BJFN.riskLvl,
					BJFN.sellDate, BJFN.totalLimit, BJFN.valueDate, BJFN.valueType));

	public static List<String> financingInfoList = Collections.unmodifiableList(Arrays.asList(BJFN.custNo, BJFN.regDate,
			BJFN.regTime, BJFN.financInt, BJFN.feeInt, BJFN.financPurpose, BJFN.useDate, BJFN.openBranch,
			BJFN.withdrawAccount, BJFN.accountType, BJFN.payeeName, BJFN.financAmt));

	/**
	 * 4.3.2.标的成废
	 */
	public static List<String> resultRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.flag, BJFN.funddata, BJFN.orderNo, BJFN.partnerTransDate,
					BJFN.partnerTransTime, BJFN.platNo, BJFN.prodId, BJFN.remark, BJFN.repayPlanList));

	/**
	 * 4.3.3.批量投标
	 */
	public static List<String> batchBidRequest = Collections.unmodifiableList(Arrays.asList(BJFN.data, BJFN.orderNo,
			BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.prodId, BJFN.totalNum));
	public static List<String> BidRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.commission, BJFN.couponAmt, BJFN.detailNo, BJFN.experienceAmt,
					BJFN.inInterest, BJFN.platCust, BJFN.remark, BJFN.selfAmt, BJFN.subjectPriority, BJFN.transAmt));

	/**
	 * 4.3.4.成标出账
	 */
	public static List<String> chargeOffRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.chargeOffList, BJFN.notifyUrl, BJFN.orderNo, BJFN.partnerTransDate,
					BJFN.partnerTransTime, BJFN.payCode, BJFN.platNo, BJFN.prodId, BJFN.remark));

	/**
	 * 4.3.9.成标出账通知
	 */
	public static List<String> chargeOffReceiveRequest = Collections.unmodifiableList(Arrays.asList(BJFN.errorInfo,
			BJFN.errorNo, BJFN.hostReqSerialNo, BJFN.openBranch, BJFN.orderNo, BJFN.orderStatus, BJFN.outAmt,
			BJFN.payFinishDate, BJFN.payFinishTime, BJFN.payeeName, BJFN.platNo, BJFN.platCust, BJFN.withdrawAccount));

	/**
	 * 4.3.5.标的转让
	 */
	public static List<String> transferDebtRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.commission, BJFN.commissionExt, BJFN.couponAmt, BJFN.dealAmount,
					BJFN.dealPlatcust, BJFN.incomeAcct, BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime,
					BJFN.platNo, BJFN.platCust, BJFN.prodId, BJFN.publishDate, BJFN.relatedProdIds, BJFN.remark,
					BJFN.subjectPriority, BJFN.transAmt, BJFN.transDate, BJFN.transShare, BJFN.transferIncome));

	/**
	 * 4.3.6.标的还款
	 */
	public static List<String> repayRequest = Collections.unmodifiableList(
			Arrays.asList(BJFN.funddata, BJFN.isPayoff, BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime,
					BJFN.platNo, BJFN.prodId, BJFN.remark, BJFN.repayFlag, BJFN.repayNum, BJFN.transAmt));

	/**
	 * 4.3.7.借款人还款计划更新
	 */
	public static List<String> repayPlaneUpdateRequest = Collections.unmodifiableList(Arrays.asList(BJFN.orderNo,
			BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.prodId, BJFN.remark, BJFN.repayPlanList));

	/**
	 * 4.3.8.标的出账信息修改
	 */
	public static List<String> chargeOffChangeRequest = Collections.unmodifiableList(
			Arrays.asList(BJFN.accountType, BJFN.openBranch, BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime,
					BJFN.payeeName, BJFN.platNo, BJFN.prodId, BJFN.remark, BJFN.withdrawAccount));

	/**
	 * 4.3.10.标的转让（批量）
	 */
	public static List<String> batchTransferDebtRequest = Collections.unmodifiableList(Arrays.asList(BJFN.data,
			BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.platCust));
	public static List<String> dataTransferDebtRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.prodId, BJFN.transferData));
	public static List<String> transferDataTransferDebtRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.commission, BJFN.commissionExt, BJFN.couponAmt, BJFN.dealAmount,
					BJFN.dealPlatcust, BJFN.detailNo, BJFN.incomeAcct, BJFN.publishDate, BJFN.remark,
					BJFN.subjectPriority, BJFN.transAmt, BJFN.transDate, BJFN.transShare, BJFN.transferIncome));
	
	public static List<String> commission = Collections
			.unmodifiableList(Arrays.asList(BJFN.payoutAmt,BJFN.payoutPlatType));
	
	public static List<String> commissionExt = Collections
			.unmodifiableList(Arrays.asList(BJFN.payoutAmt,BJFN.payoutPlatType));
	
	

}

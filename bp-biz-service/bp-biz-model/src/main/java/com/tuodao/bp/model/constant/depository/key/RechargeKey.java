package com.tuodao.bp.model.constant.depository.key;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.tuodao.bp.model.constant.depository.BJFN;

public class RechargeKey {
	/**
	 * 4.4.1.网关充值请求
	 */
	public static List<String> gatewayRechargeRequest = Collections.unmodifiableList(Arrays.asList(BJFN.amt,
			BJFN.bankcode, BJFN.cardNo, BJFN.cardType, BJFN.chargeType, BJFN.clientName, BJFN.currencyCode, BJFN.idKind,
			BJFN.idNo, BJFN.notifyUrl, BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.partnerUserid,
			BJFN.payCode, BJFN.platNo, BJFN.platCust, BJFN.remark, BJFN.returnUrl, BJFN.type, BJFN.userInfoDtIp,
			BJFN.userInfoDtRegister, BJFN.userInfoIdentifyState, BJFN.userInfoIdentifyType));

	/**
	 * 4.4.2.充值回调通知
	 */
	public static List<String> rechargeReceiveRequest = Collections.unmodifiableList(
			Arrays.asList(BJFN.errorInfo, BJFN.errorNo, BJFN.hostReqSerialNo, 
					BJFN.orderAmt, BJFN.orderNo, BJFN.orderStatus, BJFN.payAmt, BJFN.payFinishDate, BJFN.payFinishTime,
					BJFN.platNo, BJFN.platCust, BJFN.signdata, BJFN.transDate, BJFN.transTime, BJFN.type));

	/**
	 * 4.4.3.快快捷充值请求
	 */
	public static List<String> quickRechargeApplyRequest = Collections.unmodifiableList(Arrays.asList(BJFN.amt,
			BJFN.branchFlag, BJFN.cardNo, BJFN.cardType, BJFN.accountType, BJFN.currencyCode, BJFN.email,
			BJFN.idCode, BJFN.idType, BJFN.mobile, BJFN.name, BJFN.notifyUrl, BJFN.orderNo, BJFN.partnerTransDate,
			BJFN.partnerTransTime, BJFN.payCode, BJFN.platNo, BJFN.platCust, BJFN.remark));
	/**
	 * 4.4.4.快捷充值确认
	 */
	public static List<String> quickRechargeConfirmRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.identifyingCode, BJFN.originOrderNo, BJFN.partnerTransDate,
					BJFN.partnerTransTime, BJFN.payCode, BJFN.platNo, BJFN.remark));
	/**
	 * 4.4.7.借款人线下还款
	 */
	public static List<String> borrowCutRepayRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.amt, BJFN.notifyUrl, BJFN.orderNo, BJFN.partnerTransDate,
					BJFN.partnerTransTime, BJFN.platNo, BJFN.platcustlist, BJFN.remark));
	public static List<String> eleBorrowCutRepayRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.amt, BJFN.platCust));

}

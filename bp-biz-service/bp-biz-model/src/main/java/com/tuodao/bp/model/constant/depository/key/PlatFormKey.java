package com.tuodao.bp.model.constant.depository.key;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.tuodao.bp.model.constant.depository.BJFN;

public class PlatFormKey {
	/**
	 * 4.7.1.资金冻结解冻
	 */
	public static List<String> fundBlockAndDeblockRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.amount, BJFN.freezeFlg, BJFN.freezeOrderNo, BJFN.orderNo,
					BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.platCust, BJFN.remark));
	/**
	 * 4.7.2.平台不同子账户转账
	 */
	public static List<String> platformConverseRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.amt, BJFN.destAccount, BJFN.orderNo, BJFN.partnerTransDate,
					BJFN.partnerTransTime, BJFN.platNo, BJFN.remark, BJFN.sourceAccount));
	/**
	 * 4.7.3.平台充值
	 */
	public static List<String> platformRechargeRequest = Collections.unmodifiableList(Arrays.asList(BJFN.amount,
			BJFN.notifyUrl, BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.remark));
	/**
	 * 4.7.10.平台转帐个人（新）
	 */
	public static List<String> platformTransferRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.amount, BJFN.causeType, BJFN.orderNo, BJFN.partnerTransDate,
					BJFN.partnerTransTime, BJFN.platAccount, BJFN.platNo, BJFN.platCust, BJFN.remark));
	/**
	 * 4.7.4.平台提现
	 */
	public static List<String> platformWithDrawRequest = Collections.unmodifiableList(Arrays.asList(BJFN.amount,
			BJFN.notifyUrl, BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.remark));

}

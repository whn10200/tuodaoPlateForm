package com.tuodao.bp.model.constant.depository.key;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.tuodao.bp.model.constant.depository.BJFN;

public class WithdrawKey {
	/**
	 * 4.5.1.批量提现
	 */
	public static List<String> batchApplyWithdrawCashRequest = Collections.unmodifiableList(Arrays.asList(BJFN.data,
			BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.totalNum));
	public static List<String> applyWithdrawCashRequest = Collections.unmodifiableList(Arrays.asList(BJFN.amt,
			BJFN.bankId, BJFN.brabankName, BJFN.cityCode, BJFN.clientProperty, BJFN.detailNo, BJFN.feeAmt,
			BJFN.isAdvance, BJFN.notifyUrl, BJFN.payCode, BJFN.platCust, BJFN.remark, BJFN.withdrawType));

	/**
	 * 4.5.1.提现通知
	 */
	public static List<String> withDrawApplyReceive = Collections
			.unmodifiableList(Arrays.asList(BJFN.errorInfo, BJFN.errorNo, BJFN.hostReqSerialNo, BJFN.orderAmt,
					BJFN.orderNo, BJFN.orderStatus, BJFN.payAmt, BJFN.payFinishDate, BJFN.payFinishTime,
					BJFN.payOrderNo, BJFN.platNo, BJFN.platCust, BJFN.transDate, BJFN.transTime));

}

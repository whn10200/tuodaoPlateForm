package com.tuodao.bp.model.constant.depository.key;

import com.tuodao.bp.model.constant.depository.BJFN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BorrowerKey {
	/**
	 * 4.2.1.借款人批量还款
	 */
	public static List<String> batchPayBakRequest = Collections.unmodifiableList(Arrays.asList(BJFN.data, BJFN.orderNo,
			BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.totalNum));
	public static List<String> PayBakRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.detailNo, BJFN.feeAmt, BJFN.platCust, BJFN.prodId, BJFN.realRepayAmt,
					BJFN.realRepayDate, BJFN.remark, BJFN.repayAmt, BJFN.repayDate, BJFN.repayNum, BJFN.transAmt));
	/**
	 * 4.2.2.标的代偿（委托）还款
	 */
	public static List<String> biddingCompensationRequest = Collections.unmodifiableList(Arrays.asList(
			BJFN.compensationPlatcust, BJFN.feeAmt, BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime,
			BJFN.platNo, BJFN.platCust, BJFN.prodId, BJFN.realRepayAmt, BJFN.realRepayDate, BJFN.remark, BJFN.repayAmt,
			BJFN.repayDate, BJFN.repayNum, BJFN.repayType, BJFN.transAmt));
	/**
	 * 4.2.3.借款人还款代偿（委托）
	 */
	public static List<String> borrowerCompensationRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.compensationPlatcust, BJFN.orderNo, BJFN.partnerTransDate,
					BJFN.partnerTransTime, BJFN.platNo, BJFN.platCust, BJFN.prodId, BJFN.remark, BJFN.repayAmt));

}

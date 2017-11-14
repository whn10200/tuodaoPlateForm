package com.tuodao.bp.model.constant.depository.key;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.CFN;

public class UserKey {
	/**
	 * 4.1.1.批量开户(四要素绑卡)
	 */
	public static final List<String> batchRegist4EleRequest = Collections.unmodifiableList(Arrays.asList(BJFN.data,
			BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.totalNum));

	public static final List<String> regist4EleRequest = Collections.unmodifiableList(Arrays.asList(BJFN.birthday,
			BJFN.cardNo, BJFN.cardType, BJFN.cusType, BJFN.detailNo, BJFN.email, BJFN.idCode, BJFN.idType, BJFN.mobile,
			BJFN.name, BJFN.notifyUrl, BJFN.openBranch, BJFN.payCode, BJFN.preMobile, BJFN.remark, BJFN.sex));
	/**
	 * 4.1.2.批量开户（实名认证）
	 */
	public static List<String> batchRegistRealnameRequest = Collections.unmodifiableList(Arrays.asList(BJFN.data,
			BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.totalNum));

	public static List<String> registRealnameRequest = Collections.unmodifiableList(
			Arrays.asList(BJFN.bankLicense, BJFN.birthday, BJFN.businessLicense, BJFN.cusType, BJFN.detailNo,
					BJFN.email, BJFN.idCode, BJFN.mobile, BJFN.name, BJFN.orgName, BJFN.remark, BJFN.sex));

	/*
	 * public static final Map<String,List<String>> brrrRequestPackage = new
	 * HashMap<String,List<String>>(){ private static final long
	 * serialVersionUID = -5839232266135504470L;
	 * 
	 * { put(BJFN.mainBusiness, batchRegistRealnameRequest); put(BJFN.data,
	 * registRealnameRequest); }};
	 * 
	 * public static final Map<String,List<String>>
	 * batchRegistRealnameRequestPackage =
	 * Collections.unmodifiableMap(br4eRequestPackage);
	 */
	/**
	 * 4.1.4.批量换卡（解绑+换卡申请）
	 */
	public static List<String> batchChangeCardRequest = Collections.unmodifiableList(Arrays.asList(BJFN.data,
			BJFN.orderNo, BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.totalNum));

	public static List<String> changeCardRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.cardNo, BJFN.cardNoOld, BJFN.cardType, BJFN.cardTypeOld, BJFN.detailNo,
					BJFN.mobile, BJFN.name, BJFN.payCode, BJFN.platCust, BJFN.remark));
	/**
	 * 4.1.6.短验绑卡（可包含开户）申请
	 */
	public static List<String> messageBoundCardApplyRequest = Collections.unmodifiableList(Arrays.asList(BJFN.cardNo,
			BJFN.cardType, BJFN.idCode, BJFN.idType, BJFN.name, BJFN.orderNo, BJFN.partnerTransDate,
			BJFN.partnerTransTime, BJFN.payCode, BJFN.platNo, BJFN.platCust, BJFN.preMobile, BJFN.remark));
	/**
	 * 4.1.7.短验绑卡（可包含开户）确认
	 */
	public static List<String> messageBoundCardValidateRequest = Collections
			.unmodifiableList(Arrays.asList(BJFN.cardNo, BJFN.cardType, BJFN.idCode, BJFN.idType, BJFN.identifyingCode,
					BJFN.name, BJFN.orderNo, BJFN.originOrderNo, BJFN.partnerTransDate, BJFN.partnerTransTime,
					BJFN.payCode, BJFN.platNo, BJFN.platCust, BJFN.preMobile, BJFN.remark));

	public static List<String> boundCardRequest = Collections.unmodifiableList(Arrays.asList());
	/**
	 * 4.1.8.客户信息变更
	 */
	public static List<String> updateInfoRequest = Collections.unmodifiableList(Arrays.asList(BJFN.bankLicense,
			BJFN.businessLicense, BJFN.cusType, BJFN.email, BJFN.mobile, BJFN.notifyUrl, BJFN.orderNo, BJFN.orgName,
			BJFN.partnerTransDate, BJFN.partnerTransTime, BJFN.platNo, BJFN.platCust));
}

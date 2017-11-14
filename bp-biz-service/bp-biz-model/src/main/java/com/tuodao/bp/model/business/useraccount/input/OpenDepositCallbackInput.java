package com.tuodao.bp.model.business.useraccount.input;

import java.util.HashMap;

import javax.validation.constraints.Pattern;

import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.RegexpConstant;
import com.tuodao.bp.model.constant.depository.CFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import com.tuodao.bp.model.depository.DepositoryParaPojo;

/**
 * Description: 激活存管回调输入类 User: zkai Date: 2017/10/16 Time: 17:27
 * Copyright:拓道金服</br>
 * 修改：杨超凯 Date: 2017/10/26 Copyright (c) 2017
 */
public class OpenDepositCallbackInput extends DepositoryParaPojo {

	private static final long serialVersionUID = -3286422541317669339L;

	/**
	 * 用户编号
	 */
	private String userId;

	/**
	 * 存管编号
	 */
	private String depositNo;

	/**
	 * 平台编号
	 */
	private String platNo;

	/**
	 * 银行卡号
	 */
	@Pattern(regexp = RegexpConstant.PATTERN_BANK_NUM_REGEXP, message = UaParamExceptionConstant.BANK_NUM_FORMAT_ERROR
			+ "")
	private String cardNo;

	/**
	 * 预留手机号
	 */
	@PhoneNum(message = UaParamExceptionConstant.RESERVATION_MOBILE_FORMAT_ERROR + "")
	private String preMobile;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDepositNo() {
		return depositNo;
	}

	public void setDepositNo(String depositNo) {
		this.depositNo = depositNo;
	}

	public String getCardNo() {
		return cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getPreMobile() {
		return preMobile;
	}

	public void setPreMobile(String preMobile) {
		this.preMobile = preMobile;
	}

	public String getPlatNo() {
		return platNo;
	}

	public void setPlatNo(String platNo) {
		this.platNo = platNo;
	}

	// 回调对象，无需toHashMap
	@Override
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> map = super.toHashMap();
		return map;
	}

	// 如果重复开户，北京银行不会回传身份证！
	@Override
	public void fromHashMap(HashMap<String, String> map) {
		super.fromHashMap(map);
		setUserId(map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.userId));
		setDepositNo(map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.depositNo));
		setCardNo(map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.bankNum));
		setPreMobile(map.get(TDFN.subbean + CFN._eleln_(0) + TDFN.reservationMobile));
	}

}

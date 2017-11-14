package com.tuodao.bp.model.business.useraccount.input;

import java.util.HashMap;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.tuodao.bp.model.annotation.IDCard;
import com.tuodao.bp.model.annotation.Md5;
import com.tuodao.bp.model.annotation.PhoneNum;
import com.tuodao.bp.model.constant.RegexpConstant;
import com.tuodao.bp.model.constant.depository.CFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import com.tuodao.bp.model.depository.DepositoryParaPojo;

import net.logstash.logback.encoder.org.apache.commons.lang.StringUtils;

/**
 * @description: 存管开通请求类
 * @author: mif
 * @date: 2017/8/28
 * @time: 15:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class OpenDepositInput extends DepositoryParaPojo {

	private static final long serialVersionUID = -7374026360076396604L;

	HashMap<String, String> hm = new HashMap<String, String>();

	/**
	 * 真实姓名
	 */
	@NotBlank(message = UaParamExceptionConstant.REAL_NAME_CAN_NOT_BE_BLANK + "")
	@Size(max = 10, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
	private String realName;

	/**
	 * 身份证号码
	 */
	@NotBlank(message = UaParamExceptionConstant.ID_CARD_CAN_NOT_BE_BLANK + "")
	@IDCard(message = UaParamExceptionConstant.ID_CARD_FORMAT_ERROR + "")
	private String idCard;

	/**
	 * 银行编码
	 */
	@NotBlank(message = UaParamExceptionConstant.BANK_CODE_CAN_NOT_BE_BLANK + "")
	private String bankCode;

	/**
	 * 银行卡号
	 */
	@NotBlank(message = UaParamExceptionConstant.BANK_NUM_CAN_NOT_BE_BLANK + "")
	@Pattern(regexp = RegexpConstant.PATTERN_BANK_NUM_REGEXP, message = UaParamExceptionConstant.BANK_NUM_FORMAT_ERROR
			+ "")
	private String bankNum;

	/**
	 * 预留手机号码
	 */
	@NotBlank(message = UaParamExceptionConstant.RESERVATION_MOBILE_CAN_NOT_BE_NULL + "")
	@PhoneNum(message = UaParamExceptionConstant.RESERVATION_MOBILE_FORMAT_ERROR + "")
	private String reservationMobile;

	/**
	 * 支付密码
	 */
	@NotBlank(message = UaParamExceptionConstant.PAY_PASSWORD_CAN_NOT_BE_BLANK + "")
	@Md5(message = UaParamExceptionConstant.PASS_WORD_MUST_BE_MD5 + "")
	private String payPassword;

	/**
	 * 添加人
	 */
	@Size(max = 10, message = UaParamExceptionConstant.PARAM_BEYOND_THE_LENGTH + "")
	private String gmtCreator;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getReservationMobile() {
		return reservationMobile;
	}

	public void setReservationMobile(String reservationMobile) {
		this.reservationMobile = reservationMobile;
	}

	public String getPayPassword() {
		return payPassword;
	}

	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}

	public String getGmtCreator() {
		return StringUtils.isBlank(this.gmtCreator) ? realName : gmtCreator;
	}

	public void setGmtCreator(String gmtCreator) {
		this.gmtCreator = gmtCreator;
	}

	@Override
	public String toString() {
		return "OpenDepositInput{" + "realName='" + realName + '\'' + ", idCard='" + idCard + '\'' + ", bankCode='"
				+ bankCode + '\'' + ", bankNum='" + bankNum + '\'' + ", reservationMobile='" + reservationMobile + '\''
				+ ", payPassword='" + payPassword + '\'' + ", gmtCreator='" + gmtCreator + '\'' + "} "
				+ super.toString();
	}

	@Override
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> map = super.toHashMap();
		map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.userId, getUserId());
		map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.name, getRealName());
		map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.reservationMobile, getReservationMobile());
		map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.idCard, getIdCard());
		map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.bankNum, getBankNum());
		map.put(TDFN.subbean + CFN._eleln_(0) + TDFN.mobile, getMobile());
		return map;
	}

	// 请求参数，无需fromHashMap
	@Override
	public void fromHashMap(HashMap<String, String> map) {
		super.fromHashMap(map);

	}
}

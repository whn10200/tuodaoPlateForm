package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.depository.DepositoryPara;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @description: 提现异步回调
 * @author: 王艳兵
 * @date: 2017/10/16
 * @time: 10:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashMqInfo extends DepositoryPara implements Serializable {

	/**
	 * 提现金额
	 */
	private String amt;

	/**
	 * 提现手续费
	 */
	private String feeAmt;

	private String userId;

	public String getAmt() {
		return amt;
	}

	public void setAmt(String amt) {
		this.amt = amt;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> map = super.toHashMap();
		map.put(TDFN.account, getAmt());
		map.put(TDFN.fee, getFeeAmt());
		map.put(TDFN.userId, getUserId());
		return map;
	}

	@Override
	public void fromHashMap(HashMap<String, String> map) {
		super.fromHashMap(map);
		setAmt(map.get(TDFN.account));
		setFeeAmt(map.get(TDFN.fee));
		setUserId(map.get(TDFN.userId));
	}
}

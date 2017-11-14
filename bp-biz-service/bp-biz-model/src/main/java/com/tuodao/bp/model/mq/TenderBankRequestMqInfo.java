package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.depository.DepositoryPara;
import com.tuodao.bp.utils.BDC;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @description: 投标请求银行MQ参数
 * @author: 王艳兵
 * @date: 2017/10/19
 * @time: 11:06
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderBankRequestMqInfo extends DepositoryPara implements Serializable {

	private String userId;

	/**
	 * 产品编号
	 */
	private String productCode;

	/**
	 * 投标金额
	 */
	private double preAccount;

	/**
	 * 实际投标金额
	 */
	private double account;

	/**
	 * 抵用券面值
	 */
	private double voucherMoney;

	/**
	 * 加息券面值%
	 */
	private double voucherCouponMoney;

	/**
	 * 订单号:在投标冻结时已经生成时已经生成
	 */
	private String orderId;

	/**
	 * 查询结果
	 */
	private String tenderKey;

	public double getVoucherCouponMoney() {
		return voucherCouponMoney;
	}

	public void setVoucherCouponMoney(double voucherCouponMoney) {
		this.voucherCouponMoney = voucherCouponMoney;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "TenderBankRequestMqInfo [userId=" + userId + ", productCode=" + productCode + ", preAccount="
				+ preAccount + ", account=" + account + ", voucherMoney=" + voucherMoney + ", orderId=" + orderId
				+ ", tenderKey=" + tenderKey + "]";
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public double getPreAccount() {
		return preAccount;
	}

	public void setPreAccount(double preAccount) {
		this.preAccount = preAccount;
	}

	public double getAccount() {
		return account;
	}

	public void setAccount(double account) {
		this.account = account;
	}

	public double getVoucherMoney() {
		return voucherMoney;
	}

	public void setVoucherMoney(double voucherMoney) {
		this.voucherMoney = voucherMoney;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTenderKey() {
		return tenderKey;
	}

	public void setTenderKey(String tenderKey) {
		this.tenderKey = tenderKey;
	}

	@Override
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> map = super.toHashMap();
		/*map.put(TDFN.inInterest, new BDC(getVoucherCouponMoney()).toS0Bd().toString());
		map.put(TDFN.userId, getUserId());
		map.put(TDFN.prodId, getProductCode());
		map.put(TDFN.couponAmt, new BDC(getPreAccount()).toS0Bd().toString());
		map.put(TDFN.selfAmt, new BDC(getAccount()).toS0Bd().toString());
		map.put(TDFN.couponAmt, new BDC(getVoucherMoney()).toS0Bd().toString());
		map.put(TDFN.orderNo, getOrderId());
		//map.put(TDFN.tenderKey, getTenderKey());
*/		return map;
	}

	@Override
	public void fromHashMap(HashMap<String, String> map) {
		super.fromHashMap(map);
		/*if (map.get(TDFN.inInterest) != null & map.get(TDFN.inInterest).length() > 0)
			setVoucherCouponMoney(new Double(map.get(TDFN.inInterest)));
		setUserId(map.get(TDFN.userId));
		setProductCode(map.get(TDFN.prodId));
		if (map.get(TDFN.couponAmt) != null & map.get(TDFN.couponAmt).length() > 0)
			setPreAccount(new Double(map.get(TDFN.couponAmt)));
		if (map.get(TDFN.selfAmt) != null & map.get(TDFN.selfAmt).length() > 0)
			setAccount(new Double(map.get(TDFN.selfAmt)));
		if (map.get(TDFN.couponAmt) != null & map.get(TDFN.couponAmt).length() > 0)
			setVoucherMoney(new Double(map.get(TDFN.couponAmt)));
		setOrderId(map.get(TDFN.orderNo));
		setTenderKey(map.get(TDFN.tenderKey));*/
	}
}
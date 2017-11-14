package com.tuodao.bp.model.depository;

import java.util.HashMap;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.constant.depository.TDFN;

public abstract class DepositoryPara implements DepositoryParaInterface {

	private static final long serialVersionUID = 232416858232115971L;

	private boolean isSuccess;

	private String remark;

	private String orderNo;

	private String recode;

	private String remsg;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getRecode() {
		return recode;
	}

	public void setRecode(String recode) {
		this.recode = recode;
	}

	public String getRemsg() {
		return remsg;
	}

	public void setRemsg(String remsg) {
		this.remsg = remsg;
	}

	@Override
	public HashMap<String, String> toHashMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put(TDFN.remark, getRemark());
		map.put(TDFN.orderNo, getOrderNo());
		map.put(TDFN.recode, getRecode());
		map.put(TDFN.remsg, getRemsg());
		return map;
	}

	@Override
	public void fromHashMap(HashMap<String, String> map) {
		if (map.get(TDFN.success) != null && map.get(TDFN.success).length() > 0)
			setSuccess(new Boolean(map.get(TDFN.success)));
		setRemark(map.get(TDFN.remark));
		setOrderNo(map.get(TDFN.orderNo));
		setRecode(map.get(TDFN.recode));
		setRemsg(map.get(TDFN.remsg));
	}

}

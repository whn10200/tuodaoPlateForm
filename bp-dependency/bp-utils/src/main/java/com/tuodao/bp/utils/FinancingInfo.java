package com.tuodao.bp.utils;

/**  标的发布投资人信息 */
public   class FinancingInfo {

	/** 融资人的平台客户编号 */
	private String custNo;
	/** 申请日期（YYYY-MM-DD） */
	private String regDate;
	/** 申请时间（HH:mm:ss） */
	private String regTime;
	/** 融资利息（eg：0.12） */
	private String financInt;
	/** 手续费（eg：0.01） */
	private String feeInt;
	/** 借款用途[限制100字符以内] */
	private String financPurpose;
	/** 用款日期（YYYY-MM-DD） */
	private String useDate;
	/** 产品收款人开户行 */
	private String openBranch;
	/** 产品收款人银行卡号 */
	private String withdrawAccount;
	/** 卡类型(1-个人 2-企业) */
	private String accountType;
	/** 产品收款人姓名 */
	private String payeeName;
	/** 融资金额[N(19,2)] */
	private String financAmt;

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public String getFinancInt() {
		return financInt;
	}

	public void setFinancInt(String financInt) {
		this.financInt = financInt;
	}

	public String getFeeInt() {
		return feeInt;
	}

	public void setFeeInt(String feeInt) {
		this.feeInt = feeInt;
	}

	public String getFinancPurpose() {
		return financPurpose;
	}

	public void setFinancPurpose(String financPurpose) {
		this.financPurpose = financPurpose;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public String getOpenBranch() {
		return openBranch;
	}

	public void setOpenBranch(String openBranch) {
		this.openBranch = openBranch;
	}

	public String getWithdrawAccount() {
		return withdrawAccount;
	}

	public void setWithdrawAccount(String withdrawAccount) {
		this.withdrawAccount = withdrawAccount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getFinancAmt() {
		return financAmt;
	}

	public void setFinancAmt(String financAmt) {
		this.financAmt = financAmt;
	}

}

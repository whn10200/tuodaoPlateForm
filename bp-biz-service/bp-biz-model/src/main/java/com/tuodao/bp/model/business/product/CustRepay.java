package com.tuodao.bp.model.business.product;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.tuodao.bp.model.constant.depository.TDFN;

public class CustRepay {

	protected HashMap<String, Object> map = new HashMap<String, Object>();

	/** 本期已还清：0-是，1-否 */
	private String repayFlag;
	/** 投资人平台客户号 */
	private String custNo;
	/** 实际还款金额（实际还款本金+体验金+加息金+利息+手续费） */
	private BigDecimal realRepayAmt;
	/** 实际还款本金 */
	private BigDecimal realRepayAmount;
	/** 实际还款利息 */
	private BigDecimal realRepayVal;
	/** 体验金 */
	private BigDecimal experienceAmt;
	/** 手续费 */
	private BigDecimal repayFee;
	/** 加息金 */
	private BigDecimal ratesAmt;
	/** 还款期数 */
	private String repayNum;

	/** 预计还款日期 */
	private String repayDate;
	/** 实际还款日期 */
	private String realRepayDate;

	public Map<String, Object> toMap() {
		/*map.put(TDFN.repayFlag, getRepayFlag());
		map.put(TDFN.custNo, getCustNo());
		map.put(TDFN.realRepayAmt, getRealRepayAmt());
		map.put(TDFN.realRepayAmount, getRealRepayAmount());
		map.put(TDFN.realRepayVal, getRealRepayVal());
		map.put(TDFN.experienceAmt, getExperienceAmt());
		map.put(TDFN.repayFee, getRepayFee());
		map.put(TDFN.ratesAmt, getRatesAmt());
		map.put(TDFN.repayNum, getRepayNum());
		map.put(TDFN.repayDate, getRepayDate());
		map.put(TDFN.realRepayDate, getRealRepayDate());*/
		return map;
	}

	public String getRepayFlag() {
		return repayFlag;
	}

	public void setRepayFlag(String repayFlag) {
		this.repayFlag = repayFlag;
	}

	public BigDecimal getRealRepayAmount() {
		return realRepayAmount;
	}

	public void setRealRepayAmount(BigDecimal realRepayAmount) {
		this.realRepayAmount = realRepayAmount;
	}

	public BigDecimal getRealRepayVal() {
		return realRepayVal;
	}

	public void setRealRepayVal(BigDecimal realRepayVal) {
		this.realRepayVal = realRepayVal;
	}

	public BigDecimal getExperienceAmt() {
		return experienceAmt;
	}

	public void setExperienceAmt(BigDecimal experienceAmt) {
		this.experienceAmt = experienceAmt;
	}

	public BigDecimal getRepayFee() {
		return repayFee;
	}

	public void setRepayFee(BigDecimal repayFee) {
		this.repayFee = repayFee;
	}

	public BigDecimal getRatesAmt() {
		return ratesAmt;
	}

	public void setRatesAmt(BigDecimal ratesAmt) {
		this.ratesAmt = ratesAmt;
	}

	public String getCustNo() {
		return custNo;
	}

	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}

	public String getRepayNum() {
		return repayNum;
	}

	public void setRepayNum(String repayNum) {
		this.repayNum = repayNum;
	}

	public String getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(String repayDate) {
		this.repayDate = repayDate;
	}

	public String getRealRepayDate() {
		return realRepayDate;
	}

	public void setRealRepayDate(String realRepayDate) {
		this.realRepayDate = realRepayDate;
	}

	public BigDecimal getRealRepayAmt() {
		return realRepayAmt;
	}

	public void setRealRepayAmt(BigDecimal realRepayAmt) {
		this.realRepayAmt = realRepayAmt;
	}

}

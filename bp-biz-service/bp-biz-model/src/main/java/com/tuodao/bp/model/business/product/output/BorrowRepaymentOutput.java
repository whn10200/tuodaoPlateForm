package com.tuodao.bp.model.business.product.output;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author wuchengjie
 * @Date 2017/9/19 0019 15:11
 * @Introduction
 */
public class BorrowRepaymentOutput implements Serializable {

	private static final long serialVersionUID = -699773089282235627L;
	/** 提前还款 */
	private Boolean advance = false;

	private Integer id;

	private String userId;

	private Integer borrowId;

	private Integer status;

	private Integer period;

	private Integer periods;

	private BigDecimal fee;

	private BigDecimal preInterest;

	private BigDecimal preCapital;

	private Date preRepayTime;

	private BigDecimal interest;

	private BigDecimal capital;

	private Date repayTime;

	private Integer repayMode;

	private String remarks;

	private String borrowNid;
	
	/** 还款步骤：0：初始 1：标的余额足够 2：标的还款成功（银行）3：标的还款成功 **/
	private Integer repayStep;
    
    public Integer getRepayStep() {
		return repayStep;
	}

	public void setRepayStep(Integer repayStep) {
		this.repayStep = repayStep;
	}


	public Boolean getAdvance() {
		return advance;
	}

	public void setAdvance(Boolean advance) {
		this.advance = advance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getPeriods() {
		return periods;
	}

	public void setPeriods(Integer periods) {
		this.periods = periods;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public BigDecimal getPreInterest() {
		return preInterest;
	}

	public void setPreInterest(BigDecimal preInterest) {
		this.preInterest = preInterest;
	}

	public BigDecimal getPreCapital() {
		return preCapital;
	}

	public void setPreCapital(BigDecimal preCapital) {
		this.preCapital = preCapital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public Integer getRepayMode() {
		return repayMode;
	}

	public void setRepayMode(Integer repayMode) {
		this.repayMode = repayMode;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks == null ? null : remarks.trim();
	}

	public String getBorrowNid() {
		return borrowNid;
	}

	public void setBorrowNid(String borrowNid) {
		this.borrowNid = borrowNid == null ? null : borrowNid.trim();
	}

	public Date getPreRepayTime() {
		return preRepayTime;
	}

	public void setPreRepayTime(Date preRepayTime) {
		this.preRepayTime = preRepayTime;
	}

	public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}
}

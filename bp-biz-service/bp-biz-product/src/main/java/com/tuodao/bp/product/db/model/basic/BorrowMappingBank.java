package com.tuodao.bp.product.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;

public class BorrowMappingBank implements Serializable {
	 
	private static final long serialVersionUID = -4643604095574999943L;
	/****/
	private Integer id;
	/**借款id**/
	private Integer borrowId;
	/**是否要代偿  1:是  0 否**/
	private Integer isCompensatory;
	/**借款存管状态 0：初始    1：成标    2： 出账 **/
	private Integer borrowBankStatus;
	/**预计回到代偿人的钱**/
	private BigDecimal compensatoryAmount;
	/**实际偿还金额**/
	private BigDecimal compensatoryAmountYes;
	/**最后代偿请求状态（0 初始 1 部分成功 2全部成功 3 失败）**/
	private Integer compensatoryStatus;
	public Integer getId() {
	    return this.id;
	}
	public void setId(Integer id) {
	    this.id=id;
	}
	public Integer getBorrowId() {
	    return this.borrowId;
	}
	public void setBorrowId(Integer borrowId) {
	    this.borrowId=borrowId;
	}
	public Integer getIsCompensatory() {
	    return this.isCompensatory;
	}
	public void setIsCompensatory(Integer isCompensatory) {
	    this.isCompensatory=isCompensatory;
	}
	public Integer getBorrowBankStatus() {
	    return this.borrowBankStatus;
	}
	public void setBorrowBankStatus(Integer borrowBankStatus) {
	    this.borrowBankStatus=borrowBankStatus;
	}
	public BigDecimal getCompensatoryAmount() {
	    return this.compensatoryAmount;
	}
	public void setCompensatoryAmount(BigDecimal compensatoryAmount) {
	    this.compensatoryAmount=compensatoryAmount;
	}
	public BigDecimal getCompensatoryAmountYes() {
	    return this.compensatoryAmountYes;
	}
	public void setCompensatoryAmountYes(BigDecimal compensatoryAmountYes) {
	    this.compensatoryAmountYes=compensatoryAmountYes;
	}
	public Integer getCompensatoryStatus() {
	    return this.compensatoryStatus;
	}
	public void setCompensatoryStatus(Integer compensatoryStatus) {
	    this.compensatoryStatus=compensatoryStatus;
	}
}
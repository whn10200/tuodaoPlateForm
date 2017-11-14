package com.tuodao.bp.model.business.product.requset;

import java.util.Date;

public class ProductAuditRecordReq {

	/** 主键ID **/
	private Integer id;
	/** 产品ID **/
	private Integer productId;
	/** 审核人ID **/
	private String auditUserId;
	/** 审核人名称 **/
	private String auditName;
	/** 审核时间 **/
	private Date auditTime;
	/** 审核类型 0：设置标的 1：发布标的 2：审核标的 3：手动满标审核标的 **/
	private Integer auditType;
	/** 审核结果 0：打回,1：通过 2：撤回 **/
	private Integer auditResult;
	/** 审核备注 **/
	private Object auditRemark;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return this.productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getAuditUserId() {
		return this.auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditName() {
		return this.auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public Date getAuditTime() {
		return this.auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getAuditType() {
		return this.auditType;
	}

	public void setAuditType(Integer auditType) {
		this.auditType = auditType;
	}

	public Integer getAuditResult() {
		return this.auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}

	public Object getAuditRemark() {
		return this.auditRemark;
	}

	public void setAuditRemark(Object auditRemark) {
		this.auditRemark = auditRemark;
	}
}

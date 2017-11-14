package com.tuodao.bp.model.business.product.requset;

import java.util.Date;

public class ProductReq {

	/** id - 主键ID */
	private Integer id;

	/** audit_remark - 审核备注 */
	private String auditRemark;

	/** audit_user_id - 审核人 */
	private String auditUserId;

	/** audit_user_name - 审核人名称 */
	private String auditUserName;

	/** audit_time - 审核时间 */
	private Date auditTime;

	/**
	 * product_status - 产品状态 0：初始 1：被打回 2：被撤回 3：待审核4：待发布 5：募集中 6：满标待审7：还款中 8：已还款
	 * 9：已还款（提前还款）
	 */
	private Integer productStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuditRemark() {
		return auditRemark;
	}

	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}

	public String getAuditUserId() {
		return auditUserId;
	}

	public void setAuditUserId(String auditUserId) {
		this.auditUserId = auditUserId;
	}

	public String getAuditUserName() {
		return auditUserName;
	}

	public void setAuditUserName(String auditUserName) {
		this.auditUserName = auditUserName;
	}

	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

}
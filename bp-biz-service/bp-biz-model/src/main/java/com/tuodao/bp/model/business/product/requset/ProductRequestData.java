package com.tuodao.bp.model.business.product.requset;

import com.tuodao.bp.model.BasePojo;

public class ProductRequestData extends BasePojo {

	private static final long serialVersionUID = 7847920633279623679L;
	private ProductReq productReq;
	private ProductAuditRecordReq productAuditRecordReq;

	public ProductReq getProductReq() {
		return productReq;
	}

	public void setProductReq(ProductReq productReq) {
		this.productReq = productReq;
	}

	public ProductAuditRecordReq getProductAuditRecordReq() {
		return productAuditRecordReq;
	}

	public void setProductAuditRecordReq(ProductAuditRecordReq productAuditRecordReq) {
		this.productAuditRecordReq = productAuditRecordReq;
	}

}

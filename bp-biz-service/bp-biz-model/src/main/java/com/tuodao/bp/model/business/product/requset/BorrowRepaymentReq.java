package com.tuodao.bp.model.business.product.requset;

import java.io.Serializable;

public class BorrowRepaymentReq implements Serializable {

	private static final long serialVersionUID = -8165098388205095605L;
	/** 还款表主键 **/
	private Integer id;

	/** 提前还款 true ：是，否：false */
	private Boolean advance = false;
	/** 产品状态 */
	private Integer productStatus;
	/** 产品Id */
	private Integer productId;

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(Integer productStatus) {
		this.productStatus = productStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Boolean getAdvance() {
		return advance;
	}

	public void setAdvance(Boolean advance) {
		this.advance = advance;
	}

}

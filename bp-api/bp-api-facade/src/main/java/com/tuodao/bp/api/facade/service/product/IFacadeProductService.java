package com.tuodao.bp.api.facade.service.product;

import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;

/**
 * @Author wuchengjie
 * @Date 2017/9/26 0026 17:11
 * @Introduction
 */
public interface IFacadeProductService {
	Boolean excutePurchased();

	/**
	 * 标的还款
	 * 
	 * @param requestData
	 * @return
	 */
	Boolean repaymentProduct(ProductOutput productOutput ,BorrowRepaymentOutput borrowRepaymentOutput,Boolean advance);

}

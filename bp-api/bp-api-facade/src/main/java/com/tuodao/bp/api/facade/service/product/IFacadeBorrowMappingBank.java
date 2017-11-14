package com.tuodao.bp.api.facade.service.product;


import com.tuodao.bp.model.business.product.output.BorrowMappingBankOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;

/**
 * @Author wuchengjie
 * @Date 2017/9/26 0026 17:11
 * @Introduction
 */
public interface IFacadeBorrowMappingBank {


	/**
	 * 满标复审
	 * @return
	 */
	Boolean reverifyDepositoryStatus(Integer BorrowId);

    Boolean borrowerCompensation(BorrowMappingBankOutput output, ProductOutput product);
}

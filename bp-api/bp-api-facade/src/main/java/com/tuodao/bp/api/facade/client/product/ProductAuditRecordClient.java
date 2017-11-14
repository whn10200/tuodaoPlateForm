package com.tuodao.bp.api.facade.client.product;

import com.tuodao.bp.model.business.product.output.BorrowPlanTransferOutput;
import com.tuodao.bp.model.business.product.output.ProductWithRecordOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/productAuditRecord")
@FeignClient(value="biz-product")
public interface ProductAuditRecordClient {


	/**
	 * 查询标的的审核记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getAuditRecordList",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public  List<ProductWithRecordOutput>  getBorrowPlanTransfer(List<Integer> id) ;


}

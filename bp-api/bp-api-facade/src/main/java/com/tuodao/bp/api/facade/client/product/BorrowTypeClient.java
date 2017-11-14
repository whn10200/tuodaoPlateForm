package com.tuodao.bp.api.facade.client.product;

import com.tuodao.bp.model.business.product.output.BorrowTypeOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/borrowType")
@FeignClient(value="biz-product")
public interface BorrowTypeClient {

	/**
	 * 查询自定义标标总
	 */
	@RequestMapping(value="/getEnableBorrowTypeById",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public BorrowTypeOutput getEnableBorrowType(Integer id);

 }

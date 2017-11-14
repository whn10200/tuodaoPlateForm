package com.tuodao.bp.api.facade.client.product;

import com.tuodao.bp.model.business.product.output.BorrowExpandOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/borrowExpand")
@FeignClient(value="biz-product")
public interface BorrowExpandClient {

	/**
	 * 查询自定义标标总
	 */
	@RequestMapping(value="/getBorrowExpandByPcode",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	BorrowExpandOutput getBorrowExpandByPcode(String code);

	/**
	 * 通过id 获取扩展信息
	 */
	@RequestMapping(value="/getBorrowExpandById",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	Integer getById(Integer pId);

	/**
	 * 通过id 获取类型
	 *  项目说明类型 0：抵押1:质押 2:4s店合作标
	 */
	@RequestMapping(value="/getExplaindTypeById",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	Integer getExplaindTypeById(Integer pId);

 }

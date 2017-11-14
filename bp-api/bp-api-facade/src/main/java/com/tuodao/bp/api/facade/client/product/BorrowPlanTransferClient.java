package com.tuodao.bp.api.facade.client.product;

import com.tuodao.bp.model.business.product.output.BorrowPlanTransferOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping("/borrowPlanTransfer")
@FeignClient(value="biz-product")
public interface BorrowPlanTransferClient {


	/**
	 * 查询超过48小时的理财计划债权 用于内部账号购买
	 * @return
	 */
	@RequestMapping(value="/getOvertimeTransferList",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<BorrowPlanTransferOutput> getOvertimeTransferList();


	/**
	 * 查询理财计划下需要复审转让标的
	 */
	@RequestMapping(value = "/getPlanReverifyTransferList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<BorrowPlanTransferOutput> getPlanReverifyTransferList();



	/**
	 * 获取债权转让信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/getBorrowPlanTransList",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	public BorrowPlanTransferOutput getBorrowPlanTransfer( Integer id);

}

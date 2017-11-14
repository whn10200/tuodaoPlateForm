package com.tuodao.bp.api.facade.client.product;

import java.util.List;

import com.tuodao.bp.model.business.product.output.RepayPlanOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.RepayQueryInput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentInfoOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.model.business.product.output.RepayQueryOutput;

@FeignClient(value = "biz-product")
public interface RepayClient {

	/**
	 * 查询某一个标的未还款期数 上期还款日期 如果没还款过 那么就取复审时间
	 */
	@RequestMapping(value = "/repay/getRepayInfoByBorrowId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	BorrowRepaymentInfoOutput getRepayInfoByBorrowId(Integer borrowId);

	/**
	 * 还款列表
	 * 
	 * @param input
	 */
	@RequestMapping(value = "/repay/getRepayList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<RepayQueryOutput> getRepayList(RepayQueryInput input);


	/**
	 * 还款的临时列表（成标的时候用）
	 * @param borrowId
	 */
	@RequestMapping(value = "/repay/getTemporaryRepayList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<RepayPlanOutput> getTemporaryRepayList(Integer borrowId);


	/**
	 * 根据id主键查询回款信息
	 */
	@RequestMapping(value = "/repay/getRepayInfoById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	BorrowRepaymentOutput getRepayInfoById(Integer id);

	/**
	 * 根据传入参数获取当前期数之前已还款数据
	 */
	@RequestMapping(value = "/repay/getRepayInfoByparam", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	BorrowRepaymentOutput getRepayInfoByparam(BorrowRepaymentInput input);
	
	/**
	 * 根据id更新还款表
	 */
	@RequestMapping(value = "/repay/updateRepayment", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Boolean updateRepayment(BorrowRepaymentInput borrowRepaymentInput); 
}

package com.tuodao.bp.product.controller;

import java.util.List;

import com.tuodao.bp.model.business.product.output.RepayPlanOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.RepayQueryInput;
import com.tuodao.bp.model.business.product.input.TransferInput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentInfoOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.model.business.product.output.RepayQueryOutput;
import com.tuodao.bp.product.db.model.basic.BorrowRepayment;
import com.tuodao.bp.product.service.IBorrowRepayService;
import com.tuodao.bp.utils.TransferUtil;

@RestController
@RequestMapping("/repay")
public class RepayController {
	
	private static final Logger logger = LoggerFactory.getLogger(RepayController.class);

	@Autowired
	private IBorrowRepayService borrowRepayService;


	/**
	 * 插入还款计划
	 * @param repaymentInputList
	 * @return
	 */
	@RequestMapping(value="/insertBorrowRepayment",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean insertBorrowRepayment( List<BorrowRepaymentInput> repaymentInputList) {
		logger.info("name = {}",repaymentInputList);
	    Boolean bb =  borrowRepayService.insertBorrowRepayment(repaymentInputList);
		logger.info("bb  = {}", bb);
		return bb;
	}


	/**
	 * 理财计划还款
	 * @param input
	 * @return
	 */
	@RequestMapping(value="/borrowRepay",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean doBorrowRepay( BorrowRepaymentInput input ,List<TransferInput> list) {
		Integer borrowId = input.getBorrowId();
		Integer period = input.getPeriod();
		Boolean advance = input.getAdvance();
		Boolean bb =  borrowRepayService.doBorrowRepay( borrowId,  period, list,advance);
		return bb;
	}


	/**
	 * 正常还款
	 * @param input
	 * @return
	 */
	@RequestMapping(value="/doRepay",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean doRepay( BorrowRepaymentInput input) {
		Integer borrowId = input.getBorrowId();
		Integer period = input.getPeriod();
     	Boolean advance = input.getAdvance();
 	    Boolean bb =  borrowRepayService.doRepay( borrowId,  period,advance);
		return bb;
	}

	/**
	 * 正常还款
	 * @param input
	 * @return
	 */
	@RequestMapping(value="/getRepayList", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RepayQueryOutput> getRepayList(RepayQueryInput input) {
		List<BorrowRepayment> borrowRepaymentList = borrowRepayService.getRepayList(input.getBorrowId(),
				input.getStatus());
		List<RepayQueryOutput> list = TransferUtil.transferList(borrowRepaymentList, RepayQueryOutput.class);
		return list;
	}



	/**
	 * 还款的临时列表（成标的时候用）
	 * @param borrowId
	 */
	@RequestMapping(value = "/getTemporaryRepayList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<RepayPlanOutput> getTemporaryRepayList(Integer borrowId){

		List<RepayPlanOutput> repayPlanOutputs =  borrowRepayService.getTemporaryRepayList(borrowId);
		return repayPlanOutputs;
	}



	/**
	 * 查询某一个标的未还款期数 上期还款日期  如果没还款过 那么就取复审时间
	 * @return
	 */
	@RequestMapping(value="/getRepayInfoByBorrowId", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BorrowRepaymentInfoOutput getRepayInfoByBorrowId(Integer borrowId) {
		BorrowRepaymentInfoOutput output = borrowRepayService.getRepayInfoByBorrowId(borrowId);
		return output;
	}

	/**
	 * 根据主键ID获取还款信息
	 * @return
	 */
	@RequestMapping(value="/getRepayInfoById", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BorrowRepaymentInput getRepayInfoById(Integer borrowId) {
		BorrowRepaymentInput output = borrowRepayService.getRepayInfoById(borrowId);
		return output;
	}

	/**
	 * 根据传入参数获取当前期数之前已还款数据
	 * @return
	 */
	@RequestMapping(value="/getRepayInfoByparam", method=RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public BorrowRepaymentOutput getRepayInfoByparam(BorrowRepaymentInput input) {
		BorrowRepaymentOutput output = borrowRepayService.getRepayInfoByparam(input);
		return output;
	}

	/**
	 * 更新还款计划
	 * @param borrowRepaymentInput
	 * @return
	 */
	@RequestMapping(value="/updateRepayment",method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean updateRepayment(BorrowRepaymentInput borrowRepaymentInput) {
		logger.info("name = {}",borrowRepaymentInput);
	    Boolean bb =  borrowRepayService.updateRepayment(borrowRepaymentInput);
		logger.info("bb  = {}", bb);
		return bb;
	}
 

}

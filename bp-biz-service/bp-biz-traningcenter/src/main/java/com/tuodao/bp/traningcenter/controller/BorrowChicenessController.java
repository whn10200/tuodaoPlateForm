package com.tuodao.bp.traningcenter.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.BorrowChicenessTenderService;
import com.tuodao.bp.traningcenter.service.BorrowCollectionService;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.List;

/**
 * @description:理财计划投资相关的
 * @author: wuzf
 * @date: 2017/9/8 0008.
 * @time: 14:00
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/TraningCenter")
public class BorrowChicenessController {
	
	private static final Logger logger = LoggerFactory.getLogger(BorrowChicenessController.class);
	
	@Autowired
	private BorrowChicenessTenderService borrowChicenessTenderService;

	@Autowired
	private BorrowTenderService borrowTenderService;

	@Autowired
	private BorrowCollectionService borrowCollectionService;

	@Resource
	protected ProjectInfoCache projectInfoCache;

	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Resource(name = "traningJoinChoiseQueue")
	private Queue traningJoinChoiseQueue;
	@Resource
	private ProducerMq producerMq;

	/**
	 * 加入理财计划（生产者）
	 * @param choicenessTenderInput
	 * @return
	 */
	@RequestMapping(value = "/borrowChicenessTender/insertChoicenessProvide", method = RequestMethod.POST)
	public void insertChoicenessProvide(ChoicenessTenderInput choicenessTenderInput) {
		Integer dikId = null;
		if (choicenessTenderInput.getVoucherId() != null) {
			dikId = choicenessTenderInput.getVoucherId();
		} else if (choicenessTenderInput.getVoucherCouponId() != null) {
			dikId = choicenessTenderInput.getVoucherCouponId();
		}
		if(dikId!=null)
		{
			//将优惠卷修改成已使用
			producerMq.updateVoucherStatus(dikId,2,choicenessTenderInput.getUserId(),null);
		}
		jmsMessagingTemplate.convertAndSend(traningJoinChoiseQueue, choicenessTenderInput);
	}

	/**
	 * 根据标的id查询理财计划加入记录
	 */
	@PostMapping(value = "/borrowChiceness/getJoinLists")
	public PageInfo<TenderRecord> getJoinLists(TenderTraRecordInput tenderTrRecordInput) {
		return borrowChicenessTenderService.getJoinLists(tenderTrRecordInput);
	}

	/**
	 * 查询未匹配的投资
	 * @param
	 * @return List<BorrowChoicenessTender>
	 */
	@RequestMapping(value = "/borrowChicenessTender/selectNoMate", method = RequestMethod.POST)
	public List<SelectNoMateOutput> selectNoMate() {
		return borrowChicenessTenderService.selectNoMate();
	}


	/**
	 * 根据userid和状态和起始时间分页查询理财计划投资记录
	 * @param  tenderInput
	 * @return PageInfo<SelectTenderOutput>
	 */
	@RequestMapping(value = "/borrowChicenessTender/selectTenderByBorrowId", method = RequestMethod.POST)
	public PageInfo<SelectTenderOutput> selectTenderByBorrowId(SelectTenderInput tenderInput) {
		return borrowChicenessTenderService.selectByStatusAndUserId(tenderInput);
	}


	/**
	 * 查询我的投资（理财计划投资详情债权明细）
	 * @param  justIdInput
	 * @return PageInfo<UnderTenderOutput>
	 */
	@RequestMapping(value = "/borrowChicenessTender/selectTenderListByChoicenessTenderId", method = RequestMethod.POST)
	public PageInfo<UnderTenderOutput> selectTenderListByChoicenessTenderId(JustIdInput justIdInput) {
		return  borrowTenderService.selectTenderListByChoicenessTenderId(justIdInput);
	}

	/**
	 * 查询我的投资（理财计划投资详情还款计划）
	 * @param  justIdInput
	 * @return PageInfo<UnderTenderOutput>
	 */
	@RequestMapping(value = "/borrowChiceness/selectRecoverListBychioId", method = RequestMethod.POST)
	public PageInfo<RecoverListOutput> selectRecoverListBychioId(JustIdInput justIdInput) {
		return  borrowCollectionService.selectRecoverListBychioId(justIdInput);
	}


	/**
	 * 理财计划投资详情
	 * @param justIdInput
	 * @return TenderDetailsOutput
	 */
	@RequestMapping(value = "/borrowChicenessTender/selectTenderById", method = RequestMethod.POST)
	public TenderDetailsOutput selectTenderById(JustIdInput justIdInput) {

		return borrowChicenessTenderService.selectTenderById(justIdInput.getJustId());
	}


	/**
	 * 查询加入该笔理财计划的最多投资以及捡漏用户
	 * @param tenderTrRecordInput
	 * @return TenderTraRecordOutput
	 */
	@RequestMapping(value = "/borrowChicenessTender/getMaxAndLast", method = RequestMethod.POST)
	public TenderTraRecordOutput getMaxAndLast(TenderTraRecordInput tenderTrRecordInput) {
		return borrowChicenessTenderService.getMaxAndLast(tenderTrRecordInput);
	}

	/**
	 * 理财计划的中所有没有匹配到债权的投资记录
	 * @return TenderOutput
	 */
	@RequestMapping(value = "/borrowChicenessTender/selectUnmatchingList", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ChicenessTenderOutput> selectUnmatchingList() {
		return borrowChicenessTenderService.selectUnmatchingList();
	}

	/**
	 * 释放zq(不包含内部账户)
	 * @param selectClaimInput
	 */
	@RequestMapping(value = "/borrowChiceness/selectClaim", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void test(SelectClaimInput selectClaimInput) {
		if(selectClaimInput.getType()==0)
		{
			borrowChicenessTenderService.selectClaim(selectClaimInput.getJustId(), null);
		}
		else
		{
			borrowChicenessTenderService.selectClaim(null, selectClaimInput.getJustId());
		}
	}

	/**
	 * 释放zq(内部账户)
	 * @param justIdInput
	 */
	@RequestMapping(value = "/borrowChiceness/selectClaimInside", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public void selectClaimInside(JustIdInput justIdInput) {
		borrowChicenessTenderService.selectClaimInside(justIdInput.getJustId().toString());
	}

}

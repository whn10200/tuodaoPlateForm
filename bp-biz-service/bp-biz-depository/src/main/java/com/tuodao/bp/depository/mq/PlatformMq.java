package com.tuodao.bp.depository.mq;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.depository.service.PlatformService;

@Component
public class PlatformMq extends BaseMq {

	@Autowired
	private PlatformService platformService;

	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Resource(name = "deOutPlatformFundFreeze")
	private Queue deOutPlatformFundFreeze;

	@Resource(name = "deOutPlatformFundUnblock")
	private Queue deOutPlatformFundUnblock;

	@Resource(name = "deOutPlatformConverse")
	private Queue deOutPlatformConverse;

	@Resource(name = "deOutPlatformRecharge")
	private Queue deOutPlatformRecharge;

	@Resource(name = "deOutPlatformTransfer")
	private Queue deOutPlatformTransfer;

	@Resource(name = "deOutPlatformWithdraw")
	private Queue deOutPlatformWithdraw;

	@JmsListener(destination = DepositoryMqConstant.DE_IN_PLATFORM_FUND_FREEZE)
	public void fundFreeze(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutPlatformFundFreeze, platformService.fundFreeze(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_PLATFORM_FUND_UNBLOCK)
	public void fundUnblock(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutPlatformFundUnblock, platformService.fundUnblock(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_PLATFORM_CONVERSE)
	public void platformConverse(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutPlatformConverse, platformService.platformConverse(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_PLATFORM_RECHARGE)
	public void platformRecharge(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutPlatformRecharge, platformService.platformRecharge(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER)
	public void platformTransfer(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutPlatformTransfer, platformService.platformTransfer(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_PLATFORM_WITHDRAW)
	public void platformWithDraw(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutPlatformWithdraw, platformService.platformWithDraw(map), head);
	}

}

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
import com.tuodao.bp.depository.service.WithdrawService;

@Component
public class WithdrawMq extends BaseMq{

	@Autowired
	private WithdrawService withdrawService;

	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Resource(name = "deInWithdrawApplyInvestor")
	private Queue deInWithdrawApplyInvestor;

	@JmsListener(destination = DepositoryMqConstant.DE_IN_WITHDRAW_APPLY_INVESTOR)
	public void withDrawApplyInvestor(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deInWithdrawApplyInvestor, withdrawService.withDrawApplyInvestor(map),head);
	}

}

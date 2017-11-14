package com.tuodao.bp.depository.mq;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.jms.core.JmsMessagingTemplate;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;

public class BaseMq {
	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;

	public void convertAndSend(Queue destination, Object payload, String head) {
		HashMap<String, Object> header = new HashMap<String, Object>();
		header.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY, head);
		jmsMessagingTemplate.convertAndSend(destination, payload, header);
	}
}

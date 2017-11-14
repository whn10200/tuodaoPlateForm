package com.tuodao.bp.depository.mq;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.jms.Queue;

import com.alibaba.fastjson.JSON;
import org.apache.activemq.command.ActiveMQMapMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.depository.service.UserService;

@Component
public class UserMq extends BaseMq {
	private final static Logger logger = LoggerFactory.getLogger(UserMq.class);
	@Autowired
	private UserService userService;

	@Resource(name = "deOutUserRegistRealName")
	private Queue deOutUserRegistRealName;

	@Resource(name = "deOutUserChangeCard")
	private Queue deOutUserChangeCard;

	@Resource(name = "deOutUserMessageBoundCardApply")
	private Queue deOutUserMessageBoundCardApply;

	@Resource(name = "deOutUserMessageBoundCardValidate")
	private Queue deOutUserMessageBoundCardValidate;

	@Resource(name = "deOutUserBoundCard")
	private Queue deOutUserBoundCard;

	@Resource(name = "deOutUserUpdateInfo")
	private Queue deOutUserUpdateInfo;

	@Resource(name = "deOutUserRegist4Ele")
	private Queue deOutUserRegist4Ele;

	@JmsListener(destination = DepositoryMqConstant.DE_IN_USER_REGIST_4_ELE)
	public void regist4Ele(ActiveMQMapMessage message,HashMap<String, String> map,
						   @Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		HashMap<String, String> mapReturn = userService.regist4Ele(map);
		super.convertAndSend(deOutUserRegist4Ele,mapReturn , head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_USER_REGIST_REAL_NAME)
	public void registRealname(ActiveMQMapMessage message,HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutUserRegist4Ele, userService.registRealname(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_USER_CHANGE_CARD)
	public void changeCard(ActiveMQMapMessage message,HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutUserRegist4Ele, userService.changeCard(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_USER_MESSAGE_BOUND_CARD_APPLY)
	public void messageBoundCardApply(ActiveMQMapMessage message,HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutUserRegist4Ele, userService.messageBoundCardApply(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_USER_MESSAGE_BOUND_CARD_VALIDATE)
	public void messageBoundCardValidate(ActiveMQMapMessage message,HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutUserRegist4Ele, userService.messageBoundCardValidate(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_USER_BOUND_CARD)
	public void boundCard(ActiveMQMapMessage message,HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutUserRegist4Ele, userService.boundCard(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_USER_UPDATE_INFO)
	public void updateInfo(ActiveMQMapMessage message,HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutUserRegist4Ele, userService.updateInfo(map), head);
	}

}

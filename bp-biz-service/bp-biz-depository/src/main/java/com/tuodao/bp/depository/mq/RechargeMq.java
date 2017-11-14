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
import com.tuodao.bp.depository.service.RechargeService;

@Component
public class RechargeMq extends BaseMq {

	@Autowired
	private RechargeService rechargeService;
	
	/*@Resource(name = "deOutRecharge")
	private Queue deOutRecharge;*/
	@Resource(name = "deOutRechargeGateway")
	private Queue deOutRechargeGateway;
	/*@Resource(name = "deOutRechargeQuick")
	private Queue deOutRechargeQuick;*/
	@Resource(name = "deOutRechargeQuickApply")
	private Queue deOutRechargeQuickApply;
	@Resource(name = "deOutRechargeQuickConfirm")
	private Queue deOutRechargeQuickConfirm;

	/*
	 * @Resource(name = "deOutRechargeBorrowCutRepay") private Queue
	 * deOutRechargeBorrowCutRepay;
	 */

	@JmsListener(destination = DepositoryMqConstant.DE_IN_RECHARGE_GATEWAY)
	public void gatewayRecharge(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutRechargeGateway, rechargeService.gatewayRecharge(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_RECHARGE_QUICK_APPLY)
	public void quickRechargeApply(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutRechargeQuickApply, rechargeService.quickRechargeApply(map), head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_RECHARGE_QUICK_CONFIRM)
	public void quickRechargeConfirm(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutRechargeQuickConfirm, rechargeService.quickRechargeConfirm(map), head);
	}

	/*
	 * @JmsListener(destination = DepositoryMqConstant.) public void
	 * borrowCutRepay(HashMap<String,String>map) { OpenDepositCallbackInput re =
	 * new OpenDepositCallbackInput();
	 * re.fromHashMap(rechargeService.borrowCutRepay(map),head);
	 * super.convertAndSend(deOutRechargeBorrowCutRepay,re); }
	 */

}

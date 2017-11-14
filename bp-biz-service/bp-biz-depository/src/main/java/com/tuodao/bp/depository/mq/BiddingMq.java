package com.tuodao.bp.depository.mq;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.depository.service.BiddingService;

@Component
public class BiddingMq extends BaseMq{

	@Autowired
	private BiddingService biddingService;

	@Resource(name = "deOutBiddingPublish")
	private Queue deOutBiddingPublish;

	@Resource(name = "deOutBiddingComplate")
	private Queue deOutBiddingComplate;
	
	@Resource(name = "deOutBiddingCancel")
	private Queue deOutBiddingCancel;

	@Resource(name = "deOutBiddingBid")
	private Queue deOutBiddingBid;

	@Resource(name = "deOutBiddingChargeOff")
	private Queue deOutBiddingChargeOff;

	/*@Resource(name = "deOutBiddingChargeOffReceive")
	private Queue deOutBiddingChargeOffReceive;*/

	@Resource(name = "deOutBiddingTransferDebt")
	private Queue deOutBiddingTransferDebt;

	@Resource(name = "deOutBiddingRepay")
	private Queue deOutBiddingRepay;

	@Resource(name = "deOutBiddingRepayPlaneUpdate")
	private Queue deOutBiddingRepayPlaneUpdate;

	@Resource(name = "deOutBiddingChargeOffChange")
	private Queue deOutBiddingChargeOffChange;

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_PUBLISH)
	public void publish(@Payload HashMap<String, String> map,@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		
		super.convertAndSend(deOutBiddingPublish, biddingService.complate(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_COMPLATE)
	public void complate(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingComplate, biddingService.complate(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_CANCEL)
	public void cancel(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingCancel, biddingService.cancel(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_BID)
	public void bid(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingBid, biddingService.bid(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_CHARGE_OFF)
	public void chargeOff(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingChargeOff, biddingService.chargeOff(map),head);
	}

	/*@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_CHARGE_OFF_CHANGE)
	public void chargeOffReceive(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingChargeOffReceive, biddingService.chargeOffReceive(map),head);
	}*/

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_TRANSFER_DEBT)
	public void transferDebt(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingTransferDebt, biddingService.transferDebt(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_REPAY)
	public void repay(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingRepay, biddingService.repay(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_REPAY_PLANE_UPDATE)
	public void repayPlaneUpdate(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingRepayPlaneUpdate, biddingService.repayPlaneUpdate(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BIDDING_CHARGE_OFF_CHANGE)
	public void chargeOffChange(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBiddingChargeOffChange, biddingService.chargeOffChange(map),head);
	}

}

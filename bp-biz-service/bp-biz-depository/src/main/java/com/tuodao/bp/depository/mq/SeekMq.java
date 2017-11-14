package com.tuodao.bp.depository.mq;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.Header;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.depository.service.SeekService;

public class SeekMq extends BaseMq{

	@Autowired
	private SeekService seekService;

	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;

	@Resource(name = "deOutSeekAccountBalanceDetail")
	private Queue deOutSeekAccountBalanceDetail;
	
	@Resource(name = "deOutSeekBiddingBalance")
	private Queue deOutSeekBiddingBalance;
	
	@Resource(name = "deOutSeekBiddingBidDetail")
	private Queue deOutSeekBiddingBidDetail;
	
	@Resource(name = "deOutSeekBiddingInfo")
	private Queue deOutSeekBiddingInfo;
	
	@Resource(name = "deOutSeekRechargeOrderStatus")
	private Queue deOutSeekRechargeOrderStatus;
	
	@Resource(name = "deOutSeekRepayDetail")
	private Queue deOutSeekRepayDetail;
	
	@Resource(name = "deOutFundChangeDetail")
	private Queue deOutSeekFundChangeDetail;
	
	@Resource(name = "deOutFundBalance")
	private Queue deOutSeekFundBalance;
	
	@Resource(name = "deOutOrderStatus")
	private Queue deOutOrderStatus;

	@JmsListener(destination = DepositoryMqConstant.DE_IN_SEEK_FUND_CHANGE_DETAIL)
	void fundChangeDetail(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutSeekFundChangeDetail, seekService.fundChangeDetail(map),head);
	}

	/** 4.6.2资金余额 */
	@JmsListener(destination = DepositoryMqConstant.DE_IN_SEEK_FUND_BALANCE)
	void fundBalance(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutSeekFundBalance, seekService.fundBalance(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_SEEK_REPAY_DETAIL)
	void repayDetail(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutSeekRepayDetail, seekService.repayDetail(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_SEEK_BIDDING_BID_DETAIL)
	void biddingBidDetail(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutSeekBiddingBidDetail, seekService.biddingBidDetail(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_SEEK_BIDDING_INFO)
	void biddingInfo(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutSeekBiddingInfo, seekService.biddingInfo(map),head);
	}

	/** 4.6.6账户余额明细查询 */
	@JmsListener(destination = DepositoryMqConstant.DE_IN_SEEK_ACCOUNT_BALANCE_DETAIL)
	void accountBalanceDetail(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutSeekAccountBalanceDetail, seekService.accountBalanceDetail(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_ORDER_STATUS)
	void orderStatus(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutOrderStatus, seekService.orderStatus(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_SEEK_BIDDING_BALANCE)
	void biddingBalance(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutSeekBiddingBalance, seekService.biddingBalance(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_SEEK_RECHARGE_ORDER_STATUS)
	void rechargeOrderStatus(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutSeekRechargeOrderStatus, seekService.rechargeOrderStatus(map),head);
	}

}

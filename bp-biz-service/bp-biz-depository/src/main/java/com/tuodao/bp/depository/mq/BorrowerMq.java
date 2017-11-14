package com.tuodao.bp.depository.mq;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.depository.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.Header;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.HashMap;

public class BorrowerMq extends BaseMq{

	@Autowired
	private BorrowerService borrowerService;

	@Resource(name = "deOutBorrowerPayBak")
	private Queue deOutBorrowerPayBak;

	@Resource(name = "deOutBorrowerBiddingCompensation")
	private Queue deOutBorrowerBiddingCompensation;

	@Resource(name = "deOutBorrowerBorrowerCompensation")
	private Queue deOutBorrowerBorrowerCompensation;



	@JmsListener(destination = DepositoryMqConstant.DE_IN_BORROWER_BATCH_PAY_BACK)
	void payBak(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBorrowerPayBak, borrowerService.payBak(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BORROWER_BIDDING_COMPENSATION)
	void biddingCompensation(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBorrowerBiddingCompensation, borrowerService.biddingCompensation(map),head);
	}

	@JmsListener(destination = DepositoryMqConstant.DE_IN_BORROWER_BORROWER_COMPENSATION)
	void borrowerCompensation(HashMap<String, String> map,
			@Header(name = DepositoryMqConstant.DEPOSITORY_HEAD_KEY) String head) {
		super.convertAndSend(deOutBorrowerBorrowerCompensation, borrowerService.borrowerCompensation(map),head);
	}


}

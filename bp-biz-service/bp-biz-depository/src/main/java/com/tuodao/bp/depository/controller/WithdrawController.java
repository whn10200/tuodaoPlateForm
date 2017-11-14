package com.tuodao.bp.depository.controller;

import com.tuodao.bp.depository.service.BaseService;
import com.tuodao.bp.model.constant.depository.BBDD;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.depository.key.WithdrawKey;
import com.tuodao.bp.model.mq.AccountCashMqInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.HashMap;

@RestController
public class WithdrawController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WithdrawController.class);

	@Autowired
    private BaseService baseService;
	@Resource
	private JmsMessagingTemplate jmsMessagingTemplate;
	
	@Resource(name = "deOutWithdrawNotifyInvestor")
    private Queue deOutWithdrawNotifyInvestor;

	@RequestMapping(value = "depository/withdraw/notifyInvestor", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	public HashMap<String, String> notifyInvestor(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = baseService.notify(requestMap, WithdrawKey.withDrawApplyReceive);
		
		if (re.get(BJFN.success).equals(BBDD.xtrue)) {
			
			
			AccountCashMqInfo aci=new AccountCashMqInfo();
			aci.fromHashMap(re);
			jmsMessagingTemplate.convertAndSend(deOutWithdrawNotifyInvestor,aci);
		}
		
		return re ;
	}
	
	
}

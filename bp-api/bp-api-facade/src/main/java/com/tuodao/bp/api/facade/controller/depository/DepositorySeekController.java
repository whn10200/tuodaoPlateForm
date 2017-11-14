package com.tuodao.bp.api.facade.controller.depository;

import com.tuodao.bp.api.facade.client.depository.DepositorySeekClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class DepositorySeekController {

	@Autowired
	private DepositorySeekClient depositorySeekClient;

	@RequestMapping(value = "depository/seek/fundChangeDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> fundChangeDetail(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> re = depositorySeekClient.fundChangeDetail(map);
		return re;
	}

	/** 4.6.2资金余额 */
	@RequestMapping(value = "depository/seek/fundBalance", produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> fundBalance(HashMap<String, String> map) {
		HashMap<String, String> re = depositorySeekClient.fundBalance(map);
		return re;
	}

	@RequestMapping(value = "depository/seek/repayDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> repayDetail(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> re = depositorySeekClient.repayDetail(map);
		return re;
	}

	@RequestMapping(value = "depository/seek/biddingBidDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> biddingBidDetail(HashMap<String, String> map) {
		ArrayList<HashMap<String, String>> re = depositorySeekClient.biddingBidDetail(map);
		return re;
	}

	@RequestMapping(value = "depository/seek/biddingInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> biddingInfo(HashMap<String, String> map) {
		HashMap<String, String> re = depositorySeekClient.biddingInfo(map);
		return re;
	}

	/** 4.6.6账户余额明细查询 */
	@RequestMapping(value = "depository/seek/accountBalanceDetail", produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> accountBalanceDetail(HashMap<String, String> map) {
		HashMap<String, String> re = depositorySeekClient.accountBalanceDetail(map);
		return re;
	}

	@RequestMapping(value = "depository/seek/orderStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> orderStatus(HashMap<String, String> map) {
		HashMap<String, String> re = depositorySeekClient.orderStatus(map);
		return re;
	}

	@RequestMapping(value = "depository/seek/biddingBalance", produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> biddingBalance(HashMap<String, String> map) {
		HashMap<String, String> re = depositorySeekClient.biddingBalance(map);
		return re;
	}

	@RequestMapping(value = "depository/seek/rechargeOrderStatus", produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> rechargeOrderStatus(HashMap<String, String> map) {
		HashMap<String, String> re = depositorySeekClient.rechargeOrderStatus(map);
		return re;
	}

}

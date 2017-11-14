package com.tuodao.bp.depository.controller;

import com.tuodao.bp.depository.service.SeekService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class SeekControlle {

	@Autowired
	private SeekService seekService;

	@RequestMapping(value = "depository/seek/fundChangeDetail", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> fundChangeDetail(@RequestBody HashMap<String, String> requestMap) {
		ArrayList<HashMap<String, String>> re = seekService.fundChangeDetail(requestMap);
		return re;
	}

	/** 4.6.2资金余额 */
	@RequestMapping(value = "depository/seek/fundBalance", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> fundBalance(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = seekService.fundBalance(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/seek/repayDetail", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> repayDetail(@RequestBody HashMap<String, String> requestMap) {
		ArrayList<HashMap<String, String>> re = seekService.repayDetail(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/seek/biddingBidDetail", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> biddingBidDetail(@RequestBody HashMap<String, String> requestMap) {
		ArrayList<HashMap<String, String>> re = seekService.biddingBidDetail(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/seek/biddingInfo", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> biddingInfo(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = seekService.biddingInfo(requestMap);
		return re;
	}

	/** 4.6.6账户余额明细查询 */
	@RequestMapping(value = "depository/seek/accountBalanceDetail", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> accountBalanceDetail(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = seekService.accountBalanceDetail(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/seek/orderStatus", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> orderStatus(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = seekService.orderStatus(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/seek/biddingBalance", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> biddingBalance(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = seekService.biddingBalance(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/seek/rechargeOrderStatus", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> rechargeOrderStatus(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = seekService.rechargeOrderStatus(requestMap);
		return re;
	}

}

package com.tuodao.bp.depository.controller;

import com.tuodao.bp.depository.service.BiddingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BiddingController {

	@Autowired
	private BiddingService biddingService;

	@RequestMapping(value = "depository/Bidding/publish", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> publish(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.publish(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/complate", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> complate(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.complate(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/cancel", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> cancel(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.cancel(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/bid", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> bid(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.bid(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/chargeOff", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> chargeOff(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.chargeOff(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/chargeOffReceive", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> chargeOffReceive(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.chargeOffReceive(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/transferDebt", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> transferDebt(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.transferDebt(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/repay", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> repay(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.repay(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/repayPlaneUpdate", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> repayPlaneUpdate(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.repayPlaneUpdate(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/Bidding/chargeOffChange", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> chargeOffChange(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = biddingService.chargeOffChange(requestMap);
		return re;
	}

}

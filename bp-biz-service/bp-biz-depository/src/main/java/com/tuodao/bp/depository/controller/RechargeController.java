package com.tuodao.bp.depository.controller;

import com.tuodao.bp.depository.service.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RechargeController {

	@Autowired
	private RechargeService rechargeService;

	@RequestMapping(value = "depository/rechargeService/gatewayRecharge", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> gatewayRecharge(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = rechargeService.gatewayRecharge(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/rechargeService/rechargeReceive", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> rechargeReceive(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = rechargeService.rechargeReceive(requestMap);
		return re;
	}
	@RequestMapping(value = "depository/rechargeService/quickRechargeApply", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> quickRechargeApply(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = rechargeService.quickRechargeApply(requestMap);
		return re;
	}
	@RequestMapping(value = "depository/rechargeService/quickRechargeConfirm", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> quickRechargeConfirm(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = rechargeService.quickRechargeConfirm(requestMap);
		return re;
	}
	@RequestMapping(value = "depository/rechargeService/borrowCutRepay", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> borrowCutRepay(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = rechargeService.borrowCutRepay(requestMap);
		return re;
	}



}

package com.tuodao.bp.depository.controller;

import com.tuodao.bp.depository.service.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PlatformController {

	@Autowired
	private PlatformService platformService;

	@RequestMapping(value = "depository/platform/fundFreeze", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> fundFreeze(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = platformService.fundFreeze(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/platform/fundUnblock", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> fundUnblock(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = platformService.fundUnblock(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/platform/platformConverse", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> platformConverse(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = platformService.platformConverse(requestMap);
		return re;
	}
	@RequestMapping(value = "depository/platform/platformRecharge", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> platformRecharge(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = platformService.platformRecharge(requestMap);
		return re;
	}
	@RequestMapping(value = "depository/platform/platformTransfer", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> platformTransfer(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = platformService.platformTransfer(requestMap);
		return re;
	}
	@RequestMapping(value = "depository/platform/platformWithDraw", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
			HashMap<String, String> platformWithDraw(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = platformService.platformWithDraw(requestMap);
		return re;
	}


}

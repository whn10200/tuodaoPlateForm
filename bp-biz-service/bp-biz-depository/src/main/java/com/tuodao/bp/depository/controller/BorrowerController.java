package com.tuodao.bp.depository.controller;

import com.tuodao.bp.depository.service.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class BorrowerController {

	@Autowired
	private BorrowerService borrowerService;

	@RequestMapping(value = "depository/borrower/payBak", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> payBak(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = borrowerService.payBak(requestMap);
		return re;
	}


	@RequestMapping(value = "depository/borrower/biddingCompensation", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> biddingCompensation(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = borrowerService.biddingCompensation(requestMap);
		return re;
	}

	@RequestMapping(value = "depository/borrower/borrowerCompensation", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> borrowerCompensation(@RequestBody HashMap<String, String> requestMap) {
		HashMap<String, String> re = borrowerService.borrowerCompensation(requestMap);
		return re;
	}


}

package com.tuodao.bp.product.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 标的相关Client</br>
 * 
 */
@FeignClient("BIZ-DEPOSITORY")
public interface DepositoryBiddingClient {

	/**标的，发标*/
	@RequestMapping(value = "depository/bidding/publish", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> publish(@RequestBody HashMap<String, String> requestMap);
	/**标的，成标*/
	@RequestMapping(value = "depository/bidding/complate", method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> complate(@RequestBody HashMap<String, String> requestMap);
	
	/**标的，撤标</br>
	 * withdraw用于撤销，提现。所以改为cancel*/
	@RequestMapping(value = "depository/bidding/cancel", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> cancel(@RequestBody HashMap<String, String> requestMap);
 
 
 
}

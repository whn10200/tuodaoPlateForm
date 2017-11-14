package com.tuodao.bp.api.facade.client.depository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 标的相关Client</br>
 * 传入参数格式</br>
 * 金额：分</br>
 * 时间：毫秒时间戳</br>
 * 用户ID：userID无需传银行开户号</br>
 * 
 * @author: 杨超凯
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

	/**标的，投标*/
	@RequestMapping(value = "depository/bidding/bid", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> bid(@RequestBody HashMap<String, String> requestMap);

	/**标的，成标出账*/
	@RequestMapping(value = "depository/bidding/chargeOff", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> chargeOff(@RequestBody HashMap<String, String> requestMap);

	/**标的，成交出账通知*/
	@RequestMapping(value = "depository/bidding/chargeOffReceive", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> chargeOffReceive(@RequestBody HashMap<String, String> requestMap);

	/**标的，标的转让*/
	@RequestMapping(value = "depository/bidding/transferDebt", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> transferDebt(@RequestBody HashMap<String, String> requestMap);

	/**标的，成交出账通知*/
	@RequestMapping(value = "depository/bidding/repay", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> repay(@RequestBody HashMap<String, String> requestMap);

	/**标的，还款计划更新*/
	@RequestMapping(value = "depository/bidding/repayPlaneUpdate", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> repayPlaneUpdate(@RequestBody HashMap<String, String> requestMap);

	/**标的，出账信息修改*/
	@RequestMapping(value = "depository/bidding/chargeOffChange", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> chargeOffChange(@RequestBody HashMap<String, String> requestMap);
	
	/**标的，撤销投资*/
	@RequestMapping(value = "depository/bidding/cancelBid", method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> cancelBid(@RequestBody HashMap<String, String> requestMap);

}

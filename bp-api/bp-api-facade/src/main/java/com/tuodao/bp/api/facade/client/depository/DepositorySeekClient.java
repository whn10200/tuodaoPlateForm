package com.tuodao.bp.api.facade.client.depository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 查询相关Client</br>
 * 传入参数格式</br>
 * 金额：分</br>
 * 时间：毫秒时间戳</br>
 * 用户ID：userID无需传银行开户号</br>
 * 
 * @author: 杨超凯
 * 
 */
@FeignClient("BIZ-DEPOSITORY")
	public interface DepositorySeekClient {

	/**资金变动明细查询*/
	@RequestMapping(value = "depository/seek/fundChangeDetail", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> fundChangeDetail(@RequestBody HashMap<String, String> requestMap);

	/** 资金余额查询 */
	@RequestMapping(value = "depository/seek/fundBalance", method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> fundBalance(@RequestBody HashMap<String, String> requestMap);

	/** 还款明细查询 */
	@RequestMapping(value = "depository/seek/repayDetail", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> repayDetail(@RequestBody HashMap<String, String> requestMap);

	/** 标的投资明细查询 */
	@RequestMapping(value = "depository/seek/biddingBidDetail", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	ArrayList<HashMap<String, String>> biddingBidDetail(@RequestBody HashMap<String, String> requestMap);
	
	/** 标的信息查询 */
	@RequestMapping(value = "depository/seek/biddingInfo", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> biddingInfo(@RequestBody HashMap<String, String> requestMap);

	/** 账户余额明细查询 */
	@RequestMapping(value = "depository/seek/accountBalanceDetail", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> accountBalanceDetail(@RequestBody HashMap<String, String> requestMap);

	/** 订单状态查询 */
	@RequestMapping(value = "depository/seek/orderStatus", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> orderStatus(@RequestBody HashMap<String, String> requestMap);

	/** 标的账户余额查询 */
	@RequestMapping(value = "depository/seek/biddingBalance", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> biddingBalance(@RequestBody HashMap<String, String> requestMap);

	/** 充值订单状态查询 */
	@RequestMapping(value = "depository/seek/rechargeOrderStatus", method=RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	HashMap<String, String> rechargeOrderStatus(@RequestBody HashMap<String, String> requestMap);

}

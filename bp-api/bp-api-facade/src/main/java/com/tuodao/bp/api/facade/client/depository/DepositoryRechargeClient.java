package com.tuodao.bp.api.facade.client.depository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 充值相关Client</br>
 * 传入参数格式</br>
 * 金额：分</br>
 * 时间：毫秒时间戳</br>
 * 用户ID：userID无需传银行开户号</br>
 * 
 * @author: 杨超凯
 * 
 */
@FeignClient("BIZ-DEPOSITORY")
public interface DepositoryRechargeClient {

	/**网关充值*/
    @RequestMapping(value = "depository/recharge/gatewayRecharge",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> gatewayRecharge(@RequestBody HashMap<String, String> requestMap);

    /**充值通知*/
    @RequestMapping(value = "depository/recharge/rechargeReceive",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> rechargeReceive(@RequestBody HashMap<String, String> requestMap);

    /**快捷充值申请*/
    @RequestMapping(value = "depository/recharge/quickRechargeApply",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> quickRechargeApply(@RequestBody HashMap<String, String> requestMap);
    /**快捷充值确认*/
    @RequestMapping(value = "depository/recharge/quickRechargeConfirm",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> quickRechargeConfirm(@RequestBody HashMap<String, String> requestMap);

    /**借款人线下还款*/
    @RequestMapping(value = "depository/recharge/borrowCutRepay",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> borrowCutRepay(@RequestBody HashMap<String, String> requestMap);
    


    
}

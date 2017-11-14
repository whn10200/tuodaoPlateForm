package com.tuodao.bp.api.facade.client.depository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 平台相关Client</br>
 * 传入参数格式</br>
 * 金额：分</br>
 * 时间：毫秒时间戳</br>
 * 用户ID：userID无需传银行开户号</br>
 * 
 * @author: 杨超凯
 * 
 */
@FeignClient("BIZ-DEPOSITORY")
public interface DepositoryPlatformClient {

	/**资金冻结*/
    @RequestMapping(value = "depository/platform/fundFreeze",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> fundFreeze(@RequestBody HashMap<String, String> requestMap);

    /**资金解冻*/
    @RequestMapping(value = "depository/platform/fundUnblock",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> fundUnblock(@RequestBody HashMap<String, String> requestMap);

    /**平台自有账户的不同子账户转账*/
    @RequestMapping(value = "depository/platform/platformConverse",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> platformConverse(@RequestBody HashMap<String, String> requestMap);
    /**平台充值*/
    @RequestMapping(value = "depository/platform/platformRecharge",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> platformRecharge(@RequestBody HashMap<String, String> requestMap);
    /**平台账户转个人账户*/
    @RequestMapping(value = "depository/platform/platformTransfer",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> platformTransfer(@RequestBody HashMap<String, String> requestMap);
    /**平台提现*/
    @RequestMapping(value = "depository/platform/platformWithDraw",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> platformWithDraw(@RequestBody HashMap<String, String> requestMap);
    


    
}

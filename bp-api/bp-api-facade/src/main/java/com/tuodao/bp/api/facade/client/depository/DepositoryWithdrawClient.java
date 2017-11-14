package com.tuodao.bp.api.facade.client.depository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 提现相关Client</br>
 * 传入参数格式</br>
 * 金额：分</br>
 * 时间：毫秒时间戳</br>
 * 用户ID：userID无需传银行开户号</br>
 * 
 * @author: 杨超凯
 * 
 */
@FeignClient("BIZ-DEPOSITORY")
public interface DepositoryWithdrawClient {

	/**投资人提现*/
    @RequestMapping(value = "depository/withdraw/notifyInvestor",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> notifyInvester(@RequestBody HashMap<String, String> requestMap);
    


    
}

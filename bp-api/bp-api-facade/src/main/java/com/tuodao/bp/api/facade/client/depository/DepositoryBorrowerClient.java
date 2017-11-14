package com.tuodao.bp.api.facade.client.depository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 借款人相关Client</br>
 * 传入参数格式</br>
 * 金额：分</br>
 * 时间：毫秒时间戳</br>
 * 用户ID：userID无需传银行开户号</br>
 * 
 * @author: 杨超凯
 * 
 */
@FeignClient("BIZ-DEPOSITORY")
public interface DepositoryBorrowerClient {

	/**借款人，还款*/
    @RequestMapping(value = "depository/borrower/payBack",method= RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> payBack(@RequestBody HashMap<String, String> requestMap);

    /**借款人，标的代偿（委托）还款*/
    @RequestMapping(value = "depository/borrower/biddingCompensation",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> biddingCompensation(@RequestBody HashMap<String, String> requestMap);

    /**借款人，借款人还款代偿（委托）*/
    @RequestMapping(value = "depository/borrower/borrowerCompensation",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
            HashMap<String, String> borrowerCompensation(@RequestBody HashMap<String, String> requestMap);

}

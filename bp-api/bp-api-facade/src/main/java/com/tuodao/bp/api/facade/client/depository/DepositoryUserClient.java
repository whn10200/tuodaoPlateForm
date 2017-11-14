package com.tuodao.bp.api.facade.client.depository;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * 用户相关Client</br>
 * 传入参数格式</br>
 * 金额：分</br>
 * 时间：毫秒时间戳</br>
 * 用户ID：userID无需传银行开户号</br>
 * 
 * @author: 杨超凯
 * 
 */
@FeignClient("BIZ-DEPOSITORY")
public interface DepositoryUserClient {

	/**4要素注册*/
    @RequestMapping(value = "depository/user/regist4Ele",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> regist4Ele(@RequestBody  HashMap<String, String> requestMap);

    /**实名注册*/
    @RequestMapping(value = "depository/user/registRealname",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String>  registRealname(@RequestBody HashMap<String, String> requestMap);

    /**换卡*/
    @RequestMapping(value = "depository/user/changeCard",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> changeCard(@RequestBody HashMap<String, String> requestMap);

    /**短验绑卡（可包含开户）申请*/
    @RequestMapping(value = "depository/user/messageBoundCardApply",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> messageBoundCardApply(@RequestBody HashMap<String, String> requestMap);

    /**短验绑卡（可包含开户）确认*/
    @RequestMapping(value = "depository/user/messageBoundCardValidate",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> messageBoundCardValidate(@RequestBody HashMap<String, String> requestMap);

    /**绑卡*/
    @RequestMapping(value = "depository/user/boundCard",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> boundCard(@RequestBody HashMap<String, String> requestMap);

    /**客户信息变更*/
    @RequestMapping(value = "depository/user/updateInfo",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> updateInfo(@RequestBody HashMap<String, String> requestMap);
    
}

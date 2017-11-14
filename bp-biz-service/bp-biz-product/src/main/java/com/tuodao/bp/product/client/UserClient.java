package com.tuodao.bp.product.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;

 
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface UserClient {
    /**
     * 获取用户信息
     *
     * @param pojo
     * @return
     */
    @RequestMapping(value = "ua/getUserAccountInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserAccountInfo getUserAccountInfo(BasePojo pojo);
    /**
     * 获取用户开户信息
     *
     * @param pojo
     * @return
     */
    @RequestMapping(value = "ua/account/getUserDepositInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserDepositOutput getUserDepositInfo(BasePojo basePojo);


}

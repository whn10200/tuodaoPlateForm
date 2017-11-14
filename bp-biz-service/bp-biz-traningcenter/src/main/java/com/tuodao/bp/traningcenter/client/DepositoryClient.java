package com.tuodao.bp.traningcenter.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;

/**
 * @author tookbra
 * @date 2017/11/1
 * @description
 */
@FeignClient(value = "BIZ-DEPOSITORY")
public interface DepositoryClient {

    @RequestMapping(value = "depository/rechargeService/quickRechargeConfirm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    HashMap<String, String> quickRechargeConfirm(HashMap<String, String> map);
}

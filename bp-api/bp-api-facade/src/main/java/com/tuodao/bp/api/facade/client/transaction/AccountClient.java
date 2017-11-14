package com.tuodao.bp.api.facade.client.transaction;

import com.tuodao.bp.model.traningcenter.input.AccountInput;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description: 调用资金账户接口
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 17:46
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface AccountClient {


    /**
     * 获取用户资金账户信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "/account/getUserAccount",consumes = MediaType.APPLICATION_JSON_VALUE)
    AccountOutput getUserAccount(@RequestParam("userId")String userId);



}

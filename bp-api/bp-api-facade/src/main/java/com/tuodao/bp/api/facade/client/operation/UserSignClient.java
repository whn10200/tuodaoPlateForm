package com.tuodao.bp.api.facade.client.operation;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.output.CheckUserSignOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: 运营中心 用户签到 FEIGN
 * User: zkai
 * Date: 2017/9/26
 * Time: 10:06
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-OPERATION")
public interface UserSignClient {
    /**
     * 用户签到
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "/userSign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    int userSign(BasePojo input);

    /**
     * 用户是否已签到
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "/checkSign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    CheckUserSignOutput checkSign(BasePojo input);

}

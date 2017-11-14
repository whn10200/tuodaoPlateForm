package com.tuodao.bp.api.facade.client.operation;

import com.tuodao.bp.model.business.operation.input.OpCooperationInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: 加盟管理 FEIGN
 * Author: yinping
 * Date: 2017/9/22
 * Time: 17:26
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-OPERATION")
public interface OpCooperationClient {
    /**
     * 新增加盟管理
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "cooperation/addCooperation", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addCooperation(OpCooperationInput input);
}

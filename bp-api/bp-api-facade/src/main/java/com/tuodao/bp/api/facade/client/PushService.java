package com.tuodao.bp.api.facade.client;

import com.tuodao.bp.model.business.common.input.PushInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: 消息推送
 * User: zkai
 * Date: 2017/9/4
 * Time: 11:18
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value="BP-COMMON-MS")
public interface PushService {
    @RequestMapping(value="/common/pushMessage",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    void pushMessage(@RequestBody PushInput input);
}

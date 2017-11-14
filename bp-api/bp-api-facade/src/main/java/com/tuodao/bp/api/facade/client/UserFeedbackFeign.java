package com.tuodao.bp.api.facade.client;

import com.tuodao.bp.model.business.useraccount.input.SAddUserFeedBack;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description:聚合-用户反馈相关
 * User: zkai
 * Date: 2017/9/12
 * Time: 15:59
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value="BIZ-USER-ACCOUNT")
public interface UserFeedbackFeign {

    @RequestMapping(value="/ua/addUserFeedBack",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    void addUserFeedBack(@RequestBody SAddUserFeedBack input);
}

package com.tuodao.bp.api.facade.client.operation;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.output.UserOperationStatisOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Description:
 * User: zkai
 * Date: 2017/10/30
 * Time: 14:00
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-OPERATION")
public interface UserOperationDataClient {

    /**
     * 交互文档_PC重构0914/index.html#g=1&p=我的帐户-账户总览
     * 获取用户运营数据
     * @param input
     * @return
     */
    @RequestMapping(value = "/userOperation/getUserOperationData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<UserOperationStatisOutput> getUserOperationData(BasePojo input);
}

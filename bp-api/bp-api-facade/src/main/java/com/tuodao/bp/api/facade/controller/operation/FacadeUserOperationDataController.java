package com.tuodao.bp.api.facade.controller.operation;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.UserOperationDataClient;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.output.UserOperationStatisOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 聚合 - 用户运营数据controller
 * User: zkai
 * Date: 2017/10/30
 * Time: 13:57
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router/op")
@RestController
public class FacadeUserOperationDataController {
    private static final Logger logger = LoggerFactory.getLogger(FacadeUserSignController.class);

    @Resource
    private UserOperationDataClient userOperationDataClient;
    /**
     * 交互文档_PC重构0914/index.html#g=1&p=我的帐户-账户总览
     * 获取用户运营数据
     * @param input
     * @return
     */
    @RequestMapping(value="/getUserOperationData",method= RequestMethod.POST)
    @AccessToken(checkAccess=true)
    public RespResult<List<UserOperationStatisOutput>> getUserOperationData(BasePojo input){
        logger.info("获取用户运营数据 input={}", JSON.toJSONString(input));
        List<UserOperationStatisOutput> outputs = userOperationDataClient.getUserOperationData(input);
        return RespResult.<List<UserOperationStatisOutput>>create().setContent(outputs);
    }
}

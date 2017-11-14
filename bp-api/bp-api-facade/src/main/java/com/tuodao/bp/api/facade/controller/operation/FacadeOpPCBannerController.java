package com.tuodao.bp.api.facade.controller.operation;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.OpPCBannerClient;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.input.OpBannerManagerInput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.operation.output.OpBannerManagelListOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: banner管理（PC）聚合
 * Author: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router/op")
@RestController
public class FacadeOpPCBannerController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FacadeOpPCBannerController.class);

    @Autowired
    private OpPCBannerClient opPCBannerClient;

    /**
     * 根据contentRemark查询内容管理
     * @param input
     * @return
     */
    @RequestMapping(value="/selectPCBannerByParams",method= RequestMethod.POST)
    @AccessToken(checkAccess=false,access = {AccessType.PC})
    public RespResult selectPCBanner(OpBannerManagerInput input){
        logger.info("查询banner管理（PC） input={}", JSON.toJSONString(input));
        List<OpBannerManagelListOutput> list = opPCBannerClient.selectBannerByParams(input);
        return RespResult.create().setContent(list);
    }
}

package com.tuodao.bp.api.facade.controller.operation;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.OpContentClient;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.input.OpContentInput;
import com.tuodao.bp.model.business.operation.input.OpContentTitleInput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.operation.output.OpContentManagelListOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: 内容管理聚合
 * Author: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router/op")
@RestController
public class FacadeOpContentController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FacadeOpContentController.class);

    @Autowired
    private OpContentClient opContentClient;

    /**
     * 根据contentRemark查询标题列表和发布时间
     * @param input
     * @return
     */
    @RequestMapping(value="/selectContentByContentRemark",method= RequestMethod.POST)
    @AccessToken(checkAccess=false,access = {AccessType.APP,AccessType.PC})
    public RespResult selectContent(OpContentTitleInput input){
        logger.info("查询内容管理（理财百科、媒体报道、公告） input={}", JSON.toJSONString(input));
        List<OpContentManagelListOutput> list = opContentClient.selectContentTitleByContentRemark(input);
        return RespResult.create().setContent(list);
    }
    /**
     * 根据contentId查询内容管理
     * @param input
     * @return
     */
    @RequestMapping(value="/selectContentByContentId",method= RequestMethod.POST)
    @AccessToken(checkAccess=false,access = {AccessType.APP,AccessType.PC})
    public RespResult selectContentByContentId(OpContentInput input){
        logger.info("查询内容管理（理财百科、媒体报道、公告） input={}", JSON.toJSONString(input));
        List<OpContentManagelListOutput> list = opContentClient.selectContentByContentId(input);
        return RespResult.create().setContent(list);
    }
}

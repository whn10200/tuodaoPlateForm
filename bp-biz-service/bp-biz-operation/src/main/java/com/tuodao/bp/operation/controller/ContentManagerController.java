package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.business.operation.input.OpContentInput;
import com.tuodao.bp.model.business.operation.input.OpContentTitleInput;
import com.tuodao.bp.model.facade.operation.output.OpContentManagelListOutput;
import com.tuodao.bp.operation.service.IOpContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: 内容管理controller
 * author: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "/content")
public class ContentManagerController {
    private static final Logger logger = LoggerFactory.getLogger(ContentManagerController.class);
    @Resource
    private IOpContentService iOpContentService;

    /**
     * 根据contentRemark查询内容管理标题列表和发布时间
     *
     * @param input
     */
    @RequestMapping(value = "/selectContentTitle", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OpContentManagelListOutput> selectContentByContentRemark(OpContentTitleInput input) {
        logger.info("查询内容管理标题列表开始："+input);
        return iOpContentService.selectContentTitle(input);
    }
    /**
     * 根据contentId查询内容管理
     *
     * @param input
     */
    @RequestMapping(value = "/selectContent", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OpContentManagelListOutput> selectContentByContentId(OpContentInput input) {
        logger.info("查询内容管理开始："+input);
        return iOpContentService.selectContentManager(input);
    }

}

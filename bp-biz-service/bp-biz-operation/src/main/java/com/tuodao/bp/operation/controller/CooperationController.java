package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.business.operation.input.OpCooperationInput;
import com.tuodao.bp.operation.service.IOpCooperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Description: 加盟管理controller
 * author: yinping
 * Date: 2017/9/22
 * Time: 15:44
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "/cooperation")
public class CooperationController {
    private static final Logger logger = LoggerFactory.getLogger(CooperationController.class);
    @Resource
    private IOpCooperationService iOpCooperationService;

    /**
     * 新增用户加盟
     *
     * @param input
     */
    @RequestMapping(value = "/addOpCooperation", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void addOpCooperation(OpCooperationInput input) {
        logger.info("新增加盟商开始： "+input);
        iOpCooperationService.addCooperationManage(input);
    }

}

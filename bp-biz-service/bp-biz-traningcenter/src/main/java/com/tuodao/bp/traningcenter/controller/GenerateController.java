package com.tuodao.bp.traningcenter.controller;

import com.tuodao.bp.traningcenter.service.impl.GenerateService;
import com.tuodao.bp.model.BasePojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/13
 * @time: 18:23
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
public class GenerateController {

    @Autowired
    private GenerateService generateService;

    @RequestMapping(value = "/getGenerateId",consumes = MediaType.APPLICATION_JSON_VALUE)
    public String getGenerateId(BasePojo pojo){
        return generateService.get();
    }
}

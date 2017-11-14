package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.business.operation.input.OpBannerManagerInput;
import com.tuodao.bp.model.facade.operation.output.OpBannerManagelListOutput;
import com.tuodao.bp.operation.persistence.model.basic.OpBannerManager;
import com.tuodao.bp.operation.service.IOpBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Description: banner管理controller
 * author: yinping
 * Date: 2017/11/2
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "/banner")
public class BannerManagerController {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(BannerManagerController.class);
    @Resource
    private IOpBannerService iOpBannerService;
    /**
     * 根据params查询pc banner
     *
     * @param input
     */
    @RequestMapping(value = "/selectPCBanner", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OpBannerManagelListOutput> selectPCBannerByParams(OpBannerManagerInput input) {
        logger.info("查询PC banner开始："+input);
        OpBannerManager om = new OpBannerManager();
        BeanUtils.copyProperties(input, om);
        om.setBannerRemark("PC");
        return iOpBannerService.selectBanner(om);
    }

    /**
     * 根据params查询app banner
     *
     * @param input
     */
    @RequestMapping(value = "/selectAPPBanner", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<OpBannerManagelListOutput> selectAPPBannerByParams(OpBannerManagerInput input) {
        logger.info("查询APP banner开始："+input);
        OpBannerManager om = new OpBannerManager();
        BeanUtils.copyProperties(input, om);
        om.setBannerRemark("APP");
        return iOpBannerService.selectBanner(om);
    }

}

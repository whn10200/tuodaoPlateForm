package com.tuodao.bp.api.facade.controller.common;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.service.common.IFacadeCommonService;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.common.output.SmsOutput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.common.input.FacadeSmsInput;
import com.tuodao.bp.model.facade.common.input.FacadeValidateSmsCodeInput;
import com.tuodao.bp.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 短信服务
 * @author: mif
 * @date: 2017/9/12
 * @time: 11:44
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "router/common")
public class FacadeSmsController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FacadeSmsController.class);

    @Resource
    private IFacadeCommonService facadeCommonService;

    /**
     * 发送验证短信
     *
     * @param request
     * @param input
     * @return
     */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    @AccessToken(checkAccess = false, access = {AccessType.PC, AccessType.APP})
    public RespResult sendSms(HttpServletRequest request, FacadeSmsInput input) {
        logger.info("sendSms input={}", input);
        facadeCommonService.sendSms(input, WebUtils.getClientIP(request));
        return RespResult.<SmsOutput>create();
    }

    /**
     * 验证短信验证码
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "/validateSmsCode", method = RequestMethod.POST)
    @AccessToken(checkAccess = false, access = {AccessType.APP, AccessType.PC})
    public RespResult validateSmsCode(FacadeValidateSmsCodeInput input) {
        facadeCommonService.validateSmsCode(input);
        return RespResult.<SmsOutput>create();
    }


}

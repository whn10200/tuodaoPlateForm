package com.tuodao.bp.api.facade.controller.useraccount;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.service.useraccount.IFacadeUserFeedbackService;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.facade.useraccout.input.FacadeAddUserFeedBack;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 聚合-用户反馈
 * User: zkai
 * Date: 2017/9/12
 * Time: 15:56
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router/ua")
@RestController
public class FacadeUserFeedbackController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FacadeUserFeedbackController.class);

    @Autowired
    private IFacadeUserFeedbackService fUserFeedbackService;

    /**
     * 添加用户反馈
     * @param input
     * @return
     */
    @RequestMapping(value="/addUserFeedBack",method= RequestMethod.POST)
	@AccessToken(checkAccess=false)
    public RespResult<String> addUserFeedBack(FacadeAddUserFeedBack input){
        logger.info("添加用户反馈");
        fUserFeedbackService.addUserFeedBack(input);
        return RespResult.<String>create();
    }
}

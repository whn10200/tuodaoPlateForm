package com.tuodao.bp.api.facade.service.useraccount.impl;

import com.tuodao.bp.api.facade.client.UserFeedbackFeign;
import com.tuodao.bp.api.facade.service.BaseService;
import com.tuodao.bp.api.facade.service.useraccount.IFacadeUserFeedbackService;
import com.tuodao.bp.model.constant.facade.FacadeConstants;
import com.tuodao.bp.model.business.useraccount.input.SAddUserFeedBack;
import com.tuodao.bp.model.facade.useraccout.input.FacadeAddUserFeedBack;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:
 * User: zkai
 * Date: 2017/9/14
 * Time: 10:59
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class FacadeUserFeedbackServiceImpl extends BaseService implements IFacadeUserFeedbackService {

    @Autowired
    private UserFeedbackFeign userFeedbackFeign;


    @Override
    public void addUserFeedBack(FacadeAddUserFeedBack input) {
        // 用户没有登入（传入的userId为空），校验短信验证码
        if(StringUtils.isBlank(input.getUserId())){
            checkSmsCode(input.getMobile(),input.getSmsCode(),FacadeConstants.SMS_TYPE_FEEDBACK);

        }
        // 添加用户反馈
        SAddUserFeedBack sAddUserFeedBack = new SAddUserFeedBack();
        BeanUtils.copyProperties(input,sAddUserFeedBack);
        userFeedbackFeign.addUserFeedBack(sAddUserFeedBack);
    }
}

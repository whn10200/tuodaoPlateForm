package com.tuodao.bp.useraccount.service.impl;

import com.tuodao.bp.model.business.useraccount.input.SAddUserFeedBack;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserFeedbackMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.UserFeedback;
import com.tuodao.bp.useraccount.service.IUserFeedBackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Description: 用户反馈相关实现
 * User: zkai
 * Date: 2017/9/12
 * Time: 14:25
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class UserFeedbackImpl implements IUserFeedBackService {
    private static final Logger logger = LoggerFactory.getLogger(UserFeedbackImpl.class);

    @Autowired
    private UserFeedbackMapper userFeedbackMapper;

    /**
     * 添加用户反馈
     * @param addUserFeedBack
     */
    @Override
    public void addUserFeedBack(SAddUserFeedBack addUserFeedBack) {
        UserFeedback userFeedback = new UserFeedback();
        BeanUtils.copyProperties(addUserFeedBack,userFeedback);
        userFeedback.setGmtCreate(new Date());
        userFeedback.setGmtCreator(addUserFeedBack.getMobile());
        int result = userFeedbackMapper.insertSelective(userFeedback);
        if(result != 1){
            logger.error("添加用户反馈失败");
            throw new BizFeignException(UaRespExceptionConstant.FEEDBACK_ADD_ERROR);
        }
    }
}

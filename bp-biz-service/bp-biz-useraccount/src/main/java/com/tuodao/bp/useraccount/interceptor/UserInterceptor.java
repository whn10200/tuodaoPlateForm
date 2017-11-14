package com.tuodao.bp.useraccount.interceptor;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.service.UserBaseService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @description: 判断用户是否存在
 * @author: mif
 * @date: 2017/8/28
 * @time: 16:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Aspect
@Order(-1)
@Component
public class UserInterceptor extends UserBaseService {
    private static final Logger logger = LoggerFactory.getLogger(UserInterceptor.class);

    @Pointcut("execution(public * com.tuodao.bp.useraccount.controller..*(..)) && !@annotation(com.tuodao.bp.useraccount.interceptor.UnableValidate)")
    public void existValidate() {
    }

    @Before("existValidate()")
    public void validateUser(JoinPoint point) {
        Object[] args = point.getArgs();
        Object obj = args[0];
        BasePojo input = (BasePojo) obj;
        if (null == input.getUserId()) {
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_USER_ID_CAN_NOT_BE_NULL);
        }

        UserAccountInfo userAccoutInfo = super.getUserAccountInfo(input.getUserId());
        if (null == userAccoutInfo) {
            logger.info("用户不存在，userid={}", input.getUserId());
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_USER_NOT_EXISTENCE);
        }
    }
}

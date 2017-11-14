package com.tuodao.bp.common.interceptor;

import com.tuodao.bp.common.persistence.mapper.basic.PushLogMapper;
import com.tuodao.bp.common.persistence.model.basic.PushLog;
import com.tuodao.bp.model.business.common.input.PushInput;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Description: 推送消息日志记录
 * User: zkai
 * Date: 2017/9/27
 * Time: 16:22
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Aspect
@Component
@Async
public class PushLogInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(PushLogInterceptor.class);

    @Value("${pushTool}")
    private String pushTool;

    @Autowired
    private PushLogMapper pushLogMapper;

    @AfterThrowing(value = "within(com.tuodao.bp.common.controller.PushController)",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) throws Throwable {

        Object[] args = joinPoint.getArgs();
        PushInput pushInput = (PushInput) args[0];
        logger.info("pushInput={}", pushInput);
        try {
            PushLog pushLog = new PushLog();
            pushLog.setPushObject(pushInput.getPushObject());
            pushLog.setPushContent(pushInput.getPushContent());
            pushLog.setPushAlias(pushInput.getPushAlias());
            pushLog.setPushResult("ERROR");
            pushLog.setErrorInfo(e.getMessage());
            pushLog.setPushTool(Integer.valueOf(pushTool));
            pushLogMapper.insertSelective(pushLog);
        }catch (Exception e1){
            logger.error("插入消息推送日志表异常:e={}",e1);
        }


    }

    @AfterReturning("within(com.tuodao.bp.common.controller.PushController)")
    public void afterReturning(JoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();
        PushInput pushInput = (PushInput) args[0];
        logger.info("pushInput={}", pushInput);
        try {
            PushLog pushLog = new PushLog();
            pushLog.setPushObject(pushInput.getPushObject());
            pushLog.setPushContent(pushInput.getPushContent());
            pushLog.setPushAlias(pushInput.getPushAlias());
            pushLog.setPushResult("SUCCESS");
            pushLog.setPushTool(Integer.valueOf(pushTool));
            pushLogMapper.insertSelective(pushLog);
        }catch (Exception e1){
            logger.error("插入消息推送日志表异常:e={}",e1);
        }

    }

}

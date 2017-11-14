package com.tuodao.bp.common.service.async;

import com.tuodao.bp.common.persistence.mapper.basic.EmailLogMapper;
import com.tuodao.bp.common.persistence.model.basic.EmailLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 邮件日志
 * @author: mif
 * @date: 2017/9/11
 * @time: 11:23
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class EmailLogService {

    @Resource
    private EmailLogMapper emailLogMapper;

    @Async
    public void insertEmailLog(EmailLog emailLog) {
        emailLogMapper.insertSelective(emailLog);
    }
}

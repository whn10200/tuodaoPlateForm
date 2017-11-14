package com.tuodao.bp.common.service.async;

import com.tuodao.bp.common.persistence.mapper.basic.SmsLogMapper;
import com.tuodao.bp.common.persistence.model.basic.SmsLog;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description:
 * @author: mif
 * @date: 2017/8/22
 * @time: 11:43
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class SmsLogService {

    @Resource
    private SmsLogMapper smsLogMapper;

    @Async
    public void insertSmsLog(SmsLog smsLog) {
        smsLogMapper.insertSelective(smsLog);
    }
}

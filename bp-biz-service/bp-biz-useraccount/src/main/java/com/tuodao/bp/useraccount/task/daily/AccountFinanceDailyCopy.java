package com.tuodao.bp.useraccount.task.daily;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizTaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 金融账户复制入日报表
 * @author: mif
 * @date: 2017/9/4
 * @time: 11:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class AccountFinanceDailyCopy{

    private static Logger logger = LoggerFactory.getLogger(AccountFinanceDailyCopy.class);

    @Resource
    private BizTaskMapper bizTaskMapper;

    /**
     * 每天凌晨0点执行一次（0 0 0 * * ?）
     *
     * @param taskEvent
     * @return
     */
    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='ACCOUNT_FINANCE_DAILY_COPY_TIMER'")
    public void execute(Map<String,Object> map) {
        logger.info("beggin to accountFinanceDailyCopy,map={}", map);
        bizTaskMapper.accountFinaceDailyCopy();
        logger.info("end to accountFinanceDailyCopy,map={}", map);
    }
}

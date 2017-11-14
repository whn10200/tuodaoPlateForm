package com.tuodao.bp.common.timer;


import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.common.persistence.mapper.basic.DelaySmsMapper;
import com.tuodao.bp.common.persistence.model.basic.DelaySms;
import com.tuodao.bp.common.persistence.model.basic.DelaySmsExample;
import com.tuodao.bp.common.service.ISmsService;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.constant.PublicConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description: 定时发送任务()
 * @author: mif
 * @date: 2017/10/9
 * @time: 11:41
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class DelaySendTimer{

    private static Logger logger = LoggerFactory.getLogger(DelaySendTimer.class);

    @Resource
    private DelaySmsMapper delaySmsMapper;

    @Resource
    private ISmsService smsService;


    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='COMMON_SCHEDULED_SEND_TIMER'")
    public void execute(Map<String,Object> map) {
        logger.info("延迟发送短信、推送信息");

        DelaySmsExample example = new DelaySmsExample();
        example.createCriteria().andIsDelEqualTo(PublicConstant.DEL_NO);
        List<DelaySms> delaySmsList = delaySmsMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(delaySmsList)) {
            logger.info("需延迟发送短信数量 0");
            return;
        }
        logger.info("需延迟发送短信量为", delaySmsList.size());
        delaySmsList.forEach(delaySms -> {
            smsService.sendSms(new SmsInput(delaySms.getMobiles(), delaySms.getContent(), delaySms.getCustomsIp(),delaySms.getRemark()), delaySms.getRequestIp());

            delaySms.setIsDel(PublicConstant.DEL_YES);
            delaySmsMapper.updateByPrimaryKey(delaySms);
        });

    }
}

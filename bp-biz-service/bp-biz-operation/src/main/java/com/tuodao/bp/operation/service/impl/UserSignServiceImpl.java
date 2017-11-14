package com.tuodao.bp.operation.service.impl;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.output.CheckUserSignOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.OpScoreTaskMqInput;
import com.tuodao.bp.operation.constant.OperationRespExceptionConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserSingHistoryMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpUserSingHistory;
import com.tuodao.bp.operation.persistence.model.basic.OpUserSingHistoryExample;
import com.tuodao.bp.operation.service.IUserSignService;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Description: 服务 - 用户签到service实现
 * User: zkai
 * Date: 2017/9/25
 * Time: 15:25
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class UserSignServiceImpl implements IUserSignService {
    private static Logger logger = LoggerFactory.getLogger(UserSignServiceImpl.class);

    @Autowired
    private OpUserSingHistoryMapper opUserSingHistoryMapper;

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Resource(name = "opScoreTaskQueue")
    private Queue opScoreTaskQueue;

    /**
     *  用户签到
     * @param input
     * @return 签到积分
     */
    @Override
    public int userSign(BasePojo input) {
        // 判断用户今日是否签到
        OpUserSingHistoryExample opUserSingHistoryExample = new OpUserSingHistoryExample();
        opUserSingHistoryExample.createCriteria()
                .andUserIdEqualTo(input.getUserId())
                .andSignTimeEqualTo(new Date());
        List<OpUserSingHistory> opUserSingHistories =opUserSingHistoryMapper.selectByExample(opUserSingHistoryExample);
        if(opUserSingHistories.size() >= 1){
            logger.info("用户userId={} 已签到", input.getUserId());
            // 用户今日已签到
            throw new BizFeignException(OperationRespExceptionConstant.USER_HAS_SIGNED_IN);
        }
        OpScoreTaskMqInput opScoreTaskMqInput = new OpScoreTaskMqInput();
        opScoreTaskMqInput.setUserId(input.getUserId());
        opScoreTaskMqInput.setMobile(input.getMobile());
        Random random = new Random();
        int score = random.nextInt(6) +1;
        opScoreTaskMqInput.setScore(score);
        opScoreTaskMqInput.setSource("用户签到");
        opScoreTaskMqInput.setRemark("用户"+ TimeUtils.getSysdate()+"签到");
        opScoreTaskMqInput.setTaskId(PublicConstant.TASK_ID_CHECK_IN);
        logger.debug("发送签到信息到用户任务队列中 opScoreTaskMqInput={}", JSON.toJSONString(opScoreTaskMqInput));
        jmsTemplate.convertAndSend(opScoreTaskQueue, opScoreTaskMqInput);
        return score;
    }

    /**
     *  用户是否签到
     * @param input
     * @return true 签到  false 没有签到
     */
    @Override
    public CheckUserSignOutput checkSign(BasePojo input) {
        CheckUserSignOutput output = new CheckUserSignOutput();
        // 判断用户今日是否签到
        OpUserSingHistoryExample opUserSingHistoryExample = new OpUserSingHistoryExample();
        opUserSingHistoryExample.createCriteria()
                .andUserIdEqualTo(input.getUserId())
                .andSignTimeEqualTo(new Date());
        List<OpUserSingHistory> opUserSingHistories =opUserSingHistoryMapper.selectByExample(opUserSingHistoryExample);
        if(opUserSingHistories.size() >= 1){
            logger.info("用户userId={} 已签到", input.getUserId());
            output.setIfSign(true);
            output.setSignScore(opUserSingHistories.get(0).getThisSignScore());
        }else {
            output.setIfSign(false);
        }
        return output;
    }
}

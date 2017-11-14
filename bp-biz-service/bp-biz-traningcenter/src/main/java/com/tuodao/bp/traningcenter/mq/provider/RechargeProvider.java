package com.tuodao.bp.traningcenter.mq.provider;

import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.OpScoreTaskMqInput;
import com.tuodao.bp.model.mq.GatewayRechargeMqInfo;
import com.tuodao.bp.model.mq.QuickRechargeApplyMqInfo;
import com.tuodao.bp.model.mq.QuickRechargeConfirmMqInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.HashMap;
import java.util.Map;

/**
 * 充值队列 生产者
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
@Component
public class RechargeProvider {

    private static final Logger logger = LoggerFactory.getLogger(RechargeProvider.class);

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    ScoreTaskCache scoreTaskCache;

    @Resource(name = "opScoreTaskQueue")
    private Queue opScoreTaskQueue;

    @Resource(name = "deInRechargeGateway")
    private Queue rechargeGateQueue;

    @Resource(name = "deInRechargeQuickApply")
    private Queue rechargeFastSmsCodeQueue;

    @Resource(name = "deInRechargeQuickConfirm")
    private Queue rechargeFastQueue;

    /**
     *
     * @param userId
     * @param mobile
     */
    public void sendToUserScore(String userId, String mobile) {
        OpScoreTaskMqInput opScoreTaskMqInput = new OpScoreTaskMqInput();
        OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_FIRST_RECHARGE);
        opScoreTaskMqInput.setUserId(userId);
        opScoreTaskMqInput.setMobile(mobile);
        opScoreTaskMqInput.setScore(scoreTaskInfo.getScore());
        opScoreTaskMqInput.setSource(scoreTaskInfo.getTaskName());
        opScoreTaskMqInput.setTaskId(PublicConstant.TASK_ID_FIRST_RECHARGE);
        jmsMessagingTemplate.convertAndSend(opScoreTaskQueue, opScoreTaskMqInput);
        logger.info("send jms to user score, userId:{}, mobile:{}", userId, mobile);
    }

    /**
     * 网银充值
     * @param request
     */
    public void sendGateRechargeToDepository(GatewayRechargeMqInfo request) {
        Map<String, Object> header = new HashMap<>(1);
        header.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY, DepositoryMqConstant.DE_IN_RECHARGE_GATEWAY_VALUE);
        jmsMessagingTemplate.convertAndSend(rechargeGateQueue, request.toHashMap(), header);
        logger.info("send gate recharge jms to depository, content:{}", request.toString());
    }

    /**
     * 快捷充值发送短信
     * @param request
     */
    public void sendSmsCodeToDepository(QuickRechargeApplyMqInfo request) {
        Map<String, Object> header = new HashMap<>(1);
        header.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY, DepositoryMqConstant.DE_IN_RECHARGE_QUICK_APPLY_VALUE);
        jmsMessagingTemplate.convertAndSend(rechargeFastSmsCodeQueue, request.toHashMap());
        logger.info("send fast recharge sms code jms to depository, content:{}", request.toString());
    }

    /**
     * 快捷充值
     * @param request
     */
    public void sendFastRechargeToDepository(QuickRechargeConfirmMqInfo request) {
        Map<String, Object> header = new HashMap<>(1);
        header.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY, DepositoryMqConstant.DE_IN_RECHARGE_QUICK_CONFIRM_VALUE);
        jmsMessagingTemplate.convertAndSend(rechargeFastQueue, request.toHashMap());
        logger.info("send fast recharge jms to depository, content:{}", request.toString());
    }
}

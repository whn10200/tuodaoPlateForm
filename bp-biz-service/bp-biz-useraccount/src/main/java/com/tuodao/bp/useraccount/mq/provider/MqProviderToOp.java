package com.tuodao.bp.useraccount.mq.provider;

import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.mq.CouponGrantMqInfo;
import com.tuodao.bp.model.mq.OpFirstTimeInvestAwardMqInfo;
import com.tuodao.bp.model.mq.OpPaybackInviteAwardMqInfo;
import com.tuodao.bp.model.mq.OpScoreTaskMqInput;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfo;
import com.tuodao.bp.useraccount.service.UserBaseService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.math.BigDecimal;

/**
 * @description: 发送给运营中心的MQ 生产者
 * @author: mif
 * @date: 2017/9/22
 * @time: 11:08
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class MqProviderToOp extends UserBaseService {

    private static Logger logger = LoggerFactory.getLogger(MqProviderToOp.class);

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Resource
    private ScoreTaskCache scoreTaskCache;

    @Resource(name = "opPaybackInviteAwardQueue")
    private Queue opPaybackInviteAwardQueue;

    @Resource(name = "opCouponGrantQueue")
    private Queue opCouponGrantQueue;

    @Resource(name = "opScoreTaskQueue")
    private Queue opScoreTaskQueue;

    @Resource(name = "opFirstTimeInvestAwardQueue")
    private Queue opFirstTimeInvestAwardQueue;


    /**
     * 发送消息到运营中心-优惠券发放
     *
     * @param userId              用户编号
     * @param mobile              用户手机号
     * @param welfareActivityCode 福利活动CODE
     */
    public void send2Operation4CouponGrant(String userId, String mobile, String welfareActivityCode) {
        logger.info("发送消息到运营中心-优惠券发放,userId={},mobile={},welfareActivityCode={}", userId, mobile, welfareActivityCode);
        jmsMessagingTemplate.convertAndSend(opCouponGrantQueue, new CouponGrantMqInfo(userId, mobile, welfareActivityCode));
    }

    /**
     * 发送消息运营中心-用户回款理财师奖励
     *
     * @param awardMqInfo 队列对象
     */
    public void send2Operation4PaybackInviteAward(OpPaybackInviteAwardMqInfo awardMqInfo) {
        if (null == awardMqInfo) {
            return;
        }
        logger.info("用户回款，根据邀请人理财师等级发放邀请奖励queue ,opPaybackInviteAwardMqInfo={}", awardMqInfo);
        jmsMessagingTemplate.convertAndSend(opPaybackInviteAwardQueue, awardMqInfo);
    }


    /**
     * 发送消息运营中心-用户积分任务
     *
     * @param userInfo 用户信息
     * @param taskId   积分任务Id
     * @param remark 备注
     */
    public void send2Operation4ScoreTask(UserInfo userInfo, Long taskId, String remark) {
        OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(taskId);

        OpScoreTaskMqInput input = new OpScoreTaskMqInput();
        input.setUserId(userInfo.getUserId());
        input.setMobile(userInfo.getMobile());
        input.setTaskId(taskId);
        input.setScore(scoreTaskInfo.getScore());
        input.setSource(scoreTaskInfo.getTaskName());
        input.setRemark(remark);
        logger.info("向运营中心发送用户积分奖励队列 , opScoreTaskMqInput={}", input);
        jmsMessagingTemplate.convertAndSend(opScoreTaskQueue, input);
    }

    /**
     * 发送消息运营中心-首投邀请奖励队列
     *
     * @param userAccountInfo 用户信息
     * @param awardMoney      奖励金额
     */
    public void send2Operation4FirstTimeInvest(UserAccountInfo userAccountInfo, BigDecimal awardMoney) {
        if (StringUtils.isEmpty(userAccountInfo.getDirectInviterNo())) {
            logger.info("用户 ={}, 没有直接邀请人", userAccountInfo.getUserId());
            return;
        }
        //直接邀请人
        UserAccountInfo directInvite = super.getUserAccountInfo(userAccountInfo.getDirectInviterNo());

        if (null == directInvite) {
            return;
        }

        OpFirstTimeInvestAwardMqInfo mqInfo = new OpFirstTimeInvestAwardMqInfo();
        mqInfo.setUserId(userAccountInfo.getUserId());
        mqInfo.setMobile(userAccountInfo.getMobile());
        mqInfo.setRegisterTime(userAccountInfo.getRegisterDate());
        mqInfo.setAwardMoney(awardMoney);
        mqInfo.setDirectInviterNo(directInvite.getUserId());
        mqInfo.setDirectInviterMobile(directInvite.getMobile());

        logger.info("发送消息运营中心-首投邀请奖励队列,mqInfo={}", mqInfo);
        jmsMessagingTemplate.convertAndSend(opFirstTimeInvestAwardQueue, mqInfo);
    }

}
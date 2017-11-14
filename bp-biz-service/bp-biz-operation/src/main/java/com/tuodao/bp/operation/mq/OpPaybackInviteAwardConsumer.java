package com.tuodao.bp.operation.mq;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.OpPaybackInviteAwardMqInfo;
import com.tuodao.bp.operation.persistence.mapper.basic.OpInviteAwardRecordMapper;
import com.tuodao.bp.operation.persistence.mapper.basic.OpInviteRecordMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteAwardRecord;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRecord;
import com.tuodao.bp.result.exception.BizFeignException;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @description: 回款理财师邀请奖励消费者
 * @author: mif
 * @date: 2017/9/26
 * @time: 10:13
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class OpPaybackInviteAwardConsumer {
    private static Logger logger = LoggerFactory.getLogger(OpPaybackInviteAwardConsumer.class);

    @Resource
    private OpInviteRecordMapper inviteRecordMapper;

    @Resource
    private OpInviteAwardRecordMapper inviteAwardRecordMapper;


    @JmsListener(destination = MqContstant.OP_PAYBACK_INVITE_AWARD_QUEUE)
    @Transactional(rollbackFor = BizFeignException.class)
    public void consumer(ActiveMQObjectMessage message, OpPaybackInviteAwardMqInfo mqInfo) {
        logger.info("用户回款好友邀请奖励,opPaybackInviteAwardMqInfo={}", mqInfo);

        OpInviteRecord inviteRecord = new OpInviteRecord();
        inviteRecord.setUserId(mqInfo.getUserId());
        if (!StringUtils.isEmpty(mqInfo.getIndirectInviteeMobile())) {
            inviteRecord.setFriendPhoneNum(mqInfo.getIndirectInviteeMobile());//间接被邀请人
        } else {
            inviteRecord.setFriendPhoneNum(mqInfo.getDirectInviteeMobile());//直接被邀请人
        }
        inviteRecord.setRegisterTime(mqInfo.getRegisterTime());
        inviteRecord.setDirectCashback(mqInfo.getAwardMoney());
        inviteRecord.setGmtCreator(mqInfo.getUserId());
        inviteRecordMapper.insertSelective(inviteRecord);

        insertOpInviteAwardRecord(mqInfo);
    }

    /**
     * 插入邀请奖励记录表
     *
     * @param mqInfo
     */
    private void insertOpInviteAwardRecord(OpPaybackInviteAwardMqInfo mqInfo) {

        OpInviteAwardRecord inviteAwardRecord = new OpInviteAwardRecord();
        inviteAwardRecord.setUserId(mqInfo.getUserId());
        inviteAwardRecord.setInvitePhoneNum(mqInfo.getMobile());
        inviteAwardRecord.setAwardMoney(mqInfo.getAwardMoney());
        inviteAwardRecord.setDirectInvitee(mqInfo.getDirectInviteeMobile()); //直接被邀请人
        inviteAwardRecord.setIndirectInvitee(mqInfo.getIndirectInviteeMobile()); //间接被邀请人
        inviteAwardRecord.setRemark("好友回款奖励(根据理财师等级)");

        inviteAwardRecord.setAwardType(PublicConstant.INVITE_AWARD_TYPE_RETURN_CASH);
        logger.info(" 邀请人返现奖励 , inviteAwardRecord={}", inviteAwardRecord);
        inviteAwardRecordMapper.insertSelective(inviteAwardRecord);
    }
}

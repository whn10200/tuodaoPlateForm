package com.tuodao.bp.operation.mq;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.OpFirstTimeInvestAwardMqInfo;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpInviteAwardRecordMapper;
import com.tuodao.bp.operation.persistence.mapper.basic.OpInviteRecordMapper;
import com.tuodao.bp.operation.persistence.mapper.biz.BizOpWelfareActivityMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteAwardRecord;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRecord;
import com.tuodao.bp.operation.persistence.model.biz.BizOpWelfareActivity;
import com.tuodao.bp.utils.NumberUtils;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

/**
 * @description: 首投邀请奖励消费者 只有B级用户有奖励
 * @author: mif
 * @date: 2017/9/26
 * @time: 09:46
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class OpFirstTimeInvestMqConsumer {

    private static Logger logger = LoggerFactory.getLogger(OpFirstTimeInvestMqConsumer.class);

    @Resource
    private OpInviteRecordMapper inviteRecordMapper;

    @Resource
    private OpInviteAwardRecordMapper inviteAwardRecordMapper;

    @Resource
    private BizOpWelfareActivityMapper bizOpWelfareActivityMapper;


    @JmsListener(destination = MqContstant.OP_FIRST_TIME_INVEST_AWARD_QUEUE)
    public void consumer(ActiveMQObjectMessage message,OpFirstTimeInvestAwardMqInfo mqInfo) {
        logger.info("用户首投奖励 ,opFirstTimeInvestAwardMqInfo=", mqInfo);

        Double vouchers = selectCoupanVouchers(PublicConstant.WELFARE_ACTIVITY_CODE_FRIEND_FIRST_INVEST);

        OpInviteRecord inviteRecord = new OpInviteRecord();
        inviteRecord.setUserId(mqInfo.getUserId());
        inviteRecord.setFriendPhoneNum(mqInfo.getDirectInviterMobile());
        inviteRecord.setVouchers(vouchers);
        inviteRecord.setDirectCashback(mqInfo.getAwardMoney());
        inviteRecord.setRegisterTime(mqInfo.getRegisterTime());
        inviteRecord.setGmtCreator(mqInfo.getUserId());
        inviteRecordMapper.insertSelective(inviteRecord);

        OpInviteAwardRecord inviteAwardRecord = new OpInviteAwardRecord();
        inviteAwardRecord.setUserId(mqInfo.getDirectInviterNo());
        inviteAwardRecord.setInvitePhoneNum(mqInfo.getDirectInviterMobile());
        inviteAwardRecord.setDirectInvitee(mqInfo.getMobile());
        inviteAwardRecord.setAwardType(PublicConstant.INVITE_AWARD_TYPE_COUPON);
        inviteAwardRecord.setAwardMoney(BigDecimal.valueOf(vouchers));
        inviteAwardRecord.setRemark("好友首投奖励");
        //插入优惠券奖励
        inviteAwardRecordMapper.insertSelective(inviteAwardRecord);

        //插入首投返现奖励
        inviteAwardRecord.setAwardType(PublicConstant.INVITE_AWARD_TYPE_FIRST_TIME_INVEST);
        inviteAwardRecord.setAwardMoney(mqInfo.getAwardMoney());
        inviteAwardRecordMapper.insertSelective(inviteAwardRecord);
    }

    /**
     * 获取好友是首次投资优惠券奖励
     *
     * @param welfareActivityCode 福利活动CODE
     * @return 优惠券面值
     */
    private Double selectCoupanVouchers(String welfareActivityCode) {
        List<BizOpWelfareActivity> bizOpWelfareActivities = bizOpWelfareActivityMapper.selectOpWelfareActivityListByCode(welfareActivityCode);

        return bizOpWelfareActivities.stream()
                .filter(bizOpWelfareActivity -> Objects.equals(bizOpWelfareActivity.getDiscountType(), OperationBizConstant.DISCOUNT_TYPE_VOUCHER))
                .mapToDouble(c -> NumberUtils.str2Double(c.getDiscountAvailable()) * c.getQuantity()).sum();

    }
}

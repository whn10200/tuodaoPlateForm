package com.tuodao.bp.useraccount.task.daily;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.useraccount.mq.provider.MqProviderToOp;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserVipInfoMapper;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizTaskMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.UserVipInfo;
import com.tuodao.bp.useraccount.service.transfer.RibbonCommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户生日礼包定时任务（生日月发放）
 * @author: mif
 * @date: 2017/9/28
 * @time: 11:26
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class UserBirthdayGiftBagTask{

    private static Logger logger = LoggerFactory.getLogger(UserBirthdayGiftBagTask.class);

    @Resource
    private BizTaskMapper bizTaskMapper;

    @Resource
    private MqProviderToOp mqProviderToOp;

    @Resource
    private UserVipInfoMapper userVipInfoMapper;

    @Resource
    private UserAccountCache userAccountCache;

    @Resource
    private RibbonCommonService ribbonCommonService;

    /**
     * 每月1日凌晨1点执行一次(0 0 1 1 * ?)
     *
     * @param map
     * @return
     */
    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='GRANT_BIRTHDAY_GIFT_BAG_TIMER'")
    public void execute(Map<String,Object> map) {
        logger.info("发送用户生日礼包开始，map={}", map);
        List<String> userIds = bizTaskMapper.selectBirthdayUsers();
        if (CollectionUtils.isEmpty(userIds)) {
            return;
        }
        logger.info("本月生日用户数量 ={}", userIds.size());

        userIds.forEach(userId -> {
            UserAccountInfo accountInfo = userAccountCache.getUserAccoutInfo(userId);
            if (null == accountInfo) {
                logger.info("无法获取用户账户信息，userId={}", userId);
                return;
            }

            String birthdayWelfareActivityCode = getBirthdayWelfareActivityCode(userId);
            if (StringUtils.isEmpty(birthdayWelfareActivityCode)) {
                logger.info("无法获取福利活动CODE:用户VIP信息为空或等级为V0 ,userId={}", userId);
                return;
            }
            mqProviderToOp.send2Operation4CouponGrant(userId, accountInfo.getMobile(), birthdayWelfareActivityCode);

            //发送祝福短信
            ribbonCommonService.sendBirthDaySms(accountInfo.getMobile());
        });
        logger.info("发送用户生日礼包结束，map={}", map);

    }


    private String getBirthdayWelfareActivityCode(String userId) {
        UserVipInfo userVipInfo = userVipInfoMapper.selectByPrimaryKey(userId);
        if (null == userVipInfo) {
            return "";
        }
        if (null == userVipInfo.getVipLevel()) {
            return "";
        }

        return PublicConstant.VIP_BIRTHDAY_MAP.get(userVipInfo.getVipLevel());
    }
}

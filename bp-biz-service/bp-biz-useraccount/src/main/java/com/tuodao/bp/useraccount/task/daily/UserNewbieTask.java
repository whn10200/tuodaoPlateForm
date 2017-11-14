package com.tuodao.bp.useraccount.task.daily;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserInfoMapper;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizTaskMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfo;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @description: 用户是否新手定时任务 (注册日志与当前日期相差30天)
 * @author: mif
 * @date: 2017/9/25
 * @time: 11:53
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class UserNewbieTask {

    private static Logger logger = LoggerFactory.getLogger(UserNewbieTask.class);

    @Resource
    private BizTaskMapper bizTaskMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    /**
     * 每天凌晨1点执行一次(cron = "0 0 1 * * ?")
     *
     * @param map
     * @return
     */
    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='UPDATE_USER_NEWBIE_TIMER'")
    public void execute(Map<String,Object> map) {

        logger.info("定时更新用户新手信息开始 ：{}", map);
        Set<String> userIds = bizTaskMapper.selectNewbieUser();
        if (CollectionUtils.isEmpty(userIds)) {
            return;
        }
        logger.info("新手用户数量 ={}", userIds.size());

        userIds.forEach(userId -> {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setIsNewbie(PublicConstant.IF_NO);
            userInfo.setGmtModify(TimeUtils.getBeforeDayEnd(new Date()));
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        });
        logger.info("定时更新用户新手信息wc ：{}", map);

    }
}

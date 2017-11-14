package com.tuodao.bp.useraccount.task.daily;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizUserAccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @description: 用户账户缓存更新
 * @author: mif
 * @date: 2017/9/29
 * @time: 18:09
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class UserAccountCacheUpdateTask{

    private static Logger logger = LoggerFactory.getLogger(UserAccountCacheUpdateTask.class);

    @Resource
    private UserAccountCache userAccountCache;

    @Resource
    private BizUserAccountMapper bizUserAccountMapper;


    /**
     * 0 15 3 * * ? * 每天03：15
     *
     * @param map
     * @return
     */
    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='USER_ACCOUNT_CACHE_UPDATE_TIMER'")
    public void execute(Map<String,Object> map) {
        logger.info("定时更新用户缓存信息开始 ：{}", map);

        List<UserAccountInfo> list = bizUserAccountMapper.selectUserAccountInfoList();
        logger.info("用户账户信息存入Redis中size={}", list.size());
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(userAccountInfo -> userAccountCache.putCache(userAccountInfo));
            logger.info("用户账户信息存入Redis完成");
        }

    }
}

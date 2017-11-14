package com.tuodao.bp.useraccount.listener;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserInfoMapper;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizTaskMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfoExample;
import com.tuodao.bp.useraccount.service.IUserVipInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Description: 用户vip定时任务
 * User: zkai
 * Date: 2017/9/22
 * Time: 14:16
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Component
public class UserVipTimerBusiness {

    private static final Logger logger = LoggerFactory.getLogger(UserVipTimerBusiness.class);

    @Autowired
    private IUserVipInfoService userVipInfoService;

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Autowired
    private BizTaskMapper bizTaskMapper;

    @JmsListener(destination = MqContstant.TASK_TIMER_SEND_QUEUE,selector = "taskQueue='userVipTimer'")
    public void execute(Map<String,Object> map) {
        logger.info("用户vip定时任务 map={} 开始",map);
        UserInfoExample userInfoExample = new UserInfoExample();
        userInfoExample.createCriteria().andIsDelEqualTo(PublicConstant.DEL_NO);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(userInfoExample);
        List<Map<String,BigDecimal>> lastMonthAvgMaps = bizTaskMapper.selectFinaceDailyLastMonthAvgMap();
        logger.info("本次需要修改用户VIP等级的有:{} 个",userInfos.size());
        for (UserInfo userInfo: userInfos) {
           BigDecimal lastMonthAvg = null;
            try {
                logger.info("修改用户userId={}的信息",userInfo.getUserId());
                for (int i = 0; i <lastMonthAvgMaps.size() ; i++) {
                    Map<String,BigDecimal> lastMonthAvgMap = lastMonthAvgMaps.get(i);
                    if(lastMonthAvgMap.containsKey(userInfo.getUserId())){
                        lastMonthAvg = lastMonthAvgMap.get(userInfo.getUserId());
                        break;
                    }
                }
                logger.info("用户编号userId={} 的上月日均账户资产lastMonthAvg={}",userInfo.getUserId(),lastMonthAvg);
                userVipInfoService.updateVipLevel(userInfo.getUserId(), lastMonthAvg);
            }catch (Exception e){
                logger.info("修改用户 userId={} vip异常,e={}",userInfo.getUserId(),e);
            }
        }
        logger.info("用户vip定时任务 map={} 结束",map);
    }
}

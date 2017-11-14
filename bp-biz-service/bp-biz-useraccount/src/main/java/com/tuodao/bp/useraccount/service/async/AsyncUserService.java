package com.tuodao.bp.useraccount.service.async;

import com.tuodao.bp.model.business.useraccount.input.LoginInput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UserAccountBizConstant;
import com.tuodao.bp.useraccount.mq.provider.MqProviderToOp;
import com.tuodao.bp.useraccount.mq.provider.MqProviderToTraning;
import com.tuodao.bp.useraccount.persistence.mapper.basic.*;
import com.tuodao.bp.useraccount.persistence.model.basic.*;
import com.tuodao.bp.useraccount.persistence.model.biz.RegionInfo;
import com.tuodao.bp.useraccount.service.RegionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description: 异步用户操作相关
 * @author: mif
 * @date: 2017/8/28
 * @time: 11:56
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
@Async
public class AsyncUserService {

    private final static Logger logger = LoggerFactory.getLogger(AsyncUserService.class);

    @Resource
    private RegionService regionService;

    @Resource
    private UserInfoDetailMapper userInfoDetailMapper;

    @Resource
    private UserVipInfoMapper userVipInfoMapper;

    @Resource
    private UserBusinessMapper userBusinessMapper;

    @Resource
    private AccountFundsMapper accountFundsMapper;

    @Resource
    private AccountFinanceMapper accountFinanceMapper;

    @Resource
    private UserLoginLogMapper userLoginLogMapper;

    @Resource
    private UserInfoMapper userInfoMapper;

    @Resource
    private MqProviderToOp mqProviderToOp;

    @Resource
    private MqProviderToTraning mqProviderToTraning;

    /**
     * 异步初始化 注册信息
     *
     * @param userInfo 用户信息
     */
    @Transactional(rollbackFor = BizFeignException.class)
    public void initRegister(UserInfo userInfo) {
        logger.info("异步初始化用户注册相关信息 ,userInfo.userId ={}", userInfo.getUserId());
        // 用户信息详情(user_info_detail）
        insertUserInfoDetail(userInfo);
        //用户VIP信息（user_vip_info）
        insertUserVipInfo(userInfo);
        //用户业务表（user_business）
        insertUserBusiness(userInfo);
        //（账户）平台账户资金（account_funds）
        insertAccountFunds(userInfo);
        //insert（账户）金融资金账户（account_finance）
        insertAccountFinance(userInfo);

        //发送优惠券
        logger.info("用户注册发放新人福利奖励，userId={}", userInfo.getUserId());
        mqProviderToOp.send2Operation4CouponGrant(userInfo.getUserId(), userInfo.getMobile(), PublicConstant.WELFARE_ACTIVITY_CODE_REGISTER);

        //发送优惠券
        logger.info("交易中心初始化数据，userId={}", userInfo.getUserId());
        mqProviderToTraning.send2Deal4Register(userInfo.getUserId());
    }

    private void insertAccountFinance(UserInfo userInfo) {
        AccountFinance accountFinance = new AccountFinance();
        accountFinance.setUserId(userInfo.getUserId());
        accountFinance.setGmtCreator(userInfo.getGmtCreator());
        accountFinanceMapper.insertSelective(accountFinance);
    }

    private void insertAccountFunds(UserInfo userInfo) {
        AccountFunds accountFunds = new AccountFunds();
        accountFunds.setUserId(userInfo.getUserId());
        accountFunds.setGmtCreator(userInfo.getGmtCreator());
        accountFundsMapper.insertSelective(accountFunds);
    }

    private void insertUserBusiness(UserInfo userInfo) {
        UserBusiness userBusiness = new UserBusiness();
        userBusiness.setUserId(userInfo.getUserId());
        userBusiness.setBussinessCode(UserAccountBizConstant.BUSSINESS_CODE_FINANCE);
        userBusiness.setOpenType(userInfo.getRegisterSource());
        userBusiness.setGmtCreator(userInfo.getGmtCreator());
        userBusinessMapper.insertSelective(userBusiness);
    }

    private void insertUserVipInfo(UserInfo userInfo) {
        UserVipInfo vipInfo = new UserVipInfo();
        vipInfo.setUserId(userInfo.getUserId());
        vipInfo.setStartTime(new Date());
        vipInfo.setEndTime(null);
        vipInfo.setRemark("注册");
        vipInfo.setGmtCreator(userInfo.getGmtCreator());
        userVipInfoMapper.insertSelective(vipInfo);
    }

    private void insertUserInfoDetail(UserInfo userInfo) {
        UserInfoDetail detail = new UserInfoDetail();
        detail.setUserId(userInfo.getUserId());

        RegionInfo regionInfo = regionService.getRegion(userInfo.getRegisterIp());
        detail.setProvinceName(regionInfo.getProvince());
        detail.setCityName(regionInfo.getCity());
        detail.setGmtCreator(userInfo.getGmtCreator());
        userInfoDetailMapper.insertSelective(detail);
    }

    /**
     * 插入登陆日志
     *
     * @param input  登录信息
     * @param userId 用户编码
     */
    public void insertLoginLog(LoginInput input, String userId) {
        UserLoginLog loginLog = new UserLoginLog();
        loginLog.setUserId(userId);
        loginLog.setLoginTime(new Date());
        loginLog.setLoginIp(input.getLoginIp());
        loginLog.setLoginSource(input.getLoginSource());
        loginLog.setLoginVersion(input.getLoginVersion());
        userLoginLogMapper.insertSelective(loginLog);
    }

    /**
     * 首次邀请发送新手任务消息
     * 条件：1：新手；2：首次邀请
     *
     * @param userId        用户id
     * @param directInviter 直接邀请人
     */
    public void send2FirstInvite(String userId, UserInfo directInviter) {

        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andDirectInviterNoEqualTo(directInviter.getUserId())
                .andUserIdNotEqualTo(userId);
        if (userInfoMapper.countByExample(example) == 0) {//第一次邀请
            logger.info("用户完成首次邀请任务，userId={}", userId);
            //发送MQ
            mqProviderToOp.send2Operation4ScoreTask(directInviter, PublicConstant.TASK_ID_FIRST_INVITE_REGISTER, "完成新手邀请任务");
        }
    }

}

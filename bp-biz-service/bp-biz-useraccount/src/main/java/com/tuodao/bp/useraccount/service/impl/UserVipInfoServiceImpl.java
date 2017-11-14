package com.tuodao.bp.useraccount.service.impl;

import com.google.common.collect.Maps;
import com.google.common.collect.Range;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.common.input.PushInput;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.output.UserVipInfoOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.cache.BaseVipLevelCache;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.BaseLevelInfoMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserVipInfoHisMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserVipInfoMapper;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizTaskMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.*;
import com.tuodao.bp.useraccount.service.IUserVipInfoService;
import com.tuodao.bp.useraccount.service.UserBaseService;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Description: 用户等级相关service
 * User: zkai
 * Date: 2017/9/19
 * Time: 10:58
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class UserVipInfoServiceImpl extends UserBaseService implements IUserVipInfoService{

    private static final Logger logger = LoggerFactory.getLogger(UserVipInfoServiceImpl.class);

    @Autowired
    private BizTaskMapper bizTaskMapper;

    @Autowired
    private BaseLevelInfoMapper baseLevelInfoMapper;

    @Autowired
    private UserVipInfoMapper userVipInfoMapper;

    @Autowired
    private UserVipInfoHisMapper userVipInfoHisMapper;

    @Autowired
    private UserAccountCache userAccountCache;

    @Autowired
    private BaseVipLevelCache baseVipLevelCache;




    /**
     * 修改用户vip等级
     * @param userId 用户编号
     * @param lastMonthAvg 上月资产
     */
    @Override
    @Transactional
    public void updateVipLevel(String userId, BigDecimal lastMonthAvg) {
        if(lastMonthAvg == null){
            lastMonthAvg = BigDecimal.valueOf(0);
        }
        doUpdateVipLevel(userId,lastMonthAvg);
    }

    /**
     * 修改用户vip等级
     * @param userId 用户编号
     */
    @Override
    @Transactional
    public void updateVipLevel(String userId) {
        // 上月日均账户资产
        BigDecimal lastMonthAvg = bizTaskMapper.selectFinaceDailyLastMonthAvgByUid(userId);
        if(lastMonthAvg == null){
            lastMonthAvg = BigDecimal.valueOf(0);
        }
        doUpdateVipLevel(userId,lastMonthAvg);

    }

    /**
     * 根据用户Id查询VIP信息
     *
     * @param userId 用户编码
     * @return 用户VIP信息
     */
    @Override
    public UserVipInfoOutput selectByUserId(String userId) {

        // 获取用户vip信息
        UserVipInfo userVipInfo = userVipInfoMapper.selectByPrimaryKey(userId);
        UserVipInfoOutput output = new UserVipInfoOutput();
        BeanUtils.copyProperties(userVipInfo, output);

        // 本月日均账户资产
        BigDecimal thisMonth =  bizTaskMapper.selectFinaceDailyThisMonthAvgByUid(userId);
        if(thisMonth == null){
            thisMonth = new BigDecimal(0);
        }
        output.setThisMonthAvg(thisMonth);

        // 获取等级
        BaseLevelInfo baseLevelInfo = getVipLevel(thisMonth);
        if(baseLevelInfo == null){
            logger.error("本月日均账户资产为 thisMonthAvg={}的用户等级不存在",thisMonth);
            throw new BizFeignException(UaRespExceptionConstant.LEVEL_INFO_NOT_EXIT);
        }
        int thisVipLevel = baseLevelInfo.getLevel();
        if(thisVipLevel == getMaxVip()){
            // 如果已是最大等级
            output.setIfMaxLevel(true);
        }else {
            output.setIfMaxLevel(false);
            // 下一vip等级
            int nextVipLevel = baseLevelInfo.getLevel()+1;
            output.setNextLevel(nextVipLevel);

            // 下一等级日均账户资产区间最小值
            BigDecimal nextVipLevelMinMoney = getVipMinMoney(nextVipLevel);
            if(nextVipLevelMinMoney == null){
                nextVipLevelMinMoney = new BigDecimal(0);
            }
            BigDecimal distanceNextLevelAmount = nextVipLevelMinMoney.subtract(thisMonth);
            output.setDistanceNextLevelAmount(distanceNextLevelAmount);
        }
        return output;
    }

    /**
     * 修改用户vip等级
     *  2.符合升级或者降级的 修改
     *       user_vip_info(用户VIP信息)
     *       user_vip_info_his(用户VIP信息历史)
     *  3.同步到运营中心
     *  4.发送短信
     *  5.消息推送
     * @param userId 用户编号
     * @param lastMonthAvg 上月资产
     */
    private void doUpdateVipLevel(String userId,BigDecimal lastMonthAvg){
        logger.debug("用户编号userId={} 的上月日均账户资产={}",userId,lastMonthAvg);
        // 获取等级
        BaseLevelInfo baseLevelInfo = getVipLevel(lastMonthAvg);
        if(baseLevelInfo == null){
            logger.info("上月日均账户资产为 lastMonthAvg={}的用户等级不存在",lastMonthAvg);
            throw new BizFeignException(UaRespExceptionConstant.LEVEL_INFO_NOT_EXIT);
        }
        // 本次vip等级
        int thisVipLevel = baseLevelInfo.getLevel();

        // 原vip信息
        UserVipInfo userVipInfo = getUserVipInfo(userId);
        // 原vip等级
        int lastVipLevel = userVipInfo.getVipLevel();

        /**
         * 修改 user_vip_info(用户VIP信息)
         */
        // 获取历史最高等级
        int lastMaxVipLevel = userVipInfo.getMaxVipLevel();
        int maxVipLevel = lastMaxVipLevel;
        if(lastMaxVipLevel < thisVipLevel){
            maxVipLevel = thisVipLevel;
        }
        int continusSameLevelTimes = 0;
        if(thisVipLevel == lastVipLevel){
            // 如果等级没有修改，连续相同等级次数
            continusSameLevelTimes = userVipInfo.getContinusSameLevelTimes()+1;
        }
        updateUserVipInfo(userId,thisVipLevel,lastMonthAvg,maxVipLevel,continusSameLevelTimes);

        // 插入vip历史信息
        UserVipInfoHis userVipInfoHis = new UserVipInfoHis();
        BeanUtils.copyProperties(userVipInfo,userVipInfoHis);
        insertUserVipHistory(userVipInfoHis);

        // todo 同步运营中心

        // todo 发送短信
        // todo 发送消息推送
        sendMessage(lastMaxVipLevel,thisVipLevel,lastVipLevel,userId,userVipInfo.getContinusSameLevelTimes());
    }

    /**
     * 等级说明
     * 会员等级  上月日均账户资产
     *  V0        0≤X＜5万
     *  V1        5万≤X＜10万
     *  V2        10万≤X＜30万
     *  V3        30万≤X＜50万
     *  V4        50万≤X＜100万
     *  V5        100万≤X＜500万
     *  V6        X≥500万
     * 获取vip等级
     * @param lastMonthAvg 上月日均账户资产
     */
    private BaseLevelInfo getVipLevel(BigDecimal lastMonthAvg){
        if(lastMonthAvg == null ){
            lastMonthAvg= new BigDecimal(0);
        }
        Map<Range<BigDecimal>,BaseLevelInfo> map = Maps.newHashMap();

        // 获取等级信息
        List<BaseLevelInfo> vipInfos =  baseVipLevelCache.getBaseLevelInfos();
        if(vipInfos == null || vipInfos.size() == 0){
            logger.error("用户基础vip等级不存在");
            return null;
        }

        // 匹配等级信息
        for (BaseLevelInfo baseLevelInfo: vipInfos) {
            BigDecimal min = BigDecimal.valueOf(baseLevelInfo.getMin());
            BigDecimal max = BigDecimal.valueOf(baseLevelInfo.getMax());
            map.put(Range.closedOpen(min,max),baseLevelInfo);
        }

        for(Map.Entry<Range<BigDecimal>,BaseLevelInfo> entry : map.entrySet()){
            if(entry.getKey().contains(lastMonthAvg)){
                return entry.getValue();
            }
        }
        return null;
    }

    /**
     * 根据vip等级获取日均账户资产区间最小值
     * @param level
     * @return
     */
    private BigDecimal getVipMinMoney(Integer level){
        BigDecimal min = null;
        // 获取等级信息
        List<BaseLevelInfo> vipInfos =  baseVipLevelCache.getBaseLevelInfos();
        if(vipInfos == null || vipInfos.size() == 0){
            logger.error("用户基础vip等级不存在");
            return null;
        }
        // 匹配等级信息
        for (BaseLevelInfo baseLevelInfo: vipInfos) {
            if(baseLevelInfo.getLevel().equals(level)){
                min = BigDecimal.valueOf(baseLevelInfo.getMin());
                break;
            }
        }
        return min;
    }

    /**
     * 获取基础vip中的最大等级
     * @return
     */
    private int getMaxVip(){
        int maxVip = 0;
        // 获取等级信息
        List<BaseLevelInfo> vipInfos =  baseVipLevelCache.getBaseLevelInfos();
        if(vipInfos == null || vipInfos.size() == 0){
            logger.error("用户基础vip等级不存在");
            return 0;
        }
        // 匹配等级信息
        for (BaseLevelInfo baseLevelInfo: vipInfos) {
            int level = baseLevelInfo.getLevel();
            if(level>maxVip){
                maxVip = level;
            }
        }
        return maxVip;
    }


    /**
     * 修改用户vip等级
     * @param userId 用户编号
     * @param vipLevel vip等级
     * @param lastMonthAvg 上月日均账户资产
     * @param continusSameLevelTimes 连续相同等级次数
     */
    private void updateUserVipInfo(String userId,int vipLevel,BigDecimal lastMonthAvg,int maxVipLevel,int continusSameLevelTimes ){
        UserVipInfo userVipInfoUpdate = new UserVipInfo();
        userVipInfoUpdate.setUserId(userId);
        userVipInfoUpdate.setVipLevel(vipLevel);
        userVipInfoUpdate.setMaxVipLevel(maxVipLevel);
        userVipInfoUpdate.setLastMonthAvg(lastMonthAvg);
        userVipInfoUpdate.setStartTime(new Date());
        userVipInfoUpdate.setEndTime(TimeUtils.addMonth(new Date(),1));
        userVipInfoUpdate.setContinusSameLevelTimes(continusSameLevelTimes);
        userVipInfoMapper.updateByPrimaryKeySelective(userVipInfoUpdate);
    }

    /**
     * 获取用户vip信息
     * @param userId
     * @return
     */
    private UserVipInfo getUserVipInfo(String userId){
        UserVipInfoExample userVipInfoExample = new UserVipInfoExample();
        userVipInfoExample.createCriteria()
                .andUserIdEqualTo(userId)
                .andIsDelEqualTo(PublicConstant.DEL_NO);
        List<UserVipInfo> userVipInfos = userVipInfoMapper.selectByExample(userVipInfoExample);
        if(userVipInfos == null || userVipInfos.size() == 0){
            // 用户VIP信息不存在
            logger.error("userId={}的用户VIP信息不存在",userId);
            throw new BizFeignException(UaRespExceptionConstant.DATA_NOT_EXIT);
        }else if(userVipInfos.size()>1){
            logger.error("userId={}的用户VIP信息不唯一",userId);
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_RECORD_NOT_ONLY);
        }
        UserVipInfo userVipInfo = userVipInfos.get(0);
        return userVipInfo;
    }

    /**
     * 插入用户vip历史记录
     * @param userVipInfoHis
     */
    private void insertUserVipHistory(UserVipInfoHis userVipInfoHis){
        // 插入 用户VIP信息历史（user_vip_info_his）
        int insertResult = userVipInfoHisMapper.insertSelective(userVipInfoHis);
        if(insertResult != 1){
            logger.error("userId={}的用户VIP信息历史添加异常",userVipInfoHis.getUserId());
            throw new BizFeignException(UaRespExceptionConstant.ADD_ERROR);
        }
    }

    /**
     * 发送通知 短信通知,消息推送通知
     * @param lastMaxLevel 历史最大等级
     * @param newLevel 新等级
     * @param lastLevel 上次等级
     * @param userId 用户编号
     * @param continusSameLevelTimes 连续相同等级次数
     */
    private void sendMessage(int lastMaxLevel,int newLevel,int lastLevel,String userId,int continusSameLevelTimes){
        UserAccountInfo userAccountInfo = userAccountCache.getUserAccoutInfo(userId);
        String pushContent = null;
        String smsContent = null;
        if(newLevel == lastLevel){
            logger.info("userId={},用户没有升级",userId);
            // 判断是否连续3个月没有升级
            if(continusSameLevelTimes == 2){
                logger.info("userId={},用户连续3个月没有升级",userId);
                // 判断曾经是否发已发生过连续3月没有升级的情况，如果曾经已发生过，不在推送消息
                UserVipInfoHisExample userVipInfoHisExample = new UserVipInfoHisExample();
                userVipInfoHisExample.createCriteria()
                        .andIsDelEqualTo(PublicConstant.DEL_NO)
                        .andContinusSameLevelTimesEqualTo(2)
                        .andUserIdEqualTo(userId)
                        .andVipLevelEqualTo(newLevel);
                List<UserVipInfoHis> userVipInfoHiss = userVipInfoHisMapper.selectByExample(userVipInfoHisExample);
                if(userVipInfoHiss.size()>=1){
                    logger.info("userId={},用户曾经是否发已发生过连续3月没有升级的情况，不在推送消息",userId);
                    return;
                }
                pushContent = "";
                smsContent="";
            }else {
                return;
            }

        }
        else if(newLevel<lastLevel){
            // 新等级小于上次等级,降级
            logger.info("userId={},用户降级啦",userId);
            pushContent = "";
            smsContent="";
        }
        // 用户升级了
        else if(newLevel > lastMaxLevel){
            // 新等级大于历史最高等级 -- 升级-首次到新一级
            logger.info("userId={},用户升级了,新等级大于历史最高等级 -- 升级-首次到新一级",userId);
            pushContent = "";
            smsContent="";

        }else {
            logger.info("userId={},用户升级了,新等级大于历史最高等级 -- 升级-非首次到新一级",userId);
            pushContent = "";
            smsContent="";
        }
        SmsInput smsInput = new SmsInput();
        smsInput.setMobiles(userAccountInfo.getMobile());
        smsInput.setContent(smsContent);
        smsInput.setCustomerIp("127.0.0.1");
//            sendSms(smsInput);
        PushInput pushInput = new PushInput();
        pushInput.setPushObject(Integer.valueOf(PublicConstant.PUSH_OJECT_ALIA));
        pushInput.setPushContent(pushContent);
        pushInput.setPushAlias(userId);
//            pushMessage(pushInput);
    }


}

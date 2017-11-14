package com.tuodao.bp.useraccount.service;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Joiner;
import com.tuodao.bp.cache.basic.ConfigDictionaryCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.common.input.EmailInput;
import com.tuodao.bp.model.business.common.input.PushInput;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.constant.RegexpConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.constant.UserAccountBizConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.AccountFinanceMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserInfoMapper;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizUserAccountMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.AccountFinance;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description: 用户基础服务
 * @author: mif
 * @date: 2017/8/28
 * @time: 15:00
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserBaseService {
    private static Logger logger = LoggerFactory.getLogger(UserBaseService.class);

    @Resource
    protected UserInfoMapper userInfoMapper;

    @Resource
    protected UserAccountCache userAccountCache;

    @Resource
    private BizUserAccountMapper bizUserAccountMapper;

    @Resource
    private ConfigDictionaryCache configDictionaryCache;

    @Resource
    private AccountFinanceMapper accountFinanceMapper;

    @Autowired
    RestTemplate restTemplate;

    /**
     * LoadBalanced 注解表明restTemplate使用LoadBalancerClient执行请求
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(3000);
        factory.setReadTimeout(3000);
        RestTemplate template = new RestTemplate(factory);
        return template;
    }

    /**
     * 根据用户编号查询用户信息
     *
     * @param userId 用户编号
     * @return 用户信息
     */
    public UserInfo selectUserByUserId(String userId) {
        if (StringUtils.isEmpty(userId)) {
            return null;
        }
        return userInfoMapper.selectByPrimaryKey(userId);
    }

    /**
     * 根据手机号码查询用户信息
     *
     * @param mobile 用户号码
     * @return 用户信息
     */
    protected UserInfo selectUserByMobile(String mobile) {
        if (StringUtils.isEmpty(mobile)) {
            return null;
        }
        UserInfoExample example = new UserInfoExample();
        example.createCriteria()
                .andMobileEqualTo(mobile)
                .andIsDelEqualTo(PublicConstant.DEL_NO)
                .andUserStatusEqualTo(UserAccountBizConstant.USER_STATUS_NOMAL)
        ;

        List<UserInfo> list = userInfoMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        } else {
            return list.get(0);
        }
    }

    /**
     * 获取用户账户缓存信息：优先缓存
     *
     * @param userId 用户编码
     * @return
     */
    public UserAccountInfo getUserAccountInfo(String userId) {
        UserAccountInfo userAccountInfo = userAccountCache.getUserAccoutInfo(userId);
        if (null == userAccountInfo) {
            userAccountInfo = bizUserAccountMapper.selectUserAccountInfo(userId);
            if (null == userAccountInfo) {
                throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_USER_NOT_EXISTENCE);
            }
            userAccountCache.putCache(userAccountInfo);
        }
        return userAccountInfo;
    }

    /**
     * 验证用户是否存在
     *
     * @param userId
     */
    protected UserAccountInfo checkUserExist(String userId) {
        UserAccountInfo userAccountInfo = this.getUserAccountInfo(userId);
        if (null == userAccountInfo) {
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_USER_NOT_EXISTENCE);
        }
        return userAccountInfo;
    }

    /**
     * 根据邀请码或邀请人号码获取用户
     *
     * @param inviteCode 邀请码/手机号码
     * @return
     */
    protected UserInfo selectUserByInviteCode(String inviteCode) {

        if (StringUtils.isEmpty(inviteCode)) {
            return null;
        }
        //手机号码
        if (inviteCode.matches(RegexpConstant.PATTERN_MOBILE_REGEXP)) {
            return this.selectUserByMobile(inviteCode);
        }
        //邀请码
        if (inviteCode.matches(RegexpConstant.PATTERN_INVIT_CODE)) {
            UserInfoExample example = new UserInfoExample();
            example.createCriteria()
                    .andInviteCodeEqualTo(inviteCode)
                    .andIsDelEqualTo(PublicConstant.DEL_NO)
                    .andUserStatusEqualTo(UserAccountBizConstant.USER_STATUS_NOMAL)
            ;

            List<UserInfo> list = userInfoMapper.selectByExample(example);
            if (CollectionUtils.isEmpty(list)) {
                return null;
            } else {
                return list.get(0);
            }
        }
        return null;
    }

    /**
     * 获取数据字典名字
     *
     * @param dicType
     * @param dicCode
     * @return
     */
    protected String getDictionaryName(String dicType, String dicCode) {
        return configDictionaryCache.getDictionaryName(Joiner.on("_").join(dicType, dicCode));
    }

    /**
     * 根据用户编码获取账户资金
     *
     * @param userId
     * @return
     */
    protected AccountFinance getAccounTFinanceByUserId(String userId) {
        AccountFinance accountFinance = accountFinanceMapper.selectByPrimaryKey(userId);
        if (null == accountFinance) {
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_USER_NOT_EXISTENCE);
        }
        return accountFinance;
    }

    /**
     * 发送邮件
     *
     * @param emailInput
     */
    public void sendEmail(EmailInput emailInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(emailInput), headers);

        ResponseEntity<String> entity = restTemplate.postForEntity("http://BP-COMMON-MS/common/sendEmail",
                requestEntity, String.class);
        if (entity.getStatusCodeValue() != 200) {
            logger.error("邮件发送异常,发送内容inpu={}", JSON.toJSONString(emailInput));
        }
    }

    /**
     * 发送短信
     *
     * @param smsInput
     */
    public void sendSms(SmsInput smsInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(smsInput), headers);

        ResponseEntity<String> entity = restTemplate.postForEntity("http://BP-COMMON-MS/common/sendSms",
                requestEntity, String.class);
        if (entity.getStatusCodeValue() != 200) {
            logger.error("发送短信,发送内容inpu={}", JSON.toJSONString(smsInput));
        }
    }

    /**
     * 消息推送
     *
     * @param pushInput
     */
    public void pushMessage(PushInput pushInput) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> requestEntity = new HttpEntity<String>(JSON.toJSONString(pushInput), headers);

        ResponseEntity<String> entity = restTemplate.postForEntity("http://BP-COMMON-MS/common/pushMessage",
                requestEntity, String.class);
        if (entity.getStatusCodeValue() != 200) {
            logger.error("消息推送,发送内容inpu={}", JSON.toJSONString(pushInput));
        }
    }

    /**
     * 异步刷新用户账户缓存
     *
     * @param userId 用户ID
     */
    @Async
    protected void refreshUserAccountCache(String userId) {
        userAccountCache.putCache(bizUserAccountMapper.selectUserAccountInfo(userId));
    }
}

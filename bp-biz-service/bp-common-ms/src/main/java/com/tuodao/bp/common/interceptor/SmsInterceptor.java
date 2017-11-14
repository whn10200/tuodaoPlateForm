package com.tuodao.bp.common.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Splitter;
import com.tuodao.bp.common.cache.SmsCache;
import com.tuodao.bp.common.config.SmsConfig;
import com.tuodao.bp.common.constants.CommonConstant;
import com.tuodao.bp.common.persistence.mapper.basic.DelaySmsMapper;
import com.tuodao.bp.common.persistence.model.basic.DelaySms;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.constant.common.CommonExceptionConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.TimeUtils;
import com.tuodao.bp.utils.WebUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: 短信拦截器
 * @author: mif
 * @date: 2017/8/23
 * @time: 14:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Aspect
//@Component TODO
@Order(2)
public class SmsInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(SmsInterceptor.class);

    @Resource
    private SmsConfig smsConfig;

    @Resource
    private SmsCache smsCache;

    @Resource
    private DelaySmsMapper delaySmsMapper;



    @Before("within(com.tuodao.bp.common.controller.SmsController)")
    public void doBefore(JoinPoint joinPoint) throws Throwable {

        if (!smsConfig.getEnable()) {
            logger.info("发送短信失败：功能未启用");
            throw new BizFeignException(CommonExceptionConstant.SMS_SEND_UNABLE);
        }

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String requestIp = WebUtils.getClientIP(request);

        Object[] args = joinPoint.getArgs();
        SmsInput smsInput = (SmsInput) args[1];
        String mobiles = smsInput.getMobiles();
        String customerIp = smsInput.getCustomerIp();
        String remark = smsInput.getRemark();

        List<String> mobileList = Splitter.on(CommonConstant.SPLIT_CHAR).trimResults().omitEmptyStrings().splitToList(smsInput.getMobiles());

        if (mobileList.size() > 50) {
            logger.info("手机号码个数超过50个");
            throw new BizFeignException(CommonExceptionConstant.MOBILES_COUNT_BEYOND_50);
        }

        if (smsInput.isInner()) {
            if (TimeUtils.between22And9()) {
                logger.info("内部调用晚10点到次日9点则次日9点触发短息,暂保存至DB common_scheduled_sms ");
                delaySmsMapper.insertSelective(new DelaySms(requestIp, mobiles, smsInput.getContent(), customerIp,remark));
                throw new BizFeignException(CommonExceptionConstant.CONTENT_WILL_BE_DELAY_SEND);
            }
            return;
        }


        logger.info("smsDTO={}", smsInput);

        String cachePhone = smsCache.getMobiles(mobiles);
        logger.debug("phone1 ={} ", cachePhone);
        if (StringUtils.isEmpty(cachePhone)) {
            smsCache.putMobiles(mobiles);
        } else {
            logger.info("发短信太过频繁，每个手机60秒以内只能发一次...");
            throw new BizFeignException(CommonExceptionConstant.SMS_SEND_TOO_FREQUENTLY);
        }

        Integer count = smsCache.getMobilesNumber(mobiles);
        logger.debug("count ={} ", count);
        if (count <= smsConfig.getTopNumberLimit()) {
            smsCache.putMobilesNumber(mobiles, count + 1);
        } else {
            logger.info("每天同一手机最多发短信次数:{}", smsConfig.getTopNumberLimit());
            throw new BizFeignException(CommonExceptionConstant.MOBILE_COUNT_TOP_LIMIT);
        }


        count = smsCache.getIpCount(customerIp);
        logger.debug("ipCount ={} ", count);
        if (count < smsConfig.getTopIpLimit()) {
            smsCache.putIpCount(customerIp, count + 1);
        } else {
            logger.info("同一天，同一IP，最多发短信次数:{}", smsConfig.getTopIpLimit());
            throw new BizFeignException(CommonExceptionConstant.IP_COUNT_TOP_LIMIT);
        }


        Set<String> ipSet = smsCache.getMobilesIpList(mobiles);
        if (null == ipSet) {
            ipSet = new HashSet<String>();
            ipSet.add(customerIp);
            smsCache.putMobilesIpList(mobiles, ipSet);
        } else {
            logger.debug("ipList.size ={},content{}", ipSet.size(), JSON.toJSONString(ipSet));
            if (ipSet.size() < smsConfig.getTopMobileIpLimit()) {
                ipSet.add(customerIp);
                smsCache.putMobilesIpList(mobiles, ipSet);
            } else {
                logger.info("单个手机号在1天内最多可绑定{}个IP获取注册短信验证码.", smsConfig.getTopMobileIpLimit());
                throw new BizFeignException(CommonExceptionConstant.MOBILE_BAND_IP_COUNT_TOP_LIMIT);
            }
        }

        if (TimeUtils.between22And9()) {
            logger.info("非内部调用晚10点到次日9点则次日9点触发短息,暂保存至DB common_scheduled_sms ");
            delaySmsMapper.insertSelective(new DelaySms(requestIp, mobiles, smsInput.getContent(), customerIp,smsInput.getRemark()));
            throw new BizFeignException(CommonExceptionConstant.CONTENT_WILL_BE_DELAY_SEND);
        }
    }
}

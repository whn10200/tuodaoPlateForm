package com.tuodao.bp.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.rholder.retry.*;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tuodao.bp.common.constants.CommonConstant;
import com.tuodao.bp.common.persistence.mapper.basic.SmsAccoutMapper;
import com.tuodao.bp.common.persistence.model.basic.SmsAccout;
import com.tuodao.bp.common.persistence.model.basic.SmsAccoutExample;
import com.tuodao.bp.common.persistence.model.basic.SmsLog;
import com.tuodao.bp.common.service.ISmsService;
import com.tuodao.bp.common.service.async.SmsLogService;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.common.output.SmsOutput;
import com.tuodao.bp.model.constant.RegexpConstant;
import com.tuodao.bp.model.constant.common.CommonExceptionConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @description: 短信服务Service
 * @author: mif
 * @date: 2017/5/9
 * @time: 16:05
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class SmsServiceImpl implements ISmsService {

    private static final Logger logger = LoggerFactory.getLogger(SmsServiceImpl.class);

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private SmsAccoutMapper smsAccoutMapper;

    @Resource
    private SmsLogService smsLogService;

    private static final String HUYI_SUCCESS_CODE = "2";

    private static final String CHUANGLAN_SUCCESS_CODE = "0";

    private Map<String, String> errorMap = null;

    /**
     * guava retry
     * 结果返回false  重试:固定等待时长为 300 ms,最多尝试 3 次
     */
    static Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
            .retryIfExceptionOfType(RestClientException.class)
            .retryIfResult(aBoolean -> Objects.equals(aBoolean, false))
            .withWaitStrategy(WaitStrategies.fixedWait(300, TimeUnit.MILLISECONDS))
            .withStopStrategy(StopStrategies.stopAfterAttempt(3))
            .build();

    /**
     * 发送短信
     *
     * @param smsInput  短信请求信息
     * @param requestIp 请求IP
     * @return 发送结果
     */
    @Override
    public SmsOutput sendSms(SmsInput smsInput, final String requestIp) {

        final String content = smsInput.getContent();

        List<String> mobiles = Splitter.on(CommonConstant.SPLIT_CHAR).trimResults().omitEmptyStrings().splitToList(smsInput.getMobiles());

        int count = mobiles.size();

        List<String> errors = Lists.newArrayList();
        errorMap = Maps.newLinkedHashMap();
        //过滤格式不合法手机号码
        List<String> rightMobiles = mobiles.stream().filter(mobile -> {
            if (!checkPhone(mobile)) {
                errors.add(mobile);
                errorMap.put(mobile, "号码格式不匹配");
                return false;
            }
            return true;
        }).collect(Collectors.toList());

        List<SmsAccout> smsAccouts = selectSmsAccounts();

        if (CollectionUtils.isEmpty(smsAccouts)) {
            throw new BizFeignException(CommonExceptionConstant.HUYI_ACCOUT_UNCONFIG);
        }
        final SmsAccout smsAccout = smsAccouts.get(0);

        for (final String mobile : rightMobiles) {
            final SmsLog smsLog = new SmsLog(requestIp, new Date(), smsAccout.getSmsServicer(), mobile, content, smsInput.getCustomerIp(),smsInput.getRemark());

            Callable<Boolean> sendTask = () -> requestSend(mobile, content, smsLog, smsAccout);
            try {
                retryer.call(sendTask);
            } catch (ExecutionException | RetryException e) {
                logger.error("重试三次，调用第三方短信平台发送短信失败");
            }
            // 记录发送日志
            smsLogService.insertSmsLog(smsLog);
        }

        SmsOutput output = new SmsOutput();
        output.setSuccessCount(count - errorMap.size());
        output.setErrorCount(errorMap.size());
        output.setErrorMap(errorMap);
        logger.info("发送结果={}", JSON.toJSONString(output));
        return output;
    }

    /**
     * 获取短信配置信息
     *
     * @return 帐号列表
     */
    private List<SmsAccout> selectSmsAccounts() {
        SmsAccoutExample example = new SmsAccoutExample();
        example.createCriteria().andSmsStatusEqualTo(CommonConstant.SMS_ACCOUNT_STATUS_ENABLED);
        example.setOrderByClause("gmt_modify DESC");
        return smsAccoutMapper.selectByExample(example);
    }

    /**
     * 发送短信
     *
     * @param mobile    号码
     * @param content   短信内容
     * @param smsLog    日志信息
     * @param smsAccout 帐号信息
     * @return 是否成功
     */

    private Boolean requestSend(String mobile, String content, SmsLog smsLog, SmsAccout smsAccout) {
        boolean success = true;
        switch (smsAccout.getSmsServicer()) {
            case CommonConstant.HUYI_SMS_SERVICER:
                success = request2HuYi(mobile, content, smsLog, smsAccout);
                break;
            case CommonConstant.CHUANGLAN_SMS_SERVICE:
                success = request2ChuangLan(mobile, content, smsLog, smsAccout);
                break;
            case CommonConstant.EMP_SMS_SERVICE:
                success = request2EMP(mobile, content, smsLog, smsAccout);
                break;
        }
        return success;
    }

    /**
     * 发送梦网短信
     *
     * @param mobile    手机号码
     * @param content   短信内容
     * @param smsLog    短息日志
     * @param smsAccout 帐号信息
     * @return
     */
    private boolean request2EMP(String mobile, String content, SmsLog smsLog, SmsAccout smsAccout) {
        logger.info("发送梦网短信，mobile={},content={}", mobile, content);
        String code = null, msg = null, smsid = null;
        //短信记录
        MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
        variables.add("userId", smsAccout.getSmsAccount());     //用户账号
        variables.add("password", smsAccout.getSmsPassword());  //用户密码
        variables.add("pszMobis", mobile);
        variables.add("pszMsg", content);
        variables.add("pszSubPort", "*");//子端口号码，不带请填*号长度由账号类型定4-6位，通道号总长度不能超过20位。如：10657****主通道号，3321绑定的扩展端口，主+扩展+子端口总长度不能超过20位。

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(variables, headers);

        String result = restTemplate.postForEntity(smsAccout.getSmsUrl(), entity, String.class).getBody();

        smsLog.setResposeTime(new Date());

        if (StringUtils.isEmpty(result)) {
            logger.error("无返回结果");
            throw new BizFeignException(CommonExceptionConstant.COOPERATION_PLATFORM_EXCEPTION);
        }

        Document doc = null;
        try {
            doc = DocumentHelper.parseText(result);
        } catch (DocumentException e) {
            throw new BizFeignException(CommonExceptionConstant.ANALYTIC_RESULT_EXCEPTION);
        }
        Element root = doc.getRootElement();

        smsid = root.getTextTrim();
        logger.info("返回结果解析：result={},smsid={}", result, smsid);

        smsLog.setSmsServicer(smsAccout.getSmsServicer());
        smsLog.setResposeContent(result);
        smsLog.setResposeResult(!StringUtils.isEmpty(smsid) ? CommonConstant.RESULT_SUCCESS : CommonConstant.RESULT_FAILED);
//
        if (StringUtils.isEmpty(smsid)) {
            errorMap.put(mobile, "发送失败");
            return false;
        }

        return true;
    }

    /**
     * 发送创蓝短信
     *
     * @param mobile    号码
     * @param content   短信内容
     * @param smsLog    日志信息
     * @param smsAccout 帐号信息
     * @return 是否成功
     */
    private boolean request2ChuangLan(String mobile, String content, SmsLog smsLog, SmsAccout smsAccout) {
        logger.info("发送创蓝短信，mobile={},content={}", mobile, content);
        String code = null, msg = null, smsid = null;
        //短信记录
        MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
        variables.add("account", smsAccout.getSmsAccount());
        variables.add("pswd", smsAccout.getSmsPassword());
        variables.add("mobile", mobile);
        variables.add("msg", content);
        variables.add("needstatus", "true");//必填参数。是否需要状态报告，取值true或false，true，表明需要状态报告；false不需要状态报告

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(variables, headers);

        String result = restTemplate.postForEntity(smsAccout.getSmsUrl(), entity, String.class).getBody();

        smsLog.setResposeTime(new Date());

        if (StringUtils.isEmpty(result)) {
            logger.error("无返回结果");
            throw new BizFeignException(CommonExceptionConstant.COOPERATION_PLATFORM_EXCEPTION);
        }

        code = result.split(",")[1];

        smsLog.setSmsServicer(smsAccout.getSmsServicer());
        smsLog.setResposeContent(result);
        smsLog.setResposeResult(CHUANGLAN_SUCCESS_CODE.equals(code) ? CommonConstant.RESULT_SUCCESS : CommonConstant.RESULT_FAILED);
//
        if (!CHUANGLAN_SUCCESS_CODE.equals(code)) {
            errorMap.put(mobile, code);
            return false;
        }

        return true;
    }

    /**
     * 向互亿客户端发送请求
     *
     * @param mobile    号码
     * @param content   短信内容
     * @param smsLog    日志信息
     * @param smsAccout 帐号信息
     * @return 是否成功
     */
    private Boolean request2HuYi(String mobile, String content, SmsLog smsLog, SmsAccout smsAccout) {
        logger.info("发送互亿短信，mobile={},content={}", mobile, content);
        String code = null, msg = null, smsid = null;
        //短信记录
        MultiValueMap<String, String> variables = new LinkedMultiValueMap<String, String>();
        variables.add("account", smsAccout.getSmsAccount());
        variables.add("password", smsAccout.getSmsPassword());
        variables.add("mobile", mobile);
        variables.add("content", content);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        final HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(variables, headers);

        String result = restTemplate.postForEntity(smsAccout.getSmsUrl(), entity, String.class).getBody();

        smsLog.setResposeTime(new Date());

        if (StringUtils.isEmpty(result)) {
            logger.error("无返回结果");
            throw new BizFeignException(CommonExceptionConstant.COOPERATION_PLATFORM_EXCEPTION);
        }

        Document doc = null;
        try {
            doc = DocumentHelper.parseText(result);
        } catch (DocumentException e) {
            throw new BizFeignException(CommonExceptionConstant.ANALYTIC_RESULT_EXCEPTION);
        }
        Element root = doc.getRootElement();

        code = root.elementText("code");
        msg = root.elementText("msg");

        smsid = root.elementText("smsid");
        logger.info("返回结果解析：code={},msg={},smsid={}", code, msg, smsid);

        smsLog.setSmsServicer(smsAccout.getSmsServicer());
        smsLog.setResposeContent(result);
        smsLog.setResposeResult(HUYI_SUCCESS_CODE.equals(code) ? CommonConstant.RESULT_SUCCESS : CommonConstant.RESULT_FAILED);

        if (!HUYI_SUCCESS_CODE.equals(code)) {
            errorMap.put(mobile, msg);
            return false;
        }

        return true;
    }

    /**
     * 判断是否为合法手机号码
     *
     * @param mobile
     * @return
     */
    private boolean checkPhone(String mobile) {
        return mobile.matches(RegexpConstant.PATTERN_MOBILE_REGEXP);
    }

}

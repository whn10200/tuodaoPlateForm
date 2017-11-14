package com.tuodao.bp.useraccount.service.transfer;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.cache.basic.MsgTempCache;
import com.tuodao.bp.db.model.basic.ConfigSmsTemp;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.common.output.SmsOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.client.AsyncRestTemplate;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @description: 公共服务
 * @author: mif
 * @date: 2017/10/9
 * @time: 16:41
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class RibbonCommonService {
    private static Logger logger = LoggerFactory.getLogger(RibbonCommonService.class);

    private AsyncRestTemplate asyncRestTemplate;

    @Resource
    private MsgTempCache msgTempCache;

    /**
     * 发送生日祝福短信
     *
     * @param mobile 手机号码
     */
    public void sendBirthDaySms(String mobile) {
        ConfigSmsTemp configSmsTemp = msgTempCache.getSmsTemp(PublicConstant.SMS_TEMP_BIRTHDAY);
        if (configSmsTemp == null) {
            logger.info("短信模板未配置,type={}", PublicConstant.SMS_TEMP_BIRTHDAY);
            throw new BizFeignException(UaRespExceptionConstant.SMS_TEMPLATE_NOT_FIND);
        }
        sendSms(mobile, configSmsTemp.getContent(),configSmsTemp.getName());
    }

    /**
     * 发送短息
     *
     * @param mobile  手机号码
     * @param content 短信内容
     */
    private void sendSms(String mobile, String content,String remark) {

        SmsInput smsInput = new SmsInput(mobile, content, null,remark, true);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(JSON.toJSONString(smsInput), httpHeaders);
        ListenableFuture<ResponseEntity<SmsOutput>> listenableFuture = asyncRestTemplate.postForEntity("http：//BP-COMMON-MS/common/sendSms", entity, SmsOutput.class);
        try {
            SmsOutput output = listenableFuture.get().getBody();
            logger.info("短信发送结果，output = {}", output);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }
}

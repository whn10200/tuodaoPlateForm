package com.tuodao.bp.api.facade.service.common.impl;

import com.google.common.base.Joiner;
import com.tuodao.bp.api.facade.client.CommonClient;
import com.tuodao.bp.api.facade.constant.FacadeRespExceptionConstant;
import com.tuodao.bp.api.facade.service.BaseService;
import com.tuodao.bp.api.facade.service.common.IFacadeCommonService;
import com.tuodao.bp.cache.basic.MsgTempCache;
import com.tuodao.bp.cache.basic.SmsCodeCache;
import com.tuodao.bp.db.model.basic.ConfigSmsTemp;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.common.output.SmsOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.constant.facade.FacadeConstants;
import com.tuodao.bp.model.facade.common.input.FacadeSmsInput;
import com.tuodao.bp.model.facade.common.input.FacadeValidateSmsCodeInput;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * @description: Facade common service implement
 * @author: mif
 * @date: 2017/9/12
 * @time: 18:11
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
class FacadeCommonServiceImpl extends BaseService implements IFacadeCommonService {
    private static Logger logger = LoggerFactory.getLogger(FacadeCommonServiceImpl.class);

    @Resource
    private CommonClient commonClient;

    @Resource
    private SmsCodeCache smsCodeCache;

    @Resource
    private MsgTempCache msgTempCache;

    @Override
    public void sendSms(FacadeSmsInput input, String clientIP) {
        logger.info("facade send sms ,input={}", input);

        String smsCode = CommonUtils.generateSmsCode();

        check(input, smsCode);
        ConfigSmsTemp configSmsTemp = getSmsTmep(input, smsCode);
        String content = MessageFormat.format(configSmsTemp.getContent(), smsCode);
        String remark = configSmsTemp.getName();
        SmsOutput output = commonClient.sendSms(
                new SmsInput(input.getMobile(), content, clientIP,remark
                ));
        if (output.getSuccessCount() != 1) {
            throw new MicroServiceException();
        }
    }

    @Override
    public void validateSmsCode(FacadeValidateSmsCodeInput input) {
        logger.info("facade validate sms code  ,input={}", input);
        String key = Joiner.on("_").join(input.getMobile(), input.getSmsType());

        String smsCode = smsCodeCache.getSmsCode(key);
        if (StringUtils.isEmpty(smsCode)) {
            throw new MicroServiceException(FacadeRespExceptionConstant.SMS_CODE_IS_NON_EXISTENT);
        }
        if (!Objects.equals(input.getSmsCode(), smsCode)) {
            throw new MicroServiceException(FacadeRespExceptionConstant.SMS_CODE_NOT_MATCH);
        }
    }

    /**
     * 获取短信模版
     *
     * @param input   短信入参
     * @param smsCode 验证码
     * @return 短信内容
     */
    private ConfigSmsTemp getSmsTmep(FacadeSmsInput input, String smsCode) {
        ConfigSmsTemp configSmsTemp;
        switch (input.getSmsType()) {
            case FacadeConstants.SMS_TYPE_REGISTER:
                configSmsTemp = msgTempCache.getSmsTemp(PublicConstant.SMS_TEMP_REGISTER_CODE);
                break;
            case FacadeConstants.SMS_TYPE_FORGET_LOGIN_PW:
                configSmsTemp = msgTempCache.getSmsTemp(PublicConstant.SMS_TEMP_FORGET_LOGIN_PW);
                break;
            case FacadeConstants.SMS_TYPE_FORGET_PAY_PW:
                configSmsTemp = msgTempCache.getSmsTemp(PublicConstant.SMS_TEMP_FORGET_PAY_PW);
                break;
            case FacadeConstants.SMS_TYPE_FEEDBACK:
                configSmsTemp = msgTempCache.getSmsTemp(PublicConstant.SMS_TEMP_FEEDBACK);
                break;
            default:
                configSmsTemp = null;
        }
        if (configSmsTemp == null) {
            logger.warn("缓存中无短信模版，input = {}", input);
            throw new MicroServiceException(FacadeRespExceptionConstant.SMS_TEMPLATE_NOT_FIND);
        }
        return configSmsTemp;
    }

    /**
     * 校验缓存中的验证码
     *
     * @param input
     */
    private void check(FacadeSmsInput input, String smsCode) {

        String key = Joiner.on("_").join(input.getMobile(), input.getSmsType());
        logger.info("send sms key ={},smsCode={}", key, smsCode);
        smsCodeCache.putSmsCode(key, smsCode);
             /*
            String smsCode = smsCodeCache.getSmsCode(key);
    
            if (StringUtils.isEmpty(smsCode)) {
                smsCodeCache.putSmsCode(key, CommonUtils.generateSmsCode());
                return;
            }
            throw new MicroServiceException(FacadeRespExceptionConstant.SMS_SEND_TOO_OFTEN);*/
    }
}
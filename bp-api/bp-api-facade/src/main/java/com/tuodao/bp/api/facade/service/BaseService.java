package com.tuodao.bp.api.facade.service;

import com.google.common.base.Joiner;
import com.tuodao.bp.api.facade.constant.FacadeRespExceptionConstant;
import com.tuodao.bp.cache.basic.ConfigDictionaryCache;
import com.tuodao.bp.cache.basic.MsgTempCache;
import com.tuodao.bp.cache.basic.SmsCodeCache;
import com.tuodao.bp.model.constant.useraccount.UaParamExceptionConstant;
import com.tuodao.bp.result.exception.MicroServiceException;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Description: 基础service
 * User: zkai
 * Date: 2017/9/14
 * Time: 10:54
 * Copyright:拓道金服 Copyright (c) 2017
 */
public class BaseService {
    @Resource
    private SmsCodeCache smsCodeCache;

    @Resource
    protected MsgTempCache msgTempCache;

    /**
     * 校验短信码
     *
     * @param mobile  手机号码
     * @param smsCode 短信校验码
     * @param smsType 短信类型
     */
    public void checkSmsCode(String mobile, String smsCode, String smsType) {
        if(StringUtils.isBlank(smsCode)){
            //短信验证码不能为空
            throw new MicroServiceException(UaParamExceptionConstant.SMS_CODE_CANT_BE_BLANK);
        }

        String key = Joiner.on("_").join(mobile, smsType);
        String cacheSmsCode = smsCodeCache.getSmsCode(key);
        if (StringUtils.isBlank(cacheSmsCode)) {
            throw new MicroServiceException(FacadeRespExceptionConstant.SMS_CODE_IS_NON_EXISTENT);
        }
        if (!Objects.equals(smsCode, cacheSmsCode)) {
            throw new MicroServiceException(FacadeRespExceptionConstant.SMS_CODE_NOT_MATCH);
        }
        smsCodeCache.delSmsCode(key);
    }

}

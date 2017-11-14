package com.tuodao.bp.api.facade.service.useraccount.impl;

import com.google.common.base.Joiner;
import com.tuodao.bp.api.facade.client.CommonClient;
import com.tuodao.bp.api.facade.client.useraccount.UserClient;
import com.tuodao.bp.api.facade.constant.FacadeRespExceptionConstant;
import com.tuodao.bp.api.facade.service.BaseService;
import com.tuodao.bp.api.facade.service.useraccount.IFacadeUaService;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.db.model.basic.ConfigSmsTemp;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.input.*;
import com.tuodao.bp.model.business.useraccount.output.UserAccountInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.constant.facade.FacadeConstants;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetLoginPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetPayPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeRegisterInput;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.CommonUtils;
import com.tuodao.bp.utils.TimeUtils;
import com.tuodao.bp.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @description: 聚合层用户接口实现
 * @author: mif
 * @date: 2017/9/12
 * @time: 17:47
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class FacadeUaServiceImpl extends BaseService implements IFacadeUaService {
    private static Logger logger = LoggerFactory.getLogger(FacadeUaServiceImpl.class);

    @Value("${invite_url}")
    private String inviteUrl;

    @Resource
    private UserClient userClient;

    @Resource
    private UserAccountCache userAccountCache;

    @Resource
    private CommonClient commonClient;

    @Value("${avater.upload-path}")
    private String avatarPath;

    @Override
    public void register(FacadeRegisterInput input) {
        logger.info("register user input={}", input);

        checkSmsCode(input.getMobile(), input.getSmsCode(), FacadeConstants.SMS_TYPE_REGISTER);

        userClient.register(input);

        //发送短信
        sendRegisterSms(input.getMobile());

    }

    /**
     * 异步发送注册成功短信
     *
     * @param mobile 手机号码
     */
    @Async
    void sendRegisterSms(String mobile) {
        SmsInput smsInput = new SmsInput();
        smsInput.setMobiles(mobile);
        smsInput.setInner(true);
        smsInput.setCustomerIp(WebUtils.getHostIP());
        ConfigSmsTemp configSmsTemp = msgTempCache.getSmsTemp(PublicConstant.SMS_TEMP_REGISTER_SUCCESS);
        if (null == configSmsTemp) {
            logger.warn("缓存中无注册成功短信模版");
            return;
        }
        smsInput.setContent(configSmsTemp.getContent());
        logger.info("用户注册成攻短信内容，smsInput={}", smsInput);
        commonClient.sendSms(smsInput);
    }

    @Override
    public UserLoginOutput login(LoginInput input) {
        return userClient.login(input);
    }

    @Override
    public void forgetLoginPw(FacadeForgetLoginPwInput input) {
        logger.info(" facade forget login password , input={}", input);
        checkSmsCode(input.getMobile(), input.getSmsCode(), FacadeConstants.SMS_TYPE_FORGET_LOGIN_PW);
        userClient.forgetLoginPw(input);
    }

    @Override
    public void forgetPayPw(FacadeForgetPayPwInput input) {
        logger.info(" facade forget pay password , input={}", input);
        checkSmsCode(input.getMobile(), input.getSmsCode(), FacadeConstants.SMS_TYPE_FORGET_PAY_PW);
        userClient.forgetPayPw(input);
    }


    /**
     * 获取用户账户信息
     *
     * @param basePojo 基础对象
     * @return 用户账户信息
     */
    @Override
    public UserAccountInfoOutput getUserAccountInfo(BasePojo basePojo) {
        return userClient.getUserAccountInfo(basePojo);
    }

    @Override
    public void updateLoginPw(UpdateLoginPwInput input) {
        userClient.updateLoginPw(input);
    }

    @Override
    public void updatePayPw(UpdatePayPwInput input) {
        userClient.updatePayPw(input);
    }

    @Override
    public String getInviteUrl(BasePojo basePojo) {
        UserAccountInfo info = userClient.getUserAccountInfo(basePojo);
        return inviteUrl + "?inviteCode=" + CommonUtils.encodeBase64String(info.getInviteCode());
    }

    @Override
    public String encodeInviteCode(String inviteCode) {
        return CommonUtils.decodeBase64String(inviteCode);
    }

    @Override
    public void uploadAvatar(MultipartFile avatarFile, String userId) {
        String fileName;
        if (!avatarFile.isEmpty()) {
            try {

                byte[] bytes = avatarFile.getBytes();

                String name = avatarFile.getOriginalFilename();
                String postfix = name.substring(name.lastIndexOf("."), name.length());

                if (!CommonUtils.checkConfig("jpg;png;gif", postfix)) {
                    throw new MicroServiceException(FacadeRespExceptionConstant.AVATAR_FORMAT_ERROR);
                }

                fileName = Joiner.on("-").join(TimeUtils.getSysTimeLong(), userId) + postfix;
                String avatarUrl = avatarPath + fileName;
                logger.info("文件上传,文件名={}", avatarPath);

                File file = new File(avatarUrl);
                if (!file.getParentFile().exists()) {
                    file.getParentFile().mkdirs();
                }

                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));

                bufferedOutputStream.write(bytes);
                bufferedOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
                throw new MicroServiceException(FacadeRespExceptionConstant.AVATAR_UPLOAD_FAILED);
            }
        } else {
            throw new MicroServiceException(FacadeRespExceptionConstant.FILE_NOT_EXIST);
        }

        UserAvatarInput input = new UserAvatarInput(userId, fileName);
        userClient.uploadAvatar(input);

    }

    /**
     * 验证手机号码是否已注册
     *
     * @param mobileInput 手机号码
     * @return 是否已注册
     */
    @Override
    public Boolean validateMobileRegistered(MobileInput mobileInput) {
        return userClient.validateMobileRegistered(mobileInput);
    }


}

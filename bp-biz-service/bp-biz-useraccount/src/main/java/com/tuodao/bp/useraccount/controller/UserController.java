package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.business.useraccount.input.*;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.useraccount.interceptor.UnableValidate;
import com.tuodao.bp.useraccount.service.IUserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: 用户相关操作
 * @author: mif
 * @date: 2017/8/28
 * @time: 10:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "ua/user")
public class UserController {

    @Resource
    private IUserService userService;

    /**
     * 注册
     *
     * @param registerInput
     */
    @RequestMapping(value = "register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @UnableValidate
    public void register(RegisterInput registerInput) {
        userService.register(registerInput);
    }

    /**
     * 用户登录
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @UnableValidate
    public UserLoginOutput login(LoginInput input) {
        return userService.login(input);
    }

    /**
     * 忘记登录密码
     *
     * @param input
     */
    @RequestMapping(value = "forgetLoginPw", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @UnableValidate
    public void forgetLoginPw(ForgetLoginPwInput input) {
        userService.forgetLoginPw(input);
    }

    /**
     * 忘记支付密码
     *
     * @param input
     */
    @RequestMapping(value = "forgetPayPw", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @UnableValidate
    public void forgetPayPw(ForgetPayPwInput input) {
        userService.forgetPayPw(input);
    }

    /**
     * 修改登录密码
     *
     * @param input
     */
    @RequestMapping(value = "updateLoginPw", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updateLoginPw(UpdateLoginPwInput input) {
        userService.updateLoginPw(input);
    }

    /**
     * 修改支付密码
     *
     * @param input
     */
    @RequestMapping(value = "updatePayPw", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void updatePayPw(UpdatePayPwInput input) {
        userService.updatePayPw(input);
    }


    /**
     * 上传头像
     *
     * @param input
     */
    @RequestMapping(value = "uploadAvatar", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void uploadAvatar(UserAvatarInput input) {
        userService.uploadAvatar(input);
    }

    /**
     * 验证手机号码是否已注册
     *
     * @param mobileInput 手机号码
     * @return 是否已注册
     */
    @RequestMapping(value = "validateMobileRegistered", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @UnableValidate
    public Boolean validateMobileRegistered(MobileInput mobileInput) {
        return userService.validateMobileRegistered(mobileInput.getMobile());
    }
}

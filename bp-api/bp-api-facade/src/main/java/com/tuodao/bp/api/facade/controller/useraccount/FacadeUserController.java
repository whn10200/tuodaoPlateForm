package com.tuodao.bp.api.facade.controller.useraccount;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.useraccount.UserVipClient;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.service.useraccount.IFacadeUaService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.input.LoginInput;
import com.tuodao.bp.model.business.useraccount.input.MobileInput;
import com.tuodao.bp.model.business.useraccount.input.UpdateLoginPwInput;
import com.tuodao.bp.model.business.useraccount.input.UpdatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.UserAccountInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.model.business.useraccount.output.UserVipInfoOutput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.useraccout.input.DecodeInviteCodeInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetLoginPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetPayPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeRegisterInput;
import com.tuodao.bp.utils.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用户控制类
 * @author: mif
 * @date: 2017/9/12
 * @time: 10:33
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping(value = "router/user")
public class FacadeUserController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FacadeUserController.class);

    @Resource
    private IFacadeUaService facadeUaService;

    @Resource
    private UserVipClient userVipClient;

    /**
     * 注册
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @AccessToken(checkAccess = false, access = {AccessType.APP, AccessType.PC})
    public RespResult register(FacadeRegisterInput input, HttpServletRequest request) {
        logger.info("user register ,input={}", input);
        input.setRegisterIp(WebUtils.getClientIP(request));
        facadeUaService.register(input);
        return RespResult.create();
    }

    /**
     * 登录
     *
     * @param input
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @AccessToken(checkAccess = false, access = {AccessType.APP, AccessType.PC})
    public RespResult<UserLoginOutput> login(LoginInput input, HttpServletRequest request) {
        input.setLoginIp(WebUtils.getClientIP(request));
        input.setRequestType(request.getHeader("requestType"));
        return RespResult.<UserLoginOutput>create().setContent(facadeUaService.login(input));
    }

    /**
     * 忘记登录密码
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "forgetLoginPw", method = RequestMethod.POST)
    @AccessToken(checkAccess = false, access = {AccessType.APP, AccessType.PC})
    public RespResult forgetLoginPw(FacadeForgetLoginPwInput input) {
        facadeUaService.forgetLoginPw(input);
        return RespResult.create();
    }


    /**
     * 忘记支付密码
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "forgetPayPw", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult forgetPayPw(FacadeForgetPayPwInput input) {
        facadeUaService.forgetPayPw(input);
        return RespResult.create();
    }

    /**
     * 获取用户账户信息
     *
     * @param basePojo 基础数据
     * @return 用户信息
     */
    @RequestMapping(value = "getUserAccountInfo", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<UserAccountInfoOutput> getUserAccountInfo(BasePojo basePojo) {
        return RespResult.<UserAccountInfoOutput>create()
                .setContent(facadeUaService.getUserAccountInfo(basePojo));
    }

    /**
     * 修改登录密码
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "updateLoginPw", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult updateLoginPw(UpdateLoginPwInput input) {
        facadeUaService.updateLoginPw(input);
        return RespResult.<UserAccountInfo>create();
    }

    /**
     * 修改支付密码
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "updatePayPw", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult updatePayPw(UpdatePayPwInput input) {
        facadeUaService.updatePayPw(input);
        return RespResult.<UserAccountInfo>create();
    }

    /**
     * 获取用户邀请链接
     *
     * @param basePojo
     * @return
     */
    @RequestMapping(value = "getInviteUrl", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<String> getInviteUrl(BasePojo basePojo) {
        return RespResult.<String>create().setContent(facadeUaService.getInviteUrl(basePojo));
    }

    /**
     * 解密邀请码
     *
     * @return
     */
    @RequestMapping(value = "encodeInviteCode", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<String> encodeInviteCode(DecodeInviteCodeInput inviteCodeInput) {
        return RespResult.<String>create().setContent(facadeUaService.encodeInviteCode(inviteCodeInput.getInviteCode()));
    }

    /**
     * 上传头像
     *
     * @return
     */
    @RequestMapping(value = "uploadAvatar", method = RequestMethod.POST)
    @AccessToken(access = {AccessType.APP, AccessType.PC})
    public RespResult<String> uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("userId") String userId) {
        facadeUaService.uploadAvatar(file, userId);
        return RespResult.<String>create();
    }

    /**
     * 验证手机号码是否已注册
     *
     * @param mobileInput 手机号码
     * @return 是否已注册
     */
    @RequestMapping(value = "validateMobileRegistered", method = RequestMethod.POST)
    @AccessToken(checkAccess = false, access = {AccessType.APP, AccessType.PC})
    public RespResult<Boolean> validateMobileRegistered(MobileInput mobileInput) {
        return RespResult.<Boolean>create().setContent(facadeUaService.validateMobileRegistered(mobileInput));
    }

    /**
     * 获取用户vip信息
     */
    @RequestMapping(value = "getUserVipInfo", method = RequestMethod.POST)
    @AccessToken(checkAccess = true, access = {AccessType.APP, AccessType.PC})
    public RespResult<UserVipInfoOutput> getUserVipInfo(BasePojo input) {
        return RespResult.<UserVipInfoOutput>create().setContent(userVipClient.getUserVipInfo(input));
    }

}

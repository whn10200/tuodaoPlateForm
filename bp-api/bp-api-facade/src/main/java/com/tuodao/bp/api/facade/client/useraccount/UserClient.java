package com.tuodao.bp.api.facade.client.useraccount;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.*;
import com.tuodao.bp.model.business.useraccount.output.UserAccountInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetLoginPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetPayPwInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @description: 用户账户客户端
 * @author: mif
 * @date: 2017/9/12
 * @time: 10:35
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-USER-ACCOUNT")
public interface UserClient {
    /**
     * 注册
     *
     * @param input 输入参数
     * @return
     */
    @RequestMapping(value = "ua/user/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void register(RegisterInput input);


    /**
     * 用户登录
     *
     * @param input
     */
    @RequestMapping(value = "ua/user/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserLoginOutput login(LoginInput input);

    /**
     * 忘记登录密码
     *
     * @param input
     */
    @RequestMapping(value = "ua/user/forgetLoginPw", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void forgetLoginPw(FacadeForgetLoginPwInput input);

    /**
     * 忘记支付密码
     *
     * @param input
     */
    @RequestMapping(value = "ua/user/forgetPayPw", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void forgetPayPw(FacadeForgetPayPwInput input);

    /**
     * 修改登录密码
     *
     * @param input
     */
    @RequestMapping(value = "ua/user/updateLoginPw", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updateLoginPw(UpdateLoginPwInput input);

    /**
     * 修改支付密码
     *
     * @param input
     */
    @RequestMapping(value = "ua/user/updatePayPw", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void updatePayPw(UpdatePayPwInput input);

    /**
     * 获取用户信息
     *
     * @param pojo 基础数据
     * @return 用户帐号信息
     */
    @RequestMapping(value = "ua/getUserAccountInfo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    UserAccountInfoOutput getUserAccountInfo(BasePojo pojo);

    /**
     * 上传用户头像
     *
     * @param input 头像信息
     */
    @RequestMapping(value = "ua/user/uploadAvatar", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void uploadAvatar(UserAvatarInput input);

    /**
     * 验证手机号码是否已注册
     *
     * @param mobileInput 手机号码
     * @return 是否已注册
     */
    @RequestMapping(value = "ua/user/validateMobileRegistered", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean validateMobileRegistered(MobileInput mobileInput);
}

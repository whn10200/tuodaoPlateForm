package com.tuodao.bp.useraccount.service;

import com.tuodao.bp.model.admin.UserModifyMobileInput;
import com.tuodao.bp.model.business.useraccount.input.*;
import com.tuodao.bp.model.business.useraccount.output.UserAccountInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;

/**
 * @description: 用户相关service interface
 * @author: mif
 * @date: 2017/8/28
 * @time: 10:44
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IUserService {

    /**
     * 注册
     *
     * @param input
     */
    void register(RegisterInput input);

    /**
     * 获取用户帐号基本信息
     *
     * @param userId 用户编码
     * @return
     */
    UserAccountInfoOutput getUserAccountInfo(String userId);

    /**
     * 用户登录
     *
     * @param input
     * @return
     */
    UserLoginOutput login(LoginInput input);


    /**
     * 忘记登录密码
     *
     * @param input
     */
    void forgetLoginPw(ForgetLoginPwInput input);

    /**
     * 忘记支付密码
     *
     * @param input
     */
    void forgetPayPw(ForgetPayPwInput input);

    /**
     * 修改登录密码
     *
     * @param input
     */
    void updateLoginPw(UpdateLoginPwInput input);

    /**
     * 修改支付密码
     *
     * @param input
     */
    void updatePayPw(UpdatePayPwInput input);

    /**
     * 上传头像
     *
     * @param input 头像地址
     */
    void uploadAvatar(UserAvatarInput input);

    /**
     * 验证手机号码是否已注册
     *
     * @param mobile 手机号码
     * @return 是否注册过
     */
    Boolean validateMobileRegistered(String mobile);

    /**
     * 修改手机号码
     *
     * @param userModifyMobileInput 修改内容
     */
    void modifyMobile(UserModifyMobileInput userModifyMobileInput);
}

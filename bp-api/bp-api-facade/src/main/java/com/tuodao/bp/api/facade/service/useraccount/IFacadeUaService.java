package com.tuodao.bp.api.facade.service.useraccount;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.LoginInput;
import com.tuodao.bp.model.business.useraccount.input.MobileInput;
import com.tuodao.bp.model.business.useraccount.input.UpdateLoginPwInput;
import com.tuodao.bp.model.business.useraccount.input.UpdatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.UserAccountInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetLoginPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetPayPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeRegisterInput;
import org.springframework.web.multipart.MultipartFile;

/**
 * @description: 聚合层用户接口
 * @author: mif
 * @date: 2017/9/12
 * @time: 17:38
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface IFacadeUaService {

    /**
     * 用户注册
     *
     * @param input
     */
    void register(FacadeRegisterInput input);

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
    void forgetLoginPw(FacadeForgetLoginPwInput input);

    /**
     * 忘记支付密码
     *
     * @param input
     */
    void forgetPayPw(FacadeForgetPayPwInput input);

    /**
     * 获取用户账户信息
     *
     * @param basePojo 基础对象
     * @return 用户账户信息
     */
    UserAccountInfoOutput getUserAccountInfo(BasePojo basePojo);

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
     * 获取用户邀请链接
     *
     * @param basePojo
     * @return
     */
    String getInviteUrl(BasePojo basePojo);

    /**
     * 解密邀请码
     *
     * @param inviteCode 邀请码
     * @return
     */
    String encodeInviteCode(String inviteCode);

    /**
     * 上传头像
     *
     * @param avatarFile 头像文件
     * @param userId     用户编码
     */
    void uploadAvatar(MultipartFile avatarFile, String userId);

    /**
     * 验证手机号码是否已注册
     *
     * @param mobileInput 手机号码
     * @return 是否已注册
     */
    Boolean validateMobileRegistered(MobileInput mobileInput);
}

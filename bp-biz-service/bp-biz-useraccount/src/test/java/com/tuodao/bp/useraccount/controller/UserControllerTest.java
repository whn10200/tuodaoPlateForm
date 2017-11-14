package com.tuodao.bp.useraccount.controller;

import com.tuodao.bp.model.business.useraccount.input.*;
import com.tuodao.bp.useraccount.UserAccountTestApi;
import com.tuodao.bp.utils.MD5Utils;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/4
 * @time: 14:10
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserControllerTest extends UserAccountTestApi {

    @Test
    public void register() throws Exception {
        url = "ua/user/register";
        RegisterInput input = new RegisterInput();
        input.setMobile("18868807380");
        input.setLoginPassword(MD5Utils.md5("123456"));
        input.setPwSecurityLevel(1);
        input.setUserType(1);
        input.setInviterCode("18868807380");
        input.setRegisterSource(0);
        doPost(MediaType.APPLICATION_JSON, input);
    }

    @Test
    public void login() throws Exception {
        url = "ua/user/login";
        LoginInput input = new LoginInput();
        input.setMobile("18868807380");
        input.setLoginPassword(MD5Utils.md5("123456"));
        input.setLoginSource(1);
        doPost(MediaType.APPLICATION_JSON, input);
    }

    @Test
    public void forgetLoginPw() throws Exception {
        url = "ua/user/forgetLoginPw";
        ForgetLoginPwInput input = new ForgetLoginPwInput();
        input.setMobile("15957178759");
        input.setLoginPw("123456");
        input.setPwSecurityLevel(2);
        doPost(MediaType.APPLICATION_JSON, input);
    }

    @Test
    public void forgetPayPw() throws Exception {
        url = "ua/user/forgetPayPw";
        ForgetPayPwInput input = new ForgetPayPwInput();
        input.setMobile("15957178759");
        input.setPayPw("654321");
        doPost(MediaType.APPLICATION_JSON, input);
    }

    @Test
    public void updateLoginPw() throws Exception {
        url = "ua/user/updateLoginPw";
        UpdateLoginPwInput input = new UpdateLoginPwInput();
        input.setUserId("15988888888-e56fsscdd2o3u8ditin4");
        input.setOldLoginPw("test123456");
        input.setNewLoginPw("654321test");
        doPost(MediaType.APPLICATION_JSON, input);
    }

    @Test
    public void updatePayPw() throws Exception {
        url = "ua/user/updatePayPw";
        UpdatePayPwInput input = new UpdatePayPwInput();
        input.setUserId("15957178759-dn28892p1zm5j2x1z4lx");
        input.setOldPayPw("123456");
        input.setNewPayPw("654321");
        doPost(MediaType.APPLICATION_JSON, input);
    }

}
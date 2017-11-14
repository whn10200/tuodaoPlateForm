package com.tuodao.bp.api.facade.controller.useraccount;

import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.facade.useraccout.input.DecodeInviteCodeInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetLoginPwInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeForgetPayPwInput;
import com.tuodao.bp.model.business.useraccount.input.LoginInput;
import com.tuodao.bp.model.facade.useraccout.input.FacadeRegisterInput;
import com.tuodao.bp.model.business.useraccount.input.UpdateLoginPwInput;
import com.tuodao.bp.model.business.useraccount.input.UpdatePayPwInput;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/12
 * @time: 11:05
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class FacadeUserControllerTest extends FacadeTestApi {
    @Test
    public void register() throws Exception {
        url = "user/register";
        FacadeRegisterInput input = new FacadeRegisterInput();
        input.setMobile("15988888932");
        input.setLoginPassword("123456");
        input.setPwSecurityLevel(1);
        input.setSmsCode("477596");
        input.setRegisterSource(1);
        input.setInviterCode("15988888931");
        input.setInviteType(1);
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

    @Test
    public void login() throws Exception {
        url = "router/user/login";
        LoginInput input = new LoginInput();
        input.setMobile("18888888888");
        input.setLoginPassword("123");
        input.setLoginSource(1);
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

    @Test
    public void forgetLoginPw() throws Exception {
        url = "user/forgetLoginPw";
        FacadeForgetLoginPwInput input = new FacadeForgetLoginPwInput();
        input.setMobile("15988888888");
        input.setLoginPw("test123456");
        input.setPwSecurityLevel(1);
        input.setSmsCode("417214");
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

    @Test
    public void forgetPayPw() throws Exception {
        url = "user/forgetPayPw";
        FacadeForgetPayPwInput input = new FacadeForgetPayPwInput();
        input.setMobile("15988888888");
        input.setPayPw("654321");
        input.setSmsCode("521732");
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

    @Test
    public void getUserAccountInfo() throws Exception {
        url = "user/getUserAccountInfo";
        BasePojo input = new BasePojo();
        input.setUserId("15957178759-dn28892p1zm5j2x1z4lx");
        doPost(MediaType.APPLICATION_JSON_UTF8, input);
    }

    @Test
    public void updateLoginPw() throws Exception {
        url = "user/updateLoginPw";
        UpdateLoginPwInput input = new UpdateLoginPwInput();
        input.setUserId("15957178759-dn28892p1zm5j2x1z4lx");
        input.setOldLoginPw("123456");
        input.setNewLoginPw("123456");
        doPost(MediaType.APPLICATION_JSON, input);
    }

    @Test
    public void updatePayPw() throws Exception {
        url = "user/updatePayPw";
        UpdatePayPwInput input = new UpdatePayPwInput();
        input.setUserId("15988888888-e56fsscdd2o3u8ditin4");
        input.setOldPayPw("654321");
        input.setNewPayPw("123456");
        doPost(MediaType.APPLICATION_JSON, input);
    }

    @Test
    public void getInviteUrl() throws Exception {
        url = "user/getInviteUrl";
        BasePojo input = new BasePojo();
        input.setUserId("15988888888-e56fsscdd2o3u8ditin4");
        doPost(MediaType.APPLICATION_JSON, input);
    }

    @Test
    public void encodeInviteCode() throws Exception {
        url = "user/encodeInviteCode";
        DecodeInviteCodeInput input = new DecodeInviteCodeInput();
        input.setInviteCode("/v8AUABrADgARwAx");
        doPost(MediaType.APPLICATION_JSON, input);
    }


}
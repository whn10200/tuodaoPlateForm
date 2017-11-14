package com.tuodao.bp.api.facade.controller.traningcenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.useraccount.input.LoginInput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.model.facade.traningcenter.input.FastRechargeParam;
import com.tuodao.bp.model.facade.traningcenter.input.FastRechargeSmsParam;
import com.tuodao.bp.model.facade.traningcenter.input.OnlineBankAsynParam;
import com.tuodao.bp.model.facade.traningcenter.input.OnlineBankParam;
import com.tuodao.bp.model.facade.traningcenter.output.FastRechargeSmsVo;
import com.tuodao.bp.model.facade.traningcenter.output.FastRechargeVo;
import com.tuodao.bp.cache.basic.traningcenter.OnlineBankInfo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @author qingli.chen
 * @date 2017/10/17
 * @description 充值测试
 */
public class RechargeControllerTest extends FacadeTestApi {


    /**
     * 测试前置
     * @throws Exception
     */
    @Before
    public void login() throws Exception {
        url = "router/user/login";
        LoginInput input = new LoginInput();
        input.setMobile("18888888888");
        input.setLoginPassword("202cb962ac59075b964b07152d234b70");
        input.setLoginSource(1);
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, input);
        RespResult<UserLoginOutput> respResult = JSON.parseObject(resp, new TypeReference<RespResult<UserLoginOutput>>(){});
        UserLoginOutput output = respResult.getContent();
        super.ACCESS_ID = output.getAccessId();
        super.ACCESS_KEY = output.getAccessKey();
    }

    /**
     * 获取银行列表
     * @throws Exception
     */
    @Test
    public void bankList() throws Exception {
        url = "recharge/bankList";
        BasePojo basePojo = new BasePojo();
        basePojo.setUserId("1888888888888");

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, basePojo);

        RespResult<List<OnlineBankInfo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<List<OnlineBankInfo>>>(){});
        Assert.assertNotNull(respResult.getContent());
    }

    /**
     * 获取充值信息
     * @throws Exception
     */
    @Test
    public void getRechargeInfoTest() throws Exception {
        url = "router/recharge/rechargeInfo";
        BasePojo basePojo = new BasePojo();
//        basePojo.setUserId("1888888888888");

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, basePojo);
        RespResult<FastRechargeVo> respResult = JSON.parseObject(resp, new TypeReference<RespResult<FastRechargeVo>>(){});
        Assert.assertNotNull(respResult.getContent().getAccount());
    }

    /**
     * 网银在线充值
     * @throws Exception
     */
    @Test
    public void onlineBankRechargeTest() throws Exception {
        url = "recharge/online";
        OnlineBankParam bankParam = new OnlineBankParam();
        bankParam.setUserId("1888888888888");
        bankParam.setMoney(new BigDecimal(100));
        bankParam.setBankId("ICBC");
        bankParam.setRequestType("1");

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, bankParam);
        RespResult respResult = JSON.parseObject(resp, RespResult.class);
        Assert.assertTrue(respResult.isSuccess());
    }

    /**
     * 快捷充值发送短信
     * @throws Exception
     */
    @Test
    public void sendFastCode() throws Exception {
        url = "router/recharge/sendSmsCode";
        FastRechargeSmsParam param = new FastRechargeSmsParam();
        param.setUserId("1888888888888");
        param.setMoney(new BigDecimal(100));

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<FastRechargeSmsVo> respResult = JSON.parseObject(resp, new TypeReference<RespResult<FastRechargeSmsVo>>(){});
        Assert.assertNotNull(respResult.getContent().getOrderNo());
    }

    /**
     * 快捷充值
     * @throws Exception
     */
    @Test
    public void fastRecharge() throws Exception {
        url = "router/recharge/fast/pay";
        FastRechargeParam param = new FastRechargeParam();
        param.setUserId("1888888888888");
        param.setOrderNo("17101915110113160");
        param.setSmsCode("123456");

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult respResult = JSON.parseObject(resp, RespResult.class);
        Assert.assertTrue(respResult.isSuccess());
    }

    /**
     * 网银充值回调
     * @throws Exception
     */
    @Test
    public void rechargeAsynGateway() throws Exception {
        url = "/notice/online_bank";
        OnlineBankAsynParam param = new OnlineBankAsynParam();
        param.setOrderNo("");
        param.setStatus(1);
        param.setPayTime(new Date());

        doPost2(MediaType.APPLICATION_JSON_UTF8, param);
    }

//    /**
//     * 快捷充值回调
//     * @throws Exception
//     */
//    @Test
//    public void rechargeAsynFast() throws Exception {
//        url = "notice/fast";
//        OnlineBankAsynParam param = new OnlineBankAsynParam();
//        param.setOrderNo("");
//        param.setStatus(1);
//        param.setPayTime(new Date());
//
//        doPost2(MediaType.APPLICATION_JSON_UTF8, param);
//    }

}

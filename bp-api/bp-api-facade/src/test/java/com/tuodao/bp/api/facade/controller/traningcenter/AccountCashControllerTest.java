package com.tuodao.bp.api.facade.controller.traningcenter;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.facade.traningcenter.input.AccountCashParam;
import com.tuodao.bp.model.facade.traningcenter.input.CashCalcParam;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashExtVo;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.utils.MD5Utils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.Map;


/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/3
 * @time: 12:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountCashControllerTest extends FacadeTestApi {


    /**
     * 提现申请
     * @throws Exception
     */
    @Test
    public void applyCash()throws Exception{
        AccountCashParam param = new AccountCashParam();
        super.url = "router/tc/account/cash_apply";
        param.setCashMoney(100D);
        param.setPayPassword(MD5Utils.md5("123456"));
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<String> stringRespResult = JSONObject.parseObject(s, new TypeReference<RespResult<String>>() {});
        Assert.assertEquals(stringRespResult.getCode(),100000L);
    }

    @Test
    public void getCashFee()throws Exception{
        CashCalcParam param = new CashCalcParam();

        super.url = "router/tc/account/cash_fee";
        param.setCashMoney(49000D);

        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        System.out.println(s);
        RespResult<Map<String,Double>> result = JSONObject.parseObject(s, new TypeReference<RespResult<Map<String,Double>>>() {});
        Assert.assertNotNull(result.getContent());
    }

    @Test
    public void toCash()throws Exception{
        super.url = "router/tc/account/to_cash";
        BasePojo pojo = new BasePojo();
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8,pojo);
        RespResult<AccountCashExtVo> result = JSONObject.parseObject(s, new TypeReference<RespResult<AccountCashExtVo>>() {
        });
        Assert.assertNotNull(result.getContent());
    }

    @Test
    public void cashList() throws Exception{
        super.url = "router/tc/account/cash_list";
        CashListParam param = new CashListParam();
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8,param);
        RespResult<PageInfo<AccountCashVo>> result = JSONObject.parseObject(s, new TypeReference<RespResult<PageInfo<AccountCashVo>>>() {
        });
        Assert.assertNotNull(result.getContent());
    }
}

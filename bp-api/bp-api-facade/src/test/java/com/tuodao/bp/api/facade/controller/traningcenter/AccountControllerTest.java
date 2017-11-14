package com.tuodao.bp.api.facade.controller.traningcenter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.facade.traningcenter.output.AccountAppVo;
import com.tuodao.bp.model.facade.traningcenter.output.AccountVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 10:26
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountControllerTest extends FacadeTestApi {

    @Before
    public void setAccessId(){
        super.ACCESS_ID = "8d68a51a60be8891920152ab811c4faf";
        super.ACCESS_KEY="/v8amga0aguazgbiadgamwbladmaoqa4adkaywa1admamwa4agqanaa4adkazga1agyamwa3agmamwbjageaoaa1";
    }

    @Test
    public void getUserAccount()throws Exception{
        BasePojo pojo = new BasePojo();
        super.url = "router/tc/user/account/get_account";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, pojo);

        RespResult<AccountVo> respResult = JSONObject.parseObject(s, new TypeReference<RespResult<AccountVo>>() {
        });
        Assert.assertNotNull(respResult.getContent());
    }

    @Test
    public void getUserAccountDetail()throws Exception{
        BasePojo pojo = new BasePojo();
        super.url="router/tc/user/account/get_account_detail";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, pojo);
        RespResult<AccountAppVo> respResult = JSONObject.parseObject(s, new TypeReference<RespResult<AccountAppVo>>() {
        });
        Assert.assertNotNull(respResult.getContent());
    }

}

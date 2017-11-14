package com.tuodao.bp.api.facade.controller.traningcenter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.facade.traningcenter.input.AccountLogParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.MediaType;



/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 09:13
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AccountLogControllerTest extends FacadeTestApi {




    @Test
    public void getAccountLogList()throws Exception{
        super.url = "router/tc/account/account_log_list";
        AccountLogParam param = new AccountLogParam();
        param.setType(-1);
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<PageInfo<AccountLogVo>> respResult = JSONObject.parseObject(s, new TypeReference<RespResult<PageInfo<AccountLogVo>>>() {
        });
        Assert.assertNotNull(respResult.getContent());
    }
}

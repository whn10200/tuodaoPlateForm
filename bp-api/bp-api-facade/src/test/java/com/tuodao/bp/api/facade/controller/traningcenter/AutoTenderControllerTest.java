package com.tuodao.bp.api.facade.controller.traningcenter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.api.facade.service.transaction.AutoTenderService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderExtParam;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderAppVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 10:31
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class AutoTenderControllerTest extends FacadeTestApi {


    @Autowired
    private AutoTenderService autoTenderService;

    @Test
    public void saveAutoTender() throws Exception{
        AutoTenderParam param = new AutoTenderParam();
        param.setUseCoupon(0);
        param.setMaxPeriod(6);
        param.setMinPeriod(3);
        param.setMaxAccount(50000D);
        param.setMinAccount(200D);
        super.url="router/tc/tender/add_auto_tender";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<String> result = JSONObject.parseObject(s, new TypeReference<RespResult<String>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getUserAutoTender()throws Exception{
        super.url = "router/tc/tender/get_auto_tender";
        BasePojo pojo = new BasePojo();
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, pojo);
        RespResult<AutoTenderVo> result = JSONObject.parseObject(s, new TypeReference<RespResult<AutoTenderVo>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void closeAutoTender()throws Exception{
        super.url="router/tc/tender/close_auto_tender";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, new BasePojo());
        RespResult<String> result = JSONObject.parseObject(s, new TypeReference<RespResult<String>>() {});
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getUserBank()throws Exception{
        super.url ="router/tc/tender/check_bank";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, new BasePojo());
        RespResult<Boolean> result = JSONObject.parseObject(s, new TypeReference<RespResult<Boolean>>() {});
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getAutoTenderList()throws Exception{
        super.url = "router/tc/tender/auto_tender_list";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, new BasePojo());
        RespResult<PageInfo<AutoTenderListVo>> result = JSONObject.parseObject(s, new TypeReference<RespResult<PageInfo<AutoTenderListVo>>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getAutoTenderDetail()throws Exception{
        super.url ="router/tc/tender/auto_tender_detail";
        AutoTenderExtParam param = new AutoTenderExtParam();
        param.setAutoTenderId(70);
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<AutoTenderListVo> result = JSONObject.parseObject(s, new TypeReference<RespResult<AutoTenderListVo>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getSubAutoTenderList()throws Exception{
        AutoTenderExtParam param = new AutoTenderExtParam();
        super.url = "router/tc/tender/sub_auto_tender_list";
        param.setAutoTenderId(70);
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<PageInfo<BorrowTenderVo>> result = JSONObject.parseObject(s, new TypeReference<RespResult<PageInfo<BorrowTenderVo>>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }


    /**
     * 自动投标定时任务
     */
    @Test
    public void autoTenderService(){
        autoTenderService.autoTender();
    }

    @Test
    public void getAppTender()throws Exception{
        super.url = "router/tc/tender/to_app_auto_tender";

        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, new BasePojo());
        RespResult<AutoTenderAppVo> result = JSONObject.parseObject(s, new TypeReference<RespResult<AutoTenderAppVo>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

}

package com.tuodao.bp.api.facade.controller.traningcenter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionExtParam;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.BackMoneyPlanVo;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionDayVo;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionVo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 11:28
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowCollectionControllerTest extends FacadeTestApi {

    @Before
    public void setAccessId(){
        super.ACCESS_ID = "8c635d29f8045c3839e72d7d58674cc6";
        super.ACCESS_KEY="/v8anwbjadiazgbhadmamwawadcamqa4agiazaa4adqaoqazaguamgbkadgamwa0adqazabladuayqbiadkanaaz";
    }

    @Test
    public void getBorrowCollectionList()throws Exception{
        UserBackMoneyInput input = new UserBackMoneyInput();
        input.setTenderId(12);
        super.url = "router/tc/tender/borrow_collection_list";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, input);
        RespResult<PageInfo<BackMoneyPlanVo>> result = JSONObject.parseObject(s, new TypeReference<RespResult<PageInfo<BackMoneyPlanVo>>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getTotalCollection() throws Exception{
        BasePojo pojo = new BasePojo();
        super.url = "router/tc/tender/total_collection";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, pojo);
        RespResult<String> result = JSONObject.parseObject(s, new TypeReference<RespResult<String>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    /**
     * 查询某月或某天的 已回款,未回款资金
     * @throws Exception
     */
    @Test
    public void monthCollection()throws Exception{
        CollectionParam param = new CollectionParam();
        param.setDay("2017-12");
        param.setType(1);
        super.url = "/tc/tender/month_collection";
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<Map<String,String>> result = JSONObject.parseObject(s, new TypeReference<RespResult<Map<String,String>>>() {});

        Assert.assertEquals(result.getCode(),100000L);
    }


    /**
     * 回款日历,按天查询回款列表
     * @throws Exception
     */
    @Test
    public void getCalendarList()throws Exception{
        CollectionParam param = new CollectionParam();
        param.setDay("2017-12");
        param.setType(1);
        super.url = "router/tc/tender/collection_calendar_list";

        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<PageInfo<CollectionVo>> result = JSONObject.parseObject(s, new TypeReference<RespResult<PageInfo<CollectionVo>>>() {});

        Assert.assertEquals(result.getCode(),100000L);

    }

    /**
     * 获取某个月回款的日期与回款状态
     * @throws Exception
     */
    @Test
    public void getCollectionDays() throws Exception{
        super.url="router/tc/tender/collection_days";
        CollectionExtParam param = new CollectionExtParam();
        param.setMonth("2017-11");
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<List<CollectionDayVo>> result = JSONObject.parseObject(s, new TypeReference<RespResult<List<CollectionDayVo>>>() {});

        Assert.assertEquals(result.getCode(),100000L);

    }



}

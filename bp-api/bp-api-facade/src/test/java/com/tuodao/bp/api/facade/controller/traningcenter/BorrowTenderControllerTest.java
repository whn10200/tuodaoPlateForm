package com.tuodao.bp.api.facade.controller.traningcenter;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.service.transaction.BorrowTenderService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.ReturnCacheInfo;
import com.tuodao.bp.model.TenderResult;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowAffirmVo;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowProtocolVo;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import com.tuodao.bp.model.facade.traningcenter.output.ProductTenderVo;
import com.tuodao.bp.utils.MD5Utils;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.Map;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/11/6
 * @time: 15:18
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowTenderControllerTest extends FacadeTestApi {

    @Autowired
    private BorrowTenderService borrowTenderService;

    @Autowired
    private ProductClient productClient;


    /**
     * 打印投标验证码
     * @throws Exception
     */
    @Test
    public void image()throws Exception{
        super.url = "router/captcha";
        doPost(MediaType.APPLICATION_JSON_UTF8,new BasePojo());
    }
    /**
     * 投标   标的开启验证码时,需要先调用image()方法在控制台打印验证码
     * @throws Exception
     */
    @Test
    public void tender()throws Exception{
        super.url = "router/tc/tender/add_tender";
        TenderParam param = new TenderParam();
        param.setTenderMoney(1000D);
        param.setAuthCode("3Wwb");//调用投标之前需要调用
        param.setPayPassword(MD5Utils.md5("123456"));
        param.setVoucherId(null);
        param.setBorrowId(35);

        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<String> result = JSONObject.parseObject(s, new TypeReference<RespResult<String>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    /**
     * 投标结果查询  如果使用该方法前需要调用tender来获取唯一结果
     * @throws Exception
     */
    @Test
    public void getTenderResult()throws Exception{
        super.url = "router/tc/tender/tender_result";
        TenderResultParam param = new TenderResultParam();
        param.setNum(1);
        param.setTenderKey("13012345678-xmvbys2eozwzpk4lojj104cc85c5-c878-4823-b290-3b47af7bdcdb");
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<ReturnCacheInfo> result = JSONObject.parseObject(s, new TypeReference<RespResult<ReturnCacheInfo>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getAppTenderResult()throws Exception{
        super.url = "router/tc/tender/app_tender_result";
        TenderResultParam param = new TenderResultParam();
        param.setNum(4);
        param.setTenderKey("13012345678-xmvbys2eozwzpk4lojj104cc85c5-c878-4823-b290-3b47af7bdcdb");
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<TenderResult> result = JSONObject.parseObject(s, new TypeReference<RespResult<TenderResult>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void tenderList()throws Exception{
        super.url = "router/tc/tender/tender_list";
        TenderListParam param = new TenderListParam();
        param.setStatus(1);

        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<BorrowTenderVo>> result = JSONObject.parseObject(s, new TypeReference<RespResult<PageInfo<BorrowTenderVo>>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getTenderDetail()throws Exception{
        super.url = "router/tc/tender/tender_detail";
        TenderDetailParam param = new TenderDetailParam();
        param.setTenderId(52);

        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<BorrowTenderVo> result = JSONObject.parseObject(s, new TypeReference<RespResult<BorrowTenderVo>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);

    }

    @Test
    public void borrowFullCheck(){
        ProductOutput output = productClient.getProduct(35);
        if(output != null){
            borrowTenderService.borrowFullCheck(output);
        }
    }

    @Test
    public void tenderCorn(){
        borrowTenderService.tenderCorn();
    }



    @Test
    public void getBorrowProtocol()throws Exception{
        super.url = "router/tc/tender/borrow_protocol";
        TenderDetailParam param = new TenderDetailParam();
        param.setTenderId(12);
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<BorrowProtocolVo> result = JSONObject.parseObject(s, new TypeReference<RespResult<BorrowProtocolVo>>() {});

        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getTenderList()throws Exception{
        super.url = "router/tc/product/tender_list";
        BorrowParam param = new BorrowParam();
        param.setProductId(35);
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<ProductTenderVo>> result = JSONObject.parseObject(s, new TypeReference<RespResult<PageInfo<ProductTenderVo>>>() {});

        Assert.assertEquals(result.getCode(),100000L);
    }
    @Test
    public void getTenderMaxAndLast()throws Exception{
        super.url = "router/tc/product/tender_max_last";
        BorrowParam param = new BorrowParam();
        param.setProductId(35);
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<Map<String,String>> result = JSONObject.parseObject(s, new TypeReference<RespResult<Map<String,String>>>() {});

        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void verifyOrderPassword()throws Exception{
        super.url = "router/tc/tender/verify_order_password";
        TenderOrderParam param = new TenderOrderParam();
        param.setProductId(35);
        param.setOrderPassword(MD5Utils.md5("123456"));
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<Boolean> result = JSONObject.parseObject(s, new TypeReference<RespResult<Boolean>>() {});
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void verifyImageCode()throws Exception{
        super.url = "router/tc/tender/verify_code";
        TenderCodeParam param = new TenderCodeParam();
        param.setCode("meFC");
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<Boolean> result = JSONObject.parseObject(s, new TypeReference<RespResult<Boolean>>() {});
        Assert.assertEquals(result.getCode(),100000L);
    }

    @Test
    public void getTenderAffirm()throws Exception{
        super.url = "router/tc/tender/tender_affirm";
        BorrowParam param = new BorrowParam();
        param.setProductId(35);
        String s = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<BorrowAffirmVo> result = JSONObject.parseObject(s, new TypeReference<RespResult<BorrowAffirmVo>>() {
        });
        Assert.assertEquals(result.getCode(),100000L);
    }

}

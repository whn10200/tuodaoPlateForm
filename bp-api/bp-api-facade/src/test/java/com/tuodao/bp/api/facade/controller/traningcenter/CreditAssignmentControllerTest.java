package com.tuodao.bp.api.facade.controller.traningcenter;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.FacadeTestApi;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.traningcenter.input.CreditAssignmentInput;
import com.tuodao.bp.model.business.useraccount.input.LoginInput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.model.facade.traningcenter.output.*;
import org.apache.commons.lang.time.DateUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author tookbra
 * @date 2017/10/18
 * @description
 */
public class CreditAssignmentControllerTest extends FacadeTestApi {

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
        super.REQUEST_TYPE = "APP";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, input);
        RespResult<UserLoginOutput> respResult = JSON.parseObject(resp, new TypeReference<RespResult<UserLoginOutput>>(){});
        UserLoginOutput output = respResult.getContent();
        super.ACCESS_ID = output.getAccessId();
        super.ACCESS_KEY = output.getAccessKey();

    }


    /**
     * 债权转让列表
     */
    @Test
    public void queryType() throws Exception {
        TransferQueryParam param = new TransferQueryParam();
        param.setType(0);
        this.query(param);
    }

    @Test
    public void queryTypeOne() throws Exception {
        TransferQueryParam param = new TransferQueryParam();

        param.setType(1);
        this.query(param);
    }

    @Test
    public void queryTypeTwo() throws Exception {
        TransferQueryParam param = new TransferQueryParam();

        param.setType(2);
        this.query(param);
    }

    @Test
    public void queryTypeThree() throws Exception {
        TransferQueryParam param = new TransferQueryParam();

        param.setType(3);
        this.query(param);
    }

    @Test
    public void queryTypeFour() throws Exception {
        TransferQueryParam param = new TransferQueryParam();

        param.setType(4);
        this.query(param);
    }



    public void query(TransferQueryParam param) throws Exception {
        url = "router/creditAssignment/query";

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);
        RespResult<PageInfo<TransferQueryVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransferQueryVo>>>(){});
        Assert.assertNotNull(respResult.getContent());
    }



    /**
     * 债权转让详情
     */
    @Test
    public void queryInfo() throws Exception {
        url = "router/creditAssignment/query/info";
        TransferQueryInfoParam param = new TransferQueryInfoParam();
        param.setTransferId(2);

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<TransferQueryInfoVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransferQueryInfoVo>>>(){});
        Assert.assertNotNull(respResult.getContent());
    }

    /**
     * 投资债权, 起投金额少于100
     * @throws Exception
     */
    @Test
    public void tender() throws Exception {
        url = "router/creditAssignment/tender";
        TenderTransferParam param = new TenderTransferParam();
        param.setTransferId(1);
        param.setPayPassword("123456");
        param.setMoney(new BigDecimal(50));

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<TransferQueryInfoVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransferQueryInfoVo>>>(){});
    }

    /**
     * 投资债权
     * @throws Exception
     */
    @Test
    public void tender1() throws Exception {
        url = "router/creditAssignment/tender";
        TenderTransferParam param = new TenderTransferParam();
        param.setTransferId(1);
//        param.setPayPassword("e10adc3949ba59abbe56e057f20f883e");
//        param.setPayPassword("e10adc3949ba51abbe56e057f20f883e");
        param.setPayPassword("123");
        param.setMoney(new BigDecimal(101));

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<TransferQueryInfoVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransferQueryInfoVo>>>(){});
    }

    /**
     * 债权转让投资列表
     */
    @Test
    public void tenderList() throws Exception {
        url = "tender/transfer/list";
        TransferQueryInfoParam param = new TransferQueryInfoParam();
        param.setTransferId(1);

        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<TenderTransferVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TenderTransferVo>>>(){});
        Assert.assertNotNull(respResult.getContent());
    }



    /**
     * 我的可转让列表
     */
    @Test
    public void pageByTransferableList() throws Exception {
        CreditAssignmentInput param = new CreditAssignmentInput();

        Date date = new Date();
        param.setStartTime(DateUtils.addDays(date, -10));
        param.setEndTime(DateUtils.addDays(date, 10));
        param.setStatus(0);

        transferList(param);
    }

    /**
     * 我的转让中列表
     */
    @Test
    public void pageByTransferList() throws Exception {
        CreditAssignmentInput param = new CreditAssignmentInput();

        Date date = new Date();
        param.setStartTime(DateUtils.addDays(date, -10));
        param.setEndTime(DateUtils.addDays(date, 10));
        param.setStatus(1);

        transferList(param);
    }

    /**
     * 我的已转让列表
     */
    @Test
    public void pageByTransferedList() throws Exception {
        CreditAssignmentInput param = new CreditAssignmentInput();

        Date date = new Date();
        param.setStartTime(DateUtils.addDays(date, -10));
        param.setEndTime(DateUtils.addDays(date, 10));
        param.setStatus(2);

        transferList(param);
    }

    /**
     * 我的受让列表、app
     */
    @Test
    public void pageByCompromisedList() throws Exception {
        CreditAssignmentInput param = new CreditAssignmentInput();

        Date date = new Date();
        param.setStartTime(DateUtils.addDays(date, -10));
        param.setEndTime(DateUtils.addDays(date, 10));
        param.setStatus(3);

        transferList(param);
    }

    private void transferList(CreditAssignmentInput param) throws Exception {
        url = "router/creditAssignment/list";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<CreditAssignmentVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<CreditAssignmentVo>>>(){});
        Assert.assertNotNull(respResult.getContent());
    }


    /**
     * 我的受让列表、pc
     * @throws Exception
     */
    @Test
    public void compromisedList() throws Exception {
        CreditAssignmentInput param = new CreditAssignmentInput();
        Date date = new Date();
        param.setStartTime(DateUtils.addDays(date, -10));
        param.setEndTime(DateUtils.addDays(date, 10));
        param.setStatus(0);
        url = "router/creditAssignment/compromisedList";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<CreditAssignmentVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<CreditAssignmentVo>>>(){});
        Assert.assertNotNull(respResult.getContent());
    }

    /**
     * 我的受让详情，产品
     */
    @Test
    public void tenderInfo() throws Exception {
        TransfereeProductParam param = new TransfereeProductParam();
        param.setTenderId(1);

        url = "router/creditAssignment/tender/product/info";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<TransfereeVo> respResult = JSON.parseObject(resp, new TypeReference<RespResult<TransfereeVo>>(){});
        Assert.assertNotNull(respResult.getContent());
    }

    /**
     * 我的受让详情，回款计划
     */
    @Test
    public void repaymentPlan() throws Exception {
        TransfereeProductParam param = new TransfereeProductParam();
        param.setTenderId(1);

        url = "router/creditAssignment/tender/repayment/plan";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<TransfereeVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransfereeVo>>>(){});
        Assert.assertTrue(respResult.isSuccess());
    }

    /**
     * 债权转让申请 信息
     */
    @Test
    public void preApply() throws Exception {
        TransferApplyParam param = new TransferApplyParam();
        param.setTenderId(1);

        url = "router/creditAssignment/preApply";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<TransfereeVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransfereeVo>>>(){});
        Assert.assertTrue(respResult.isSuccess());
    }

    /**
     * 债权转让申请
     */
    @Test
    public void apply() throws Exception {
        SaveTransferParam param = new SaveTransferParam();
        param.setTenderId(1);
        param.setPayPassword("123455");

        url = "router/creditAssignment/apply";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<PageInfo<TransfereeVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransfereeVo>>>(){});
        Assert.assertTrue(respResult.isSuccess());
    }

    /**
     * 我的债权列表-可转让 app
     */
    @Test
    public void pageByTransferable() throws Exception {
        PagePojo pagePojo = new PagePojo();
        pagePojo.setRequestType("APP");

        url = "router/creditAssignment/app/transferableList";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, pagePojo);

        RespResult<PageInfo<TransferableVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransferableVo>>>(){});
        Assert.assertTrue(respResult.isSuccess());
    }

    /**
     * 我的债权列表-转让记录 app
     */
    @Test
    public void transferLog() throws Exception {
        PagePojo pagePojo = new PagePojo();
        pagePojo.setRequestType("APP");

        url = "router/creditAssignment/app/transferLog";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, pagePojo);

        RespResult<PageInfo<TransferCompromisedVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransferCompromisedVo>>>(){});
        Assert.assertTrue(respResult.isSuccess());
    }

    /**
     * 我的债权列表-已受让 app
     */
    @Test
    public void pageByCompromisedListApp() throws Exception {
        PagePojo pagePojo = new PagePojo();
        pagePojo.setRequestType("APP");

        url = "router/creditAssignment/app/compromisedList";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, pagePojo);

        RespResult<PageInfo<TransferCompromisedVo>> respResult = JSON.parseObject(resp, new TypeReference<RespResult<PageInfo<TransferCompromisedVo>>>(){});
        Assert.assertTrue(respResult.isSuccess());
    }

    /**
     * 我的受让详情，产品 APP
     */
    @Test
    public void tenderInfoApp() throws Exception {
        TransfereeProductParam param = new TransfereeProductParam();
        param.setTenderId(1);

        url = "router/creditAssignment/app/tender/product/info";
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, param);

        RespResult<TransfereeAppVo> respResult = JSON.parseObject(resp, new TypeReference<RespResult<TransfereeAppVo>>(){});
        Assert.assertNotNull(respResult.getContent());
    }
}

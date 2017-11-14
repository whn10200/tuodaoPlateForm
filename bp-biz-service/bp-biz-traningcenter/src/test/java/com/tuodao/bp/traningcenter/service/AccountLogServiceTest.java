package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogInput;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.model.facade.traningcenter.output.AccountLogVo;
import com.tuodao.bp.traningcenter.TraningCenterApplication;
import com.tuodao.bp.traningcenter.db.model.basic.AccountCash;
import com.tuodao.bp.traningcenter.db.model.basic.AccountLog;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.service.impl.GenerateService;
import com.tuodao.bp.utils.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/18
 * @time: 17:10
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountLogServiceTest {

    AccountLog log = new AccountLog();

    AccountLogInput input = new AccountLogInput();

    @Autowired
    private AccountLogService accountLogService;

    @Autowired
    private GenerateService generateService;

    @Before
    public void before(){
        input.setUserId("wangyanbingzhuanyong");
        input.setStartTime(DateUtil.subDay(new Date(),30));
        input.setEndTime(new Date());
        input.setCurrentPage(1);
        input.setPageSize(20);
    }

    @Test
    public void getUserAccountLogByPage(){
        PageInfo<AccountLogVo> page = accountLogService.getUserAccountLogByPage(input);
        System.out.println("总条数:" + page.getTotal());
        page.getList().forEach(accountLogVo -> {
            System.out.println("查询结果:" + accountLogVo);
        });
    }

    @Test
    public void insertBorrowCollectionLogBatch(){
        List<BorrowCollection> list = new ArrayList<>();

        BorrowCollection output = new BorrowCollection();
        output.setBorrowId(10086);
        output.setCapital(BigDecimal.valueOf(1));
        output.setPreCollectionTime(new Date());
        output.setCouponAccount(BigDecimal.valueOf(2));
        output.setPreInterest(BigDecimal.valueOf(3));
        output.setPeriod(4);
        output.setPeriods(5);
        output.setPlatformAccount(BigDecimal.valueOf(6));
        output.setStatus(7);
        output.setPreCapital(BigDecimal.valueOf(8));
        output.setUserId("wangyanbingzhuanyong");
        list.add(output);

        output = new BorrowCollection();
        output.setBorrowId(10086);
        output.setCapital(BigDecimal.valueOf(2));
        output.setPreCollectionTime(new Date());
        output.setCouponAccount(BigDecimal.valueOf(3));
        output.setPreInterest(BigDecimal.valueOf(4));
        output.setPeriod(5);
        output.setPeriods(6);
        output.setPlatformAccount(BigDecimal.valueOf(7));
        output.setStatus(8);
        output.setPreCapital(BigDecimal.valueOf(9));
        output.setUserId("wangyanbingzhuanyong");
        list.add(output);

        accountLogService.insertBorrowCollectionLogBatch(list);
    }

    @Test
    public void insertAccountCashApplyLog(){
        AccountCash accountCash = new AccountCash();
        accountCash.setUserId("wangyanbingzhuanyong");
        accountCash.setSource(1);
        accountCash.setAccount(BigDecimal.valueOf(100000));
        accountCash.setOrderNo("test_________");
        accountCash.setCashNum("");
        accountCash.setFee(BigDecimal.valueOf(300));
        accountLogService.insertAccountCashApplyLog(accountCash);
    }

    @Test
    public void insertAccountCashLog(){
        AccountCash accountCash = new AccountCash();
        accountCash.setUserId("wangyanbingzhuanyong");
        accountCash.setSource(1);
        accountCash.setAccount(BigDecimal.valueOf(100000));
        accountCash.setRealAccount(BigDecimal.valueOf(99700));
        accountCash.setStatus(1);
        accountCash.setOrderNo("test_________");
        accountCash.setCashNum("");
        accountCash.setFee(BigDecimal.valueOf(300));
        accountLogService.insertAccountCashSuccessLog(accountCash,"");
    }
    @Test
    public void insertBorrowTenderFrostLog(){
        BorrowTender tender = new BorrowTender();
        tender.setOrderId(generateService.get());
        tender.setStatus(0);
        tender.setBorrowId(10087);
        tender.setMobile("1335251251");
        tender.setUserId("wangyanbingzhuanyong");
        tender.setPreAccount(BigDecimal.valueOf(20000));
        tender.setAccount(BigDecimal.valueOf(19000));
        tender.setAccountInterest(BigDecimal.valueOf(5000));
        accountLogService.insertBorrowTenderFrostLog(tender,"");
    }



    @Test
    public void insertTenderCancelLog(){
        BorrowTender tender = new BorrowTender();
        tender.setOrderId(generateService.get());
        tender.setStatus(0);
        tender.setBorrowId(10087);
        tender.setMobile("1335251251");
        tender.setUserId("wangyanbingzhuanyong");
        tender.setPreAccount(BigDecimal.valueOf(20000));
        tender.setAccount(BigDecimal.valueOf(19000));
        tender.setAccountInterest(BigDecimal.valueOf(5000));
        accountLogService.insertTenderCancelLog(Lists.newArrayList(tender),"");
    }

    @Test
    public void insertTenderFailLog(){
        BorrowTender tender = new BorrowTender();
        tender.setOrderId(generateService.get());
        tender.setStatus(0);
        tender.setBorrowId(10087);
        tender.setMobile("1335251251");
        tender.setUserId("wangyanbingzhuanyong");
        tender.setPreAccount(BigDecimal.valueOf(20000));
        tender.setAccount(BigDecimal.valueOf(19000));
        tender.setAccountInterest(BigDecimal.valueOf(5000));
        accountLogService.insertTenderFailLog(tender, AccountLogType.TRANSFER_FREEZE_FAIL);
    }

}

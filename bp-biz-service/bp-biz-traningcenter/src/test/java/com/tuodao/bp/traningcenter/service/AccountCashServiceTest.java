package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.model.traningcenter.input.AccountCashApplyInput;
import com.tuodao.bp.traningcenter.TraningCenterApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

/**
 * @description: 提现测试
 * @author: 王艳兵
 * @date: 2017/10/18
 * @time: 15:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountCashServiceTest {

    @Autowired
    private AccountCashService accountCashService;

    AccountCashApplyInput input = new AccountCashApplyInput();

    CashListParam param = new CashListParam();
    @Before
    public void before(){
        input.setUserId("wangyanbingzhuanyong");
        input.setUseFree(1);
        input.setAccount(BigDecimal.valueOf(10000));
        input.setAddIp("127.0.0.1");
        input.setBankNum("0000");
        input.setFee(BigDecimal.valueOf(200));
        input.setOrderNo("123123123123123123");
        input.setSource(3);
        param.setUserId("wangyanbingzhuanyong");
        param.setCurrentPage(1);
        param.setPageSize(20);
    }

    @Test
    public void getCashTotalByUserId(){
        AccountCashApplyInput input = new AccountCashApplyInput();
        double free = accountCashService.getCashTotalByUserId(input.getUserId());
        System.out.println("获取用户提现总额:" + free);
    }

    @Test
    public void addAccountCash(){
        accountCashService.addAccountCash(input);
    }


    @Test
    public void updateAccountCash(){
        accountCashService.updateAccountCash("123123123123123123",2);
    }

    @Test
    public void getUserCashListByPage(){
        PageInfo<AccountCashVo> page = accountCashService.getUserCashListByPage(param);
        System.out.println("总条数:" + page.getTotal());
        page.getList().forEach(accountCashVo -> System.out.println(accountCashVo));
    }

}

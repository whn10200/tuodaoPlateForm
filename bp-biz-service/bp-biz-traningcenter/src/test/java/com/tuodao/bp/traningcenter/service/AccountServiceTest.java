package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.traningcenter.TraningCenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/24
 * @time: 10:52
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void getUserAccount(){
        AccountOutput account = accountService.getUserAccount("wangyanbingzhuanyong");
        System.out.println(account);
    }

    @Test
    public void createUserAccount(){
        accountService.createUserAccount("张三");
    }

}

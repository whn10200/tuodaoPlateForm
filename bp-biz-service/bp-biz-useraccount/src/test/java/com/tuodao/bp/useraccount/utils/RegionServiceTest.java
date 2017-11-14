package com.tuodao.bp.useraccount.utils;

import com.tuodao.bp.useraccount.UserAccountApplication;
import com.tuodao.bp.useraccount.service.RegionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/21
 * @time: 14:17
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = UserAccountApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RegionServiceTest {

    @Resource
    private RegionService regionService;

    @Test
    public void getRegion() throws Exception {
        System.out.println(regionService.getRegion("125.119.253.193"));
    }

}
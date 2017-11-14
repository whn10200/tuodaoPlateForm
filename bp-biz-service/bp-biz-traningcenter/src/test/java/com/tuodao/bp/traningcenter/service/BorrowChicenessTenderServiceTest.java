package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.traningcenter.TraningCenterApplication;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/30
 * @time: 14:09
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BorrowChicenessTenderServiceTest {

    @Autowired
    private BorrowChicenessTenderService borrowChicenessTenderService;


    @Test
    public void selectClaim(){
        borrowChicenessTenderService.selectClaim(12,12);
    }

    @Test
    public void getTendingByChoicenessTenderId(){
        List<BorrowCollection> list = borrowChicenessTenderService.getTendingByChoicenessTenderId(31, 55);
        list.forEach(collection -> System.out.println("回款列表:" + collection));
    }

}

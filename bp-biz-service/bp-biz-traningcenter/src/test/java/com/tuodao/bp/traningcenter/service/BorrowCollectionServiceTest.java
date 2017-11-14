package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;
import com.tuodao.bp.model.business.traningcenter.output.CollectionCountOutput;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionDayVo;
import com.tuodao.bp.model.traningcenter.input.BorrowCollectionInput;
import com.tuodao.bp.traningcenter.TraningCenterApplication;
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
import java.util.Map;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/19
 * @time: 09:14
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BorrowCollectionServiceTest {

    @Autowired
    private BorrowCollectionService borrowCollectionService;

    BorrowCollectionInput input = new BorrowCollectionInput();

    @Before
    public void before(){
        input.setUserId("wangyanbingzhuanyong");
    }

    @Test
    public void getUserTotalCollection(){
        double userTotalCollection = borrowCollectionService.getUserTotalCollection(input.getUserId());
        System.out.println("回款总额:" + userTotalCollection);
    }



    @Test
    public void updateCollection(){
        BorrowRepaymentInput input = new BorrowRepaymentInput();
        borrowCollectionService.collection(input);
    }

    @Test
    public void getMonthRealCollection(){
        CollectionParam param = new CollectionParam();
        param.setUserId("13012345678-xmvbys2eozwzpk4lojj1");
        param.setType(0);
        param.setDay("2017-11-23");
        Map<String, BigDecimal> map = borrowCollectionService.getRealCollection(param);
        for(String key :map.keySet()){
            System.out.println(key + ":" +map.get(key).doubleValue());
        }
    }

    @Test
    public void getMonthPreCollection(){
        CollectionParam param = new CollectionParam();
        param.setUserId("13012345678-xmvbys2eozwzpk4lojj1");
        param.setType(0);
        param.setDay("2017-11-23");
        Map<String, BigDecimal> map = borrowCollectionService.getPreCollection(param);
        for(String key :map.keySet()){
            System.out.println(key + ":" +map.get(key).doubleValue());
        }
    }
    @Test
    public void getCollectionCalendarByPage(){
        CollectionParam param = new CollectionParam();
        param.setDay("2017-11");
        param.setType(0);
        param.setUserId("wangyanbingzhuanyong");
        PageInfo<BackMoneyPlanOutput> page = borrowCollectionService.getCollectionCalendarByPage(param);
        if(page.getList() != null && page.getList().size() > 0){
            page.getList().forEach(planOutput -> System.out.println(planOutput));
        }
    }

    @Test
    public void getCollectionDays(){
        List<CollectionDayVo> list = borrowCollectionService.getCollectionDays("wangyanbingzhuanyong", "2017-11");
        list.forEach(vo-> System.out.println(vo));
    }


    @Test
    public void collection(){
        BorrowRepaymentInput input = new BorrowRepaymentInput();
        input.setBorrowId(35);
        input.setPeriod(1);
        input.setAdvance(true);
        borrowCollectionService.collection(input);
    }

    @Test
    public void getUserCollectionInterest(){
        CollectionCountOutput userCollectionInterest = borrowCollectionService.getUserCollectionInterest("13012345678-xmvbys2eozwzpk4lojj1");
        System.out.println(userCollectionInterest);
    }

}


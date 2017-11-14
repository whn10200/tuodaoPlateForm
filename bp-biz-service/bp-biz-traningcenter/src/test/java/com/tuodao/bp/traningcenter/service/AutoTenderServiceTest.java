package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.traningcenter.output.AutoTenderOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;
import com.tuodao.bp.traningcenter.TraningCenterApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/10/24
 * @time: 11:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AutoTenderServiceTest {

    @Autowired
    private AutoTenderService autoTenderService;

    @Test
    public void saveAutoTender(){
        AutoTenderParam param = new AutoTenderParam();
        param.setUserId("李四");
        param.setMinAccount(1000D);
        param.setMaxAccount(10000D);
        param.setMinPeriod(1);
        param.setMaxPeriod(10);
        param.setUseCoupon(1);
        autoTenderService.saveAutoTender(param);
    }

    @Test
    public void getUserAutoTender(){
        AutoTenderVo 李四 = autoTenderService.getUserAutoTender("李四");
        System.out.println(李四);
    }

    @Test
    public void closeAutoTender(){
        autoTenderService.closeAutoTender("李四");
    }

    @Test
    public void getTotalAutoTender(){
        long totalAutoTender = autoTenderService.getTotalAutoTender();
        System.out.println("总自动投标人数:" + totalAutoTender);
    }

    @Test
    public void getList(){
        List<AutoTenderOutput> list = autoTenderService.getList(100);
        if(list != null && list.size() > 0){
            list.forEach(output -> System.out.println(output));
        }
    }

    @Test
    public void getAutoTenderListByPage(){
        PagePojo pojo = new PagePojo();
        pojo.setUserId("李四");
        PageInfo<AutoTenderListVo> page = autoTenderService.getAutoTenderListByPage(pojo);
        if(page.getList() != null && page.getList().size() > 0){
            page.getList().forEach(vo-> System.out.println( "自动投标记录:" + vo));
        }
    }


    @Test
    public void getAutoTenderDetail(){
        autoTenderService.getAutoTenderDetail("李四",12);
    }

}

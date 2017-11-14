package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.product.ProductConstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.facade.traningcenter.input.BorrowParam;
import com.tuodao.bp.model.facade.traningcenter.output.ProductTenderVo;
import com.tuodao.bp.traningcenter.TraningCenterApplication;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
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
import java.util.concurrent.CountDownLatch;

/**
 * @description: 投标单元测试
 * @author: 王艳兵
 * @date: 2017/10/20
 * @time: 10:01
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class BorrowTenderServiceTest {

    TenderExecutor executor = new TenderExecutor();
    TenderExecutor executor2 = new TenderExecutor();

    @Autowired
    private BorrowTenderService borrowTenderService;

    @Autowired
    private BorrowTenderMapper borrowTenderMapper;

    @Before
    public void before(){
        executor.setProduct(new ProductOutput());
        executor.setDiscount(null);
//        executor.setUser(new UserAccountInfo("wangyanbingzhuanyong"));
        executor.setTenderMoney(30000D);
        executor.setTenderMode(TenderConstant.TENDER_MODE_HAND);
        executor.setChannel(1);
        executor.setAddIp("127.0.0.2");
        executor.setTenderType(ProductConstant.PRODUCT_TYPE_COMMON);
        /**
         * 生成唯一查询结果
         */
        String tenderKey = "wangyanbingzhuanyongb1a8a7b5";
        executor.setTenderKey(tenderKey);

        executor2.setProduct(new ProductOutput());
        executor2.setDiscount(null);
//        executor2.setUser(new UserAccountInfo("wangyanbingzhuanyong2"));
        executor2.setTenderMoney(20000D);
        executor2.setTenderMode(TenderConstant.TENDER_MODE_HAND);
        executor2.setChannel(2);
        executor2.setAddIp("127.0.0.3");
        executor2.setTenderType(ProductConstant.PRODUCT_TYPE_COMMON);
        /**
         * 生成唯一查询结果
         */
        String key = "wangyanbingzhuanyong2";

        executor2.setTenderKey(key);
    }



    @Test
    public void tenderProducer1(){
        CountDownLatch count = new CountDownLatch(1);
        try {
            for(int i = 1 ;i < 200; i++){
                new Thread(new TenderTest(borrowTenderService,count)).start();
            }
        }finally {
            count.countDown();
        }
    }

    class TenderTest implements Runnable{

        private BorrowTenderService tenderService;

        private CountDownLatch count;

        public TenderTest(BorrowTenderService service,CountDownLatch count){
            this.count = count;
            this.tenderService = service;
        }

        @Override
        public void run() {
            try {
                count.await();
                tenderService.tenderProducer(executor);
            }catch (Exception e){
            }
        }
    }

    @Test
    public void tenderProducer(){
        borrowTenderService.tenderProducer(executor);
    }

    @Test
    public void tenderConsumer(){
        borrowTenderService.tenderConsumer(executor);
        System.out.println("唯一查询key:" + executor.getTenderKey());
    }

    @Test
    public void tenderSuccessTask(){
        borrowTenderService.tenderSuccessTask("p35_tender171110103250355920_0","13012345678-xmvbys2eozwzpk4lojj104cc85c5-c878-4823-b290-3b47af7bdcdb");
    }

    @Test
    public void tenderFailTask(){
        borrowTenderService.tenderFailTask("p10086_tender17102011141820964_0","投标失败",null);
    }



    @Test
    public void withdrawTender(){
        ProductOutput productOutput = new ProductOutput();
        borrowTenderService.withdrawTender(productOutput);
    }


    @Test
    public void getTenderVoucherCount(){
        BigDecimal test1 = borrowTenderMapper.getTenderVoucherCount("test1");
        System.out.println(test1.doubleValue());
    }

    @Test
    public void getProductTenderByPage(){
        BorrowParam param = new BorrowParam();
        param.setProductId(35);
        PageInfo<ProductTenderVo> page = borrowTenderService.getProductTenderByPage(param);
        page.getList().forEach(productTenderVo -> System.out.println(productTenderVo));
    }


}

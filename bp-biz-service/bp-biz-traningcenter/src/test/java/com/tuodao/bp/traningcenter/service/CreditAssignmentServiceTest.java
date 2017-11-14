package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.model.business.traningcenter.cache.TransferTenderInfo;
import com.tuodao.bp.model.business.traningcenter.cache.UnFreezeInfo;
import com.tuodao.bp.model.business.traningcenter.input.CreditAssignmentInput;
import com.tuodao.bp.traningcenter.TraningCenterApplication;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author qingli.chen
 * @date 2017/10/20
 * @description
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CreditAssignmentServiceTest {

    @Autowired
    private CreditAssignmentService creditAssignmentService;

    @Test
    public void pageByTransferableList(){
        CreditAssignmentInput input = new CreditAssignmentInput();
        Date date = new Date();
        input.setUserId("1888888888888");
        input.setStartTime(DateUtils.addDays(date, -10));
        input.setEndTime(DateUtils.addDays(date, 10));
        input.setStatus(0);
        creditAssignmentService.pageByTransferableList(input);
    }

    public static void main(String[] args) {
        UnFreezeInfo unFreezeInfo = new UnFreezeInfo();
        List<TransferTenderInfo> tenderInfoList = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            TransferTenderInfo transferTenderInfo = new TransferTenderInfo();
            transferTenderInfo.setOrderNo(i + "");
            tenderInfoList.add(transferTenderInfo);
        }

        tenderInfoList.forEach(transferTenderInfo -> {
            transferTenderInfo.setSuccess(true);
        });

        tenderInfoList.forEach(transferTenderInfo -> {
            System.out.println(transferTenderInfo.toString());
        });

        tenderInfoList.stream()
                .filter(p -> p.getOrderNo().equals("0")).findFirst().get().setSuccess(false);

        tenderInfoList.forEach(transferTenderInfo -> {
            System.out.println(transferTenderInfo.toString());
        });

        Long count = tenderInfoList.stream().filter(p -> p.isSuccess()).count();
        System.out.println(count);

    }
}

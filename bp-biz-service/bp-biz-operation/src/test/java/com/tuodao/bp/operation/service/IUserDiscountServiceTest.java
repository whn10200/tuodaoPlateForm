package com.tuodao.bp.operation.service;

import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.CouponGrantMqInfo;
import com.tuodao.bp.operation.OperationTestApi;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/29
 * @time: 14:35
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class IUserDiscountServiceTest extends OperationTestApi {

    @Resource
    private IUserDiscountService userDiscountService;

    @Test
    public void couponGrant() throws Exception {

        CouponGrantMqInfo mqInfo = new CouponGrantMqInfo();
        mqInfo.setUserId("15988888884-d9ccs6n849nz9wl8t1ci");
        mqInfo.setMobile("15988888884");
        mqInfo.setWelfareActivityCode(PublicConstant.WELFARE_ACTIVITY_CODE_V6_UP);
        userDiscountService.couponGrant(mqInfo, true);

    }

}
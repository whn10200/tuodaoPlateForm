package com.tuodao.bp.useraccount.service;

import com.tuodao.bp.model.business.useraccount.output.UserVipInfoOutput;

import java.math.BigDecimal;

/**
 * Description:
 * User: zkai
 * Date: 2017/9/19
 * Time: 10:58
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface IUserVipInfoService {

    /**
     * 修改用户vip等级
     *
     * @param userId       用户编号
     * @param lastMonthAvg 上月日均账户资产
     */
    void updateVipLevel(String userId, BigDecimal lastMonthAvg);

    /**
     * 修改用户vip等级
     *
     * @param userId 用户编号
     */
    void updateVipLevel(String userId);

    /**
     * 根据用户Id查询VIP信息
     *
     * @param userId 用户编码
     * @return 用户VIP信息
     */
    UserVipInfoOutput selectByUserId(String userId);
}

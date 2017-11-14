package com.tuodao.bp.useraccount.persistence.mapper.biz;

import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @description:
 * @author: mif
 * @date: 2017/9/4
 * @time: 11:33
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface BizTaskMapper {

    /**
     * 金融帐号资金每日掺入历史表
     */
    void accountFinaceDailyCopy();

    /**
     * 根据用户编号查找 上月日均账户资产
     *
     * @return
     */
    BigDecimal selectFinaceDailyLastMonthAvgByUid(@Param("userId") String userId);

    /**
     * 根据用户编号查找 本月日均账户资产
     *
     * @return
     */
    BigDecimal selectFinaceDailyThisMonthAvgByUid(@Param("userId") String userId);

    /**
     * 上月日均账户资产 用户编号为key, 上月日均账户资产为value
     *
     * @return
     */
    List<Map<String,BigDecimal>> selectFinaceDailyLastMonthAvgMap();

    /**
     * 查询新手用户（注册日期距今30天）
     */
    Set<String> selectNewbieUser();

    /**
     * 查询当月生日的用户
     *
     * @return
     */
    List<String> selectBirthdayUsers();
}

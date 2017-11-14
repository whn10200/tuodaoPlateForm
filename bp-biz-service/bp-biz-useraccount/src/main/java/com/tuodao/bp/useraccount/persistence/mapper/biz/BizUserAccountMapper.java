package com.tuodao.bp.useraccount.persistence.mapper.biz;

import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.UserDepositNo;
import com.tuodao.bp.useraccount.persistence.model.biz.FinancialPlannerStatistics;

import java.util.List;

/**
 * @description: 自定义用户账户Mapper
 * @author: mif
 * @date: 2017/9/5
 * @time: 16:05
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface BizUserAccountMapper {

    /**
     * 查询用户账户基本信息
     *
     * @param userId 用户编号
     * @return 用户账号信息
     */
    UserAccountInfo selectUserAccountInfo(String userId);

    /**
     * 根据用户编号查询用户理财师等级统计信息
     *
     * @param userId 用户编号
     * @return 理财师等级
     */
    FinancialPlannerStatistics selectUserFinancialPlanner(String userId);

    /**
     * 获取所有的用户帐号信息
     *
     * @return 用户帐号列表
     */
    List<UserAccountInfo> selectUserAccountInfoList();

    /**
     * 查询存管帐号列表
     *
     * @param userIds 用户编码列表
     * @return 数据列表
     */
    List<UserDepositNo> selectDepositNoList(List<String> userIds);
}

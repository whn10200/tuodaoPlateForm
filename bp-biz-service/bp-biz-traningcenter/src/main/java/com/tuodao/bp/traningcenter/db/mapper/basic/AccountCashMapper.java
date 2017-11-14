package com.tuodao.bp.traningcenter.db.mapper.basic;


import com.tuodao.bp.traningcenter.db.model.basic.AccountCash;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author 王艳兵
 */
public interface AccountCashMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据库记录
     *
     * @param record
     * @return
     */
    int insert(AccountCash record);

    /**
     * 插入数据库记录
     *
     * @param record
     * @return
     */
    int insertSelective(AccountCash record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     * @return
     */
    AccountCash selectByPrimaryKey(Long id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AccountCash record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(AccountCash record);

    /**
     * 将提现记录状态由申请中更新为处理后的状态
     * @param accountCash 提现信息
     * @return
     */
    int updateAccountCashStatus(@Param("cash") AccountCash accountCash);

    /**
     * 获取用户总的提现金额 包含提现申请中的,
     * @param userId 用户ID
     * @return
     */
    BigDecimal getTotalCashByUserId(String userId);

    /**
     * 根据条件查询提现列表
     * @param cash 条件
     * @return
     */
    List<AccountCash> getList(AccountCash cash);

    /**
     * 根据订单号查询提现记录
     * @param orderNo
     * @return
     */
    AccountCash getByOrderNo(@Param("orderNo") String orderNo);

}
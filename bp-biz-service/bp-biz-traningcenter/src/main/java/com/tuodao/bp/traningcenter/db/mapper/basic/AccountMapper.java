package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.traningcenter.db.model.basic.Account;
import org.apache.ibatis.annotations.Param;

/**
 * @author 王艳兵
 */
public interface AccountMapper {
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
    int insert(Account record);

    /**
     * 插入数据库记录
     *
     * @param record
     * @return
     */
    int insertSelective(Account record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     * @return
     */
    Account selectByPrimaryKey(Long id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Account record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(Account record);

    /**
     * 根据用户ID查询账户信息
     * @param userId
     * @return
     */
    Account getByUserId(String userId);



    /**
     * 更新用户的提现金额 ,冻结金额增加,可用余额减少
     * @param userId 用户ID
     * @param cashFrost 提现金额
     * @return
     */
    int updateCashFrost(@Param("userId") String userId, @Param("cashFrost") double cashFrost);


}
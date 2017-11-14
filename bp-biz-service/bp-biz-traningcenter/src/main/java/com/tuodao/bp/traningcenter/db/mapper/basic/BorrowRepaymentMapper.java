package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.traningcenter.db.model.basic.BorrowRepayment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BorrowRepaymentMapper {
    /**
     * 根据主键删除数据库的记录
     *
     * @param id
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入数据库记录
     *
     * @param record
     */
    int insert(BorrowRepayment record);

    /**
     * 插入不为空的记录
     *
     * @param record
     */
    int insertSelective(BorrowRepayment record);

    /**
     * 根据主键获取一条数据库记录
     *
     * @param id
     */
    BorrowRepayment selectByPrimaryKey(Integer id);

    /**
     * 根据主键来更新部分数据库记录
     *
     * @param record
     */
    int updateByPrimaryKeySelective(BorrowRepayment record);

    /**
     * 根据主键来更新数据库记录
     *
     * @param record
     */
    int updateByPrimaryKey(BorrowRepayment record);

    /**
     * 批量插入
     * @param list
     * @return
     */
    int insertBatch(@Param("list")List<BorrowRepayment> list);

    /**
     * 查询已还
     * @param borrowId 标的id
     * @return
     */
    List<BorrowRepayment> selectByBorrowId(@Param("borrowId") Integer borrowId);

    /**
     * 查询还款计划
     * @param borrowId 标的id
     * @param status 状态
     * @return
     */
    List<BorrowRepayment> selectByBorrowIdAndStatus(@Param("borrowId") Integer borrowId, @Param("status") Integer status);

}
package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.model.business.traningcenter.input.AccountLogInput;
import com.tuodao.bp.traningcenter.db.model.basic.AccountLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @author 王艳兵
 */
public interface AccountLogMapper {

    /**
     * 根据主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 全量添加资金日志信息
     * @param record
     * @return
     */
    int insert(AccountLog record);

    /**
     * 添加非空资金日志信息
     * @param record
     * @return
     */
    int insertSelective(AccountLog record);

    /**
     * 根据主键获取资金日志信息
     * @param id
     * @return
     */
    AccountLog selectByPrimaryKey(Integer id);

    /**
     * 根据主键更新非空日志信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AccountLog record);

    /**
     * 根据主键全量更新资金日志信息
     * @param record
     * @return
     */
    int updateByPrimaryKey(AccountLog record);


    /**
     * 根据条件获取用户的资金记录信息
     * @param input
     * @return
     */
    List<AccountLog> getList(AccountLogInput input);

    /**
     * 查询资金记录
     * @param OrderNo 订单号
     * @return
     */
    AccountLog selectByOderNo(@Param("orderNo") String OrderNo);


}
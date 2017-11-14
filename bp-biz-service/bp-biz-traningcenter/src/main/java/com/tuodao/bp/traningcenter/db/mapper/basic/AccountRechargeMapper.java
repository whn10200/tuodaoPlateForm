package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.traningcenter.db.model.basic.AccountRecharge;
import com.tuodao.bp.traningcenter.db.model.basic.AccountRechargeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface AccountRechargeMapper {
    int countByExample(AccountRechargeExample example);

    int deleteByExample(AccountRechargeExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountRecharge record);

    int insertSelective(AccountRecharge record);

    List<AccountRecharge> selectByExample(AccountRechargeExample example);

    AccountRecharge selectByPrimaryKey(Integer id);

    @Select("select user_id, type, status, account, source, channel, order_no from account_recharge where order_no = #{orderNo}")
    AccountRecharge findByOrderNo(@Param("orderNo") String orderNo);

    int updateByExampleSelective(@Param("record") AccountRecharge record, @Param("example") AccountRechargeExample example);

    int updateByExample(@Param("record") AccountRecharge record, @Param("example") AccountRechargeExample example);

    int updateByPrimaryKeySelective(AccountRecharge record);

    int updateByPrimaryKey(AccountRecharge record);

    @Select("select user_id, type, status, account, add_time from account_recharge where user_id = #{userId} order by add_time limit 0,1")
    AccountRecharge selectTopByUserId(@Param("userId") String userId);
}
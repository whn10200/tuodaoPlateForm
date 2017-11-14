package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.UserDepositInfoHistory;
import com.tuodao.bp.useraccount.persistence.model.basic.UserDepositInfoHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDepositInfoHistoryMapper {
    int countByExample(UserDepositInfoHistoryExample example);

    int deleteByExample(UserDepositInfoHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDepositInfoHistory record);

    int insertSelective(UserDepositInfoHistory record);

    List<UserDepositInfoHistory> selectByExample(UserDepositInfoHistoryExample example);

    UserDepositInfoHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDepositInfoHistory record, @Param("example") UserDepositInfoHistoryExample example);

    int updateByExample(@Param("record") UserDepositInfoHistory record, @Param("example") UserDepositInfoHistoryExample example);

    int updateByPrimaryKeySelective(UserDepositInfoHistory record);

    int updateByPrimaryKey(UserDepositInfoHistory record);
}
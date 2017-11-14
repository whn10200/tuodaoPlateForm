package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.UserDepositResult;
import com.tuodao.bp.useraccount.persistence.model.basic.UserDepositResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDepositResultMapper {
    int countByExample(UserDepositResultExample example);

    int deleteByExample(UserDepositResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDepositResult record);

    int insertSelective(UserDepositResult record);

    List<UserDepositResult> selectByExample(UserDepositResultExample example);

    UserDepositResult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserDepositResult record, @Param("example") UserDepositResultExample example);

    int updateByExample(@Param("record") UserDepositResult record, @Param("example") UserDepositResultExample example);

    int updateByPrimaryKeySelective(UserDepositResult record);

    int updateByPrimaryKey(UserDepositResult record);
}
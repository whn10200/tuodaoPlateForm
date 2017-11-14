package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.traningcenter.db.model.basic.AccountStatus;
import com.tuodao.bp.traningcenter.db.model.basic.AccountStatusExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountStatusMapper {
    int countByExample(AccountStatusExample example);

    int deleteByExample(AccountStatusExample example);

    int deleteByPrimaryKey(Integer productId);

    int insert(AccountStatus record);

    int insertSelective(AccountStatus record);

    List<AccountStatus> selectByExample(AccountStatusExample example);

    AccountStatus selectByPrimaryKey(Integer productId);

    int updateByExampleSelective(@Param("record") AccountStatus record, @Param("example") AccountStatusExample example);

    int updateByExample(@Param("record") AccountStatus record, @Param("example") AccountStatusExample example);

    int updateByPrimaryKeySelective(AccountStatus record);

    int updateByPrimaryKey(AccountStatus record);
}
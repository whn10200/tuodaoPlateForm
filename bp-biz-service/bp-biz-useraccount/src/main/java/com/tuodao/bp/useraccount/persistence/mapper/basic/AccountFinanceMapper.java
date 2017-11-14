package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.AccountFinance;
import com.tuodao.bp.useraccount.persistence.model.basic.AccountFinanceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountFinanceMapper {
    int countByExample(AccountFinanceExample example);

    int deleteByExample(AccountFinanceExample example);

    int deleteByPrimaryKey(String userId);

    int insert(AccountFinance record);

    int insertSelective(AccountFinance record);

    List<AccountFinance> selectByExample(AccountFinanceExample example);

    AccountFinance selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") AccountFinance record, @Param("example") AccountFinanceExample example);

    int updateByExample(@Param("record") AccountFinance record, @Param("example") AccountFinanceExample example);

    int updateByPrimaryKeySelective(AccountFinance record);

    int updateByPrimaryKey(AccountFinance record);
}
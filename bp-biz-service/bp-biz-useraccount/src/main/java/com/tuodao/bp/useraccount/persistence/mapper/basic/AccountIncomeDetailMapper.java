package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.AccountIncomeDetail;
import com.tuodao.bp.useraccount.persistence.model.basic.AccountIncomeDetailExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AccountIncomeDetailMapper {
    int countByExample(AccountIncomeDetailExample example);

    int deleteByExample(AccountIncomeDetailExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountIncomeDetail record);

    int insertSelective(AccountIncomeDetail record);

    List<AccountIncomeDetail> selectByExample(AccountIncomeDetailExample example);

    AccountIncomeDetail selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountIncomeDetail record, @Param("example") AccountIncomeDetailExample example);

    int updateByExample(@Param("record") AccountIncomeDetail record, @Param("example") AccountIncomeDetailExample example);

    int updateByPrimaryKeySelective(AccountIncomeDetail record);

    int updateByPrimaryKey(AccountIncomeDetail record);
}
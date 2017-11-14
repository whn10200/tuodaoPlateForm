package com.tuodao.bp.traningcenter.db.mapper.basic;

import com.tuodao.bp.traningcenter.db.model.basic.AccountBankPayment;
import com.tuodao.bp.traningcenter.db.model.basic.AccountBankPaymentExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountBankPaymentMapper {
    long countByExample(AccountBankPaymentExample example);

    int deleteByExample(AccountBankPaymentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AccountBankPayment record);

    int insertSelective(AccountBankPayment record);

    List<AccountBankPayment> selectByExample(AccountBankPaymentExample example);

    AccountBankPayment selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AccountBankPayment record, @Param("example") AccountBankPaymentExample example);

    int updateByExample(@Param("record") AccountBankPayment record, @Param("example") AccountBankPaymentExample example);

    int updateByPrimaryKeySelective(AccountBankPayment record);

    int updateByPrimaryKey(AccountBankPayment record);
}
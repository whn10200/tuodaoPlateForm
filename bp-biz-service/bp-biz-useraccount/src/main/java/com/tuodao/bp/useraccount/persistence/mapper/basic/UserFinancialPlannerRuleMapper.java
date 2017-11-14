package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.UserFinancialPlannerRule;
import com.tuodao.bp.useraccount.persistence.model.basic.UserFinancialPlannerRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFinancialPlannerRuleMapper {
    int countByExample(UserFinancialPlannerRuleExample example);

    int deleteByExample(UserFinancialPlannerRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserFinancialPlannerRule record);

    int insertSelective(UserFinancialPlannerRule record);

    List<UserFinancialPlannerRule> selectByExample(UserFinancialPlannerRuleExample example);

    UserFinancialPlannerRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserFinancialPlannerRule record, @Param("example") UserFinancialPlannerRuleExample example);

    int updateByExample(@Param("record") UserFinancialPlannerRule record, @Param("example") UserFinancialPlannerRuleExample example);

    int updateByPrimaryKeySelective(UserFinancialPlannerRule record);

    int updateByPrimaryKey(UserFinancialPlannerRule record);
}
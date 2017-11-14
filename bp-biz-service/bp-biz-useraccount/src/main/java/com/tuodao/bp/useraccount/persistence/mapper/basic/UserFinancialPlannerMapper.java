package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.UserFinancialPlanner;
import com.tuodao.bp.useraccount.persistence.model.basic.UserFinancialPlannerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserFinancialPlannerMapper {
    int countByExample(UserFinancialPlannerExample example);

    int deleteByExample(UserFinancialPlannerExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserFinancialPlanner record);

    int insertSelective(UserFinancialPlanner record);

    List<UserFinancialPlanner> selectByExample(UserFinancialPlannerExample example);

    UserFinancialPlanner selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserFinancialPlanner record, @Param("example") UserFinancialPlannerExample example);

    int updateByExample(@Param("record") UserFinancialPlanner record, @Param("example") UserFinancialPlannerExample example);

    int updateByPrimaryKeySelective(UserFinancialPlanner record);

    int updateByPrimaryKey(UserFinancialPlanner record);
}
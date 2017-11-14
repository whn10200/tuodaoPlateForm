package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpInviteRule;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpInviteRuleMapper {
    int countByExample(OpInviteRuleExample example);

    int deleteByExample(OpInviteRuleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpInviteRule record);

    int insertSelective(OpInviteRule record);

    List<OpInviteRule> selectByExample(OpInviteRuleExample example);

    OpInviteRule selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpInviteRule record, @Param("example") OpInviteRuleExample example);

    int updateByExample(@Param("record") OpInviteRule record, @Param("example") OpInviteRuleExample example);

    int updateByPrimaryKeySelective(OpInviteRule record);

    int updateByPrimaryKey(OpInviteRule record);
}
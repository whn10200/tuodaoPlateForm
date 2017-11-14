package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpInviteRool;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRoolExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpInviteRoolMapper {
    int countByExample(OpInviteRoolExample example);

    int deleteByExample(OpInviteRoolExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpInviteRool record);

    int insertSelective(OpInviteRool record);

    List<OpInviteRool> selectByExample(OpInviteRoolExample example);

    OpInviteRool selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpInviteRool record, @Param("example") OpInviteRoolExample example);

    int updateByExample(@Param("record") OpInviteRool record, @Param("example") OpInviteRoolExample example);

    int updateByPrimaryKeySelective(OpInviteRool record);

    int updateByPrimaryKey(OpInviteRool record);
}
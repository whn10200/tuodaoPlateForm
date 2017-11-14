package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpUserTaskRelation;
import com.tuodao.bp.operation.persistence.model.basic.OpUserTaskRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpUserTaskRelationMapper {
    int countByExample(OpUserTaskRelationExample example);

    int deleteByExample(OpUserTaskRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpUserTaskRelation record);

    int insertSelective(OpUserTaskRelation record);

    List<OpUserTaskRelation> selectByExample(OpUserTaskRelationExample example);

    OpUserTaskRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpUserTaskRelation record, @Param("example") OpUserTaskRelationExample example);

    int updateByExample(@Param("record") OpUserTaskRelation record, @Param("example") OpUserTaskRelationExample example);

    int updateByPrimaryKeySelective(OpUserTaskRelation record);

    int updateByPrimaryKey(OpUserTaskRelation record);
}
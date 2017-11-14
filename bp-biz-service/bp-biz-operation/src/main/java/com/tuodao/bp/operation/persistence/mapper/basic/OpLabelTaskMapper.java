package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpLabelTask;
import com.tuodao.bp.operation.persistence.model.basic.OpLabelTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpLabelTaskMapper {
    int countByExample(OpLabelTaskExample example);

    int deleteByExample(OpLabelTaskExample example);

    int deleteByPrimaryKey(Integer taskId);

    int insert(OpLabelTask record);

    int insertSelective(OpLabelTask record);

    List<OpLabelTask> selectByExample(OpLabelTaskExample example);

    OpLabelTask selectByPrimaryKey(Integer taskId);

    int updateByExampleSelective(@Param("record") OpLabelTask record, @Param("example") OpLabelTaskExample example);

    int updateByExample(@Param("record") OpLabelTask record, @Param("example") OpLabelTaskExample example);

    int updateByPrimaryKeySelective(OpLabelTask record);

    int updateByPrimaryKey(OpLabelTask record);
}
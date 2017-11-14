package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpScoreTask;
import com.tuodao.bp.operation.persistence.model.basic.OpScoreTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpScoreTaskMapper {
    int countByExample(OpScoreTaskExample example);

    int deleteByExample(OpScoreTaskExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpScoreTask record);

    int insertSelective(OpScoreTask record);

    List<OpScoreTask> selectByExample(OpScoreTaskExample example);

    OpScoreTask selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpScoreTask record, @Param("example") OpScoreTaskExample example);

    int updateByExample(@Param("record") OpScoreTask record, @Param("example") OpScoreTaskExample example);

    int updateByPrimaryKeySelective(OpScoreTask record);

    int updateByPrimaryKey(OpScoreTask record);
}
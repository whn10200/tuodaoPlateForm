package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpUserScore;
import com.tuodao.bp.operation.persistence.model.basic.OpUserScoreExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpUserScoreMapper {
    int countByExample(OpUserScoreExample example);

    int deleteByExample(OpUserScoreExample example);

    int deleteByPrimaryKey(String userId);

    int insert(OpUserScore record);

    int insertSelective(OpUserScore record);

    List<OpUserScore> selectByExample(OpUserScoreExample example);

    OpUserScore selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") OpUserScore record, @Param("example") OpUserScoreExample example);

    int updateByExample(@Param("record") OpUserScore record, @Param("example") OpUserScoreExample example);

    int updateByPrimaryKeySelective(OpUserScore record);

    int updateByPrimaryKey(OpUserScore record);
}
package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpScoreMall;
import com.tuodao.bp.operation.persistence.model.basic.OpScoreMallExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpScoreMallMapper {
    int countByExample(OpScoreMallExample example);

    int deleteByExample(OpScoreMallExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpScoreMall record);

    int insertSelective(OpScoreMall record);

    List<OpScoreMall> selectByExample(OpScoreMallExample example);

    OpScoreMall selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpScoreMall record, @Param("example") OpScoreMallExample example);

    int updateByExample(@Param("record") OpScoreMall record, @Param("example") OpScoreMallExample example);

    int updateByPrimaryKeySelective(OpScoreMall record);

    int updateByPrimaryKey(OpScoreMall record);
}
package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpProblems;
import com.tuodao.bp.operation.persistence.model.basic.OpProblemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpProblemsMapper {
    int countByExample(OpProblemsExample example);

    int deleteByExample(OpProblemsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpProblems record);

    int insertSelective(OpProblems record);

    List<OpProblems> selectByExample(OpProblemsExample example);

    OpProblems selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpProblems record, @Param("example") OpProblemsExample example);

    int updateByExample(@Param("record") OpProblems record, @Param("example") OpProblemsExample example);

    int updateByPrimaryKeySelective(OpProblems record);

    int updateByPrimaryKey(OpProblems record);
}
package com.tuodao.bp.task.server.dao.mapper;

import com.tuodao.bp.task.server.dao.model.TaskResult;
import com.tuodao.bp.task.server.dao.model.TaskResultExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TaskResultMapper {
    int countByExample(TaskResultExample example);

    int deleteByExample(TaskResultExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TaskResult record);

    int insertSelective(TaskResult record);

    List<TaskResult> selectByExample(TaskResultExample example);

    TaskResult selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TaskResult record, @Param("example") TaskResultExample example);

    int updateByExample(@Param("record") TaskResult record, @Param("example") TaskResultExample example);

    int updateByPrimaryKeySelective(TaskResult record);

    int updateByPrimaryKey(TaskResult record);
}
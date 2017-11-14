package com.tuodao.bp.common.persistence.mapper.basic;

import com.tuodao.bp.common.persistence.model.basic.PushLog;
import com.tuodao.bp.common.persistence.model.basic.PushLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PushLogMapper {
    int countByExample(PushLogExample example);

    int deleteByExample(PushLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PushLog record);

    int insertSelective(PushLog record);

    List<PushLog> selectByExample(PushLogExample example);

    PushLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") PushLog record, @Param("example") PushLogExample example);

    int updateByExample(@Param("record") PushLog record, @Param("example") PushLogExample example);

    int updateByPrimaryKeySelective(PushLog record);

    int updateByPrimaryKey(PushLog record);
}
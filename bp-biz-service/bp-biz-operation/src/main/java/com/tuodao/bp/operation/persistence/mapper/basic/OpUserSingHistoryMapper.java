package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpUserSingHistory;
import com.tuodao.bp.operation.persistence.model.basic.OpUserSingHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpUserSingHistoryMapper {
    int countByExample(OpUserSingHistoryExample example);

    int deleteByExample(OpUserSingHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpUserSingHistory record);

    int insertSelective(OpUserSingHistory record);

    List<OpUserSingHistory> selectByExample(OpUserSingHistoryExample example);

    OpUserSingHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpUserSingHistory record, @Param("example") OpUserSingHistoryExample example);

    int updateByExample(@Param("record") OpUserSingHistory record, @Param("example") OpUserSingHistoryExample example);

    int updateByPrimaryKeySelective(OpUserSingHistory record);

    int updateByPrimaryKey(OpUserSingHistory record);
}
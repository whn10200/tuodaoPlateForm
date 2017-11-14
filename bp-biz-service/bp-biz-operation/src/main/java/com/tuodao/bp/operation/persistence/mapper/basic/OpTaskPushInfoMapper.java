package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpTaskPushInfo;
import com.tuodao.bp.operation.persistence.model.basic.OpTaskPushInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpTaskPushInfoMapper {
    int countByExample(OpTaskPushInfoExample example);

    int deleteByExample(OpTaskPushInfoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpTaskPushInfo record);

    int insertSelective(OpTaskPushInfo record);

    List<OpTaskPushInfo> selectByExample(OpTaskPushInfoExample example);

    OpTaskPushInfo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpTaskPushInfo record, @Param("example") OpTaskPushInfoExample example);

    int updateByExample(@Param("record") OpTaskPushInfo record, @Param("example") OpTaskPushInfoExample example);

    int updateByPrimaryKeySelective(OpTaskPushInfo record);

    int updateByPrimaryKey(OpTaskPushInfo record);
}
package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationData;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationDataExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpUserOperationDataMapper {
    int countByExample(OpUserOperationDataExample example);

    int deleteByExample(OpUserOperationDataExample example);

    int deleteByPrimaryKey(String userId);

    int insert(OpUserOperationData record);

    int insertSelective(OpUserOperationData record);

    List<OpUserOperationData> selectByExample(OpUserOperationDataExample example);

    OpUserOperationData selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") OpUserOperationData record, @Param("example") OpUserOperationDataExample example);

    int updateByExample(@Param("record") OpUserOperationData record, @Param("example") OpUserOperationDataExample example);

    int updateByPrimaryKeySelective(OpUserOperationData record);

    int updateByPrimaryKey(OpUserOperationData record);
}
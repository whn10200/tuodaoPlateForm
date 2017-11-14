package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpCooperation;
import com.tuodao.bp.operation.persistence.model.basic.OpCooperationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpCooperationMapper {
    int countByExample(OpCooperationExample example);

    int deleteByExample(OpCooperationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpCooperation record);

    int insertSelective(OpCooperation record);

    List<OpCooperation> selectByExample(OpCooperationExample example);

    OpCooperation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpCooperation record, @Param("example") OpCooperationExample example);

    int updateByExample(@Param("record") OpCooperation record, @Param("example") OpCooperationExample example);

    int updateByPrimaryKeySelective(OpCooperation record);

    int updateByPrimaryKey(OpCooperation record);
}
package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpUserLabel;
import com.tuodao.bp.operation.persistence.model.basic.OpUserLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpUserLabelMapper {
    int countByExample(OpUserLabelExample example);

    int deleteByExample(OpUserLabelExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpUserLabel record);

    int insertSelective(OpUserLabel record);

    List<OpUserLabel> selectByExample(OpUserLabelExample example);

    OpUserLabel selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpUserLabel record, @Param("example") OpUserLabelExample example);

    int updateByExample(@Param("record") OpUserLabel record, @Param("example") OpUserLabelExample example);

    int updateByPrimaryKeySelective(OpUserLabel record);

    int updateByPrimaryKey(OpUserLabel record);
}
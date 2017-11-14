package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpWelfareActivity;
import com.tuodao.bp.operation.persistence.model.basic.OpWelfareActivityExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpWelfareActivityMapper {
    int countByExample(OpWelfareActivityExample example);

    int deleteByExample(OpWelfareActivityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpWelfareActivity record);

    int insertSelective(OpWelfareActivity record);

    List<OpWelfareActivity> selectByExample(OpWelfareActivityExample example);

    OpWelfareActivity selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpWelfareActivity record, @Param("example") OpWelfareActivityExample example);

    int updateByExample(@Param("record") OpWelfareActivity record, @Param("example") OpWelfareActivityExample example);

    int updateByPrimaryKeySelective(OpWelfareActivity record);

    int updateByPrimaryKey(OpWelfareActivity record);
}
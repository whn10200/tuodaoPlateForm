package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpScoreDrawPrize;
import com.tuodao.bp.operation.persistence.model.basic.OpScoreDrawPrizeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpScoreDrawPrizeMapper {
    int countByExample(OpScoreDrawPrizeExample example);

    int deleteByExample(OpScoreDrawPrizeExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpScoreDrawPrize record);

    int insertSelective(OpScoreDrawPrize record);

    List<OpScoreDrawPrize> selectByExample(OpScoreDrawPrizeExample example);

    OpScoreDrawPrize selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpScoreDrawPrize record, @Param("example") OpScoreDrawPrizeExample example);

    int updateByExample(@Param("record") OpScoreDrawPrize record, @Param("example") OpScoreDrawPrizeExample example);

    int updateByPrimaryKeySelective(OpScoreDrawPrize record);

    int updateByPrimaryKey(OpScoreDrawPrize record);
}
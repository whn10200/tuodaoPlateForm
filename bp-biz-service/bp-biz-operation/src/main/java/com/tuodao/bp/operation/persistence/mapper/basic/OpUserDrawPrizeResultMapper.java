package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpUserDrawPrizeResult;
import com.tuodao.bp.operation.persistence.model.basic.OpUserDrawPrizeResultExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpUserDrawPrizeResultMapper {
    int countByExample(OpUserDrawPrizeResultExample example);

    int deleteByExample(OpUserDrawPrizeResultExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpUserDrawPrizeResult record);

    int insertSelective(OpUserDrawPrizeResult record);

    List<OpUserDrawPrizeResult> selectByExample(OpUserDrawPrizeResultExample example);

    OpUserDrawPrizeResult selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpUserDrawPrizeResult record, @Param("example") OpUserDrawPrizeResultExample example);

    int updateByExample(@Param("record") OpUserDrawPrizeResult record, @Param("example") OpUserDrawPrizeResultExample example);

    int updateByPrimaryKeySelective(OpUserDrawPrizeResult record);

    int updateByPrimaryKey(OpUserDrawPrizeResult record);
}
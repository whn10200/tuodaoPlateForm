package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpUserScoreDetail;
import com.tuodao.bp.operation.persistence.model.basic.OpUserScoreDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpUserScoreDetailMapper {
    int countByExample(OpUserScoreDetailExample example);

    int deleteByExample(OpUserScoreDetailExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OpUserScoreDetail record);

    int insertSelective(OpUserScoreDetail record);

    List<OpUserScoreDetail> selectByExample(OpUserScoreDetailExample example);

    OpUserScoreDetail selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OpUserScoreDetail record, @Param("example") OpUserScoreDetailExample example);

    int updateByExample(@Param("record") OpUserScoreDetail record, @Param("example") OpUserScoreDetailExample example);

    int updateByPrimaryKeySelective(OpUserScoreDetail record);

    int updateByPrimaryKey(OpUserScoreDetail record);
}
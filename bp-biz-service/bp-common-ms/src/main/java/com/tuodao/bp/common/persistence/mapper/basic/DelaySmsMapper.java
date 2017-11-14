package com.tuodao.bp.common.persistence.mapper.basic;

import com.tuodao.bp.common.persistence.model.basic.DelaySms;
import com.tuodao.bp.common.persistence.model.basic.DelaySmsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DelaySmsMapper {
    int countByExample(DelaySmsExample example);

    int deleteByExample(DelaySmsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DelaySms record);

    int insertSelective(DelaySms record);

    List<DelaySms> selectByExample(DelaySmsExample example);

    DelaySms selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DelaySms record, @Param("example") DelaySmsExample example);

    int updateByExample(@Param("record") DelaySms record, @Param("example") DelaySmsExample example);

    int updateByPrimaryKeySelective(DelaySms record);

    int updateByPrimaryKey(DelaySms record);
}
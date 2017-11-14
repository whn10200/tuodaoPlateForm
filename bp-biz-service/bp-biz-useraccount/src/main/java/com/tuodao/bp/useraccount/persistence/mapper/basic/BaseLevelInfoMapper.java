package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.BaseLevelInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.BaseLevelInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BaseLevelInfoMapper {
    int countByExample(BaseLevelInfoExample example);

    int deleteByExample(BaseLevelInfoExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(BaseLevelInfo record);

    int insertSelective(BaseLevelInfo record);

    List<BaseLevelInfo> selectByExample(BaseLevelInfoExample example);

    BaseLevelInfo selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") BaseLevelInfo record, @Param("example") BaseLevelInfoExample example);

    int updateByExample(@Param("record") BaseLevelInfo record, @Param("example") BaseLevelInfoExample example);

    int updateByPrimaryKeySelective(BaseLevelInfo record);

    int updateByPrimaryKey(BaseLevelInfo record);
}
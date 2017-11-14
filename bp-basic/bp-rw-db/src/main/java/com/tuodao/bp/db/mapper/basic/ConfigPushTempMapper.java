package com.tuodao.bp.db.mapper.basic;

import com.tuodao.bp.db.model.basic.ConfigPushTemp;
import com.tuodao.bp.db.model.basic.ConfigPushTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigPushTempMapper {
    int countByExample(ConfigPushTempExample example);

    int deleteByExample(ConfigPushTempExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigPushTemp record);

    int insertSelective(ConfigPushTemp record);

    List<ConfigPushTemp> selectByExample(ConfigPushTempExample example);

    ConfigPushTemp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigPushTemp record, @Param("example") ConfigPushTempExample example);

    int updateByExample(@Param("record") ConfigPushTemp record, @Param("example") ConfigPushTempExample example);

    int updateByPrimaryKeySelective(ConfigPushTemp record);

    int updateByPrimaryKey(ConfigPushTemp record);
}
package com.tuodao.bp.db.mapper.basic;

import com.tuodao.bp.db.model.basic.ConfigSmsTemp;
import com.tuodao.bp.db.model.basic.ConfigSmsTempExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ConfigSmsTempMapper {
    int countByExample(ConfigSmsTempExample example);

    int deleteByExample(ConfigSmsTempExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigSmsTemp record);

    int insertSelective(ConfigSmsTemp record);

    List<ConfigSmsTemp> selectByExample(ConfigSmsTempExample example);

    ConfigSmsTemp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigSmsTemp record, @Param("example") ConfigSmsTempExample example);

    int updateByExample(@Param("record") ConfigSmsTemp record, @Param("example") ConfigSmsTempExample example);

    int updateByPrimaryKeySelective(ConfigSmsTemp record);

    int updateByPrimaryKey(ConfigSmsTemp record);
}
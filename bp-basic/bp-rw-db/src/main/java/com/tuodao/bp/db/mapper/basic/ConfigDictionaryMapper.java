package com.tuodao.bp.db.mapper.basic;

import com.tuodao.bp.db.model.basic.ConfigDictionary;
import com.tuodao.bp.db.model.basic.ConfigDictionaryExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ConfigDictionaryMapper {
    int countByExample(ConfigDictionaryExample example);

    int deleteByExample(ConfigDictionaryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConfigDictionary record);

    int insertSelective(ConfigDictionary record);

    List<ConfigDictionary> selectByExample(ConfigDictionaryExample example);

    ConfigDictionary selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConfigDictionary record, @Param("example") ConfigDictionaryExample example);

    int updateByExample(@Param("record") ConfigDictionary record, @Param("example") ConfigDictionaryExample example);

    int updateByPrimaryKeySelective(ConfigDictionary record);

    int updateByPrimaryKey(ConfigDictionary record);
}
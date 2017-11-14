package com.tuodao.bp.task.server.dao.mapper;

import com.tuodao.bp.task.server.dao.model.BusinessServer;
import com.tuodao.bp.task.server.dao.model.BusinessServerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessServerMapper {
    int countByExample(BusinessServerExample example);

    int deleteByExample(BusinessServerExample example);

    int deleteByPrimaryKey(Integer serverid);

    int insert(BusinessServer record);

    int insertSelective(BusinessServer record);

    List<BusinessServer> selectByExample(BusinessServerExample example);

    BusinessServer selectByPrimaryKey(Integer serverid);

    int updateByExampleSelective(@Param("record") BusinessServer record, @Param("example") BusinessServerExample example);

    int updateByExample(@Param("record") BusinessServer record, @Param("example") BusinessServerExample example);

    int updateByPrimaryKeySelective(BusinessServer record);

    int updateByPrimaryKey(BusinessServer record);
}
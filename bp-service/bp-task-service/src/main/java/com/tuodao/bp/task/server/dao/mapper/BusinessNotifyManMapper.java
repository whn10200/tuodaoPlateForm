package com.tuodao.bp.task.server.dao.mapper;

import com.tuodao.bp.task.server.dao.model.BusinessNotifyMan;
import com.tuodao.bp.task.server.dao.model.BusinessNotifyManExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BusinessNotifyManMapper {
    int countByExample(BusinessNotifyManExample example);

    int deleteByExample(BusinessNotifyManExample example);

    int deleteByPrimaryKey(Integer notifymanid);

    int insert(BusinessNotifyMan record);

    int insertSelective(BusinessNotifyMan record);

    List<BusinessNotifyMan> selectByExample(BusinessNotifyManExample example);

    BusinessNotifyMan selectByPrimaryKey(Integer notifymanid);

    int updateByExampleSelective(@Param("record") BusinessNotifyMan record, @Param("example") BusinessNotifyManExample example);

    int updateByExample(@Param("record") BusinessNotifyMan record, @Param("example") BusinessNotifyManExample example);

    int updateByPrimaryKeySelective(BusinessNotifyMan record);

    int updateByPrimaryKey(BusinessNotifyMan record);
}
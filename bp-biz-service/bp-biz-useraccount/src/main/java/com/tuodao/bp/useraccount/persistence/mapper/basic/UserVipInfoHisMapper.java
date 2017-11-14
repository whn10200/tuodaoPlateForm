package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.UserVipInfoHis;
import com.tuodao.bp.useraccount.persistence.model.basic.UserVipInfoHisExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVipInfoHisMapper {
    int countByExample(UserVipInfoHisExample example);

    int deleteByExample(UserVipInfoHisExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UserVipInfoHis record);

    int insertSelective(UserVipInfoHis record);

    List<UserVipInfoHis> selectByExample(UserVipInfoHisExample example);

    UserVipInfoHis selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UserVipInfoHis record, @Param("example") UserVipInfoHisExample example);

    int updateByExample(@Param("record") UserVipInfoHis record, @Param("example") UserVipInfoHisExample example);

    int updateByPrimaryKeySelective(UserVipInfoHis record);

    int updateByPrimaryKey(UserVipInfoHis record);
}
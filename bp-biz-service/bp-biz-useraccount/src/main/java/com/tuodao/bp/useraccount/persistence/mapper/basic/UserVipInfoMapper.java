package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.UserVipInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.UserVipInfoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserVipInfoMapper {
    int countByExample(UserVipInfoExample example);

    int deleteByExample(UserVipInfoExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserVipInfo record);

    int insertSelective(UserVipInfo record);

    List<UserVipInfo> selectByExample(UserVipInfoExample example);

    UserVipInfo selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserVipInfo record, @Param("example") UserVipInfoExample example);

    int updateByExample(@Param("record") UserVipInfo record, @Param("example") UserVipInfoExample example);

    int updateByPrimaryKeySelective(UserVipInfo record);

    int updateByPrimaryKey(UserVipInfo record);
}
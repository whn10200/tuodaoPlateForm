package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.UserInviteInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInviteInfoExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserInviteInfoMapper {
    int countByExample(UserInviteInfoExample example);

    int deleteByExample(UserInviteInfoExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UserInviteInfo record);

    int insertSelective(UserInviteInfo record);

    List<UserInviteInfo> selectByExample(UserInviteInfoExample example);

    UserInviteInfo selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UserInviteInfo record, @Param("example") UserInviteInfoExample example);

    int updateByExample(@Param("record") UserInviteInfo record, @Param("example") UserInviteInfoExample example);

    int updateByPrimaryKeySelective(UserInviteInfo record);

    int updateByPrimaryKey(UserInviteInfo record);

    int deleteAll();
}
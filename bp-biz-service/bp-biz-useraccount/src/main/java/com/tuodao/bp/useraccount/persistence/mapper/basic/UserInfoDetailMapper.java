package com.tuodao.bp.useraccount.persistence.mapper.basic;

import com.tuodao.bp.useraccount.persistence.model.basic.UserInfoDetail;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfoDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoDetailMapper {
    int countByExample(UserInfoDetailExample example);

    int deleteByExample(UserInfoDetailExample example);

    int deleteByPrimaryKey(String userId);

    int insert(UserInfoDetail record);

    int insertSelective(UserInfoDetail record);

    List<UserInfoDetail> selectByExample(UserInfoDetailExample example);

    UserInfoDetail selectByPrimaryKey(String userId);

    int updateByExampleSelective(@Param("record") UserInfoDetail record, @Param("example") UserInfoDetailExample example);

    int updateByExample(@Param("record") UserInfoDetail record, @Param("example") UserInfoDetailExample example);

    int updateByPrimaryKeySelective(UserInfoDetail record);

    int updateByPrimaryKey(UserInfoDetail record);
}
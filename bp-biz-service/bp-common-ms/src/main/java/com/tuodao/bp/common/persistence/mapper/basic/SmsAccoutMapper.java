package com.tuodao.bp.common.persistence.mapper.basic;

import com.tuodao.bp.common.persistence.model.basic.SmsAccout;
import com.tuodao.bp.common.persistence.model.basic.SmsAccoutExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SmsAccoutMapper {
    int countByExample(SmsAccoutExample example);

    int deleteByExample(SmsAccoutExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(SmsAccout record);

    int insertSelective(SmsAccout record);

    List<SmsAccout> selectByExample(SmsAccoutExample example);

    SmsAccout selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") SmsAccout record, @Param("example") SmsAccoutExample example);

    int updateByExample(@Param("record") SmsAccout record, @Param("example") SmsAccoutExample example);

    int updateByPrimaryKeySelective(SmsAccout record);

    int updateByPrimaryKey(SmsAccout record);
}
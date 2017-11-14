package com.tuodao.bp.common.persistence.mapper.basic;

import com.tuodao.bp.common.persistence.model.basic.EmailLog;
import com.tuodao.bp.common.persistence.model.basic.EmailLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EmailLogMapper {
    int countByExample(EmailLogExample example);

    int deleteByExample(EmailLogExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(EmailLog record);

    int insertSelective(EmailLog record);

    List<EmailLog> selectByExampleWithBLOBs(EmailLogExample example);

    List<EmailLog> selectByExample(EmailLogExample example);

    EmailLog selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") EmailLog record, @Param("example") EmailLogExample example);

    int updateByExampleWithBLOBs(@Param("record") EmailLog record, @Param("example") EmailLogExample example);

    int updateByExample(@Param("record") EmailLog record, @Param("example") EmailLogExample example);

    int updateByPrimaryKeySelective(EmailLog record);

    int updateByPrimaryKeyWithBLOBs(EmailLog record);

    int updateByPrimaryKey(EmailLog record);
}
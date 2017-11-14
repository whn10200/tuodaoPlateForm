package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpInviteAwardRecord;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteAwardRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpInviteAwardRecordMapper {
    int countByExample(OpInviteAwardRecordExample example);

    int deleteByExample(OpInviteAwardRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpInviteAwardRecord record);

    int insertSelective(OpInviteAwardRecord record);

    List<OpInviteAwardRecord> selectByExample(OpInviteAwardRecordExample example);

    OpInviteAwardRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpInviteAwardRecord record, @Param("example") OpInviteAwardRecordExample example);

    int updateByExample(@Param("record") OpInviteAwardRecord record, @Param("example") OpInviteAwardRecordExample example);

    int updateByPrimaryKeySelective(OpInviteAwardRecord record);

    int updateByPrimaryKey(OpInviteAwardRecord record);
}
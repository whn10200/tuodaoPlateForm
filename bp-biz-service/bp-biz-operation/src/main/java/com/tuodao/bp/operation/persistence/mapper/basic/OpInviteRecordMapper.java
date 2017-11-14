package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpInviteRecord;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OpInviteRecordMapper {
    int countByExample(OpInviteRecordExample example);

    int deleteByExample(OpInviteRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(OpInviteRecord record);

    int insertSelective(OpInviteRecord record);

    List<OpInviteRecord> selectByExample(OpInviteRecordExample example);

    OpInviteRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") OpInviteRecord record, @Param("example") OpInviteRecordExample example);

    int updateByExample(@Param("record") OpInviteRecord record, @Param("example") OpInviteRecordExample example);

    int updateByPrimaryKeySelective(OpInviteRecord record);

    int updateByPrimaryKey(OpInviteRecord record);
}
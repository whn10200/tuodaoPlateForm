package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.model.business.operation.input.OpContentInput;
import com.tuodao.bp.model.business.operation.input.OpContentTitleInput;
import com.tuodao.bp.operation.persistence.model.basic.OpContentManager;
import com.tuodao.bp.operation.persistence.model.basic.OpContentManagerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpContentManagerMapper {
    int countByExample(OpContentManagerExample example);

    int deleteByExample(OpContentManagerExample example);

    int deleteByPrimaryKey(Integer contentId);

    int insert(OpContentManager record);

    int insertSelective(OpContentManager record);

    List<OpContentManager> selectByExample(OpContentManagerExample example);

    OpContentManager selectByPrimaryKey(Integer contentId);

    int updateByExampleSelective(@Param("record") OpContentManager record, @Param("example") OpContentManagerExample example);

    int updateByExample(@Param("record") OpContentManager record, @Param("example") OpContentManagerExample example);

    int updateByPrimaryKeySelective(OpContentManager record);

    int updateByPrimaryKey(OpContentManager record);

    List<OpContentManager> selectByContentRemark(OpContentTitleInput record);

    List<OpContentManager> selectByContentId(OpContentInput record);
}
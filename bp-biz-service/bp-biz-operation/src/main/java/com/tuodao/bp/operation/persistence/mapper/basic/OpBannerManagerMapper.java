package com.tuodao.bp.operation.persistence.mapper.basic;

import com.tuodao.bp.operation.persistence.model.basic.OpBannerManager;
import com.tuodao.bp.operation.persistence.model.basic.OpBannerManagerExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OpBannerManagerMapper {
    int countByExample(OpBannerManagerExample example);

    int deleteByExample(OpBannerManagerExample example);

    int deleteByPrimaryKey(Integer bannerId);

    int insert(OpBannerManager record);

    int insertSelective(OpBannerManager record);

    List<OpBannerManager> selectByExample(OpBannerManagerExample example);

    OpBannerManager selectByPrimaryKey(Integer bannerId);

    int updateByExampleSelective(@Param("record") OpBannerManager record, @Param("example") OpBannerManagerExample example);

    int updateByExample(@Param("record") OpBannerManager record, @Param("example") OpBannerManagerExample example);

    int updateByPrimaryKeySelective(OpBannerManager record);

    int updateByPrimaryKey(OpBannerManager record);

    List<OpBannerManager> selectBannerByParams(OpBannerManager input);
}
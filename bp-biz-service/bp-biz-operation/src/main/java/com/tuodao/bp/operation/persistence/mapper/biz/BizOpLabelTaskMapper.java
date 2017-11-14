package com.tuodao.bp.operation.persistence.mapper.biz;

import com.tuodao.bp.model.business.operation.output.UserLabelTaskStatusOutput;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Description: 用户标签任务自定义mapper
 * User: zkai
 * Date: 2017/9/20
 * Time: 14:32
 * Copyright:拓道金服 Copyright (c) 2017
 */
public interface BizOpLabelTaskMapper {

    /**
     * 查看用户标签任务完成情况
     * @param
     * @return
     */
    List<UserLabelTaskStatusOutput> userLabelTaskFinishStatus(@Param("userId") String userId);
}

package com.tuodao.bp.api.facade.client.operation;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.model.business.operation.output.OpScoreTaskDetailOutput;
import com.tuodao.bp.model.business.operation.output.UserTaskListOutput;
import com.tuodao.bp.model.facade.operation.input.UserScoreDetailListInput;
import com.tuodao.bp.model.facade.operation.output.OpUserScoreDetailListOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Description: 运营中心用户任务相关 FEIGN
 * User: zkai
 * Date: 2017/9/21
 * Time: 19:26
 * Copyright:拓道金服 Copyright (c) 2017
 */
@FeignClient(value = "BIZ-OPERATION")
public interface OpScoreTaskClient {
    /**
     * 查找用户任务(新手任务,日常任务)
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "operation/scoreTask/findUserTask", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<UserTaskListOutput> findUserTask(UserTaskListInput input);

    /**
     * 根据用户编号,获取任务信息
     *
     * @param input
     */
    @RequestMapping(value = "operation/scoreTask/opScoreTaskDetail", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public OpScoreTaskDetailOutput opScoreTaskDetail(OpScoreTaskDetailInput input);

    /**
     * 根据用户编号,获取任务信息
     *
     * @param input
     */
    @RequestMapping(value = "operation/scoreTask/userScoreDetailList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<OpUserScoreDetailListOutput> userScoreDetailList(UserScoreDetailListInput input);
}

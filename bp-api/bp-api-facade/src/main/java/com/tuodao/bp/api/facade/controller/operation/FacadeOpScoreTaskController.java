package com.tuodao.bp.api.facade.controller.operation;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.OpScoreTaskClient;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.input.UserTaskListInput;
import com.tuodao.bp.model.business.operation.output.OpScoreTaskDetailOutput;
import com.tuodao.bp.model.business.operation.output.UserTaskListOutput;
import com.tuodao.bp.model.facade.operation.input.UserScoreDetailListInput;
import com.tuodao.bp.model.facade.operation.output.OpUserScoreDetailListOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description: 聚合-运营中心用户任务关系
 * User: zkai
 * Date: 2017/9/12
 * Time: 15:56
 * Copyright:拓道金服 Copyright (c) 2017
 */
@RequestMapping("/router/op")
@RestController
public class FacadeOpScoreTaskController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(FacadeOpScoreTaskController.class);

    @Autowired
    private OpScoreTaskClient opScoreTaskClient;

    /**
     * 查找用户任务(新手任务,日常任务)
     * @param input
     * @return
     */
    @RequestMapping(value="/findUserTask",method= RequestMethod.POST)
	@AccessToken(checkAccess=true)
    public RespResult<PageInfo<UserTaskListOutput>> findUserTask(UserTaskListInput input){
        logger.info("查找用户任务(新手任务,日常任务) input={}", JSON.toJSONString(input));
        PageInfo<UserTaskListOutput> userTaskListOutput = opScoreTaskClient.findUserTask(input);
        return RespResult.<PageInfo<UserTaskListOutput>>create().setContent(userTaskListOutput);
    }

    /**
     * 根据任务编号,获取任务信息
     * @param input
     * @return
     */
    @RequestMapping(value="/opScoreTaskDetail",method= RequestMethod.POST)
    @AccessToken(checkAccess=true)
    public RespResult<OpScoreTaskDetailOutput> opScoreTaskDetail(OpScoreTaskDetailInput input){
        logger.info("根据任务编号,获取任务信息 input={}", JSON.toJSONString(input));
        return RespResult.<OpScoreTaskDetailOutput>create().setContent(opScoreTaskClient.opScoreTaskDetail(input));
    }

    /**
     * 根据用户编号,获取用户积分明细信息
     * @param input
     * @return
     */
    @RequestMapping(value="/userScoreDetailList",method= RequestMethod.POST)
    @AccessToken(checkAccess=true)
    public RespResult<PageInfo<OpUserScoreDetailListOutput>> userScoreDetailList(UserScoreDetailListInput input){
        logger.info("根据用户编号,获取用户积分明细信息 input={}", JSON.toJSONString(input));
        PageInfo<OpUserScoreDetailListOutput> out = opScoreTaskClient.userScoreDetailList(input);
        return RespResult.<PageInfo<OpUserScoreDetailListOutput>>create().setContent(out);
    }
}

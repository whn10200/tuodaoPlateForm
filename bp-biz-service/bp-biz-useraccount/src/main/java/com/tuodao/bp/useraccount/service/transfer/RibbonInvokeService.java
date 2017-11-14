package com.tuodao.bp.useraccount.service.transfer;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.model.business.common.input.SmsInput;
import com.tuodao.bp.model.business.common.output.SmsOutput;
import com.tuodao.bp.model.business.operation.input.OpScoreTaskDetailInput;
import com.tuodao.bp.model.business.operation.output.OpScoreTaskDetailOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @description: Ribbon 服务调用
 * @author: mif
 * @date: 2017/9/22
 * @time: 10:59
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class RibbonInvokeService {

    @Resource
    private RestTemplate restTemplate;

    /**
     * 获取运营中心积分任务详情
     *
     * @param taskId 任务编号
     * @return 任务详情
     */
    public OpScoreTaskDetailOutput getOpScoreTaskDetail(Long taskId) {
        OpScoreTaskDetailInput input = new OpScoreTaskDetailInput();
        input.setTaskId(taskId);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(JSON.toJSONString(input), httpHeaders);
        return restTemplate.postForEntity("http：//BIZ-OPERATION/operation/scoreTask/opScoreTaskDetail", entity, OpScoreTaskDetailOutput.class).getBody();
    }


}

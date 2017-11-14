package com.tuodao.bp.operation.controller;

import com.tuodao.bp.model.business.operation.input.InverstmentInput;
import com.tuodao.bp.model.business.operation.input.InverstmentQueryInput;
import com.tuodao.bp.model.business.operation.output.InverstmentOutput;
import com.tuodao.bp.model.business.operation.output.InverstmentQueryOutput;
import com.tuodao.bp.operation.service.IInverstmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 抽奖controller
 * author hechuan
 * <p>
 * created on 2017/9/28
 * <p>
 * since V1.0.0
 */
@RequestMapping("/inverst")
@RestController
public class InverstmentController {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(InverstmentController.class);

    @Autowired
    private IInverstmentService inverstmentService;

    /**
     * 查询抽奖列表
     * @param input
     * @return
     */
    @RequestMapping(value = "/getInverstQueryList",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public List<InverstmentQueryOutput> getInverstQueryList(InverstmentQueryInput input){
        logger.info("查询抽奖列表 input :[{}]",input);
        return inverstmentService.getInverstQueryList(input);
    }

    /**
     * 抽奖-并返回抽奖结果
     * @param input
     * @return
     */
    @RequestMapping(value = "/getInverstResult",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public InverstmentOutput getInverstResult(InverstmentInput input){
        logger.info("开始抽奖[抽奖计算，结果保存并返回] input :[{}]",input);
        return inverstmentService.getInverstResult(input);
    }
}

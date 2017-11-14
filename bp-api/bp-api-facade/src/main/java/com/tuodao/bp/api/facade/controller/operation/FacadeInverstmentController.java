package com.tuodao.bp.api.facade.controller.operation;

import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.InverstmentClient;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.input.InverstmentInput;
import com.tuodao.bp.model.business.operation.input.InverstmentQueryInput;
import com.tuodao.bp.model.business.operation.output.InverstmentOutput;
import com.tuodao.bp.model.business.operation.output.InverstmentQueryOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 聚合-运营中心-用户积分抽奖
 * author hechuan
 * <p>
 * creed on 2017/10/9
 * <p>
 * since V1.0.0
 */
@RestController
@RequestMapping("/router/op")
public class FacadeInverstmentController {
    // 日志
    private static final Logger logger = LoggerFactory.getLogger(FacadeInverstmentController.class);

    @Autowired
    private InverstmentClient inverstmentClient;


    /**
     * 查询抽奖列表
     * @param input
     * @return 抽奖列表
     */
    @RequestMapping(value = "/getInverstQueryList",method = RequestMethod.POST)
    @AccessToken(checkAccess = false,access= {AccessType.APP,AccessType.PC})
    public RespResult<List<InverstmentQueryOutput>> getInverstQueryList(InverstmentQueryInput input){
        logger.info("聚合-运营中心-查询抽奖列表-input:[{}]",input);
        List<InverstmentQueryOutput> inverstQueryList = inverstmentClient.getInverstQueryList(input);

        logger.info("分发到业务inverstmentClient成功，抽奖列表：[{}]",inverstQueryList);
        return RespResult.<List<InverstmentQueryOutput>>create().setContent(inverstQueryList);
    }


    /**
     * 抽奖
     * @param input
     * @return 抽奖结果
     */
    @RequestMapping(value = "/getInverstResult",method = RequestMethod.POST)
    @AccessToken(checkAccess = false,access= {AccessType.APP,AccessType.PC})
    public RespResult<InverstmentOutput> getInverstResult(InverstmentInput input){
        logger.info("聚合-运营中心-抽奖-input:[{}]",input);
        InverstmentOutput inverstResult = inverstmentClient.getInverstResult(input);

        logger.info("分发到业务inverstmentClient成功，inverstResult：[{}]",inverstResult);
        return RespResult.<InverstmentOutput>create().setContent(inverstResult);
    }
}

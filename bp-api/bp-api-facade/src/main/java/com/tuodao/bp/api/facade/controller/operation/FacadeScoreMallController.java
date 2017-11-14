package com.tuodao.bp.api.facade.controller.operation;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.operation.ScoreMallClient;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.operation.input.ScoreExchangeInput;
import com.tuodao.bp.model.business.operation.output.MyScoreOutput;
import com.tuodao.bp.model.business.operation.output.ScoreDetailOutput;
import com.tuodao.bp.model.business.operation.output.ScoreMallOutput;
import com.tuodao.bp.model.enums.AccessType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 聚合-积分商城Controller
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
@RestController
@RequestMapping("/router/op")
public class FacadeScoreMallController {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(FacadeScoreMallController.class);

    @Autowired
    private ScoreMallClient scoreMallClient;

    /**
     * 积分兑换专区分页查询
     * @param input
     * @return
     */
    @AccessToken(checkAccess = false,access = {AccessType.APP,AccessType.PC})
    @RequestMapping(value = "/getScoreMallPaged",method = RequestMethod.POST)
    public RespResult<PageInfo<ScoreMallOutput>> getScoreMallPaged(PagePojo input){
        logger.info("聚合-积分商城 - 积分兑换 - input : [{}]",input);
        PageInfo<ScoreMallOutput> scoreMallPaged = scoreMallClient.getScoreMallPaged(input);

        logger.info("分发到业务scoreMallClient成功，积分兑换专区分页查询：[{}]",scoreMallPaged);
        return RespResult.<PageInfo<ScoreMallOutput>>create().setContent(scoreMallPaged);
    }


    /**
     * 我的积分-统计
     * @param input 用户信息
     * @return 统计信息
     */
    @AccessToken(checkAccess = false,access = {AccessType.APP,AccessType.PC})
    @RequestMapping(value = "/getMyScoreStatistic",method = RequestMethod.POST)
    public RespResult<MyScoreOutput> getMyScoreStatistic(BasePojo input){
        logger.info("聚合-积分商城 - 我的积分统计-input :[{}] ",input);
        MyScoreOutput myScoreStatistic = scoreMallClient.getMyScoreStatistic(input);

        logger.info("分发到业务scoreMallClient成功，我的积分统计：[{}]",myScoreStatistic);
        return RespResult.<MyScoreOutput>create().setContent(myScoreStatistic);
    }


    /**
     * 我的积分-兑换
     * @param input 兑换信息
     * @return 兑换成功
     */
    @AccessToken(checkAccess = false,access = {AccessType.APP,AccessType.PC})
    @RequestMapping(value = "/scoreExchange",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public RespResult<Boolean> scoreExchange(ScoreExchangeInput input){
        logger.info("聚合-积分商城 - 积分兑换input : [{}]",input);
        boolean b = scoreMallClient.scoreExchange(input);

        logger.info("分发到业务scoreMallClient成功，我的积分-兑换是否成功：[{}]",b);
        return RespResult.<Boolean>create().setContent(b);
    }

    /**
     * 我的积分-积分明细列表
     * @param input 用户信息
     * @return 积分明细列表
     */
    @AccessToken(checkAccess = false,access = {AccessType.APP,AccessType.PC})
    @RequestMapping(value = "/getMyScoreDetailPaged",method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public RespResult<PageInfo<ScoreDetailOutput>> getMyScoreDetailPaged(PagePojo input){
        logger.info("聚合-积分商城 - 我的积分-明细列表 input:[{}]",input);
        PageInfo<ScoreDetailOutput> myScoreDetailPaged = scoreMallClient.getMyScoreDetailPaged(input);

        logger.info("分发到业务scoreMallClient成功，我的积分-积分明细列表：[{}]",myScoreDetailPaged);
        return RespResult.<PageInfo<ScoreDetailOutput>>create().setContent(myScoreDetailPaged);
    }
}

package com.tuodao.bp.operation.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.input.ScoreExchangeInput;
import com.tuodao.bp.model.business.operation.output.MyScoreOutput;
import com.tuodao.bp.model.business.operation.output.ScoreDetailOutput;
import com.tuodao.bp.model.business.operation.output.ScoreMallOutput;
import com.tuodao.bp.operation.service.IScoreMallService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 积分商城Controller
 * author hechuan
 * <p>
 * created on 2017/9/25
 * <p>
 * since V1.0.0
 */
@RestController
@RequestMapping("/scoreMall")
public class ScoreMallController {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(ScoreMallController.class);

    @Autowired
    private IScoreMallService scoreMallService;

    /**
     * 积分兑换专区分页查询
     *
     * @param input
     * @return
     */
    @RequestMapping(value = "/getScoreMallPaged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<ScoreMallOutput> getScoreMallPaged(PagePojo input) {
        logger.info("积分商城 - 积分兑换 - input : [{}]", input);
        return scoreMallService.getScoreMallPaged(input);
    }

    /**
     * 我的积分-统计
     *
     * @param input 用户信息
     * @return 统计信息
     */
    @RequestMapping(value = "/getMyScoreStatistic", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public MyScoreOutput getMyScoreStatistic(BasePojo input) {
        logger.info("我的积分统计-input :[{}] ", input);
        return scoreMallService.getMyScoreStatistic(input);
    }


    /**
     * 我的积分-兑换
     *
     * @param input 兑换信息
     * @return 兑换成功
     */
    @RequestMapping(value = "/scoreExchange", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public boolean scoreExchange(ScoreExchangeInput input) {
        logger.info("积分兑换input : [{}]", input);
        return scoreMallService.scoreExchange(input);
    }

    /**
     * 我的积分-积分明细列表
     *
     * @param input 用户信息
     * @return 积分明细列表
     */
    @RequestMapping(value = "/getMyScoreDetailPaged", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<ScoreDetailOutput> getMyScoreDetailPaged(PagePojo input) {
        logger.info("我的积分-明细列表 input:[{}]", input);

        return scoreMallService.getMyScoreDetailPaged(input);
    }
}

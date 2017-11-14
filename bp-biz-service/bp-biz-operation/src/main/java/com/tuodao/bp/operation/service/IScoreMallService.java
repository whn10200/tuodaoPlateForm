package com.tuodao.bp.operation.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.input.ScoreExchangeInput;
import com.tuodao.bp.model.business.operation.output.MyScoreOutput;
import com.tuodao.bp.model.business.operation.output.ScoreDetailOutput;
import com.tuodao.bp.model.business.operation.output.ScoreMallOutput;

/**
 * 积分商城-service
 * author hechuan
 * <p>
 * created on 2017/9/26
 * <p>
 * since V1.0.0
 */
public interface IScoreMallService {

    /**
     * 获取积分商城可以兑换的分页列表
     * @param input 分页信息
     * @return
     */
    PageInfo<ScoreMallOutput> getScoreMallPaged(PagePojo input);

    /**
     * 我的积分-统计
     * @param input 用户信息
     * @return 统计信息
     */
    MyScoreOutput getMyScoreStatistic(BasePojo input);

    /**
     * 我的积分-兑换
     * @param input 兑换信息
     * @return 兑换成功
     */
    boolean scoreExchange(ScoreExchangeInput input);


    /**
     * 我的积分-积分明细列表
     * @param input 用户信息
     * @return 积分明细列表
     */
    PageInfo<ScoreDetailOutput> getMyScoreDetailPaged(PagePojo input);
}

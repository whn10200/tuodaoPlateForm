package com.tuodao.bp.api.facade.client.operation;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.input.ScoreExchangeInput;
import com.tuodao.bp.model.business.operation.output.MyScoreOutput;
import com.tuodao.bp.model.business.operation.output.ScoreDetailOutput;
import com.tuodao.bp.model.business.operation.output.ScoreMallOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 积分商城-client
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
@FeignClient(value = "BIZ-OPERATION")
public interface ScoreMallClient {

    /**
     * 积分兑换专区分页查询
     * @param input
     * @return 积分兑换专区分页查询内容
     */
    @RequestMapping(value = "/scoreMall/getScoreMallPaged",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<ScoreMallOutput> getScoreMallPaged(PagePojo input);


    /**
     * 我的积分-统计
     * @param input 用户信息
     * @return 统计信息
     */
    @RequestMapping(value = "/scoreMall/getMyScoreStatistic",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    MyScoreOutput getMyScoreStatistic(BasePojo input);


    /**
     * 我的积分-兑换
     * @param input 兑换信息
     * @return 兑换成功
     */
    @RequestMapping(value = "/scoreMall/scoreExchange",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    boolean scoreExchange(ScoreExchangeInput input);


    /**
     * 我的积分-积分明细列表
     * @param input 用户信息
     * @return 积分明细列表
     */
    @RequestMapping(value = "/scoreMall/getMyScoreDetailPaged",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<ScoreDetailOutput> getMyScoreDetailPaged(PagePojo input);
}

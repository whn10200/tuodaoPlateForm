package com.tuodao.bp.api.facade.client.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;
import com.tuodao.bp.model.business.traningcenter.output.BorrowCollectionCapitalOutput;
import com.tuodao.bp.model.business.traningcenter.output.CollectionCountOutput;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionDayVo;
import com.tuodao.bp.model.traningcenter.output.BorrowCollectionOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author 王艳兵
 * 回款接口
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface BorrowCollectionClient {

    /**
     * 获取用户总的已回款总额
     * @param userId
     * @return
     */
    @RequestMapping(value = "/account/getUserTotalCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowCollectionOutput getUserTotalCollection(@RequestParam("userId") String userId);

    /**
     * 获取用户总 投资收益,平台奖励,加息券收益,
     * @param userId
     * @return
     */
    @RequestMapping(value = "/account/getUserCollectionInterest",consumes = MediaType.APPLICATION_JSON_VALUE)
    CollectionCountOutput getUserCollectionInterest(@RequestParam("userId")String userId);

    /**
     * 债转 回款计划
     * @param input
     * @return
     */
    @RequestMapping(value = "/backPlan/recovered", consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<BackMoneyPlanOutput> getRecoveredByTenderId(UserBackMoneyInput input);


    /**
     * 统计剩余期数
     * @param input
     * @return
     */
    @RequestMapping(value = "/backPlan/backPeriod",consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowCollectionCapitalOutput getBackPeriod(UserBackMoneyInput input);

    /**
     * 根据期限与标的ID获取该期标的所有投资的回款列表
     * @param repayment 还款信息
     * @return
     */
    @RequestMapping(value = "/collection/getTenderCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BackMoneyPlanOutput> getTenderCollection(@RequestParam("repaymentInput") BorrowRepaymentInput repayment);

    /**
     * 查询回款计划
     * @param input
     * @return
     */
    @RequestMapping(value = "/backPlan/getByTenderId", consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BackMoneyPlanOutput> getByTenderId(UserBackMoneyInput input);

    /**
     * 统计未回款剩余
     * @param input
     * @return
     */
    @RequestMapping(value = "/backPlan/unBackPeriod",consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowCollectionCapitalOutput getUnBackPeriod(UserBackMoneyInput input);

    /**
     * 根据月份或天获取用户待收本息 已收本息
     * @param param 用户ID,day 查询天或者月,month时间类型 天yyyy-mm-dd 月yyyy-mm
     * @return
     */
    @RequestMapping(value = "/collection/getMonthCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String,String> getMonthCollection(CollectionParam param);

    /**
     * 根据天分页获取回款信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/collection/getCollectionCalendarByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<BackMoneyPlanOutput> getCollectionCalendarByPage(CollectionParam param);

    /**
     * 获取某个月的回款日期
     * @param userId
     * @param month 月份 yyyy-MM
     * @return
     */
    @RequestMapping(value = "/collection/getCollectionDays",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<CollectionDayVo> getCollectionDays(@RequestParam("userId")String userId, @RequestParam("month") String month);

    /**
     * 借款人还款后调用投资人回款接口
     * @param repaymentInput 还款信息
     */
    @RequestMapping(value = "/collection/collectionRepayment",consumes = MediaType.APPLICATION_JSON_VALUE)
    void collectionRepayment(@RequestParam("repaymentInput") BorrowRepaymentInput repaymentInput);


}

package com.tuodao.bp.api.facade.controller.traningcenter;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.transaction.AccountClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowCollectionClient;
import com.tuodao.bp.api.facade.service.transaction.BorrowCollectionService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionExtParam;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.BackMoneyPlanVo;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionDayVo;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionVo;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.BigDecimalUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/20
 * @time: 10:10
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/router")
public class BorrowCollectionController {

    @Autowired
    private BorrowCollectionService borrowCollectionService;

    @Autowired
    private AccountClient accountClient;

    @Autowired
    private BorrowCollectionClient borrowCollectionClient;

    /**
     * 获取用户投标的回款计划列表
     * @param param 投标信息
     * @return
     */
    @RequestMapping("/tc/tender/borrow_collection_list")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<PageInfo<BackMoneyPlanVo>> getBorrowCollectionList(UserBackMoneyInput param){

        PageInfo<BackMoneyPlanVo> planVoPageInfo = borrowCollectionService.pageByTenderId(param);

        return RespResult.<PageInfo<BackMoneyPlanVo>>create().setContent(planVoPageInfo);
    }


    /**
     * 获取用户总待收 以account表为主
     * @param pojo
     * @return
     */
    @RequestMapping("/tc/tender/total_collection")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<String> getTotalCollection(BasePojo pojo){
        AccountOutput account = accountClient.getUserAccount(pojo.getUserId());
        if (account == null){
            throw new MicroServiceException(TransactError.ACCOUNT_NOT_FOUND);
        }
        String totalCollection = BigDecimalUtils.centToYuanFormat(account.getAwaitCapital().add(account.getAwaitInterest()));
        return RespResult.<String>create().setContent(totalCollection);
    }

    /**
     * 查询某月或某天的 已回款,未回款资金
     * @param param 哪一月数据或那一天
     * @return
     */
    @RequestMapping("/tc/tender/month_collection")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<Map<String,String>> monthCollection(CollectionParam param){
        Map<String,String> map = borrowCollectionClient.getMonthCollection(param);

        return RespResult.<Map<String,String>>create().setContent(map);
    }


    /**
     * 回款日历,按天查询回款列表
     * @param param
     * @return
     */
    @RequestMapping("/tc/tender/collection_calendar_list")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<PageInfo<CollectionVo>> getCalendarList(CollectionParam param){
        PageInfo<CollectionVo> paging = borrowCollectionService.getCollectionCalendarByPage(param);
        return RespResult.<PageInfo<CollectionVo>>create().setContent(paging);
    }

    /**
     * 获取某个月回款的日期与回款状态
     * @param param
     * @return
     */
    @RequestMapping("/tc/tender/collection_days")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<List<CollectionDayVo>> getCollectionDays(CollectionExtParam param){
        List<CollectionDayVo> list = borrowCollectionClient.getCollectionDays(param.getUserId(),param.getMonth());
        return RespResult.<List<CollectionDayVo>>create().setContent(list);
    }



}

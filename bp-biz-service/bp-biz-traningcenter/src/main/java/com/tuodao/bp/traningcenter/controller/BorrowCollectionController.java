package com.tuodao.bp.traningcenter.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;
import com.tuodao.bp.model.business.traningcenter.output.BorrowCollectionCapitalOutput;
import com.tuodao.bp.model.business.traningcenter.output.CollectionCountOutput;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionDayVo;
import com.tuodao.bp.model.traningcenter.output.BorrowCollectionOutput;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollectionCapital;
import com.tuodao.bp.traningcenter.enums.CollectionStatus;
import com.tuodao.bp.traningcenter.service.BorrowCollectionService;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 20:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
public class BorrowCollectionController {

    @Autowired
    private BorrowCollectionService borrowCollectionService;

//    @Autowired
//    BorrowRepaymentService borrowRepaymentService;



    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowCollectionController.class);

    /**
     * 获取用户已回款总额
     * @param userId
     * @return
     */
    @RequestMapping(value = "/account/getUserTotalCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowCollectionOutput getUserTotalCollection(@RequestParam("userId") String userId){
        LOGGER.debug("获取用户已回款总额,入参:{}",userId);
        double totalCollection = borrowCollectionService.getUserTotalCollection(userId);
        return new BorrowCollectionOutput(totalCollection);
    }


    /**
     * 查询回款计划
     * @param input
     * @return
     */
    @RequestMapping(value = "/backPlan/recovered")
    public PageInfo<BackMoneyPlanOutput> getRecoveredByTenderId(UserBackMoneyInput input) {
        return borrowCollectionService.getRecoveredByTenderId(input);
    }

    /**
     * 查询回款计划
     * @param input
     * @return
     */
    @RequestMapping(value = "/backPlan/getByTenderId")
    public List<BackMoneyPlanOutput> getByTenderId(UserBackMoneyInput input) {
        return borrowCollectionService.getByTenderId(input.getUserId(), input.getTenderId());
    }


    /**
     * 统计未回款剩余
     * @param input
     * @return
     */
    @RequestMapping(value = "/backPlan/unBackPeriod")
    public BorrowCollectionCapitalOutput getUnBackPlan(UserBackMoneyInput input) {
        BorrowCollectionCapital borrowCollectionCapital = borrowCollectionService.getUnBackPlan(input.getTenderId());

        BorrowCollectionCapitalOutput borrowCollectionCapitalOutput = new BorrowCollectionCapitalOutput();
        TransferUtil.transfer(borrowCollectionCapital,borrowCollectionCapitalOutput);

        //已经获取的利息
        BorrowCollectionCapital borrowCollectionCapital1 = borrowCollectionService.getBackPlan(input.getTenderId());
        borrowCollectionCapital.setInterest(borrowCollectionCapital1.getInterest());

        //未获得利息
        borrowCollectionCapitalOutput.setUnInterest(borrowCollectionCapital.getInterest());

        //最新还款时间
//        Date repayTime = borrowRepaymentService.getLastRepayTime(input.getBorrowId());
//        if(repayTime != null) {
//            borrowCollectionCapitalOutput.setLastRepayTime(repayTime);
//        }
        List<BorrowCollection> borrowCollectionList = borrowCollectionService.getCollection(input.getTenderId(), 0);
        if(!CollectionUtils.isEmpty(borrowCollectionList)) {
            BorrowCollection borrowCollection = borrowCollectionList.get(0);
            borrowCollectionCapitalOutput.setCurrentInterest(borrowCollection.getPreInterest());
        }

        return borrowCollectionCapitalOutput;
    }


    /**
     * 统计剩余期数
     * @param input
     * @return
     */
    @RequestMapping(value = "/backPlan/backPeriod")
    public BorrowCollectionCapitalOutput getBackPlan(UserBackMoneyInput input) {
        BorrowCollectionCapitalOutput borrowCollectionCapitalOutput = new BorrowCollectionCapitalOutput();
        BorrowCollectionCapital borrowCollectionCapital = borrowCollectionService.getBackPlan(input.getTenderId());
        TransferUtil.transfer(borrowCollectionCapital, borrowCollectionCapitalOutput);

        //最新还款时间
        List<BorrowCollection> borrowCollectionList =  borrowCollectionService.getCollection(input.getTenderId(),
                CollectionStatus.SUCCESS.getValue());
        if(!CollectionUtils.isEmpty(borrowCollectionList)) {
            BorrowCollection  borrowCollection = borrowCollectionList.get(borrowCollectionList.size());
            borrowCollectionCapitalOutput.setLastRepayTime(borrowCollection.getCollectionTime());
        }

        //最早一笔应还利息
//        BigDecimal repayInterest = borrowRepaymentService.getEarliestInterest(input.getBorrowId());
//        if(repayInterest != null) {
//            borrowCollectionCapitalOutput.setRepayInterest(repayInterest);
//        }

        borrowCollectionList = borrowCollectionService.getCollection(input.getTenderId(), 0);
        if(!CollectionUtils.isEmpty(borrowCollectionList)) {
            BorrowCollection borrowCollection = borrowCollectionList.get(0);
            borrowCollectionCapitalOutput.setCurrentInterest(borrowCollection.getPreInterest());
        }

        return borrowCollectionCapitalOutput;
    }


    /**
     * 获取标的对应某一期的回款记录列表
     * @param repayment
     * @return
     */
    @RequestMapping(value = "/collection/getTenderCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BackMoneyPlanOutput> getTenderCollection(@RequestParam("repayment")BorrowRepaymentInput repayment){
        LOGGER.debug("获取标的对应某一期的回款记录列表,入参:{}",repayment);
        return  borrowCollectionService.getTenderCollection(repayment);
    }



    /**
     * 根据月份获取用户待收本息 已收本息
     * @param param 参数
     * @return
     */
    @RequestMapping(value = "/collection/getMonthCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> getMonthCollection(CollectionParam param){
        LOGGER.debug("根据月份获取用户待收本息,已收本息 入参:{}",param);
        Map<String,String> map = new HashMap<>();

        Map<String,BigDecimal> realCollection = borrowCollectionService.getRealCollection(param);
        Map<String,BigDecimal> collection = borrowCollectionService.getPreCollection(param);
        //已回款本息=本金+利息
        double realTotal = realCollection.get("interest").add(realCollection.get("capital")).doubleValue();
        //未回款本息
        double total = collection.get("interest").add(collection.get("capital")).doubleValue();
        map.put("preCollection", BigDecimalUtils.centToYuanFormat(total));
        map.put("realCollection",BigDecimalUtils.centToYuanFormat(realTotal));
        return map;
    }

    /**
     * 根据天分页获取回款计划信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/collection/getCollectionCalendarByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<BackMoneyPlanOutput> getCollectionCalendarByPage(CollectionParam param){
        LOGGER.debug("根据天分页获取回款计划信息,入参:{},条件:{}",param,param.getDay());
        return borrowCollectionService.getCollectionCalendarByPage(param);
    }

    /**
     * @param userId
     * @param month
     * @return
     */
    @RequestMapping(value = "/collection/getCollectionDays",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CollectionDayVo> getCollectionDays(@RequestParam("userId")String userId, @RequestParam("month") String month){
        return borrowCollectionService.getCollectionDays(userId,month);
    }

    /**
     * 借款人还款后调用投资人回款接口
     * @param repayment     回款信息
     */
    @RequestMapping(value = "/collection/collectionRepayment",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void collectionRepayment(@RequestParam("repaymentInput") BorrowRepaymentInput repayment){
        borrowCollectionService.collection(repayment);
    }


    /**
     * 获取用户总回款收益
     * @param userId
     * @return
     */
    @RequestMapping(value = "/account/getUserCollectionInterest",consumes = MediaType.APPLICATION_JSON_VALUE)
    public CollectionCountOutput getUserCollectionInterest(@RequestParam("userId")String userId){
        return borrowCollectionService.getUserCollectionInterest(userId);
    }

}

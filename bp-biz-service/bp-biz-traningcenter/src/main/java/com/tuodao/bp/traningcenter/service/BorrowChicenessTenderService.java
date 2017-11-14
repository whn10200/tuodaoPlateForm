package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.ChoicenessTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.SelectTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderTraRecordInput;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowChoicenessTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowCollection;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;

import java.math.BigDecimal;
import java.util.List;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/8 0008.
 * @time: 14:00
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface BorrowChicenessTenderService {

    BigDecimal insertChoiceness(ChoicenessTenderInput choicenessTenderInput,String ip);

    List<SelectNoMateOutput> selectNoMate();

    int updateChoiceness(ChoicenessTenderInput choicenessTenderInput);

    PageInfo<TenderRecord> getJoinLists(TenderTraRecordInput tenderTrRecordInput);

    PageInfo<SelectTenderOutput> selectByStatusAndUserId(SelectTenderInput selectTenderInput);

    TenderDetailsOutput selectTenderById(Integer tenderId);

    List<ChicenessTenderOutput> selectUnmatchingList();


    void insertChoiceness(BigDecimal account,Integer tenderId);

    /**
     * 精选计划投标到期 将相关在投投标撤回
     * @param tenderId 精选计划投标id
     * @param borrowId 精选计划标的id
     */
    List<SelectClaimOutput> selectClaim(Integer tenderId,Integer borrowId);

    /**
     * 获取精选计划到期时,待转让的回款列表
     * @param tenderId 精选计划投标id
     * @param borrowId 精选计划标的id
     * @return
     */
    List<BorrowCollection> getTendingByChoicenessTenderId(Integer tenderId,Integer borrowId);


    List<BorrowCollection> selectClaimInside(String userId);

    TenderTraRecordOutput getMaxAndLast(TenderTraRecordInput tenderTrRecordInput);

    /**
     * 回款更新精选计划相关数据
     * @param collection 回款信息
     * @param choicenessTenderId 精选计划投标id
     */
    void updatePlanCollection(BorrowCollection collection,Integer choicenessTenderId);

    /**
     * 扫尾并且最大投标 3倍积分
     * @param max 最大投标
     * @param last 最后投标
     * @param product 产品信息
     */
    void maxAndLastTender(BorrowChoicenessTender max, BorrowChoicenessTender last, ProductOutput product);

    /**
     * 扫尾投资 双倍积分
     * @param product
     * @return 减少查询次数
     */
    BorrowChoicenessTender lastTender(ProductOutput product);

    /**
     * 投资最多积分 双倍投资积分
     * @param product
     * @return 减少查询次数
     */
    BorrowChoicenessTender maxTender(ProductOutput product);

    /**
     * 获取本次投标的基础积分
     * 天标投资奖励 奖励：1积分/5000元投资额
     * 12月份之上的投标奖励 奖励：1积分/1000元投资额
     * 12月份之下的投标奖励 奖励：1积分/2000元投资额
     * @param tender
     * @param product
     * @return 积分数
     */
    int getBaseScore(BorrowChoicenessTender tender,ProductOutput product);

    /**
     * 天标投资奖励 奖励：1积分/5000元投资额
     * @param tender
     * @param product
     */
    void dayTender(BorrowChoicenessTender tender, ProductOutput product);

    /**
     * 12月份之上的投标奖励 奖励：1积分/1000元投资额
     * @param tender
     * @param product
     */
    void above12MonthTender(BorrowChoicenessTender tender, ProductOutput product);

    /**
     * 12月份之下的投标奖励 奖励：1积分/2000元投资额
     * @param tender
     * @param product
     */
    void under12MonthTender(BorrowChoicenessTender tender, ProductOutput product);

    /**
     * 手机投资奖励
     * @param tender
     */
    void appTender(BorrowChoicenessTender tender);

    /**
     * 首投积分奖励,
     * 首投等额本息奖励,
     * 第二次投标奖励
     * @param tender
     */
    void firstOrSecondTender(BorrowChoicenessTender tender,ProductOutput product);

}

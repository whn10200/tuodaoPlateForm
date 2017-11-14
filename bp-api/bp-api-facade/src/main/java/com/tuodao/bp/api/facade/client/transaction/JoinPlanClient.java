package com.tuodao.bp.api.facade.client.transaction;


import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.traningcenter.input.ChoicenessTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.model.business.traningcenter.input.SelectTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderTraRecordInput;
import com.tuodao.bp.model.business.traningcenter.output.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description:客户端调用
 * @author: wuzf
 * @date: 2017/10/20 0011.
 * @time: 11:40
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface JoinPlanClient {

    /**
     * 生成加入理财计划生产者
     * @param choicenessTenderInput
     * @return int
     */
    @RequestMapping(value = "/TraningCenter/borrowChicenessTender/insertChoicenessProvide", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public void insertChoiceness(ChoicenessTenderInput choicenessTenderInput);

    /**
     * 根据产品id查询加入理财计划的记录
     * @param tenderTrRecordInput
     * @return PageInfo<TenderRecord>
     */
    @RequestMapping(value = "/TraningCenter/borrowChiceness/getJoinLists", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<TenderRecord> getJoinLists(TenderTraRecordInput tenderTrRecordInput);

    /**
     * 根据userid和状态和起始时间分页查询理财计划投资记录
     * @param  tenderInput
     * @return PageInfo<SelectTenderOutput>
     */
    @RequestMapping(value = "/TraningCenter/borrowChicenessTender/selectTenderByBorrowId", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<SelectTenderOutput> getTenderByUserId(SelectTenderInput tenderInput);


    /**
     * 精选计划投资详情（1）
     * @param justIdInput
     * @return TenderDetailsOutput
     */
    @RequestMapping(value = "/TraningCenter/borrowChicenessTender/selectTenderById", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public TenderDetailsOutput selectTenderById(JustIdInput justIdInput);

    /**
     * 精选计划投资详情（2理财计划投资详情债权明细）
     * @param  justIdInput
     * @return PageInfo<UnderTenderOutput>
     */
    @RequestMapping(value = "/TraningCenter/borrowChicenessTender/selectTenderListByChoicenessTenderId", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<UnderTenderOutput> selectTenderListByChoicenessTenderId(JustIdInput justIdInput);


    /**
     * 精选计划投资详情（3理财计划投资详情还款计划）
     * @param  justIdInput
     * @return PageInfo<UnderTenderOutput>
     */
    @RequestMapping(value = "/TraningCenter/borrowChiceness/selectRecoverListBychioId", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<RecoverListOutput> selectRecoverListBychioId(JustIdInput justIdInput);


    @RequestMapping(value = "/TraningCenter/borrowChicenessTender/getMaxAndLast", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public TenderTraRecordOutput getMaxAndLast(TenderTraRecordInput tenderTrRecordInput);


    /**
     * 查询未匹配的资金
     * @return
     */
    @RequestMapping(value = "/TraningCenter/borrowChicenessTender/selectNoMate", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public List<SelectNoMateOutput> selectNoMate();


}

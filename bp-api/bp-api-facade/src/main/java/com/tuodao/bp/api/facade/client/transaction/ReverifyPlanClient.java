package com.tuodao.bp.api.facade.client.transaction;

import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.PlanBorrowTenderOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author wuzf
 * @date 2017/9/13
 * @description 理财计划复审 理财计划下普通标的复审 理财计划下转让标的复审
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface ReverifyPlanClient {

    /**
     * 理财计划复审
     * @param reverifyPlanInput
     */
    @RequestMapping(value = "/TraningCenter/plan/reverifyPlan", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    void reverifyPlan(ReverifyPlanInput reverifyPlanInput);



    /**
     * 查询投资该标的的所有投资（理财计划下底层标的的普通投标）
     */
    @RequestMapping(value = "/TraningCenter/plan/selectTenderListOnOrgReverify", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    List<PlanBorrowTenderOutput> selectTenderListOnOrgReverify(JustIdInput inputs);




    /**
     * 查询投资该标的的所有投资（理财计划下底层标的的普通投标）
     */
    @RequestMapping(value = "/TraningCenter/plan/selectTenderListOnDevReverify", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    List<PlanBorrowTenderOutput> selectTenderListOnDevReverify(JustIdInput inputs);

    /**
     * 查询投资该标的的所有投资（理财计划下底层标的的普通投标）
     */
    @RequestMapping(value = "/TraningCenter/plan/selectTenderListOnDevReverifyPlay", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    List<PlanBorrowTenderOutput> selectTenderListOnDevReverifyPlay(JustIdInput inputs);


    /**
     * 理财计划下底层普通标的复审
     * @param inputs
     */
    @RequestMapping(value = "/TraningCenter/plan/reverifyOrignalBorrow", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    void reverifyOrignalBorrow(PlanNomalListInput inputs);


    /**
     * 理财计划下底层转让标的复审
     * @param inputs
     */
    @RequestMapping(value = "/TraningCenter/plan/reverifyDevelopBorrow", method = RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    void reverifyDevelopBorrow(PlanDevelopListInput inputs);



}


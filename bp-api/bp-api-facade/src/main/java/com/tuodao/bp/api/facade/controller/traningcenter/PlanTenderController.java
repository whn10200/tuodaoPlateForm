package com.tuodao.bp.api.facade.controller.traningcenter;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.model.business.traningcenter.input.SelectTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderTraRecordInput;
import com.tuodao.bp.model.business.traningcenter.output.SelectTenderOutput;
import com.tuodao.bp.model.business.traningcenter.output.TenderRecord;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *理财计划投资相关
 */
@RestController
@RequestMapping("PlanTenderController")
public class PlanTenderController {


    /**
     * 根据userid和状态和起始时间分页查询理财计划投资记录（pc我的投资精选计划）
     * @param  tenderInput
     * @return PageInfo<SelectTenderOutput>
     */
//    @RequestMapping(value = "/selectTenderByBorrowId", method = RequestMethod.POST)
//    public PageInfo<SelectTenderOutput> selectTenderByBorrowId(SelectTenderInput tenderInput) {
//        return borrowChicenessTenderService.selectByStatusAndUserId(tenderInput);
//    }

}

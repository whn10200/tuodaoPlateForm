package com.tuodao.bp.product.controller;

import com.tuodao.bp.model.business.product.input.BorrowMappingBankInput;
import com.tuodao.bp.model.business.product.output.BorrowMappingBankOutput;
import com.tuodao.bp.product.service.IBorrowMappingBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/11/6 0006 15:58
 * @Introduction
 */
@RestController
@RequestMapping("/borrowMappingBank")
public class BorrowMappingBankController {


    @Autowired
    private IBorrowMappingBankService borrowMappingBankService;

    @RequestMapping(value="/updateRecord",method= RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public Boolean  getBorrowPlanTransfer(BorrowMappingBankInput input) {
        return borrowMappingBankService.updateRecord(input);
    }


    /**
     * 获取记录
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/getRecordByBorrowId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    BorrowMappingBankOutput getRecordByBorrowId(Integer borrowId){

        return borrowMappingBankService.getRecordByBorrowId(borrowId);
    }


    /**
     * 查询所有需要垫付资金的 记录
     * @return
     */
    @RequestMapping(value = "/getCompensationList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    List<BorrowMappingBankOutput> getCompensationList(){
        return borrowMappingBankService.getCompensationList();
    }

}

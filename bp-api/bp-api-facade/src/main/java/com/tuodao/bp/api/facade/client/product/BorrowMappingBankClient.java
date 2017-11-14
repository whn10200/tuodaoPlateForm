package com.tuodao.bp.api.facade.client.product;

import com.tuodao.bp.model.business.product.input.BorrowMappingBankInput;
import com.tuodao.bp.model.business.product.output.BorrowMappingBankOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/11/6 0006 15:58
 * @Introduction
 */
@RequestMapping("/borrowMappingBank")
@FeignClient(value="biz-product")
public interface BorrowMappingBankClient {


    /**
     * 更新记录
     * @param input
     * @return
     */
    @RequestMapping(value = "/updateRecord", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateRecord(BorrowMappingBankInput input);

    /**
     * 获取记录
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/getRecordByBorrowId", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowMappingBankOutput getRecordByBorrowId(Integer borrowId);

    /**
     * 查询所有需要垫付资金的 记录
     * @return
     */
    @RequestMapping(value = "/getCompensationList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BorrowMappingBankOutput>  getCompensationList();
}

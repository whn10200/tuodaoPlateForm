package com.tuodao.bp.product.client;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;

/**
 * 回款接口
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface BorrowCollectionClient {

    

    /**
     * 根据期限与标的ID获取该期标的所有投资的回款列表
     * @param repayment 还款信息
     * @return
     */
    @RequestMapping(value = "/collection/getTenderCollection",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BackMoneyPlanOutput> getTenderCollection(@RequestParam("repaymentInput") BorrowRepaymentInput repayment);


    
    /**
     * 借款人还款后调用投资人回款接口
     * @param repaymentInput 还款信息
     */
    @RequestMapping(value = "/collection/collectionRepayment",consumes = MediaType.APPLICATION_JSON_VALUE)
    void collectionRepayment(@RequestParam("repaymentInput") BorrowRepaymentInput repaymentInput);


}

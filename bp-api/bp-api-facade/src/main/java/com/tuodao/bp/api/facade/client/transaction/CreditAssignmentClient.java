package com.tuodao.bp.api.facade.client.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author qingli.chen
 * @date 2017/9/15
 * @description 债转
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface CreditAssignmentClient {

    /**
     * 可转让列表
     * @param creditAssignmentInput
     * @return
     */
    @RequestMapping(value = "/creditAssignment/transferable",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<CreditAssignmentOutput> pageByTransferableList(CreditAssignmentInput creditAssignmentInput);

    /**
     * 转让中、已转让列表
     * @param creditAssignmentInput
     * @return
     */
    @RequestMapping(value = "/creditAssignment/transfer", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<BorrowTransferOutput> pageByTransferList(CreditAssignmentInput creditAssignmentInput);

    /**
     * 已受让列表
     * @param creditAssignmentInput
     * @return
     */
    @RequestMapping(value = "/creditAssignment/transferee",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<BorrowTransferOutput> pageByTransferee(CreditAssignmentInput creditAssignmentInput);

    /**
     * 债转详情
     * @param tenderTransferInput
     * @return
     */
    @RequestMapping(value = "/info",consumes = MediaType.APPLICATION_JSON_VALUE)
    TenderTransferOutput info(TenderTransferInput tenderTransferInput);

    /**
     * 债转详情
     * @param tenderTransferInput
     * @return
     */
    @RequestMapping(value = "/getById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    TenderTransferOutput getById(TenderTransferInput tenderTransferInput);

    /**
     * 债转保存
     * @param transferSaveInput
     * @return
     */
    @RequestMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE)
    void save(TransferSaveInput transferSaveInput);

    /**
     * 债转保存
     * @param transferModifyInput
     * @return
     */
    @RequestMapping(value = "/modify",consumes = MediaType.APPLICATION_JSON_VALUE)
    void modify(TransferModifyInput transferModifyInput);

    /**
     * 债转投资列表查询
     * @param input
     * @return
     */
    @RequestMapping(value = "/creditAssignment/query", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<BorrowTransferQueryOutput> query(TransferQueryInput input);

    /**
     * 债转投资详情查询
     * @param input
     * @return
     */
    @RequestMapping(value = "/creditAssignment/query/info", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowTransferInfoOutput queryInfo(TransferQueryInput input);

    /**
     * 投资债权
     * @param input
     */
    @RequestMapping(value = "/creditAssignment/tender", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowTenderOutput tender(TransferTenderInput input);


    @RequestMapping(value = "/mq/test", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    void test(BasePojo basePojo);
}



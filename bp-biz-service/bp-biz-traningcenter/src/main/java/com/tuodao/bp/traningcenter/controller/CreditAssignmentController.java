package com.tuodao.bp.traningcenter.controller;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTransfer;
import com.tuodao.bp.traningcenter.enums.TransferRepayType;
import com.tuodao.bp.traningcenter.service.AccountService;
import com.tuodao.bp.traningcenter.service.CreditAssignmentService;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author qingli.chen
 * @date 2017/9/14
 * @description 债权转让
 */
@RestController
@RequestMapping("/creditAssignment")
public class CreditAssignmentController {

    @Autowired
    CreditAssignmentService assignmentService;

    @Autowired
    AccountService accountService;

    /**
     * 可转让列表
     * @param input
     * @return
     */
    @RequestMapping("/transferable")
    public PageInfo<CreditAssignmentOutput> pageByTransferableList(CreditAssignmentInput input) {
        PageInfo<CreditAssignmentOutput> pageInfo = assignmentService.pageByTransferableList(input);
        return pageInfo;
    }

    /**
     * 转让中、已转让列表
     * @param input
     * @return
     */
    @RequestMapping("/transfer")
    public PageInfo<BorrowTransferOutput> pageByTransferList(CreditAssignmentInput input) {
        PageInfo<BorrowTransferOutput> pageInfo = assignmentService.pageByTransferList(input);
        return pageInfo;
    }

    /**
     * 已受让列表
     * @param input
     * @return
     */
    @RequestMapping("/transferee")
    public PageInfo<BorrowTransferOutput> pageByTransferee(CreditAssignmentInput input) {
        PageInfo<BorrowTransferOutput> pageInfo = assignmentService.pageByTransferee(input);
        return pageInfo;
    }

    /**
     * 债权详情
     * @param tenderTransferInput
     * @return
     */
    @RequestMapping("/info")
    public TenderTransferOutput info(TenderTransferInput tenderTransferInput) {
        TenderTransferOutput tenderTransferOutput = new TenderTransferOutput();
        BorrowTransfer borrowTransfer = assignmentService.findByTenderId(tenderTransferInput.getTenderId());
        TransferUtil.transfer(borrowTransfer, tenderTransferOutput);
        return tenderTransferOutput;
    }

    /**
     * 债权详情
     * @param tenderTransferInput
     * @return
     */
    @RequestMapping("/getById")
    public TenderTransferOutput getById(TenderTransferInput tenderTransferInput) {
        TenderTransferOutput tenderTransferOutput = new TenderTransferOutput();
        BorrowTransfer borrowTransfer = assignmentService.findById(tenderTransferInput.getTenderId());
        TransferUtil.transfer(borrowTransfer, tenderTransferOutput);
        return tenderTransferOutput;
    }

    /**
     * 保存债转
     * @param input
     */
    @RequestMapping("/save")
    public void save(TransferSaveInput input) {
        BorrowTransfer borrowTransfer = new BorrowTransfer();
        TransferUtil.transfer(input, borrowTransfer);
        assignmentService.save(borrowTransfer);
    }

    /**
     * 保存债转
     * @param input
     */
    @RequestMapping("/modify")
    public void modify(TransferModifyInput input) {
        BorrowTransfer borrowTransfer = new BorrowTransfer();
        TransferUtil.transfer(input, borrowTransfer);
        assignmentService.save(borrowTransfer);
    }

    /**
     * 债权转让查询
     * @param input
     */
    @RequestMapping("/query")
    public PageInfo<BorrowTransferQueryOutput> query(TransferQueryInput input) {
        PageInfo<BorrowTransfer> pageInfo = assignmentService.findByStatus(input);
        List<BorrowTransfer> borrowTransferList = pageInfo.getList();

        PageInfo<BorrowTransferQueryOutput> outputPageInfo = new PageInfo<>();
        List<BorrowTransferQueryOutput> list =  new ArrayList<>();
        borrowTransferList.forEach(borrowTransfer -> {
            BorrowTransferQueryOutput borrowTransferQueryOutput = new BorrowTransferQueryOutput();
            TransferUtil.transfer(borrowTransfer, BorrowTransferQueryOutput.class);

            borrowTransferQueryOutput.setClaimsValue(BigDecimalUtils.centToYuan(borrowTransfer.getTransferWorth()));
            borrowTransferQueryOutput.setAccount(BigDecimalUtils.centToYuan(borrowTransfer.getAccount()));
            borrowTransferQueryOutput.setLastAccount(BigDecimalUtils.centToYuan(borrowTransfer.getAccount()
                    .subtract(borrowTransfer.getAccountYes())));
            borrowTransferQueryOutput.setFinished(borrowTransfer.getAccount()
                    .compareTo(borrowTransfer.getAccountYes()) == 0 ? true : false);

            borrowTransferQueryOutput.setReward((borrowTransfer.getToSource() == null
                    || borrowTransfer.getToSource() == 0) ? BigDecimalUtils.centToYuan(borrowTransfer.getFee()) : BigDecimal.ZERO);
            list.add(borrowTransferQueryOutput);
        });

        outputPageInfo.setList(list);
        outputPageInfo.setTotal(pageInfo.getTotal());

        return outputPageInfo;
    }

    /**
     * 债权转让详情查询
     * @param input
     */
    @RequestMapping("/query/info")
    public BorrowTransferInfoOutput queryInfo(TransferQueryInput input) {
        BorrowTransfer borrowTransfer = assignmentService.findById(input.getTransferId());

        BorrowTransferInfoOutput borrowTransferQueryOutput = new BorrowTransferInfoOutput();
        TransferUtil.transfer(borrowTransfer, borrowTransferQueryOutput);

        borrowTransferQueryOutput.setClaimsValue(BigDecimalUtils.centToYuan(borrowTransfer.getTransferWorth()));
        borrowTransferQueryOutput.setLastAccount(BigDecimalUtils.centToYuan(borrowTransfer.getAccount().subtract(borrowTransfer.getAccountYes())));
        borrowTransferQueryOutput.setAccount(BigDecimalUtils.centToYuan(borrowTransfer.getAccount()));
        borrowTransferQueryOutput.setReward((Objects.isNull(borrowTransfer.getToSource())
                || borrowTransfer.getToSource() == 0) ? BigDecimalUtils.centToYuan(borrowTransfer.getFee()) : BigDecimal.ZERO);
        borrowTransferQueryOutput.setRepaymentType(TransferRepayType.getMsg(borrowTransfer.getRaymentType()));
        borrowTransferQueryOutput.setFinished(borrowTransfer.getAccount()
                .compareTo(borrowTransfer.getAccountYes()) == 0 ? true : false);
        borrowTransferQueryOutput.setFee(BigDecimalUtils.centToYuan(borrowTransfer.getFee()));
        return borrowTransferQueryOutput;
    }

    @RequestMapping("/tender")
    public BorrowTenderOutput tender(TransferTenderInput input) {
        BorrowTender borrowTender = assignmentService.tender(input);
        BorrowTenderOutput borrowTenderOutput = new BorrowTenderOutput();
        TransferUtil.transfer(borrowTender, borrowTenderOutput);
        return borrowTenderOutput;
    }
}

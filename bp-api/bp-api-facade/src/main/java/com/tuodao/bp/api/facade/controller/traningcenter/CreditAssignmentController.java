package com.tuodao.bp.api.facade.controller.traningcenter;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.enums.CreditAssignmentStatus;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.utils.TransferUtil;
import com.tuodao.bp.api.facade.client.transaction.CreditAssignmentClient;
import com.tuodao.bp.api.facade.service.transaction.BorrowCollectionService;
import com.tuodao.bp.api.facade.service.transaction.BorrowTenderService;
import com.tuodao.bp.api.facade.service.transaction.CreditAssignmentService;
import com.tuodao.bp.model.business.traningcenter.input.CreditAssignmentInput;
import com.tuodao.bp.model.business.traningcenter.input.TransferQueryInput;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.model.facade.traningcenter.output.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author qingli.chen
 * @date 2017/9/15
 * @description 债权转让
 */
@RestController
@RequestMapping("/router/creditAssignment")
public class CreditAssignmentController {


    @Autowired
    CreditAssignmentClient creditAssignmentClient;

    @Autowired
    BorrowTenderService borrowTenderService;

    @Autowired
    BorrowCollectionService borrowCollectionService;

    @Autowired
    CreditAssignmentService creditAssignmentService;

    @Autowired
    ProductClient productClient;

    /**
     * 我的债权转让列表
     * @param input
     * @return
     */
    @RequestMapping("/list")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult pageByTransferableList(CreditAssignmentInput input) {
        PageInfo<CreditAssignmentVo> pageInfo = new PageInfo<>();

        if(input.getStatus() == CreditAssignmentStatus.TRANSFERABLE.getValue()) {
            pageInfo = creditAssignmentService.pageByTransferableList(input);
        } else if(input.getStatus() == CreditAssignmentStatus.TRANSFER.getValue()) {
            pageInfo = creditAssignmentService.pageByTransferList(input);
        } else if(input.getStatus() == CreditAssignmentStatus.TRANSFERRED.getValue()) {
            pageInfo = creditAssignmentService.pageByTransferList(input);
        }
        return RespResult.create().setContent(pageInfo);
    }

    /**
     * 我的债权列表-可转让 app
     * @param pagePojo
     * @return
     */
    @RequestMapping("/app/transferableList")
    @AccessToken(access = {AccessType.APP})
    public RespResult pageByTransferable(PagePojo pagePojo) {
        PageInfo<TransferableVo> pageInfo = creditAssignmentService.pageByTransferable(pagePojo);
        return RespResult.create().setContent(pageInfo);
    }

    /**
     * 我的债权列表-转让记录 app
     * @param pagePojo
     * @return
     */
    @RequestMapping("/app/transferLog")
    @AccessToken(access = {AccessType.APP})
    public RespResult transferLog(PagePojo pagePojo) {
        PageInfo<TransferCompromisedVo> pageInfo = creditAssignmentService.pageByTransferLog(pagePojo);
        return RespResult.create().setContent(pageInfo);
    }

    /**
     * 我的债权列表-已受让 app
     * @param pagePojo
     * @return
     */
    @RequestMapping("/app/compromisedList")
    @AccessToken(access = {AccessType.APP})
    public RespResult pageByCompromisedListApp(PagePojo pagePojo) {
        PageInfo<TransferCompromisedVo> pageInfo = creditAssignmentService.pageByCompromised(pagePojo);
        return RespResult.create().setContent(pageInfo);
    }

    /**
     * 我的债权转让列表-已受让 pc
     * @param input
     * @return
     */
    @RequestMapping("/compromisedList")
    @AccessToken(access = {AccessType.PC})
    public RespResult pageByCompromisedList(CreditAssignmentInput input) {
        PageInfo<CreditAssignmentVo> pageInfo = creditAssignmentService.pageByTransferee(input);
        return RespResult.create().setContent(pageInfo);
    }


    /**
     * 我的受让详情 产品信息 app
     * @param param
     * @return
     */
    @RequestMapping("/app/tender/product/info")
    @AccessToken(access = {AccessType.APP})
    public RespResult tenderInfoApp(TransfereeProductParam param) {
        TransfereeVo transfereeVo = borrowTenderService.tenderInfo(param.getTenderId());
        TransfereeAppVo transfereeAppVo = new TransfereeAppVo();
        TransferUtil.transfer(transfereeVo, transfereeAppVo);

        UserBackMoneyInput input = new UserBackMoneyInput();
        TransferUtil.transfer(param, input);
        List<BackMoneyPlanVo> planVoList = borrowCollectionService.getByTenderId(input);
        transfereeAppVo.setBackPlan(TransferUtil.transferList(planVoList, BackMoneyPlanAppVo.class));
        return RespResult.create().setContent(transfereeAppVo);
    }

    /**
     * 我的受让详情 产品信息 pc
     * @param param
     * @return
     */
    @RequestMapping("/tender/product/info")
    @AccessToken(access = {AccessType.PC})
    public RespResult tenderInfo(TransfereeProductParam param) {
        TransfereeVo transfereeVo = borrowTenderService.tenderInfo(param.getTenderId());
        UserBackMoneyInput input = new UserBackMoneyInput();
        input.setTenderId(param.getTenderId());
        PageInfo<BackMoneyPlanVo> planVoPageInfo = borrowCollectionService.pageByTenderId(input);
        return RespResult.create().setContent(transfereeVo);
    }

    /**
     * 我的受让详情 回款计划
     * @param param
     * @return
     */
    @RequestMapping("/tender/repayment/plan")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult repaymentPlan(TransfereeProductParam param) {
        UserBackMoneyInput input = new UserBackMoneyInput();
        input.setTenderId(param.getTenderId());
        PageInfo<BackMoneyPlanVo> planVoPageInfo = borrowCollectionService.pageByTenderId(input);
        return RespResult.create().setContent(planVoPageInfo);
    }

    /**
     * 我的债权转让信息
     * @param tenderId
     * @return
     */
    @RequestMapping("/info")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult info(String tenderId) {

        return RespResult.create();
    }


    /**
     * 债权申请 返回信息
     * @param param
     * @return
     */
    @RequestMapping("/preApply")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult preApply(TransferApplyParam param) {
        CreditAssignmentInfoVo creditAssignmentInfoVo = creditAssignmentService.info(param.getTenderId());
        return RespResult.create().setContent(creditAssignmentInfoVo);
    }

    /**
     * 债权申请
     * @param saveTransferParam
     * @return
     */
    @RequestMapping("/apply")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult apply(SaveTransferParam saveTransferParam) {
        creditAssignmentService.applyTransfer(saveTransferParam);
        return RespResult.create();
    }

    /**
     * 理财-债转列表查询
     * @param
     * @return
     */
    @RequestMapping("/query")
    public RespResult query(TransferQueryParam transferQueryParam) {

        TransferQueryInput input = new TransferQueryInput();
        input.setBeginPeriod(transferQueryParam.getBeginPeriod());
        input.setEndPeriod(transferQueryParam.getEndPeriod());
        PageInfo<BorrowTransferQueryOutput> pageInfo = creditAssignmentClient.query(input);


        List<TransferQueryVo> transferQueryVoList = TransferUtil.transferList(pageInfo.getList(), TransferQueryVo.class);
        PageInfo<TransferQueryVo> outPageInfo = new PageInfo<>();
        outPageInfo.setList(transferQueryVoList);
        outPageInfo.setTotal(pageInfo.getTotal());

        return RespResult.create().setContent(outPageInfo);
    }


    /**
     * 债转详情
     * @param transferQueryInfoParam
     * @return
     */
    @RequestMapping("/query/info")
    public RespResult queryInfo(TransferQueryInfoParam transferQueryInfoParam) {
        TransferVo transferQueryInfoVo = creditAssignmentService.queryInfo(transferQueryInfoParam);
        return RespResult.create().setContent(transferQueryInfoVo);
    }

    /**
     * 可转让详情
     * @param param
     * @return
     */
    @RequestMapping("/transferable/info")
    public RespResult queryInfo(TenderDetailParam param) {
        TransferableInfoVo transferQueryInfoVo = creditAssignmentService.queryTransferableInfo(param);
        return RespResult.create().setContent(transferQueryInfoVo);
    }

    /**
     * 转让中详情
     * @param transferQueryInfoParam
     * @return
     */
    @RequestMapping("/transfer/info")
    public RespResult queryTransferInfo(TransferQueryInfoParam transferQueryInfoParam) {
        TransferQueryInfoVo transferQueryInfoVo = creditAssignmentService.queryTransferInfo(transferQueryInfoParam);
        return RespResult.create().setContent(transferQueryInfoVo);
    }

    /**
     * 投资债权
     * @return
     */
    @RequestMapping("/tender")
    @AccessToken(access = {AccessType.PC, AccessType.APP})
    public RespResult tenderTransfer(TenderTransferParam param) {
        TenderTransferInfoVo tenderTransferInfoVo = creditAssignmentService.tenderTransfer(param);
        return RespResult.create().setContent(tenderTransferInfoVo);
    }

    /**
     * 投资债权
     * @return
     */
    @RequestMapping("/test")
    public RespResult test(BasePojo basePojo) {
        creditAssignmentClient.test(basePojo);
        return RespResult.create();
    }
}

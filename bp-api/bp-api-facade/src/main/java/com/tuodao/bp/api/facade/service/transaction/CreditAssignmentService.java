package com.tuodao.bp.api.facade.service.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.traningcenter.input.CreditAssignmentInput;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.model.facade.traningcenter.output.*;

/**
 * @author qingli.chen
 * @date 2017/9/19
 * @description 债权转让
 */
public interface CreditAssignmentService {

    /**
     * 债权转让申请 信息
     * @param tenderId 投资id
     * @return
     */
    CreditAssignmentInfoVo info(Integer tenderId);

    /**
     * 债权转让申请
     * @param saveTransferParam
     */
    void applyTransfer(SaveTransferParam saveTransferParam);

    /**
     * 可转让列表
     * @param input
     * @return
     */
    PageInfo<CreditAssignmentVo> pageByTransferableList(CreditAssignmentInput input);

    /**
     * 转让中、已转让列表
     * @param input
     * @return
     */
    PageInfo<CreditAssignmentVo> pageByTransferList(CreditAssignmentInput input);

    /**
     * 已受让列表 pc
     * @param input
     * @return
     */
    PageInfo<CreditAssignmentVo> pageByTransferee(CreditAssignmentInput input);

    /**
     * 我的已受让列表 app
     * @param pagePojo
     * @return
     */
    PageInfo<TransferCompromisedVo> pageByCompromised(PagePojo pagePojo);

    /**
     * 我的转让列表 app
     * @param pagePojo
     * @return
     */
    PageInfo<TransferCompromisedVo> pageByTransferLog(PagePojo pagePojo);

    /**
     * 我的可转让列表 app
     * @param pagePojo
     * @return
     */
    PageInfo<TransferableVo> pageByTransferable(PagePojo pagePojo);

    /**
     * 债权转让详情
     * @param transferQueryInfoParam
     * @return
     */
    TransferVo queryInfo(TransferQueryInfoParam transferQueryInfoParam);

    /**
     * 债权转让中详情
     * @param transferQueryInfoParam
     * @return
     */
    TransferQueryInfoVo queryTransferInfo(TransferQueryInfoParam transferQueryInfoParam);

    /**
     * 可转让详情
     * @param param
     * @return
     */
    TransferableInfoVo queryTransferableInfo(TenderDetailParam param);

    /**
     * 债权投标
     */
    TenderTransferInfoVo tenderTransfer(TenderTransferParam param);


}

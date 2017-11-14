package com.tuodao.bp.traningcenter.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.traningcenter.input.CreditAssignmentInput;
import com.tuodao.bp.model.business.traningcenter.input.TransferQueryInput;
import com.tuodao.bp.model.business.traningcenter.input.TransferTenderInput;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTransferOutput;
import com.tuodao.bp.model.business.traningcenter.output.CreditAssignmentOutput;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTransfer;

import java.util.List;


/**
 * @author qingli.chen
 * @date 2017/9/14
 * @description
 */
public interface CreditAssignmentService {

    /**
     * 可转让列表
     * @param creditAssignmentInput
     * @return
     */
    PageInfo<CreditAssignmentOutput> pageByTransferableList(CreditAssignmentInput creditAssignmentInput);

    /**
     * 转让中、已转让列表
     * @param creditAssignmentInput
     * @return
     */
    PageInfo<BorrowTransferOutput> pageByTransferList(CreditAssignmentInput creditAssignmentInput);

    /**
     * 已经受让
     * @param creditAssignmentInput
     * @return
     */
    PageInfo<BorrowTransferOutput> pageByTransferee(CreditAssignmentInput creditAssignmentInput);

    /**
     * 查询债权转让
     * @param tenderId 投标id
     * @param userId 用户id
     * @param statusList 状态列表
     * @return
     */
    BorrowTransfer findByTenderId(Integer tenderId, String userId, List<Integer> statusList);

    /**
     * 查询债权转让
     * @param tenderId 投标id
     * @return
     */
    BorrowTransfer findByTenderId(Integer tenderId);

    /**
     * 查询债权转让
     * @param id
     * @return
     */
    BorrowTransfer findById(Integer id);

    /**
     * 分页查询债权转让
     * @param input
     * @return
     */
    PageInfo<BorrowTransfer> findByStatus(TransferQueryInput input);

    /**
     * 保存
     * @param borrowTransfer
     */
    void save(BorrowTransfer borrowTransfer);


    /**
     * 投标
     * @param input
     */
    BorrowTender tender(TransferTenderInput input);

    /**
     * 回滚冻结
     * @param orderNo
     */
    void rollbackFreeze(String orderNo);

    /**
     * 解冻
     */
    void unFreeze();

    /**
     * 验证是否全部解冻
     * 如果全部解冻，更新缓存
     * @param orderNo 订单号
     */
    void validateUnFreeze(String orderNo);

    /**
     * 查询满标未复审
     * @return
     */
    List<BorrowTransfer> findFullAndNotReview();

    /**
     * 满标复审, 转让
     */
    void fullReview();

    /**
     * 撤销债转
     * @param borrowId 标的id
     * @param borrowTransfer 债转对象
     */
    void revokedTransfer(Integer borrowId, BorrowTransfer borrowTransfer);

    /**
     * 撤销债转
     */
    void revokedTransfer();

    /**
     * 满标复审
     * @param transferId
     */
    void modifyCollection(Integer transferId);

    void test();
}

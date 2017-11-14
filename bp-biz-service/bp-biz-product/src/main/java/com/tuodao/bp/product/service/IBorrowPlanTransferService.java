package com.tuodao.bp.product.service;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.input.BorrowPlanTransferInput;
import com.tuodao.bp.model.business.product.input.TransferInput;
import com.tuodao.bp.model.business.product.output.BorrowPlanTransferOutput;
import com.tuodao.bp.model.mq.ProductUpdateMqInfo;
import com.tuodao.bp.product.db.model.basic.BorrowCollection;
import com.tuodao.bp.result.exception.BizFeignException;

import java.math.BigDecimal;
import java.util.List;

public interface IBorrowPlanTransferService {
   /**
     * com.tuodao.bp.product.service.BorrowPlanTransferService#
     * @参数
     * @方法描述: 债转表列表-带分页
     * @作者 lujing
     * @时间 2017/9/14 0014
     * @拓道金服 Copyright (c) 2017
     * ======================修改==================
     * @修改者
     * @修改内容: TODO
     * @修改时间 yyyy-MM-dd HH:mm:ss
     * ============================================
     **/
    PageInfo<BorrowPlanTransferOutput> getBorrowPlanTransferPage(BorrowPlanTransferInput input);

    /**
      * com.tuodao.bp.product.service.BorrowPlanTransferService#
      * @参数
      * @方法描述: 债转标列表-无分页
      * @作者 lujing
      * @时间 2017/9/14 0014
      * @拓道金服 Copyright (c) 2017
      * ======================修改==================
      * @修改者
      * @修改内容: TODO
      * @修改时间 yyyy-MM-dd HH:mm:ss
      * ============================================
      **/
    List<BorrowPlanTransferOutput> getBorrowPlanTransferList(BorrowPlanTransferInput input);


    Boolean updateTransferStatus(ProductUpdateMqInfo mqInfo);

    /**
     * 债权 重置
     * @param collections
     * @return
     * @throws BizFeignException
     */
    Boolean refreshTransfer(List<TransferInput> collections) throws BizFeignException;

   /**
    * 查询超过48小时的理财计划债权 用于内部账号购买
    * @return
    */
   List<BorrowPlanTransferOutput> getOvertimeTransferList();

    boolean updateTransferAmount(Integer transferId, BigDecimal accountYes);

    BorrowPlanTransferOutput getBorrowPlanTransfer(Integer id);

    /**
     * 查询理财计划下需要复审转让标的
     */
    List<BorrowPlanTransferOutput> getPlanReverifyTransferList();
}

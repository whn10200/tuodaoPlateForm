package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.until.BorrowPlan;

import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/12 0012.
 * @time: 16:24
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface BorrowChicenessService {

    void doOwnPlanTransfer(OwnTransferInput ownTransferInput);

    void reverifyPlanNomal(PlanNomalListInput inputs);

    void reverifyDevelop(PlanDevelopListInput inputs);

    /**
     * 该理财计划复审
     * @param reverifyPlanInput
     * @return
     */
   void planReverify(ReverifyPlanInput reverifyPlanInput);
}

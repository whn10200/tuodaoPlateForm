package com.tuodao.bp.api.facade.service.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.JoinReturnContent;
import com.tuodao.bp.model.business.traningcenter.input.JoinPlanInput;
import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.model.business.traningcenter.input.SelectTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderTraRecordInput;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.model.facade.traningcenter.input.TenderParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/10/11 0011.
 */
public interface JoinPlanService {

    JoinReturnContent joinPlan(TenderParam param,HttpServletRequest request,String key);

    PageInfo<TenderRecord> getJoinLists(TenderTraRecordInput tenderTrRecordInput);

    PageInfo<SelectTenderOutput> getTenderByUserId(SelectTenderInput tenderInput);

    TenderDetailsOutput selectTenderById(JustIdInput justIdInput);

    PageInfo<UnderTenderOutput> selectTenderListByChoicenessTenderId(JustIdInput justIdInput);

    PageInfo<RecoverListOutput> selectRecoverListBychioId(JustIdInput justIdInput);

    TenderTraRecordOutput getMaxAndLast(TenderTraRecordInput tenderTrRecordInput);
}

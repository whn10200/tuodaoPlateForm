package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:理财计划下普通标的的投资
 * @author: wuzf
 * @date: 2017/10/18
 * @time: 14:48
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface OrginalBorrowTenderService {


    void tenderProducer(TenderExecutor executor);

    void tenderConsumer(TenderExecutor executor);

    void tenderFail(BorrowTender tender,String errorMsg,boolean isSend);

    void tenderSuccess(ProductOutput product,BorrowTenderOutput borrowTender,int choicenessTenderId);
}

package com.tuodao.bp.traningcenter.service;

import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowChoicenessTenderMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowChoicenessTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @description:理财计划下转让标的的投资
 * @author: 吴张峰
 * @date: 2017/10/18
 * @time: 14:48
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public interface DevelopTenderService {

    void tenderSuccess(ProductOutput product,BorrowTenderOutput borrowTender,int choicenessTenderId);


    void tenderConsumer(TenderExecutor executor);
}

package com.tuodao.bp.traningcenter.service.impl;

import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.model.ProjectInfoCacheInfo;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.constant.traningcenter.BankConstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowChoicenessTenderMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowChoicenessTender;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import com.tuodao.bp.traningcenter.service.DevelopTenderService;
import com.tuodao.bp.traningcenter.service.OrginalBorrowTenderService;
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
public class DevelopTenderServiceImpl implements DevelopTenderService{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrginalBorrowTenderService.class);

    @Autowired
    private ProducerMq producerMQ;
    @Autowired
    private BorrowTenderService borrowTenderService;
    @Autowired
    private BorrowChoicenessTenderMapper borrowChoicenessTenderMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tenderConsumer(TenderExecutor executor) {
        LOGGER.debug("投标队列消费者,入参:{}", executor);
        try{
//            verifyBorrow(executor.getProduct(),executor.getTenderMoney());
            //加入预投资
            BorrowTender borrowTender = borrowTenderService.insertBorrowTender(executor);
            LOGGER.debug("投标处理成功");
            BorrowTenderOutput output = transfer(borrowTender);
            this.tenderSuccess(executor.getProduct(),output,executor.getChoicenessTenderId());

        }catch (Exception e){
            if(e instanceof BizFeignException) {
                LOGGER.error("投标队列消费者异常:{}",e.getMessage());
            }else{
                throw e;
            }
        }
    }

    /**
     * 对象转换
     * @param tender
     * @return
     */
    public BorrowTenderOutput transfer(BorrowTender tender){
        BorrowTenderOutput output = new BorrowTenderOutput();
        BeanUtils.copyProperties(tender, output);
        return output;
    }

    /**
     * 投标后置处理,更新投标状态,更新收益等
     * @param product 标的信息
     * @param borrowTender 投标信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void tenderSuccess(ProductOutput product,BorrowTenderOutput borrowTender,int choicenessTenderId){
        LOGGER.debug("投标后置处理,标的信息:{},投标信息:{}", product, borrowTender);
        //更新投标状态,计算预计收益等
        borrowTenderService.updateBorrowTenderStatus(borrowTender.getId(), TenderConstant.TENDER_SUCCESS);
        //更具choicenessTenderId查询精选计划记录
        BorrowChoicenessTender borrowChoicenessTender=borrowChoicenessTenderMapper.selectByPrimaryKey(choicenessTenderId);
        borrowChoicenessTender.setAccount(borrowChoicenessTender.getAccount().add(borrowTender.getAccount()));
        borrowChoicenessTender.setUpdateTime(new Date());
        borrowChoicenessTenderMapper.updateByPrimaryKey(borrowChoicenessTender);
        producerMQ.updateProductResidueBalanceMQ(Integer.parseInt(product.getBusinessId()), borrowTender.getAccount());
        //TODO 发送短信通知
    }
}

package com.tuodao.bp.traningcenter.service.impl;

import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.model.ProjectInfoCacheInfo;
import com.tuodao.bp.model.ReturnCacheInfo;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.constant.product.ProductConstant;
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
import com.tuodao.bp.traningcenter.service.OrginalBorrowTenderService;
import com.tuodao.bp.utils.BigDecimalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @description:理财计划下普通标的的投资
 * @author: 吴张峰
 * @date: 2017/10/18
 * @time: 14:48
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class OrginalBorrowTenderServiceImpl implements OrginalBorrowTenderService{

    private static final Logger LOGGER = LoggerFactory.getLogger(OrginalBorrowTenderService.class);

    @Autowired
    private ProducerMq producerMQ;
    @Autowired
    private BorrowTenderService borrowTenderService;
    @Autowired
    private BorrowTenderMapper borrowTenderMapper;
    @Autowired
    private BorrowChoicenessTenderMapper borrowChoicenessTenderMapper;
    @Autowired
    private ProjectInfoCache projectInfoCache;


    @Override
    public void tenderProducer(TenderExecutor executor) {
        if(executor.getTenderType()==2)
        {
            producerMQ.orginalTender(executor);
        }
        else if(executor.getTenderType()==3)
        {
            producerMQ.developTender(executor);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tenderConsumer(TenderExecutor executor) {
        LOGGER.debug("投标队列消费者,入参:{}", executor);
        try{
            borrowTenderService.verifyBorrow(executor.getProduct().getId(),executor.getTenderMoney());
            //加入预投资
            BorrowTender borrowTender = borrowTenderService.insertBorrowTender(executor);
            //投标请求银行
            producerMQ.tenderBankRequest(executor.getProduct(), transfer(borrowTender), executor.getUser());

        }catch (Exception e){
            if(e instanceof BizFeignException) {
                LOGGER.error("投标队列消费者异常:{}",e.getMessage());
            }else{
                throw e;
            }
        }
    }


    /**
     * 投标失败处理.如果投标时间过长而用户等待时间较短,用户看不到投标结果,需要短信通知失败 如果投标时间果断 则用户可以直接查询到投标结果 不需要通知
     * @param tender 投标信息
     * @param errorMsg   失败原因
     * @param isSend 是否发送短信
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tenderFail(BorrowTender tender,String errorMsg,boolean isSend) {

        tender.setRemarks(errorMsg);
        tender.setStatus(TenderConstant.TENDER_FAIL);
        tender.setUpdateTime(new Date());

        borrowTenderMapper.updateByPrimaryKeySelective(tender);//更新投标记录为失败

        //TODO 短信通知
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
        //更新投标状态
        borrowTenderService.updateBorrowTenderStatus(borrowTender.getId(),TenderConstant.TENDER_SUCCESS);
        //更新缓存信息
        borrowTenderService.updateProductResidue(product.getId(), borrowTender.getPreAccount());
        //TODO 加入表需改匹配资金
        //更具choicenessTenderId查询精选计划记录
        BorrowChoicenessTender borrowChoicenessTender=borrowChoicenessTenderMapper.selectByPrimaryKey(choicenessTenderId);
        borrowChoicenessTender.setAccount(borrowChoicenessTender.getAccount().add(borrowTender.getAccount()));
        borrowChoicenessTender.setUpdateTime(new Date());
        borrowChoicenessTenderMapper.updateByPrimaryKey(borrowChoicenessTender);
        //TODO 发送短信通知
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


}

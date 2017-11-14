package com.tuodao.bp.traningcenter.mq.consumer;

import com.tuodao.bp.activemq.constant.MqContstant;
import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.model.ProjectInfoCacheInfo;
import com.tuodao.bp.model.ReturnCacheInfo;
import com.tuodao.bp.model.business.traningcenter.input.ChoicenessTenderInput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.traningcenter.constant.TraningCenterExceptionConstant;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.BorrowChicenessTenderService;
import com.tuodao.bp.utils.ArithUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.math.BigDecimal;

/**
 * @description:加入理财计划的mq
 * @author: wuzf
 * @date: 2017/10/10 0027.
 * @time: 11:12
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class JoinChoicenessMq {

    private static final Logger logger = LoggerFactory.getLogger(JoinChoicenessMq.class);

    @Resource
    private ProducerMq producerMq;
    @Resource
    private ProjectInfoCache projectInfoCache;
    @Resource
    private ReturnsCache returnsCache;
    @Resource
    private BorrowChicenessTenderService borrowChicenessTenderService;
    @Resource
    private ProducerMq producerMQ;

    /**
     * 加入理财计划消费者
     * @param choicenessTenderInput
     */
    @JmsListener(destination= MqContstant.TRANING_JOIN_CHOISE_QUEUE)
    public void joinChoicenessMq(ChoicenessTenderInput choicenessTenderInput) {
        ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
        returnCacheInfo.setKey(choicenessTenderInput.getKey());
        //加息劵id
        Integer dikId=null;
        try
        {
            if(choicenessTenderInput.getVoucherId()!=null)
            {
                dikId = choicenessTenderInput.getVoucherId();
                returnCacheInfo.setVoucherType(2);
                returnCacheInfo.setResidueAmount(choicenessTenderInput.getVoucherApr()+"%");
            }
            else if(choicenessTenderInput.getVoucherCouponId()!=null)
            {
                dikId = choicenessTenderInput.getVoucherCouponId();
                returnCacheInfo.setVoucherType(1);
                returnCacheInfo.setResidueAmount(choicenessTenderInput.getDkamount());
            }
            else
            {
                returnCacheInfo.setVoucherType(0);
            }
            ProjectInfoCacheInfo projectInfoCacheInfo = projectInfoCache.getProjectInfo(choicenessTenderInput.getBorrowId());
            if(projectInfoCacheInfo==null)
            {
                projectInfoCacheInfo = new ProjectInfoCacheInfo();
                projectInfoCacheInfo.setProjectId(choicenessTenderInput.getBorrowId());
                projectInfoCacheInfo.setLeftAccount(new BigDecimal(1000000));
                projectInfoCache.putProjectInfo(projectInfoCacheInfo);
            }
            int r=projectInfoCacheInfo.getLeftAccount().compareTo(BigDecimal.ZERO);

            if(r==1) //大于
            {
                //获取投资金额
                int j=projectInfoCacheInfo.getLeftAccount().compareTo(choicenessTenderInput.getPreAccount());
                if(j==1)
                {
                    choicenessTenderInput.setPreAccount(choicenessTenderInput.getPreAccount());
                }
                else
                {
                    choicenessTenderInput.setPreAccount(projectInfoCacheInfo.getLeftAccount());
                }
                if(choicenessTenderInput.getMoneyLimit()!=null)
                {
                    int limit=choicenessTenderInput.getPreAccount().compareTo(choicenessTenderInput.getMoneyLimit());
                    if(limit==-1)
                    {
                        //抛出投标金额不满足优惠券使用额度
                        returnCacheInfo.setStatus(2);
                        returnCacheInfo.setCode(TransactError.VOUCHER_MONEY_ERROR.getCode());
                        returnsCache.putReturnInfo(returnCacheInfo);
                        throw new MicroServiceException(TransactError.VOUCHER_MONEY_ERROR);
                    }
                }

                BigDecimal preAccountIntrest=borrowChicenessTenderService.insertChoiceness(choicenessTenderInput, choicenessTenderInput.getIp());
                returnCacheInfo.setStatus(1);
                returnCacheInfo.setPreInterest(ArithUtils.toYuan(preAccountIntrest).toString());
                returnCacheInfo.setAmount(ArithUtils.toYuan(choicenessTenderInput.getPreAccount()).toString());
                returnsCache.putReturnInfo(returnCacheInfo);

                //更新该产品剩余金额缓存
                projectInfoCacheInfo.setProjectId(choicenessTenderInput.getBorrowId());
                projectInfoCacheInfo.setLeftAccount(projectInfoCacheInfo.getLeftAccount().subtract(choicenessTenderInput.getPreAccount()));
                projectInfoCache.putProjectInfo(projectInfoCacheInfo);
                //成功需修改产品表 creatproductUpdateMqInfo
                producerMQ.updateProductResidueBalanceMQ(choicenessTenderInput.getBorrowId(),choicenessTenderInput.getPreAccount());
                //加入理财计划短信的发送
                logger.info("加入理财计划成功  choicenessTenderInput{}",choicenessTenderInput);
            }
            else
            {
                returnCacheInfo.setStatus(2);
                returnCacheInfo.setCode(TraningCenterExceptionConstant.PROJECT_FULL);
                returnsCache.putReturnInfo(returnCacheInfo);
                //失败需修改优惠券表
                if(dikId!=null) {
                    producerMq.updateVoucherStatus(dikId,1,choicenessTenderInput.getUserId(),null);
                }
                throw new BizFeignException(TraningCenterExceptionConstant.PROJECT_FULL);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            //失败需修改优惠券表
            returnCacheInfo.setStatus(2);
            returnCacheInfo.setCode(TraningCenterExceptionConstant.JOIN_LC_FARIL);
            if(dikId!=null)
            {
                producerMq.updateVoucherStatus(dikId,1,choicenessTenderInput.getUserId(),null);
            }
            returnsCache.putReturnInfo(returnCacheInfo);
            logger.error("/borrowChicenessTender/insertChoiceness  down{}",e);
            throw new BizFeignException(TraningCenterExceptionConstant.JOIN_LC_FARIL);
        }
    }
}

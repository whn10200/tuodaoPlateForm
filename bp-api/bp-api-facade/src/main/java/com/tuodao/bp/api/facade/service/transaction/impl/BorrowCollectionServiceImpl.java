package com.tuodao.bp.api.facade.service.transaction.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.*;
import com.tuodao.bp.api.facade.client.useraccount.AccountClient;
import com.tuodao.bp.model.business.UserIdListInput;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.useraccount.UserDepositNo;
import com.tuodao.bp.model.constant.product.ProductConstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.facade.traningcenter.input.CollectionParam;
import com.tuodao.bp.model.facade.traningcenter.output.CollectionVo;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.TransferUtil;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowCollectionClient;
import com.tuodao.bp.model.traningcenter.input.Repayment;
import com.tuodao.bp.api.facade.service.transaction.BorrowCollectionService;
import com.tuodao.bp.model.business.traningcenter.input.UserBackMoneyInput;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;
import com.tuodao.bp.model.facade.traningcenter.output.BackMoneyPlanVo;
import com.tuodao.bp.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author qingli.chen
 * @date 2017/9/19
 * @description 回款service
 */
@Service
public class BorrowCollectionServiceImpl implements BorrowCollectionService {

    @Autowired
    private BorrowCollectionClient borrowCollectionClient;

    @Autowired
    ProductClient productClient;

    @Autowired
    private AccountClient accountClient;

    @Override
    public PageInfo<BackMoneyPlanVo> pageByTenderId(UserBackMoneyInput input) {
        PageInfo<BackMoneyPlanOutput> backMoneyPlanPageInfo = borrowCollectionClient.getRecoveredByTenderId(input);

        PageInfo<BackMoneyPlanVo> backMoneyPlanVoPageInfo = new PageInfo<>();
        List<BackMoneyPlanOutput> backMoneyPlanList = backMoneyPlanPageInfo.getList();
        if(CollectionUtils.isEmpty(backMoneyPlanList)) {
            return backMoneyPlanVoPageInfo;
        }

        List<BackMoneyPlanVo> backMoneyPlanVoList = convertToPlanVo(backMoneyPlanList);

        backMoneyPlanVoPageInfo.setList(backMoneyPlanVoList);
        backMoneyPlanVoPageInfo.setTotal(backMoneyPlanPageInfo.getTotal());
        return backMoneyPlanVoPageInfo;
    }

    @Override
    public List<BackMoneyPlanVo> getByTenderId(UserBackMoneyInput input) {
        List<BackMoneyPlanOutput> backMoneyPlanList = borrowCollectionClient.getByTenderId(input);

        List<BackMoneyPlanVo> backMoneyPlanVoList = convertToPlanVo(backMoneyPlanList);
        return backMoneyPlanVoList;
    }

    private List<BackMoneyPlanVo> convertToPlanVo(List<BackMoneyPlanOutput> backMoneyPlanList) {
        List<BackMoneyPlanVo> backMoneyPlanVoList = new ArrayList<>();
        backMoneyPlanList.forEach(backMoneyPlanOutput -> {
            BackMoneyPlanVo vo = new BackMoneyPlanVo();
            TransferUtil.transfer(backMoneyPlanOutput, vo);
            vo.setCollectionTime(DateUtil.formatShort(backMoneyPlanOutput.getCollectionTime()));
            vo.setInterest(BigDecimalUtils.centToYuanFormat(backMoneyPlanOutput.getInterest()));
            vo.setCapital(BigDecimalUtils.centToYuanFormat(backMoneyPlanOutput.getCapital()));
            vo.setStatus(backMoneyPlanOutput.getStatusMsg());
            backMoneyPlanVoList.add(vo);
        });
        return backMoneyPlanVoList;
    }


    @Override
    public PageInfo<CollectionVo> getCollectionCalendarByPage(CollectionParam param) {
        PageInfo<BackMoneyPlanOutput> pageInfo = borrowCollectionClient.getCollectionCalendarByPage(param);

        PageInfo<CollectionVo> paging = new PageInfo<>();

        if (CollectionUtils.isEmpty(pageInfo.getList())){
            return paging;
        }
        List<Integer> productIdsList = FluentIterable.from(pageInfo.getList()).transform(input -> input.getBorrowId()).toList();

        final List<ProductOutput> list = productClient.getListByIdList(productIdsList);

        List<CollectionVo> voList = FluentIterable.from(pageInfo.getList()).transform(input -> {
            for(ProductOutput product : list){
                if(product.getId().intValue() == input.getBorrowId().intValue()){
                    CollectionVo vo = new CollectionVo();
                    vo.setPeriod(input.getPeriod());
                    vo.setPeriods(input.getPeriods());
                    vo.setProductTitile(product.getProductTitle());
                    vo.setProductType(product.getProductType());
                    //未还款
                    if(input.getStatus() == TenderConstant.COLLECTION_STATUS_0){
                        //预计本金+预计利息
                        vo.setCollectionMoney(BigDecimalUtils.centToYuanFormat(input.getPreInterest().add(input.getPreCapital()).doubleValue()));
                        vo.setCollectionTime(DateUtil.formatShort(input.getPreCollectionTime()));
                    }else{//已转让查询时已经过滤 该处无需else if
                        vo.setCollectionMoney(BigDecimalUtils.centToYuanFormat(input.getInterest().add(input.getCapital()).doubleValue()));
                        vo.setCollectionTime(DateUtil.formatShort(input.getCollectionTime()));
                    }
                    vo.setStatus(input.getStatus());
                    vo.setProductId(product.getId());
                    //回款类型 等额本息与精选计划均为本息 ,按月付息还款期数等于总期数默认最后一起还款
                    if (product.getRefundWay() == ProductConstant.REFUND_WAY
                            || product.getProductType() == ProductConstant.PRODUCT_TYPE_1
                            || input.getPeriod().intValue() == input.getPeriods().intValue()){
                        vo.setType(0);
                    }else{
                        vo.setType(1);
                    }
                    return vo;
                }
            }
            return null;
        }).toList();

        paging.setList(voList);
        paging.setTotal(pageInfo.getTotal());

        return paging;
    }





}

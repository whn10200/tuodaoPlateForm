package com.tuodao.bp.traningcenter.service.impl;

import com.tuodao.bp.model.business.traningcenter.input.BorrowRepaymentInput;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowRepaymentMapper;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowRepayment;
import com.tuodao.bp.traningcenter.service.BorrowRepaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/19
 * @time: 15:04
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("borrowRepaymentService")
public class BorrowRepaymentServiceImpl implements BorrowRepaymentService {

    @Autowired
    private BorrowRepaymentMapper borrowRepaymentMapper;


    @Override
    public int insertBorrowRepayment(List<BorrowRepaymentInput> list) {
        if(list != null && list.size() > 0){
            List<BorrowRepayment> repaymentList = new ArrayList<>();
            BorrowRepayment borrowRepayment;
            for(BorrowRepaymentInput input :list){
                borrowRepayment = new BorrowRepayment();
                BeanUtils.copyProperties(input,borrowRepayment);
                borrowRepayment.setStatus(TenderConstant.REPAYMENT_STATUS);//默认状态0:未还款
                borrowRepayment.setFee(BigDecimal.valueOf(input.getFee()));//手续费
                borrowRepayment.setPreInterest(BigDecimal.valueOf(input.getPreInterest()));//本期还款利息
                borrowRepayment.setPreCapital(BigDecimal.valueOf(input.getPreCapital())); //本期还款本金
                repaymentList.add(borrowRepayment);
            }
            return borrowRepaymentMapper.insertBatch(repaymentList);
        }
        return 0;
    }

    @Override
    public Date getLastRepayTime(Integer borrowId) {
        List<BorrowRepayment> list = borrowRepaymentMapper.selectByBorrowId(borrowId);
        if(!CollectionUtils.isEmpty(list)) {
            BorrowRepayment borrowRepayment = list.get(0);
            return borrowRepayment.getPreRepayTime();
        }
        return null;
    }

    @Override
    public BigDecimal getEarliestInterest(Integer borrowId) {
        List<BorrowRepayment> list = borrowRepaymentMapper.selectByBorrowId(borrowId);
        if(!CollectionUtils.isEmpty(list)) {
            BorrowRepayment borrowRepayment = list.get(0);
            return borrowRepayment.getPreInterest();
        }
        return BigDecimal.ZERO;
    }
}

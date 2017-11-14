package com.tuodao.bp.operation.service;

import com.tuodao.bp.model.business.operation.output.TenderVoucherOutput;

import java.util.List;
import java.util.Map;

/**
 * 用户投资-使用优惠券 SERVICE
 * author hechuan
 * <p>
 * created on 2017/10/18
 * <p>
 * since V1.0.0
 */
public interface IUserTenderService {

    /**
     * 用户投资-使用优惠券
     * @param inputList
     * @return
     */
    Map<String,TenderVoucherOutput> getUserTender(List<Map<String,Integer>> inputList);
}

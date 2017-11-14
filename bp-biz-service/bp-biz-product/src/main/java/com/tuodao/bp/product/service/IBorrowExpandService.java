package com.tuodao.bp.product.service;

import com.tuodao.bp.model.business.product.output.BorrowExpandOutput;

/**
 * @Author wuchengjie
 * @Date 2017/9/1 0001 16:57
 * @Introduction 产品的还款功能
 */
public interface IBorrowExpandService {

    /**
     * 获取扩展数据
     * @return
     */

    BorrowExpandOutput getBorrowExpandByPcode(String pcode);

    BorrowExpandOutput getBorrowExpandById(Integer id);
}

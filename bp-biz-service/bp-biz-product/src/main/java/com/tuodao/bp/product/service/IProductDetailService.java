package com.tuodao.bp.product.service;

import com.tuodao.bp.model.business.product.input.ProductDetailQueryInput;
import com.tuodao.bp.model.business.product.output.ProductDetailOutput;
import com.tuodao.bp.model.mq.ProductTenderMqInfo;
import com.tuodao.bp.product.db.model.basic.ProductDetails;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/9/27 0001 16:57
 * @Introduction
 */
public interface IProductDetailService {

    /**
     * 获取 产品
     * @param input 查询条件
     * @return
     */
    public List<ProductDetails> getProductDetail(ProductDetailQueryInput input);


    List<ProductDetailOutput> getBorrowList(Integer productId);

    Boolean mergeProductDetailByMqInfo(ProductTenderMqInfo mqInfo);

    Boolean withdrawDetailByBorrowId(Integer borrowId);
}

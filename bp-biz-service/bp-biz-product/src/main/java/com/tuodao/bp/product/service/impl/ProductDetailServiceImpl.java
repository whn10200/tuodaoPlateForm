package com.tuodao.bp.product.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.tuodao.bp.model.business.product.input.ProductDetailQueryInput;
import com.tuodao.bp.model.business.product.output.ProductDetailOutput;
import com.tuodao.bp.model.mq.ProductTenderMqInfo;
import com.tuodao.bp.product.constants.ProductDetailsExceptionConstant;
import com.tuodao.bp.product.constants.ProductResponseExceptionConstant;
import com.tuodao.bp.product.db.mapper.basic.BorrowPlanTransferMapper;
import com.tuodao.bp.product.db.mapper.basic.ProductDetailsMapper;
import com.tuodao.bp.product.db.mapper.basic.ProductMapper;
import com.tuodao.bp.product.db.model.basic.BorrowPlanTransfer;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.product.db.model.basic.ProductDetails;
import com.tuodao.bp.product.db.model.basic.ProductDetailsExample;
import com.tuodao.bp.product.service.IProductDetailService;
import com.tuodao.bp.result.exception.BizFeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/9/27 0001 16:57
 * @Introduction
 */

@Service
@Transactional
public class ProductDetailServiceImpl implements IProductDetailService {

    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(ProductDetailServiceImpl.class);


    @Autowired
    private ProductDetailsMapper productDetailsMapper;

    @Autowired
    private ProductMapper productMapper;


    @Autowired
    private BorrowPlanTransferMapper borrowPlanTransferMapper;
    /**
     * 获取 产品
     * @param input 查询条件
     * @return
     */
    @Override
    public List<ProductDetails> getProductDetail(ProductDetailQueryInput input)
    {
        ProductDetailsExample example = new ProductDetailsExample();
        example.createCriteria().andProductIdEqualTo(input.getProductId());

        List<ProductDetails> list = productDetailsMapper.selectByExample(example);


        return list;

    }


    /**
     * 获取理财产品中的 底层标的
     * @param productId 查询条件
     * @return
     */
    @Override
    public List<ProductDetailOutput> getBorrowList(Integer productId)
    {
        ProductDetailsExample example = new ProductDetailsExample();
        example.createCriteria().andProductIdEqualTo(productId)
        //'标的类型  0：散标 2：债权',
        .andBorrowTypeEqualTo(0);

        List<ProductDetails> list = productDetailsMapper.selectByExample(example);

        ImmutableList<ProductDetailOutput> resultList = FluentIterable.<ProductDetails>from(list)
                .transform(new Function<ProductDetails, ProductDetailOutput>() {
            @Override
            public ProductDetailOutput apply(ProductDetails input) {
                ProductDetailOutput out = new ProductDetailOutput();
                BeanUtils.copyProperties(input, out);
                return out;
            }
        }).toList();

        return resultList;

    }


    /**
     * insert or update productDetail infomation
     */
    @Override
    public Boolean mergeProductDetailByMqInfo(ProductTenderMqInfo mqInfo)
    {

        Integer borrowId = mqInfo.getBorrowId();
        Integer productId = mqInfo.getProductId();
        Integer borrowType = mqInfo.getType();
        BigDecimal amount = mqInfo.getTenderAmount();

        borrowType = borrowType == 1?0: borrowType;

        ProductDetailsExample example = new ProductDetailsExample();
        example.createCriteria().andBorrowIdEqualTo(borrowId).andProductIdEqualTo(productId)
                .andBorrowTypeEqualTo(borrowType);

        List<ProductDetails> details =  productDetailsMapper.selectByExample(example);

        ProductDetails  detail= null;

      //  BigDecimal productTotalAmount = null;

        //判断这个产品是否被包含进去
        if(CollectionUtils.isEmpty(details)){

        //没有就进行new
            detail = new ProductDetails();
            detail.setBorrowAmount(amount);
            detail.setAddTime(new Date());
            detail.setBorrowId(borrowId);
            detail.setProductId(productId);
            detail.setBorrowType(borrowType);
            detail.setStatus(2);


            if(borrowType == 0 ){
              Product product =  productMapper.selectByPrimaryKey(borrowId);
              detail.setOrginalId(product.getId());
              detail.setBorrowTitle(product.getProductTitle());
            // productTotalAmount  = product.getBorrowAmount();

            }else if(borrowType == 2){
                BorrowPlanTransfer borrowPlanTransfer =  borrowPlanTransferMapper.selectByPrimaryKey(borrowId);
                detail.setOrginalId(borrowPlanTransfer.getPreBorrowId());
                detail.setBorrowTitle(borrowPlanTransfer.getBorrowName());
                //productTotalAmount  = detail.getBorrowAmount();
            }else {
                throw new BizFeignException(ProductResponseExceptionConstant.BORROW_DETIAL_UNKONW_BORROW_TYPE);
            }
//            //简单的判断 详细表中的钱 不能大于来源表中的钱
//            if(productTotalAmount.compareTo(detail.getBorrowAmount())<0){
//                throw new BizFeignException(ProductBizExceptionConstant.BORROW_DETIAL_INCORRECT_AMOUNT);
//            }
            productDetailsMapper.insert(detail);

        }else if(details.size()>1){
            throw new BizFeignException(ProductResponseExceptionConstant.BORROW_DETIAL_ONLY_ONE);
        }else{
            //有就直接加
            detail = details.get(0);
            detail.setBorrowAmount( detail.getBorrowAmount().add(amount));
            productDetailsMapper.updateByPrimaryKey(detail);
        }

        return true;
    }

    /**
     * 修改理财计划详细信息的状态
     */
    private Boolean updateDetailStatus(Integer borrowId,Integer borrowType,Integer status )
    {

        try {
            ProductDetailsExample example= new ProductDetailsExample();
            ProductDetailsExample.Criteria cnd= example.createCriteria();
            cnd.andBorrowIdEqualTo(borrowId).andBorrowTypeEqualTo(borrowType);
            ProductDetails details = new   ProductDetails();
            details.setStatus(status);

            productDetailsMapper.updateByExampleSelective(details,example);
        } catch (Exception e) {
            logger.error(" 修改理财计划详细信息的状态失败 ：borrowId{} borrowType{},status{}",borrowId,borrowType,status);
            throw new BizFeignException(ProductDetailsExceptionConstant.UPDATE_FAIL);
        }

        return true;
    }


    /**
     * 撤销理财计划匹配到的债权detial
     * @param borrowId
     * @return
     */
    @Override
    public Boolean withdrawDetailByBorrowId(Integer borrowId)
    {
       return updateDetailStatus(borrowId,0,3);
    }
}

package com.tuodao.bp.product.utils;

import com.tuodao.bp.product.constants.ProductBizExceptionConstant;
import com.tuodao.bp.product.constants.ProductConstant;
import com.tuodao.bp.product.constants.ProductResponseExceptionConstant;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.result.exception.BizFeignException;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * @Author wuchengjie
 * @Date 2017/9/7 0007 11:25
 * @Introduction 检查产品 工具类
 */
public class CheckProductUtil {


    /**
     * 检查产品是否符合加入理财要求
     * @param product
     */
    public static void checkJoin(Product product){
        //理财计划不能为空
        if(product == null){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_IS_NULL);
        }
        // 产品必须为理财计划
        if(product.getProductType() != 1){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_MUST_FEATURED);
        }

        // 产品必须为募集中
        if(product.getProductStatus() != ProductConstant.PRODUCT_STATUS_5){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_STATUS_INCORRECT);
        }
    }


    /**
     * 检查产品是否符合是否债权要求
     * @param product
     */
    public static void checkProductDebts(Product product){
        //理财计划不能为空
        if(product == null){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_IS_NULL);
        }
        // 产品必须为理财计划
        if(product.getProductType() != 1){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_MUST_FEATURED);
        }

        // 产品必须为还款中
        if(product.getProductStatus() != ProductConstant.PRODUCT_STATUS_7){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_STATUS_INCORRECT);
        }
    }


    /**
     * 检查产品是否符合发标的要求
     * @param product
     */
    public static void checkIssuance(Product product){
        //不能为空
        if(product == null){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_IS_NULL);
        }
        // 产品必须为散标
        if(product.getProductType() != 0){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_MUST_BORROW);
        }
        // 产品必须为4：待发布
        if(product.getProductStatus() != ProductConstant.PRODUCT_STATUS_4 && product.getBorrowFullStatus() != 0){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_STATUS_INCORRECT);
        }
        //标的如果定时 定时时间必须大于当前 15分钟
        if(product.getIsAutoPublish() == 1){
            if(DateUtils.addMinutes(new Date(),15).getTime()>product.getAutoPublishTime().getTime())
            {
                throw  new BizFeignException(ProductResponseExceptionConstant.AUTOTIME_NOT_CORRECT);
            }
        }


    }



    /**
     * 检查产品是否符合满标状态的要求
     * @param product
     */
    public static void checkReverify(Product product){
        //不能为空
        if(product == null){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_IS_NULL);
        }
        // 产品必须为散标
        if(product.getProductType() != 0){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_MUST_BORROW);
        }
        // 产品必须为6：满标待审
        if(product.getProductStatus() != ProductConstant.PRODUCT_STATUS_6 && product.getBorrowFullStatus() != 1){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_STATUS_INCORRECT);
        }
    }


    /**
     * 检查产品是否符合撤标的要求
     * @param product
     */
    public static void checkWithdraw(Product product){
        //不能为空
        if(product == null){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_IS_NULL);
        }
        // 产品必须为6：满标待审
        if((product.getProductStatus() != ProductConstant.PRODUCT_STATUS_6 && product.getBorrowFullStatus() != 1)
                ||  product.getProductStatus() != ProductConstant.PRODUCT_STATUS_5){
            throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_STATUS_INCORRECT);
        }
    }


}

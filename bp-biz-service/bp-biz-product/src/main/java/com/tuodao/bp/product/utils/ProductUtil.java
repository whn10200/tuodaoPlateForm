package com.tuodao.bp.product.utils;

import com.tuodao.bp.model.constant.product.ProductExceptionConstant;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.product.constants.ProductEnumConstant;
import com.tuodao.bp.product.constants.ProductResponseExceptionConstant;
import com.tuodao.bp.product.db.model.basic.BorrowDefineType;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.product.db.model.basic.ProductWithBLOBs;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.ArithUtils;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.BorrowPlan;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/9/7 0007 11:25
 * @Introduction 产品工具类
 */
public class ProductUtil {

    /**
     * 将产品转化成前台的
     * @param product
     */
    public static void doTranfer(Product product){
        product.getProductStatus();
        //理财计划不能为空
        if(product == null){
            throw new BizFeignException(ProductExceptionConstant.PRODUCT_IS_NULL);
        }
        // 产品必须为理财计划
        if(product.getProductType() != 1){
            throw new BizFeignException(ProductExceptionConstant.PRODUCT_MUST_FEATURED);
        }

        // 产品必须为还款中
        if(product.getProductStatus() != 7){
            throw new BizFeignException(ProductExceptionConstant.PRODUCT_STATUS_INCORRECT);
        }
    }

    /**
     * 将产品转化成前台的对象
     * @param product
     */
    public static ProductOutput doTranfer(ProductWithBLOBs product, List<BorrowDefineType> typeList){
        ProductOutput out = new ProductOutput();
        BeanUtils.copyProperties(product, out);

        out.setBorrowAccountYes(ArithUtils.toYuan(out.getBorrowAccountYes()));  //借款金额（元）
        out.setBorrowAmount(ArithUtils.toYuan(out.getBorrowAmount()));      //实际借款人到账金额（元）
        out.setBorrowFee(ArithUtils.toYuan(out.getBorrowFee()));        //借款手续费（元）
        out.setSurplusInvestAmount(ArithUtils.toYuan(out.getSurplusInvestAmount()));        //剩余可投

        out.setBorrowTypeText(ProductEnumConstant.getBorrowTypeText(out.getBorrowType()));
        out.setRefundWayText(ProductEnumConstant.getRefundWayText(out.getRefundWay()));
        out.setPeriodUnitText(ProductEnumConstant.getPeriodUnitText(out.getPeriodUnit()));
        out.setProductStatusText(ProductEnumConstant.getTypeName(out.getProductStatus()));


        /**
         * 找到对应的标种
         */
        if(!CollectionUtils.isEmpty(typeList)){
            for(BorrowDefineType defineType : typeList){
                if(defineType.getId() == product.getDefineType()){
                    out.setDefineTypeText(defineType.getName());
                    out.setTypePcUrl(defineType.getPcPictureUrl());
                    out.setTypeAppUrl(defineType.getAppPictureUrl());
                    out.setTypeRemark(defineType.getRemark());

                    break;
                }
            }
        }

        return out;
    }

    /**
     * 将产品转化成前台的对象
     * @param  productList
     * @param typeList
     */
    public static  List<ProductOutput> doTranfer(List<ProductWithBLOBs> productList, List<BorrowDefineType> typeList){

        List<ProductOutput> outputList = new ArrayList<>();
        if(CollectionUtils.isEmpty(productList))
        {
            return null;
        }

        for(ProductWithBLOBs productWithBLOBs : productList){
            ProductOutput out = doTranfer(productWithBLOBs,typeList);
            outputList.add(out);
        }

        return  outputList;
    }

    public static List<BorrowPlan> getPlan(Product product)
    {
        Double award = 0d;
        //还款时 奖励不加入
//        if( product.getAwardType() == 2){
//            award =  product.getAwardScale() == null ? 0d: product.getAwardScale().doubleValue();
//        }
        Double apr = product.getBorrowApr().doubleValue()+award;
        return getPlan(product.getRefundWay(),product.getBorrowAmount().longValue(),apr,product.getProductPeriod());
    }

    /**
     * 计算利息
     * @param style 还款方式 0：等额本息 1：按月付息 2：按天付息',
     * @param account 分,
     * @return
     */
    public static List<BorrowPlan> getPlan(Integer style, long account, double apr, int period) {
        List<BorrowPlan> planList = new ArrayList<BorrowPlan>();
        Date reverifyTime = new Date();

        if (style.equals(0)) { // 等额本息
            double interest = account * apr * 0.01 / 12;
            BigDecimal bg = new BigDecimal(interest);
           // interest = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            double perMonthApr = apr * 0.01 / 12;
            double preMonth = account * perMonthApr * Math.pow((1 + perMonthApr), period) / (Math.pow((1 + perMonthApr), period) - 1);

            double leftAcc = account;
            for (int i = 0; i < period; i++) {

                double perInterest = BigDecimalUtils.round(leftAcc * perMonthApr);
                double perCapital = BigDecimalUtils.round(preMonth - perInterest);
                BorrowPlan p = new BorrowPlan();
                p.setPeriod(i + 1);
                p.setPreInterest(perInterest);
                p.setPreTime(DateUtils.addMonths(reverifyTime, i + 1));

                if (i != period - 1) {
                    p.setPreCapital(perCapital);
                    leftAcc -= perCapital;
                } else {
                    p.setPreCapital(leftAcc);
                    leftAcc = 0;
                }
                planList.add(p);

            }
        } else if (style.equals(1)) {//按月付息
            double interest = account * apr * 0.01 / 12;
            BigDecimal bg = new BigDecimal(interest);
            interest = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            for (int i = 0; i < period; i++) {
                BorrowPlan p = new BorrowPlan();
                p.setPeriod(i + 1);
                if (i == period - 1) {
                    p.setPreCapital(account);
                } else {
                    p.setPreCapital(0);
                }
                p.setPreTime(DateUtils.addMonths(reverifyTime, i + 1));
                p.setPreInterest(interest);
                planList.add(p);
            }

        } else if (style.equals(2)) {//2：按天付息
            BorrowPlan p = new BorrowPlan();
            p.setPeriod(1);
            p.setPreCapital(account);
            double interest = account * period * apr * 0.01 / 365;
            BigDecimal bg = new BigDecimal(interest);
            interest = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            p.setPreInterest(interest);
            p.setPreTime(DateUtils.addDays(reverifyTime, period));
            planList.add(p);
        } else {
            throw new BizFeignException(ProductResponseExceptionConstant.STYLE_NOT_CORRECT);
        }
        return planList;
    }


}

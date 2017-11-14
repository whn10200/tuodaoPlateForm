package com.tuodao.bp.api.facade.service.transaction.impl;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Ordering;
import com.tuodao.bp.api.facade.client.operation.UserDiscountsClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.transaction.AutoTenderClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowTenderClient;
import com.tuodao.bp.api.facade.service.transaction.AccountService;
import com.tuodao.bp.api.facade.service.transaction.AutoTenderService;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountQueryOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.output.AutoTenderOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.operation.UserDiscountConstant;
import com.tuodao.bp.model.constant.product.ProductConstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.enums.ChannelType;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.BorrowUtils;
import com.tuodao.bp.utils.TransferUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @description: 自动投标
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 14:18
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service("autoTenderService")
public class AutoTenderServiceImpl implements AutoTenderService{

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoTenderServiceImpl.class);

    @Autowired
    private AutoTenderClient autoTenderClient;

    @Autowired
    private AccountService accountService;

    @Autowired
    private BorrowTenderClient borrowTenderClient;

    @Autowired
    private UserAccountCache userAccountCache;

    @Autowired
    public UserDiscountsClient userDiscountsClient;

    @Autowired
    private ProductClient productClient;

    @Override
    public void saveAutoTender(AutoTenderParam param) {
        autoTenderClient.saveAutoTender(param);
    }

    @Override
    public AutoTenderVo getUserAutoTender(String userId){
        AutoTenderVo vo =  autoTenderClient.getUserAutoTender(userId);
        vo.setMaxAccount(BigDecimalUtils.centToYuan(vo.getMaxAccount()));
        vo.setMinAccount(BigDecimalUtils.centToYuan(vo.getMinAccount()));
        return vo;
    }

    @Override
    public void closeAutoTender(String userId) {
        autoTenderClient.closeAutoTender(userId);
    }

    @Override
    public long getTotalAutoTender() {
        return autoTenderClient.getTotalAutoTender();
    }

    /**
     * 自动投标定时任务完整逻辑
     */
    @Override
    public void autoTender() {

        List<ProductOutput> productList = productClient.getAutoTenderingBorrowList();

        if(productList != null && productList.size() > 0){
            List<AutoTenderOutput> tenderList = autoTenderClient.getList(TenderConstant.AUTO_TENDER_MAX_QUERY);

            if(tenderList != null && tenderList.size() > 0){

               for(AutoTenderOutput tender : tenderList){
                   double totalSurplus = getProductTotalSurplus(productList,tender.getMinPeriod(),tender.getMaxPeriod());

                   double useMoney = getUserMoney(tender.getUserId());
                    //预计投标金额
                   double preTender;
                   //可用余额不够
                   if(useMoney < tender.getMinAccount().doubleValue()){
                       LOGGER.debug("自动投标,用户可用余额不足continue,可用:{},最小投标:{}",useMoney,tender.getMinAccount());
                       //重置排名
                       addAutoTenderLog(tender);
                       continue;
                   }
                   //可用余额比设定的最大金额还多 可以全部投出
                   if(useMoney > tender.getMaxAccount().doubleValue()){
                       preTender = tender.getMaxAccount().doubleValue();
                   }else{
                       //可用余额在自动投标设置区间中
                       preTender = useMoney;
                   }
                   //标量足够 可以全部投完
                   if (totalSurplus >= preTender){
                       //实际投标金额 = 预计投标金额
                       preTender(productList,preTender,tender);
                   }else if(totalSurplus >= tender.getMinAccount().doubleValue()){
                       //标量满足最小投标设置,会将所有标的投满
                       //实际投标金额=标的剩余可投
                       preTender(productList,totalSurplus,tender);
                       //由于已经投满因此可以打断
                       break;
                   }
               }
            }
        }
    }

    /**
     * 添加自动投标设置信息及日志
     * @param output 自动投标设置信息
     */
    private void addAutoTenderLog(AutoTenderOutput output){

        AutoTenderParam param = new AutoTenderParam();
        BeanUtils.copyProperties(output,param);
        param.setMaxAccount(BigDecimalUtils.centToYuan(output.getMaxAccount().doubleValue()));
        param.setMinAccount(BigDecimalUtils.centToYuan(output.getMinAccount().doubleValue()));

        autoTenderClient.saveAutoTender(param);
    }


    /**
     * 投标
     * @param list 产品池
     * @param tenderMoney 投标金额
     * @param output 自动投标设置参数
     * @return 满标标的
     */
    private void preTender(List<ProductOutput> list,double tenderMoney,AutoTenderOutput output){

        UserDiscountOutput voucher = getBestVoucher(list,tenderMoney,output.getUserId());
        //剩余待投金额
        double lastTender = tenderMoney;

        Iterator<ProductOutput> iterator = list.iterator();
        while (iterator.hasNext()){
            ProductOutput product = iterator.next();
            //期限设定过滤
            if (product.getProductPeriod() >= output.getMinPeriod()
                    && product.getProductPeriod() <= output.getMaxPeriod()){
                //本次预计投标金额
                double nowTender = lastTender;
                //标的剩余可投金额 不够投
                if(product.getSurplusInvestAmount().doubleValue() < lastTender){
                    lastTender = BigDecimalUtils.sub(lastTender,product.getSurplusInvestAmount().doubleValue());
                    nowTender = product.getSurplusInvestAmount().doubleValue();
                    //该标的将被投满 直接在集合中去除 减少重复数据
                    iterator.remove();
                }

                UserAccountInfo user = userAccountCache.getUserAccoutInfo(output.getUserId());

                TenderExecutor executor = new TenderExecutor();
                executor.setAutoTenderId(output.getId());
                executor.setDiscount(voucher);
                executor.setProduct(product);
                executor.setUser(user);
                executor.setTenderMoney(nowTender);
                executor.setTenderMode(TenderConstant.TENDER_MODE_AUTO);
                executor.setChannel(ChannelType.PC.getValue());
                executor.setAddIp("127.0.0.1");
                executor.setTenderType(ProductConstant.PRODUCT_TYPE_COMMON);
                executor.setAutoTenderId(output.getId());

                borrowTenderClient.tenderProducer(executor);
            }
        }
    }


    /**
     * 获取用户最佳使用加息券
     * @param list  要投的标的列表
     * @param tenderMoney 投标金额
     * @param userId
     * @return
     */
    @Override
    public UserDiscountOutput getBestVoucher(List<ProductOutput> list,double tenderMoney,String userId){

        BasePojo input = new BasePojo();
        input.setUserId(userId);

        List<UserDiscountQueryOutput> userDiscountQueryList = userDiscountsClient.getUserDiscountQueryList(input);


        if(userDiscountQueryList != null && userDiscountQueryList.size() > 0){
            return  matchVoucher(
                    //抵用券
                    getTypeVoucher(userDiscountQueryList, UserDiscountConstant.VOUCHER_TYPE),
                    //加息券
                    getTypeVoucher(userDiscountQueryList,UserDiscountConstant.COUPON_TYPE),
                    list,
                    tenderMoney
                    );
        }
        return null;
    }

    @Override
    public PageInfo<AutoTenderListVo> getAutoTenderListByPage(PagePojo pojo) {
        return autoTenderClient.getAutoTenderListByPage(pojo);
    }

    @Override
    public AutoTenderListVo getAutoTenderDetail(String userId, Integer autoTenderId) {
        return autoTenderClient.getAutoTenderDetail(userId,autoTenderId);
    }


    /**
     * 获取指定类型的抵用券或加息券
     * @param list 用户所有可用的券
     * @param type 加息券或抵用券
     * @return
     */
    private List<UserDiscountQueryOutput> getTypeVoucher(List<UserDiscountQueryOutput> list,int type){
        List<UserDiscountQueryOutput> outputList = new ArrayList<>();
        for (UserDiscountQueryOutput voucherOutput: list){
            if(voucherOutput.getDiscountType() == type){
                outputList.add(voucherOutput);
            }
        }
        return outputList;
    }


    /**
     * 多个标的获取最佳匹配到的抵用券或加息券<br/>
     * 逻辑说明:抵用券与加息券在自动投标时计算方法不一样 因此需要分成两个集合进行操作<br/>
     * 抵用券:先获取对应某个标的投标金额时可以使用的最佳加息券,但由于一个投标可能会拆成多次投标,因此在循环时需要额外与上一次收益以获取最大化的抵用券<br>
     * 例子:用户投标10000,分在三个标上面使用2000,3000,5000,此时用户抵用券10元 1000起投 20元 2000起投 30元 5000起投,在自动投标情况只会选择一个进行投标即30 而不是三张都使用<br>
     * 加息券:在所有标的中,即便是拆分投资 加息券所产生的利息只会因周期的长度所改变,因此只需要累计增加即可得到最大收益,最后进行加息券与抵用券收益对比<br/>
     *
     * @param voucherList 可用的抵用券列表
     * @param couponList 可用的加息券列表
     * @param productList 产品列表
     * @param tenderMoney 投标金额
     * @return
     */
    private UserDiscountOutput matchVoucher(List<UserDiscountQueryOutput> voucherList,List<UserDiscountQueryOutput> couponList,List<ProductOutput> productList,double tenderMoney){
        //剩余待投金额
        double lastTender = tenderMoney;
        //抵用券总收益
        double voucherInterest = 0D;
        //加息券总收益
        double couponInterest = 0D;

        UserDiscountQueryOutput voucherOutput = null;
        UserDiscountQueryOutput couponOutput = null;
        for (ProductOutput product :productList){
            double nowTender = lastTender;
            if(product.getSurplusInvestAmount().doubleValue() < lastTender){
                lastTender = BigDecimalUtils.sub(lastTender,product.getSurplusInvestAmount().doubleValue());
                //本次投标金额
                nowTender = product.getSurplusInvestAmount().doubleValue();
            }
            UserDiscountQueryOutput voucher = getProductMatchVoucher(voucherList,product,nowTender);

            if(voucher != null){
                double interest = calcVoucherInterest(product.getProductPeriod(),nowTender,product.getRefundWay(),voucher);
                if(interest > voucherInterest){
                    voucherInterest = interest;
                    voucherOutput = voucher;
                }
            }

            UserDiscountQueryOutput coupon = getMaxVoucherCoupon(couponList);
            if(coupon != null){
                double interest = calcVoucherInterest(product.getProductPeriod(),nowTender,product.getRefundWay(),coupon);
                couponInterest = BigDecimalUtils.add(couponInterest,interest);
                couponOutput = coupon;
            }
        }

        if(voucherInterest >= couponInterest){
            return transfer(voucherOutput);
        }else{
            return transfer(couponOutput);
        }
    }

    /**
     * @param output
     * @return
     */
    private UserDiscountOutput transfer(UserDiscountQueryOutput output){
        if(output != null){
            return TransferUtil.transferBean(output,UserDiscountOutput.class);
        }
        return null;
    }

    /**
     * 获取加息利息最大的加息券
     * @param couponList
     * @return
     */
    private  UserDiscountQueryOutput getMaxVoucherCoupon(List<UserDiscountQueryOutput> couponList){
        if(couponList == null || couponList.size() == 0){
            return null;
        }
        Ordering<UserDiscountQueryOutput> ordering = new Ordering<UserDiscountQueryOutput>() {
            @Override
            public int compare(UserDiscountQueryOutput left, UserDiscountQueryOutput right) {
                double sub = Double.parseDouble(right.getDiscountAvailable()) - Double.parseDouble(left.getDiscountAvailable());
                if(sub > 0){
                    return 1;
                }else if(sub == 0){
                    return 0;
                }
                return -1;
            }
        };
        return ordering.sortedCopy(couponList).get(0);
    }



    /**
     * 根据标的选择最佳的抵用券(非加息券)
     * @param voucherList
     * @param productOutput
     * @param tenderMoney
     * @return
     */
    private UserDiscountQueryOutput getProductMatchVoucher(List<UserDiscountQueryOutput> voucherList,ProductOutput productOutput,double tenderMoney){
        if(voucherList == null || voucherList.size() == 0){
            return null;
        }
        UserDiscountQueryOutput voucherOutput = null;
        for (UserDiscountQueryOutput voucher :voucherList){
            //期限匹配,金额匹配
            if(voucher.getDateLimit() <= productOutput.getProductPeriod() && voucher.getMoneyLimit().doubleValue() <= tenderMoney){
                if(voucherOutput == null){
                    voucherOutput = voucher;
                }else{
                    double outputMoney =  calcVoucherInterest(productOutput.getProductPeriod(),tenderMoney,productOutput.getRefundWay(),voucherOutput);
                    double voucherMoney = calcVoucherInterest(productOutput.getProductPeriod(),tenderMoney,productOutput.getRefundWay(),voucher);
                    //如果利息相等 优先使用抵用券
                    if(voucherMoney > outputMoney ){
                        voucherOutput = voucher;
                    }
                }
            }
        }
        return voucherOutput;
    }


    /**
     * 计算抵用券产生收收益
     * @param period
     * @param tenderMoney
     * @param refundWay
     * @param output
     * @return
     */
    private double calcVoucherInterest(int period,double tenderMoney,int refundWay,UserDiscountQueryOutput output){
        if(output.getDiscountType() == UserDiscountConstant.VOUCHER_TYPE){
            return Double.parseDouble(output.getDiscountAvailable());
        }
        if(refundWay == ProductConstant.REFUND_WAY){
            return BorrowUtils.fixedPaymentMortgage(tenderMoney,period,Double.parseDouble(output.getDiscountAvailable()));
        }else{
            return BorrowUtils.perMonth(tenderMoney,period,Double.parseDouble(output.getDiscountAvailable()));
        }
    }

    /**
     * 获取用户可用投资金额
     * @param userId
     * @return
     */
    private double getUserMoney(String userId){
        AccountOutput account = accountService.getUserAccount(userId);
        return account.getBalance().doubleValue();
    }

    /**
     * 根据期限范围计算所有标的的剩余可投总额
     * @param list 标的列表
     * @param minPeriod 最小期限
     * @param maxPeriod 最大期限
     * @return
     */
    private double getProductTotalSurplus(List<ProductOutput> list,int minPeriod,int maxPeriod){
        double total = 0D;
        for (ProductOutput product: list) {
            if (product.getProductPeriod() >= minPeriod && product.getProductPeriod() <= maxPeriod){
                total = BigDecimalUtils.add(product.getSurplusInvestAmount().doubleValue(),total);
            }
        }
        return total;
    }


    public static void main(String[] args) {
        List<ProductOutput> productOutputs = new ArrayList<>();
        ProductOutput product1 = new ProductOutput();
        product1.setProductPeriod(1);
        product1.setRefundWay(0);
        product1.setSurplusInvestAmount(BigDecimal.valueOf(10000));
        ProductOutput product2 = new ProductOutput();
        product2.setProductPeriod(3);
        product2.setRefundWay(1);

        product2.setSurplusInvestAmount(BigDecimal.valueOf(20000));
        ProductOutput product3 = new ProductOutput();
        product3.setProductPeriod(6);
        product3.setRefundWay(1);
        product3.setSurplusInvestAmount(BigDecimal.valueOf(40000));

        productOutputs.add(product1);
        productOutputs.add(product2);
        productOutputs.add(product3);

        List<UserDiscountOutput> voucherList = new ArrayList<>();

        UserDiscountOutput voucher0 = new UserDiscountOutput();
        voucher0.setMoneyLimit(20000);
        voucher0.setDiscountType(1);
        voucher0.setDiscountAvailable("2");
        voucher0.setDateLimit(6);


        UserDiscountOutput voucher1 = new UserDiscountOutput();
        voucher1.setMoneyLimit(20000);
        voucher1.setDiscountType(1);
        voucher1.setDiscountAvailable("3");
        voucher1.setDateLimit(3);


        UserDiscountOutput voucher2 = new UserDiscountOutput();
        voucher2.setMoneyLimit(32000);
        voucher2.setDiscountType(0);
        voucher2.setDiscountAvailable("500");
        voucher2.setDateLimit(6);

        UserDiscountOutput voucher3 = new UserDiscountOutput();
        voucher3.setMoneyLimit(10000);
        voucher3.setDiscountType(0);
        voucher3.setDiscountAvailable("300");
        voucher3.setDateLimit(3);

        UserDiscountOutput voucher4 = new UserDiscountOutput();
        voucher4.setMoneyLimit(10000);
        voucher4.setDiscountType(0);
        voucher4.setDiscountAvailable("200");
        voucher4.setDateLimit(1);

        voucherList.add(voucher0);
        voucherList.add(voucher1);
        //voucherList.add(voucher2);
        //voucherList.add(voucher3);
        //voucherList.add(voucher4);



    }


}

package com.tuodao.bp.traningcenter.until;

import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @description:
 * @author: wuzf
 * @date: 2017/9/14 0014.
 * @time: 11:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class BorrowUtils
{
    private static final Logger logger = LoggerFactory.getLogger(BorrowUtils.class);


    /**
     * 计算新利息
     *
     * @return
     */
    public static List<BorrowPlan> getPlan(int style, double account, double apr, int period,double couponVoucherApr,double awardScale,double borrowApr) {
        List<BorrowPlan> planList = new ArrayList<BorrowPlan>();
        if (style==0) { // 等额本息
            double perMonthApr = apr * 0.01 / 12;
            double preMonth = account * perMonthApr * Math.pow((1 + perMonthApr), period) / (Math.pow((1 + perMonthApr), period) - 1);

            double oldBorrowApr = borrowApr * 0.01 / 12;

            double leftAcc = account;
            for (int i = 0; i < period; i++) {

                double perInterest = Arith.roundMoney(leftAcc * perMonthApr);
                perInterest = new BigDecimal(perInterest).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
                double perCapital = Arith.roundMoney(preMonth - perInterest);
                perCapital = new BigDecimal(perCapital).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
                double couponVoucherAccount = Arith.roundMoney(perInterest * couponVoucherApr / apr);
                couponVoucherAccount = new BigDecimal(couponVoucherAccount).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();

                double perInterest1 = Arith.roundMoney(leftAcc * oldBorrowApr);
                perInterest1 = new BigDecimal(perInterest1).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();

                BorrowPlan p = new BorrowPlan();
                p.setPeriod(i + 1);
                p.setInterest(perInterest);
                p.setOldInterest(perInterest1);
                p.setCouponVoucherAccount(couponVoucherAccount);
                if (couponVoucherApr == 0 && awardScale != 0) {
                    p.setCouponVoucherAccount(0);
                    p.setAwardAccount(perInterest - perInterest1);
                } else if (couponVoucherApr != 0 && awardScale == 0) {
                    p.setAwardAccount(0);
                    p.setCouponVoucherAccount(perInterest - perInterest1);
                } else if (couponVoucherApr == 0 && awardScale == 0) {
                    p.setAwardAccount(0);
                    p.setCouponVoucherAccount(0);
                } else {
                    p.setCouponVoucherAccount(couponVoucherAccount);
                    p.setAwardAccount(perInterest - couponVoucherAccount - perInterest1);
                }
                if (i != period - 1) {
                    p.setCapital(perCapital);
                    leftAcc -= perCapital;
                } else {
                    p.setCapital(leftAcc);
                    leftAcc = 0;
                }
                planList.add(p);

            }
        } else if (style==1) {//抵押标
            double interestTotal = account * apr * 0.01 / 12 * period;
            //  BigDecimal bg = new BigDecimal(interestTotal);
            //   interestTotal = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            for (int i = 0; i < period; i++) {
                BorrowPlan p = new BorrowPlan();
                p.setPeriod(i + 1);
                double interest = account * apr * 0.01 / 12;
                interest = new BigDecimal(interest).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
                double oldInterest = account * borrowApr * 0.01 / 12;
                oldInterest = new BigDecimal(oldInterest).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
                double couponVoucherAccount = Arith.roundMoney(interest * couponVoucherApr / apr);
                couponVoucherAccount = new BigDecimal(couponVoucherAccount).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
//                double awardAccount = Arith.roundMoney(interest*awardScale/apr);
                //     BigDecimal bigDecimal = new BigDecimal(interest);
                //      interest = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                if (i == period - 1) {
                    p.setCapital(account);
//                    interest = interestTotal; //最后一个月把所有剩余利息给它
                } else {
                    p.setCapital(0);
//                    interestTotal -= interest;  //总利息减每个月的利息
                }
                if (couponVoucherApr == 0 && awardScale != 0) {
                    p.setCouponVoucherAccount(0);
                    p.setAwardAccount(interest - oldInterest);
                } else if (couponVoucherApr != 0 && awardScale == 0) {
                    p.setAwardAccount(0);
                    p.setCouponVoucherAccount(interest - oldInterest);
                } else if (couponVoucherApr == 0 && awardScale == 0) {
                    p.setAwardAccount(0);
                    p.setCouponVoucherAccount(0);
                } else {
                    p.setCouponVoucherAccount(couponVoucherAccount);
                    p.setAwardAccount(interest - couponVoucherAccount - oldInterest);
                }
                p.setInterest(Arith.roundMoney(interest));
                p.setOldInterest(Arith.roundMoney(oldInterest));
                planList.add(p);
            }

        }else if("3".equals(style))
        {
            BorrowPlan p = new BorrowPlan();
            p.setPeriod(1);
            p.setCapital(account);
            double interest = account * period * apr * 0.01 / 365;
            interest= new BigDecimal(interest).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
            double interest1 = account * period * borrowApr * 0.01 / 365;
            interest1= new BigDecimal(interest1).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
            double couponVoucherAccount = Arith.roundMoney(interest*couponVoucherApr/apr);
            couponVoucherAccount= new BigDecimal(couponVoucherAccount).setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
            if(couponVoucherApr==0 && awardScale!=0)
            {
                p.setCouponVoucherAccount(0);
                p.setAwardAccount(interest-interest1);
            }
            else if (couponVoucherApr!=0 && awardScale==0)
            {
                p.setAwardAccount(0);
                p.setCouponVoucherAccount(interest-interest1);
            }
            else if (couponVoucherApr==0 && awardScale==0)
            {
                p.setAwardAccount(0);
                p.setCouponVoucherAccount(0);
            }
            else
            {
                p.setCouponVoucherAccount(couponVoucherAccount);
                p.setAwardAccount(interest-couponVoucherAccount-interest1);
            }
            // BigDecimal bg = new BigDecimal(interest);
            //interest = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            p.setInterest(Arith.roundMoney(interest));
            p.setOldInterest(Arith.roundMoney(interest1));
//            double awardAccount = Arith.roundMoney(interest*awardScale/apr);
//            p.setCouponVoucherAccount(couponVoucherAccount);
//            p.setAwardAccount(awardAccount);
            planList.add(p);
        }
        else
        {
            throw new BizFeignException(TransactError.UPDATE_CASH_ERROR);
        }
        return planList;
    }


    /**
     * 获得还款计划
     *
     * @return
     * @param recoverPlanList 投资人的回款计划
     * @param repayPlanMap  之前的借款人的还款计划
     */
    public static HashMap<Integer,BorrowPlan> getRepayPlan(List<BorrowPlan> recoverPlanList, HashMap<Integer,BorrowPlan> repayPlanMap) {

        if(recoverPlanList == null || recoverPlanList.isEmpty()){
            return  null;
        }

        if(repayPlanMap == null ){
            repayPlanMap = new HashMap<Integer,BorrowPlan> ();
        }
        BorrowPlan repayPlan = null;
        for(BorrowPlan rp : recoverPlanList){
            Integer period = rp.getPeriod();
            if(repayPlanMap.containsKey(period)){
                repayPlan = repayPlanMap.get(period);
            }else{
                repayPlan = new BorrowPlan();
                repayPlanMap.put(period,repayPlan);
            }
            Double interest = rp.getInterest()+ repayPlan.getInterest();
            Double oldinterest = rp.getOldInterest()+ repayPlan.getOldInterest();
            Double couponVoucherAccount = rp.getCouponVoucherAccount()+ repayPlan.getCouponVoucherAccount();
            Double awardAccount = rp.getAwardAccount()+ repayPlan.getAwardAccount();
            Double capital = rp.getCapital()+ repayPlan.getCapital();
            repayPlan.setOldInterest(oldinterest);
            repayPlan.setPeriod(period);
            repayPlan.setInterest(interest);
            repayPlan.setCouponVoucherAccount(couponVoucherAccount);
            repayPlan.setAwardAccount(awardAccount);
            repayPlan.setCapital(capital);
        }

        return repayPlanMap;
    }


    /**
     * 根据期数获得利息
     *
     * @return
     */
    public static BigDecimal getPlan(double account, double apr, int period, int index) {

        apr = apr / 12;
        double interest = 0.0;
        double preMonth = account * apr * Math.pow((1 + apr), period) / (Math.pow((1 + apr), period) - 1);

        double leftAcc = account;
        for (int i = 0; i < period; i++) {

            double perInterest = Arith.roundMoney(leftAcc * apr);
            double perCapital = Arith.roundMoney(preMonth - perInterest);
            int j = i + 1;
            if (j == index) {
                interest = perInterest;
                break;
            }
            if (i != period - 1) {
                leftAcc -= perCapital;
            } else {
                leftAcc = 0;
            }
        }

        return new BigDecimal(interest);
    }


}

package com.tuodao.bp.traningcenter.until;

/**
 * Created by somesky on 15/7/11.
 */
public class BorrowPlan {
    private int period;
    private double account;     //总金额
    private  double capital;    //本金
    private  double interest;   //利息
    private double couponVoucherAccount; //券得到的收益
    private double awardAccount;       //平台奖励得到里收益
    private double oldInterest;       //不加奖励的收益

    public double getOldInterest() {
        return oldInterest;
    }

    public void setOldInterest(double oldInterest) {
        this.oldInterest = oldInterest;
    }

    public double getCouponVoucherAccount() {
        return couponVoucherAccount;
    }

    public void setCouponVoucherAccount(double couponVoucherAccount) {
        this.couponVoucherAccount = couponVoucherAccount;
    }

    public double getAwardAccount() {
        return awardAccount;
    }

    public void setAwardAccount(double awardAccount) {
        this.awardAccount = awardAccount;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getAccount() {
        return account;
    }

    public void setAccount(double account) {
        this.account = account;
    }

    public double getCapital() {
        return capital;
    }

    public void setCapital(double capital) {
        this.capital = capital;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}

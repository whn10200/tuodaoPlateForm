package com.tuodao.bp.useraccount.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountFinanceExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public AccountFinanceExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria implements Serializable {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(String value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(String value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(String value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(String value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(String value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLike(String value) {
            addCriterion("user_id like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotLike(String value) {
            addCriterion("user_id not like", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<String> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<String> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(String value1, String value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(String value1, String value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andDepositNoIsNull() {
            addCriterion("deposit_no is null");
            return (Criteria) this;
        }

        public Criteria andDepositNoIsNotNull() {
            addCriterion("deposit_no is not null");
            return (Criteria) this;
        }

        public Criteria andDepositNoEqualTo(String value) {
            addCriterion("deposit_no =", value, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoNotEqualTo(String value) {
            addCriterion("deposit_no <>", value, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoGreaterThan(String value) {
            addCriterion("deposit_no >", value, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_no >=", value, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoLessThan(String value) {
            addCriterion("deposit_no <", value, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoLessThanOrEqualTo(String value) {
            addCriterion("deposit_no <=", value, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoLike(String value) {
            addCriterion("deposit_no like", value, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoNotLike(String value) {
            addCriterion("deposit_no not like", value, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoIn(List<String> values) {
            addCriterion("deposit_no in", values, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoNotIn(List<String> values) {
            addCriterion("deposit_no not in", values, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoBetween(String value1, String value2) {
            addCriterion("deposit_no between", value1, value2, "depositNo");
            return (Criteria) this;
        }

        public Criteria andDepositNoNotBetween(String value1, String value2) {
            addCriterion("deposit_no not between", value1, value2, "depositNo");
            return (Criteria) this;
        }

        public Criteria andTotalFundIsNull() {
            addCriterion("total_fund is null");
            return (Criteria) this;
        }

        public Criteria andTotalFundIsNotNull() {
            addCriterion("total_fund is not null");
            return (Criteria) this;
        }

        public Criteria andTotalFundEqualTo(BigDecimal value) {
            addCriterion("total_fund =", value, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundNotEqualTo(BigDecimal value) {
            addCriterion("total_fund <>", value, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundGreaterThan(BigDecimal value) {
            addCriterion("total_fund >", value, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_fund >=", value, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundLessThan(BigDecimal value) {
            addCriterion("total_fund <", value, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_fund <=", value, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundIn(List<BigDecimal> values) {
            addCriterion("total_fund in", values, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundNotIn(List<BigDecimal> values) {
            addCriterion("total_fund not in", values, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_fund between", value1, value2, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_fund not between", value1, value2, "totalFund");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsIsNull() {
            addCriterion("total_earnings is null");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsIsNotNull() {
            addCriterion("total_earnings is not null");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsEqualTo(BigDecimal value) {
            addCriterion("total_earnings =", value, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsNotEqualTo(BigDecimal value) {
            addCriterion("total_earnings <>", value, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsGreaterThan(BigDecimal value) {
            addCriterion("total_earnings >", value, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_earnings >=", value, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsLessThan(BigDecimal value) {
            addCriterion("total_earnings <", value, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_earnings <=", value, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsIn(List<BigDecimal> values) {
            addCriterion("total_earnings in", values, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsNotIn(List<BigDecimal> values) {
            addCriterion("total_earnings not in", values, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_earnings between", value1, value2, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andTotalEarningsNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_earnings not between", value1, value2, "totalEarnings");
            return (Criteria) this;
        }

        public Criteria andDueInFundIsNull() {
            addCriterion("due_in_fund is null");
            return (Criteria) this;
        }

        public Criteria andDueInFundIsNotNull() {
            addCriterion("due_in_fund is not null");
            return (Criteria) this;
        }

        public Criteria andDueInFundEqualTo(BigDecimal value) {
            addCriterion("due_in_fund =", value, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundNotEqualTo(BigDecimal value) {
            addCriterion("due_in_fund <>", value, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundGreaterThan(BigDecimal value) {
            addCriterion("due_in_fund >", value, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("due_in_fund >=", value, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundLessThan(BigDecimal value) {
            addCriterion("due_in_fund <", value, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("due_in_fund <=", value, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundIn(List<BigDecimal> values) {
            addCriterion("due_in_fund in", values, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundNotIn(List<BigDecimal> values) {
            addCriterion("due_in_fund not in", values, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("due_in_fund between", value1, value2, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andDueInFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("due_in_fund not between", value1, value2, "dueInFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundIsNull() {
            addCriterion("usable_fund is null");
            return (Criteria) this;
        }

        public Criteria andUsableFundIsNotNull() {
            addCriterion("usable_fund is not null");
            return (Criteria) this;
        }

        public Criteria andUsableFundEqualTo(BigDecimal value) {
            addCriterion("usable_fund =", value, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundNotEqualTo(BigDecimal value) {
            addCriterion("usable_fund <>", value, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundGreaterThan(BigDecimal value) {
            addCriterion("usable_fund >", value, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("usable_fund >=", value, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundLessThan(BigDecimal value) {
            addCriterion("usable_fund <", value, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("usable_fund <=", value, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundIn(List<BigDecimal> values) {
            addCriterion("usable_fund in", values, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundNotIn(List<BigDecimal> values) {
            addCriterion("usable_fund not in", values, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usable_fund between", value1, value2, "usableFund");
            return (Criteria) this;
        }

        public Criteria andUsableFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("usable_fund not between", value1, value2, "usableFund");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalIsNull() {
            addCriterion("due_in_principal is null");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalIsNotNull() {
            addCriterion("due_in_principal is not null");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalEqualTo(BigDecimal value) {
            addCriterion("due_in_principal =", value, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalNotEqualTo(BigDecimal value) {
            addCriterion("due_in_principal <>", value, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalGreaterThan(BigDecimal value) {
            addCriterion("due_in_principal >", value, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("due_in_principal >=", value, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalLessThan(BigDecimal value) {
            addCriterion("due_in_principal <", value, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("due_in_principal <=", value, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalIn(List<BigDecimal> values) {
            addCriterion("due_in_principal in", values, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalNotIn(List<BigDecimal> values) {
            addCriterion("due_in_principal not in", values, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("due_in_principal between", value1, value2, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInPrincipalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("due_in_principal not between", value1, value2, "dueInPrincipal");
            return (Criteria) this;
        }

        public Criteria andDueInInterestIsNull() {
            addCriterion("due_in_interest is null");
            return (Criteria) this;
        }

        public Criteria andDueInInterestIsNotNull() {
            addCriterion("due_in_interest is not null");
            return (Criteria) this;
        }

        public Criteria andDueInInterestEqualTo(BigDecimal value) {
            addCriterion("due_in_interest =", value, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestNotEqualTo(BigDecimal value) {
            addCriterion("due_in_interest <>", value, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestGreaterThan(BigDecimal value) {
            addCriterion("due_in_interest >", value, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("due_in_interest >=", value, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestLessThan(BigDecimal value) {
            addCriterion("due_in_interest <", value, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("due_in_interest <=", value, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestIn(List<BigDecimal> values) {
            addCriterion("due_in_interest in", values, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestNotIn(List<BigDecimal> values) {
            addCriterion("due_in_interest not in", values, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("due_in_interest between", value1, value2, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andDueInInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("due_in_interest not between", value1, value2, "dueInInterest");
            return (Criteria) this;
        }

        public Criteria andFreezeFundIsNull() {
            addCriterion("freeze_fund is null");
            return (Criteria) this;
        }

        public Criteria andFreezeFundIsNotNull() {
            addCriterion("freeze_fund is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeFundEqualTo(BigDecimal value) {
            addCriterion("freeze_fund =", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundNotEqualTo(BigDecimal value) {
            addCriterion("freeze_fund <>", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundGreaterThan(BigDecimal value) {
            addCriterion("freeze_fund >", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_fund >=", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundLessThan(BigDecimal value) {
            addCriterion("freeze_fund <", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("freeze_fund <=", value, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundIn(List<BigDecimal> values) {
            addCriterion("freeze_fund in", values, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundNotIn(List<BigDecimal> values) {
            addCriterion("freeze_fund not in", values, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_fund between", value1, value2, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andFreezeFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("freeze_fund not between", value1, value2, "freezeFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundIsNull() {
            addCriterion("can_withdraw_fund is null");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundIsNotNull() {
            addCriterion("can_withdraw_fund is not null");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundEqualTo(BigDecimal value) {
            addCriterion("can_withdraw_fund =", value, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundNotEqualTo(BigDecimal value) {
            addCriterion("can_withdraw_fund <>", value, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundGreaterThan(BigDecimal value) {
            addCriterion("can_withdraw_fund >", value, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("can_withdraw_fund >=", value, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundLessThan(BigDecimal value) {
            addCriterion("can_withdraw_fund <", value, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundLessThanOrEqualTo(BigDecimal value) {
            addCriterion("can_withdraw_fund <=", value, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundIn(List<BigDecimal> values) {
            addCriterion("can_withdraw_fund in", values, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundNotIn(List<BigDecimal> values) {
            addCriterion("can_withdraw_fund not in", values, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("can_withdraw_fund between", value1, value2, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andCanWithdrawFundNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("can_withdraw_fund not between", value1, value2, "canWithdrawFund");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeIsNull() {
            addCriterion("total_recharge is null");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeIsNotNull() {
            addCriterion("total_recharge is not null");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeEqualTo(BigDecimal value) {
            addCriterion("total_recharge =", value, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeNotEqualTo(BigDecimal value) {
            addCriterion("total_recharge <>", value, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeGreaterThan(BigDecimal value) {
            addCriterion("total_recharge >", value, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_recharge >=", value, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeLessThan(BigDecimal value) {
            addCriterion("total_recharge <", value, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_recharge <=", value, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeIn(List<BigDecimal> values) {
            addCriterion("total_recharge in", values, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeNotIn(List<BigDecimal> values) {
            addCriterion("total_recharge not in", values, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_recharge between", value1, value2, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalRechargeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_recharge not between", value1, value2, "totalRecharge");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawIsNull() {
            addCriterion("total_withdraw is null");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawIsNotNull() {
            addCriterion("total_withdraw is not null");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawEqualTo(BigDecimal value) {
            addCriterion("total_withdraw =", value, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawNotEqualTo(BigDecimal value) {
            addCriterion("total_withdraw <>", value, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawGreaterThan(BigDecimal value) {
            addCriterion("total_withdraw >", value, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_withdraw >=", value, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawLessThan(BigDecimal value) {
            addCriterion("total_withdraw <", value, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_withdraw <=", value, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawIn(List<BigDecimal> values) {
            addCriterion("total_withdraw in", values, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawNotIn(List<BigDecimal> values) {
            addCriterion("total_withdraw not in", values, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_withdraw between", value1, value2, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andTotalWithdrawNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_withdraw not between", value1, value2, "totalWithdraw");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesIsNull() {
            addCriterion("investment_times is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesIsNotNull() {
            addCriterion("investment_times is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesEqualTo(Integer value) {
            addCriterion("investment_times =", value, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesNotEqualTo(Integer value) {
            addCriterion("investment_times <>", value, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesGreaterThan(Integer value) {
            addCriterion("investment_times >", value, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("investment_times >=", value, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesLessThan(Integer value) {
            addCriterion("investment_times <", value, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesLessThanOrEqualTo(Integer value) {
            addCriterion("investment_times <=", value, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesIn(List<Integer> values) {
            addCriterion("investment_times in", values, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesNotIn(List<Integer> values) {
            addCriterion("investment_times not in", values, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesBetween(Integer value1, Integer value2) {
            addCriterion("investment_times between", value1, value2, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("investment_times not between", value1, value2, "investmentTimes");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountIsNull() {
            addCriterion("investment_amount is null");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountIsNotNull() {
            addCriterion("investment_amount is not null");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountEqualTo(BigDecimal value) {
            addCriterion("investment_amount =", value, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountNotEqualTo(BigDecimal value) {
            addCriterion("investment_amount <>", value, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountGreaterThan(BigDecimal value) {
            addCriterion("investment_amount >", value, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("investment_amount >=", value, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountLessThan(BigDecimal value) {
            addCriterion("investment_amount <", value, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("investment_amount <=", value, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountIn(List<BigDecimal> values) {
            addCriterion("investment_amount in", values, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountNotIn(List<BigDecimal> values) {
            addCriterion("investment_amount not in", values, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("investment_amount between", value1, value2, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andInvestmentAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("investment_amount not between", value1, value2, "investmentAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIsNull() {
            addCriterion("return_amount is null");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIsNotNull() {
            addCriterion("return_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReturnAmountEqualTo(BigDecimal value) {
            addCriterion("return_amount =", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotEqualTo(BigDecimal value) {
            addCriterion("return_amount <>", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountGreaterThan(BigDecimal value) {
            addCriterion("return_amount >", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_amount >=", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountLessThan(BigDecimal value) {
            addCriterion("return_amount <", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_amount <=", value, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountIn(List<BigDecimal> values) {
            addCriterion("return_amount in", values, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotIn(List<BigDecimal> values) {
            addCriterion("return_amount not in", values, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_amount between", value1, value2, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andReturnAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_amount not between", value1, value2, "returnAmount");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNull() {
            addCriterion("gmt_create is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIsNotNull() {
            addCriterion("gmt_create is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreateEqualTo(Date value) {
            addCriterion("gmt_create =", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotEqualTo(Date value) {
            addCriterion("gmt_create <>", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThan(Date value) {
            addCriterion("gmt_create >", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_create >=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThan(Date value) {
            addCriterion("gmt_create <", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
            addCriterion("gmt_create <=", value, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateIn(List<Date> values) {
            addCriterion("gmt_create in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotIn(List<Date> values) {
            addCriterion("gmt_create not in", values, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateBetween(Date value1, Date value2) {
            addCriterion("gmt_create between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
            addCriterion("gmt_create not between", value1, value2, "gmtCreate");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNull() {
            addCriterion("gmt_modify is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIsNotNull() {
            addCriterion("gmt_modify is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifyEqualTo(Date value) {
            addCriterion("gmt_modify =", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotEqualTo(Date value) {
            addCriterion("gmt_modify <>", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThan(Date value) {
            addCriterion("gmt_modify >", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyGreaterThanOrEqualTo(Date value) {
            addCriterion("gmt_modify >=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThan(Date value) {
            addCriterion("gmt_modify <", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyLessThanOrEqualTo(Date value) {
            addCriterion("gmt_modify <=", value, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyIn(List<Date> values) {
            addCriterion("gmt_modify in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotIn(List<Date> values) {
            addCriterion("gmt_modify not in", values, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyBetween(Date value1, Date value2) {
            addCriterion("gmt_modify between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtModifyNotBetween(Date value1, Date value2) {
            addCriterion("gmt_modify not between", value1, value2, "gmtModify");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorIsNull() {
            addCriterion("gmt_creator is null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorIsNotNull() {
            addCriterion("gmt_creator is not null");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorEqualTo(String value) {
            addCriterion("gmt_creator =", value, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorNotEqualTo(String value) {
            addCriterion("gmt_creator <>", value, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorGreaterThan(String value) {
            addCriterion("gmt_creator >", value, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorGreaterThanOrEqualTo(String value) {
            addCriterion("gmt_creator >=", value, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorLessThan(String value) {
            addCriterion("gmt_creator <", value, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorLessThanOrEqualTo(String value) {
            addCriterion("gmt_creator <=", value, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorLike(String value) {
            addCriterion("gmt_creator like", value, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorNotLike(String value) {
            addCriterion("gmt_creator not like", value, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorIn(List<String> values) {
            addCriterion("gmt_creator in", values, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorNotIn(List<String> values) {
            addCriterion("gmt_creator not in", values, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorBetween(String value1, String value2) {
            addCriterion("gmt_creator between", value1, value2, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtCreatorNotBetween(String value1, String value2) {
            addCriterion("gmt_creator not between", value1, value2, "gmtCreator");
            return (Criteria) this;
        }

        public Criteria andGmtModifierIsNull() {
            addCriterion("gmt_modifier is null");
            return (Criteria) this;
        }

        public Criteria andGmtModifierIsNotNull() {
            addCriterion("gmt_modifier is not null");
            return (Criteria) this;
        }

        public Criteria andGmtModifierEqualTo(String value) {
            addCriterion("gmt_modifier =", value, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierNotEqualTo(String value) {
            addCriterion("gmt_modifier <>", value, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierGreaterThan(String value) {
            addCriterion("gmt_modifier >", value, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierGreaterThanOrEqualTo(String value) {
            addCriterion("gmt_modifier >=", value, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierLessThan(String value) {
            addCriterion("gmt_modifier <", value, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierLessThanOrEqualTo(String value) {
            addCriterion("gmt_modifier <=", value, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierLike(String value) {
            addCriterion("gmt_modifier like", value, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierNotLike(String value) {
            addCriterion("gmt_modifier not like", value, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierIn(List<String> values) {
            addCriterion("gmt_modifier in", values, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierNotIn(List<String> values) {
            addCriterion("gmt_modifier not in", values, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierBetween(String value1, String value2) {
            addCriterion("gmt_modifier between", value1, value2, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andGmtModifierNotBetween(String value1, String value2) {
            addCriterion("gmt_modifier not between", value1, value2, "gmtModifier");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion implements Serializable {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
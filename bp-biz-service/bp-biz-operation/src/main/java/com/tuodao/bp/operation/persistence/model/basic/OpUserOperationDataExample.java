package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OpUserOperationDataExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OpUserOperationDataExample() {
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

        public Criteria andUserMobileIsNull() {
            addCriterion("user_mobile is null");
            return (Criteria) this;
        }

        public Criteria andUserMobileIsNotNull() {
            addCriterion("user_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andUserMobileEqualTo(String value) {
            addCriterion("user_mobile =", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotEqualTo(String value) {
            addCriterion("user_mobile <>", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileGreaterThan(String value) {
            addCriterion("user_mobile >", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileGreaterThanOrEqualTo(String value) {
            addCriterion("user_mobile >=", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLessThan(String value) {
            addCriterion("user_mobile <", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLessThanOrEqualTo(String value) {
            addCriterion("user_mobile <=", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileLike(String value) {
            addCriterion("user_mobile like", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotLike(String value) {
            addCriterion("user_mobile not like", value, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileIn(List<String> values) {
            addCriterion("user_mobile in", values, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotIn(List<String> values) {
            addCriterion("user_mobile not in", values, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileBetween(String value1, String value2) {
            addCriterion("user_mobile between", value1, value2, "userMobile");
            return (Criteria) this;
        }

        public Criteria andUserMobileNotBetween(String value1, String value2) {
            addCriterion("user_mobile not between", value1, value2, "userMobile");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountIsNull() {
            addCriterion("voucher_amount is null");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountIsNotNull() {
            addCriterion("voucher_amount is not null");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountEqualTo(BigDecimal value) {
            addCriterion("voucher_amount =", value, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountNotEqualTo(BigDecimal value) {
            addCriterion("voucher_amount <>", value, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountGreaterThan(BigDecimal value) {
            addCriterion("voucher_amount >", value, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("voucher_amount >=", value, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountLessThan(BigDecimal value) {
            addCriterion("voucher_amount <", value, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("voucher_amount <=", value, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountIn(List<BigDecimal> values) {
            addCriterion("voucher_amount in", values, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountNotIn(List<BigDecimal> values) {
            addCriterion("voucher_amount not in", values, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("voucher_amount between", value1, value2, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andVoucherAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("voucher_amount not between", value1, value2, "voucherAmount");
            return (Criteria) this;
        }

        public Criteria andUsableScoresIsNull() {
            addCriterion("usable_scores is null");
            return (Criteria) this;
        }

        public Criteria andUsableScoresIsNotNull() {
            addCriterion("usable_scores is not null");
            return (Criteria) this;
        }

        public Criteria andUsableScoresEqualTo(Integer value) {
            addCriterion("usable_scores =", value, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresNotEqualTo(Integer value) {
            addCriterion("usable_scores <>", value, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresGreaterThan(Integer value) {
            addCriterion("usable_scores >", value, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresGreaterThanOrEqualTo(Integer value) {
            addCriterion("usable_scores >=", value, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresLessThan(Integer value) {
            addCriterion("usable_scores <", value, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresLessThanOrEqualTo(Integer value) {
            addCriterion("usable_scores <=", value, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresIn(List<Integer> values) {
            addCriterion("usable_scores in", values, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresNotIn(List<Integer> values) {
            addCriterion("usable_scores not in", values, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresBetween(Integer value1, Integer value2) {
            addCriterion("usable_scores between", value1, value2, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableScoresNotBetween(Integer value1, Integer value2) {
            addCriterion("usable_scores not between", value1, value2, "usableScores");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherIsNull() {
            addCriterion("usable_voucher is null");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherIsNotNull() {
            addCriterion("usable_voucher is not null");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherEqualTo(Integer value) {
            addCriterion("usable_voucher =", value, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherNotEqualTo(Integer value) {
            addCriterion("usable_voucher <>", value, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherGreaterThan(Integer value) {
            addCriterion("usable_voucher >", value, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherGreaterThanOrEqualTo(Integer value) {
            addCriterion("usable_voucher >=", value, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherLessThan(Integer value) {
            addCriterion("usable_voucher <", value, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherLessThanOrEqualTo(Integer value) {
            addCriterion("usable_voucher <=", value, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherIn(List<Integer> values) {
            addCriterion("usable_voucher in", values, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherNotIn(List<Integer> values) {
            addCriterion("usable_voucher not in", values, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherBetween(Integer value1, Integer value2) {
            addCriterion("usable_voucher between", value1, value2, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsableVoucherNotBetween(Integer value1, Integer value2) {
            addCriterion("usable_voucher not between", value1, value2, "usableVoucher");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponIsNull() {
            addCriterion("usable_pate_coupon is null");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponIsNotNull() {
            addCriterion("usable_pate_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponEqualTo(Integer value) {
            addCriterion("usable_pate_coupon =", value, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponNotEqualTo(Integer value) {
            addCriterion("usable_pate_coupon <>", value, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponGreaterThan(Integer value) {
            addCriterion("usable_pate_coupon >", value, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponGreaterThanOrEqualTo(Integer value) {
            addCriterion("usable_pate_coupon >=", value, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponLessThan(Integer value) {
            addCriterion("usable_pate_coupon <", value, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponLessThanOrEqualTo(Integer value) {
            addCriterion("usable_pate_coupon <=", value, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponIn(List<Integer> values) {
            addCriterion("usable_pate_coupon in", values, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponNotIn(List<Integer> values) {
            addCriterion("usable_pate_coupon not in", values, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponBetween(Integer value1, Integer value2) {
            addCriterion("usable_pate_coupon between", value1, value2, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsablePateCouponNotBetween(Integer value1, Integer value2) {
            addCriterion("usable_pate_coupon not between", value1, value2, "usablePateCoupon");
            return (Criteria) this;
        }

        public Criteria andUsableGoldIsNull() {
            addCriterion("usable_gold is null");
            return (Criteria) this;
        }

        public Criteria andUsableGoldIsNotNull() {
            addCriterion("usable_gold is not null");
            return (Criteria) this;
        }

        public Criteria andUsableGoldEqualTo(Integer value) {
            addCriterion("usable_gold =", value, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldNotEqualTo(Integer value) {
            addCriterion("usable_gold <>", value, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldGreaterThan(Integer value) {
            addCriterion("usable_gold >", value, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("usable_gold >=", value, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldLessThan(Integer value) {
            addCriterion("usable_gold <", value, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldLessThanOrEqualTo(Integer value) {
            addCriterion("usable_gold <=", value, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldIn(List<Integer> values) {
            addCriterion("usable_gold in", values, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldNotIn(List<Integer> values) {
            addCriterion("usable_gold not in", values, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldBetween(Integer value1, Integer value2) {
            addCriterion("usable_gold between", value1, value2, "usableGold");
            return (Criteria) this;
        }

        public Criteria andUsableGoldNotBetween(Integer value1, Integer value2) {
            addCriterion("usable_gold not between", value1, value2, "usableGold");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesIsNull() {
            addCriterion("continuous_sign_times is null");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesIsNotNull() {
            addCriterion("continuous_sign_times is not null");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesEqualTo(Integer value) {
            addCriterion("continuous_sign_times =", value, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesNotEqualTo(Integer value) {
            addCriterion("continuous_sign_times <>", value, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesGreaterThan(Integer value) {
            addCriterion("continuous_sign_times >", value, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("continuous_sign_times >=", value, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesLessThan(Integer value) {
            addCriterion("continuous_sign_times <", value, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesLessThanOrEqualTo(Integer value) {
            addCriterion("continuous_sign_times <=", value, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesIn(List<Integer> values) {
            addCriterion("continuous_sign_times in", values, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesNotIn(List<Integer> values) {
            addCriterion("continuous_sign_times not in", values, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesBetween(Integer value1, Integer value2) {
            addCriterion("continuous_sign_times between", value1, value2, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andContinuousSignTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("continuous_sign_times not between", value1, value2, "continuousSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesIsNull() {
            addCriterion("cumulative_sign_times is null");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesIsNotNull() {
            addCriterion("cumulative_sign_times is not null");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesEqualTo(Integer value) {
            addCriterion("cumulative_sign_times =", value, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesNotEqualTo(Integer value) {
            addCriterion("cumulative_sign_times <>", value, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesGreaterThan(Integer value) {
            addCriterion("cumulative_sign_times >", value, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("cumulative_sign_times >=", value, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesLessThan(Integer value) {
            addCriterion("cumulative_sign_times <", value, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesLessThanOrEqualTo(Integer value) {
            addCriterion("cumulative_sign_times <=", value, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesIn(List<Integer> values) {
            addCriterion("cumulative_sign_times in", values, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesNotIn(List<Integer> values) {
            addCriterion("cumulative_sign_times not in", values, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesBetween(Integer value1, Integer value2) {
            addCriterion("cumulative_sign_times between", value1, value2, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andCumulativeSignTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("cumulative_sign_times not between", value1, value2, "cumulativeSignTimes");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberIsNull() {
            addCriterion("free_get_number is null");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberIsNotNull() {
            addCriterion("free_get_number is not null");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberEqualTo(Integer value) {
            addCriterion("free_get_number =", value, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberNotEqualTo(Integer value) {
            addCriterion("free_get_number <>", value, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberGreaterThan(Integer value) {
            addCriterion("free_get_number >", value, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_get_number >=", value, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberLessThan(Integer value) {
            addCriterion("free_get_number <", value, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberLessThanOrEqualTo(Integer value) {
            addCriterion("free_get_number <=", value, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberIn(List<Integer> values) {
            addCriterion("free_get_number in", values, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberNotIn(List<Integer> values) {
            addCriterion("free_get_number not in", values, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberBetween(Integer value1, Integer value2) {
            addCriterion("free_get_number between", value1, value2, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("free_get_number not between", value1, value2, "freeGetNumber");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualIsNull() {
            addCriterion("free_get_number_perpetual is null");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualIsNotNull() {
            addCriterion("free_get_number_perpetual is not null");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualEqualTo(Integer value) {
            addCriterion("free_get_number_perpetual =", value, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualNotEqualTo(Integer value) {
            addCriterion("free_get_number_perpetual <>", value, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualGreaterThan(Integer value) {
            addCriterion("free_get_number_perpetual >", value, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_get_number_perpetual >=", value, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualLessThan(Integer value) {
            addCriterion("free_get_number_perpetual <", value, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualLessThanOrEqualTo(Integer value) {
            addCriterion("free_get_number_perpetual <=", value, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualIn(List<Integer> values) {
            addCriterion("free_get_number_perpetual in", values, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualNotIn(List<Integer> values) {
            addCriterion("free_get_number_perpetual not in", values, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualBetween(Integer value1, Integer value2) {
            addCriterion("free_get_number_perpetual between", value1, value2, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberPerpetualNotBetween(Integer value1, Integer value2) {
            addCriterion("free_get_number_perpetual not between", value1, value2, "freeGetNumberPerpetual");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireIsNull() {
            addCriterion("free_get_number_expire is null");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireIsNotNull() {
            addCriterion("free_get_number_expire is not null");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireEqualTo(Integer value) {
            addCriterion("free_get_number_expire =", value, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireNotEqualTo(Integer value) {
            addCriterion("free_get_number_expire <>", value, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireGreaterThan(Integer value) {
            addCriterion("free_get_number_expire >", value, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireGreaterThanOrEqualTo(Integer value) {
            addCriterion("free_get_number_expire >=", value, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireLessThan(Integer value) {
            addCriterion("free_get_number_expire <", value, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireLessThanOrEqualTo(Integer value) {
            addCriterion("free_get_number_expire <=", value, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireIn(List<Integer> values) {
            addCriterion("free_get_number_expire in", values, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireNotIn(List<Integer> values) {
            addCriterion("free_get_number_expire not in", values, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireBetween(Integer value1, Integer value2) {
            addCriterion("free_get_number_expire between", value1, value2, "freeGetNumberExpire");
            return (Criteria) this;
        }

        public Criteria andFreeGetNumberExpireNotBetween(Integer value1, Integer value2) {
            addCriterion("free_get_number_expire not between", value1, value2, "freeGetNumberExpire");
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
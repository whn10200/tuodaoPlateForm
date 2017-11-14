package com.tuodao.bp.traningcenter.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowTransferExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BorrowTransferExample() {
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

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
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

        public Criteria andTenderIdIsNull() {
            addCriterion("tender_id is null");
            return (Criteria) this;
        }

        public Criteria andTenderIdIsNotNull() {
            addCriterion("tender_id is not null");
            return (Criteria) this;
        }

        public Criteria andTenderIdEqualTo(Integer value) {
            addCriterion("tender_id =", value, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdNotEqualTo(Integer value) {
            addCriterion("tender_id <>", value, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdGreaterThan(Integer value) {
            addCriterion("tender_id >", value, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("tender_id >=", value, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdLessThan(Integer value) {
            addCriterion("tender_id <", value, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdLessThanOrEqualTo(Integer value) {
            addCriterion("tender_id <=", value, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdIn(List<Integer> values) {
            addCriterion("tender_id in", values, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdNotIn(List<Integer> values) {
            addCriterion("tender_id not in", values, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdBetween(Integer value1, Integer value2) {
            addCriterion("tender_id between", value1, value2, "tenderId");
            return (Criteria) this;
        }

        public Criteria andTenderIdNotBetween(Integer value1, Integer value2) {
            addCriterion("tender_id not between", value1, value2, "tenderId");
            return (Criteria) this;
        }

        public Criteria andBorrowNameIsNull() {
            addCriterion("borrow_name is null");
            return (Criteria) this;
        }

        public Criteria andBorrowNameIsNotNull() {
            addCriterion("borrow_name is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowNameEqualTo(String value) {
            addCriterion("borrow_name =", value, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameNotEqualTo(String value) {
            addCriterion("borrow_name <>", value, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameGreaterThan(String value) {
            addCriterion("borrow_name >", value, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_name >=", value, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameLessThan(String value) {
            addCriterion("borrow_name <", value, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameLessThanOrEqualTo(String value) {
            addCriterion("borrow_name <=", value, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameLike(String value) {
            addCriterion("borrow_name like", value, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameNotLike(String value) {
            addCriterion("borrow_name not like", value, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameIn(List<String> values) {
            addCriterion("borrow_name in", values, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameNotIn(List<String> values) {
            addCriterion("borrow_name not in", values, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameBetween(String value1, String value2) {
            addCriterion("borrow_name between", value1, value2, "borrowName");
            return (Criteria) this;
        }

        public Criteria andBorrowNameNotBetween(String value1, String value2) {
            addCriterion("borrow_name not between", value1, value2, "borrowName");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(BigDecimal value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(BigDecimal value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(BigDecimal value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(BigDecimal value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<BigDecimal> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<BigDecimal> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountYesIsNull() {
            addCriterion("account_yes is null");
            return (Criteria) this;
        }

        public Criteria andAccountYesIsNotNull() {
            addCriterion("account_yes is not null");
            return (Criteria) this;
        }

        public Criteria andAccountYesEqualTo(BigDecimal value) {
            addCriterion("account_yes =", value, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesNotEqualTo(BigDecimal value) {
            addCriterion("account_yes <>", value, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesGreaterThan(BigDecimal value) {
            addCriterion("account_yes >", value, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_yes >=", value, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesLessThan(BigDecimal value) {
            addCriterion("account_yes <", value, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_yes <=", value, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesIn(List<BigDecimal> values) {
            addCriterion("account_yes in", values, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesNotIn(List<BigDecimal> values) {
            addCriterion("account_yes not in", values, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_yes between", value1, value2, "accountYes");
            return (Criteria) this;
        }

        public Criteria andAccountYesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_yes not between", value1, value2, "accountYes");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNull() {
            addCriterion("period is null");
            return (Criteria) this;
        }

        public Criteria andPeriodIsNotNull() {
            addCriterion("period is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodEqualTo(Integer value) {
            addCriterion("period =", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotEqualTo(Integer value) {
            addCriterion("period <>", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThan(Integer value) {
            addCriterion("period >", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("period >=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThan(Integer value) {
            addCriterion("period <", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("period <=", value, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodIn(List<Integer> values) {
            addCriterion("period in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotIn(List<Integer> values) {
            addCriterion("period not in", values, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodBetween(Integer value1, Integer value2) {
            addCriterion("period between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("period not between", value1, value2, "period");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNull() {
            addCriterion("period_type is null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIsNotNull() {
            addCriterion("period_type is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeEqualTo(Integer value) {
            addCriterion("period_type =", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotEqualTo(Integer value) {
            addCriterion("period_type <>", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThan(Integer value) {
            addCriterion("period_type >", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("period_type >=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThan(Integer value) {
            addCriterion("period_type <", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeLessThanOrEqualTo(Integer value) {
            addCriterion("period_type <=", value, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeIn(List<Integer> values) {
            addCriterion("period_type in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotIn(List<Integer> values) {
            addCriterion("period_type not in", values, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeBetween(Integer value1, Integer value2) {
            addCriterion("period_type between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andPeriodTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("period_type not between", value1, value2, "periodType");
            return (Criteria) this;
        }

        public Criteria andAprIsNull() {
            addCriterion("apr is null");
            return (Criteria) this;
        }

        public Criteria andAprIsNotNull() {
            addCriterion("apr is not null");
            return (Criteria) this;
        }

        public Criteria andAprEqualTo(BigDecimal value) {
            addCriterion("apr =", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotEqualTo(BigDecimal value) {
            addCriterion("apr <>", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprGreaterThan(BigDecimal value) {
            addCriterion("apr >", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("apr >=", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprLessThan(BigDecimal value) {
            addCriterion("apr <", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprLessThanOrEqualTo(BigDecimal value) {
            addCriterion("apr <=", value, "apr");
            return (Criteria) this;
        }

        public Criteria andAprIn(List<BigDecimal> values) {
            addCriterion("apr in", values, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotIn(List<BigDecimal> values) {
            addCriterion("apr not in", values, "apr");
            return (Criteria) this;
        }

        public Criteria andAprBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apr between", value1, value2, "apr");
            return (Criteria) this;
        }

        public Criteria andAprNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("apr not between", value1, value2, "apr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprIsNull() {
            addCriterion("platform_apr is null");
            return (Criteria) this;
        }

        public Criteria andPlatformAprIsNotNull() {
            addCriterion("platform_apr is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformAprEqualTo(BigDecimal value) {
            addCriterion("platform_apr =", value, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprNotEqualTo(BigDecimal value) {
            addCriterion("platform_apr <>", value, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprGreaterThan(BigDecimal value) {
            addCriterion("platform_apr >", value, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_apr >=", value, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprLessThan(BigDecimal value) {
            addCriterion("platform_apr <", value, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprLessThanOrEqualTo(BigDecimal value) {
            addCriterion("platform_apr <=", value, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprIn(List<BigDecimal> values) {
            addCriterion("platform_apr in", values, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprNotIn(List<BigDecimal> values) {
            addCriterion("platform_apr not in", values, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_apr between", value1, value2, "platformApr");
            return (Criteria) this;
        }

        public Criteria andPlatformAprNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("platform_apr not between", value1, value2, "platformApr");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeIsNull() {
            addCriterion("rayment_type is null");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeIsNotNull() {
            addCriterion("rayment_type is not null");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeEqualTo(Integer value) {
            addCriterion("rayment_type =", value, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeNotEqualTo(Integer value) {
            addCriterion("rayment_type <>", value, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeGreaterThan(Integer value) {
            addCriterion("rayment_type >", value, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("rayment_type >=", value, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeLessThan(Integer value) {
            addCriterion("rayment_type <", value, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeLessThanOrEqualTo(Integer value) {
            addCriterion("rayment_type <=", value, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeIn(List<Integer> values) {
            addCriterion("rayment_type in", values, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeNotIn(List<Integer> values) {
            addCriterion("rayment_type not in", values, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeBetween(Integer value1, Integer value2) {
            addCriterion("rayment_type between", value1, value2, "raymentType");
            return (Criteria) this;
        }

        public Criteria andRaymentTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("rayment_type not between", value1, value2, "raymentType");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdIsNull() {
            addCriterion("pre_borrow_id is null");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdIsNotNull() {
            addCriterion("pre_borrow_id is not null");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdEqualTo(Integer value) {
            addCriterion("pre_borrow_id =", value, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdNotEqualTo(Integer value) {
            addCriterion("pre_borrow_id <>", value, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdGreaterThan(Integer value) {
            addCriterion("pre_borrow_id >", value, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pre_borrow_id >=", value, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdLessThan(Integer value) {
            addCriterion("pre_borrow_id <", value, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdLessThanOrEqualTo(Integer value) {
            addCriterion("pre_borrow_id <=", value, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdIn(List<Integer> values) {
            addCriterion("pre_borrow_id in", values, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdNotIn(List<Integer> values) {
            addCriterion("pre_borrow_id not in", values, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdBetween(Integer value1, Integer value2) {
            addCriterion("pre_borrow_id between", value1, value2, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andPreBorrowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pre_borrow_id not between", value1, value2, "preBorrowId");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Date value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Date value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Date value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Date value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Date> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Date> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Date value1, Date value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andAccountMinIsNull() {
            addCriterion("account_min is null");
            return (Criteria) this;
        }

        public Criteria andAccountMinIsNotNull() {
            addCriterion("account_min is not null");
            return (Criteria) this;
        }

        public Criteria andAccountMinEqualTo(BigDecimal value) {
            addCriterion("account_min =", value, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinNotEqualTo(BigDecimal value) {
            addCriterion("account_min <>", value, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinGreaterThan(BigDecimal value) {
            addCriterion("account_min >", value, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_min >=", value, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinLessThan(BigDecimal value) {
            addCriterion("account_min <", value, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_min <=", value, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinIn(List<BigDecimal> values) {
            addCriterion("account_min in", values, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinNotIn(List<BigDecimal> values) {
            addCriterion("account_min not in", values, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_min between", value1, value2, "accountMin");
            return (Criteria) this;
        }

        public Criteria andAccountMinNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_min not between", value1, value2, "accountMin");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(BigDecimal value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(BigDecimal value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(BigDecimal value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(BigDecimal value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<BigDecimal> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<BigDecimal> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIsNull() {
            addCriterion("verify_time is null");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIsNotNull() {
            addCriterion("verify_time is not null");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeEqualTo(Date value) {
            addCriterion("verify_time =", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotEqualTo(Date value) {
            addCriterion("verify_time <>", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeGreaterThan(Date value) {
            addCriterion("verify_time >", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("verify_time >=", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeLessThan(Date value) {
            addCriterion("verify_time <", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeLessThanOrEqualTo(Date value) {
            addCriterion("verify_time <=", value, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeIn(List<Date> values) {
            addCriterion("verify_time in", values, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotIn(List<Date> values) {
            addCriterion("verify_time not in", values, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeBetween(Date value1, Date value2) {
            addCriterion("verify_time between", value1, value2, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andVerifyTimeNotBetween(Date value1, Date value2) {
            addCriterion("verify_time not between", value1, value2, "verifyTime");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNull() {
            addCriterion("remarks is null");
            return (Criteria) this;
        }

        public Criteria andRemarksIsNotNull() {
            addCriterion("remarks is not null");
            return (Criteria) this;
        }

        public Criteria andRemarksEqualTo(String value) {
            addCriterion("remarks =", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotEqualTo(String value) {
            addCriterion("remarks <>", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThan(String value) {
            addCriterion("remarks >", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("remarks >=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThan(String value) {
            addCriterion("remarks <", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("remarks <=", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksLike(String value) {
            addCriterion("remarks like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotLike(String value) {
            addCriterion("remarks not like", value, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksIn(List<String> values) {
            addCriterion("remarks in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotIn(List<String> values) {
            addCriterion("remarks not in", values, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksBetween(String value1, String value2) {
            addCriterion("remarks between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("remarks not between", value1, value2, "remarks");
            return (Criteria) this;
        }

        public Criteria andTransferWorthIsNull() {
            addCriterion("transfer_worth is null");
            return (Criteria) this;
        }

        public Criteria andTransferWorthIsNotNull() {
            addCriterion("transfer_worth is not null");
            return (Criteria) this;
        }

        public Criteria andTransferWorthEqualTo(BigDecimal value) {
            addCriterion("transfer_worth =", value, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthNotEqualTo(BigDecimal value) {
            addCriterion("transfer_worth <>", value, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthGreaterThan(BigDecimal value) {
            addCriterion("transfer_worth >", value, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("transfer_worth >=", value, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthLessThan(BigDecimal value) {
            addCriterion("transfer_worth <", value, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthLessThanOrEqualTo(BigDecimal value) {
            addCriterion("transfer_worth <=", value, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthIn(List<BigDecimal> values) {
            addCriterion("transfer_worth in", values, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthNotIn(List<BigDecimal> values) {
            addCriterion("transfer_worth not in", values, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transfer_worth between", value1, value2, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andTransferWorthNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("transfer_worth not between", value1, value2, "transferWorth");
            return (Criteria) this;
        }

        public Criteria andToSourceIsNull() {
            addCriterion("to_source is null");
            return (Criteria) this;
        }

        public Criteria andToSourceIsNotNull() {
            addCriterion("to_source is not null");
            return (Criteria) this;
        }

        public Criteria andToSourceEqualTo(Integer value) {
            addCriterion("to_source =", value, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceNotEqualTo(Integer value) {
            addCriterion("to_source <>", value, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceGreaterThan(Integer value) {
            addCriterion("to_source >", value, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("to_source >=", value, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceLessThan(Integer value) {
            addCriterion("to_source <", value, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceLessThanOrEqualTo(Integer value) {
            addCriterion("to_source <=", value, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceIn(List<Integer> values) {
            addCriterion("to_source in", values, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceNotIn(List<Integer> values) {
            addCriterion("to_source not in", values, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceBetween(Integer value1, Integer value2) {
            addCriterion("to_source between", value1, value2, "toSource");
            return (Criteria) this;
        }

        public Criteria andToSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("to_source not between", value1, value2, "toSource");
            return (Criteria) this;
        }

        public Criteria andProfitIsNull() {
            addCriterion("profit is null");
            return (Criteria) this;
        }

        public Criteria andProfitIsNotNull() {
            addCriterion("profit is not null");
            return (Criteria) this;
        }

        public Criteria andProfitEqualTo(BigDecimal value) {
            addCriterion("profit =", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotEqualTo(BigDecimal value) {
            addCriterion("profit <>", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThan(BigDecimal value) {
            addCriterion("profit >", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("profit >=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThan(BigDecimal value) {
            addCriterion("profit <", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitLessThanOrEqualTo(BigDecimal value) {
            addCriterion("profit <=", value, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitIn(List<BigDecimal> values) {
            addCriterion("profit in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotIn(List<BigDecimal> values) {
            addCriterion("profit not in", values, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andProfitNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("profit not between", value1, value2, "profit");
            return (Criteria) this;
        }

        public Criteria andReturnInterestIsNull() {
            addCriterion("return_interest is null");
            return (Criteria) this;
        }

        public Criteria andReturnInterestIsNotNull() {
            addCriterion("return_interest is not null");
            return (Criteria) this;
        }

        public Criteria andReturnInterestEqualTo(BigDecimal value) {
            addCriterion("return_interest =", value, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestNotEqualTo(BigDecimal value) {
            addCriterion("return_interest <>", value, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestGreaterThan(BigDecimal value) {
            addCriterion("return_interest >", value, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("return_interest >=", value, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestLessThan(BigDecimal value) {
            addCriterion("return_interest <", value, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestLessThanOrEqualTo(BigDecimal value) {
            addCriterion("return_interest <=", value, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestIn(List<BigDecimal> values) {
            addCriterion("return_interest in", values, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestNotIn(List<BigDecimal> values) {
            addCriterion("return_interest not in", values, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_interest between", value1, value2, "returnInterest");
            return (Criteria) this;
        }

        public Criteria andReturnInterestNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("return_interest not between", value1, value2, "returnInterest");
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
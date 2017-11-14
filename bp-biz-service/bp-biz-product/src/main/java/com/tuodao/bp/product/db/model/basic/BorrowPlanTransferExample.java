package com.tuodao.bp.product.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BorrowPlanTransferExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BorrowPlanTransferExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andAccountSuccessYesIsNull() {
            addCriterion("account_success_yes is null");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesIsNotNull() {
            addCriterion("account_success_yes is not null");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesEqualTo(BigDecimal value) {
            addCriterion("account_success_yes =", value, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesNotEqualTo(BigDecimal value) {
            addCriterion("account_success_yes <>", value, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesGreaterThan(BigDecimal value) {
            addCriterion("account_success_yes >", value, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("account_success_yes >=", value, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesLessThan(BigDecimal value) {
            addCriterion("account_success_yes <", value, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("account_success_yes <=", value, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesIn(List<BigDecimal> values) {
            addCriterion("account_success_yes in", values, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesNotIn(List<BigDecimal> values) {
            addCriterion("account_success_yes not in", values, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_success_yes between", value1, value2, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andAccountSuccessYesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("account_success_yes not between", value1, value2, "accountSuccessYes");
            return (Criteria) this;
        }

        public Criteria andStartPeriodIsNull() {
            addCriterion("start_period is null");
            return (Criteria) this;
        }

        public Criteria andStartPeriodIsNotNull() {
            addCriterion("start_period is not null");
            return (Criteria) this;
        }

        public Criteria andStartPeriodEqualTo(Integer value) {
            addCriterion("start_period =", value, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodNotEqualTo(Integer value) {
            addCriterion("start_period <>", value, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodGreaterThan(Integer value) {
            addCriterion("start_period >", value, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("start_period >=", value, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodLessThan(Integer value) {
            addCriterion("start_period <", value, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("start_period <=", value, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodIn(List<Integer> values) {
            addCriterion("start_period in", values, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodNotIn(List<Integer> values) {
            addCriterion("start_period not in", values, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodBetween(Integer value1, Integer value2) {
            addCriterion("start_period between", value1, value2, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andStartPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("start_period not between", value1, value2, "startPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodIsNull() {
            addCriterion("left_period is null");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodIsNotNull() {
            addCriterion("left_period is not null");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodEqualTo(Integer value) {
            addCriterion("left_period =", value, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodNotEqualTo(Integer value) {
            addCriterion("left_period <>", value, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodGreaterThan(Integer value) {
            addCriterion("left_period >", value, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("left_period >=", value, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodLessThan(Integer value) {
            addCriterion("left_period <", value, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("left_period <=", value, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodIn(List<Integer> values) {
            addCriterion("left_period in", values, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodNotIn(List<Integer> values) {
            addCriterion("left_period not in", values, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodBetween(Integer value1, Integer value2) {
            addCriterion("left_period between", value1, value2, "leftPeriod");
            return (Criteria) this;
        }

        public Criteria andLeftPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("left_period not between", value1, value2, "leftPeriod");
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

        public Criteria andAwardAprIsNull() {
            addCriterion("award_apr is null");
            return (Criteria) this;
        }

        public Criteria andAwardAprIsNotNull() {
            addCriterion("award_apr is not null");
            return (Criteria) this;
        }

        public Criteria andAwardAprEqualTo(BigDecimal value) {
            addCriterion("award_apr =", value, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprNotEqualTo(BigDecimal value) {
            addCriterion("award_apr <>", value, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprGreaterThan(BigDecimal value) {
            addCriterion("award_apr >", value, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("award_apr >=", value, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprLessThan(BigDecimal value) {
            addCriterion("award_apr <", value, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprLessThanOrEqualTo(BigDecimal value) {
            addCriterion("award_apr <=", value, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprIn(List<BigDecimal> values) {
            addCriterion("award_apr in", values, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprNotIn(List<BigDecimal> values) {
            addCriterion("award_apr not in", values, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_apr between", value1, value2, "awardApr");
            return (Criteria) this;
        }

        public Criteria andAwardAprNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_apr not between", value1, value2, "awardApr");
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

        public Criteria andTransfeStartTimeIsNull() {
            addCriterion("transfe_start_time is null");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeIsNotNull() {
            addCriterion("transfe_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeEqualTo(Date value) {
            addCriterion("transfe_start_time =", value, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeNotEqualTo(Date value) {
            addCriterion("transfe_start_time <>", value, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeGreaterThan(Date value) {
            addCriterion("transfe_start_time >", value, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("transfe_start_time >=", value, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeLessThan(Date value) {
            addCriterion("transfe_start_time <", value, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("transfe_start_time <=", value, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeIn(List<Date> values) {
            addCriterion("transfe_start_time in", values, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeNotIn(List<Date> values) {
            addCriterion("transfe_start_time not in", values, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeBetween(Date value1, Date value2) {
            addCriterion("transfe_start_time between", value1, value2, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andTransfeStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("transfe_start_time not between", value1, value2, "transfeStartTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIsNull() {
            addCriterion("success_time is null");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIsNotNull() {
            addCriterion("success_time is not null");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeEqualTo(Date value) {
            addCriterion("success_time =", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotEqualTo(Date value) {
            addCriterion("success_time <>", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeGreaterThan(Date value) {
            addCriterion("success_time >", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("success_time >=", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeLessThan(Date value) {
            addCriterion("success_time <", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeLessThanOrEqualTo(Date value) {
            addCriterion("success_time <=", value, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeIn(List<Date> values) {
            addCriterion("success_time in", values, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotIn(List<Date> values) {
            addCriterion("success_time not in", values, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeBetween(Date value1, Date value2) {
            addCriterion("success_time between", value1, value2, "successTime");
            return (Criteria) this;
        }

        public Criteria andSuccessTimeNotBetween(Date value1, Date value2) {
            addCriterion("success_time not between", value1, value2, "successTime");
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
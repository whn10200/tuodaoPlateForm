package com.tuodao.bp.product.db.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BorrowMappingBankExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public BorrowMappingBankExample() {
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

        public Criteria andBorrowIdIsNull() {
            addCriterion("borrow_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowIdIsNotNull() {
            addCriterion("borrow_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowIdEqualTo(Integer value) {
            addCriterion("borrow_id =", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotEqualTo(Integer value) {
            addCriterion("borrow_id <>", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdGreaterThan(Integer value) {
            addCriterion("borrow_id >", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("borrow_id >=", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdLessThan(Integer value) {
            addCriterion("borrow_id <", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdLessThanOrEqualTo(Integer value) {
            addCriterion("borrow_id <=", value, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdIn(List<Integer> values) {
            addCriterion("borrow_id in", values, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotIn(List<Integer> values) {
            addCriterion("borrow_id not in", values, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdBetween(Integer value1, Integer value2) {
            addCriterion("borrow_id between", value1, value2, "borrowId");
            return (Criteria) this;
        }

        public Criteria andBorrowIdNotBetween(Integer value1, Integer value2) {
            addCriterion("borrow_id not between", value1, value2, "borrowId");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryIsNull() {
            addCriterion("is_compensatory is null");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryIsNotNull() {
            addCriterion("is_compensatory is not null");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryEqualTo(Integer value) {
            addCriterion("is_compensatory =", value, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryNotEqualTo(Integer value) {
            addCriterion("is_compensatory <>", value, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryGreaterThan(Integer value) {
            addCriterion("is_compensatory >", value, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_compensatory >=", value, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryLessThan(Integer value) {
            addCriterion("is_compensatory <", value, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryLessThanOrEqualTo(Integer value) {
            addCriterion("is_compensatory <=", value, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryIn(List<Integer> values) {
            addCriterion("is_compensatory in", values, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryNotIn(List<Integer> values) {
            addCriterion("is_compensatory not in", values, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryBetween(Integer value1, Integer value2) {
            addCriterion("is_compensatory between", value1, value2, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andIsCompensatoryNotBetween(Integer value1, Integer value2) {
            addCriterion("is_compensatory not between", value1, value2, "isCompensatory");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusIsNull() {
            addCriterion("borrow_bank_status is null");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusIsNotNull() {
            addCriterion("borrow_bank_status is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusEqualTo(Integer value) {
            addCriterion("borrow_bank_status =", value, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusNotEqualTo(Integer value) {
            addCriterion("borrow_bank_status <>", value, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusGreaterThan(Integer value) {
            addCriterion("borrow_bank_status >", value, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("borrow_bank_status >=", value, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusLessThan(Integer value) {
            addCriterion("borrow_bank_status <", value, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusLessThanOrEqualTo(Integer value) {
            addCriterion("borrow_bank_status <=", value, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusIn(List<Integer> values) {
            addCriterion("borrow_bank_status in", values, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusNotIn(List<Integer> values) {
            addCriterion("borrow_bank_status not in", values, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusBetween(Integer value1, Integer value2) {
            addCriterion("borrow_bank_status between", value1, value2, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowBankStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("borrow_bank_status not between", value1, value2, "borrowBankStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountIsNull() {
            addCriterion("compensatory_amount is null");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountIsNotNull() {
            addCriterion("compensatory_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountEqualTo(Long value) {
            addCriterion("compensatory_amount =", value, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountNotEqualTo(Long value) {
            addCriterion("compensatory_amount <>", value, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountGreaterThan(Long value) {
            addCriterion("compensatory_amount >", value, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("compensatory_amount >=", value, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountLessThan(Long value) {
            addCriterion("compensatory_amount <", value, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountLessThanOrEqualTo(Long value) {
            addCriterion("compensatory_amount <=", value, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountIn(List<Long> values) {
            addCriterion("compensatory_amount in", values, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountNotIn(List<Long> values) {
            addCriterion("compensatory_amount not in", values, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountBetween(Long value1, Long value2) {
            addCriterion("compensatory_amount between", value1, value2, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountNotBetween(Long value1, Long value2) {
            addCriterion("compensatory_amount not between", value1, value2, "compensatoryAmount");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesIsNull() {
            addCriterion("compensatory_amount_yes is null");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesIsNotNull() {
            addCriterion("compensatory_amount_yes is not null");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesEqualTo(Long value) {
            addCriterion("compensatory_amount_yes =", value, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesNotEqualTo(Long value) {
            addCriterion("compensatory_amount_yes <>", value, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesGreaterThan(Long value) {
            addCriterion("compensatory_amount_yes >", value, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesGreaterThanOrEqualTo(Long value) {
            addCriterion("compensatory_amount_yes >=", value, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesLessThan(Long value) {
            addCriterion("compensatory_amount_yes <", value, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesLessThanOrEqualTo(Long value) {
            addCriterion("compensatory_amount_yes <=", value, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesIn(List<Long> values) {
            addCriterion("compensatory_amount_yes in", values, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesNotIn(List<Long> values) {
            addCriterion("compensatory_amount_yes not in", values, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesBetween(Long value1, Long value2) {
            addCriterion("compensatory_amount_yes between", value1, value2, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryAmountYesNotBetween(Long value1, Long value2) {
            addCriterion("compensatory_amount_yes not between", value1, value2, "compensatoryAmountYes");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusIsNull() {
            addCriterion("compensatory_status is null");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusIsNotNull() {
            addCriterion("compensatory_status is not null");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusEqualTo(Integer value) {
            addCriterion("compensatory_status =", value, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusNotEqualTo(Integer value) {
            addCriterion("compensatory_status <>", value, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusGreaterThan(Integer value) {
            addCriterion("compensatory_status >", value, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("compensatory_status >=", value, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusLessThan(Integer value) {
            addCriterion("compensatory_status <", value, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusLessThanOrEqualTo(Integer value) {
            addCriterion("compensatory_status <=", value, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusIn(List<Integer> values) {
            addCriterion("compensatory_status in", values, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusNotIn(List<Integer> values) {
            addCriterion("compensatory_status not in", values, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusBetween(Integer value1, Integer value2) {
            addCriterion("compensatory_status between", value1, value2, "compensatoryStatus");
            return (Criteria) this;
        }

        public Criteria andCompensatoryStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("compensatory_status not between", value1, value2, "compensatoryStatus");
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
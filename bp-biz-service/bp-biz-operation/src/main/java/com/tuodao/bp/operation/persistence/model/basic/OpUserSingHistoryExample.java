package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OpUserSingHistoryExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OpUserSingHistoryExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
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

        public Criteria andSignTimeIsNull() {
            addCriterion("sign_time is null");
            return (Criteria) this;
        }

        public Criteria andSignTimeIsNotNull() {
            addCriterion("sign_time is not null");
            return (Criteria) this;
        }

        public Criteria andSignTimeEqualTo(Date value) {
            addCriterionForJDBCDate("sign_time =", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("sign_time <>", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("sign_time >", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sign_time >=", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeLessThan(Date value) {
            addCriterionForJDBCDate("sign_time <", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("sign_time <=", value, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeIn(List<Date> values) {
            addCriterionForJDBCDate("sign_time in", values, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("sign_time not in", values, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sign_time between", value1, value2, "signTime");
            return (Criteria) this;
        }

        public Criteria andSignTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("sign_time not between", value1, value2, "signTime");
            return (Criteria) this;
        }

        public Criteria andLastSingDateIsNull() {
            addCriterion("last_sing_date is null");
            return (Criteria) this;
        }

        public Criteria andLastSingDateIsNotNull() {
            addCriterion("last_sing_date is not null");
            return (Criteria) this;
        }

        public Criteria andLastSingDateEqualTo(Date value) {
            addCriterionForJDBCDate("last_sing_date =", value, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("last_sing_date <>", value, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateGreaterThan(Date value) {
            addCriterionForJDBCDate("last_sing_date >", value, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("last_sing_date >=", value, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateLessThan(Date value) {
            addCriterionForJDBCDate("last_sing_date <", value, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("last_sing_date <=", value, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateIn(List<Date> values) {
            addCriterionForJDBCDate("last_sing_date in", values, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("last_sing_date not in", values, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("last_sing_date between", value1, value2, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andLastSingDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("last_sing_date not between", value1, value2, "lastSingDate");
            return (Criteria) this;
        }

        public Criteria andIsContinuousIsNull() {
            addCriterion("is_continuous is null");
            return (Criteria) this;
        }

        public Criteria andIsContinuousIsNotNull() {
            addCriterion("is_continuous is not null");
            return (Criteria) this;
        }

        public Criteria andIsContinuousEqualTo(String value) {
            addCriterion("is_continuous =", value, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousNotEqualTo(String value) {
            addCriterion("is_continuous <>", value, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousGreaterThan(String value) {
            addCriterion("is_continuous >", value, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousGreaterThanOrEqualTo(String value) {
            addCriterion("is_continuous >=", value, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousLessThan(String value) {
            addCriterion("is_continuous <", value, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousLessThanOrEqualTo(String value) {
            addCriterion("is_continuous <=", value, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousLike(String value) {
            addCriterion("is_continuous like", value, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousNotLike(String value) {
            addCriterion("is_continuous not like", value, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousIn(List<String> values) {
            addCriterion("is_continuous in", values, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousNotIn(List<String> values) {
            addCriterion("is_continuous not in", values, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousBetween(String value1, String value2) {
            addCriterion("is_continuous between", value1, value2, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andIsContinuousNotBetween(String value1, String value2) {
            addCriterion("is_continuous not between", value1, value2, "isContinuous");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreIsNull() {
            addCriterion("this_sign_score is null");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreIsNotNull() {
            addCriterion("this_sign_score is not null");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreEqualTo(Integer value) {
            addCriterion("this_sign_score =", value, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreNotEqualTo(Integer value) {
            addCriterion("this_sign_score <>", value, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreGreaterThan(Integer value) {
            addCriterion("this_sign_score >", value, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("this_sign_score >=", value, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreLessThan(Integer value) {
            addCriterion("this_sign_score <", value, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreLessThanOrEqualTo(Integer value) {
            addCriterion("this_sign_score <=", value, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreIn(List<Integer> values) {
            addCriterion("this_sign_score in", values, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreNotIn(List<Integer> values) {
            addCriterion("this_sign_score not in", values, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreBetween(Integer value1, Integer value2) {
            addCriterion("this_sign_score between", value1, value2, "thisSignScore");
            return (Criteria) this;
        }

        public Criteria andThisSignScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("this_sign_score not between", value1, value2, "thisSignScore");
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
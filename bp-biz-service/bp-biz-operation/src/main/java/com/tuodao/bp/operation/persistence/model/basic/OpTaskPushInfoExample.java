package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OpTaskPushInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OpTaskPushInfoExample() {
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

        public Criteria andTriggerTimeIsNull() {
            addCriterion("trigger_time is null");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIsNotNull() {
            addCriterion("trigger_time is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeEqualTo(Integer value) {
            addCriterion("trigger_time =", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotEqualTo(Integer value) {
            addCriterion("trigger_time <>", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeGreaterThan(Integer value) {
            addCriterion("trigger_time >", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("trigger_time >=", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeLessThan(Integer value) {
            addCriterion("trigger_time <", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeLessThanOrEqualTo(Integer value) {
            addCriterion("trigger_time <=", value, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeIn(List<Integer> values) {
            addCriterion("trigger_time in", values, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotIn(List<Integer> values) {
            addCriterion("trigger_time not in", values, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeBetween(Integer value1, Integer value2) {
            addCriterion("trigger_time between", value1, value2, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andTriggerTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("trigger_time not between", value1, value2, "triggerTime");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksIsNull() {
            addCriterion("completed_tasks is null");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksIsNotNull() {
            addCriterion("completed_tasks is not null");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksEqualTo(String value) {
            addCriterion("completed_tasks =", value, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksNotEqualTo(String value) {
            addCriterion("completed_tasks <>", value, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksGreaterThan(String value) {
            addCriterion("completed_tasks >", value, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksGreaterThanOrEqualTo(String value) {
            addCriterion("completed_tasks >=", value, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksLessThan(String value) {
            addCriterion("completed_tasks <", value, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksLessThanOrEqualTo(String value) {
            addCriterion("completed_tasks <=", value, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksLike(String value) {
            addCriterion("completed_tasks like", value, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksNotLike(String value) {
            addCriterion("completed_tasks not like", value, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksIn(List<String> values) {
            addCriterion("completed_tasks in", values, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksNotIn(List<String> values) {
            addCriterion("completed_tasks not in", values, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksBetween(String value1, String value2) {
            addCriterion("completed_tasks between", value1, value2, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andCompletedTasksNotBetween(String value1, String value2) {
            addCriterion("completed_tasks not between", value1, value2, "completedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksIsNull() {
            addCriterion("incompleted_tasks is null");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksIsNotNull() {
            addCriterion("incompleted_tasks is not null");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksEqualTo(String value) {
            addCriterion("incompleted_tasks =", value, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksNotEqualTo(String value) {
            addCriterion("incompleted_tasks <>", value, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksGreaterThan(String value) {
            addCriterion("incompleted_tasks >", value, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksGreaterThanOrEqualTo(String value) {
            addCriterion("incompleted_tasks >=", value, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksLessThan(String value) {
            addCriterion("incompleted_tasks <", value, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksLessThanOrEqualTo(String value) {
            addCriterion("incompleted_tasks <=", value, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksLike(String value) {
            addCriterion("incompleted_tasks like", value, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksNotLike(String value) {
            addCriterion("incompleted_tasks not like", value, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksIn(List<String> values) {
            addCriterion("incompleted_tasks in", values, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksNotIn(List<String> values) {
            addCriterion("incompleted_tasks not in", values, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksBetween(String value1, String value2) {
            addCriterion("incompleted_tasks between", value1, value2, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andIncompletedTasksNotBetween(String value1, String value2) {
            addCriterion("incompleted_tasks not between", value1, value2, "incompletedTasks");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andSmsCodeIsNull() {
            addCriterion("sms_code is null");
            return (Criteria) this;
        }

        public Criteria andSmsCodeIsNotNull() {
            addCriterion("sms_code is not null");
            return (Criteria) this;
        }

        public Criteria andSmsCodeEqualTo(String value) {
            addCriterion("sms_code =", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeNotEqualTo(String value) {
            addCriterion("sms_code <>", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeGreaterThan(String value) {
            addCriterion("sms_code >", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sms_code >=", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeLessThan(String value) {
            addCriterion("sms_code <", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeLessThanOrEqualTo(String value) {
            addCriterion("sms_code <=", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeLike(String value) {
            addCriterion("sms_code like", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeNotLike(String value) {
            addCriterion("sms_code not like", value, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeIn(List<String> values) {
            addCriterion("sms_code in", values, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeNotIn(List<String> values) {
            addCriterion("sms_code not in", values, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeBetween(String value1, String value2) {
            addCriterion("sms_code between", value1, value2, "smsCode");
            return (Criteria) this;
        }

        public Criteria andSmsCodeNotBetween(String value1, String value2) {
            addCriterion("sms_code not between", value1, value2, "smsCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeIsNull() {
            addCriterion("push_code is null");
            return (Criteria) this;
        }

        public Criteria andPushCodeIsNotNull() {
            addCriterion("push_code is not null");
            return (Criteria) this;
        }

        public Criteria andPushCodeEqualTo(String value) {
            addCriterion("push_code =", value, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeNotEqualTo(String value) {
            addCriterion("push_code <>", value, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeGreaterThan(String value) {
            addCriterion("push_code >", value, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeGreaterThanOrEqualTo(String value) {
            addCriterion("push_code >=", value, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeLessThan(String value) {
            addCriterion("push_code <", value, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeLessThanOrEqualTo(String value) {
            addCriterion("push_code <=", value, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeLike(String value) {
            addCriterion("push_code like", value, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeNotLike(String value) {
            addCriterion("push_code not like", value, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeIn(List<String> values) {
            addCriterion("push_code in", values, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeNotIn(List<String> values) {
            addCriterion("push_code not in", values, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeBetween(String value1, String value2) {
            addCriterion("push_code between", value1, value2, "pushCode");
            return (Criteria) this;
        }

        public Criteria andPushCodeNotBetween(String value1, String value2) {
            addCriterion("push_code not between", value1, value2, "pushCode");
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
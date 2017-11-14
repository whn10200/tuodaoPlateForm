package com.tuodao.bp.common.persistence.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PushLogExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public PushLogExample() {
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

        public Criteria andPushToolIsNull() {
            addCriterion("push_tool is null");
            return (Criteria) this;
        }

        public Criteria andPushToolIsNotNull() {
            addCriterion("push_tool is not null");
            return (Criteria) this;
        }

        public Criteria andPushToolEqualTo(Integer value) {
            addCriterion("push_tool =", value, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolNotEqualTo(Integer value) {
            addCriterion("push_tool <>", value, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolGreaterThan(Integer value) {
            addCriterion("push_tool >", value, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_tool >=", value, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolLessThan(Integer value) {
            addCriterion("push_tool <", value, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolLessThanOrEqualTo(Integer value) {
            addCriterion("push_tool <=", value, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolIn(List<Integer> values) {
            addCriterion("push_tool in", values, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolNotIn(List<Integer> values) {
            addCriterion("push_tool not in", values, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolBetween(Integer value1, Integer value2) {
            addCriterion("push_tool between", value1, value2, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushToolNotBetween(Integer value1, Integer value2) {
            addCriterion("push_tool not between", value1, value2, "pushTool");
            return (Criteria) this;
        }

        public Criteria andPushObjectIsNull() {
            addCriterion("push_object is null");
            return (Criteria) this;
        }

        public Criteria andPushObjectIsNotNull() {
            addCriterion("push_object is not null");
            return (Criteria) this;
        }

        public Criteria andPushObjectEqualTo(Integer value) {
            addCriterion("push_object =", value, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectNotEqualTo(Integer value) {
            addCriterion("push_object <>", value, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectGreaterThan(Integer value) {
            addCriterion("push_object >", value, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectGreaterThanOrEqualTo(Integer value) {
            addCriterion("push_object >=", value, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectLessThan(Integer value) {
            addCriterion("push_object <", value, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectLessThanOrEqualTo(Integer value) {
            addCriterion("push_object <=", value, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectIn(List<Integer> values) {
            addCriterion("push_object in", values, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectNotIn(List<Integer> values) {
            addCriterion("push_object not in", values, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectBetween(Integer value1, Integer value2) {
            addCriterion("push_object between", value1, value2, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushObjectNotBetween(Integer value1, Integer value2) {
            addCriterion("push_object not between", value1, value2, "pushObject");
            return (Criteria) this;
        }

        public Criteria andPushAliasIsNull() {
            addCriterion("push_alias is null");
            return (Criteria) this;
        }

        public Criteria andPushAliasIsNotNull() {
            addCriterion("push_alias is not null");
            return (Criteria) this;
        }

        public Criteria andPushAliasEqualTo(String value) {
            addCriterion("push_alias =", value, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasNotEqualTo(String value) {
            addCriterion("push_alias <>", value, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasGreaterThan(String value) {
            addCriterion("push_alias >", value, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasGreaterThanOrEqualTo(String value) {
            addCriterion("push_alias >=", value, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasLessThan(String value) {
            addCriterion("push_alias <", value, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasLessThanOrEqualTo(String value) {
            addCriterion("push_alias <=", value, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasLike(String value) {
            addCriterion("push_alias like", value, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasNotLike(String value) {
            addCriterion("push_alias not like", value, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasIn(List<String> values) {
            addCriterion("push_alias in", values, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasNotIn(List<String> values) {
            addCriterion("push_alias not in", values, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasBetween(String value1, String value2) {
            addCriterion("push_alias between", value1, value2, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushAliasNotBetween(String value1, String value2) {
            addCriterion("push_alias not between", value1, value2, "pushAlias");
            return (Criteria) this;
        }

        public Criteria andPushContentIsNull() {
            addCriterion("push_content is null");
            return (Criteria) this;
        }

        public Criteria andPushContentIsNotNull() {
            addCriterion("push_content is not null");
            return (Criteria) this;
        }

        public Criteria andPushContentEqualTo(String value) {
            addCriterion("push_content =", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentNotEqualTo(String value) {
            addCriterion("push_content <>", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentGreaterThan(String value) {
            addCriterion("push_content >", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentGreaterThanOrEqualTo(String value) {
            addCriterion("push_content >=", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentLessThan(String value) {
            addCriterion("push_content <", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentLessThanOrEqualTo(String value) {
            addCriterion("push_content <=", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentLike(String value) {
            addCriterion("push_content like", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentNotLike(String value) {
            addCriterion("push_content not like", value, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentIn(List<String> values) {
            addCriterion("push_content in", values, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentNotIn(List<String> values) {
            addCriterion("push_content not in", values, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentBetween(String value1, String value2) {
            addCriterion("push_content between", value1, value2, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushContentNotBetween(String value1, String value2) {
            addCriterion("push_content not between", value1, value2, "pushContent");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNull() {
            addCriterion("push_time is null");
            return (Criteria) this;
        }

        public Criteria andPushTimeIsNotNull() {
            addCriterion("push_time is not null");
            return (Criteria) this;
        }

        public Criteria andPushTimeEqualTo(Date value) {
            addCriterion("push_time =", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotEqualTo(Date value) {
            addCriterion("push_time <>", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThan(Date value) {
            addCriterion("push_time >", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("push_time >=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThan(Date value) {
            addCriterion("push_time <", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("push_time <=", value, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeIn(List<Date> values) {
            addCriterion("push_time in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotIn(List<Date> values) {
            addCriterion("push_time not in", values, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeBetween(Date value1, Date value2) {
            addCriterion("push_time between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("push_time not between", value1, value2, "pushTime");
            return (Criteria) this;
        }

        public Criteria andPushResultIsNull() {
            addCriterion("push_result is null");
            return (Criteria) this;
        }

        public Criteria andPushResultIsNotNull() {
            addCriterion("push_result is not null");
            return (Criteria) this;
        }

        public Criteria andPushResultEqualTo(String value) {
            addCriterion("push_result =", value, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultNotEqualTo(String value) {
            addCriterion("push_result <>", value, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultGreaterThan(String value) {
            addCriterion("push_result >", value, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultGreaterThanOrEqualTo(String value) {
            addCriterion("push_result >=", value, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultLessThan(String value) {
            addCriterion("push_result <", value, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultLessThanOrEqualTo(String value) {
            addCriterion("push_result <=", value, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultLike(String value) {
            addCriterion("push_result like", value, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultNotLike(String value) {
            addCriterion("push_result not like", value, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultIn(List<String> values) {
            addCriterion("push_result in", values, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultNotIn(List<String> values) {
            addCriterion("push_result not in", values, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultBetween(String value1, String value2) {
            addCriterion("push_result between", value1, value2, "pushResult");
            return (Criteria) this;
        }

        public Criteria andPushResultNotBetween(String value1, String value2) {
            addCriterion("push_result not between", value1, value2, "pushResult");
            return (Criteria) this;
        }

        public Criteria andErrorInfoIsNull() {
            addCriterion("error_info is null");
            return (Criteria) this;
        }

        public Criteria andErrorInfoIsNotNull() {
            addCriterion("error_info is not null");
            return (Criteria) this;
        }

        public Criteria andErrorInfoEqualTo(String value) {
            addCriterion("error_info =", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoNotEqualTo(String value) {
            addCriterion("error_info <>", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoGreaterThan(String value) {
            addCriterion("error_info >", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoGreaterThanOrEqualTo(String value) {
            addCriterion("error_info >=", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoLessThan(String value) {
            addCriterion("error_info <", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoLessThanOrEqualTo(String value) {
            addCriterion("error_info <=", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoLike(String value) {
            addCriterion("error_info like", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoNotLike(String value) {
            addCriterion("error_info not like", value, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoIn(List<String> values) {
            addCriterion("error_info in", values, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoNotIn(List<String> values) {
            addCriterion("error_info not in", values, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoBetween(String value1, String value2) {
            addCriterion("error_info between", value1, value2, "errorInfo");
            return (Criteria) this;
        }

        public Criteria andErrorInfoNotBetween(String value1, String value2) {
            addCriterion("error_info not between", value1, value2, "errorInfo");
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
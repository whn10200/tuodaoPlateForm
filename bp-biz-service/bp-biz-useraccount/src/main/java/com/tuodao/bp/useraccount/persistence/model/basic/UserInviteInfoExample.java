package com.tuodao.bp.useraccount.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class UserInviteInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UserInviteInfoExample() {
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

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNull() {
            addCriterion("user_phone is null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIsNotNull() {
            addCriterion("user_phone is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhoneEqualTo(String value) {
            addCriterion("user_phone =", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotEqualTo(String value) {
            addCriterion("user_phone <>", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThan(String value) {
            addCriterion("user_phone >", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("user_phone >=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThan(String value) {
            addCriterion("user_phone <", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("user_phone <=", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneLike(String value) {
            addCriterion("user_phone like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotLike(String value) {
            addCriterion("user_phone not like", value, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneIn(List<String> values) {
            addCriterion("user_phone in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotIn(List<String> values) {
            addCriterion("user_phone not in", values, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneBetween(String value1, String value2) {
            addCriterion("user_phone between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andUserPhoneNotBetween(String value1, String value2) {
            addCriterion("user_phone not between", value1, value2, "userPhone");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelIsNull() {
            addCriterion("financial_planner_level is null");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelIsNotNull() {
            addCriterion("financial_planner_level is not null");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelEqualTo(Integer value) {
            addCriterion("financial_planner_level =", value, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelNotEqualTo(Integer value) {
            addCriterion("financial_planner_level <>", value, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelGreaterThan(Integer value) {
            addCriterion("financial_planner_level >", value, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("financial_planner_level >=", value, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelLessThan(Integer value) {
            addCriterion("financial_planner_level <", value, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelLessThanOrEqualTo(Integer value) {
            addCriterion("financial_planner_level <=", value, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelIn(List<Integer> values) {
            addCriterion("financial_planner_level in", values, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelNotIn(List<Integer> values) {
            addCriterion("financial_planner_level not in", values, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelBetween(Integer value1, Integer value2) {
            addCriterion("financial_planner_level between", value1, value2, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andFinancialPlannerLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("financial_planner_level not between", value1, value2, "financialPlannerLevel");
            return (Criteria) this;
        }

        public Criteria andInvitePersonIsNull() {
            addCriterion("invite_person is null");
            return (Criteria) this;
        }

        public Criteria andInvitePersonIsNotNull() {
            addCriterion("invite_person is not null");
            return (Criteria) this;
        }

        public Criteria andInvitePersonEqualTo(String value) {
            addCriterion("invite_person =", value, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNotEqualTo(String value) {
            addCriterion("invite_person <>", value, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonGreaterThan(String value) {
            addCriterion("invite_person >", value, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonGreaterThanOrEqualTo(String value) {
            addCriterion("invite_person >=", value, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonLessThan(String value) {
            addCriterion("invite_person <", value, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonLessThanOrEqualTo(String value) {
            addCriterion("invite_person <=", value, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonLike(String value) {
            addCriterion("invite_person like", value, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNotLike(String value) {
            addCriterion("invite_person not like", value, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonIn(List<String> values) {
            addCriterion("invite_person in", values, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNotIn(List<String> values) {
            addCriterion("invite_person not in", values, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonBetween(String value1, String value2) {
            addCriterion("invite_person between", value1, value2, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNotBetween(String value1, String value2) {
            addCriterion("invite_person not between", value1, value2, "invitePerson");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeIsNull() {
            addCriterion("invite_register_type is null");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeIsNotNull() {
            addCriterion("invite_register_type is not null");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeEqualTo(String value) {
            addCriterion("invite_register_type =", value, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeNotEqualTo(String value) {
            addCriterion("invite_register_type <>", value, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeGreaterThan(String value) {
            addCriterion("invite_register_type >", value, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeGreaterThanOrEqualTo(String value) {
            addCriterion("invite_register_type >=", value, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeLessThan(String value) {
            addCriterion("invite_register_type <", value, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeLessThanOrEqualTo(String value) {
            addCriterion("invite_register_type <=", value, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeLike(String value) {
            addCriterion("invite_register_type like", value, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeNotLike(String value) {
            addCriterion("invite_register_type not like", value, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeIn(List<String> values) {
            addCriterion("invite_register_type in", values, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeNotIn(List<String> values) {
            addCriterion("invite_register_type not in", values, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeBetween(String value1, String value2) {
            addCriterion("invite_register_type between", value1, value2, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInviteRegisterTypeNotBetween(String value1, String value2) {
            addCriterion("invite_register_type not between", value1, value2, "inviteRegisterType");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumIsNull() {
            addCriterion("invite_person_num is null");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumIsNotNull() {
            addCriterion("invite_person_num is not null");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumEqualTo(Integer value) {
            addCriterion("invite_person_num =", value, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumNotEqualTo(Integer value) {
            addCriterion("invite_person_num <>", value, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumGreaterThan(Integer value) {
            addCriterion("invite_person_num >", value, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("invite_person_num >=", value, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumLessThan(Integer value) {
            addCriterion("invite_person_num <", value, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumLessThanOrEqualTo(Integer value) {
            addCriterion("invite_person_num <=", value, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumIn(List<Integer> values) {
            addCriterion("invite_person_num in", values, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumNotIn(List<Integer> values) {
            addCriterion("invite_person_num not in", values, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumBetween(Integer value1, Integer value2) {
            addCriterion("invite_person_num between", value1, value2, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvitePersonNumNotBetween(Integer value1, Integer value2) {
            addCriterion("invite_person_num not between", value1, value2, "invitePersonNum");
            return (Criteria) this;
        }

        public Criteria andInvestTimesIsNull() {
            addCriterion("invest_times is null");
            return (Criteria) this;
        }

        public Criteria andInvestTimesIsNotNull() {
            addCriterion("invest_times is not null");
            return (Criteria) this;
        }

        public Criteria andInvestTimesEqualTo(Integer value) {
            addCriterion("invest_times =", value, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesNotEqualTo(Integer value) {
            addCriterion("invest_times <>", value, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesGreaterThan(Integer value) {
            addCriterion("invest_times >", value, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesGreaterThanOrEqualTo(Integer value) {
            addCriterion("invest_times >=", value, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesLessThan(Integer value) {
            addCriterion("invest_times <", value, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesLessThanOrEqualTo(Integer value) {
            addCriterion("invest_times <=", value, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesIn(List<Integer> values) {
            addCriterion("invest_times in", values, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesNotIn(List<Integer> values) {
            addCriterion("invest_times not in", values, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesBetween(Integer value1, Integer value2) {
            addCriterion("invest_times between", value1, value2, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTimesNotBetween(Integer value1, Integer value2) {
            addCriterion("invest_times not between", value1, value2, "investTimes");
            return (Criteria) this;
        }

        public Criteria andInvestTotalIsNull() {
            addCriterion("invest_total is null");
            return (Criteria) this;
        }

        public Criteria andInvestTotalIsNotNull() {
            addCriterion("invest_total is not null");
            return (Criteria) this;
        }

        public Criteria andInvestTotalEqualTo(BigDecimal value) {
            addCriterion("invest_total =", value, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalNotEqualTo(BigDecimal value) {
            addCriterion("invest_total <>", value, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalGreaterThan(BigDecimal value) {
            addCriterion("invest_total >", value, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("invest_total >=", value, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalLessThan(BigDecimal value) {
            addCriterion("invest_total <", value, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("invest_total <=", value, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalIn(List<BigDecimal> values) {
            addCriterion("invest_total in", values, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalNotIn(List<BigDecimal> values) {
            addCriterion("invest_total not in", values, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invest_total between", value1, value2, "investTotal");
            return (Criteria) this;
        }

        public Criteria andInvestTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("invest_total not between", value1, value2, "investTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalIsNull() {
            addCriterion("backcash_total is null");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalIsNotNull() {
            addCriterion("backcash_total is not null");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalEqualTo(BigDecimal value) {
            addCriterion("backcash_total =", value, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalNotEqualTo(BigDecimal value) {
            addCriterion("backcash_total <>", value, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalGreaterThan(BigDecimal value) {
            addCriterion("backcash_total >", value, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("backcash_total >=", value, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalLessThan(BigDecimal value) {
            addCriterion("backcash_total <", value, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalLessThanOrEqualTo(BigDecimal value) {
            addCriterion("backcash_total <=", value, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalIn(List<BigDecimal> values) {
            addCriterion("backcash_total in", values, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalNotIn(List<BigDecimal> values) {
            addCriterion("backcash_total not in", values, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("backcash_total between", value1, value2, "backcashTotal");
            return (Criteria) this;
        }

        public Criteria andBackcashTotalNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("backcash_total not between", value1, value2, "backcashTotal");
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
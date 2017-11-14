package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpInviteAwardRecordExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OpInviteAwardRecordExample() {
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

        public Criteria andInvitePhoneNumIsNull() {
            addCriterion("invite_phone_num is null");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumIsNotNull() {
            addCriterion("invite_phone_num is not null");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumEqualTo(String value) {
            addCriterion("invite_phone_num =", value, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumNotEqualTo(String value) {
            addCriterion("invite_phone_num <>", value, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumGreaterThan(String value) {
            addCriterion("invite_phone_num >", value, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("invite_phone_num >=", value, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumLessThan(String value) {
            addCriterion("invite_phone_num <", value, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumLessThanOrEqualTo(String value) {
            addCriterion("invite_phone_num <=", value, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumLike(String value) {
            addCriterion("invite_phone_num like", value, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumNotLike(String value) {
            addCriterion("invite_phone_num not like", value, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumIn(List<String> values) {
            addCriterion("invite_phone_num in", values, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumNotIn(List<String> values) {
            addCriterion("invite_phone_num not in", values, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumBetween(String value1, String value2) {
            addCriterion("invite_phone_num between", value1, value2, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andInvitePhoneNumNotBetween(String value1, String value2) {
            addCriterion("invite_phone_num not between", value1, value2, "invitePhoneNum");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeIsNull() {
            addCriterion("direct_invitee is null");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeIsNotNull() {
            addCriterion("direct_invitee is not null");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeEqualTo(String value) {
            addCriterion("direct_invitee =", value, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeNotEqualTo(String value) {
            addCriterion("direct_invitee <>", value, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeGreaterThan(String value) {
            addCriterion("direct_invitee >", value, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeGreaterThanOrEqualTo(String value) {
            addCriterion("direct_invitee >=", value, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeLessThan(String value) {
            addCriterion("direct_invitee <", value, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeLessThanOrEqualTo(String value) {
            addCriterion("direct_invitee <=", value, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeLike(String value) {
            addCriterion("direct_invitee like", value, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeNotLike(String value) {
            addCriterion("direct_invitee not like", value, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeIn(List<String> values) {
            addCriterion("direct_invitee in", values, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeNotIn(List<String> values) {
            addCriterion("direct_invitee not in", values, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeBetween(String value1, String value2) {
            addCriterion("direct_invitee between", value1, value2, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andDirectInviteeNotBetween(String value1, String value2) {
            addCriterion("direct_invitee not between", value1, value2, "directInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeIsNull() {
            addCriterion("indirect_invitee is null");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeIsNotNull() {
            addCriterion("indirect_invitee is not null");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeEqualTo(String value) {
            addCriterion("indirect_invitee =", value, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeNotEqualTo(String value) {
            addCriterion("indirect_invitee <>", value, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeGreaterThan(String value) {
            addCriterion("indirect_invitee >", value, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeGreaterThanOrEqualTo(String value) {
            addCriterion("indirect_invitee >=", value, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeLessThan(String value) {
            addCriterion("indirect_invitee <", value, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeLessThanOrEqualTo(String value) {
            addCriterion("indirect_invitee <=", value, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeLike(String value) {
            addCriterion("indirect_invitee like", value, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeNotLike(String value) {
            addCriterion("indirect_invitee not like", value, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeIn(List<String> values) {
            addCriterion("indirect_invitee in", values, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeNotIn(List<String> values) {
            addCriterion("indirect_invitee not in", values, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeBetween(String value1, String value2) {
            addCriterion("indirect_invitee between", value1, value2, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andIndirectInviteeNotBetween(String value1, String value2) {
            addCriterion("indirect_invitee not between", value1, value2, "indirectInvitee");
            return (Criteria) this;
        }

        public Criteria andAwardTypeIsNull() {
            addCriterion("award_type is null");
            return (Criteria) this;
        }

        public Criteria andAwardTypeIsNotNull() {
            addCriterion("award_type is not null");
            return (Criteria) this;
        }

        public Criteria andAwardTypeEqualTo(Integer value) {
            addCriterion("award_type =", value, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeNotEqualTo(Integer value) {
            addCriterion("award_type <>", value, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeGreaterThan(Integer value) {
            addCriterion("award_type >", value, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("award_type >=", value, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeLessThan(Integer value) {
            addCriterion("award_type <", value, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeLessThanOrEqualTo(Integer value) {
            addCriterion("award_type <=", value, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeIn(List<Integer> values) {
            addCriterion("award_type in", values, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeNotIn(List<Integer> values) {
            addCriterion("award_type not in", values, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeBetween(Integer value1, Integer value2) {
            addCriterion("award_type between", value1, value2, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("award_type not between", value1, value2, "awardType");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyIsNull() {
            addCriterion("award_money is null");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyIsNotNull() {
            addCriterion("award_money is not null");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyEqualTo(BigDecimal value) {
            addCriterion("award_money =", value, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyNotEqualTo(BigDecimal value) {
            addCriterion("award_money <>", value, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyGreaterThan(BigDecimal value) {
            addCriterion("award_money >", value, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("award_money >=", value, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyLessThan(BigDecimal value) {
            addCriterion("award_money <", value, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("award_money <=", value, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyIn(List<BigDecimal> values) {
            addCriterion("award_money in", values, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyNotIn(List<BigDecimal> values) {
            addCriterion("award_money not in", values, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_money between", value1, value2, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andAwardMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_money not between", value1, value2, "awardMoney");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNull() {
            addCriterion("release_time is null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNotNull() {
            addCriterion("release_time is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeEqualTo(Date value) {
            addCriterion("release_time =", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotEqualTo(Date value) {
            addCriterion("release_time <>", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThan(Date value) {
            addCriterion("release_time >", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("release_time >=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThan(Date value) {
            addCriterion("release_time <", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("release_time <=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIn(List<Date> values) {
            addCriterion("release_time in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotIn(List<Date> values) {
            addCriterion("release_time not in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeBetween(Date value1, Date value2) {
            addCriterion("release_time between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("release_time not between", value1, value2, "releaseTime");
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
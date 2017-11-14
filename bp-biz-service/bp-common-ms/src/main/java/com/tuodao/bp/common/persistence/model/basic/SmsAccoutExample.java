package com.tuodao.bp.common.persistence.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsAccoutExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SmsAccoutExample() {
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

        public Criteria andSmsServicerIsNull() {
            addCriterion("sms_servicer is null");
            return (Criteria) this;
        }

        public Criteria andSmsServicerIsNotNull() {
            addCriterion("sms_servicer is not null");
            return (Criteria) this;
        }

        public Criteria andSmsServicerEqualTo(Integer value) {
            addCriterion("sms_servicer =", value, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerNotEqualTo(Integer value) {
            addCriterion("sms_servicer <>", value, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerGreaterThan(Integer value) {
            addCriterion("sms_servicer >", value, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_servicer >=", value, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerLessThan(Integer value) {
            addCriterion("sms_servicer <", value, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerLessThanOrEqualTo(Integer value) {
            addCriterion("sms_servicer <=", value, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerIn(List<Integer> values) {
            addCriterion("sms_servicer in", values, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerNotIn(List<Integer> values) {
            addCriterion("sms_servicer not in", values, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerBetween(Integer value1, Integer value2) {
            addCriterion("sms_servicer between", value1, value2, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsServicerNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_servicer not between", value1, value2, "smsServicer");
            return (Criteria) this;
        }

        public Criteria andSmsAccountIsNull() {
            addCriterion("sms_account is null");
            return (Criteria) this;
        }

        public Criteria andSmsAccountIsNotNull() {
            addCriterion("sms_account is not null");
            return (Criteria) this;
        }

        public Criteria andSmsAccountEqualTo(String value) {
            addCriterion("sms_account =", value, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountNotEqualTo(String value) {
            addCriterion("sms_account <>", value, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountGreaterThan(String value) {
            addCriterion("sms_account >", value, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountGreaterThanOrEqualTo(String value) {
            addCriterion("sms_account >=", value, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountLessThan(String value) {
            addCriterion("sms_account <", value, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountLessThanOrEqualTo(String value) {
            addCriterion("sms_account <=", value, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountLike(String value) {
            addCriterion("sms_account like", value, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountNotLike(String value) {
            addCriterion("sms_account not like", value, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountIn(List<String> values) {
            addCriterion("sms_account in", values, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountNotIn(List<String> values) {
            addCriterion("sms_account not in", values, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountBetween(String value1, String value2) {
            addCriterion("sms_account between", value1, value2, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsAccountNotBetween(String value1, String value2) {
            addCriterion("sms_account not between", value1, value2, "smsAccount");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordIsNull() {
            addCriterion("sms_password is null");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordIsNotNull() {
            addCriterion("sms_password is not null");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordEqualTo(String value) {
            addCriterion("sms_password =", value, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordNotEqualTo(String value) {
            addCriterion("sms_password <>", value, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordGreaterThan(String value) {
            addCriterion("sms_password >", value, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("sms_password >=", value, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordLessThan(String value) {
            addCriterion("sms_password <", value, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordLessThanOrEqualTo(String value) {
            addCriterion("sms_password <=", value, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordLike(String value) {
            addCriterion("sms_password like", value, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordNotLike(String value) {
            addCriterion("sms_password not like", value, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordIn(List<String> values) {
            addCriterion("sms_password in", values, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordNotIn(List<String> values) {
            addCriterion("sms_password not in", values, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordBetween(String value1, String value2) {
            addCriterion("sms_password between", value1, value2, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsPasswordNotBetween(String value1, String value2) {
            addCriterion("sms_password not between", value1, value2, "smsPassword");
            return (Criteria) this;
        }

        public Criteria andSmsUrlIsNull() {
            addCriterion("sms_url is null");
            return (Criteria) this;
        }

        public Criteria andSmsUrlIsNotNull() {
            addCriterion("sms_url is not null");
            return (Criteria) this;
        }

        public Criteria andSmsUrlEqualTo(String value) {
            addCriterion("sms_url =", value, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlNotEqualTo(String value) {
            addCriterion("sms_url <>", value, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlGreaterThan(String value) {
            addCriterion("sms_url >", value, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlGreaterThanOrEqualTo(String value) {
            addCriterion("sms_url >=", value, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlLessThan(String value) {
            addCriterion("sms_url <", value, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlLessThanOrEqualTo(String value) {
            addCriterion("sms_url <=", value, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlLike(String value) {
            addCriterion("sms_url like", value, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlNotLike(String value) {
            addCriterion("sms_url not like", value, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlIn(List<String> values) {
            addCriterion("sms_url in", values, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlNotIn(List<String> values) {
            addCriterion("sms_url not in", values, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlBetween(String value1, String value2) {
            addCriterion("sms_url between", value1, value2, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsUrlNotBetween(String value1, String value2) {
            addCriterion("sms_url not between", value1, value2, "smsUrl");
            return (Criteria) this;
        }

        public Criteria andSmsStatusIsNull() {
            addCriterion("sms_status is null");
            return (Criteria) this;
        }

        public Criteria andSmsStatusIsNotNull() {
            addCriterion("sms_status is not null");
            return (Criteria) this;
        }

        public Criteria andSmsStatusEqualTo(Integer value) {
            addCriterion("sms_status =", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusNotEqualTo(Integer value) {
            addCriterion("sms_status <>", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusGreaterThan(Integer value) {
            addCriterion("sms_status >", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sms_status >=", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusLessThan(Integer value) {
            addCriterion("sms_status <", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusLessThanOrEqualTo(Integer value) {
            addCriterion("sms_status <=", value, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusIn(List<Integer> values) {
            addCriterion("sms_status in", values, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusNotIn(List<Integer> values) {
            addCriterion("sms_status not in", values, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusBetween(Integer value1, Integer value2) {
            addCriterion("sms_status between", value1, value2, "smsStatus");
            return (Criteria) this;
        }

        public Criteria andSmsStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("sms_status not between", value1, value2, "smsStatus");
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
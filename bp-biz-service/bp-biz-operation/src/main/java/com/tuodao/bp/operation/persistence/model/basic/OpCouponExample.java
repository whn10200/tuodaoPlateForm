package com.tuodao.bp.operation.persistence.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OpCouponExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public OpCouponExample() {
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

        public Criteria andDiscountTitleIsNull() {
            addCriterion("discount_title is null");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleIsNotNull() {
            addCriterion("discount_title is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleEqualTo(String value) {
            addCriterion("discount_title =", value, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleNotEqualTo(String value) {
            addCriterion("discount_title <>", value, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleGreaterThan(String value) {
            addCriterion("discount_title >", value, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleGreaterThanOrEqualTo(String value) {
            addCriterion("discount_title >=", value, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleLessThan(String value) {
            addCriterion("discount_title <", value, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleLessThanOrEqualTo(String value) {
            addCriterion("discount_title <=", value, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleLike(String value) {
            addCriterion("discount_title like", value, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleNotLike(String value) {
            addCriterion("discount_title not like", value, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleIn(List<String> values) {
            addCriterion("discount_title in", values, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleNotIn(List<String> values) {
            addCriterion("discount_title not in", values, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleBetween(String value1, String value2) {
            addCriterion("discount_title between", value1, value2, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTitleNotBetween(String value1, String value2) {
            addCriterion("discount_title not between", value1, value2, "discountTitle");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNull() {
            addCriterion("discount_type is null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIsNotNull() {
            addCriterion("discount_type is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeEqualTo(Integer value) {
            addCriterion("discount_type =", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotEqualTo(Integer value) {
            addCriterion("discount_type <>", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThan(Integer value) {
            addCriterion("discount_type >", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("discount_type >=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThan(Integer value) {
            addCriterion("discount_type <", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeLessThanOrEqualTo(Integer value) {
            addCriterion("discount_type <=", value, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeIn(List<Integer> values) {
            addCriterion("discount_type in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotIn(List<Integer> values) {
            addCriterion("discount_type not in", values, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeBetween(Integer value1, Integer value2) {
            addCriterion("discount_type between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("discount_type not between", value1, value2, "discountType");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableIsNull() {
            addCriterion("discount_available is null");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableIsNotNull() {
            addCriterion("discount_available is not null");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableEqualTo(String value) {
            addCriterion("discount_available =", value, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableNotEqualTo(String value) {
            addCriterion("discount_available <>", value, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableGreaterThan(String value) {
            addCriterion("discount_available >", value, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableGreaterThanOrEqualTo(String value) {
            addCriterion("discount_available >=", value, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableLessThan(String value) {
            addCriterion("discount_available <", value, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableLessThanOrEqualTo(String value) {
            addCriterion("discount_available <=", value, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableLike(String value) {
            addCriterion("discount_available like", value, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableNotLike(String value) {
            addCriterion("discount_available not like", value, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableIn(List<String> values) {
            addCriterion("discount_available in", values, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableNotIn(List<String> values) {
            addCriterion("discount_available not in", values, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableBetween(String value1, String value2) {
            addCriterion("discount_available between", value1, value2, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andDiscountAvailableNotBetween(String value1, String value2) {
            addCriterion("discount_available not between", value1, value2, "discountAvailable");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitIsNull() {
            addCriterion("money_limit is null");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitIsNotNull() {
            addCriterion("money_limit is not null");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitEqualTo(Integer value) {
            addCriterion("money_limit =", value, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitNotEqualTo(Integer value) {
            addCriterion("money_limit <>", value, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitGreaterThan(Integer value) {
            addCriterion("money_limit >", value, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_limit >=", value, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitLessThan(Integer value) {
            addCriterion("money_limit <", value, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitLessThanOrEqualTo(Integer value) {
            addCriterion("money_limit <=", value, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitIn(List<Integer> values) {
            addCriterion("money_limit in", values, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitNotIn(List<Integer> values) {
            addCriterion("money_limit not in", values, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitBetween(Integer value1, Integer value2) {
            addCriterion("money_limit between", value1, value2, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andMoneyLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("money_limit not between", value1, value2, "moneyLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitIsNull() {
            addCriterion("date_limit is null");
            return (Criteria) this;
        }

        public Criteria andDateLimitIsNotNull() {
            addCriterion("date_limit is not null");
            return (Criteria) this;
        }

        public Criteria andDateLimitEqualTo(Integer value) {
            addCriterion("date_limit =", value, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitNotEqualTo(Integer value) {
            addCriterion("date_limit <>", value, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitGreaterThan(Integer value) {
            addCriterion("date_limit >", value, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("date_limit >=", value, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitLessThan(Integer value) {
            addCriterion("date_limit <", value, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitLessThanOrEqualTo(Integer value) {
            addCriterion("date_limit <=", value, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitIn(List<Integer> values) {
            addCriterion("date_limit in", values, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitNotIn(List<Integer> values) {
            addCriterion("date_limit not in", values, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitBetween(Integer value1, Integer value2) {
            addCriterion("date_limit between", value1, value2, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andDateLimitNotBetween(Integer value1, Integer value2) {
            addCriterion("date_limit not between", value1, value2, "dateLimit");
            return (Criteria) this;
        }

        public Criteria andEffectDayIsNull() {
            addCriterion("effect_day is null");
            return (Criteria) this;
        }

        public Criteria andEffectDayIsNotNull() {
            addCriterion("effect_day is not null");
            return (Criteria) this;
        }

        public Criteria andEffectDayEqualTo(Integer value) {
            addCriterion("effect_day =", value, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayNotEqualTo(Integer value) {
            addCriterion("effect_day <>", value, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayGreaterThan(Integer value) {
            addCriterion("effect_day >", value, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("effect_day >=", value, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayLessThan(Integer value) {
            addCriterion("effect_day <", value, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayLessThanOrEqualTo(Integer value) {
            addCriterion("effect_day <=", value, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayIn(List<Integer> values) {
            addCriterion("effect_day in", values, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayNotIn(List<Integer> values) {
            addCriterion("effect_day not in", values, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayBetween(Integer value1, Integer value2) {
            addCriterion("effect_day between", value1, value2, "effectDay");
            return (Criteria) this;
        }

        public Criteria andEffectDayNotBetween(Integer value1, Integer value2) {
            addCriterion("effect_day not between", value1, value2, "effectDay");
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
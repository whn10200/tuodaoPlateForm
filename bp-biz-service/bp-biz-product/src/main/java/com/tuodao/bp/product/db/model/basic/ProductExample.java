package com.tuodao.bp.product.db.model.basic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public ProductExample() {
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

        public Criteria andProductCodeIsNull() {
            addCriterion("product_code is null");
            return (Criteria) this;
        }

        public Criteria andProductCodeIsNotNull() {
            addCriterion("product_code is not null");
            return (Criteria) this;
        }

        public Criteria andProductCodeEqualTo(String value) {
            addCriterion("product_code =", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotEqualTo(String value) {
            addCriterion("product_code <>", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThan(String value) {
            addCriterion("product_code >", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeGreaterThanOrEqualTo(String value) {
            addCriterion("product_code >=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThan(String value) {
            addCriterion("product_code <", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLessThanOrEqualTo(String value) {
            addCriterion("product_code <=", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeLike(String value) {
            addCriterion("product_code like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotLike(String value) {
            addCriterion("product_code not like", value, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeIn(List<String> values) {
            addCriterion("product_code in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotIn(List<String> values) {
            addCriterion("product_code not in", values, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeBetween(String value1, String value2) {
            addCriterion("product_code between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andProductCodeNotBetween(String value1, String value2) {
            addCriterion("product_code not between", value1, value2, "productCode");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNull() {
            addCriterion("business_id is null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIsNotNull() {
            addCriterion("business_id is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessIdEqualTo(String value) {
            addCriterion("business_id =", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotEqualTo(String value) {
            addCriterion("business_id <>", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThan(String value) {
            addCriterion("business_id >", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdGreaterThanOrEqualTo(String value) {
            addCriterion("business_id >=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThan(String value) {
            addCriterion("business_id <", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLessThanOrEqualTo(String value) {
            addCriterion("business_id <=", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdLike(String value) {
            addCriterion("business_id like", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotLike(String value) {
            addCriterion("business_id not like", value, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdIn(List<String> values) {
            addCriterion("business_id in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotIn(List<String> values) {
            addCriterion("business_id not in", values, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdBetween(String value1, String value2) {
            addCriterion("business_id between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andBusinessIdNotBetween(String value1, String value2) {
            addCriterion("business_id not between", value1, value2, "businessId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdIsNull() {
            addCriterion("orginal_id is null");
            return (Criteria) this;
        }

        public Criteria andOrginalIdIsNotNull() {
            addCriterion("orginal_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrginalIdEqualTo(Integer value) {
            addCriterion("orginal_id =", value, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdNotEqualTo(Integer value) {
            addCriterion("orginal_id <>", value, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdGreaterThan(Integer value) {
            addCriterion("orginal_id >", value, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("orginal_id >=", value, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdLessThan(Integer value) {
            addCriterion("orginal_id <", value, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdLessThanOrEqualTo(Integer value) {
            addCriterion("orginal_id <=", value, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdIn(List<Integer> values) {
            addCriterion("orginal_id in", values, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdNotIn(List<Integer> values) {
            addCriterion("orginal_id not in", values, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdBetween(Integer value1, Integer value2) {
            addCriterion("orginal_id between", value1, value2, "orginalId");
            return (Criteria) this;
        }

        public Criteria andOrginalIdNotBetween(Integer value1, Integer value2) {
            addCriterion("orginal_id not between", value1, value2, "orginalId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(Integer value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(Integer value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(Integer value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(Integer value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(Integer value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<Integer> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(Integer value1, Integer value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(Integer value1, Integer value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdIsNull() {
            addCriterion("borrow_user_id is null");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdIsNotNull() {
            addCriterion("borrow_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdEqualTo(String value) {
            addCriterion("borrow_user_id =", value, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdNotEqualTo(String value) {
            addCriterion("borrow_user_id <>", value, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdGreaterThan(String value) {
            addCriterion("borrow_user_id >", value, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_user_id >=", value, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdLessThan(String value) {
            addCriterion("borrow_user_id <", value, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdLessThanOrEqualTo(String value) {
            addCriterion("borrow_user_id <=", value, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdLike(String value) {
            addCriterion("borrow_user_id like", value, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdNotLike(String value) {
            addCriterion("borrow_user_id not like", value, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdIn(List<String> values) {
            addCriterion("borrow_user_id in", values, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdNotIn(List<String> values) {
            addCriterion("borrow_user_id not in", values, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdBetween(String value1, String value2) {
            addCriterion("borrow_user_id between", value1, value2, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserIdNotBetween(String value1, String value2) {
            addCriterion("borrow_user_id not between", value1, value2, "borrowUserId");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameIsNull() {
            addCriterion("borrow_user_name is null");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameIsNotNull() {
            addCriterion("borrow_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameEqualTo(String value) {
            addCriterion("borrow_user_name =", value, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameNotEqualTo(String value) {
            addCriterion("borrow_user_name <>", value, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameGreaterThan(String value) {
            addCriterion("borrow_user_name >", value, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_user_name >=", value, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameLessThan(String value) {
            addCriterion("borrow_user_name <", value, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameLessThanOrEqualTo(String value) {
            addCriterion("borrow_user_name <=", value, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameLike(String value) {
            addCriterion("borrow_user_name like", value, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameNotLike(String value) {
            addCriterion("borrow_user_name not like", value, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameIn(List<String> values) {
            addCriterion("borrow_user_name in", values, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameNotIn(List<String> values) {
            addCriterion("borrow_user_name not in", values, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameBetween(String value1, String value2) {
            addCriterion("borrow_user_name between", value1, value2, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowUserNameNotBetween(String value1, String value2) {
            addCriterion("borrow_user_name not between", value1, value2, "borrowUserName");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountIsNull() {
            addCriterion("borrow_amount is null");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountIsNotNull() {
            addCriterion("borrow_amount is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountEqualTo(BigDecimal value) {
            addCriterion("borrow_amount =", value, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountNotEqualTo(BigDecimal value) {
            addCriterion("borrow_amount <>", value, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountGreaterThan(BigDecimal value) {
            addCriterion("borrow_amount >", value, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_amount >=", value, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountLessThan(BigDecimal value) {
            addCriterion("borrow_amount <", value, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_amount <=", value, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountIn(List<BigDecimal> values) {
            addCriterion("borrow_amount in", values, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountNotIn(List<BigDecimal> values) {
            addCriterion("borrow_amount not in", values, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_amount between", value1, value2, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_amount not between", value1, value2, "borrowAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesIsNull() {
            addCriterion("borrow_account_yes is null");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesIsNotNull() {
            addCriterion("borrow_account_yes is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesEqualTo(BigDecimal value) {
            addCriterion("borrow_account_yes =", value, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesNotEqualTo(BigDecimal value) {
            addCriterion("borrow_account_yes <>", value, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesGreaterThan(BigDecimal value) {
            addCriterion("borrow_account_yes >", value, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_account_yes >=", value, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesLessThan(BigDecimal value) {
            addCriterion("borrow_account_yes <", value, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesLessThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_account_yes <=", value, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesIn(List<BigDecimal> values) {
            addCriterion("borrow_account_yes in", values, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesNotIn(List<BigDecimal> values) {
            addCriterion("borrow_account_yes not in", values, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_account_yes between", value1, value2, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowAccountYesNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_account_yes not between", value1, value2, "borrowAccountYes");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeIsNull() {
            addCriterion("borrow_fee is null");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeIsNotNull() {
            addCriterion("borrow_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeEqualTo(BigDecimal value) {
            addCriterion("borrow_fee =", value, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeNotEqualTo(BigDecimal value) {
            addCriterion("borrow_fee <>", value, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeGreaterThan(BigDecimal value) {
            addCriterion("borrow_fee >", value, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_fee >=", value, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeLessThan(BigDecimal value) {
            addCriterion("borrow_fee <", value, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_fee <=", value, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeIn(List<BigDecimal> values) {
            addCriterion("borrow_fee in", values, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeNotIn(List<BigDecimal> values) {
            addCriterion("borrow_fee not in", values, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_fee between", value1, value2, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowFeeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_fee not between", value1, value2, "borrowFee");
            return (Criteria) this;
        }

        public Criteria andBorrowAprIsNull() {
            addCriterion("borrow_apr is null");
            return (Criteria) this;
        }

        public Criteria andBorrowAprIsNotNull() {
            addCriterion("borrow_apr is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowAprEqualTo(BigDecimal value) {
            addCriterion("borrow_apr =", value, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprNotEqualTo(BigDecimal value) {
            addCriterion("borrow_apr <>", value, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprGreaterThan(BigDecimal value) {
            addCriterion("borrow_apr >", value, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_apr >=", value, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprLessThan(BigDecimal value) {
            addCriterion("borrow_apr <", value, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprLessThanOrEqualTo(BigDecimal value) {
            addCriterion("borrow_apr <=", value, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprIn(List<BigDecimal> values) {
            addCriterion("borrow_apr in", values, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprNotIn(List<BigDecimal> values) {
            addCriterion("borrow_apr not in", values, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_apr between", value1, value2, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowAprNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("borrow_apr not between", value1, value2, "borrowApr");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeIsNull() {
            addCriterion("borrow_type is null");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeIsNotNull() {
            addCriterion("borrow_type is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeEqualTo(Integer value) {
            addCriterion("borrow_type =", value, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeNotEqualTo(Integer value) {
            addCriterion("borrow_type <>", value, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeGreaterThan(Integer value) {
            addCriterion("borrow_type >", value, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("borrow_type >=", value, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeLessThan(Integer value) {
            addCriterion("borrow_type <", value, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeLessThanOrEqualTo(Integer value) {
            addCriterion("borrow_type <=", value, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeIn(List<Integer> values) {
            addCriterion("borrow_type in", values, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeNotIn(List<Integer> values) {
            addCriterion("borrow_type not in", values, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeBetween(Integer value1, Integer value2) {
            addCriterion("borrow_type between", value1, value2, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("borrow_type not between", value1, value2, "borrowType");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeIsNull() {
            addCriterion("borrow_full_time is null");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeIsNotNull() {
            addCriterion("borrow_full_time is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeEqualTo(Date value) {
            addCriterion("borrow_full_time =", value, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeNotEqualTo(Date value) {
            addCriterion("borrow_full_time <>", value, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeGreaterThan(Date value) {
            addCriterion("borrow_full_time >", value, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("borrow_full_time >=", value, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeLessThan(Date value) {
            addCriterion("borrow_full_time <", value, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeLessThanOrEqualTo(Date value) {
            addCriterion("borrow_full_time <=", value, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeIn(List<Date> values) {
            addCriterion("borrow_full_time in", values, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeNotIn(List<Date> values) {
            addCriterion("borrow_full_time not in", values, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeBetween(Date value1, Date value2) {
            addCriterion("borrow_full_time between", value1, value2, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullTimeNotBetween(Date value1, Date value2) {
            addCriterion("borrow_full_time not between", value1, value2, "borrowFullTime");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusIsNull() {
            addCriterion("borrow_full_status is null");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusIsNotNull() {
            addCriterion("borrow_full_status is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusEqualTo(Integer value) {
            addCriterion("borrow_full_status =", value, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusNotEqualTo(Integer value) {
            addCriterion("borrow_full_status <>", value, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusGreaterThan(Integer value) {
            addCriterion("borrow_full_status >", value, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("borrow_full_status >=", value, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusLessThan(Integer value) {
            addCriterion("borrow_full_status <", value, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusLessThanOrEqualTo(Integer value) {
            addCriterion("borrow_full_status <=", value, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusIn(List<Integer> values) {
            addCriterion("borrow_full_status in", values, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusNotIn(List<Integer> values) {
            addCriterion("borrow_full_status not in", values, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusBetween(Integer value1, Integer value2) {
            addCriterion("borrow_full_status between", value1, value2, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andBorrowFullStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("borrow_full_status not between", value1, value2, "borrowFullStatus");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountIsNull() {
            addCriterion("surplus_invest_amount is null");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountIsNotNull() {
            addCriterion("surplus_invest_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountEqualTo(BigDecimal value) {
            addCriterion("surplus_invest_amount =", value, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountNotEqualTo(BigDecimal value) {
            addCriterion("surplus_invest_amount <>", value, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountGreaterThan(BigDecimal value) {
            addCriterion("surplus_invest_amount >", value, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("surplus_invest_amount >=", value, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountLessThan(BigDecimal value) {
            addCriterion("surplus_invest_amount <", value, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("surplus_invest_amount <=", value, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountIn(List<BigDecimal> values) {
            addCriterion("surplus_invest_amount in", values, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountNotIn(List<BigDecimal> values) {
            addCriterion("surplus_invest_amount not in", values, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("surplus_invest_amount between", value1, value2, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andSurplusInvestAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("surplus_invest_amount not between", value1, value2, "surplusInvestAmount");
            return (Criteria) this;
        }

        public Criteria andIsJionIsNull() {
            addCriterion("is_jion is null");
            return (Criteria) this;
        }

        public Criteria andIsJionIsNotNull() {
            addCriterion("is_jion is not null");
            return (Criteria) this;
        }

        public Criteria andIsJionEqualTo(Integer value) {
            addCriterion("is_jion =", value, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionNotEqualTo(Integer value) {
            addCriterion("is_jion <>", value, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionGreaterThan(Integer value) {
            addCriterion("is_jion >", value, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_jion >=", value, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionLessThan(Integer value) {
            addCriterion("is_jion <", value, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionLessThanOrEqualTo(Integer value) {
            addCriterion("is_jion <=", value, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionIn(List<Integer> values) {
            addCriterion("is_jion in", values, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionNotIn(List<Integer> values) {
            addCriterion("is_jion not in", values, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionBetween(Integer value1, Integer value2) {
            addCriterion("is_jion between", value1, value2, "isJion");
            return (Criteria) this;
        }

        public Criteria andIsJionNotBetween(Integer value1, Integer value2) {
            addCriterion("is_jion not between", value1, value2, "isJion");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeIsNull() {
            addCriterion("repay_last_time is null");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeIsNotNull() {
            addCriterion("repay_last_time is not null");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeEqualTo(Date value) {
            addCriterion("repay_last_time =", value, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeNotEqualTo(Date value) {
            addCriterion("repay_last_time <>", value, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeGreaterThan(Date value) {
            addCriterion("repay_last_time >", value, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("repay_last_time >=", value, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeLessThan(Date value) {
            addCriterion("repay_last_time <", value, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeLessThanOrEqualTo(Date value) {
            addCriterion("repay_last_time <=", value, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeIn(List<Date> values) {
            addCriterion("repay_last_time in", values, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeNotIn(List<Date> values) {
            addCriterion("repay_last_time not in", values, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeBetween(Date value1, Date value2) {
            addCriterion("repay_last_time between", value1, value2, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andRepayLastTimeNotBetween(Date value1, Date value2) {
            addCriterion("repay_last_time not between", value1, value2, "repayLastTime");
            return (Criteria) this;
        }

        public Criteria andMateAmountIsNull() {
            addCriterion("mate_amount is null");
            return (Criteria) this;
        }

        public Criteria andMateAmountIsNotNull() {
            addCriterion("mate_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMateAmountEqualTo(BigDecimal value) {
            addCriterion("mate_amount =", value, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountNotEqualTo(BigDecimal value) {
            addCriterion("mate_amount <>", value, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountGreaterThan(BigDecimal value) {
            addCriterion("mate_amount >", value, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("mate_amount >=", value, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountLessThan(BigDecimal value) {
            addCriterion("mate_amount <", value, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("mate_amount <=", value, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountIn(List<BigDecimal> values) {
            addCriterion("mate_amount in", values, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountNotIn(List<BigDecimal> values) {
            addCriterion("mate_amount not in", values, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mate_amount between", value1, value2, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andMateAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("mate_amount not between", value1, value2, "mateAmount");
            return (Criteria) this;
        }

        public Criteria andBorrowUseIsNull() {
            addCriterion("borrow_use is null");
            return (Criteria) this;
        }

        public Criteria andBorrowUseIsNotNull() {
            addCriterion("borrow_use is not null");
            return (Criteria) this;
        }

        public Criteria andBorrowUseEqualTo(String value) {
            addCriterion("borrow_use =", value, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseNotEqualTo(String value) {
            addCriterion("borrow_use <>", value, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseGreaterThan(String value) {
            addCriterion("borrow_use >", value, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseGreaterThanOrEqualTo(String value) {
            addCriterion("borrow_use >=", value, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseLessThan(String value) {
            addCriterion("borrow_use <", value, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseLessThanOrEqualTo(String value) {
            addCriterion("borrow_use <=", value, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseLike(String value) {
            addCriterion("borrow_use like", value, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseNotLike(String value) {
            addCriterion("borrow_use not like", value, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseIn(List<String> values) {
            addCriterion("borrow_use in", values, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseNotIn(List<String> values) {
            addCriterion("borrow_use not in", values, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseBetween(String value1, String value2) {
            addCriterion("borrow_use between", value1, value2, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andBorrowUseNotBetween(String value1, String value2) {
            addCriterion("borrow_use not between", value1, value2, "borrowUse");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishIsNull() {
            addCriterion("is_auto_publish is null");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishIsNotNull() {
            addCriterion("is_auto_publish is not null");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishEqualTo(Integer value) {
            addCriterion("is_auto_publish =", value, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishNotEqualTo(Integer value) {
            addCriterion("is_auto_publish <>", value, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishGreaterThan(Integer value) {
            addCriterion("is_auto_publish >", value, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_auto_publish >=", value, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishLessThan(Integer value) {
            addCriterion("is_auto_publish <", value, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishLessThanOrEqualTo(Integer value) {
            addCriterion("is_auto_publish <=", value, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishIn(List<Integer> values) {
            addCriterion("is_auto_publish in", values, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishNotIn(List<Integer> values) {
            addCriterion("is_auto_publish not in", values, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishBetween(Integer value1, Integer value2) {
            addCriterion("is_auto_publish between", value1, value2, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsAutoPublishNotBetween(Integer value1, Integer value2) {
            addCriterion("is_auto_publish not between", value1, value2, "isAutoPublish");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueIsNull() {
            addCriterion("is_yet_issue is null");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueIsNotNull() {
            addCriterion("is_yet_issue is not null");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueEqualTo(Integer value) {
            addCriterion("is_yet_issue =", value, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueNotEqualTo(Integer value) {
            addCriterion("is_yet_issue <>", value, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueGreaterThan(Integer value) {
            addCriterion("is_yet_issue >", value, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_yet_issue >=", value, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueLessThan(Integer value) {
            addCriterion("is_yet_issue <", value, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueLessThanOrEqualTo(Integer value) {
            addCriterion("is_yet_issue <=", value, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueIn(List<Integer> values) {
            addCriterion("is_yet_issue in", values, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueNotIn(List<Integer> values) {
            addCriterion("is_yet_issue not in", values, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueBetween(Integer value1, Integer value2) {
            addCriterion("is_yet_issue between", value1, value2, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andIsYetIssueNotBetween(Integer value1, Integer value2) {
            addCriterion("is_yet_issue not between", value1, value2, "isYetIssue");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeIsNull() {
            addCriterion("auto_publish_time is null");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeIsNotNull() {
            addCriterion("auto_publish_time is not null");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeEqualTo(Date value) {
            addCriterion("auto_publish_time =", value, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeNotEqualTo(Date value) {
            addCriterion("auto_publish_time <>", value, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeGreaterThan(Date value) {
            addCriterion("auto_publish_time >", value, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("auto_publish_time >=", value, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeLessThan(Date value) {
            addCriterion("auto_publish_time <", value, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("auto_publish_time <=", value, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeIn(List<Date> values) {
            addCriterion("auto_publish_time in", values, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeNotIn(List<Date> values) {
            addCriterion("auto_publish_time not in", values, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeBetween(Date value1, Date value2) {
            addCriterion("auto_publish_time between", value1, value2, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andAutoPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("auto_publish_time not between", value1, value2, "autoPublishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNull() {
            addCriterion("publish_time is null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIsNotNull() {
            addCriterion("publish_time is not null");
            return (Criteria) this;
        }

        public Criteria andPublishTimeEqualTo(Date value) {
            addCriterion("publish_time =", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotEqualTo(Date value) {
            addCriterion("publish_time <>", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThan(Date value) {
            addCriterion("publish_time >", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("publish_time >=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThan(Date value) {
            addCriterion("publish_time <", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeLessThanOrEqualTo(Date value) {
            addCriterion("publish_time <=", value, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeIn(List<Date> values) {
            addCriterion("publish_time in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotIn(List<Date> values) {
            addCriterion("publish_time not in", values, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeBetween(Date value1, Date value2) {
            addCriterion("publish_time between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andPublishTimeNotBetween(Date value1, Date value2) {
            addCriterion("publish_time not between", value1, value2, "publishTime");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestIsNull() {
            addCriterion("is_auto_invest is null");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestIsNotNull() {
            addCriterion("is_auto_invest is not null");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestEqualTo(Integer value) {
            addCriterion("is_auto_invest =", value, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestNotEqualTo(Integer value) {
            addCriterion("is_auto_invest <>", value, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestGreaterThan(Integer value) {
            addCriterion("is_auto_invest >", value, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_auto_invest >=", value, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestLessThan(Integer value) {
            addCriterion("is_auto_invest <", value, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestLessThanOrEqualTo(Integer value) {
            addCriterion("is_auto_invest <=", value, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestIn(List<Integer> values) {
            addCriterion("is_auto_invest in", values, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestNotIn(List<Integer> values) {
            addCriterion("is_auto_invest not in", values, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestBetween(Integer value1, Integer value2) {
            addCriterion("is_auto_invest between", value1, value2, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andIsAutoInvestNotBetween(Integer value1, Integer value2) {
            addCriterion("is_auto_invest not between", value1, value2, "isAutoInvest");
            return (Criteria) this;
        }

        public Criteria andProductTitleIsNull() {
            addCriterion("product_title is null");
            return (Criteria) this;
        }

        public Criteria andProductTitleIsNotNull() {
            addCriterion("product_title is not null");
            return (Criteria) this;
        }

        public Criteria andProductTitleEqualTo(String value) {
            addCriterion("product_title =", value, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleNotEqualTo(String value) {
            addCriterion("product_title <>", value, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleGreaterThan(String value) {
            addCriterion("product_title >", value, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleGreaterThanOrEqualTo(String value) {
            addCriterion("product_title >=", value, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleLessThan(String value) {
            addCriterion("product_title <", value, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleLessThanOrEqualTo(String value) {
            addCriterion("product_title <=", value, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleLike(String value) {
            addCriterion("product_title like", value, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleNotLike(String value) {
            addCriterion("product_title not like", value, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleIn(List<String> values) {
            addCriterion("product_title in", values, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleNotIn(List<String> values) {
            addCriterion("product_title not in", values, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleBetween(String value1, String value2) {
            addCriterion("product_title between", value1, value2, "productTitle");
            return (Criteria) this;
        }

        public Criteria andProductTitleNotBetween(String value1, String value2) {
            addCriterion("product_title not between", value1, value2, "productTitle");
            return (Criteria) this;
        }

        public Criteria andRefundWayIsNull() {
            addCriterion("refund_way is null");
            return (Criteria) this;
        }

        public Criteria andRefundWayIsNotNull() {
            addCriterion("refund_way is not null");
            return (Criteria) this;
        }

        public Criteria andRefundWayEqualTo(Integer value) {
            addCriterion("refund_way =", value, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayNotEqualTo(Integer value) {
            addCriterion("refund_way <>", value, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayGreaterThan(Integer value) {
            addCriterion("refund_way >", value, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayGreaterThanOrEqualTo(Integer value) {
            addCriterion("refund_way >=", value, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayLessThan(Integer value) {
            addCriterion("refund_way <", value, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayLessThanOrEqualTo(Integer value) {
            addCriterion("refund_way <=", value, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayIn(List<Integer> values) {
            addCriterion("refund_way in", values, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayNotIn(List<Integer> values) {
            addCriterion("refund_way not in", values, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayBetween(Integer value1, Integer value2) {
            addCriterion("refund_way between", value1, value2, "refundWay");
            return (Criteria) this;
        }

        public Criteria andRefundWayNotBetween(Integer value1, Integer value2) {
            addCriterion("refund_way not between", value1, value2, "refundWay");
            return (Criteria) this;
        }

        public Criteria andProductPeriodIsNull() {
            addCriterion("product_period is null");
            return (Criteria) this;
        }

        public Criteria andProductPeriodIsNotNull() {
            addCriterion("product_period is not null");
            return (Criteria) this;
        }

        public Criteria andProductPeriodEqualTo(Integer value) {
            addCriterion("product_period =", value, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodNotEqualTo(Integer value) {
            addCriterion("product_period <>", value, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodGreaterThan(Integer value) {
            addCriterion("product_period >", value, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_period >=", value, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodLessThan(Integer value) {
            addCriterion("product_period <", value, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodLessThanOrEqualTo(Integer value) {
            addCriterion("product_period <=", value, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodIn(List<Integer> values) {
            addCriterion("product_period in", values, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodNotIn(List<Integer> values) {
            addCriterion("product_period not in", values, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodBetween(Integer value1, Integer value2) {
            addCriterion("product_period between", value1, value2, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andProductPeriodNotBetween(Integer value1, Integer value2) {
            addCriterion("product_period not between", value1, value2, "productPeriod");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitIsNull() {
            addCriterion("period_unit is null");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitIsNotNull() {
            addCriterion("period_unit is not null");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitEqualTo(Integer value) {
            addCriterion("period_unit =", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitNotEqualTo(Integer value) {
            addCriterion("period_unit <>", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitGreaterThan(Integer value) {
            addCriterion("period_unit >", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitGreaterThanOrEqualTo(Integer value) {
            addCriterion("period_unit >=", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitLessThan(Integer value) {
            addCriterion("period_unit <", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitLessThanOrEqualTo(Integer value) {
            addCriterion("period_unit <=", value, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitIn(List<Integer> values) {
            addCriterion("period_unit in", values, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitNotIn(List<Integer> values) {
            addCriterion("period_unit not in", values, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitBetween(Integer value1, Integer value2) {
            addCriterion("period_unit between", value1, value2, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andPeriodUnitNotBetween(Integer value1, Integer value2) {
            addCriterion("period_unit not between", value1, value2, "periodUnit");
            return (Criteria) this;
        }

        public Criteria andDefineTypeIsNull() {
            addCriterion("define_type is null");
            return (Criteria) this;
        }

        public Criteria andDefineTypeIsNotNull() {
            addCriterion("define_type is not null");
            return (Criteria) this;
        }

        public Criteria andDefineTypeEqualTo(Integer value) {
            addCriterion("define_type =", value, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeNotEqualTo(Integer value) {
            addCriterion("define_type <>", value, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeGreaterThan(Integer value) {
            addCriterion("define_type >", value, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("define_type >=", value, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeLessThan(Integer value) {
            addCriterion("define_type <", value, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeLessThanOrEqualTo(Integer value) {
            addCriterion("define_type <=", value, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeIn(List<Integer> values) {
            addCriterion("define_type in", values, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeNotIn(List<Integer> values) {
            addCriterion("define_type not in", values, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeBetween(Integer value1, Integer value2) {
            addCriterion("define_type between", value1, value2, "defineType");
            return (Criteria) this;
        }

        public Criteria andDefineTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("define_type not between", value1, value2, "defineType");
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

        public Criteria andAwardAmountIsNull() {
            addCriterion("award_amount is null");
            return (Criteria) this;
        }

        public Criteria andAwardAmountIsNotNull() {
            addCriterion("award_amount is not null");
            return (Criteria) this;
        }

        public Criteria andAwardAmountEqualTo(BigDecimal value) {
            addCriterion("award_amount =", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountNotEqualTo(BigDecimal value) {
            addCriterion("award_amount <>", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountGreaterThan(BigDecimal value) {
            addCriterion("award_amount >", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("award_amount >=", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountLessThan(BigDecimal value) {
            addCriterion("award_amount <", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("award_amount <=", value, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountIn(List<BigDecimal> values) {
            addCriterion("award_amount in", values, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountNotIn(List<BigDecimal> values) {
            addCriterion("award_amount not in", values, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_amount between", value1, value2, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_amount not between", value1, value2, "awardAmount");
            return (Criteria) this;
        }

        public Criteria andAwardScaleIsNull() {
            addCriterion("award_scale is null");
            return (Criteria) this;
        }

        public Criteria andAwardScaleIsNotNull() {
            addCriterion("award_scale is not null");
            return (Criteria) this;
        }

        public Criteria andAwardScaleEqualTo(BigDecimal value) {
            addCriterion("award_scale =", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleNotEqualTo(BigDecimal value) {
            addCriterion("award_scale <>", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleGreaterThan(BigDecimal value) {
            addCriterion("award_scale >", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("award_scale >=", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleLessThan(BigDecimal value) {
            addCriterion("award_scale <", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleLessThanOrEqualTo(BigDecimal value) {
            addCriterion("award_scale <=", value, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleIn(List<BigDecimal> values) {
            addCriterion("award_scale in", values, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleNotIn(List<BigDecimal> values) {
            addCriterion("award_scale not in", values, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_scale between", value1, value2, "awardScale");
            return (Criteria) this;
        }

        public Criteria andAwardScaleNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("award_scale not between", value1, value2, "awardScale");
            return (Criteria) this;
        }

        public Criteria andMinAmountIsNull() {
            addCriterion("min_amount is null");
            return (Criteria) this;
        }

        public Criteria andMinAmountIsNotNull() {
            addCriterion("min_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMinAmountEqualTo(BigDecimal value) {
            addCriterion("min_amount =", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotEqualTo(BigDecimal value) {
            addCriterion("min_amount <>", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountGreaterThan(BigDecimal value) {
            addCriterion("min_amount >", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_amount >=", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountLessThan(BigDecimal value) {
            addCriterion("min_amount <", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_amount <=", value, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountIn(List<BigDecimal> values) {
            addCriterion("min_amount in", values, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotIn(List<BigDecimal> values) {
            addCriterion("min_amount not in", values, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_amount between", value1, value2, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMinAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_amount not between", value1, value2, "minAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountIsNull() {
            addCriterion("max_amount is null");
            return (Criteria) this;
        }

        public Criteria andMaxAmountIsNotNull() {
            addCriterion("max_amount is not null");
            return (Criteria) this;
        }

        public Criteria andMaxAmountEqualTo(BigDecimal value) {
            addCriterion("max_amount =", value, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountNotEqualTo(BigDecimal value) {
            addCriterion("max_amount <>", value, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountGreaterThan(BigDecimal value) {
            addCriterion("max_amount >", value, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_amount >=", value, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountLessThan(BigDecimal value) {
            addCriterion("max_amount <", value, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_amount <=", value, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountIn(List<BigDecimal> values) {
            addCriterion("max_amount in", values, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountNotIn(List<BigDecimal> values) {
            addCriterion("max_amount not in", values, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_amount between", value1, value2, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andMaxAmountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_amount not between", value1, value2, "maxAmount");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayIsNull() {
            addCriterion("available_transfer_day is null");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayIsNotNull() {
            addCriterion("available_transfer_day is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayEqualTo(Integer value) {
            addCriterion("available_transfer_day =", value, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayNotEqualTo(Integer value) {
            addCriterion("available_transfer_day <>", value, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayGreaterThan(Integer value) {
            addCriterion("available_transfer_day >", value, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayGreaterThanOrEqualTo(Integer value) {
            addCriterion("available_transfer_day >=", value, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayLessThan(Integer value) {
            addCriterion("available_transfer_day <", value, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayLessThanOrEqualTo(Integer value) {
            addCriterion("available_transfer_day <=", value, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayIn(List<Integer> values) {
            addCriterion("available_transfer_day in", values, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayNotIn(List<Integer> values) {
            addCriterion("available_transfer_day not in", values, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayBetween(Integer value1, Integer value2) {
            addCriterion("available_transfer_day between", value1, value2, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andAvailableTransferDayNotBetween(Integer value1, Integer value2) {
            addCriterion("available_transfer_day not between", value1, value2, "availableTransferDay");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldIsNull() {
            addCriterion("integral_fold is null");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldIsNotNull() {
            addCriterion("integral_fold is not null");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldEqualTo(Integer value) {
            addCriterion("integral_fold =", value, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldNotEqualTo(Integer value) {
            addCriterion("integral_fold <>", value, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldGreaterThan(Integer value) {
            addCriterion("integral_fold >", value, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldGreaterThanOrEqualTo(Integer value) {
            addCriterion("integral_fold >=", value, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldLessThan(Integer value) {
            addCriterion("integral_fold <", value, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldLessThanOrEqualTo(Integer value) {
            addCriterion("integral_fold <=", value, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldIn(List<Integer> values) {
            addCriterion("integral_fold in", values, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldNotIn(List<Integer> values) {
            addCriterion("integral_fold not in", values, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldBetween(Integer value1, Integer value2) {
            addCriterion("integral_fold between", value1, value2, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIntegralFoldNotBetween(Integer value1, Integer value2) {
            addCriterion("integral_fold not between", value1, value2, "integralFold");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeIsNull() {
            addCriterion("is_auth_code is null");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeIsNotNull() {
            addCriterion("is_auth_code is not null");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeEqualTo(Integer value) {
            addCriterion("is_auth_code =", value, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeNotEqualTo(Integer value) {
            addCriterion("is_auth_code <>", value, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeGreaterThan(Integer value) {
            addCriterion("is_auth_code >", value, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_auth_code >=", value, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeLessThan(Integer value) {
            addCriterion("is_auth_code <", value, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeLessThanOrEqualTo(Integer value) {
            addCriterion("is_auth_code <=", value, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeIn(List<Integer> values) {
            addCriterion("is_auth_code in", values, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeNotIn(List<Integer> values) {
            addCriterion("is_auth_code not in", values, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeBetween(Integer value1, Integer value2) {
            addCriterion("is_auth_code between", value1, value2, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsAuthCodeNotBetween(Integer value1, Integer value2) {
            addCriterion("is_auth_code not between", value1, value2, "isAuthCode");
            return (Criteria) this;
        }

        public Criteria andIsIssueIsNull() {
            addCriterion("is_issue is null");
            return (Criteria) this;
        }

        public Criteria andIsIssueIsNotNull() {
            addCriterion("is_issue is not null");
            return (Criteria) this;
        }

        public Criteria andIsIssueEqualTo(Integer value) {
            addCriterion("is_issue =", value, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueNotEqualTo(Integer value) {
            addCriterion("is_issue <>", value, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueGreaterThan(Integer value) {
            addCriterion("is_issue >", value, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_issue >=", value, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueLessThan(Integer value) {
            addCriterion("is_issue <", value, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueLessThanOrEqualTo(Integer value) {
            addCriterion("is_issue <=", value, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueIn(List<Integer> values) {
            addCriterion("is_issue in", values, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueNotIn(List<Integer> values) {
            addCriterion("is_issue not in", values, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueBetween(Integer value1, Integer value2) {
            addCriterion("is_issue between", value1, value2, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsIssueNotBetween(Integer value1, Integer value2) {
            addCriterion("is_issue not between", value1, value2, "isIssue");
            return (Criteria) this;
        }

        public Criteria andIsAppIsNull() {
            addCriterion("is_app is null");
            return (Criteria) this;
        }

        public Criteria andIsAppIsNotNull() {
            addCriterion("is_app is not null");
            return (Criteria) this;
        }

        public Criteria andIsAppEqualTo(Integer value) {
            addCriterion("is_app =", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppNotEqualTo(Integer value) {
            addCriterion("is_app <>", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppGreaterThan(Integer value) {
            addCriterion("is_app >", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_app >=", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppLessThan(Integer value) {
            addCriterion("is_app <", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppLessThanOrEqualTo(Integer value) {
            addCriterion("is_app <=", value, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppIn(List<Integer> values) {
            addCriterion("is_app in", values, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppNotIn(List<Integer> values) {
            addCriterion("is_app not in", values, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppBetween(Integer value1, Integer value2) {
            addCriterion("is_app between", value1, value2, "isApp");
            return (Criteria) this;
        }

        public Criteria andIsAppNotBetween(Integer value1, Integer value2) {
            addCriterion("is_app not between", value1, value2, "isApp");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordIsNull() {
            addCriterion("appoint_password is null");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordIsNotNull() {
            addCriterion("appoint_password is not null");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordEqualTo(String value) {
            addCriterion("appoint_password =", value, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordNotEqualTo(String value) {
            addCriterion("appoint_password <>", value, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordGreaterThan(String value) {
            addCriterion("appoint_password >", value, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("appoint_password >=", value, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordLessThan(String value) {
            addCriterion("appoint_password <", value, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordLessThanOrEqualTo(String value) {
            addCriterion("appoint_password <=", value, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordLike(String value) {
            addCriterion("appoint_password like", value, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordNotLike(String value) {
            addCriterion("appoint_password not like", value, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordIn(List<String> values) {
            addCriterion("appoint_password in", values, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordNotIn(List<String> values) {
            addCriterion("appoint_password not in", values, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordBetween(String value1, String value2) {
            addCriterion("appoint_password between", value1, value2, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andAppointPasswordNotBetween(String value1, String value2) {
            addCriterion("appoint_password not between", value1, value2, "appointPassword");
            return (Criteria) this;
        }

        public Criteria andProductStatusIsNull() {
            addCriterion("product_status is null");
            return (Criteria) this;
        }

        public Criteria andProductStatusIsNotNull() {
            addCriterion("product_status is not null");
            return (Criteria) this;
        }

        public Criteria andProductStatusEqualTo(Integer value) {
            addCriterion("product_status =", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotEqualTo(Integer value) {
            addCriterion("product_status <>", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusGreaterThan(Integer value) {
            addCriterion("product_status >", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_status >=", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLessThan(Integer value) {
            addCriterion("product_status <", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusLessThanOrEqualTo(Integer value) {
            addCriterion("product_status <=", value, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusIn(List<Integer> values) {
            addCriterion("product_status in", values, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotIn(List<Integer> values) {
            addCriterion("product_status not in", values, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusBetween(Integer value1, Integer value2) {
            addCriterion("product_status between", value1, value2, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("product_status not between", value1, value2, "productStatus");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNull() {
            addCriterion("product_type is null");
            return (Criteria) this;
        }

        public Criteria andProductTypeIsNotNull() {
            addCriterion("product_type is not null");
            return (Criteria) this;
        }

        public Criteria andProductTypeEqualTo(Integer value) {
            addCriterion("product_type =", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotEqualTo(Integer value) {
            addCriterion("product_type <>", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThan(Integer value) {
            addCriterion("product_type >", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_type >=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThan(Integer value) {
            addCriterion("product_type <", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeLessThanOrEqualTo(Integer value) {
            addCriterion("product_type <=", value, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeIn(List<Integer> values) {
            addCriterion("product_type in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotIn(List<Integer> values) {
            addCriterion("product_type not in", values, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeBetween(Integer value1, Integer value2) {
            addCriterion("product_type between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andProductTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("product_type not between", value1, value2, "productType");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeIsNull() {
            addCriterion("available_invest_time is null");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeIsNotNull() {
            addCriterion("available_invest_time is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeEqualTo(Date value) {
            addCriterion("available_invest_time =", value, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeNotEqualTo(Date value) {
            addCriterion("available_invest_time <>", value, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeGreaterThan(Date value) {
            addCriterion("available_invest_time >", value, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("available_invest_time >=", value, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeLessThan(Date value) {
            addCriterion("available_invest_time <", value, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeLessThanOrEqualTo(Date value) {
            addCriterion("available_invest_time <=", value, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeIn(List<Date> values) {
            addCriterion("available_invest_time in", values, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeNotIn(List<Date> values) {
            addCriterion("available_invest_time not in", values, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeBetween(Date value1, Date value2) {
            addCriterion("available_invest_time between", value1, value2, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAvailableInvestTimeNotBetween(Date value1, Date value2) {
            addCriterion("available_invest_time not between", value1, value2, "availableInvestTime");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNull() {
            addCriterion("audit_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIsNotNull() {
            addCriterion("audit_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdEqualTo(String value) {
            addCriterion("audit_user_id =", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotEqualTo(String value) {
            addCriterion("audit_user_id <>", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThan(String value) {
            addCriterion("audit_user_id >", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdGreaterThanOrEqualTo(String value) {
            addCriterion("audit_user_id >=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThan(String value) {
            addCriterion("audit_user_id <", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLessThanOrEqualTo(String value) {
            addCriterion("audit_user_id <=", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdLike(String value) {
            addCriterion("audit_user_id like", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotLike(String value) {
            addCriterion("audit_user_id not like", value, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdIn(List<String> values) {
            addCriterion("audit_user_id in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotIn(List<String> values) {
            addCriterion("audit_user_id not in", values, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdBetween(String value1, String value2) {
            addCriterion("audit_user_id between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserIdNotBetween(String value1, String value2) {
            addCriterion("audit_user_id not between", value1, value2, "auditUserId");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNull() {
            addCriterion("audit_user_name is null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIsNotNull() {
            addCriterion("audit_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameEqualTo(String value) {
            addCriterion("audit_user_name =", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotEqualTo(String value) {
            addCriterion("audit_user_name <>", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThan(String value) {
            addCriterion("audit_user_name >", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("audit_user_name >=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThan(String value) {
            addCriterion("audit_user_name <", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLessThanOrEqualTo(String value) {
            addCriterion("audit_user_name <=", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameLike(String value) {
            addCriterion("audit_user_name like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotLike(String value) {
            addCriterion("audit_user_name not like", value, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameIn(List<String> values) {
            addCriterion("audit_user_name in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotIn(List<String> values) {
            addCriterion("audit_user_name not in", values, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameBetween(String value1, String value2) {
            addCriterion("audit_user_name between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditUserNameNotBetween(String value1, String value2) {
            addCriterion("audit_user_name not between", value1, value2, "auditUserName");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNull() {
            addCriterion("audit_time is null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIsNotNull() {
            addCriterion("audit_time is not null");
            return (Criteria) this;
        }

        public Criteria andAuditTimeEqualTo(Date value) {
            addCriterion("audit_time =", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotEqualTo(Date value) {
            addCriterion("audit_time <>", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThan(Date value) {
            addCriterion("audit_time >", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("audit_time >=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThan(Date value) {
            addCriterion("audit_time <", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeLessThanOrEqualTo(Date value) {
            addCriterion("audit_time <=", value, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeIn(List<Date> values) {
            addCriterion("audit_time in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotIn(List<Date> values) {
            addCriterion("audit_time not in", values, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeBetween(Date value1, Date value2) {
            addCriterion("audit_time between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andAuditTimeNotBetween(Date value1, Date value2) {
            addCriterion("audit_time not between", value1, value2, "auditTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andSourceStoreIsNull() {
            addCriterion("source_store is null");
            return (Criteria) this;
        }

        public Criteria andSourceStoreIsNotNull() {
            addCriterion("source_store is not null");
            return (Criteria) this;
        }

        public Criteria andSourceStoreEqualTo(String value) {
            addCriterion("source_store =", value, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreNotEqualTo(String value) {
            addCriterion("source_store <>", value, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreGreaterThan(String value) {
            addCriterion("source_store >", value, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreGreaterThanOrEqualTo(String value) {
            addCriterion("source_store >=", value, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreLessThan(String value) {
            addCriterion("source_store <", value, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreLessThanOrEqualTo(String value) {
            addCriterion("source_store <=", value, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreLike(String value) {
            addCriterion("source_store like", value, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreNotLike(String value) {
            addCriterion("source_store not like", value, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreIn(List<String> values) {
            addCriterion("source_store in", values, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreNotIn(List<String> values) {
            addCriterion("source_store not in", values, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreBetween(String value1, String value2) {
            addCriterion("source_store between", value1, value2, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andSourceStoreNotBetween(String value1, String value2) {
            addCriterion("source_store not between", value1, value2, "sourceStore");
            return (Criteria) this;
        }

        public Criteria andWeightingIsNull() {
            addCriterion("weighting is null");
            return (Criteria) this;
        }

        public Criteria andWeightingIsNotNull() {
            addCriterion("weighting is not null");
            return (Criteria) this;
        }

        public Criteria andWeightingEqualTo(BigDecimal value) {
            addCriterion("weighting =", value, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingNotEqualTo(BigDecimal value) {
            addCriterion("weighting <>", value, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingGreaterThan(BigDecimal value) {
            addCriterion("weighting >", value, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weighting >=", value, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingLessThan(BigDecimal value) {
            addCriterion("weighting <", value, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weighting <=", value, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingIn(List<BigDecimal> values) {
            addCriterion("weighting in", values, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingNotIn(List<BigDecimal> values) {
            addCriterion("weighting not in", values, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weighting between", value1, value2, "weighting");
            return (Criteria) this;
        }

        public Criteria andWeightingNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weighting not between", value1, value2, "weighting");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowIsNull() {
            addCriterion("is_timing_show is null");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowIsNotNull() {
            addCriterion("is_timing_show is not null");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowEqualTo(Integer value) {
            addCriterion("is_timing_show =", value, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowNotEqualTo(Integer value) {
            addCriterion("is_timing_show <>", value, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowGreaterThan(Integer value) {
            addCriterion("is_timing_show >", value, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_timing_show >=", value, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowLessThan(Integer value) {
            addCriterion("is_timing_show <", value, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowLessThanOrEqualTo(Integer value) {
            addCriterion("is_timing_show <=", value, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowIn(List<Integer> values) {
            addCriterion("is_timing_show in", values, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowNotIn(List<Integer> values) {
            addCriterion("is_timing_show not in", values, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowBetween(Integer value1, Integer value2) {
            addCriterion("is_timing_show between", value1, value2, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTimingShowNotBetween(Integer value1, Integer value2) {
            addCriterion("is_timing_show not between", value1, value2, "isTimingShow");
            return (Criteria) this;
        }

        public Criteria andIsTurnIsNull() {
            addCriterion("is_turn is null");
            return (Criteria) this;
        }

        public Criteria andIsTurnIsNotNull() {
            addCriterion("is_turn is not null");
            return (Criteria) this;
        }

        public Criteria andIsTurnEqualTo(String value) {
            addCriterion("is_turn =", value, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnNotEqualTo(String value) {
            addCriterion("is_turn <>", value, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnGreaterThan(String value) {
            addCriterion("is_turn >", value, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnGreaterThanOrEqualTo(String value) {
            addCriterion("is_turn >=", value, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnLessThan(String value) {
            addCriterion("is_turn <", value, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnLessThanOrEqualTo(String value) {
            addCriterion("is_turn <=", value, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnLike(String value) {
            addCriterion("is_turn like", value, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnNotLike(String value) {
            addCriterion("is_turn not like", value, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnIn(List<String> values) {
            addCriterion("is_turn in", values, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnNotIn(List<String> values) {
            addCriterion("is_turn not in", values, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnBetween(String value1, String value2) {
            addCriterion("is_turn between", value1, value2, "isTurn");
            return (Criteria) this;
        }

        public Criteria andIsTurnNotBetween(String value1, String value2) {
            addCriterion("is_turn not between", value1, value2, "isTurn");
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
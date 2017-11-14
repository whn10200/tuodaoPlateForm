package com.tuodao.bp.useraccount.persistence.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserInfoExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public UserInfoExample() {
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

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIsNull() {
            addCriterion("login_password is null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIsNotNull() {
            addCriterion("login_password is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordEqualTo(String value) {
            addCriterion("login_password =", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotEqualTo(String value) {
            addCriterion("login_password <>", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordGreaterThan(String value) {
            addCriterion("login_password >", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("login_password >=", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLessThan(String value) {
            addCriterion("login_password <", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLessThanOrEqualTo(String value) {
            addCriterion("login_password <=", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLike(String value) {
            addCriterion("login_password like", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotLike(String value) {
            addCriterion("login_password not like", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIn(List<String> values) {
            addCriterion("login_password in", values, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotIn(List<String> values) {
            addCriterion("login_password not in", values, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordBetween(String value1, String value2) {
            addCriterion("login_password between", value1, value2, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotBetween(String value1, String value2) {
            addCriterion("login_password not between", value1, value2, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyIsNull() {
            addCriterion("login_pwd_key is null");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyIsNotNull() {
            addCriterion("login_pwd_key is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyEqualTo(String value) {
            addCriterion("login_pwd_key =", value, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyNotEqualTo(String value) {
            addCriterion("login_pwd_key <>", value, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyGreaterThan(String value) {
            addCriterion("login_pwd_key >", value, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyGreaterThanOrEqualTo(String value) {
            addCriterion("login_pwd_key >=", value, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyLessThan(String value) {
            addCriterion("login_pwd_key <", value, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyLessThanOrEqualTo(String value) {
            addCriterion("login_pwd_key <=", value, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyLike(String value) {
            addCriterion("login_pwd_key like", value, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyNotLike(String value) {
            addCriterion("login_pwd_key not like", value, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyIn(List<String> values) {
            addCriterion("login_pwd_key in", values, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyNotIn(List<String> values) {
            addCriterion("login_pwd_key not in", values, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyBetween(String value1, String value2) {
            addCriterion("login_pwd_key between", value1, value2, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andLoginPwdKeyNotBetween(String value1, String value2) {
            addCriterion("login_pwd_key not between", value1, value2, "loginPwdKey");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelIsNull() {
            addCriterion("pw_security_level is null");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelIsNotNull() {
            addCriterion("pw_security_level is not null");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelEqualTo(Integer value) {
            addCriterion("pw_security_level =", value, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelNotEqualTo(Integer value) {
            addCriterion("pw_security_level <>", value, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelGreaterThan(Integer value) {
            addCriterion("pw_security_level >", value, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("pw_security_level >=", value, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelLessThan(Integer value) {
            addCriterion("pw_security_level <", value, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelLessThanOrEqualTo(Integer value) {
            addCriterion("pw_security_level <=", value, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelIn(List<Integer> values) {
            addCriterion("pw_security_level in", values, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelNotIn(List<Integer> values) {
            addCriterion("pw_security_level not in", values, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelBetween(Integer value1, Integer value2) {
            addCriterion("pw_security_level between", value1, value2, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPwSecurityLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("pw_security_level not between", value1, value2, "pwSecurityLevel");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNull() {
            addCriterion("pay_password is null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIsNotNull() {
            addCriterion("pay_password is not null");
            return (Criteria) this;
        }

        public Criteria andPayPasswordEqualTo(String value) {
            addCriterion("pay_password =", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotEqualTo(String value) {
            addCriterion("pay_password <>", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThan(String value) {
            addCriterion("pay_password >", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("pay_password >=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThan(String value) {
            addCriterion("pay_password <", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLessThanOrEqualTo(String value) {
            addCriterion("pay_password <=", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordLike(String value) {
            addCriterion("pay_password like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotLike(String value) {
            addCriterion("pay_password not like", value, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordIn(List<String> values) {
            addCriterion("pay_password in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotIn(List<String> values) {
            addCriterion("pay_password not in", values, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordBetween(String value1, String value2) {
            addCriterion("pay_password between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPasswordNotBetween(String value1, String value2) {
            addCriterion("pay_password not between", value1, value2, "payPassword");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyIsNull() {
            addCriterion("pay_pwd_key is null");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyIsNotNull() {
            addCriterion("pay_pwd_key is not null");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyEqualTo(String value) {
            addCriterion("pay_pwd_key =", value, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyNotEqualTo(String value) {
            addCriterion("pay_pwd_key <>", value, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyGreaterThan(String value) {
            addCriterion("pay_pwd_key >", value, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyGreaterThanOrEqualTo(String value) {
            addCriterion("pay_pwd_key >=", value, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyLessThan(String value) {
            addCriterion("pay_pwd_key <", value, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyLessThanOrEqualTo(String value) {
            addCriterion("pay_pwd_key <=", value, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyLike(String value) {
            addCriterion("pay_pwd_key like", value, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyNotLike(String value) {
            addCriterion("pay_pwd_key not like", value, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyIn(List<String> values) {
            addCriterion("pay_pwd_key in", values, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyNotIn(List<String> values) {
            addCriterion("pay_pwd_key not in", values, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyBetween(String value1, String value2) {
            addCriterion("pay_pwd_key between", value1, value2, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andPayPwdKeyNotBetween(String value1, String value2) {
            addCriterion("pay_pwd_key not between", value1, value2, "payPwdKey");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNull() {
            addCriterion("avatar_url is null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIsNotNull() {
            addCriterion("avatar_url is not null");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlEqualTo(String value) {
            addCriterion("avatar_url =", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotEqualTo(String value) {
            addCriterion("avatar_url <>", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThan(String value) {
            addCriterion("avatar_url >", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlGreaterThanOrEqualTo(String value) {
            addCriterion("avatar_url >=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThan(String value) {
            addCriterion("avatar_url <", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLessThanOrEqualTo(String value) {
            addCriterion("avatar_url <=", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlLike(String value) {
            addCriterion("avatar_url like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotLike(String value) {
            addCriterion("avatar_url not like", value, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlIn(List<String> values) {
            addCriterion("avatar_url in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotIn(List<String> values) {
            addCriterion("avatar_url not in", values, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlBetween(String value1, String value2) {
            addCriterion("avatar_url between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andAvatarUrlNotBetween(String value1, String value2) {
            addCriterion("avatar_url not between", value1, value2, "avatarUrl");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoIsNull() {
            addCriterion("direct_inviter_no is null");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoIsNotNull() {
            addCriterion("direct_inviter_no is not null");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoEqualTo(String value) {
            addCriterion("direct_inviter_no =", value, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoNotEqualTo(String value) {
            addCriterion("direct_inviter_no <>", value, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoGreaterThan(String value) {
            addCriterion("direct_inviter_no >", value, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoGreaterThanOrEqualTo(String value) {
            addCriterion("direct_inviter_no >=", value, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoLessThan(String value) {
            addCriterion("direct_inviter_no <", value, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoLessThanOrEqualTo(String value) {
            addCriterion("direct_inviter_no <=", value, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoLike(String value) {
            addCriterion("direct_inviter_no like", value, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoNotLike(String value) {
            addCriterion("direct_inviter_no not like", value, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoIn(List<String> values) {
            addCriterion("direct_inviter_no in", values, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoNotIn(List<String> values) {
            addCriterion("direct_inviter_no not in", values, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoBetween(String value1, String value2) {
            addCriterion("direct_inviter_no between", value1, value2, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andDirectInviterNoNotBetween(String value1, String value2) {
            addCriterion("direct_inviter_no not between", value1, value2, "directInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoIsNull() {
            addCriterion("indirect_inviter_no is null");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoIsNotNull() {
            addCriterion("indirect_inviter_no is not null");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoEqualTo(String value) {
            addCriterion("indirect_inviter_no =", value, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoNotEqualTo(String value) {
            addCriterion("indirect_inviter_no <>", value, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoGreaterThan(String value) {
            addCriterion("indirect_inviter_no >", value, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoGreaterThanOrEqualTo(String value) {
            addCriterion("indirect_inviter_no >=", value, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoLessThan(String value) {
            addCriterion("indirect_inviter_no <", value, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoLessThanOrEqualTo(String value) {
            addCriterion("indirect_inviter_no <=", value, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoLike(String value) {
            addCriterion("indirect_inviter_no like", value, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoNotLike(String value) {
            addCriterion("indirect_inviter_no not like", value, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoIn(List<String> values) {
            addCriterion("indirect_inviter_no in", values, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoNotIn(List<String> values) {
            addCriterion("indirect_inviter_no not in", values, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoBetween(String value1, String value2) {
            addCriterion("indirect_inviter_no between", value1, value2, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andIndirectInviterNoNotBetween(String value1, String value2) {
            addCriterion("indirect_inviter_no not between", value1, value2, "indirectInviterNo");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNull() {
            addCriterion("user_status is null");
            return (Criteria) this;
        }

        public Criteria andUserStatusIsNotNull() {
            addCriterion("user_status is not null");
            return (Criteria) this;
        }

        public Criteria andUserStatusEqualTo(Integer value) {
            addCriterion("user_status =", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotEqualTo(Integer value) {
            addCriterion("user_status <>", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThan(Integer value) {
            addCriterion("user_status >", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_status >=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThan(Integer value) {
            addCriterion("user_status <", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusLessThanOrEqualTo(Integer value) {
            addCriterion("user_status <=", value, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusIn(List<Integer> values) {
            addCriterion("user_status in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotIn(List<Integer> values) {
            addCriterion("user_status not in", values, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusBetween(Integer value1, Integer value2) {
            addCriterion("user_status between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("user_status not between", value1, value2, "userStatus");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNull() {
            addCriterion("user_type is null");
            return (Criteria) this;
        }

        public Criteria andUserTypeIsNotNull() {
            addCriterion("user_type is not null");
            return (Criteria) this;
        }

        public Criteria andUserTypeEqualTo(Integer value) {
            addCriterion("user_type =", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotEqualTo(Integer value) {
            addCriterion("user_type <>", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThan(Integer value) {
            addCriterion("user_type >", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_type >=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThan(Integer value) {
            addCriterion("user_type <", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("user_type <=", value, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeIn(List<Integer> values) {
            addCriterion("user_type in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotIn(List<Integer> values) {
            addCriterion("user_type not in", values, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("user_type between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("user_type not between", value1, value2, "userType");
            return (Criteria) this;
        }

        public Criteria andInvestFlagIsNull() {
            addCriterion("invest_flag is null");
            return (Criteria) this;
        }

        public Criteria andInvestFlagIsNotNull() {
            addCriterion("invest_flag is not null");
            return (Criteria) this;
        }

        public Criteria andInvestFlagEqualTo(Integer value) {
            addCriterion("invest_flag =", value, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagNotEqualTo(Integer value) {
            addCriterion("invest_flag <>", value, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagGreaterThan(Integer value) {
            addCriterion("invest_flag >", value, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("invest_flag >=", value, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagLessThan(Integer value) {
            addCriterion("invest_flag <", value, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagLessThanOrEqualTo(Integer value) {
            addCriterion("invest_flag <=", value, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagIn(List<Integer> values) {
            addCriterion("invest_flag in", values, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagNotIn(List<Integer> values) {
            addCriterion("invest_flag not in", values, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagBetween(Integer value1, Integer value2) {
            addCriterion("invest_flag between", value1, value2, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("invest_flag not between", value1, value2, "investFlag");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeIsNull() {
            addCriterion("invest_user_type is null");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeIsNotNull() {
            addCriterion("invest_user_type is not null");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeEqualTo(Integer value) {
            addCriterion("invest_user_type =", value, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeNotEqualTo(Integer value) {
            addCriterion("invest_user_type <>", value, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeGreaterThan(Integer value) {
            addCriterion("invest_user_type >", value, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("invest_user_type >=", value, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeLessThan(Integer value) {
            addCriterion("invest_user_type <", value, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeLessThanOrEqualTo(Integer value) {
            addCriterion("invest_user_type <=", value, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeIn(List<Integer> values) {
            addCriterion("invest_user_type in", values, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeNotIn(List<Integer> values) {
            addCriterion("invest_user_type not in", values, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeBetween(Integer value1, Integer value2) {
            addCriterion("invest_user_type between", value1, value2, "investUserType");
            return (Criteria) this;
        }

        public Criteria andInvestUserTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("invest_user_type not between", value1, value2, "investUserType");
            return (Criteria) this;
        }

        public Criteria andIsNewbieIsNull() {
            addCriterion("is_newbie is null");
            return (Criteria) this;
        }

        public Criteria andIsNewbieIsNotNull() {
            addCriterion("is_newbie is not null");
            return (Criteria) this;
        }

        public Criteria andIsNewbieEqualTo(Integer value) {
            addCriterion("is_newbie =", value, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieNotEqualTo(Integer value) {
            addCriterion("is_newbie <>", value, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieGreaterThan(Integer value) {
            addCriterion("is_newbie >", value, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_newbie >=", value, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieLessThan(Integer value) {
            addCriterion("is_newbie <", value, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieLessThanOrEqualTo(Integer value) {
            addCriterion("is_newbie <=", value, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieIn(List<Integer> values) {
            addCriterion("is_newbie in", values, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieNotIn(List<Integer> values) {
            addCriterion("is_newbie not in", values, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieBetween(Integer value1, Integer value2) {
            addCriterion("is_newbie between", value1, value2, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsNewbieNotBetween(Integer value1, Integer value2) {
            addCriterion("is_newbie not between", value1, value2, "isNewbie");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositIsNull() {
            addCriterion("is_open_deposit is null");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositIsNotNull() {
            addCriterion("is_open_deposit is not null");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositEqualTo(Integer value) {
            addCriterion("is_open_deposit =", value, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositNotEqualTo(Integer value) {
            addCriterion("is_open_deposit <>", value, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositGreaterThan(Integer value) {
            addCriterion("is_open_deposit >", value, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_open_deposit >=", value, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositLessThan(Integer value) {
            addCriterion("is_open_deposit <", value, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositLessThanOrEqualTo(Integer value) {
            addCriterion("is_open_deposit <=", value, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositIn(List<Integer> values) {
            addCriterion("is_open_deposit in", values, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositNotIn(List<Integer> values) {
            addCriterion("is_open_deposit not in", values, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositBetween(Integer value1, Integer value2) {
            addCriterion("is_open_deposit between", value1, value2, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsOpenDepositNotBetween(Integer value1, Integer value2) {
            addCriterion("is_open_deposit not between", value1, value2, "isOpenDeposit");
            return (Criteria) this;
        }

        public Criteria andIsBindBankIsNull() {
            addCriterion("is_bind_bank is null");
            return (Criteria) this;
        }

        public Criteria andIsBindBankIsNotNull() {
            addCriterion("is_bind_bank is not null");
            return (Criteria) this;
        }

        public Criteria andIsBindBankEqualTo(Integer value) {
            addCriterion("is_bind_bank =", value, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankNotEqualTo(Integer value) {
            addCriterion("is_bind_bank <>", value, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankGreaterThan(Integer value) {
            addCriterion("is_bind_bank >", value, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_bind_bank >=", value, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankLessThan(Integer value) {
            addCriterion("is_bind_bank <", value, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankLessThanOrEqualTo(Integer value) {
            addCriterion("is_bind_bank <=", value, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankIn(List<Integer> values) {
            addCriterion("is_bind_bank in", values, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankNotIn(List<Integer> values) {
            addCriterion("is_bind_bank not in", values, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankBetween(Integer value1, Integer value2) {
            addCriterion("is_bind_bank between", value1, value2, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andIsBindBankNotBetween(Integer value1, Integer value2) {
            addCriterion("is_bind_bank not between", value1, value2, "isBindBank");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceIsNull() {
            addCriterion("register_source is null");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceIsNotNull() {
            addCriterion("register_source is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceEqualTo(Integer value) {
            addCriterion("register_source =", value, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceNotEqualTo(Integer value) {
            addCriterion("register_source <>", value, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceGreaterThan(Integer value) {
            addCriterion("register_source >", value, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceGreaterThanOrEqualTo(Integer value) {
            addCriterion("register_source >=", value, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceLessThan(Integer value) {
            addCriterion("register_source <", value, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceLessThanOrEqualTo(Integer value) {
            addCriterion("register_source <=", value, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceIn(List<Integer> values) {
            addCriterion("register_source in", values, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceNotIn(List<Integer> values) {
            addCriterion("register_source not in", values, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceBetween(Integer value1, Integer value2) {
            addCriterion("register_source between", value1, value2, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterSourceNotBetween(Integer value1, Integer value2) {
            addCriterion("register_source not between", value1, value2, "registerSource");
            return (Criteria) this;
        }

        public Criteria andRegisterIpIsNull() {
            addCriterion("register_ip is null");
            return (Criteria) this;
        }

        public Criteria andRegisterIpIsNotNull() {
            addCriterion("register_ip is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterIpEqualTo(String value) {
            addCriterion("register_ip =", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpNotEqualTo(String value) {
            addCriterion("register_ip <>", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpGreaterThan(String value) {
            addCriterion("register_ip >", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpGreaterThanOrEqualTo(String value) {
            addCriterion("register_ip >=", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpLessThan(String value) {
            addCriterion("register_ip <", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpLessThanOrEqualTo(String value) {
            addCriterion("register_ip <=", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpLike(String value) {
            addCriterion("register_ip like", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpNotLike(String value) {
            addCriterion("register_ip not like", value, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpIn(List<String> values) {
            addCriterion("register_ip in", values, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpNotIn(List<String> values) {
            addCriterion("register_ip not in", values, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpBetween(String value1, String value2) {
            addCriterion("register_ip between", value1, value2, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterIpNotBetween(String value1, String value2) {
            addCriterion("register_ip not between", value1, value2, "registerIp");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionIsNull() {
            addCriterion("register_version is null");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionIsNotNull() {
            addCriterion("register_version is not null");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionEqualTo(String value) {
            addCriterion("register_version =", value, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionNotEqualTo(String value) {
            addCriterion("register_version <>", value, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionGreaterThan(String value) {
            addCriterion("register_version >", value, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionGreaterThanOrEqualTo(String value) {
            addCriterion("register_version >=", value, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionLessThan(String value) {
            addCriterion("register_version <", value, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionLessThanOrEqualTo(String value) {
            addCriterion("register_version <=", value, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionLike(String value) {
            addCriterion("register_version like", value, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionNotLike(String value) {
            addCriterion("register_version not like", value, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionIn(List<String> values) {
            addCriterion("register_version in", values, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionNotIn(List<String> values) {
            addCriterion("register_version not in", values, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionBetween(String value1, String value2) {
            addCriterion("register_version between", value1, value2, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andRegisterVersionNotBetween(String value1, String value2) {
            addCriterion("register_version not between", value1, value2, "registerVersion");
            return (Criteria) this;
        }

        public Criteria andSourceChannelIsNull() {
            addCriterion("source_channel is null");
            return (Criteria) this;
        }

        public Criteria andSourceChannelIsNotNull() {
            addCriterion("source_channel is not null");
            return (Criteria) this;
        }

        public Criteria andSourceChannelEqualTo(String value) {
            addCriterion("source_channel =", value, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelNotEqualTo(String value) {
            addCriterion("source_channel <>", value, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelGreaterThan(String value) {
            addCriterion("source_channel >", value, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelGreaterThanOrEqualTo(String value) {
            addCriterion("source_channel >=", value, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelLessThan(String value) {
            addCriterion("source_channel <", value, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelLessThanOrEqualTo(String value) {
            addCriterion("source_channel <=", value, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelLike(String value) {
            addCriterion("source_channel like", value, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelNotLike(String value) {
            addCriterion("source_channel not like", value, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelIn(List<String> values) {
            addCriterion("source_channel in", values, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelNotIn(List<String> values) {
            addCriterion("source_channel not in", values, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelBetween(String value1, String value2) {
            addCriterion("source_channel between", value1, value2, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andSourceChannelNotBetween(String value1, String value2) {
            addCriterion("source_channel not between", value1, value2, "sourceChannel");
            return (Criteria) this;
        }

        public Criteria andInviteTypeIsNull() {
            addCriterion("invite_type is null");
            return (Criteria) this;
        }

        public Criteria andInviteTypeIsNotNull() {
            addCriterion("invite_type is not null");
            return (Criteria) this;
        }

        public Criteria andInviteTypeEqualTo(Integer value) {
            addCriterion("invite_type =", value, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeNotEqualTo(Integer value) {
            addCriterion("invite_type <>", value, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeGreaterThan(Integer value) {
            addCriterion("invite_type >", value, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("invite_type >=", value, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeLessThan(Integer value) {
            addCriterion("invite_type <", value, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeLessThanOrEqualTo(Integer value) {
            addCriterion("invite_type <=", value, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeIn(List<Integer> values) {
            addCriterion("invite_type in", values, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeNotIn(List<Integer> values) {
            addCriterion("invite_type not in", values, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeBetween(Integer value1, Integer value2) {
            addCriterion("invite_type between", value1, value2, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("invite_type not between", value1, value2, "inviteType");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIsNull() {
            addCriterion("invite_code is null");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIsNotNull() {
            addCriterion("invite_code is not null");
            return (Criteria) this;
        }

        public Criteria andInviteCodeEqualTo(String value) {
            addCriterion("invite_code =", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotEqualTo(String value) {
            addCriterion("invite_code <>", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeGreaterThan(String value) {
            addCriterion("invite_code >", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeGreaterThanOrEqualTo(String value) {
            addCriterion("invite_code >=", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLessThan(String value) {
            addCriterion("invite_code <", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLessThanOrEqualTo(String value) {
            addCriterion("invite_code <=", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeLike(String value) {
            addCriterion("invite_code like", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotLike(String value) {
            addCriterion("invite_code not like", value, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeIn(List<String> values) {
            addCriterion("invite_code in", values, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotIn(List<String> values) {
            addCriterion("invite_code not in", values, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeBetween(String value1, String value2) {
            addCriterion("invite_code between", value1, value2, "inviteCode");
            return (Criteria) this;
        }

        public Criteria andInviteCodeNotBetween(String value1, String value2) {
            addCriterion("invite_code not between", value1, value2, "inviteCode");
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
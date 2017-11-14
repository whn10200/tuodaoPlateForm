package com.tuodao.bp.common.persistence.model.basic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SmsLogExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private static final long serialVersionUID = 1L;

    public SmsLogExample() {
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

        public Criteria andRequestIpIsNull() {
            addCriterion("request_ip is null");
            return (Criteria) this;
        }

        public Criteria andRequestIpIsNotNull() {
            addCriterion("request_ip is not null");
            return (Criteria) this;
        }

        public Criteria andRequestIpEqualTo(String value) {
            addCriterion("request_ip =", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpNotEqualTo(String value) {
            addCriterion("request_ip <>", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpGreaterThan(String value) {
            addCriterion("request_ip >", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpGreaterThanOrEqualTo(String value) {
            addCriterion("request_ip >=", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpLessThan(String value) {
            addCriterion("request_ip <", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpLessThanOrEqualTo(String value) {
            addCriterion("request_ip <=", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpLike(String value) {
            addCriterion("request_ip like", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpNotLike(String value) {
            addCriterion("request_ip not like", value, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpIn(List<String> values) {
            addCriterion("request_ip in", values, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpNotIn(List<String> values) {
            addCriterion("request_ip not in", values, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpBetween(String value1, String value2) {
            addCriterion("request_ip between", value1, value2, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestIpNotBetween(String value1, String value2) {
            addCriterion("request_ip not between", value1, value2, "requestIp");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNull() {
            addCriterion("request_time is null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIsNotNull() {
            addCriterion("request_time is not null");
            return (Criteria) this;
        }

        public Criteria andRequestTimeEqualTo(Date value) {
            addCriterion("request_time =", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotEqualTo(Date value) {
            addCriterion("request_time <>", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThan(Date value) {
            addCriterion("request_time >", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("request_time >=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThan(Date value) {
            addCriterion("request_time <", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeLessThanOrEqualTo(Date value) {
            addCriterion("request_time <=", value, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeIn(List<Date> values) {
            addCriterion("request_time in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotIn(List<Date> values) {
            addCriterion("request_time not in", values, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeBetween(Date value1, Date value2) {
            addCriterion("request_time between", value1, value2, "requestTime");
            return (Criteria) this;
        }

        public Criteria andRequestTimeNotBetween(Date value1, Date value2) {
            addCriterion("request_time not between", value1, value2, "requestTime");
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

        public Criteria andContentIsNull() {
            addCriterion("content is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("content is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("content =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("content <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("content >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("content >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("content <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("content <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("content like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("content not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("content in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("content not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("content between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("content not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andCustomsIpIsNull() {
            addCriterion("customs_ip is null");
            return (Criteria) this;
        }

        public Criteria andCustomsIpIsNotNull() {
            addCriterion("customs_ip is not null");
            return (Criteria) this;
        }

        public Criteria andCustomsIpEqualTo(String value) {
            addCriterion("customs_ip =", value, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpNotEqualTo(String value) {
            addCriterion("customs_ip <>", value, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpGreaterThan(String value) {
            addCriterion("customs_ip >", value, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpGreaterThanOrEqualTo(String value) {
            addCriterion("customs_ip >=", value, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpLessThan(String value) {
            addCriterion("customs_ip <", value, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpLessThanOrEqualTo(String value) {
            addCriterion("customs_ip <=", value, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpLike(String value) {
            addCriterion("customs_ip like", value, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpNotLike(String value) {
            addCriterion("customs_ip not like", value, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpIn(List<String> values) {
            addCriterion("customs_ip in", values, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpNotIn(List<String> values) {
            addCriterion("customs_ip not in", values, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpBetween(String value1, String value2) {
            addCriterion("customs_ip between", value1, value2, "customsIp");
            return (Criteria) this;
        }

        public Criteria andCustomsIpNotBetween(String value1, String value2) {
            addCriterion("customs_ip not between", value1, value2, "customsIp");
            return (Criteria) this;
        }

        public Criteria andResposeTimeIsNull() {
            addCriterion("respose_time is null");
            return (Criteria) this;
        }

        public Criteria andResposeTimeIsNotNull() {
            addCriterion("respose_time is not null");
            return (Criteria) this;
        }

        public Criteria andResposeTimeEqualTo(Date value) {
            addCriterion("respose_time =", value, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeNotEqualTo(Date value) {
            addCriterion("respose_time <>", value, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeGreaterThan(Date value) {
            addCriterion("respose_time >", value, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("respose_time >=", value, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeLessThan(Date value) {
            addCriterion("respose_time <", value, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeLessThanOrEqualTo(Date value) {
            addCriterion("respose_time <=", value, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeIn(List<Date> values) {
            addCriterion("respose_time in", values, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeNotIn(List<Date> values) {
            addCriterion("respose_time not in", values, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeBetween(Date value1, Date value2) {
            addCriterion("respose_time between", value1, value2, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeTimeNotBetween(Date value1, Date value2) {
            addCriterion("respose_time not between", value1, value2, "resposeTime");
            return (Criteria) this;
        }

        public Criteria andResposeContentIsNull() {
            addCriterion("respose_content is null");
            return (Criteria) this;
        }

        public Criteria andResposeContentIsNotNull() {
            addCriterion("respose_content is not null");
            return (Criteria) this;
        }

        public Criteria andResposeContentEqualTo(String value) {
            addCriterion("respose_content =", value, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentNotEqualTo(String value) {
            addCriterion("respose_content <>", value, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentGreaterThan(String value) {
            addCriterion("respose_content >", value, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentGreaterThanOrEqualTo(String value) {
            addCriterion("respose_content >=", value, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentLessThan(String value) {
            addCriterion("respose_content <", value, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentLessThanOrEqualTo(String value) {
            addCriterion("respose_content <=", value, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentLike(String value) {
            addCriterion("respose_content like", value, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentNotLike(String value) {
            addCriterion("respose_content not like", value, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentIn(List<String> values) {
            addCriterion("respose_content in", values, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentNotIn(List<String> values) {
            addCriterion("respose_content not in", values, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentBetween(String value1, String value2) {
            addCriterion("respose_content between", value1, value2, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeContentNotBetween(String value1, String value2) {
            addCriterion("respose_content not between", value1, value2, "resposeContent");
            return (Criteria) this;
        }

        public Criteria andResposeResultIsNull() {
            addCriterion("respose_result is null");
            return (Criteria) this;
        }

        public Criteria andResposeResultIsNotNull() {
            addCriterion("respose_result is not null");
            return (Criteria) this;
        }

        public Criteria andResposeResultEqualTo(String value) {
            addCriterion("respose_result =", value, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultNotEqualTo(String value) {
            addCriterion("respose_result <>", value, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultGreaterThan(String value) {
            addCriterion("respose_result >", value, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultGreaterThanOrEqualTo(String value) {
            addCriterion("respose_result >=", value, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultLessThan(String value) {
            addCriterion("respose_result <", value, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultLessThanOrEqualTo(String value) {
            addCriterion("respose_result <=", value, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultLike(String value) {
            addCriterion("respose_result like", value, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultNotLike(String value) {
            addCriterion("respose_result not like", value, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultIn(List<String> values) {
            addCriterion("respose_result in", values, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultNotIn(List<String> values) {
            addCriterion("respose_result not in", values, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultBetween(String value1, String value2) {
            addCriterion("respose_result between", value1, value2, "resposeResult");
            return (Criteria) this;
        }

        public Criteria andResposeResultNotBetween(String value1, String value2) {
            addCriterion("respose_result not between", value1, value2, "resposeResult");
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
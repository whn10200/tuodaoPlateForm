package com.tuodao.bp.api.core.log;

import com.google.common.base.MoreObjects;
import com.tuodao.bp.api.core.response.RespResult;

import java.util.Date;

/**
 * 系统用户操作接口日志
 * @author : hechuan
 * @since V1.0.0
 * @Created 2017-11-10
 */
public class SystemOperateLog {
    /**
     * 自增主键
     */
    private Long id;

    /**
     * 用户编号
     */
    private String userId;

    /**
     * 用户手机号
     */
    private String mobile;

    /**
     * 请求接口
     */
    private String actionCode;

    /**
     * 请求内容（明文）
     */
    private String content;

    /**
     * 状态(1:成功;2:失败)
     */
    private Integer status;

    /**
     * 状态代码
     */
    private String code;

    /**
     * 请求时间
     */
    private Date requestTime;

    /**
     * 完成时间
     */
    private Date completeTime;

    /**
     * 请求耗时
     */
    private Integer requestDuration;

    /**
     * 服务器时间
     */
    private Date serverTime;

    /**
     * 数据库时间
     */
    private Date dbTime;

    /**
     * 请求IP地址
     */
    private String requesetIp;

    /**
     * 服务类别（APP,PC,BANK,MANAGER）
     */
    private String serviceType;

    /**
     * 请求地址
     */
    private String requestUrl;

    /**
     * 通行证编号
     */
    private String accessid;

    /**
     * 备注
     */
    private String remark;

    /**
     * 返回结果
     */
    private RespResult respResult;

    /**
     * 获取自增主键
     *
     * @return ID - 自增主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增主键
     *
     * @param id 自增主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户编号
     *
     * @return USER_ID - 用户编号
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户编号
     *
     * @param userId 用户编号
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取手机号
     * @return 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }


    /**
     * 获取请求接口
     *
     * @return ACTION_CODE - 请求接口
     */
    public String getActionCode() {
        return actionCode;
    }

    /**
     * 设置请求接口
     *
     * @param actionCode 请求接口
     */
    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    /**
     * 获取请求内容（明文）
     *
     * @return CONTENT - 请求内容（明文）
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置请求内容（明文）
     *
     * @param content 请求内容（明文）
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取状态(1:成功;2:失败)
     *
     * @return STATUS - 状态(1:成功;2:失败)
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置状态(1:成功;2:失败)
     *
     * @param status 状态(1:成功;2:失败)
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取状态代码
     *
     * @return CODE - 状态代码
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置状态代码
     *
     * @param code 状态代码
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取请求时间
     *
     * @return REQUEST_TIME - 请求时间
     */
    public Date getRequestTime() {
        return requestTime;
    }

    /**
     * 设置请求时间
     *
     * @param requestTime 请求时间
     */
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    /**
     * 获取完成时间
     *
     * @return COMPLETE_TIME - 完成时间
     */
    public Date getCompleteTime() {
        return completeTime;
    }

    /**
     * 设置完成时间
     *
     * @param completeTime 完成时间
     */
    public void setCompleteTime(Date completeTime) {
        this.completeTime = completeTime;
    }

    /**
     * 获取请求耗时
     *
     * @return REQUEST_DURATION - 请求耗时
     */
    public Integer getRequestDuration() {
        return requestDuration;
    }

    /**
     * 设置请求耗时
     *
     * @param requestDuration 请求耗时
     */
    public void setRequestDuration(Integer requestDuration) {
        this.requestDuration = requestDuration;
    }

    /**
     * 获取服务器时间
     *
     * @return SERVER_TIME - 服务器时间
     */
    public Date getServerTime() {
        return serverTime;
    }

    /**
     * 设置服务器时间
     *
     * @param serverTime 服务器时间
     */
    public void setServerTime(Date serverTime) {
        this.serverTime = serverTime;
    }

    /**
     * 获取数据库时间
     *
     * @return DB_TIME - 数据库时间
     */
    public Date getDbTime() {
        return dbTime;
    }

    /**
     * 设置数据库时间
     *
     * @param dbTime 数据库时间
     */
    public void setDbTime(Date dbTime) {
        this.dbTime = dbTime;
    }

    /**
     * 获取请求IP地址
     *
     * @return REQUESET_IP - 请求IP地址
     */
    public String getRequesetIp() {
        return requesetIp;
    }

    /**
     * 设置请求IP地址
     *
     * @param requesetIp 请求IP地址
     */
    public void setRequesetIp(String requesetIp) {
        this.requesetIp = requesetIp;
    }

    /**
     * 获取服务类别（APP,IVR,WEB）
     *
     * @return SERVICE_TYPE - 服务类别（APP,IVR,WEB）
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * 设置服务类别（APP,IVR,WEB）
     *
     * @param serviceType 服务类别（APP,IVR,WEB）
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * 获取请求地址
     *
     * @return REQUEST_URL - 请求地址
     */
    public String getRequestUrl() {
        return requestUrl;
    }

    /**
     * 设置请求地址
     *
     * @param requestUrl 请求地址
     */
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    /**
     * 获取通行证编号
     *
     * @return ACCESSID - 通行证编号
     */
    public String getAccessid() {
        return accessid;
    }

    /**
     * 设置通行证编号
     *
     * @param accessid 通行证编号
     */
    public void setAccessid(String accessid) {
        this.accessid = accessid;
    }

    /**
     * 获取备注
     *
     * @return REMARK - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 获取返回对象
     * @return 返回对象
     */
    public RespResult getRespResult() {
        return respResult;
    }

    /**
     * 设置返回对象
     * @param respResult 返回对象
     */
    public void setRespResult(RespResult respResult) {
        this.respResult = respResult;
    }


    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("userId", userId)
                .add("mobile", mobile)
                .add("actionCode", actionCode)
                .add("content", content)
                .add("status", status)
                .add("code", code)
                .add("requestTime", requestTime)
                .add("completeTime", completeTime)
                .add("requestDuration", requestDuration)
                .add("serverTime", serverTime)
                .add("dbTime", dbTime)
                .add("requesetIp", requesetIp)
                .add("serviceType", serviceType)
                .add("requestUrl", requestUrl)
                .add("accessid", accessid)
                .add("remark", remark)
                .add("respResult", respResult)
                .toString();
    }
}
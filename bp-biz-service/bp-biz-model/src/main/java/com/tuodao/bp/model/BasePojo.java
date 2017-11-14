package com.tuodao.bp.model;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class BasePojo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * PC,APP,BANK
     */
    private String requestType;

    /**
     * 版本号
     */
    private String version;
    /**
     * 用户编号
     */
    protected String userId;

    private String ip; // 用户ip编号

    /**
     * 电话号码
     */
    private String mobile;

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Map<String, Object> toMap(){
    	Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getClass().getDeclaredFields();
        for (Field field : fields){
            try {
                field.setAccessible(true);
                String name = (String) field.getName();
                Object val = (Object) field.get(this);
                map.put(name,val);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).add("userId",userId)
                .add("ip",ip)
                .add("mobile",mobile)
                .add("requestType",requestType)
                .add("version",version)
                .toString();
    }
}

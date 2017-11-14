package com.tuodao.bp.cache.basic.exception;

import java.io.Serializable;

/**
 * @description: 缓存异常
 * @author: mif
 * @date: 2017/10/13
 * @time: 15:39
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class CacheException extends RuntimeException  implements Serializable{

    private static final long serialVersionUID = 4778716174049289749L;
    /** 代码 */
    private int code;

    /** 响应消息 */
    private String msg;



    public CacheException() {
        super();
    }

    public CacheException(int code) {
        this();
        this.code = code;
    }

    public CacheException(int code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

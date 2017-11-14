package com.tuodao.bp.api.core.constant;

/**
 * 响应返回code
 * 
 * @author hechuan
 *
 * @created 2017年5月25日
 *
 * @version 1.0.0
 */
public class ResponseConstant {
	/** accessId、accesskey不匹配*/
	public static final int ACCESS_NOT_MATCH = 100101;
	
	/** 非法请求：非网关请求 */
	public static final int HEADER_ERROR = 100103;
	
	/** 非法请求：只支持POST请求 */
	public static final int POST_SUPPORT = 100104;

    /** session超时,请重新登陆*/
    public static final int SESSION_TIME_OUT = 100105;

	/** 下载用的contentType */
	public static final String OCTET_STREAM_CONTENT_TYPE = "application/octet-stream";

}

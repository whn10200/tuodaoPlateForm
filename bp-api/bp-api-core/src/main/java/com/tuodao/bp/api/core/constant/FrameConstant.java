package com.tuodao.bp.api.core.constant;

/**
 * 框架常量类
 * 
 * @author hechuan
 *
 * @created 2017年5月31日
 *
 * @version 1.0.0
 */
public class FrameConstant {
	
	/** 成功CODE */
	public static final int SUCCESS_CODE = 100000;
	/** 成功MSG */
	public static final String SUCCESS_MESSAGE = "成功";
	/** 系统异常CODE */
	public static final int SYSTEM_EXCEPTION_CODE = 100001;
	/** 系统异常MSG */
	public static final String SYSTEM_EXCEPTION_MSG = "系统异常";
	/** 分发请求异常CODE */
	public static final int FACADE_REQUEST_ERROR_CODE = 100003;
	/** 分发请求MSG */
	public static final String FACADE_REQUEST_EXCEPTION_MSG = "分发请求异常";
	/** 详见AccessType支持的类型-CODE */
	public static final int ACCESS_TYPE_NOT_SUPPORT = 100005;
	/*文件大小超出限制 */
	public static final int FILE_BEYOND_MAX_SIZE = 100006;
	/*服务请求超时 */
	public static final int SERVICE_REQUEST_TIMEOUT = 100007;
	/*服务还未注册完成 */
	public static final int SERVICE_REGISTED_JUST = 100008;

}

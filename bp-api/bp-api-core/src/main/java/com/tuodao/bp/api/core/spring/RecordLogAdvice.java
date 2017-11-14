package com.tuodao.bp.api.core.spring;

import com.tuodao.bp.api.core.constant.FrameConstant;
import com.tuodao.bp.api.core.log.SystemOperateLog;
import com.tuodao.bp.api.core.response.RespResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.annotation.Resource;

/**
 * 返回体拦截器
 * 
 * @author hechuan
 *
 * @created 2017年11月10日
 *
 * @since V1.0.0
 */
@ControllerAdvice
public class RecordLogAdvice implements ResponseBodyAdvice<RespResult> {

	/** 用户操作日志组件 */
	@Resource
	private SystemOperateLogComponent userOperateLogComponent;

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

		// 拦截所有返回体为RespResult的请求
		if (RespResult.class.equals(returnType.getParameterType())) {
			return true;
		}

		return false;
	}

	@Override
	public RespResult beforeBodyWrite(RespResult body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {

		// 记录操作日志
		SystemOperateLog userOperateLog = userOperateLogComponent.currentUserOperateLog();


		// 返回结果Code
		int code = body.getCode();

		// 请求状态
		if (FrameConstant.SUCCESS_CODE == code) {
			// 成功
			userOperateLog.setStatus(0);
		} else {
			// 失败
			userOperateLog.setStatus(1);
		}

		// 请求状态代码
		userOperateLog.setCode(String.valueOf(code));

		// 记录结果信息
		userOperateLog.setRespResult(body);

		return body;
	}

}

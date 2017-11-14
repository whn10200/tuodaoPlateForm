package com.tuodao.bp.api.facade.config;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.api.core.constant.FrameConstant;
import com.tuodao.bp.result.error.ParamErrors;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.result.exception.ParamException;

import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;

@Configuration
public class BizExceptionFeignErrorDecoder implements ErrorDecoder {

	
	private static final Logger logger = LoggerFactory.getLogger(BizExceptionFeignErrorDecoder.class);
	
	@Override
	public Exception decode(String methodKey, Response response) {
		logger.info("methodKey = [{}]",methodKey);
		
		int code = FrameConstant.SYSTEM_EXCEPTION_CODE;
		try {
			String string = Util.toString(response.body().asReader());
			logger.info("responseBody = [{}]",string);
			
			@SuppressWarnings("rawtypes")
			Map result = JSON.parseObject(string, Map.class);
			String obj = (String)result.get("message");
			ParamErrors errors = null;
			try {
				errors = JSON.parseObject(obj, ParamErrors.class);
			}catch(Exception e) {
				code = Integer.parseInt(obj);
			}
			if(null != errors) {
				throw new ParamException(errors);
			}
			
		} catch (IOException e) {
			logger.error(e.getMessage(),e);
		}
		return new MicroServiceException(code);
	}

}

package com.tuodao.bp.result.exception;

import feign.FeignException;

public class BizFeignParamException extends FeignException {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	

	public BizFeignParamException(String message) {
		super(message);
	}

}

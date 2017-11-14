package com.tuodao.bp.result.exception;

import com.tuodao.bp.result.error.DepositoryError;

import feign.FeignException;

public class DepositoryFeignException extends FeignException {

	private static final long serialVersionUID = -4733877781640056928L;

	protected DepositoryFeignException(int status, String message) {
		super(status, message);
	}

	public DepositoryFeignException(DepositoryError error) {
		this(error.getCode(), error.getMsg());
	}

	public DepositoryFeignException(int code) {
		super(String.valueOf(code));
	}

	

}

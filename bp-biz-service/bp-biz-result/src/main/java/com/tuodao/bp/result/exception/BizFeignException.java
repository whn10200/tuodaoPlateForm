package com.tuodao.bp.result.exception;

import com.tuodao.bp.result.error.TransactError;
import feign.FeignException;

public class BizFeignException extends FeignException {

	protected BizFeignException(int status, String message) {
		super(status, message);
	}

    /**
     * 交易中心内部服务使用
     * @param error
     */
    public BizFeignException(TransactError error){
	    this(error.getCode(),error.getMsg());
    }

	public BizFeignException(int code) {
		super(String.valueOf(code));
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

}



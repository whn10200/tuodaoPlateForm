package com.tuodao.bp.depository.sign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

public class CreateSign {

	private final static Logger LOGGER = LoggerFactory.getLogger("CreateSign");
	
	public static String bobRs(String presign) {
		
		LOGGER.debug("加密前字段：" + presign);
		
		BASE64Encoder b64 = new BASE64Encoder();
		byte[] bt = SignUtil.createSign(ReadCert.getPfxPrivateKey(), presign.getBytes());
		String sign = b64.encode(bt);
		LOGGER.debug("密文：" + sign);
		return sign;
	}

	
}

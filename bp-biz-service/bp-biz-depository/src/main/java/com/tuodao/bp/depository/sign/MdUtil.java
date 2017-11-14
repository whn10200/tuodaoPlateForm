package com.tuodao.bp.depository.sign;

import java.security.MessageDigest;

public class MdUtil {

	/**
	 * algorithm只有2种参数："SHA-1"和"MD5"。可以从CryptConstant中调用。
	 * */
	public static byte[] createMd(String datas, String encoding, String algorithm) {
		try {

			byte[] bytes = datas.getBytes(encoding);

			MessageDigest md = null;

			md = MessageDigest.getInstance(algorithm);
			md.reset();
			md.update(bytes);
			bytes = md.digest();

			StringBuilder md5StrBuff = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {

				String s = Integer.toHexString(0xFF & bytes[i]);

				if (s.length() == 1) {
					md5StrBuff.append("0");
				}
				md5StrBuff.append(Integer.toHexString(0xFF & bytes[i]));

			}

			return md5StrBuff.toString().getBytes(encoding);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}	
	
	
}

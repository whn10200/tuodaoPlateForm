package com.tuodao.bp.depository.sign;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Decoder;

public class SignUtil {
	
	private final static Logger log = LoggerFactory.getLogger("SignUtil");
	
	/**
	 * 软签名
	 * 
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            待签名数据
	 * @param signMethod
	 *            签名方法
	 * @return 结果
	 * @throws Exception
	 */
	public static byte[] createSign(PrivateKey privateKey, byte[] data) {
		byte[] result = null;

		try {
			Signature st = Signature.getInstance(CryptConstant.SHA1RSA);
			st.initSign(privateKey);
			st.update(data);
			result = st.sign();
			log.debug("软签名获取成功");
		} catch (Exception e) {
			log.error("软签名获取失败");
		}

		return result;
	}

	/**
	 * 软验证签名
	 * 
	 * @param publicKey
	 *            公钥
	 * @param signData
	 *            签名数据
	 * @param srcData
	 *            摘要
	 * @param validateMethod
	 *            签名方法.
	 * @return
	 * @throws Exception
	 */
	public static boolean validateSign(PublicKey publicKey, byte[] signData, byte[] srcData) {
		try {
			Signature st = Signature.getInstance(CryptConstant.SHA1RSA);
			st.initVerify(publicKey);
			st.update(srcData);
			return st.verify(signData);
		} catch (Exception e) {
			log.error("回调签名解析失败");
		}

		return false;
	}
	
	public static boolean validateSign(Certificate certificate, byte[] signData, byte[] originalData,String algorithm,String provider) {
		try {
			Security.addProvider(new BouncyCastleProvider());
			
			Signature st = Signature.getInstance(algorithm,provider);			
			st.initVerify(certificate);
			st.update(originalData);
			return st.verify(signData);
		} catch (Exception e) {
			log.error("回调签名解析失败");
		}

		return false;
	}

}

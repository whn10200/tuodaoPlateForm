package com.tuodao.bp.depository.sign;

import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

import com.tuodao.bp.model.constant.depository.BBC;





/**银联证书类（仅单证书模式）*/
public class ReadCert {
	
	/** 生成签名证书. */
	private static KeyStore keyStore = null;
	
	/** 验证签名证书. */
	private static X509Certificate cert = null;
	
	static {
		init();
	}
	
	/**
	 * 初始化所有证书.
	 */
	public static void init() {		
		keyStore = null;
		keyStore = CertUtil.loadPfx(BBC.PFX_PATH, BBC.PFX_PWD, BBC.PFX_TYPE);
		cert = CertUtil.loadX509Cert(BBC.CERT_PATH);		
	}
	
	
	
	public static PrivateKey getPfxPrivateKey(){
		return CertUtil.getPfxPrivateKey(keyStore, BBC.PFX_PWD);
	}
	
	public static PublicKey getPfxPublicKey(){
		return CertUtil.getPfxPublicKey(keyStore);
	}
	
	public static PublicKey getCertPublicKey(){
		return cert.getPublicKey();
	}
	
	public static X509Certificate getCert(){
		return cert;
	}
	
	public static String getPfxId(){
		return CertUtil.getPfxId(keyStore);
	}
		

}

package com.tuodao.bp.depository.sign;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuodao.bp.result.error.DepositoryError;
import com.tuodao.bp.result.exception.DepositoryFeignException;

import sun.misc.BASE64Decoder;

public class CertUtil {

	private final static Logger log = LoggerFactory.getLogger("CertUtil");
	
	
	/**用字符串生成PKCS8的秘钥*/
	public static PrivateKey createPKCS8Prvk(String pkString,String signType) {

		try {
			BASE64Decoder b64 = new BASE64Decoder();
			PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(b64.decodeBuffer(pkString));
			KeyFactory keyf = KeyFactory.getInstance(signType);
			PrivateKey prvKey = keyf.generatePrivate(priPKCS8);

			log.debug("私钥获取成功");
			return prvKey;
		} catch (Exception e) {
			log.error("私钥获取成功");
		}
		return null;
	}
	
	/**用字符串生成公钥*/
	public static PublicKey createPubk(String pkString,String signType) {
		try {
			KeyFactory keyFactory = KeyFactory.getInstance(signType);
			BASE64Decoder b64 = new BASE64Decoder();
			byte[] encodedKey = b64.decodeBuffer(pkString);
			PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
			
			log.debug("公钥获取成功");
			return pubKey;
		} catch (Exception e) {
			log.error("公钥获取成功");
		}
		return null;
	}

	public static KeyStore loadPfx(String pfxkeyfile, String keypwd, String type) {

		FileInputStream fis = null;
		try {
			// 注意，此处省略了IBM JDK的兼容性代码。
			KeyStore ks = KeyStore.getInstance(type);
			char[] pwd = keypwd.trim().toCharArray();
			fis = new FileInputStream(pfxkeyfile);
			ks.load(fis, pwd);
			log.info("证书文件读取成功");
			log.debug("证书路径：" + pfxkeyfile);
			return ks;
		} catch (Exception e) {
			log.error("证书文件读取失败，请检查文件路径");
			log.debug("证书路径：" + pfxkeyfile);
			e.printStackTrace();
			throw new DepositoryFeignException(DepositoryError.PFX_LOAD_ERROR);
		} finally {
			if (null != fis) {
				try {
					fis.close();
				} catch (IOException e) {
					log.error("证书文件读取失败，请检查文件路径");
					log.debug("证书路径：" + pfxkeyfile);
					e.printStackTrace();
					throw new DepositoryFeignException(DepositoryError.PFX_LOAD_ERROR);
				}
			}			
		}
		
		//return null;
	}

	public static String getPfxKeyAlias(KeyStore signKeyStore) {
		try {
			Enumeration<String> aliasenum = signKeyStore.aliases();
			String keyAlias = null;
			if (aliasenum.hasMoreElements()) {
				keyAlias = aliasenum.nextElement();
			}
			return keyAlias;
		} catch (Exception e) {
			log.error("证书文件解析失败");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取签名证书中的证书序列号
	 * 
	 * @return 证书的物理编号
	 */
	public static String getPfxId(KeyStore signKeyStore) {
		try {
			String keyAlias = getPfxKeyAlias(signKeyStore);
			X509Certificate cert = (X509Certificate) signKeyStore.getCertificate(keyAlias);
			return cert.getSerialNumber().toString();
		} catch (Exception e) {
			log.error("证书文件获取序列号失败");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 用证书获取私钥
	 */
	public static PrivateKey getPfxPrivateKey(KeyStore signKeyStore, String keypwd) {
		try {
			String keyAlias = getPfxKeyAlias(signKeyStore);
			PrivateKey privateKey = (PrivateKey) signKeyStore.getKey(keyAlias, keypwd.toCharArray());
			return privateKey;
		} catch (Exception e) {
			log.error("私钥获取失败");
			e.printStackTrace();
			throw new DepositoryFeignException(DepositoryError.PRIVATE_KEY_LOAD_ERROR);
			//return null;
		} 
	}

	/**
	 * 用证书获取公钥
	 * 
	 * @return
	 */
	public static PublicKey getPfxPublicKey(KeyStore signKeyStore) {
		try {
			String keyAlias = getPfxKeyAlias(signKeyStore);
			Certificate cert = signKeyStore.getCertificate(keyAlias);
			PublicKey pubkey = cert.getPublicKey();
			return pubkey;
		} catch (Exception e) {
			log.error("公钥获取失败");
			e.printStackTrace();
			throw new DepositoryFeignException(DepositoryError.PUBLIC_KEY_LOAD_ERROR);
			//return null;
		}
	}

	public static X509Certificate loadX509Cert(String dir){

		CertificateFactory cf = null;
		FileInputStream in = null;
		try {
			cf = CertificateFactory.getInstance("X.509");
			//in = new FileInputStream(dir);
			
			String b64_cert = "MIIFdjCCBF6gAwIBAgIKG0AAAAAAAAEnvDANBgkqhkiG9w0BAQUFADBSMQswCQYDVQQGEwJDTjENMAsGA1UECgwEQkpDQTEYMBYGA1UECwwPUHVibGljIFRydXN0IENBMRowGAYDVQQDDBFQdWJsaWMgVHJ1c3QgQ0EtMjAeFw0xNTA3MjcxNjAwMDBaFw0yNTA3MjgxNTU5NTlaMIGQMQswCQYDVQQGEwJDTjEzMDEGA1UECgwq5YyX5Lqs6ZO26KGM6IKh5Lu95pyJ6ZmQ5YWs5Y+45p2t5bee5YiG6KGMMRcwFQYDVQQLDA5CSkNBMjAxMzA2MDE4MTEzMDEGA1UEAwwq5YyX5Lqs6ZO26KGM6IKh5Lu95pyJ6ZmQ5YWs5Y+45p2t5bee5YiG6KGMMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCk34jGbHYte+AJFVunpkQcD425IVN6rDHUEJejVeHo45LoHUuxsDWhuHqNCvpFo2T78vw5DbPsxdz9FIY1AYaQif1AWGr5BdMD96974Thaz/Hy8tse8xVb2O2Rb3UM/WMMeX8UKr+wq5/LaUGzVjivJ1TaTniH0YWdMRaNqtPu4wIDAQABo4ICkTCCAo0wHwYDVR0jBBgwFoAU+7fUVhdYjCN91fhCAdTtd5tX6+kwga0GA1UdHwSBpTCBojBsoGqgaKRmMGQxCzAJBgNVBAYTAkNOMQ0wCwYDVQQKDARCSkNBMRgwFgYDVQQLDA9QdWJsaWMgVHJ1c3QgQ0ExGjAYBgNVBAMMEVB1YmxpYyBUcnVzdCBDQS0yMRAwDgYDVQQDEwdjYTRjcmwxMDKgMKAuhixodHRwOi8vbGRhcC5iamNhLm9yZy5jbi9jcmwvcHRjYS9jYTRjcmwxLmNybDAJBgNVHRMEAjAAMBEGCWCGSAGG+EIBAQQEAwIA/zAXBghghkgBhvhEAgQLSko2Nzk4OTg0MjYwGwYIKlaGSAGBMAEEDzEwNDAwMDAwMDA0NzAzMjAUBgUqVgsHCQQLSko2Nzk4OTg0MjYwGAYGKlYLBwEIBA45Q0BKSjY3OTg5ODQyNjAqBgtghkgBZQMCATAJCgQbaHR0cDovL2JqY2Eub3JnLmNuL2JqY2EuY3J0MIHnBgNVHSAEgd8wgdwwNQYJKoEcAcU4gRUBMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly93d3cuYmpjYS5vcmcuY24vY3BzMDUGCSqBHAHFOIEVAjAoMCYGCCsGAQUFBwIBFhpodHRwOi8vd3d3LmJqY2Eub3JnLmNuL2NwczA1BgkqgRwBxTiBFQMwKDAmBggrBgEFBQcCARYaaHR0cDovL3d3dy5iamNhLm9yZy5jbi9jcHMwNQYJKoEcAcU4gRUEMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly93d3cuYmpjYS5vcmcuY24vY3BzMAsGA1UdDwQEAwID+DATBgoqgRyG7zICAQEeBAUMAzY1NDANBgkqhkiG9w0BAQUFAAOCAQEAJ6tGnKhfc1H+hVmftm6mfCIr16JotFo3wd6tRuTCvemgbVjqYdyk5vXf8x5wfwcKTrNrCVdTBu7g2QVSPzmKo/dcs+98yCx7xOFyYu5zk9brSVIH/ZOo1wwcMxBXIiSTTQM+bfawueRS34DyiDUIHRHVdZcVrZM7HgMDSsNXTsygZWcNrgJC3pgHSAOE0/TEvKOx+6KS2nGvA1ALLvY9yFqWpy+45zcpqRXD+blwrlJGpnjuKM1EfmhSAVVM6iLYn+m30rNQ9MKprA90LJ9F8jt+LFpy/fdJlZhIlRHelowjBKwv7aOZJ7yIJ4+lb93RfMsdogFw9vJuH8PpK/85Ig==";
			
			BASE64Decoder b64de = new BASE64Decoder();
			X509Certificate x509 = (X509Certificate)cf.generateCertificate(new ByteArrayInputStream(b64de.decodeBuffer(b64_cert)));
			
			log.error("Certificate获取成功");
			log.debug("证书路径：" + dir);
			return x509;

		} catch (Exception e) {
			log.error("Certificate获取失败");
			log.debug("证书路径：" + dir);
			e.printStackTrace();
			throw new DepositoryFeignException(DepositoryError.CERT_LOAD_ERROR);
		}  
		finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					log.error("Certificate获取失败");
					log.debug("证书路径：" + dir);
					e.printStackTrace();
					throw new DepositoryFeignException(DepositoryError.CERT_LOAD_ERROR);
				}
			}
		}
		//return null;
	}

	
	
}

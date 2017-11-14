package com.tuodao.bp.depository.sign;

import java.io.UnsupportedEncodingException;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.utils.JsonUtil;

import sun.misc.BASE64Decoder;

public class ValidateSign {
	
	private final static Logger LOGGER = LoggerFactory.getLogger("ValidateSign");

	public static boolean validate(String json) {

		TreeMap<String, String> resMap = new TreeMap<String, String>(JsonUtil.json2Map(json));

		String sign = resMap.get(BJFN.signdata);
		resMap.put(BJFN.signdata, null);

		String presign = MapUtils.verticalCombination(resMap);

		LOGGER.debug("返回内容，加密前字段：" + presign);

		boolean b = bobRs(sign, presign);

		return b;
	}

	public static boolean bobRs(String sign, String original) {

		try {

			BASE64Decoder b64 = new BASE64Decoder();

			byte[] decodeSign = b64.decodeBuffer(sign);

			String b64_cert = "MIIFdjCCBF6gAwIBAgIKG0AAAAAAAAEnvDANBgkqhkiG9w0BAQUFADBSMQswCQYDVQQGEwJDTjENMAsGA1UECgwEQkpDQTEYMBYGA1UECwwPUHVibGljIFRydXN0IENBMRowGAYDVQQDDBFQdWJsaWMgVHJ1c3QgQ0EtMjAeFw0xNTA3MjcxNjAwMDBaFw0yNTA3MjgxNTU5NTlaMIGQMQswCQYDVQQGEwJDTjEzMDEGA1UECgwq5YyX5Lqs6ZO26KGM6IKh5Lu95pyJ6ZmQ5YWs5Y+45p2t5bee5YiG6KGMMRcwFQYDVQQLDA5CSkNBMjAxMzA2MDE4MTEzMDEGA1UEAwwq5YyX5Lqs6ZO26KGM6IKh5Lu95pyJ6ZmQ5YWs5Y+45p2t5bee5YiG6KGMMIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCk34jGbHYte+AJFVunpkQcD425IVN6rDHUEJejVeHo45LoHUuxsDWhuHqNCvpFo2T78vw5DbPsxdz9FIY1AYaQif1AWGr5BdMD96974Thaz/Hy8tse8xVb2O2Rb3UM/WMMeX8UKr+wq5/LaUGzVjivJ1TaTniH0YWdMRaNqtPu4wIDAQABo4ICkTCCAo0wHwYDVR0jBBgwFoAU+7fUVhdYjCN91fhCAdTtd5tX6+kwga0GA1UdHwSBpTCBojBsoGqgaKRmMGQxCzAJBgNVBAYTAkNOMQ0wCwYDVQQKDARCSkNBMRgwFgYDVQQLDA9QdWJsaWMgVHJ1c3QgQ0ExGjAYBgNVBAMMEVB1YmxpYyBUcnVzdCBDQS0yMRAwDgYDVQQDEwdjYTRjcmwxMDKgMKAuhixodHRwOi8vbGRhcC5iamNhLm9yZy5jbi9jcmwvcHRjYS9jYTRjcmwxLmNybDAJBgNVHRMEAjAAMBEGCWCGSAGG+EIBAQQEAwIA/zAXBghghkgBhvhEAgQLSko2Nzk4OTg0MjYwGwYIKlaGSAGBMAEEDzEwNDAwMDAwMDA0NzAzMjAUBgUqVgsHCQQLSko2Nzk4OTg0MjYwGAYGKlYLBwEIBA45Q0BKSjY3OTg5ODQyNjAqBgtghkgBZQMCATAJCgQbaHR0cDovL2JqY2Eub3JnLmNuL2JqY2EuY3J0MIHnBgNVHSAEgd8wgdwwNQYJKoEcAcU4gRUBMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly93d3cuYmpjYS5vcmcuY24vY3BzMDUGCSqBHAHFOIEVAjAoMCYGCCsGAQUFBwIBFhpodHRwOi8vd3d3LmJqY2Eub3JnLmNuL2NwczA1BgkqgRwBxTiBFQMwKDAmBggrBgEFBQcCARYaaHR0cDovL3d3dy5iamNhLm9yZy5jbi9jcHMwNQYJKoEcAcU4gRUEMCgwJgYIKwYBBQUHAgEWGmh0dHA6Ly93d3cuYmpjYS5vcmcuY24vY3BzMAsGA1UdDwQEAwID+DATBgoqgRyG7zICAQEeBAUMAzY1NDANBgkqhkiG9w0BAQUFAAOCAQEAJ6tGnKhfc1H+hVmftm6mfCIr16JotFo3wd6tRuTCvemgbVjqYdyk5vXf8x5wfwcKTrNrCVdTBu7g2QVSPzmKo/dcs+98yCx7xOFyYu5zk9brSVIH/ZOo1wwcMxBXIiSTTQM+bfawueRS34DyiDUIHRHVdZcVrZM7HgMDSsNXTsygZWcNrgJC3pgHSAOE0/TEvKOx+6KS2nGvA1ALLvY9yFqWpy+45zcpqRXD+blwrlJGpnjuKM1EfmhSAVVM6iLYn+m30rNQ9MKprA90LJ9F8jt+LFpy/fdJlZhIlRHelowjBKwv7aOZJ7yIJ4+lb93RfMsdogFw9vJuH8PpK/85Ig==";

			return SignUtil.validateSign(ReadCert.getCert(), decodeSign, original.getBytes("UTF-8"),
					CryptConstant.SHA1RSA, CryptConstant.BC);

		} catch (UnsupportedEncodingException e) {
			// LogUtil.writeErrorLog(e.getMessage(), e);
		} catch (Exception e) {
			// LogUtil.writeErrorLog(e.getMessage(), e);
		}

		return false;
	}

}

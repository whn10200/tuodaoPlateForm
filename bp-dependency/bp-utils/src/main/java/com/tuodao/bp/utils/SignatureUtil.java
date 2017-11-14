package com.tuodao.bp.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

/**
 * @description: 银行数字签名工具类
 * @author: 王艳兵
 * @date: 2017/8/28
 * @time: 14:23
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class SignatureUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(SignatureUtil.class);

    /**
     * 验证签名对象:base64 -> byte[] -> X509Certificate对象
     */
    private static final String BASE64_CERT = "MIIFdjCCBF6gAwIBAgIKG0AAAAAAAAEnvDANBgkqhkiG9w0BAQUFADBSMQswCQYDVQQGEwJDTjENMAsGA1UECgwEQkpDQTEYMBYGA1UECwwPUHVibGljIFRydXN0IENBMRowGAYDVQQDDBFQdWJsaWMgVHJ1c3QgQ0EtMjAeFw0xNTA3MjcxNjAwMDBaFw0yNTA3MjgxNTU5NTlaMIGQMQswCQYDVQQGEwJDTjEzMDEGA1UECgwq5YyX5Lqs6ZO26KGM6IKh5Lu95pyJ6ZmQ5YWs5Y";

    /**
     * rsa加密方式
     */
    private static final String SHA1RSA = "SHA1withRSA";
    /**
     * 加解密实现方式
     */
    public static final String BC = "BC";

    /**
     * 银行加密签名
     */
    private static  KeyStore keyStore = null;

    /**
     * 银行解密签名
     */
    private static X509Certificate certificate = null;

    static{
        loadBankPfxFile();
    }

    /**
     * 校验签名是否合法
     * @param signature 签名信息
     * @param original 原始信息
     * @return
     */
    public static boolean validateSign(String signature,String original){
        try {
            byte[] byteSignature = signature.getBytes();
            byte[] byteOriginal = URLDecoder.decode(original,"UTF-8").getBytes();
            return validateSign(certificate,byteSignature,byteOriginal,SHA1RSA,BC);
        } catch (Exception e) {
            LOGGER.error("解析原始签名异常",e.getMessage());
            return false;
        }
    }




    /**
     * 对给定的参数进行签名操作
     * @param original 原始数据
     * @return 签名之后的数据
     */
    public static String createSignature(String original){
        byte[] byteOriginal = original.getBytes();
        try {
            Signature signature = Signature.getInstance(SHA1RSA);
            signature.initSign(getPrivateKey());
            signature.update(byteOriginal);
            return new BASE64Encoder().encode(signature.sign());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 校验签名是否正确
     * @param certificate 解析的私钥
     * @param signature 签名信息
     * @param original 原始信息
     * @param algorithm 签名方式
     * @param provider 解析实现方式:bc,jdk
     * @return
     */
    private static boolean validateSign(X509Certificate certificate,byte[] signature,byte[] original,String algorithm,String provider){
        Security.addProvider(new BouncyCastleProvider());
        try {
            Signature st = Signature.getInstance(algorithm,provider);
            st.initVerify(certificate);
            st.update(original);
            return st.verify(signature);
        }catch (Exception e) {
            LOGGER.error("校验签名失败,{}",e.getMessage());
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 加载银行数字签名文件
     */
    private static void loadBankPfxFile(){
        String keyPath = SysConfig.getValue("pfx_path");
        String keyPwd = BankConfig.getKeyPwd();//MTcwMDExMTM=
        String keyType = SysConfig.getValue("pfx_type");//PKCS12
        keyStore = loadKeyStore(keyPath,keyPwd,keyType);
        certificate = loadX509Cert();
    }


    /**
     * 加载银行解密签名证书
     * @return
     */
    private static X509Certificate loadX509Cert(){
        FileInputStream fis = null;
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");
            ByteArrayInputStream byteIn  = new ByteArrayInputStream(new BASE64Decoder().decodeBuffer(BASE64_CERT));
            X509Certificate certificate = (X509Certificate) cf.generateCertificate(byteIn);
            return certificate;
        }catch (Exception e){
            LOGGER.error("BASE64Decoder解析x509私钥失败,{}",e.getMessage());
            e.printStackTrace();
        }finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    LOGGER.warn("关闭x509输入流失败,{}",e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 加载银行加密签名证书
     * @param keyPath 数字证书本地路径
     * @param keyPwd 数字证书密码
     * @param keyType 数字证书类型
     * @return
     */
    private static KeyStore loadKeyStore(String keyPath,String keyPwd,String keyType){
        FileInputStream fis = null;
        try {
            KeyStore keyStore = KeyStore.getInstance(keyType);
            char[] pwdChar = keyPwd.toCharArray();
            fis = new FileInputStream(keyPath);
            keyStore.load(fis,pwdChar);
            return keyStore;
        }catch (Exception e){
            LOGGER.error("解析生成keyStore对象失败,{}",e.getMessage());
            e.printStackTrace();
        }finally {
            if (fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    LOGGER.warn("关闭KeyStore输入流失败,{}",e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 根据证书获取私钥
     * @return
     */
    private static PrivateKey getPrivateKey(){
        String alias = getKeyStoreAlias();
        try {
            String keyPwd = BankConfig.getKeyPwd();
            return (PrivateKey) keyStore.getKey(alias,keyPwd.toCharArray());
        }catch (Exception e) {
            LOGGER.error("获取privateKey失败,{}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据证书获取公钥
     * @return
     */
    private static PublicKey getPublicKey(){
        String alias = getKeyStoreAlias();
        try {
            Certificate certificate = keyStore.getCertificate(alias);
            PublicKey publicKey = certificate.getPublicKey();
            return publicKey;
        } catch (KeyStoreException e) {
            LOGGER.error("获取publicKey失败,{}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取证书的名别信息
     * @return
     */
    private static String getKeyStoreAlias(){
        try {
            Enumeration<String> aliases = keyStore.aliases();
            String keyAlias = null;
            if(aliases.hasMoreElements()){
                keyAlias = aliases.nextElement();
            }
            return keyAlias;
        } catch (KeyStoreException e) {
            LOGGER.error("获取keyStore加密别名错误,{}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将加密过的签名进行二次编码
     * @param sign 需要编码的字符串
     * @return
     */
    public static String encodeSignature(String sign){
        try {
            return URLEncoder.encode(URLEncoder.encode(sign,"UTF-8"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("签名二次编码失败,{}",e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

}

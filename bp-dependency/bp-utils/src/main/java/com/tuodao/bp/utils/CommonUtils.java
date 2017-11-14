package com.tuodao.bp.utils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

public class CommonUtils {
    private static SecureRandom secureRand = new SecureRandom();
    private static Random rand = new Random(secureRand.nextLong());
    private static String localhost = getLocalHost();

    private static String CHAR_SET = "Unicode";

    private static final String RAND_STRING = "123456789abcdefghijklmnopqrstuvwxyz";
    private static final String VERIFY_CODES = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
    private static final String SMS_CODES = "123456789";

    public static String getLocalHost() {
        try {
            return InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String byte2hex(byte[] b) {

        String str = "";
        String stmp = "";

        int length = b.length;

        for (int n = 0; n < length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                str += "0";
            }
            str += stmp;
        }

        return str.toLowerCase();
    }

    public static String convertCtrlNAndBlankToHtml(String text) {
        if (StringUtils.isNotBlank(text)) {
            text = text.replace("\n", "<br/>");
        }
        return text;
    }

    public static String getRandom(String string) {

        String str = MD5Utils.md5(
                getUUID() + System.currentTimeMillis() + getRandom(999999999) + rand.nextLong() + localhost + string);
        str = str.toLowerCase();

        return str;
    }

    public static int getRandom(int accuracy) {

        return (int) (Math.random() * accuracy);
    }

    public static long getRandom(long accuracy) {

        return (long) (Math.random() * accuracy);
    }

    public static int getWordLength(String str) {

        str = StringUtils.defaultString(str, "");
        return str.replaceAll("[^\\x00-\\xff]", "**").length();
    }

    public static String nullToStrTrim(String str) {

        if (str == null) {
            str = "";
        }

        return str.trim();
    }

    public static boolean isHalfAngle(String str) {

        str = nullToStrTrim(str);
        return str.length() == getWordLength(str);
    }

    public static int nullToIntZero(String str) {
        if (nullToStrTrim(str).equals("")) {
            str = "0";
        }
        return Integer.valueOf(str, 10);
    }

    public static Integer nullToIntegerZero(String str) {
        if (nullToStrTrim(str).equals("")) {
            str = "0";
        }
        return Integer.valueOf(str, 10);
    }

    public static int getRealLength(String str, String charsetName) {
        if (StringUtils.isBlank(str)) {
            return 0;
        }
        try {
            return str.getBytes(charsetName).length;
        } catch (UnsupportedEncodingException e) {
            return 0;
        }
    }

    /**
     * 简单检查一下是否是MD5值
     *
     * @param md5
     * @return
     */
    public static boolean checkMd5(String md5) {
        if (md5.length() != 32) {
            return false;
        }
        return md5.matches("[0-9A-Fa-f]+");
    }

    public static long nullToLongZero(String str) {

        if (str == null || str.trim().length() == 0) {
            str = "0";
        }

        return Long.valueOf(str.trim(), 10);
    }

    /**
     * 生成随机验证码
     *
     * @param randstring
     * @param length
     * @return
     */
    public static String getRandom(String randstring, int length) {

        String rand = "";

        Random random = new Random();
        String c = "";
        for (int i = 1; i <= length; i++) {
            c = String.valueOf(randstring.charAt(random.nextInt(randstring.length())));
            rand += c;
        }

        return rand;
    }


    public static double nullToDoubleZero(String str) {
        if (StringUtils.isBlank(str)) {
            str = "0";
        }
        return Double.valueOf(str.trim()).doubleValue();
    }

    /**
     * 计算百分比
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return
     */
    public static String percentage(String numerator, String denominator) {
        if (StringUtils.isEmpty(numerator) || StringUtils.isEmpty(denominator)) {
            return "";
        }

        double percent = nullToDoubleZero(numerator) / nullToDoubleZero(denominator);
        // 获取格式化对象
        NumberFormat nt = NumberFormat.getPercentInstance();
        // 设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(2);

        return nt.format(percent);
    }

    /**
     * 求平均值
     *
     * @param numerator   分子
     * @param denominator 分母
     * @return
     */
    public static String getAvg(String numerator, String denominator) {
        if (StringUtils.isEmpty(numerator) || StringUtils.isEmpty(denominator)) {
            return "";
        }

        double avg = nullToDoubleZero(numerator) / nullToDoubleZero(denominator);
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(avg);
    }

    /**
     * 检验是是否存在字符串中，分号分隔
     *
     * @param config
     * @param checkData
     * @return
     */
    public static boolean checkConfig(String config, String checkData) {

        return checkData.indexOf(";") == -1 && (";" + config + ";").indexOf(";" + checkData + ";") >= 0;
    }

    public static String encode(String str, String enc) {

        String strEncode = "";

        try {
            if (str != null)
                strEncode = URLEncoder.encode(str, enc);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        return strEncode;
    }

    public static String encodeBase64String(String data) {
        try {
            return Base64.encodeBase64String(data.getBytes(CHAR_SET)).trim();
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decodeBase64String(String data) {
        try {
            return new String(Base64.decodeBase64(data), CHAR_SET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    public static String getUUID() {

        return UUID.randomUUID().toString();
    }

    /**
     * 获取指定长度的数据码
     *
     * @param length 长度
     * @return
     */
    public static String getRandRandom(int length) {
        return getRandom(RAND_STRING, length);
    }

    /**
     * 使用指定源生成验证码
     *
     * @param verifySize 验证码长度
     * @param sources    验证码字符源
     * @return
     */
    public static String generateVerifyCode(int verifySize, String sources) {
        if (sources == null || sources.length() == 0) {
            sources = VERIFY_CODES;
        }
        int codesLen = sources.length();
        Random rand = new Random(System.currentTimeMillis());
        StringBuilder verifyCode = new StringBuilder(verifySize);
        for (int i = 0; i < verifySize; i++) {
            verifyCode.append(sources.charAt(rand.nextInt(codesLen - 1)));
        }
        return verifyCode.toString();
    }


    /**
     * 使用系统默认字符源生成验证码
     *
     * @param verifySize 验证码长度
     * @return
     */
    public static String generateVerifyCode(int verifySize) {
        return generateVerifyCode(verifySize, VERIFY_CODES);
    }

    /**
     * 生成短信验证码
     *
     * @return
     */
    public static String generateSmsCode() {
        return getRandom(SMS_CODES, 6);
    }

    /**
     * 银行卡替换
     * @param bankNum 银行卡号
     * @return 替换过后的
     */
    public static String replaceBankNum(String bankNum) {
        return "***" + bankNum.substring(bankNum.length() - 4, bankNum.length());
    }

    public static void main(String[] args) {
//        System.out.println(CommonUtils.getRandRandom(22));
//        System.out.println(encodeBase64String("123456"));
//        System.out.println(decodeBase64String("MTIzNDU2"));
//        System.out.println(replaceBankNum("a465f37s54dfsd31"));
    }
}

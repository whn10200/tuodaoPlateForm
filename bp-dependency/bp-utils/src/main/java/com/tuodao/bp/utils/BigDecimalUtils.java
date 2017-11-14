package com.tuodao.bp.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Objects;

/**
 * @author 王艳兵
 * @created:2017年6月23日 上午11:11:38
 */
public class BigDecimalUtils {

    /**
     * 除法精度
     */
    private static final int DIV_SCALE = 10;
    /**
     * 乘法精度
     */
    private static final int MUL_SCALE = 2;
    /**
     * 加法精度
     */
    private static final int ADD_SCALE = 2;
    /**
     * 减法精度
     */
    private static final int SUB_SCALE = 2;

    /**
     * 四舍五入由于程序中计算均为分单位 因此默认为0
     */
    private static final int ROUND_SCALE = 0;

    /**
     * 除法 默认精确10位
     *
     * @param s1 除数
     * @param s2 被除数
     * @return
     */
    public static double div(double s1, double s2) {
        return div(s1, s2, DIV_SCALE);
    }

    /**
     * 除法
     *
     * @param s1    除数
     * @param s2    被除数
     * @param scale 精确位数
     * @return
     */
    public static double div(double s1, double s2, int scale) {
        BigDecimal ds1 = BigDecimal.valueOf(s1);
        BigDecimal ds2 = BigDecimal.valueOf(s2);
        return ds1.divide(ds2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 将元转为分
     *
     * @param money 金额 元
     * @return
     */
    public static double yuanToCent(double money) {
        return mul(money, 100);
    }

    public static BigDecimal yuanToCent(BigDecimal money) {
        if(Objects.isNull(money)) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(mul(money.doubleValue(), 100));
    }

    /**
     * 将分转为元
     *
     * @param money 金额 分
     * @return 元
     */
    public static double centToYuan(double money) {
        return div(money, 100);
    }

    /**
     * 分转元
     * @param decimal
     * @return
     */
    public static BigDecimal centToYuan(BigDecimal decimal){
        if(Objects.isNull(decimal)) {
            return BigDecimal.ZERO;
        }
        return BigDecimal.valueOf(centToYuan(decimal.doubleValue()));
    }

    /**
     * 元转分
     * @param money
     * @return
     */
    public static String centToYuanFormat(BigDecimal money){
        if(money == null){
            return "0.00";
        }
        return centToYuanFormat(money.doubleValue());
    }

    /**
     * 分转元
     * @param money
     * @return
     */
    public static String centToYuanFormat(double money) {
        double result = centToYuan(money);
        return formatMoney(result);
    }


    /**
     * 取反操作 ,正数变成负数,负数变成正数
     *
     * @param decimal
     * @return
     */
    public static BigDecimal negation(BigDecimal decimal) {
        return BigDecimal.valueOf(-decimal.doubleValue());
    }


    /**
     * 乘法 默认精确2位
     *
     * @param s1 乘数
     * @param sn 第2~N个乘数
     * @return
     */
    public static double mul(double s1, double... sn) {
        BigDecimal ms1 = BigDecimal.valueOf(s1);
        for (double s : sn) {
            ms1 = ms1.multiply(BigDecimal.valueOf(s)).setScale(MUL_SCALE, BigDecimal.ROUND_HALF_UP);
        }

        return ms1.doubleValue();
    }

    /**
     * 加法
     *
     * @param s1 第一个加数
     * @param s2 第二个加数
     * @return
     */
    public static double add(double s1, double s2) {
        return add(s1, s2, ADD_SCALE);
    }

    /**
     * 加法
     *
     * @param s1    第一个加数
     * @param s2    第二个加数
     * @param scale 精确位数
     * @return
     */
    public static double add(double s1, double s2, int scale) {
        BigDecimal as1 = BigDecimal.valueOf(s1);
        as1 = as1.add(BigDecimal.valueOf(s2));
        return as1.setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }


    /**
     * 减法 默认精确2位
     *
     * @param s1 减数
     * @param s2 被减数
     * @return
     */
    public static double sub(double s1, double s2) {
        return sub(s1, s2, SUB_SCALE);
    }

    /**
     * 减法 默认精确2位
     *
     * @param s1    减数
     * @param s2    被减数
     * @param scale 精度位数
     * @return
     */
    public static double sub(double s1, double s2, int scale) {
        BigDecimal ms1 = BigDecimal.valueOf(s1);
        return ms1.subtract(BigDecimal.valueOf(s2)).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 格式化金额显示 例如721,121.00
     *
     * @param money 入参
     * @return
     */
    public static String formatMoney(double money) {
        DecimalFormat format = new DecimalFormat("#,##0.00");
        if(money == 0){
            return "0.00";
        }
        return format.format(money);
    }

    /**
     * 将double转为string
     *
     * @param money
     * @return
     */
    public static String getMoneyStr(double money) {
        return Double.toString(money);
    }

    /**
     * 四舍五入
     * @param money
     * @param scale 保留小数位数
     * @return
     */
    public static double round(double money,int scale){
	    return BigDecimal.valueOf(money).setScale(scale).doubleValue();
    }

    /**
     * 四舍五入 默认不保留小数
     * @param money
     * @return
     */
    public static double round(double money) {
        return BigDecimal.valueOf(money).setScale(ROUND_SCALE, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * BigDecimal 乘以 double  结果去两位小数
     * @param v1
     * @param v2
     * @return
     */
    public static BigDecimal multiply(BigDecimal v1, Double v2) {
        if (null == v1 || null == v2) {
            return null;
        }
        return v1.multiply(BigDecimal.valueOf(v2)).setScale(MUL_SCALE, BigDecimal.ROUND_HALF_EVEN);

    }
}

package com.tuodao.bp.traningcenter.until;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * 由于Java的简单类型不能够精确的对浮点数进行运算，这个工具类提供精
 * <p/>
 * 确的浮点数运算，包括加减乘除和四舍五入。
 */

public class Arith {

	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	private Arith() {
	}

	/**
	 * 提供精确的加法运算。
	 *
	 * @param v1
	 *            被加数
	 * @param v2
	 *            加数
	 * @return 两个参数的和
	 */
	public static double add(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2).doubleValue();
	}
	
	public static BigDecimal addBd(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(2,BigDecimal.ROUND_HALF_UP);
		return b1.add(b2).setScale(BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供精确的减法运算。
	 *
	 * @param v1
	 *            被减数
	 * @param v2
	 *            减数
	 * @return 两个参数的差
	 */
	public static double sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2).doubleValue();
	}
	
	public static BigDecimal subBd(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(2,BigDecimal.ROUND_HALF_UP);
		return b1.subtract(b2);
	}

	/**
	 * 提供精确的乘法运算。
	 *
	 * @param v1
	 *            被乘数
	 * @param v2
	 *            乘数
	 * @return 两个参数的积
	 */
	public static double mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2).doubleValue();
	}
	
	public static BigDecimal mulBd(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(2,BigDecimal.ROUND_HALF_UP);
		return b1.multiply(b2).setScale(BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到
	 * <p/>
	 * 小数点以后10位，以后的数字四舍五入。
	 *
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2) {
		return div(v1, v2, DEF_DIV_SCALE);
	}
	
	public static BigDecimal divBd(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1)).setScale(2,BigDecimal.ROUND_HALF_UP);
		BigDecimal b2 = new BigDecimal(Double.toString(v2)).setScale(2,BigDecimal.ROUND_HALF_UP);
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
	 * <p/>
	 * 定精度，以后的数字四舍五入。
	 *
	 * @param v1
	 *            被除数
	 * @param v2
	 *            除数
	 * @param scale
	 *            表示表示需要精确到小数点以后几位。
	 * @return 两个参数的商
	 */
	public static double div(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 提供精确的小数位四舍五入处理。
	 *
	 * @param v
	 *            需要四舍五入的数字
	 * @param scale
	 *            小数点后保留几位
	 * @return 四舍五入后的结果
	 */
	public static double round(double v, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b = new BigDecimal(Double.toString(v));
		BigDecimal one = new BigDecimal("1");
		return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 格式化金额，四舍五入，保留两位小数
	 *
	 * @param money
	 * @return
	 */
	public static double roundMoney(Double money) {
		return money == null ? 0.00d
				: new BigDecimal(Double.toString(money)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static Double roundMoney2double(BigDecimal money) {
		if (money != null) {
			money.setScale(2, BigDecimal.ROUND_HALF_UP);
			return money.doubleValue();
		}
		
		return 0.00D;
	}

	/**
	 * 格式化金额，四舍五入，保留两位小数，返回字符串
	 *
	 * @param money
	 * @return
	 */
	public static String roundMoneyToString(Double money) {
		return money == null ? "0.00"
				: new BigDecimal(Double.toString(money)).setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString();
	}

	/**
	 * 格式化金额，保留两位小数，舍弃其他小数
	 *
	 * @param money
	 * @return
	 */
	public static double formatIncomeMoney(double money) {
		DecimalFormat format = new DecimalFormat();
		format.setMaximumFractionDigits(2);
		format.setGroupingSize(0);
		format.setRoundingMode(RoundingMode.FLOOR);
		return Double.parseDouble(format.format(money));
	}

	/**
	 * 格式化金额，保留两位小数，有第三位小数则入一位
	 *
	 * @param money
	 * @return
	 */
	public static double formatRepayMoney(double money) {
		DecimalFormat format = new DecimalFormat();
		format.setMaximumFractionDigits(3);
		format.setGroupingSize(0);
		format.setRoundingMode(RoundingMode.FLOOR);
		String s = new DecimalFormat("#0.000").format(Double.parseDouble(format.format(money)));
		int third = Integer.parseInt(s.substring(s.length() - 1));
		double remain = Double.parseDouble(s.substring(0, s.length() - 1));
		return third > 0 ? remain + 0.01 : remain;
	}

	public static String formatMoneyToString(double money) {
		DecimalFormat df1 = new DecimalFormat("#,##0.00");
		return df1.format(money);
	}

	public static String mathRound(double money) {
		return String.valueOf(Math.round(money));
	}

	public static void main(String[] args) {
		System.out.println(Arith.formatMoneyToString(0));
		System.out.println(Arith.formatMoneyToString(135.256));
		System.out.println(Arith.formatMoneyToString(1.578));
		System.out.println(Arith.formatMoneyToString(0.337));
		System.out.println(Arith.formatMoneyToString(1158.8));
		System.out.println(getMoneyString(126985d));
	}

	public static String formatMoney(double money) {
		return formatMoneyToString(roundMoney(money));
	}

	private static String[] digit = { "元", "", "", "", "万", "", "", "", "亿", "", "", "", "" };

	/**
	 * 取得数字对应的中文
	 *
	 * @param money
	 * @return
	 */
	public static String getMoneyString(double money) {
		// 将字符串转为为BigDecimal格式
		BigDecimal b = new BigDecimal(String.valueOf(money));
		// 设置精度为2，小数点后2位
		String strMoney = "" + b.setScale(2, BigDecimal.ROUND_UNNECESSARY);
		// 按小数点分为 整数 和 小数 两部分
		String[] amt = strMoney.split("\\.");
		// 调用函数获取 元 和 小数 部分的字符串
		strMoney = getYuan(amt[0]);
		// 返回最终得到的字符串
		return strMoney;
	}

	/**
	 * 得到元的部分
	 *
	 * @param s
	 * @return
	 */
	public static String getYuan(String s) {
		char[] c = s.toCharArray();
		StringBuffer chSb = new StringBuffer();
		int len = c.length;
		List list = new ArrayList();
		String d = "";
		for (int i = 0; i < c.length; i++) {
			// 如果有几个0挨在一起时, 只显示一个零即可
			if (i > 0 && c[i] == '0' && c[i] == c[i - 1]) {
				--len;
				continue;
			}
			// 得到数字对应的中文
			chSb.append(String.valueOf(c[i]));
			d = digit[--len];
			list.add(d);// 当数字中有万和十万时, 要去掉十万
			chSb.append(d);
		}
		return chSb.toString();
	}

	public static String getChineseYuan(String s) {
		Double dou = Double.valueOf(s);
		if (dou > 10000) {
			return (int) (dou / 10000) + "万";
		}
		return s;
	}

	/** 金额为分的格式 */
	public static final String CURRENCY_FEN_REGEX = "\\-?[0-9]+";

	/**
	 * 将分为单位的转换为元并返回金额格式的字符串 （除100）
	 *
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(Long amount) throws Exception {
		if (!amount.toString().matches(CURRENCY_FEN_REGEX)) {
			throw new Exception("金额格式有误");
		}

		int flag = 0;
		String amString = amount.toString();
		if (amString.charAt(0) == '-') {
			flag = 1;
			amString = amString.substring(1);
		}
		StringBuffer result = new StringBuffer();
		if (amString.length() == 1) {
			result.append("0.0").append(amString);
		} else if (amString.length() == 2) {
			result.append("0.").append(amString);
		} else {
			String intString = amString.substring(0, amString.length() - 2);
			for (int i = 1; i <= intString.length(); i++) {
				if ((i - 1) % 3 == 0 && i != 1) {
					result.append(",");
				}
				result.append(intString.substring(intString.length() - i, intString.length() - i + 1));
			}
			result.reverse().append(".").append(amString.substring(amString.length() - 2));
		}
		if (flag == 1) {
			return "-" + result.toString();
		} else {
			return result.toString();
		}
	}

	/**
	 * 将分为单位的转换为元 （除100）
	 *
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(String amount) throws Exception {
		if (!amount.matches(CURRENCY_FEN_REGEX)) {
			throw new Exception("金额格式有误");
		}
		return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}


	/**
	 * 将分为单位的转换为元 （除100）
	 *
	 * @param amount
	 * @return
	 * @throws Exception
	 */
	public static String changeF2Y(Integer amount) throws Exception {
		return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}

	public static String changeF2YNoException(Integer amount) {
		try {
			return BigDecimal.valueOf(Long.valueOf(amount)).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 将分为单位的转换为元  （除100）
	 *
	 * @param amount
	 * @return
	 */
	public static BigDecimal changeF2Y(Double amount) {
		return BigDecimal.valueOf(amount).divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	public static BigDecimal changeF2Y(BigDecimal amount) {
		return amount.divide(new BigDecimal(100)).setScale(2, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 * 将元为单位的转换为分 （乘100）
	 *
	 * @param amount
	 * @return
	 */
	public static String changeY2F(Long amount) {
		return String.valueOf(BigDecimal.valueOf(amount).multiply(new BigDecimal(100)).longValue());
	}


	/**
	 * 将元为单位的转换为分 替换小数点，支持以逗号区分的金额
	 *
	 * @param amount
	 * @return
	 */
	public static String changeY2F(String amount) {
		String currency = amount.replaceAll("\\$|\\￥|\\,", ""); // 处理包含, ￥
																// 或者$的金额
		int index = currency.indexOf(".");
		int length = currency.length();
		Long amLong = 0L;
		if (index == -1) {
			amLong = Long.valueOf(currency + "00");
		} else if (length - index >= 3) {
			amLong = Long.valueOf((currency.substring(0, index + 3)).replace(".", ""));
		} else if (length - index == 2) {
			amLong = Long.valueOf((currency.substring(0, index + 2)).replace(".", "") + 0);
		} else {
			amLong = Long.valueOf((currency.substring(0, index + 1)).replace(".", "") + "00");
		}
		return amLong.toString();
	}
	
	public static String to2Dec(Double value){
		return new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP).toString();
	}
	
	public static String to2Dec(String value){
		return to2Dec(new Double(value));
	}
}
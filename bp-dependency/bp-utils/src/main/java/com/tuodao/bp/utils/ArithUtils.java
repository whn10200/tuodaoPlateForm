package com.tuodao.bp.utils;

import java.math.BigDecimal;

/** BigDecimal Calculator</br> 
 * new对象默认四舍五入，保留10位小数。
 * set后，舍入模式，保留小数都可以修改。舍入模式请调用BigDecimal的常量
 * */
public class ArithUtils {


	private static  final  BigDecimal ONE_HUNDRED = new BigDecimal(100);


	/**
	 *  资金分转元 保留 位小数
	 *  decimal : 小数位数
	 */
	public static BigDecimal  toYuan(BigDecimal in,   Integer decimal)
	{
		if(in == null)
			in = new BigDecimal(0);
		 return  in.divide(ONE_HUNDRED,decimal, BigDecimal.ROUND_HALF_UP);
	}

	/**
	 *  资金分转元 保留 2位小数
	 */
	public static BigDecimal  toYuan(BigDecimal in)
	{
		if(in == null)
			in = new BigDecimal(0);
		return  in.divide(ONE_HUNDRED,2, BigDecimal.ROUND_HALF_UP);
	}


	/**
	 *  资金分转元 没有小数
	 */
	public static  BigDecimal  toCent(BigDecimal in)
	{
		return  in.multiply(ONE_HUNDRED).setScale(2,   BigDecimal.ROUND_HALF_UP);
	}

}

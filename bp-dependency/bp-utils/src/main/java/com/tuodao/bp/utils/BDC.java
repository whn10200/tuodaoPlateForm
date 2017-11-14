package com.tuodao.bp.utils;

import java.math.BigDecimal;

/** BigDecimal Calculator</br> 
 * new对象默认四舍五入，保留10位小数。
 * set后，舍入模式，保留小数都可以修改。舍入模式请调用BigDecimal的常量
 * */
public class BDC {

	private BigDecimal rbd;

	private int roundType;
	
	private int decimal;
	
	
	public BDC(BigDecimal bd) {
		this.rbd = bd;
		this.decimal=10;
		this.roundType = BigDecimal.ROUND_HALF_UP;
		rbd.setScale(decimal, roundType);
	}

	public BDC(Double d) {
		this(new BigDecimal(d));
	}
	
	public BDC(String s) {
		this(new BigDecimal(s));
	}

	public BigDecimal toBigDecimal() {
		return rbd.setScale(decimal, roundType);
	}
	
	/**四舍五入，保留0位小数。主要用于输出金额，单位“分”*/
	public BigDecimal toS0Bd() {
		return rbd.setScale(0, roundType);
	}

	public Double toDouble() {
		return toBigDecimal().doubleValue();
	}
	
	/**四舍五入，保留2位小数。主要用于输出金额，单位“元”*/
	public Double toS2Double() {
		return rbd.setScale(2, roundType).doubleValue();
	}

	public String toString() {
		return toDouble().toString();
	}

	public BDC add(BigDecimal bd) {
		rbd = rbd.add(bd).setScale(decimal, roundType);
		return this;
	}

	public BDC add(Double d) {
		return add(new BigDecimal(d));
	}

	public BDC add(String s) {
		return add(new BigDecimal(s));
	}

	public BDC add(BDC b) {
		return add(b.toBigDecimal());
	}

	public BDC sub(BigDecimal bd) {
		rbd = rbd.subtract(bd).setScale(decimal, roundType);
		return this;
	}

	public BDC sub(Double d) {
		return sub(new BigDecimal(d));
	}

	public BDC sub(String s) {
		return sub(new BigDecimal(s));
	}

	public BDC sub(BDC b) {
		return sub(b.toBigDecimal());
	}

	public BDC mul(BigDecimal bd) {
		rbd = rbd.multiply((bd).setScale(decimal, roundType));
		return this;
	}

	public BDC mul(Double d) {
		return mul(new BigDecimal(d));
	}

	public BDC mul(String s) {
		return mul(new BigDecimal(s));
	}

	public BDC mul(BDC b) {
		return mul(b.toBigDecimal());
	}

	public BDC mul100() {
		return mul(100.0);
	}
	
	public String fen2Yuan() {
		return div(100.0).toS2Double().toString();
	}

	public BDC div(BigDecimal bd) {
		rbd = rbd.divide(bd, decimal, roundType);
		return this;
	}

	public BDC div(Double d) {
		return div(new BigDecimal(d));
	}

	public BDC div(String s) {
		return div(new BigDecimal(s));
	}

	public BDC div(BDC b) {
		return div(b.toBigDecimal());
	}

	public BDC div100() {
		return div(100.0);
	}
	
	public String yuan2fen() {
		return div(100.0).toS0Bd().toString();
	}
	
	public BDC setDecimal(int decimal){
		this.decimal = decimal;
		return this;
	}
	
	public BDC setDecimal10(){
		return setDecimal(10);
	}
	
	public BDC setDecimal2(){
		return setDecimal(2);
	}
	
	public BDC setDecimal0(){
		return setDecimal(0);
	}
	
	public BDC setRoundType(int roundType){
		this.roundType = roundType;
		return this;
	}

}

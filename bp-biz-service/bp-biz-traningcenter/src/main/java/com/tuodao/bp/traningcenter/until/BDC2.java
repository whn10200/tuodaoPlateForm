package com.tuodao.bp.traningcenter.until;

import java.math.BigDecimal;

/** BigDecimal Calculator</br> 
 * new对象默认四舍五入，保留10位小数。
 * set后，舍入模式，保留小数都可以修改。舍入模式请调用BigDecimal的常量
 * */
public class BDC2 {

	private BigDecimal rbd;

	private int roundType;
	
	private int decimal;
	
	
	public BDC2(BigDecimal bd) {
		this.rbd = bd;
		this.decimal=10;
		this.roundType = BigDecimal.ROUND_HALF_UP;
		rbd.setScale(decimal, roundType);
	}

	public BDC2(Double d) {
		this(new BigDecimal(d));
	}
	
	public BDC2(String s) {
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

	@Override
	public String toString() {
		return toDouble().toString();
	}

	public BDC2 add(BigDecimal bd) {
		rbd = rbd.add(bd).setScale(decimal, roundType);
		return this;
	}

	public BDC2 add(Double d) {
		return add(new BigDecimal(d));
	}

	public BDC2 add(String s) {
		return add(new BigDecimal(s));
	}

	public BDC2 add(BDC2 b) {
		return add(b.toBigDecimal());
	}

	public BDC2 sub(BigDecimal bd) {
		rbd = rbd.subtract(bd).setScale(decimal, roundType);
		return this;
	}

	public BDC2 sub(Double d) {
		return sub(new BigDecimal(d));
	}

	public BDC2 sub(String s) {
		return sub(new BigDecimal(s));
	}

	public BDC2 sub(BDC2 b) {
		return sub(b.toBigDecimal());
	}

	public BDC2 mul(BigDecimal bd) {
		rbd = rbd.multiply((bd).setScale(decimal, roundType));
		return this;
	}

	public BDC2 mul(Double d) {
		return mul(new BigDecimal(d));
	}

	public BDC2 mul(String s) {
		return mul(new BigDecimal(s));
	}

	public BDC2 mul(BDC2 b) {
		return mul(b.toBigDecimal());
	}

	public BDC2 mul100() {
		return mul(100.0);
	}

	public BDC2 div(BigDecimal bd) {
		rbd = rbd.divide(bd, decimal, roundType);
		return this;
	}

	public BDC2 div(Double d) {
		return div(new BigDecimal(d));
	}

	public BDC2 div(String s) {
		return div(new BigDecimal(s));
	}

	public BDC2 div(BDC2 b) {
		return div(b.toBigDecimal());
	}

	public BDC2 div100() {
		return div(100.0);
	}
	
	public BDC2 setDecimal(int decimal){
		this.decimal = decimal;
		return this;
	}
	
	public BDC2 setDecimal10(){
		return setDecimal(10);
	}
	
	public BDC2 setDecimal2(){
		return setDecimal(2);
	}
	
	public BDC2 setDecimal0(){
		return setDecimal(0);
	}
	
	public BDC2 setRoundType(int roundType){
		this.roundType = roundType;
		return this;
	}

}

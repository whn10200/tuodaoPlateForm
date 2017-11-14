package com.tuodao.bp.traningcenter.until;

import java.math.BigDecimal;

/** BigDecimal Calculator */
public class BDC {

	private BigDecimal rbd;

	private int roundType;

	public BDC(BigDecimal bd) {
		this(bd, BigDecimal.ROUND_HALF_UP);
	}

	public BDC(BigDecimal bd, int roundType) {
		this.rbd = bd;
		this.roundType = roundType;
		rbd.setScale(2, roundType);
	}

	public BDC(Double d, int roundType) {
		this(new BigDecimal(d),roundType);
	}

	public BDC(Double d) {
		this(new BigDecimal(d));
	}

	public BDC(String s, int roundType) {
		this(new BigDecimal(s),roundType);
	}

	public BDC(String s) {
		this(new BigDecimal(s));
	}

	public BigDecimal toBigDecimal() {
		return rbd.setScale(2, roundType);
	}

	public Double toDouble() {
		return toBigDecimal().doubleValue();
	}

	@Override
	public String toString() {
		return toDouble().toString();
	}

	public BDC add(BigDecimal bd) {
		rbd = rbd.add(bd).setScale(2, roundType);
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
		rbd = rbd.subtract(bd).setScale(2, roundType);
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
		rbd = rbd.multiply((bd).setScale(2, roundType));
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

	public BDC div(BigDecimal bd) {
		rbd = rbd.divide(bd, 2, roundType);
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

}

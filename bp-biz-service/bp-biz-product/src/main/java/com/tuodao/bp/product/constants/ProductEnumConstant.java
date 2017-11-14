package com.tuodao.bp.product.constants;

/**
 * @Author wuchengjie
 * @Date 2017/9/15 0015 18:17
 * @Introduction
 */
public enum ProductEnumConstant {

	PRODUCT_STATUS_0(0, "初始"), PRODUCT_STATUS_1(1, "被打回"), PRODUCT_STATUS_2(2, "被撤回"), PRODUCT_STATUS_3(3,
			"待审核"), PRODUCT_STATUS_4(4, "待发布"), PRODUCT_STATUS_5(5, "募集中"), PRODUCT_STATUS_6(6,
					"满标待审"), PRODUCT_STATUS_7(7, "还款中"), PRODUCT_STATUS_8(8, "已还款"), PRODUCT_STATUS_9(9, "提前还款"),;

	private Integer type;
	private String name;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	ProductEnumConstant(Integer type, String name) {
		this.type = type;
		this.name = name;
	}

	// check type is ok or not
	public static boolean compare(String type) {
		boolean is_exists = false;
		if (type != null) {
			for (int i = 0; i < ProductEnumConstant.values().length; i++) {
				if (type.equals(ProductEnumConstant.values()[i].type)) {
					is_exists = true;
					break;
				}
			}
		}
		return is_exists;
	}

	public static String getTypeName(Integer type) {
		String typeName = "";
		if (type != null) {
			for (int i = 0; i < ProductEnumConstant.values().length; i++) {
				if (type == ProductEnumConstant.values()[i].type) {
					typeName = ProductEnumConstant.values()[i].name;
					break;
				}
			}
		}
		return typeName;
	}

	/**
	 * BORROW_NO(0, "否"), BORROW_YES(1,"是"),
	 * 
	 * @param type
	 * @return 是否 未知
	 */
	public static String getBooelanResultText(Integer type) {
		if (type != null) {
			if (type.intValue() == 0) {
				return "否";
			} else if (type.intValue() == 1) {
				return "是";
			}
		}
		return "未知";
	}

	/**
	 * AVERAGE_CAPITAL_PLUS_INTEREST(0,"等额本息"), MONTH_INTEREST(1,"按月付息"),
	 * 
	 * @param type
	 * @return 等额本息 按月付息 未知
	 */
	public static String getRefundWayText(Integer type) {
		if (type != null) {
			if (type.intValue() == 0) {
				return "等额本息";
			} else if (type.intValue() == 1) {
				return "按月付息";
			} else if (type.intValue() == 2) {
				return "按天付息";
			}
		}
		return "未知";
	}

	/**
	 * BORROW_TYPE_DAY(0,"天标"), BORROW_TYPE_MONTH(1,"月标"),
	 * 
	 * @param type
	 * @return 天标 月标 未知
	 */
	public static String getBorrowTypeText(Integer type) {
		if (type != null) {
			if (type.intValue() == 0) {
				return "天标";
			} else if (type.intValue() == 1) {
				return "月标";
			}
		}
		return "未知";
	}

	/**
	 * PERIOD_UNIT_DAY(0,"天"), PERIOD_UNIT_MONTH(1,"月"),
	 * PERIOD_UNIT_YEAR(2,"年"),
	 * 
	 * @param type
	 * @return 天 月 年 未知
	 */
	public static String getPeriodUnitText(Integer type) {
		if (type != null) {
			if (type.intValue() == 0) {
				return "天";
			} else if (type.intValue() == 1) {
				return "月";
			} else if (type.intValue() == 2) {
				return "年";
			}
		}
		return "未知";
	}

	/**
	 * 还款方式 0：等额本息 1：按月付息 2：按天付息
	 **/
	public enum RefundWay {
		PRINCIPAL("等额本息", 0), month("按月付息", 1), DAY("按天付息 ", 2);
		public final int code;
		public final String value;

		private RefundWay(String value, int code) {
			this.code = code;
			this.value = value;
		}

		public static String getValue(int code) {
			for (RefundWay refundWay : RefundWay.values()) {
				if (refundWay.code == code) {
					return refundWay.value;
				}
			}
			return null;
		}
	}

	/**
	 * 标的状态
	 **/
	public enum BorrowStatus {
		BorrowStatus1("新增标", 0), BorrowStatus2("续贷标", 1), BorrowStatus3("重贷标", 2);
		public final int code;
		public final String value;

		private BorrowStatus(String value, int code) {
			this.code = code;
			this.value = value;
		}

		public static String getValue(int code) {
			for (BorrowStatus borrowStatus : BorrowStatus.values()) {
				if (borrowStatus.code == code) {
					return borrowStatus.value;
				}
			}
			return null;
		}
	}

	/**
	 * 是否要代偿
	 **/
	public enum IsCompensatory {
		NO("否", 0), YES("是", 1);
		public final int code;
		public final String value;

		private IsCompensatory(String value, int code) {
			this.code = code;
			this.value = value;
		}

		public static String getValue(int code) {
			for (IsCompensatory isCompensatory : IsCompensatory.values()) {
				if (isCompensatory.code == code) {
					return isCompensatory.value;
				}
			}
			return null;
		}
	}
	
	/**
	 * 
	 * 借款存管状态
	 **/
	public enum BorrowBankStatus {
		BorrowBankStatus0("初始", 0), BorrowBankStatus1("成标", 1),BorrowBankStatus2("出账", 1);
		public final int code;
		public final String value;

		private BorrowBankStatus(String value, int code) {
			this.code = code;
			this.value = value;
		}

		public static String getValue(int code) {
			for (BorrowBankStatus borrowBankStatus : BorrowBankStatus.values()) {
				if (borrowBankStatus.code == code) {
					return borrowBankStatus.value;
				}
			}
			return null;
		}
	}
	
	
	/**
	 * 
	 * 借款存管状态
	 **/
	public enum CompensatoryStatus {
		CompensatoryStatus0("初始", 0), CompensatoryStatus2("部分成功", 1),CompensatoryStatus3("全部成功", 1),CompensatoryStatus4("失败", 1);
		public final int code;
		public final String value;

		private CompensatoryStatus(String value, int code) {
			this.code = code;
			this.value = value;
		}

		public static String getValue(int code) {
			for (CompensatoryStatus compensatoryStatus : CompensatoryStatus.values()) {
				if (compensatoryStatus.code == code) {
					return compensatoryStatus.value;
				}
			}
			return null;
		}
	}
	
	/**
	 * 还款状态
	 **/
	public enum RepaymentStatus {
		Status1("未还", 0), Status2("已还", 1), Status3("提前还款", 2);
		public final int code;
		public final String value;

		private RepaymentStatus(String value, int code) {
			this.code = code;
			this.value = value;
		}

		public static String getValue(int code) {
			for (RepaymentStatus repaymentStatus : RepaymentStatus.values()) {
				if (repaymentStatus.code == code) {
					return repaymentStatus.value;
				}
			}
			return null;
		}
	}
}

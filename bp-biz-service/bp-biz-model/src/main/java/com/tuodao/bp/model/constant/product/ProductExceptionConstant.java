package com.tuodao.bp.model.constant.product;

/**
 * 参数异常常量
 * @author wcj
 *
 * @created 2017年8月31日
 *
 * @since 1.0.0
 */
public class ProductExceptionConstant {

	/**	产品不能为空 */
	public static final int  PRODUCT_IS_NULL = 159001;
    /**
     * 产品必须为理财计划
     */
    public static final int  PRODUCT_MUST_FEATURED = 159002;
    /**
     * 产品状态不对
     */
    public static final int  PRODUCT_STATUS_INCORRECT = 159003;

    /** 用户不能为空 */
    public static final int  USERID_IS_NULL = 159051;
    /** 标的不能为空 */
    public static final int  BORROWID_IS_NULL = 159052;
    /** 期限为空 */
    public static final int  PERIOD_IS_NULL = 159053;


}

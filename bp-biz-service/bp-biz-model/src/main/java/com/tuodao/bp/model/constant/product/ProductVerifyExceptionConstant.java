package com.tuodao.bp.model.constant.product;

/**
 * 产品审核的参数异常
 * @author wcj
 *
 * @created 2017年8月31日
 *
 * @since 1.0.0
 */
public class ProductVerifyExceptionConstant {

	/**	审核参数为空 */
	public static final int  VERIFY_REMARK_NULL = 159701;
    /** 状态为空 */
    public static final int  VERIFY_STATUS_NULL = 159702;
    /** 审核结果  0：打回,1：通过', */
    public static final int  VERIFY_RESUALT_NULL = 159703;
    /** 标的id*/
    public static final int  VERIFY_BORROWID_NULL = 159704;

    /** 满标审核接口调用失败*/
    public static final int  REVERIFY_DEPOSI_CLIENT_ERROR = 159801;

}

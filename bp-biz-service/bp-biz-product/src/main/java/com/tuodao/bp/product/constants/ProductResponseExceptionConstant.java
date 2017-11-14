package com.tuodao.bp.product.constants;

/**
 * 参数异常常量
 * @author wcj
 *
 * @created 2017年8月31日
 *
 * @since 1.0.0
 */
public class ProductResponseExceptionConstant {


    /**	操作成功*/
    public static final int  OPERTION_SUCCESS = 150000;

    /**	产品不能为空 */
    public static final int  PRODUCT_IS_NULL = 150001;
    /**
     * 产品必须为理财计划
     */
    public static final int  PRODUCT_MUST_FEATURED = 150002;
    /**
     * 产品状态不对
     */
    public static final int  PRODUCT_STATUS_INCORRECT = 150003;

    /**无此产品*/
    public static final int  PRODUCT_NOT_MATCH = 150004;

    /**此产品已经超募*/
    public static final int  PRODUCT_OVER_RAISED = 150005;
    /**
     * 产品必须为散标
     */
    public static final int  PRODUCT_MUST_BORROW = 150006;

    /**产品的定时时间错误*/
    public static final int  AUTOTIME_NOT_CORRECT = 150007;


    /**产品还款方式*/
    public static final int  STYLE_NOT_CORRECT = 150008;


    /** 还款计划不能为空 */
    public static final int  REPAYMENT_IS_NULL = 150101;

    /** 时间转换错误 */
    public static final int  DATE_TRANSFER_ERROR = 150102;

    /**	理财计划配对错误 */
    public static final int  MATCHING_PRODUCT_ERROR = 150201;

    /** 同一个理财计划 同一种散标只能有一条记录 */
    public static final int  BORROW_DETIAL_ONLY_ONE = 150301;

    /**无法产品识别类型*/
    public static final int  BORROW_DETIAL_UNKONW_BORROW_TYPE = 150302;

    /**详细表中的钱 不能大于来源表中的钱*/
    public static final int  BORROW_DETIAL_INCORRECT_AMOUNT = 150303;

    /**无此债权*/
    public static final int  TRANSFER_NOT_MATCH = 150504;

    /**此债权已经超募*/
    public static final int  TRANSFER_OVER_RAISED = 150505;
    
    ///////////////////////////////////////////////////////////
    /**参数不能为空*/
    public static final int  PRODUCT_EXCEPTION_CODE_150506 = 150506;
    /**还款ID不能为空*/
    public static final int  PRODUCT_EXCEPTION_CODE_150507 = 150507;
    /**产品ID不能为空*/
    public static final int  PRODUCT_EXCEPTION_CODE_150508 = 150508;
    /**产品状态不能为空*/
    public static final int  PRODUCT_EXCEPTION_CODE_150509 = 150509;
    /**获取不到当前状态的产品信息*/
    public static final int  PRODUCT_EXCEPTION_CODE_150510 = 150510;
    /** 获取不到还款计划信息*/
    public static final int  PRODUCT_EXCEPTION_CODE_150511 = 150511;
    /** 该还款计划已还款*/
    public static final int  PRODUCT_EXCEPTION_CODE_150512 = 150512;
    /** 借款用户不存在*/
    public static final int  PRODUCT_EXCEPTION_CODE_150513 = 150513;
    /** 借款用户未开通存管账户*/
    public static final int  PRODUCT_EXCEPTION_CODE_150514 = 150514;
    /** 请先偿还前几期借款*/
    public static final int  PRODUCT_EXCEPTION_CODE_150515 = 150515;
    /** 还款金额不正确*/
    public static final int  PRODUCT_EXCEPTION_CODE_150516 = 150516;
    /** 标的账户余额查询失败*/
    public static final int  PRODUCT_EXCEPTION_CODE_150517 = 150517;
    /** 代偿人现金融资账户查询失败*/
    public static final int  PRODUCT_EXCEPTION_CODE_150518 = 150518;
    /** 代偿人现金融资账户余额不足*/
    public static final int  PRODUCT_EXCEPTION_CODE_150519 = 150519;
    
    
    
   
    

}

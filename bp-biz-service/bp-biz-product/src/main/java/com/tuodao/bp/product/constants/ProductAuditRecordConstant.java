package com.tuodao.bp.product.constants;

/**
 * @Author wuchengjie
 * @Date 2017/10/25 0025 10:34
 * @Introduction 审核记录的静态变量
 */
public class ProductAuditRecordConstant {
    /**
     * 0：设置标的
     */
    public static final Integer RECORD_STATUS_0= 0;
    /**
     * 1：发布标的
     */
    public static final Integer RECORD_STATUS_1= 1;
    /**
     * 2：审核标的
     */
    public static final Integer RECORD_STATUS_2= 2;
    /**
     * 3：手动满标审核标的
     */
    public static final Integer RECORD_STATUS_3= 3;
    /**
     * 4：撤标
     */
    public static final Integer RECORD_STATUS_4= 4;
    /**
     * 5：还款中、已还款，已还款（提前还款）标的转为续贷标的
     */
    public static final Integer RECORD_STATUS_5= 5;

}

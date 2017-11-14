package com.tuodao.bp.useraccount.constant;

/**
 * @description: 用户相关常量
 * @author: mif
 * @date: 2017/8/28
 * @time: 15:50
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class UserAccountBizConstant {
    /**
     * 业务代码(1：互金平台)
     */
    public final static int BUSSINESS_CODE_FINANCE = 1;


    /**
     * 用户状态（1：正常；2：冻结；3：注销）
     */
    public final static int USER_STATUS_NOMAL = 1;
    public final static int USER_STATUS_FREEZE = 2;
    public final static int USER_STATUS_CANCEL = 3;

    /**
     * 性别（1：男；2：女）
     */
    public final static int MAN = 1;
    public final static int WOMAN = 2;

    /**
     * 理财师等级有效期类型（1：6个月；2：永久）
     */
    public final static int VALIDITY_TYPE_SIX_MONTH = 1;
    public final static int VALIDITY_TYPE_FOREVER = 2;

    /**
     * 用户类型（1：投资用户：2：融资用户；）
     */
    public final static int USER_TYPE_INVEST = 1;
    public final static int USER_TYPE_INANCING = 2;

    /**
     * 投资用户类型(1：普通用户；2：内部用户)
     */
    public final static int INVEST_USER_TYPE_NOMAL = 1;
    public final static int INVEST_USER_TYPE_INNER = 2;

}

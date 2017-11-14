package com.tuodao.bp.model.constant.traningcenter;

/**
 * @description: 投标等相关常量
 * @author: 王艳兵
 * @date: 2017/9/14
 * @time: 20:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class TenderConstant {

    /**
     * 作为sessionId
     */
    public static final String ACCESS_ID = "accessId";

    /**
     * 投标冻结
     */
    public static final int TENDER_FROST = 0;

    /**
     * 投标成功
     */
    public static final int TENDER_SUCCESS = 1;

    /**
     * 还款中
     */
    public static final int TENDER_REPAYMENT = 2;

    /**
     * 投标失败
     */
    public static final int TENDER_FAIL = 3;


    /**
     * 标的撤回
     */
    public static final int TENDER_CANCEL = 4;

    /**
     * 还款完成
     */
    public static final int TENDER_COMPLETE = 5;


    /**
     * 自动投标状态:0关闭
     */
    public static final int AUTO_TENDER_CLOSE = 0;

    /**
     * 自动投标状态:1:开启
     */
    public static final int AUTO_TENDER_OPEN = 1;

    /**
     * 自动投标单次最大查询条数
     */
    public static final int AUTO_TENDER_MAX_QUERY = 100;


    /**
     * 自动投标类型 0:非投资 1:投资
     */
    public static final int AUTO_TENDER_TYPE = 1;


    /**
     * 投标撤回(平台撤回)
     */
    public static final int TENDER_REVOKE = 3;


    /**
     * 资金记录是否显示 0:不显示 1:显示
     */
    public static final int LOG_SHOW = 1;

    /**
     *  回款计划是否显示 0:不显示 1:显示
     */
    public static final int COLLECTION_SHOW = 1;

    /**
     * 回款状态 0:未回款 1:已回款 2:提前回款 7:已转让
     */
    public static final int COLLECTION_STATUS_0 = 0;

    /**
     * 回款状态 1:已回款 正常回款
     */
    public static final int COLLECTION_STATUS_1 = 1;
    /**
     * 回款状态 2:提前回款
     */
    public static final int COLLECTION_STATUS_2 = 2;


    /**
     * mq队列类型 0:投标成功触发是否为首投的业务
     */
    public static final int ACCOUNT_MQ_TYPE_0 = 0;

    /**
     * mq队列类型 1:回款成功触发 收益等奖励提成
     */
    public static final int ACCOUNT_MQ_TYPE_1 = 1;

    /**
     * 还款状态 0:未还款 1:已还款 2:已转让
     */
    public static final int REPAYMENT_STATUS = 0;

    /**
     * 投标列表查询状态
     */
    public static final String SELECT_TENDER_STATUS = "0|1|2|5";

    /**
     * 投标方式 1:自动投标
     */
    public static final int TENDER_MODE_AUTO = 1;
    /**
     * 投标方式 0:手动投标
     */
    public static final int TENDER_MODE_HAND = 0;

    /**
     * 投标类型 0:散标投标
     */
    public static final int TENDER_TYPE_0 = 0;

    /**
     * 投标类型 1:债权投标
     */
    public static final int TENDER_TYPE_1 = 1;

    /**
     * 投标结果查询状态 :投标成功
     */
    public static final int TENDER_RESULT_SUCCESS = 1;

    /**
     * 投标结果查询 :处理失败
     */
    public static final int TENDER_RESULT_FAIL = 2;

    /**
     * 投标查询查询:处理中
     */
    public static final int TENDER_RESULT_PROGRESS = 0;


    /**
     * 投标结果最大查询次数
     */
    public static final int TENDER_RESULT_MAX_QUERY = 3;


    /**
     * 是否可转让 1:可转让
     */
    public static final int IS_TRANSFERRED_YES =  1;

    /**
     * 是否可转让 0:不可转让
     */
    public static final int IS_TRANSFERRED_NO =  0;

    /**
     * 投标积分奖励月
     */
    public static final int SCORE_AWARD_MONTH = 12;

    /**
     * 投标成功跳转的h5地址
     */
    public static final String TENDER_SUCCESS_URL = "http://localhost:17000/test";

    /**
     * 投标处理中跳转的h5地址
     */
    public static final String TENDER_PROGRESS_URL = "http://localhost:17000/test";

    /**
     * 是否释放到资金池 0:不放(直接给投资人了) 1:放(不给投资人 继续进行再投操作)
     */
    public static final int IS_CAPITAL_1 = 1;
}

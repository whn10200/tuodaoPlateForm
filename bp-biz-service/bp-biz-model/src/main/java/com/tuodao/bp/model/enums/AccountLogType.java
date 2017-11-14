package com.tuodao.bp.model.enums;

import com.tuodao.bp.model.constant.PublicConstant;

/**
 * @author 王艳兵
 * 资金日志类型:如有未知类型,可自行添加
 */
public enum AccountLogType {

    /**
     * 充值
     */
    RECHARGE(0,"充值",0),
    /**
     * 投标冻结
     */
    TENDER_FROST(1,"投标冻结",2),
    /**
     * 投标成功
     */
    TENDER_SUCCESS(2,"投标成功",1),
    /**
     * 提现冻结
     */
    CASH_FROST(3,"提现冻结",2),
    /**
     * 提现成功
     */
    CASH_SUCCESS(4,"提现成功",1),

    /**
     * 提现手续费
     */
    CASH_SUCCESS_FEE(5,"提现手续费",1),
    /**
     * 提现失败
     */
    CASH_FAIL(6,"提现失败",3),
    /**
     * 标的撤回
     */
    TENDER_CANCEL(7,"标的撤回",0),
    /**
     * 标的回款
     */
    BORROW_COLLECTION(8,"标的回款",0),
    /**
     * 标的提前还款
     */
    BORROW_ADVANCE_COLLECTION(9,"标的提前还款",0),
    /**
     * 邀请好友投资奖励
     */
    INVITE_TENDER_AWARD(10,"邀请好友投资奖励",0),
    /**
     * 邀请好友回款奖励
     */
    INVITE_COLLECTION_AWARD(11,"邀请好友回款奖励",0),
    /**
     * 债权投标冻结
     */
    TRANSFER_TENDER_FROST(12,"债权投标冻结",2),
    /**
     * 债权流标
     */
    TRANSFER_FAIL(13,"债权流标",3),
    /**
     * 债券回收本金
     */
    TRANSFER_RECYCLE_PRINCIPAL(14,"债券回收本金",0),
    /**
     * 债券回收利息
     */
    TRANSFER_RECYCLE_INTEREST(15,"债券回收利息",0),
    /**
     * 债券扣除手续费
     */
    TRANSFER_FEE(16,"债券扣除手续费",1),
    /**
     * 债券奖励手续费
     */
    TRANSFER_AWARD_FEE(17,"债券奖励手续费",0),
    /**
     * 债券投标成功
     */
    TRANSFER_TENDER_SUCCESS(18,"债券投标成功",1),
    /**
     * 精选计划投标冻结
     */
    PLAN_TENDER_FROST(19,"精选计划投标冻结",2),
    /**
     * 精选计划投标解冻
     */
    PLAN_TENDER_UNFREEZE(20,"精选计划投标解冻",3),
    /**
     * 精选计划奖励利息
     */
    PLAN_AWARD_INTEREST(21,"精选计划奖励利息",3),
    /**
     * 精选计划扣除利息
     */
    PLAN_DEDUCT_INTEREST(22,"精选计划扣除利息",3),
    /**
     * 理财计划平账
     */
    PLAN_DEDUCT_BALANCE(23,"理财计划平账",3),
    /**
     * 理财计划复审
     */
    PLAN_DEDUCT_REVERIFY(24,"理财计划复审",0),
    /**
     * 理财计划下转让标复审
     */
    PLAN_DEDUCT_DEVREVERIFY(28,"理财计划下转让标复审",0),
    /**
     * 理财计划下普通标复审
     */
    PLAN_DEDUCT_ORIREVERIFY(29,"理财计划下转让标复审",0),
    /**
     * 投标失败解冻
     */
    TENDER_FAIL(25,"投标失败解冻",3),
    /**
     * 债权投标冻结失败
     */
    TRANSFER_FREEZE_FAIL(26,"债权投标冻结失败",0),
    /**
     * 债权投标冻结失败
     */
    TRANSFER_UNFREEZE(27,"债权投标冻结失败",3);


    /**
     * 资金日志code
     */
    private int code;

    /**
     * 资金日志类型 解释
     */
    private String msg;

    /**
     * 资金类型:0收入,1支出,2冻结,3解冻
     */
    private int type;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public int getType() {
        return type;
    }

    AccountLogType(int code, String msg, int type){
        this.code = code;
        this.msg = msg;
        this.type = type;
    }

    public static String getMsg(int code) {
        for (AccountLogType accountLogType : AccountLogType.values()) {
            if (accountLogType.getCode() == code) {
                return accountLogType.getMsg();
            }
        }
        return null;
    }

    public static int getType(int code) {
        for (AccountLogType accountLogType : AccountLogType.values()) {
            if (accountLogType.getCode() == code) {
                return accountLogType.getType();
            }
        }
        return 0;
    }

    public static String getNewMsg(int code)
    {
        //充值
        if(code==0)
        {
            return PublicConstant.MQ_ACCOUNT_DEAL_TYPE_RECHARGE;
        }
        //投资
        else if (code==1 || code==2 || code==12)
        {
            return PublicConstant.MQ_ACCOUNT_DEAL_TYPE_NORMAL_INVEST;
        }
        //提现
        else if (code==4 || code==6 || code==3)
        {
            return PublicConstant.MQ_ACCOUNT_DEAL_TYPE_WITHDRAW;
        }
        //回款
        else if(code==8 || code==9)
        {
            return PublicConstant.MQ_ACCOUNT_DEAL_TYPE_BACK_TO_ARTICLE;
        }
        //撤标
        else if (code==7||code==13 )
        {
            return PublicConstant.MQ_ACCOUNT_DEAL_TYPE_REVOKE;
        }
        //理财计划平账
        else if (code==23)
        {
            return PublicConstant.MQ_ACCOUNT_DEAL_TYPE_BALANCE;
        }
        //复审
        else if (code==14 || code==15 || code==16 || code==17 || code==18)
        {
            return PublicConstant.MQ_ACCOUNT_DEAL_TYPE_BALANCE;
        }
        else
        {
            return "";
        }
    }
}

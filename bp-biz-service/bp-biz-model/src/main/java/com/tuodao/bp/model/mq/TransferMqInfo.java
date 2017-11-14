package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.depository.DepositoryPara;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 标的转让
 * @author qingli.chen
 * @date 2017/10/23
 * @description
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class TransferMqInfo extends DepositoryPara {

    /**
     * 转让人平台客户号
     */
    private String platcustUserId;
    /**
     * 转让份额
     */
    private BigDecimal transShare;
    /**
     * 产品id
     */
    private Integer proId;
    /**
     * 转让金额
     */
    private BigDecimal transAmt;
    /**
     * 自费价格
     */
    private BigDecimal dealAmount;
    /**
     * 抵用券金额
     */
    private BigDecimal couponAmt;
    /**
     * 成交账号（受让人平台客户编号）
     */
    private String dealPlatcustUserId;
    /**
     * 发布时间
     */
    private String publishDate;
    /**
     * 成交时间
     */
    private String transDate;
    /**
     * 转让收益
     */
    private BigDecimal transferIncome = BigDecimal.ZERO;
    /**
     * 收益出资方账户
     * 平台：01  ；个人：对应platcust
     */
    private String incomeAcct;
    /**
     * 科目优先级
     * 0-可提优先1可投优先 新增
     */
    private String subjectPriority;
    /**
     * 涉及的标的编号，不同标的之间用逗号分隔(eg:P0001,P0002)
     */
    private Integer relatedProdIds;

    /**
     * 出让人手续费
     */
    private BigDecimal payoutAmt = BigDecimal.ZERO;

    private Integer transferId;

}

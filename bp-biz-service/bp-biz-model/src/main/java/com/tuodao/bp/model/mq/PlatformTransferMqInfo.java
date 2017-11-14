package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.depository.DepositoryPara;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 平台转个人
 * 
 * @author qingli.chen
 * @date 2017/10/23
 * @description
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class PlatformTransferMqInfo extends DepositoryPara {

	/**
	 * 平台账户(01 为平台账户，从平台自有资金转账)
	 */
	private final String platAccount = "01";
	/**
	 * 金额
	 */
	private BigDecimal amount;
	/**
	 * 原因类型（平台需提前报备，存管配置后返回类型）
	 */
	private final Integer causeType = 7;


	private String remark;

	private String userId;
	/**
	 * 标的id
	 */
	private Integer borrowId;

}

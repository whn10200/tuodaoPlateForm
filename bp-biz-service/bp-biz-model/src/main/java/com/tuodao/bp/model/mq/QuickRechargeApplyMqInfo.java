package com.tuodao.bp.model.mq;

import com.tuodao.bp.model.depository.DepositoryPara;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * 快捷充值(发送短信） 请求 存管参数
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
public class QuickRechargeApplyMqInfo extends DepositoryPara {


	private static final long serialVersionUID = 5338880653378759383L;
	/**
	 * 充值类型（1-用户充值）
	 */
	private String type;

	/**
	 * 姓名
	 * */
	private String name;
	/**
	 * 卡号
	 */
	private String cardNo;
	/**
	 * 证件类型
	 *
	 */
	private String idType;
	/**
	 * 证件号
	 */
	private String idCode;
	/**
	 * 银行预留手机号
	 */
	private String mobile;
	/**
	 * 充值金额
	 */
	private BigDecimal amt;
	/**
	 * 支付通道
	 */
	private String payCode;
	/**
	 * 北京银行或其他银行标识，1：北京银行，2:其他银行（暂不使用）
	 */
	private String branchFlag;
	/**
	 * 异步通知地址
	 */
	private String notifyUrl;

	/**
	 * 订单状态0:已接受, 1:处理中,2:处理成功,3:处理失败
	 */
	private Integer orderStatus;
	/**
	 * 系统处理日期(yyyyMMddHHmmss))
	 */
	private String processDate;
	/**
	 * 平台流水号
	 */
	private String queryId;

	private String platCust;
}

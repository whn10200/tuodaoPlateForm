package com.tuodao.bp.activemq;

import com.tuodao.bp.activemq.constant.ActiveConstant;
import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.activemq.constant.MqContstant;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jms.activemq.ActiveMQProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.jms.Queue;

import static com.tuodao.bp.activemq.constant.MqContstant.*;

/**
 * amq配置
 *
 * @author hechuan
 * @version 1.0.0
 * @created 2017年8月8日
 */
@Configuration
@EnableJms
@EnableConfigurationProperties(ActiveMQProperties.class)
public class ActiveMQConfig {

	@Autowired
	private ActiveMQProperties amqProperties;

	@Bean
	public SimpleMessageConverter simpleMessageConverter() {
		return new SimpleMessageConverter();
	}

	@Bean
	public ActiveMQConnectionFactory activeMQConnectionFactory() {
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();
		factory.setBrokerURL(amqProperties.getBrokerUrl());
		factory.setUserName(amqProperties.getUser());
		factory.setPassword(amqProperties.getPassword());
		factory.setTrustAllPackages(amqProperties.getPackages().getTrustAll());
		factory.setTrustedPackages(amqProperties.getPackages().getTrusted());
		factory.setUseAsyncSend(true);
		factory.setAlwaysSessionAsync(true);
		factory.setDispatchAsync(true);
		factory.setUseDedicatedTaskRunner(true);
		return factory;
	}

	// topic模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerTopic(
			ActiveMQConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		bean.setPubSubDomain(true);
		initPolicy(activeMQConnectionFactory);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}

	// queue模式的ListenerContainer
	@Bean
	public JmsListenerContainerFactory<?> jmsListenerContainerQueue(
			ActiveMQConnectionFactory activeMQConnectionFactory) {
		DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
		initPolicy(activeMQConnectionFactory);
		bean.setConnectionFactory(activeMQConnectionFactory);
		return bean;
	}

	@Bean
	@Primary
	public Queue queue() {
		return new ActiveMQQueue("useraccount.to.operation-master-slave-cluster");
	}

	/**
	 * 初始重试策略
	 *
	 * @param activeMQConnectionFactory
	 */
	private void initPolicy(ActiveMQConnectionFactory activeMQConnectionFactory) {

		RedeliveryPolicy policy = activeMQConnectionFactory.getRedeliveryPolicy();
		// 初次执行，1000延迟
		policy.setInitialRedeliveryDelay(1000L);
		// 每次重试延迟为1秒执行
		policy.setRedeliveryDelay(1000L);
		// 最大重发次数
		policy.setMaximumRedeliveries(ActiveConstant.MAX_REDILIVERD_COUNTER);
		// 每次重试间隔时间递增倍数,重试5次，每16s次的时间间隔依次为1s 4s 16s 64s 256s
		policy.setBackOffMultiplier(2);
		// 默认false，true表示启用指数倍数递增的方式增加延迟时间
		policy.setUseExponentialBackOff(true);

		activeMQConnectionFactory.setRedeliveryPolicy(policy);
	}

	/**
	 * 异步线程池 初始化
	 *
	 * @return
	 */
	@Bean
	public ThreadPoolTaskExecutor cosumerTaskExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(6);
		executor.setMaxPoolSize(12);
		return executor;
	}

	/**
	 * 用户账户中心-交易中心资金队列
	 *
	 * @return
	 */
	@Bean(name = "accountDealFinanceQueue")
	public Queue accountDealFinanceQueue() {
		return new ActiveMQQueue(ACCOUNT_DEAL_FINANCE_QUEUE);
	}

	/**
	 * 运营中心消费：积分任务队列
	 *
	 * @return
	 */
	@Bean(name = "opScoreTaskQueue")
	public Queue opScoreTaskQueue() {
		return new ActiveMQQueue(OP_SCORE_TASK_QUEUE);
	}

	/**
	 * 运营中心消费：邀请记录队列
	 *
	 * @return
	 */
	@Bean(name = "opPaybackInviteAwardQueue")
	public Queue opPaybackInviteAwardQueue() {
		return new ActiveMQQueue(OP_PAYBACK_INVITE_AWARD_QUEUE);
	}

	/**
	 * 运营中心消费：首投奖励
	 *
	 * @return
	 */
	@Bean(name = "opFirstTimeInvestAwardQueue")
	public Queue opFirstTimeInvestAwardQueue() {
		return new ActiveMQQueue(OP_FIRST_TIME_INVEST_AWARD_QUEUE);
	}

	/**
	 * 运营中心消费：用户VIP升级队列
	 *
	 * @return
	 */
	@Bean(name = "opVipUpMqQueue")
	public Queue opVipUpMqQueue() {
		return new ActiveMQQueue(OP_VIP_UP_MQ_QUEUE);
	}

	/*
	 * 运营中心消费：用户VIP升级队列
	 *
	 * @return
	 */
	@Bean(name = "opDiscountChangeMqQueue")
	public Queue opDiscountChangeMqQueue() {
		return new ActiveMQQueue(OP_DISCOUNT_CHANGE_MQ_QUEUE);
	}

	/**
	 * 运营中心 抵用券更新成功或失败异步通知队列
	 *
	 * @return
	 */
	@Bean(name = "opDiscountAsyncMqQueue")
	public Queue opDiscountAsyncMqQueue() {
		return new ActiveMQQueue(OP_DISCOUNT_ASYNC_MQ_QUEUE);
	}

	/**
	 * 账户中心:开通存管队列
	 *
	 * @return
	 */
	@Bean(name = "accountDepositQueue")
	public Queue accountDepositQueue() {
		return new ActiveMQQueue(ACCOUNT_DEPOSIT_QUEUE);
	}

	/**
	 * 账户中心-累计收益明细
	 *
	 * @return
	 */
	@Bean(name = "accountIncomeDetailQueue")
	public Queue accountIncomeDetailQueue() {
		return new ActiveMQQueue(ACCOUNT_INCOME_DETAIL_QUEUE);
	}

    /**
     * 账户中心-首投、返现队列
     *
     * @return
     */
    @Bean(name = "accountFirstInvestOrCashQueue")
    public Queue accountFirstInvestOrCashQueue() {
        return new ActiveMQQueue(MqContstant.ACCOUNT_FIRST_INVEST_OR_CASH_QUEUE);
    }

    /**
	 * 运营中心消费：优惠券发放队列
	 *
	 * @return
	 */
	@Bean(name = "opCouponGrantQueue")
	public Queue opCouponGrantQueue() {
		return new ActiveMQQueue(OP_COUPON_GRANT_QUEUE);
	}

	/**
	 * 定时钟发送队列
	 *
	 * @return
	 */
	@Bean(name = "taskTimerSendQueue")
	public Queue taskTimerSendQueue() {
		return new ActiveMQQueue(TASK_TIMER_SEND_QUEUE);
	}

	/**
	 * 定时钟接收队列
	 *
	 * @return
	 */
	@Bean(name = "taskTimerReceiveQueue")
	public Queue taskTimerReceiveQueue() {
		return new ActiveMQQueue(TASK_TIMER_RECEIVE_QUEUE);
	}

	/**
	 * 产品中心-交易中心投资队列
	 *
	 * @return
	 */
	@Bean(name = "productTenderQueue")
	public Queue productTenderQueue() {
		return new ActiveMQQueue(PRODUCT_TENDER_QUEUE);
	}

	/**
	 * 产品中心 更新产品队列
	 */
	@Bean(name = "productUpdateQueue")
	public Queue productUpdateQueue() {
		return new ActiveMQQueue(PRODUCT_UPDATE_QUEUE);
	}

	/**
	 * 交易中心-资金记录队列
	 *
	 * @return
	 */
	@Bean(name = "traningccountLogQueue")
	public Queue traningccountLogQueue() {
		return new ActiveMQQueue(TRANING_ACCOUNT_LOG_QUEUE);
	}

	/**
	 * 交易中心-理财计划加入队列
	 *
	 * @return
	 */
	@Bean(name = "traningJoinChoiseQueue")
	public Queue traningJoinChoiseQueue() {
		return new ActiveMQQueue(TRANING_JOIN_CHOISE_QUEUE);
	}

	/**
	 * 交易中心-理财计划下普通标的投标队列
	 *
	 * @return
	 */
	@Bean(name = "traningDevelopTenderQueue")
	public Queue traninDevelopTenderQueue() {
		return new ActiveMQQueue(TRANING_DEVELOP_TENDER_QUEUE);
	}

	/**
	 * 交易中心-理财计划下转让标的投标队列
	 *
	 * @return
	 */
	@Bean(name = "traningOrginalTenderQueue")
	public Queue traningOrginalTenderQueue() {
		return new ActiveMQQueue(TRANING_ORGINAL_TENDER_QUEUE);
	}

	/**
	 * 交易中心-理财计划复审队列
	 *
	 * @return
	 */
	@Bean(name = "traningReverifyPlanQueue")
	public Queue traningReverifyPlanQueue() {
		return new ActiveMQQueue(TRANING_REVERIFY_PLAN_QUEUE);
	}

	/**
	 * 交易中心-理财计划下普通标的复审队列
	 *
	 * @return
	 */
	@Bean(name = "traningReverifyOrginalQueue")
	public Queue traningReverifyOrginalQueue() {
		return new ActiveMQQueue(TRANING_REVERIFY_ORGINAL_QUEUE);
	}

	/**
	 * 交易中心-理财计划下到期的操作队列
	 *
	 * @return
	 */
	@Bean(name = "traningExpiredQueue")
	public Queue traningExpiredQueue() {
		return new ActiveMQQueue(TRANING_EXPIRED_QUEUE);
	}


	/**
	 * 交易中心-理财计划下转让标复审队列
	 *
	 * @return
	 */
	@Bean(name = "traningReverifyDevelopQueue")
	public Queue traningReverifyDevelopQueue() {
		return new ActiveMQQueue(TRANING_REVERIFY_DEVELOP_QUEUE);
	}

	/**
	 * 交易中心-网银充值队列
	 *
	 * @return
	 */
	@Bean(name = "rechargeGateQueue")
	public Queue rechargeGateQueue() {
		return new ActiveMQQueue(TRANING_RECHARGE_GATE_QUEUE);
	}

	/**
	 * 交易中心-快捷充值发送验证码队列
	 *
	 * @return
	 */
	@Bean(name = "rechargeFastSmsCodeQueue")
	public Queue rechargeFastSmsCodeQueue() {
		return new ActiveMQQueue(TRANING_RECHARGE_SMS_QUEUE);
	}

	/**
	 * 交易中心-快捷充值队列
	 *
	 * @return
	 */
	@Bean(name = "rechargeFastQueue")
	public Queue rechargeFastQueue() {
		return new ActiveMQQueue(TRANING_RECHARGE_FAST_QUEUE);
	}

	/**
	 * 更新提现记录队列
	 *
	 * @return
	 */
	@Bean(name = "opWithDrawTimesChangeQueue")
	public Queue opWithDrawTimesChangeQueue() {
		return new ActiveMQQueue(OP_WITH_DRAW_TIMES_CHANGE_MQ_QUEUE);
	}

	/**
	 * 创建资金账户
	 *
	 * @return
	 */
	@Bean(name = "createAccountQueue")
	public Queue createAccountQueue() {
		return new ActiveMQQueue(TRANING_CREATE_ACCOUNT_QUEUE);
	}

	/*
	 * 由com.tuodao.bp.depository.constant.BBC生成， 如
	 * DepositoryMqConstant.DE_IN_ORDER_STATUS 请搜索in和out后面的同名字段
	 */

	@Bean(name = "deInOrderCheck")
	public Queue inOrderCheck() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_ORDER_STATUS);
	}

	@Bean(name = "deOutOrderCheck")
	public Queue outOrderCheck() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_ORDER_STATUS);
	}

	@Bean(name = "deInUserRegist4Ele")
	public Queue inUserRegist4Ele() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_USER_REGIST_4_ELE);
	}

	@Bean(name = "deOutUserRegist4Ele")
	public Queue outUserRegist4Ele() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_USER_REGIST_4_ELE);
	}

	@Bean(name = "deInUserRegistRealName")
	public Queue inUserRegistRealName() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_USER_REGIST_REAL_NAME);
	}

	@Bean(name = "deOutUserRegistRealName")
	public Queue outUserRegistRealName() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_USER_REGIST_REAL_NAME);
	}

	@Bean(name = "deInUserBoundCard")
	public Queue inUserBoundCard() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_USER_BOUND_CARD);
	}

	@Bean(name = "deOutUserBoundCard")
	public Queue outUserBoundCard() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_USER_BOUND_CARD);
	}

	@Bean(name = "deInUserChangeCard")
	public Queue inUserChangeCard() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_USER_CHANGE_CARD);
	}

	@Bean(name = "deOutUserChangeCard")
	public Queue outUserChangeCard() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_USER_CHANGE_CARD);
	}

	@Bean(name = "deInUserMessageBoundCardApply")
	public Queue inUserMessageBoundCardApply() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_USER_MESSAGE_BOUND_CARD_APPLY);
	}

	@Bean(name = "deOutUserMessageBoundCardApply")
	public Queue outUserMessageBoundCardApply() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_USER_MESSAGE_BOUND_CARD_APPLY);
	}

	@Bean(name = "deInUserMessageBoundCardValidate")
	public Queue inUserMessageBoundCardValidate() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_USER_MESSAGE_BOUND_CARD_VALIDATE);
	}

	@Bean(name = "deOutUserMessageBoundCardValidate")
	public Queue outUserMessageBoundCardValidate() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_USER_MESSAGE_BOUND_CARD_VALIDATE);
	}

	@Bean(name = "deInUserUpdateInfo")
	public Queue inUserUpdateInfo() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_USER_UPDATE_INFO);
	}

	@Bean(name = "deOutUserUpdateInfo")
	public Queue outUserUpdateInfo() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_USER_UPDATE_INFO);
	}

	@Bean(name = "deInBorrowerBatchPayBak")
	public Queue inBorrowerBatchPayBak() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BORROWER_BATCH_PAY_BACK);
	}

	@Bean(name = "deOutBorrowerBatchPayBak")
	public Queue outBorrowerBatchPayBak() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BORROWER_BATCH_PAY_BACK);
	}

	@Bean(name = "deInBorrowerBiddingCompensation")
	public Queue inBorrowerBiddingCompensation() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BORROWER_BIDDING_COMPENSATION);
	}

	@Bean(name = "deOutBorrowerBiddingCompensation")
	public Queue outBorrowerBiddingCompensation() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BORROWER_BIDDING_COMPENSATION);
	}

	@Bean(name = "deInBorrowerBorrowerCompensation")
	public Queue inBorrowerBorrowerCompensation() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BORROWER_BORROWER_COMPENSATION);
	}

	@Bean(name = "deOutBorrowerBorrowerCompensation")
	public Queue outBorrowerBorrowerCompensation() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BORROWER_BORROWER_COMPENSATION);
	}

	@Bean(name = "deInBiddingPublish")
	public Queue inBiddingPublish() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_PUBLISH);
	}

	@Bean(name = "deOutBiddingPublish")
	public Queue outBiddingPublish() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_PUBLISH);
	}

	@Bean(name = "deInBiddingComplate")
	public Queue inBiddingComplate() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_COMPLATE);
	}

	@Bean(name = "deOutBiddingComplate")
	public Queue outBiddingComplate() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_COMPLATE);
	}
	
	@Bean(name = "deInBiddingCancel")
	public Queue inBiddingCancel() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_CANCEL);
	}

	@Bean(name = "deOutBiddingCancel")
	public Queue outBiddingCancel() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_CANCEL);
	}

	@Bean(name = "deInBiddingBid")
	public Queue inBiddingBid() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_BID);
	}

	@Bean(name = "deOutBiddingBid")
	public Queue outBiddingBid() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_BID);
	}

	@Bean(name = "deInBiddingChargeOff")
	public Queue inBiddingChargeOff() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_CHARGE_OFF);
	}

	@Bean(name = "deInBiddingTransferDebt")
	public Queue inBiddingTransferDebt() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_TRANSFER_DEBT);
	}

	@Bean(name = "deOutBiddingTransferDebt")
	public Queue outBiddingTransferDebt() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_TRANSFER_DEBT);
	}

	@Bean(name = "deInBiddingBatchTransferDebt")
	public Queue inBiddingBatchTransferDebt() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_BATCH_TRANSFER_DEBT);
	}

	@Bean(name = "deOutBiddingBatchTransferDebt")
	public Queue outBiddingBatchTransferDebt() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_BATCH_TRANSFER_DEBT);
	}

	@Bean(name = "deInBiddingRepay")
	public Queue inBiddingRepay() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_REPAY);
	}

	@Bean(name = "deOutBiddingRepay")
	public Queue outBiddingRepay() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_REPAY);
	}

	@Bean(name = "deInBiddingRepayPlaneUpdate")
	public Queue inBiddingRepayPlaneUpdate() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_REPAY_PLANE_UPDATE);
	}

	@Bean(name = "deOutBiddingRepayPlaneUpdate")
	public Queue outBiddingRepayPlaneUpdate() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_REPAY_PLANE_UPDATE);
	}

	@Bean(name = "deInBiddingChargeOffChange")
	public Queue inBiddingChargeOffChange() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BIDDING_CHARGE_OFF_CHANGE);
	}

	@Bean(name = "deOutBiddingChargeOffChange")
	public Queue outBiddingChargeOffChange() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_CHARGE_OFF_CHANGE);
	}

	@Bean(name = "deOutBiddingChargeOff")
	public Queue outBiddingChargeOff() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BIDDING_CHARGE_OFF);
	}

	@Bean(name = "deInRechargeGateway")
	public Queue inRechargeGateway() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_RECHARGE_GATEWAY);
	}

	@Bean(name = "deOutRechargeGateway")
	public Queue outRechargeGateway() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_RECHARGE_GATEWAY);
	}

	/*@Bean(name = "deInRecharge")
	public Queue inRecharge() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_RECHARGE);
	}*/

	/*@Bean(name = "deOutRecharge")
	public Queue outRecharge() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_RECHARGE);
	}*/

	/*@Bean(name = "deInRechargeQuick")
	public Queue inRechargeQuick() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_RECHARGE_QUICK);
	}*/

	/*@Bean(name = "deOutRechargeQuick")
	public Queue outRechargeQuick() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_RECHARGE_QUICK);
	}*/

	@Bean(name = "deInRechargeQuickApply")
	public Queue inRechargeQuickApply() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_RECHARGE_QUICK_APPLY);
	}

	@Bean(name = "deOutRechargeQuickApply")
	public Queue outRechargeQuickApply() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_RECHARGE_QUICK_APPLY);
	}

	@Bean(name = "deInRechargeQuickConfirm")
	public Queue inRechargeQuickConfirm() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_RECHARGE_QUICK_CONFIRM);
	}

	@Bean(name = "deOutRechargeQuickConfirm")
	public Queue outRechargeQuickConfirm() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_RECHARGE_QUICK_CONFIRM);
	}

	@Bean(name = "deInBorrowCutRepay")
	public Queue inBorrowCutRepay() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_BORROW_CUT_REPAY);
	}

	@Bean(name = "deOutBorrowCutRepay")
	public Queue outBorrowCutRepay() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_BORROW_CUT_REPAY);
	}

	/*@Bean(name = "deInWithdrawApply")
	public Queue inWithdrawApply() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_WITHDRAW_APPLY);
	}*/

	/*@Bean(name = "deOutWithdrawApply")
	public Queue outWithdrawApply() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_WITHDRAW_APPLY);
	}*/

	@Bean(name = "deInWithdrawApplyInvestor")
	public Queue inWithdrawApplyInvestor() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_WITHDRAW_APPLY_INVESTOR);
	}

	@Bean(name = "deOutWithdrawApplyInvestor")
	public Queue outWithdrawApplyInvestor() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_WITHDRAW_APPLY_INVESTOR);
	}

	@Bean(name = "deInWithdrawApplyBorrower")
	public Queue inWithdrawApplyBorrower() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_WITHDRAW_APPLY_BORROWER);
	}

	@Bean(name = "deOutWithdrawApplyBorrower")
	public Queue outWithdrawApplyBorrower() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_WITHDRAW_APPLY_BORROWER);
	}

	@Bean(name = "deInWithdrawNotifyInvestor")
	public Queue inWithdrawNotifyInvestor() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_WITHDRAW_NOTIFY_INVESTOR);
	}

	@Bean(name = "deOutWithdrawNotifyInvestor")
	public Queue outWithdrawNotifyInvestor() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_WITHDRAW_NOTIFY_INVESTOR);
	}

	@Bean(name = "deInWithdrawNotifyBorrower")
	public Queue inWithdrawNotifyBorrower() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_WITHDRAW_NOTIFY_BORROWER);
	}

	@Bean(name = "deOutWithdrawNotifyBorrower")
	public Queue outWithdrawNotifyBorrower() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_WITHDRAW_NOTIFY_BORROWER);
	}

	@Bean(name = "deInSeekFundChangeDetail")
	public Queue inSeekFundChangeDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_FUND_CHANGE_DETAIL);
	}

	@Bean(name = "deOutSeekFundChangeDetail")
	public Queue outSeekFundChangeDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_FUND_CHANGE_DETAIL);
	}

	@Bean(name = "deInSeekFundBalance")
	public Queue inSeekFundBalance() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_FUND_BALANCE);
	}

	@Bean(name = "deOutSeekFundBalance")
	public Queue outSeekFundBalance() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_FUND_BALANCE);
	}

	@Bean(name = "deInSeekRepayDetail")
	public Queue inSeekRepayDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_REPAY_DETAIL);
	}

	@Bean(name = "deOutSeekRepayDetail")
	public Queue outSeekRepayDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_REPAY_DETAIL);
	}

	@Bean(name = "deInSeekBiddingBidDetail")
	public Queue inSeekBiddingBidDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_BIDDING_BID_DETAIL);
	}

	@Bean(name = "deOutSeekBiddingBidDetail")
	public Queue outSeekBiddingBidDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_BIDDING_BID_DETAIL);
	}

	@Bean(name = "deInSeekBiddingInfo")
	public Queue inSeekBiddingInfo() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_BIDDING_INFO);
	}

	@Bean(name = "deOutSeekBiddingInfo")
	public Queue outSeekBiddingInfo() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_BIDDING_INFO);
	}

	@Bean(name = "deInSeekAccountBalanceDetail")
	public Queue inSeekAccountBalanceDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_ACCOUNT_BALANCE_DETAIL);
	}

	@Bean(name = "deOutSeekAccountBalanceDetail")
	public Queue outSeekAccountBalanceDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_ACCOUNT_BALANCE_DETAIL);
	}

	@Bean(name = "deInSeekPlatBalanceDetail")
	public Queue inSeekPlatBalanceDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_PLAT_BALANCE_DETAIL);
	}

	@Bean(name = "deOutSeekPlatBalanceDetail")
	public Queue outSeekPlatBalanceDetail() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_PLAT_BALANCE_DETAIL);
	}

	@Bean(name = "deInSeekBiddingBalance")
	public Queue inSeekBiddingBalance() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_BIDDING_BALANCE);
	}

	@Bean(name = "deOutSeekBiddingBalance")
	public Queue outSeekBiddingBalance() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_BIDDING_BALANCE);
	}

	@Bean(name = "deInOrderStatus")
	public Queue inOrderStatus() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_ORDER_STATUS);
	}

	@Bean(name = "deOutOrderStatus")
	public Queue outOrderStatus() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_ORDER_STATUS);
	}

	@Bean(name = "deInSeekRechargeOrderStatus")
	public Queue inSeekRechargeOrderStatus() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_SEEK_RECHARGE_ORDER_STATUS);
	}

	@Bean(name = "deOutSeekRechargeOrderStatus")
	public Queue outSeekRechargeOrderStatus() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_SEEK_RECHARGE_ORDER_STATUS);
	}

	@Bean(name = "deInPlatformFundUnblock")
	public Queue inPlatformFundBlockForDebetTransfer() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_PLATFORM_FUND_UNBLOCK);
	}

	@Bean(name = "deInPlatformFundFreeze")
	public Queue inPlatformFundUnfreezeForDebetTransfer() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_PLATFORM_FUND_FREEZE);
	}

	@Bean(name = "deOutPlatformFundUnblock")
	public Queue outPlatformFundBlockForDebetTransfer() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_PLATFORM_FUND_UNBLOCK);
	}

	@Bean(name = "deOutPlatformFundFreeze")
	public Queue outPlatformFundUnfreezeForDebetTransfer() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_PLATFORM_FUND_FREEZE);
	}

	@Bean(name = "deInPlatformTransfer")
	public Queue inPlatformTransfer() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_PLATFORM_TRANSFER);
	}

	@Bean(name = "deOutPlatformTransfer")
	public Queue outPlatformTransfer() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_PLATFORM_TRANSFER);
	}

	@Bean(name = "deInPlatformConverse")
	public Queue inPlatformConverse() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_PLATFORM_CONVERSE);
	}

	@Bean(name = "deOutPlatformConverse")
	public Queue outPlatformConverse() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_PLATFORM_CONVERSE);
	}

	@Bean(name = "deInPlatformRecharge")
	public Queue inPlatformRecharge() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_PLATFORM_RECHARGE);
	}

	@Bean(name = "deOutPlatformRecharge")
	public Queue outPlatformRecharge() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_PLATFORM_RECHARGE);
	}

	@Bean(name = "deInPlatformWithdraw")
	public Queue inPlatformWithdraw() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_PLATFORM_WITHDRAW);
	}

	@Bean(name = "deOutPlatformWithdraw")
	public Queue outPlatformWithdraw() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_PLATFORM_WITHDRAW);
	}

	@Bean(name = "deInAccountcheckBalanceInfo")
	public Queue inAccountcheckBalanceInfo() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_IN_ACCOUNTCHECK_BALANCE_INFO);
	}

	@Bean(name = "deOutAccountcheckBalanceInfo")
	public Queue outAccountcheckBalanceInfo() {
		return new ActiveMQQueue(DepositoryMqConstant.DE_OUT_ACCOUNTCHECK_BALANCE_INFO);
	}

}

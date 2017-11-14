package com.tuodao.bp.api.facade.service.product.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.api.facade.client.operation.UserTenderClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.product.RepayClient;
import com.tuodao.bp.api.facade.client.transaction.AccountLogClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowChicenessTenderClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowCollectionClient;
import com.tuodao.bp.api.facade.client.transaction.JoinPlanClient;
import com.tuodao.bp.api.facade.client.useraccount.UserClient;
import com.tuodao.bp.api.facade.service.product.IFacadeProductService;
import com.tuodao.bp.cache.basic.traningcenter.RedisLock;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.output.TenderVoucherOutput;
import com.tuodao.bp.model.business.product.CustRepay;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogExtInput;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;
import com.tuodao.bp.model.business.traningcenter.output.SelectNoMateOutput;
import com.tuodao.bp.model.business.useraccount.output.UserAccountInfoOutput;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.CFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.product.ProductConstant;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.DateUtil;

/**
 * @Author wuchengjie
 * @Date 2017/9/26 0026 17:12
 * @Introduction
 */
@Service
public class FacadeProductServiceImpl implements IFacadeProductService {

	private static final Logger logger = LoggerFactory.getLogger(FacadeProductServiceImpl.class);

	/** 精选计划投资 */
	@Autowired
	private BorrowChicenessTenderClient chicenessTenderClient;

	@Autowired
	private ProductClient productClient;

	@Autowired
	private UserTenderClient userTenderClient;

	@Autowired
	private JoinPlanClient joinPlanClient;
	@Autowired
	private UserClient userClient;
	@Autowired
	private RepayClient repayClient;
	@Autowired
	private BorrowCollectionClient borrowCollectionClient;
	@Autowired
	private AccountLogClient accountLogClient; 

	/**
	 * 执行购买
	 * 
	 * @return
	 */
	@Override
	public Boolean excutePurchased() {

		// 理财计划购买列表
		List<SelectNoMateOutput> tenderOutputList = joinPlanClient.selectNoMate();
		if (CollectionUtils.isEmpty(tenderOutputList)) {
			return true;
		}

		List<LinkedHashMap<String, Object>> tenderInputList = new ArrayList<>();
		List<Map<String, Integer>> tenderVoucherInputs = new ArrayList<>();
		for (SelectNoMateOutput output : tenderOutputList) {
			Map<String, Integer> map = new HashMap<>();
			LinkedHashMap<String, Object> out = new LinkedHashMap<String, Object>();
			out.put("Id", output.getId());
			out.put("voucherCouponId", output.getVoucherCouponId());
			out.put("productId", output.getProductId());
			out.put("userId", output.getUserId());
			out.put("account", output.getAccount());
			out.put("preAccount", output.getPreAccount());
			out.put("voucherId", output.getVoucherId());

			// 加息券
			map.put("tenderId", output.getId());
			if (output.getVoucherId() != null && output.getVoucherId() > 0) {
				map.put("voucherId", output.getVoucherId());
			} else if (output.getVoucherCouponId() != null && output.getVoucherCouponId() > 0) {
				map.put("voucherId", output.getVoucherCouponId());
			}
			tenderVoucherInputs.add(map);
			tenderInputList.add(out);
		}

		Map<String, TenderVoucherOutput> voucherMap = userTenderClient.getUserTender(tenderVoucherInputs);
		// Map<String,TenderVoucherOutput> maps = Maps.newHashMap();
		// for(Map.Entry<String,TenderVoucherOutput> entry :
		// voucherMap.entrySet()){
		// maps.put(entry.getKey().toString(),entry.getValue());
		// }

		// LinkedHashMap object = new LinkedHashMap();
		// object.put("tenderInputList",tenderInputList);
		// object.put("voucherMap",voucherMap);

		// 匹配资金
		Boolean result = productClient.matchingProduct(tenderInputList, voucherMap);

		return result;
	}

	@Override
	@Transactional(rollbackFor = RuntimeException.class)
	public Boolean repaymentProduct(ProductOutput productOutput, BorrowRepaymentOutput repayment, Boolean advance) {
		logger.info("还款开始[{}]", repayment.getId());
		RedisLock lock = new RedisLock(RedisConstans.PRODUCT_REPAYMENT_LOCK + productOutput.getProductCode());
		try {
			if (repayment.getStatus() != ProductConstant.RepaymentStatus.Status1.code) {
				logger.info("还款计划已还款[{}]", repayment.getId());
				throw new BizFeignException(null);
			}
			BasePojo pojo = new BasePojo();
			pojo.setUserId(repayment.getUserId());
			UserAccountInfoOutput user = userClient.getUserAccountInfo(pojo);

			if (null == user) {
				logger.info("还款用户不存在[{}]", repayment.getId());
				throw new BizFeignException(null);
			}

			// 判断是否还有前面的期数未还款
			BorrowRepaymentInput input = new BorrowRepaymentInput();
			input.setBorrowId(repayment.getBorrowId());
			input.setUserId(repayment.getUserId());
			input.setPeriod(repayment.getPeriod());
			input.setStatus(ProductConstant.RepaymentStatus.Status1.code);
			BorrowRepaymentOutput output = repayClient.getRepayInfoByparam(input);

			if (null != output) {
				logger.info("请先偿还前几期借款[{}]", repayment.getId());
				throw new BizFeignException(null);
			}

			// 调用回款计划接口获取
			// 调用接口根据标的repayment.getBorrowId()，当前期数 repayment.getPeriod()查询回款表
			BorrowRepaymentInput repaymentInput = new BorrowRepaymentInput();
			repaymentInput.setBorrowId(repayment.getBorrowId());
			repaymentInput.setPeriod(repayment.getPeriod());
			List<BackMoneyPlanOutput> collectionList = borrowCollectionClient.getTenderCollection(repaymentInput);
			Map<String, Object> rRetMap = this.getRepayAccountByRecover(repayment, advance, collectionList);
			// 借款人实际需要还款的金额
			Integer repayMoney = ((BigDecimal) rRetMap.get("repayMoney")).intValue();
			// 借款人实际需要还款的本金
			Integer repayCap = ((BigDecimal) rRetMap.get("repayCap")).intValue();
			// 借款人实际还款的利息
			Integer repayInterest = ((BigDecimal) rRetMap.get("repayInterest")).intValue();
			if (repayMoney <= 0) {
				logger.info("还款金额不正确[{}]", repayment.getId());
				throw new BizFeignException(null);
			}

			String orderNo = ProductConstant.ORDER_NO + productOutput.getProductCode() + "_"
					+ DateUtil.dateStr(new Date());
			boolean isPayoff = false;// 整个标的是否已还清
			if (repayment.getPeriods() == productOutput.getProductPeriod()) {// 提前还款或者还的是最后一期的情况下,
				isPayoff = true;
			}
			int step = repayment.getRepayStep();
			if (step == 0) {
				Map<String, Object> balanceMap = new HashMap<>();
				BigDecimal prodBalance = new BigDecimal(0);
				try { // 4.6.7.标的账户余额查询，查询标的账户余额是否足够
						/// Gateway_client/publishAction_getProductN_balance
					balanceMap.put(BJFN.prodId, productOutput.getProductCode());// 账户编号(标的编码)
					balanceMap.put(BJFN.type, "01");// 现金01,在途02
					// TODO 调用银行接口获取到账户余额
					prodBalance = new BigDecimal(0);
				} catch (Exception e) {
					logger.info("标的账户余额查询失败[{}]", repayment.getId());
					throw new BizFeignException(null);
				}
				logger.info("prodBalance: {}, repayMoney: {}", prodBalance, repayMoney);
				if (prodBalance.compareTo(BigDecimal.valueOf(repayMoney)) >= 0) {// 标的余额足够,
					step = 1; // 执行下一步
					BorrowRepaymentInput borrowRepaymentInput = new BorrowRepaymentInput();
					borrowRepaymentInput.setId(repayment.getId());
					borrowRepaymentInput.setRepayStep(1);
					repayClient.updateRepayment(borrowRepaymentInput);
				} else {// 标的余额不足, 补足余额
					BigDecimal notEnoughBalance = BigDecimal.valueOf(repayMoney).subtract(prodBalance);
					logger.info("notEnoughBalance: {}", notEnoughBalance);
					// 封装查询代偿人账户map对象
					balanceMap.clear();
					balanceMap.put(TDFN.account, "account");// 账户编号(代偿人即平台账户)
					balanceMap.put(BJFN.acctType, "13");// 投资账户 12、融资 13
					balanceMap.put(BJFN.type, "01");// 现金01,在途02
					// 4.6.6.账户余额明细查询,调用接口查询带承认账户资金
					/// Gateway_client/ accountAction_getAccountN_balace
					// TODO 调用银行接口
					BigDecimal dataBalance = new BigDecimal(0);
					if (dataBalance == null) {
						logger.info("代偿人现金融资账户查询失败[{}]", repayment.getId());
						throw new BizFeignException(null);
					}
					logger.info("balance: {}, notEnoughBalance: {}", dataBalance, notEnoughBalance);
					if (notEnoughBalance.compareTo(dataBalance) > 0) {
						logger.info("代偿人现金融资账户余额不足[{}]", repayment.getId());
						throw new BizFeignException(null);
					}
					// 标的代偿（委托）还款
					this.doCompensation(repayment, user, productOutput, notEnoughBalance, orderNo);
				}
			}
			if (step <= 1) {
				Map<String, Object> retMap = getRecoverMap(advance, repayment, orderNo, isPayoff, collectionList);
				boolean repayFlag = true;// 本期是否已还清, 默认都是true（不存在一期多次还）
				// 标的还款
				this.doBorrowRepay(productOutput, repayment, retMap, repayFlag, isPayoff, orderNo);
			}
			if (step <= 2) {
				doRepayAndRecover(productOutput, repayment, rRetMap, orderNo, isPayoff, advance);
			}
		} finally {
			lock.unlock();
		}
		return null;
	}

	/**
	 * @param repayId回款表主键ID
	 * @param repayment回款表信息
	 * @param user投资用户信息
	 * @param productOutput标的信息
	 * @param notEnoughBalance不足余额
	 * @param orderNo订单号
	 *            
	 */
	public void doCompensation(BorrowRepaymentOutput repayment, UserAccountInfoOutput user, ProductOutput productOutput,
			BigDecimal notEnoughBalance, String orderNo) {

		// 封装调用4.2.2. 标的代偿（委托）还款Map参数
		// ../Gateway_client/repayAction_compensate
		Map<String, Object> map = new HashMap<>();
		map.put(BJFN.orderNo, orderNo);// 订单号
		map.put(BJFN.prodId, productOutput.getProductCode());// 产品编号
		// 计划还款日期（yyyyMMdd）
		map.put(BJFN.repayDate, DateUtil.dateStr(repayment.getPreRepayTime(), "yyyyMMdd"));
		map.put(BJFN.repayAmt, repayment.getPreInterest());// 计划还款金额
		map.put(BJFN.realRepayDate, DateUtil.dateStr(repayment.getRepayTime(), "yyyyMMdd"));// 实际还款时间
		map.put(BJFN.realRepayAmt, repayment.getInterest());// 实际还款金额
		map.put(BJFN.platCust, user.getDepositNo());// 平台客户编号（借款人）
		map.put(BJFN.compensation+CFN._swb_+BJFN.platCust, null);// 代偿人平台客户编号
		map.put(BJFN.feeAmt, null);// 手续费金额
		map.put(BJFN.repayType, 1);// 还款类型 0-代偿还款 1-委托还款
		map.put(BJFN.transAmt, notEnoughBalance);// 交易金额（实际还款金额+手续费金额）

		// TODO 调用4.2.2. 标的代偿（委托）接口
		// TODO 接口调用后处理
		// 将还款步骤repayStep 状态修改为1
		BorrowRepaymentInput borrowRepaymentInput = new BorrowRepaymentInput();
		borrowRepaymentInput.setId(repayment.getId());
		borrowRepaymentInput.setRepayStep(1);
		repayClient.updateRepayment(borrowRepaymentInput);
		
		// TODO 写代偿人资金记录,更改账户金额
		AccountLogExtInput input = new  AccountLogExtInput();
		input.setUserId(user.getUserId());
		input.setOrderNo(orderNo);
		input.setType(AccountLogType.RECHARGE.getCode());
		input.setAccount(notEnoughBalance);//本次资金变动发生的金额
		
		input.setRemarks("标的代偿（委托）还款");
		//账户备份:用户账户总额,总资产=cash_frost+tender_frosh+balance+await_capital+recharge+await_interest 投资可用余额=balance+recharge 可提现金额:balance'  
		input.setTotal(BigDecimal.ZERO);
		input.setCashFrost(BigDecimal.ZERO);//账户备份:提现冻结金额
		input.setTenderFrost(BigDecimal.ZERO);//账户备份:投标冻结(散标,精选计划)
		input.setBalance(BigDecimal.ZERO);//账户备份:可用余额(可投金额+充值金额)
		input.setRecharge(BigDecimal.ZERO);//账户备份:充值金额(可投资不可提现金额)
		input.setAwaitCapital(BigDecimal.ZERO);//账户备份:总待收本金
		//fromAccount 为平台在系统中的账户，目前数据库没有该账户信息
		input.setFromAccount(null);//资金来源账户(如果为平台账户该字段有值)
		input.setFromRemarks("平台子账户");//账户名称备注(例如平台子账户,清算账户,加息券子账户等)
		input.setToAccount(user.getUserId());//资金去往账户(如果为平台账户该字段有值)
		input.setToRemarks("平台借款人账户");//账户名称备注(例如平台子账户,加息券子账户等)
		input.setIsShow(1);//是否显示资金日志,0:不显示(精选计划底层标的资金记录) 1:显示
		input.setIntrestAccount(BigDecimal.ZERO);//收益发生变动的资金
		input.setFeeAccount(BigDecimal.ZERO);//手续费发生变动的资金
		input.setChangeType(0);//0收入1支出2冻结3:解冻
		accountLogClient.addAccountLog(input);
	}
	/**
	 * 根据回款计划计算还款金额
	 *
	 * @param BorrowRepaymentOutput
	 * @param advance
	 * @param List<BackMoneyPlanOutput>
	 * @return
	 */
	public Map<String, Object> getRepayAccountByRecover(BorrowRepaymentOutput repayment, Boolean advance,List<BackMoneyPlanOutput> collectionList) {
		Map<String, Object> retMap = new HashMap<>();
		BigDecimal repayMoney = new BigDecimal(0);// 借款人实际需要还款的金额
		BigDecimal repayCap = new BigDecimal(0);// 借款人实际需要还款的本金
		BigDecimal repayInterest = new BigDecimal(0);// 借款人实际还款的利息
		// br.getRecoverCapital() 本金 preCapital
		// br.getCouponVoucherAccount() 加息券利息 couponAccount
		// br.getAwardAccount() 平台加息利息 platformAccount
		// br.getRecoverInterest() 总利息 interest

		if (advance) {
			for (BackMoneyPlanOutput br : collectionList) {
				BasePojo pojo = new BasePojo();
				pojo.setUserId(br.getUserId());
				//判断用户北京银行开户是否成功
				UserAccountInfoOutput user = userClient.getUserAccountInfo(pojo);
				if (user != null && user.getIsOpenDeposit() == 1) {
					BigDecimal ratesAmt = new BigDecimal(0);// 加息金（包含加息和奖励）
					BigDecimal realRepayVal = new BigDecimal(0);// 实际还款利息(不包含加息和奖励)
					BigDecimal realRepayAmount = new BigDecimal(0);// 实际还款本金

					// 调用接口根据标的repayment.getBorrowId()，投资人br.getUserId()，当前期数
					// repayment.getPeriod() 查询回款表
					//List<BackMoneyPlanOutput> collectionTendList = null;
					
					BorrowRepaymentInput repaymentInput = new BorrowRepaymentInput();
					repaymentInput.setBorrowId(repayment.getBorrowId());
					repaymentInput.setPeriod(repayment.getPeriod());
					repaymentInput.setUserId(br.getUserId());
					List<BackMoneyPlanOutput> collectionTendList = borrowCollectionClient.getTenderCollection(repaymentInput);

					for (BackMoneyPlanOutput sbr : collectionTendList) {
						realRepayAmount = realRepayAmount.add(sbr.getPreCapital());// 本金
						if (repayment.getPeriod() == sbr.getPeriod()) {
							int thisRatesAmt;
							if (isAllFee(repayment.getRepayTime())) {
								// 加息券利息 +平台加息利息
								thisRatesAmt = sbr.getCouponAccount().add(sbr.getPlatformAccount()).intValue();
								// 总利息
								realRepayVal = realRepayVal.add(sbr.getInterest())
										.subtract(BigDecimal.valueOf(thisRatesAmt));
							} else {
								// 加息券利息 +平台加息利息
								thisRatesAmt = sbr.getCouponAccount().add(sbr.getPlatformAccount())
										.divide(new BigDecimal(2)).intValue();
								// 总利息
								realRepayVal = realRepayVal.add(BigDecimal.valueOf(sbr.getInterest().intValue() / 2))
										.subtract(BigDecimal.valueOf(thisRatesAmt));
							}
							ratesAmt = ratesAmt.add(BigDecimal.valueOf(thisRatesAmt));
						}
					}
					repayMoney = repayMoney.add(realRepayAmount).add(realRepayVal);
					repayCap = repayCap.add(realRepayAmount);
					repayInterest = repayInterest.add(realRepayVal);
				}
			}
		} else {
			for (BackMoneyPlanOutput br : collectionList) {
				BasePojo pojo = new BasePojo();
				pojo.setUserId(repayment.getUserId());
				UserAccountInfoOutput user = userClient.getUserAccountInfo(pojo);
				if (user != null && user.getIsOpenDeposit() == 1) {
					BigDecimal realRepayAmount = br.getPreCapital();// 本金
					BigDecimal ratesAmt = br.getCouponAccount().add(br.getPlatformAccount());// 加息券利息+平台加息利息
					BigDecimal realRepayVal = br.getInterest().subtract(ratesAmt);// 总利息-（加息券利息+平台加息利息）
					repayMoney = repayMoney.add(realRepayAmount).add(realRepayVal);
					repayCap = repayCap.add(realRepayAmount);
					repayInterest = repayInterest.add(realRepayVal);
				}
			}
		}
		retMap.put("repayMoney", repayMoney);
		retMap.put("repayCap", repayCap);
		retMap.put("repayInterest", repayInterest);
		return retMap;
	}
	/**
	 * 
	 * 根据回款计划计算实际还款金额,投资人还款列表
	 * 
	 * @param advance
	 * @param repayment
	 * @param repayOrderNo
	 * @param isPayoff
	 * @param collectionList
	 * @return
	 */
	public Map<String, Object> getRecoverMap(boolean advance, BorrowRepaymentOutput repayment, String repayOrderNo,
			boolean isPayoff, List<BackMoneyPlanOutput> collectionList) {
		Map<String, Object> retMap = new HashMap<>();
		List<CustRepay> custRepayList = new ArrayList<>();
		BigDecimal recoverMoney = new BigDecimal(0);

		for (BackMoneyPlanOutput br : collectionList) {
			BasePojo pojo = new BasePojo();
			pojo.setUserId(repayment.getUserId());
			UserAccountInfoOutput user = userClient.getUserAccountInfo(pojo);
			if (user != null && user.getIsOpenDeposit() == 1) {
				CustRepay custRepay = new CustRepay();
				custRepay.setCustNo(user.getDepositNo());
				custRepay.setExperienceAmt(BigDecimal.ZERO);
				custRepay.setRealRepayDate(DateUtil.getDate("yyyyMMdd"));// 实际还款日期
				custRepay.setRepayDate(DateUtil.formatShort(br.getPreCollectionTime()));// 还款日期
				custRepay.setRepayFee(BigDecimal.ZERO);// 手续费
				custRepay.setRepayFlag("0");// 本期已还清：0-是，1-否
				custRepay.setRepayNum(String.valueOf(br.getPeriod()));// 还款期数

				BigDecimal ratesAmt = new BigDecimal(0);// 加息金
				BigDecimal realRepayVal = new BigDecimal(0);// 实际还款利息
				BigDecimal realRepayAmount = new BigDecimal(0);// 实际还款本金

				if (advance) {
					// 调用接口根据标的repayment.getBorrowId()，投资人br.getUserId()，当前期数
					// repayment.getPeriod() 查询回款表
					List<BackMoneyPlanOutput> collectionTendList = null;

					for (BackMoneyPlanOutput sbr : collectionTendList) {
						realRepayAmount = realRepayAmount.add(sbr.getCapital());
						if (repayment.getPeriod() == sbr.getPeriod()) {
							int thisRatesAmt;
							if (isAllFee(repayment.getRepayTime())) {
								thisRatesAmt = sbr.getCouponAccount().add(sbr.getPlatformAccount()).intValue();
								realRepayVal = sbr.getInterest().subtract(BigDecimal.valueOf(thisRatesAmt));
							} else {
								thisRatesAmt = sbr.getCouponAccount().add(sbr.getPlatformAccount())
										.divide(new BigDecimal(2)).intValue();
								realRepayVal = realRepayVal.add(BigDecimal.valueOf(sbr.getInterest().intValue() / 2))
										.subtract(BigDecimal.valueOf(thisRatesAmt));
							}
							ratesAmt = ratesAmt.add(BigDecimal.valueOf(thisRatesAmt));
						}
					}
				} else {
					realRepayAmount = br.getPreCapital();
					realRepayVal = br.getInterest().subtract(br.getCouponAccount()).subtract(br.getPlatformAccount());
					ratesAmt = br.getCouponAccount().add(br.getPlatformAccount());
				}
				BigDecimal realRepayAmt = realRepayAmount.add(ratesAmt).add(realRepayVal);// 实际还款金额（实际还款本金+体验金+加息金+利息+手续费）
				custRepay.setRatesAmt(ratesAmt);// 加息金
				custRepay.setRealRepayVal(realRepayVal);// 实际还款利息
				custRepay.setRealRepayAmount(realRepayAmount);// 实际还款本金
				custRepay.setRealRepayAmt(realRepayAmt);// 实际还款金额（实际还款本金+体验金+加息金+利息+手续费）
				custRepayList.add(custRepay);
				recoverMoney = recoverMoney.add(realRepayAmt);
			}
		}
		retMap.put("recoverMoney", recoverMoney);
		retMap.put("custRepayList", custRepayList);

		return retMap;
	}

	/**
	 * 4.3.6标的还款
	 * 
	 * @param productOutput
	 * @param repayment
	 * @param recoverAll
	 * @param custRepayList
	 * @param repayFlag
	 * @param isPayoff
	 */
	public void doBorrowRepay(ProductOutput productOutput, BorrowRepaymentOutput repayment, Map<String, Object> retMap,
			boolean repayFlag, boolean isPayoff, String orderNo) {

		BigDecimal recoverAll = (BigDecimal) retMap.get("recoverMoney");// 实际还款金额
		List<CustRepay> custRepayList = (List<CustRepay>) retMap.get("custRepayList");
		/// Gateway_client/ accountAction_getAccountN_balace
		// 封装调用银行接口所需参数map对象
		Map<String, Object> map = new HashMap<>();
		map.put(BJFN.orderNo, orderNo);// 订单号
		map.put(BJFN.prodId, productOutput.getProductCode());// 产品编号
		map.put(BJFN.repayNum, repayment.getPeriod());// 还款期数，如果一次性还款，repay_num为1
		map.put(BJFN.isPayoff, isPayoff ? "0" : "1");// 是否整个标的还清：0-是，1-否；
		map.put(BJFN.transAmt, recoverAll);// 交易金额（所有实际还款金额之和）
		map.put(BJFN.repayFlag, repayFlag ? "0" : "1");// 本期已还清：0-是，1-否
		map.put(BJFN.funddata, JSON.toJSONString(custRepayList));// 资金数据，json格式记录还款金额

		// 调用银行还款接口
		// TODO 调用接口返回处理

		// 将还款步骤乐观锁repayStep字段状态改为2
		BorrowRepaymentInput borrowRepaymentInput = new BorrowRepaymentInput();
		borrowRepaymentInput.setId(repayment.getId());
		borrowRepaymentInput.setRepayStep(2);
		repayClient.updateRepayment(borrowRepaymentInput);
	}

	public void doRepayAndRecover(ProductOutput productOutput, BorrowRepaymentOutput repayment,
			Map<String, Object> rRetMap, String orderNo, boolean isPayoff, boolean advance) {
		// 借款人实际需要还款的金额
		BigDecimal repayMoney = (BigDecimal) rRetMap.get("repayMoney");
		// 借款人实际需要还款的本金
		BigDecimal repayCap = (BigDecimal) rRetMap.get("repayCap");
		// 借款人实际还款的利息
		BigDecimal repayInterest = (BigDecimal) rRetMap.get("repayInterest");

		Integer repayStep = null;
		// 更新还款计划
		updateRepay(advance, repayment, repayMoney, repayCap, repayInterest, repayStep);
		// 调用交易中心 接口，处理投资人相关信息
		BorrowRepaymentInput repaymentInput = new  BorrowRepaymentInput();
		repaymentInput.setBorrowId(productOutput.getId());
		repaymentInput.setPeriod(productOutput.getProductPeriod());
		repaymentInput.setOrderId(orderNo);
		repaymentInput.setAdvance(advance);
		borrowCollectionClient.collectionRepayment(repaymentInput);
		
		ProductInput input = new ProductInput();
		// 更新标的信息
		// isPayoff 为 true 则表示已还清 ,advance 为true表示提前还款
		if (isPayoff) {
			input.setProductStatus(8);
		} else if (advance) {
			input.setProductStatus(9);
		} else {
			input.setProductStatus(7);
		}
		input.setRepayLastTime(new Date());
		input.setId(productOutput.getId());
		productClient.updateProduct(input);
		repayStep = 3;
		updateRepay(advance, repayment, repayMoney, repayCap, repayInterest, repayStep);

	}

	private void updateRepay(boolean advance, BorrowRepaymentOutput repayment, BigDecimal repayMoney,
			BigDecimal repayCap, BigDecimal repayInterest, Integer repayStep) {
		BorrowRepaymentInput borrowRepaymentInput = new BorrowRepaymentInput();
		BeanUtils.copyProperties(repayment, borrowRepaymentInput);
		borrowRepaymentInput.setCapital(repayCap);
		borrowRepaymentInput.setInterest(repayInterest);
		// 还款状态:0:未还1:已还,2:提前还款
		borrowRepaymentInput.setStatus(1);
		borrowRepaymentInput.setRepayTime(new Date());
		// 还款方式 0：平台代付 1:个人还款
		borrowRepaymentInput.setRepayMode(0);
		borrowRepaymentInput.setRepayStep(repayStep);
		repayClient.updateRepayment(borrowRepaymentInput);

	}

	private boolean isAllFee(Date repayTime) {
		long fifteen = 1296000L;
		long nowTime = DateUtil.getTimeStampSeconds();
		return nowTime + fifteen > repayTime.getDate();
	}
}

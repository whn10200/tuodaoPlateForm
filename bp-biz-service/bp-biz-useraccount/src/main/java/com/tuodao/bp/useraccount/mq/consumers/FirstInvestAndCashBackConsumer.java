package com.tuodao.bp.useraccount.mq.consumers;

import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.constant.useraccount.UaConstant;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.model.mq.OpPaybackInviteAwardMqInfo;
import com.tuodao.bp.model.mq.TenderAccountMqInfo;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.mq.provider.MqProviderToOp;
import com.tuodao.bp.useraccount.mq.provider.MqProviderToTraning;
import com.tuodao.bp.useraccount.persistence.mapper.basic.AccountFinanceMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.AccountFundsMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserFinancialPlannerRuleMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.*;
import com.tuodao.bp.useraccount.service.IAccountService;
import com.tuodao.bp.useraccount.service.UserBaseService;
import com.tuodao.bp.utils.BigDecimalUtils;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

import static com.tuodao.bp.activemq.constant.MqContstant.ACCOUNT_FIRST_INVEST_OR_CASH_QUEUE;


/**
 * @description: 首投及返现Mq消费者
 * @author: mif
 * @date: 2017/11/7
 * @time: 09:51
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Component
public class FirstInvestAndCashBackConsumer extends UserBaseService {

    private static Logger logger = LoggerFactory.getLogger(FirstInvestAndCashBackConsumer.class);

    @Resource
    private AccountFinanceMapper accountFinanceMapper;

    @Resource
    private AccountFundsMapper accountFundsMapper;

    @Resource
    private UserFinancialPlannerRuleMapper userFinancialPlannerRuleMapper;


    @Resource
    private MqProviderToOp mqProviderToOp;

    @Resource
    private MqProviderToTraning mqProviderToTraning;


    @Resource
    private IAccountService accountService;

    /**
     * 消费方法
     *
     * @param message             消息主体
     * @param tenderAccountMqInfo 对象
     */
    @JmsListener(destination = ACCOUNT_FIRST_INVEST_OR_CASH_QUEUE)
    @Transactional(rollbackFor = BizFeignException.class)
    public void consumer(ActiveMQObjectMessage message, TenderAccountMqInfo tenderAccountMqInfo) {
        logger.info("首投及返现Mq消费者开始，tenderAccountMqInfo ={}", tenderAccountMqInfo);
        final String userId = tenderAccountMqInfo.getUserId();

        UserAccountInfo accountInfo = super.getUserAccountInfo(userId);

        final String directInviteNo = accountInfo.getDirectInviterNo(); //直接邀请人
        final String mobile = accountInfo.getMobile(); // 手机号码

        if (StringUtils.isEmpty(directInviteNo)) {
            logger.info("用户无直接邀请人，userId={}", userId);
            return;
        }

        switch (tenderAccountMqInfo.getType()) {
            case 0:
                // 投标(复审) 发放首投相关奖励
                if (PublicConstant.IF_YES == accountInfo.getInvestFlag() && !tenderAccountMqInfo.getIsFirstTender()) {
                    logger.info("用户非首投：userId={}", userId);
                    break;
                }
                //发放直接邀请人首投抵用券；
                logger.info("发放直接邀请人首投抵用券,直接邀请人={},mobile={},运营活动ID=FRIEND_FIRST_INVEST", directInviteNo, message);
                mqProviderToOp.send2Operation4CouponGrant(directInviteNo, mobile, PublicConstant.WELFARE_ACTIVITY_CODE_FRIEND_FIRST_INVEST);

                //好友首投0.25%的返现
                BigDecimal firstTimeAward = BigDecimalUtils.multiply(tenderAccountMqInfo.getFirstTenderAccount(), UaConstant.FIRST_TIME_INVITE_RATE);

                //修改直接邀请人融资帐号资金
                updateInviteAccountAward(accountInfo.getDirectInviterNo(), firstTimeAward, AccountLogType.INVITE_TENDER_AWARD.getCode());//邀请好友投资奖励

                //MQ添加邀请记录
                mqProviderToOp.send2Operation4FirstTimeInvest(accountInfo, firstTimeAward);

                //修改用户是否投资标识
                updateUserInvestFlag(accountInfo);
                break;
            case 1:
                //回款
                logger.info("用户回款：userId ={},directInviteNo={}", userId, directInviteNo);
                if (null == tenderAccountMqInfo.getAwardInterest()) {
                    logger.error("回款收益为空");
                    break;
                }
                FinancialPlannerOutput financialPlanner = accountService.selectUserFinancialPlanner(directInviteNo);
                //直接邀请人理财师奖励
                UserAccountInfo directInviteUser = super.getUserAccountInfo(directInviteNo);
                if (!financialPlanner.isCanCashback()) {
                    logger.info("用户无需回款返现奖励,directInviteNo={},financialPlanner ={}", directInviteNo, financialPlanner);
                    break;
                }
                UserFinancialPlannerRule rule = getFinancialPlannerRuleByLevel(financialPlanner.getCurrentLevel());
                if (null == rule) {
                    break;
                }

                //直接邀请人返现奖励
                BigDecimal awardMoney = BigDecimalUtils.multiply(tenderAccountMqInfo.getAwardInterest(), rule.getDirectCashbackPer());
                //更新直接邀请人帐号信息
                updateInviteAccountAward(accountInfo.getDirectInviterNo(), awardMoney, AccountLogType.INVITE_COLLECTION_AWARD.getCode());//邀请好友回款奖励

                //运营中心回款奖励队列信息
                OpPaybackInviteAwardMqInfo mqInfo = new OpPaybackInviteAwardMqInfo();
                mqInfo.setUserId(directInviteNo);
                mqInfo.setMobile(directInviteUser.getMobile());
                mqInfo.setDirectInviteeMobile(accountInfo.getMobile()); //直接被邀请人
                mqInfo.setRegisterTime(accountInfo.getRegisterDate());
                mqInfo.setAwardMoney(awardMoney);
                mqProviderToOp.send2Operation4PaybackInviteAward(mqInfo);

                // 调“平台转个人”接口从平台自有子账户转给会员现金投资账户 TODO


                //间接邀请人理财师奖励
                final String indirectInviteNo = accountInfo.getIndirectInviterNo();
                if (StringUtils.isEmpty(indirectInviteNo)) {
                    logger.info("无间接邀请人，无需返现");
                }
                UserAccountInfo indirectInviteUser = super.getUserAccountInfo(indirectInviteNo);

                financialPlanner = accountService.selectUserFinancialPlanner(indirectInviteNo);
                if (!financialPlanner.isCanCashback()) {
                    logger.info("用户无需回款奖励,indirectInviteNo={},financialPlanner ={}", indirectInviteNo, financialPlanner);
                    break;
                }

                rule = getFinancialPlannerRuleByLevel(financialPlanner.getCurrentLevel());
                if (null == rule) {
                    break;
                }
                if (0 == rule.getIndirectCashbackPer()) {
                    logger.info("无需间接返现回款奖励,间接邀请人投资收益返现比例为0");
                    break;
                }
                //间接邀请人返现奖励
                awardMoney = BigDecimalUtils.multiply(tenderAccountMqInfo.getAwardInterest(), rule.getIndirectCashbackPer());
                updateInviteAccountAward(indirectInviteNo, awardMoney, AccountLogType.INVITE_COLLECTION_AWARD.getCode());//邀请好友回款奖励

                //运营中心回款奖励队列信息
                mqInfo.setUserId(indirectInviteNo);
                mqInfo.setMobile(indirectInviteUser.getMobile());
                mqInfo.setDirectInviteeMobile(directInviteUser.getMobile()); //直接被邀请人
                mqInfo.setIndirectInviteeMobile(accountInfo.getMobile());//间接被邀请人
                mqInfo.setRegisterTime(accountInfo.getRegisterDate());
                mqInfo.setAwardMoney(awardMoney);
                mqProviderToOp.send2Operation4PaybackInviteAward(mqInfo);


                // 调“平台转个人”接口从平台自有子账户转给会员现金投资账户 TODO
                break;
            default:
                break;
        }
    }

    /**
     * 根据ID 获取理财师规则
     *
     * @param currentLevel 当前理财师等级
     * @return 等级规则
     */
    private UserFinancialPlannerRule getFinancialPlannerRuleByLevel(Integer currentLevel) {
        UserFinancialPlannerRuleExample example = new UserFinancialPlannerRuleExample();
        example.createCriteria().andFinancialPlannerLevelEqualTo(currentLevel).andIsDelEqualTo(PublicConstant.DEL_NO);
        List<UserFinancialPlannerRule> list = userFinancialPlannerRuleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0);
    }

    /**
     * 更新邀请人账户资金信息
     *
     * @param directInviteNo 直接邀请人编码
     * @param award          奖励金额
     * @param type           资金变动类型
     */
    private void updateInviteAccountAward(String directInviteNo, BigDecimal award, Integer type) {
        //1.更新金融账户资金
        AccountFinance accountFinancentInfo = super.getAccounTFinanceByUserId(directInviteNo);
        accountFinancentInfo.setTotalFund(accountFinancentInfo.getTotalFund().add(award));          //存管账户总资金（分）
        accountFinancentInfo.setUsableFund(accountFinancentInfo.getUsableFund().add(award));        //可用余额(分)
        accountFinancentInfo.setReturnAmount(accountFinancentInfo.getReturnAmount().add(award));    //已获得返现奖励 (分)
        accountFinanceMapper.updateByPrimaryKeySelective(accountFinancentInfo);

        //2.更新平台账户资金
        AccountFunds funds = accountFundsMapper.selectByPrimaryKey(directInviteNo);
        AccountFunds accountFunds = new AccountFunds();
        accountFunds.setUserId(directInviteNo);
        accountFunds.setTotalFund(funds.getTotalFund().add(award));
        accountFunds.setFinanceFund(funds.getFinanceFund().add(award));
        accountFundsMapper.updateByPrimaryKeySelective(accountFunds);

        mqProviderToTraning.send2Deal4AccountLog(accountFinancentInfo, award, type);
    }

    /**
     * 修改用户是否投资标识
     *
     * @param userAccountInfo 用户账户信息
     */
    private void updateUserInvestFlag(UserAccountInfo userAccountInfo) {
        if (PublicConstant.IF_NO == userAccountInfo.getInvestFlag()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userAccountInfo.getUserId());
            userInfo.setInvestFlag(PublicConstant.IF_YES);
            userInfoMapper.updateByPrimaryKeySelective(userInfo);
        }
    }
}

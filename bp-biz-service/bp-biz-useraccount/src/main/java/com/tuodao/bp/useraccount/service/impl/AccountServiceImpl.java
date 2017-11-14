package com.tuodao.bp.useraccount.service.impl;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.cache.basic.traningcenter.OnlineBankInfo;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.UserDepositNo;
import com.tuodao.bp.model.business.useraccount.input.ValidatePayPwInput;
import com.tuodao.bp.model.business.useraccount.output.AccountSettingInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.FinancialPlannerOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.constant.UserAccountBizConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserDepositInfoMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserFinancialPlannerMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserFinancialPlannerRuleMapper;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserInfoDetailMapper;
import com.tuodao.bp.useraccount.persistence.mapper.biz.BizUserAccountMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.*;
import com.tuodao.bp.useraccount.persistence.model.biz.FinancialPlannerStatistics;
import com.tuodao.bp.useraccount.service.IAccountService;
import com.tuodao.bp.useraccount.service.UserBaseService;
import com.tuodao.bp.utils.CommonUtils;
import com.tuodao.bp.utils.MD5Utils;
import com.tuodao.bp.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.tuodao.bp.cache.constant.DictionaryConstants.FINANCIAL_PLANNER_LEVEL;

/**
 * @description: 账户相关服务
 * @author: mif
 * @date: 2017/8/28
 * @time: 16:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class AccountServiceImpl extends UserBaseService implements IAccountService {

    private final static Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    private BizUserAccountMapper bizUserAccountMapper;

    @Resource
    private UserFinancialPlannerMapper userFinancialPlannerMapper;

    @Resource
    private UserFinancialPlannerRuleMapper userFinancialPlannerRuleMapper;

    @Resource
    private UserDepositInfoMapper userDepositInfoMapper;

    @Resource
    private UserInfoDetailMapper userInfoDetailMapper;

    @Resource
    private BankCache bankCache;

    @Override
    public FinancialPlannerOutput selectUserFinancialPlanner(String userId) {

        FinancialPlannerOutput plannerOutput = new FinancialPlannerOutput();

        // 后台设置理财师等级
        UserFinancialPlanner planner = userFinancialPlannerMapper.selectByPrimaryKey(userId);
        if (null != planner) {
            //有效期内
            if (null == planner.getEndTime() || planner.getEndTime().getTime() > (new Date()).getTime()) {
                plannerOutput.setCurrentLevel(planner.getFinancialPlannerLevel());
                plannerOutput.setCurrentLevelName(super.getDictionaryName(FINANCIAL_PLANNER_LEVEL, planner.getFinancialPlannerLevel() + ""));
                plannerOutput.setAdminSetting(true);
                plannerOutput.setCanCashback(true);
                return plannerOutput;
            }
        }

        // 通过统计获取等级
        FinancialPlannerStatistics statistics = bizUserAccountMapper.selectUserFinancialPlanner(userId);
        if (null == statistics) {
            return new FinancialPlannerOutput();
        }

        UserFinancialPlannerRuleExample example = new UserFinancialPlannerRuleExample();
        example.createCriteria().andIsDelEqualTo(PublicConstant.DEL_NO);
        example.setOrderByClause("financial_planner_level desc");
        List<UserFinancialPlannerRule> ruleList = userFinancialPlannerRuleMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(ruleList)) {
            logger.info("user financial planner rule haven't data");
            return plannerOutput;
        }

        //当前理财师等级
        UserFinancialPlannerRule nowRule = ruleList.stream().filter(userFinancialPlannerRule ->
                (userFinancialPlannerRule.getVip1InviteeTotal() <= statistics.getVip1total())
                        && statistics.getDueInFundTotal().longValue() >= userFinancialPlannerRule.getDueInFundTotal().longValue())
                .findFirst().orElse(null);
        if (null != nowRule) {

            logger.info("用户={},当前理财师等级 ={}", userId, nowRule.getFinancialPlannerLevel());

            plannerOutput.setCurrentLevel(nowRule.getFinancialPlannerLevel());
            plannerOutput.setCurrentLevelName(super.getDictionaryName(FINANCIAL_PLANNER_LEVEL, nowRule.getFinancialPlannerLevel() + ""));
            plannerOutput.setCurrentV1Count(statistics.getVip1total());
            plannerOutput.setCurrentDueInFundTotal(statistics.getDueInFundTotal());

            //上一级理财师
            Iterable<UserFinancialPlannerRule> collect = ruleList.stream().filter(userFinancialPlannerRule ->
                    userFinancialPlannerRule.getId() > nowRule.getId()
            ).collect(Collectors.toList());
            if (null == collect) {
                logger.info("user haven't up financial planner level");
                return plannerOutput;
            }
            UserFinancialPlannerRule upRule = Iterables.getLast(collect);

            if (null != upRule) {
                plannerOutput.setUpLevel(upRule.getFinancialPlannerLevel());
                plannerOutput.setUpLevelName(super.getDictionaryName(FINANCIAL_PLANNER_LEVEL, upRule.getFinancialPlannerLevel() + ""));
                plannerOutput.setDifferV1Count(upRule.getVip1InviteeTotal() - statistics.getVip1total());
                plannerOutput.setDifferDueInFundTotal(upRule.getDueInFundTotal().subtract(statistics.getDueInFundTotal()));
            }

            //是否已失效
            UserAccountInfo userAccountInfo = super.getUserAccountInfo(userId);
            plannerOutput.setAdminSetting(false);
            if (nowRule.getValidityType() == UserAccountBizConstant.VALIDITY_TYPE_SIX_MONTH) {
                plannerOutput.setCanCashback(TimeUtils.getDitMonth(userAccountInfo.getRegisterDate(), new Date()) < 6);
            } else {
                plannerOutput.setCanCashback(true);
            }
        }
        return plannerOutput;
    }

    /**
     * 验证支付密码
     *
     * @param input 支付密码
     * @return 是否正确
     */
    @Override
    public Boolean validatePayPw(ValidatePayPwInput input) {
        UserInfo userInfo = super.selectUserByUserId(input.getUserId());
        if (PublicConstant.IF_NO == userInfo.getIsOpenDeposit()) {
            throw new BizFeignException(UaRespExceptionConstant.USER_NOT_OPEN_DEPOSIT);
        }
        // 支付密码
        String payPaw = MD5Utils.md5(input.getPayPw() + userInfo.getPayPwdKey());
        return Objects.equals(payPaw, userInfo.getPayPassword());
    }

    /**
     * 获取存管编码列表
     *
     * @param userIds 用户编码列表
     * @return 存管编码列表
     */
    @Override
    public List<UserDepositNo> getDepositNoList(List<String> userIds) {
        List<UserDepositNo> list = bizUserAccountMapper.selectDepositNoList(userIds);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        if (list.size() == userIds.size()) {
            return list;
        }
        List<UserDepositNo> unMatchList = Lists.newArrayList();
        //为查询到的 depositNo 为空
        userIds.forEach(userId -> {
            boolean match = list.stream().noneMatch(userDepositNo -> Objects.equals(userId, userDepositNo.getUserId()));
            if (match) {
                unMatchList.add(new UserDepositNo(userId, null));
            }
        });
        list.addAll(unMatchList);
        return list;
    }

    /**
     * 获取用户账户设置信息
     *
     * @param userId 用户ID
     * @return 用户账户设置信息
     */
    @Override
    public AccountSettingInfoOutput getAccountSetting(String userId) {
        UserAccountInfo userAccountInfo = super.getUserAccountInfo(userId);

        AccountSettingInfoOutput output = new AccountSettingInfoOutput();
        output.setIsOpenDeposit(userAccountInfo.getIsOpenDeposit());
        output.setIsBindBank(userAccountInfo.getIsBindBank());
        output.setPwSecurityLevel(userAccountInfo.getPwSecurityLevel());

        if (userAccountInfo.getIsBindBank() == PublicConstant.IF_YES) {
            UserDepositInfo userDepositInfo = userDepositInfoMapper.selectByPrimaryKey(userId);
            output.setBankNum(CommonUtils.replaceBankNum(userDepositInfo.getBankNum()));
            output.setBankName(getBankName(userDepositInfo.getBankCode()));
        }
        UserInfoDetail userInfoDetail = userInfoDetailMapper.selectByPrimaryKey(userId);

        if (StringUtils.isBlank(userInfoDetail.getConsignee())) {
            output.setHasConsigneeInfo(PublicConstant.IF_YES);
            output.setConsignee(userInfoDetail.getConsignee());
            output.setConsigneeMobile(userInfoDetail.getConsigneeMobile());
            output.setConsigneeAddress(userInfoDetail.getConsigneeAddress());
        } else {
            output.setHasConsigneeInfo(PublicConstant.IF_NO);
        }
        return output;
    }

    /**
     * 获取银行名称
     *
     * @param bankCode 银行编码
     * @return 名称
     */
    private String getBankName(String bankCode) {
        List<OnlineBankInfo> onlineBanks = bankCache.getOnlineBank();
        if (CollectionUtils.isEmpty(onlineBanks)) {
            return "";
        }
        OnlineBankInfo onlineBankInfo1 = onlineBanks.stream().filter(onlineBankInfo -> Objects.equals(onlineBankInfo.getBankId(), bankCode)).findFirst().get();
        return onlineBankInfo1.getName();
    }
}

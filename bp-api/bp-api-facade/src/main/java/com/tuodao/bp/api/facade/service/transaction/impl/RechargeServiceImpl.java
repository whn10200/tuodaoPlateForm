package com.tuodao.bp.api.facade.service.transaction.impl;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.tuodao.bp.api.facade.client.transaction.AccountClient;
import com.tuodao.bp.api.facade.client.useraccount.UserDepositFegin;
import com.tuodao.bp.cache.basic.ConfigDictionaryCache;
import com.tuodao.bp.cache.constant.DictionaryConstants;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.traningcenter.input.RechargeFastCodeInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeFastInput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeConfirmOutput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeFastOutput;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.constant.common.UserConstant;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.StringUtil;
import com.tuodao.bp.utils.TimeUtils;
import com.tuodao.bp.utils.TransferUtil;
import com.tuodao.bp.api.facade.client.transaction.RechargeClient;
import com.tuodao.bp.api.facade.service.transaction.RechargeService;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.traningcenter.input.RechargeGatewayInput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;
import com.tuodao.bp.model.facade.traningcenter.input.FastRechargeParam;
import com.tuodao.bp.model.facade.traningcenter.input.FastRechargeSmsParam;
import com.tuodao.bp.model.facade.traningcenter.input.OnlineBankParam;
import com.tuodao.bp.model.facade.traningcenter.output.FastRechargeSmsVo;
import com.tuodao.bp.model.facade.traningcenter.output.FastRechargeVo;
import com.tuodao.bp.cache.basic.traningcenter.OnlineBankInfo;
import com.tuodao.bp.result.exception.MicroServiceException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author qingli.chen
 * @date 2017/10/16
 * @description
 */
@Service
public class RechargeServiceImpl implements RechargeService {

    private static final Logger logger = LoggerFactory.getLogger(RechargeServiceImpl.class);

    @Autowired
    BankCache bankCache;

    @Autowired
    UserAccountCache userAccountCache;

    @Autowired
    UserDepositFegin userDepositFegin;

    @Autowired
    RechargeClient rechargeClient;

    @Autowired
    ConfigDictionaryCache configDictionaryCache;

    @Autowired
    AccountClient accountClient;

    @Override
    public String onlineBankRecharge(OnlineBankParam onlineBankParam) {

        validateUserInfo(onlineBankParam.getUserId());

        UserDepositOutput userDepositOutput = getUserDeposit(onlineBankParam.getUserId());
        RechargeGatewayInput input = new RechargeGatewayInput();
        TransferUtil.transfer(onlineBankParam, input);
        input.setCardNo(userDepositOutput.getBankNum());

        //入库
        rechargeClient.online(input);

        return "";
    }

    @Override
    public FastRechargeSmsVo sendFastCode(FastRechargeSmsParam param) {
        validateUserInfo(param.getUserId());

        //获取用户绑定的卡
        BasePojo basePojo = new BasePojo();
        basePojo.setUserId(param.getUserId());
        UserDepositOutput userDepositOutput =  userDepositFegin.getUserDepositInfo(basePojo);

        if(userDepositOutput == null) {
            logger.error("fast recharge card is not found, userId:{}", param.getUserId());
            throw new MicroServiceException(RechargeConstant.BANK_IS_NOT_BIND);
        }

        FastRechargeSmsVo fastRechargeSmsVo = new FastRechargeSmsVo();

        RechargeFastCodeInput input = new RechargeFastCodeInput();
        TransferUtil.transfer(param, input);
        input.setBankCode(userDepositOutput.getBankCode());
        input.setRealName(userDepositOutput.getRealName());
        input.setCardId(userDepositOutput.getIdCard());
        input.setBankNum(userDepositOutput.getBankNum());

        //入库
        RechargeFastOutput output = rechargeClient.sendSmsCode(input);
        TransferUtil.transfer(output, fastRechargeSmsVo);
        return fastRechargeSmsVo;

    }

    @Override
    public FastRechargeVo rechargeInfo(String userId, String requestType) {
        FastRechargeVo fastRechargeVo = new FastRechargeVo();

        String beginTime = configDictionaryCache.getDictionaryName(DictionaryConstants.DEPOSIT_CLENT_BEGIN_TIME);
        String endTime = configDictionaryCache.getDictionaryName(DictionaryConstants.DEPOSIT_CLENT_END_TIME);
        if(StringUtils.isNotBlank(beginTime) && StringUtils.isNotBlank(endTime)) {
            if (TimeUtils.between(new Date(), TimeUtils.convertToDate(beginTime), TimeUtils.convertToDate(endTime))) {
                fastRechargeVo.setCleanTime(true);
            }
        }

        BasePojo basePojo = new BasePojo();
        basePojo.setUserId(userId);
        UserDepositOutput userDepositOutput =  userDepositFegin.getUserDepositInfo(basePojo);

        AccountOutput accountOutput = accountClient.getUserAccount(userId);
        if(accountOutput != null) {
            fastRechargeVo.setMoney(BigDecimalUtils.centToYuan(accountOutput.getBalance()));
        }

        if(userDepositOutput == null) {
            logger.error("fast recharge card is not found, userId:{}", userId);
            throw new MicroServiceException(RechargeConstant.DEPOSIT_NOT_OPEN);
        }
        Map<String, OnlineBankInfo> map = getBankCache();

        //验证快捷充值渠道是否存在
        OnlineBankInfo onlineBankInfo = map.get(userDepositOutput.getBankCode());

        fastRechargeVo.setAccount(StringUtil.hideBankCard(userDepositOutput.getBankNum()));
        fastRechargeVo.setBankName(userDepositOutput.getBankName());
        fastRechargeVo.setLimitOneTime(onlineBankInfo.getLimitOneTime());
        fastRechargeVo.setBankUrl(Objects.equals(AccessType.APP, requestType) ?
                onlineBankInfo.getAppUrl() : onlineBankInfo.getPcUrl());
        return fastRechargeVo;

    }

    @Override
    public RechargeConfirmOutput fastRecharge(FastRechargeParam param) {
        RechargeFastInput rechargeFastInput = new RechargeFastInput();
        TransferUtil.transfer(param, rechargeFastInput);
        return rechargeClient.fastRecharge(rechargeFastInput);
    }

    /**
     * 验证用户是否开通存管和绑定银行卡
     * @param userId 用户id
     */
    private void validateUserInfo(String userId) {


        String beginTime = configDictionaryCache.getDictionaryName(DictionaryConstants.DEPOSIT_CLENT_BEGIN_TIME);
        String endTime = configDictionaryCache.getDictionaryName(DictionaryConstants.DEPOSIT_CLENT_END_TIME);
        if(TimeUtils.between(new Date(), TimeUtils.convertToDate(beginTime), TimeUtils.convertToDate(endTime))) {
            logger.error("now is deposit clean time userId:{},", userId);
            throw new MicroServiceException(RechargeConstant.DEPOSIT_CLEAN_TIME);
        }
        UserAccountInfo user = userAccountCache.getUserAccoutInfo(userId);

        if(user.getIsOpenDeposit() != 1) {
            logger.error("user is not open deposit, userId:{}, userName:{}", user.getUserId(), user.getUserName());
            throw new MicroServiceException(RechargeConstant.DEPOSIT_NOT_OPEN);
        }

        if(user.getIsBindBank() == 0) {
            logger.error("user is not binging bank card, userId:{}, userName:{}", user.getUserId(), user.getUserName());
            throw new MicroServiceException(RechargeConstant.BANK_IS_NOT_BIND);
        }
    }

    /**
     * 获取用户绑定卡
     * @param userId
     * @return
     */
    private UserDepositOutput getUserDeposit(String userId) {
        BasePojo basePojo = new BasePojo();
        basePojo.setUserId(userId);
        UserDepositOutput userDepositOutput =  userDepositFegin.getUserDepositInfo(basePojo);

        if(userDepositOutput == null) {
            logger.error("fast recharge card is not found, userId:{}", userId);
            throw new MicroServiceException(RechargeConstant.BANK_IS_NOT_BIND);
        }
        return userDepositOutput;
    }

    /**
     * 获取银行
     * @return
     */
    private Map<String, OnlineBankInfo> getBankCache() {
        List<OnlineBankInfo> list = bankCache.getOnlineBank();
        if(CollectionUtils.isEmpty(list)) {
            logger.error("online bank list is null");
            throw new MicroServiceException(RechargeConstant.BANK_LIST_IS_NULL);
        }

        Map<String, OnlineBankInfo> map = Maps.uniqueIndex(list.iterator(), new Function<OnlineBankInfo, String>() {
            @Override
            public String apply(OnlineBankInfo onlineBankInfo) {
                return onlineBankInfo.getBankId();
            }
        });
        return map;
    }
}

package com.tuodao.bp.traningcenter.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;

import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.business.traningcenter.input.RechargeFastCodeInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeFastInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeGatewayInput;
import com.tuodao.bp.model.business.traningcenter.input.RechargeOrderInput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeConfirmOutput;
import com.tuodao.bp.model.business.traningcenter.output.RechargeFastOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.traningcenter.RechargeConstant;
import com.tuodao.bp.model.mq.GatewayRechargeMqInfo;
import com.tuodao.bp.cache.basic.traningcenter.OnlineBankInfo;
import com.tuodao.bp.model.mq.QuickRechargeApplyMqInfo;
import com.tuodao.bp.model.mq.QuickRechargeConfirmMqInfo;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.traningcenter.client.DepositoryClient;
import com.tuodao.bp.traningcenter.db.mapper.basic.AccountRechargeMapper;
import com.tuodao.bp.traningcenter.db.model.basic.AccountLog;
import com.tuodao.bp.traningcenter.db.model.basic.AccountRecharge;
import com.tuodao.bp.model.enums.*;
import com.tuodao.bp.traningcenter.db.model.basic.AccountRechargeExample;
import com.tuodao.bp.traningcenter.enums.*;
import com.tuodao.bp.traningcenter.enums.depository.FastRechargeStatus;
import com.tuodao.bp.traningcenter.mq.provider.RechargeProvider;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.traningcenter.service.AccountRechargeService;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author qingli.chen
 * @date 2017/9/12
 * @description
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class AccountRechargeServiceImpl implements AccountRechargeService {

    private static final Logger logger = LoggerFactory.getLogger(AccountRechargeServiceImpl.class);

    @Autowired
    private AccountRechargeMapper accountRechargeMapper;

    @Autowired
    AccountLogService accountLogService;

    @Autowired
    RechargeProvider mqRecharge;

    @Autowired
    UserAccountCache userAccountCache;

    @Autowired
    BankCache bankCache;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    DepositoryClient depositoryClient;

    @Autowired
    private GenerateService generateService;


    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void onlineBankRecharge(RechargeGatewayInput input) {
        Map<String, OnlineBankInfo> map = getBankCache();

        OnlineBankInfo onlineBankInfo = map.get(input.getBankId());

        String orderNo = "fs_recharge_gateway" + generateService.get();

        GatewayRechargeMqInfo request = new GatewayRechargeMqInfo();
        request.setAmt(input.getMoney());
        request.setBankCode(onlineBankInfo.getPaymentCode());
        request.setCardNo(input.getCardNo());
        request.setOrderNo(orderNo);
        request.setPayCode(onlineBankInfo.getPayment());

        //调用存管接口 GatewayRechargeRequest
        mqRecharge.sendGateRechargeToDepository(request);
        input.setType(onlineBankInfo.getPayment());
        input.setOrderNo(orderNo);

        save(input, ChannelType.getValue(input.getRequestType()),
                RechargeType.GATEWAY, RechargeChannel.getCode(input.getType()));
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RechargeFastOutput sendSmsCode(RechargeFastCodeInput input) {
        AccountRecharge accountRecharge = accountRechargeMapper.selectTopByUserId(input.getUserId());
        if(accountRecharge != null && TimeUtils.getDifSec(accountRecharge.getAddTime(), new Date()) < 60L) {
            logger.info("recharge to send sms code, must to be 60s, userId:{}", input.getUserId());
            throw new MicroServiceException(RechargeConstant.RECHARGE_TIME_ERROR);
        }

        Map<String, OnlineBankInfo> map = getBankCache();
        OnlineBankInfo onlineBankInfo = map.get(input.getBankCode());
        //验证快捷充值渠道是否存在
        if(onlineBankInfo == null) {
            logger.error("fast recharge channel is not found, userId:{}, bank_code:{}",
                    input.getUserId(), input.getBankCode());
            throw new MicroServiceException(RechargeConstant.CHANNEL_NOT_FOUND);
        }

        String orderNo = "fs_recharge_apply" + generateService.get();
        QuickRechargeApplyMqInfo request = new QuickRechargeApplyMqInfo();
        request.setOrderNo(orderNo);
        request.setAmt(input.getMoney());
        request.setName(input.getRealName());
        request.setMobile(input.getMobile());
        request.setIdCode(input.getCardId());
        request.setCardNo(input.getBankNum());
        request.setPlatCust(input.getUserId());

        //调用存管接口 QuickRechargeApplyRequest
        mqRecharge.sendSmsCodeToDepository(request);

        RechargeGatewayInput rechargeInput = new RechargeGatewayInput();
        rechargeInput.setOrderNo(orderNo);
        rechargeInput.setMoney(input.getMoney());
        rechargeInput.setUserId(input.getUserId());

        save(rechargeInput, ChannelType.getValue(input.getRequestType()),
                RechargeType.FAST, null);

        RechargeFastOutput output = new RechargeFastOutput();
        output.setOrderNo(orderNo);
        output.setPhone(input.getMobile());
        return output;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public RechargeConfirmOutput fastRecharge(RechargeFastInput input) {
        AccountRecharge accountRecharge = accountRechargeMapper.findByOrderNo(input.getOrderNo());
        if(accountRecharge == null) {
            logger.error("fast recharge order num is null, userId:{}, orderNo:{}", input.getUserId(), input.getOrderNo());
            throw new MicroServiceException(RechargeConstant.COMMIT_ORDER);
        }

        String orderNo = "fs_recharge_confirm" + generateService.get();
        QuickRechargeConfirmMqInfo request = new QuickRechargeConfirmMqInfo();
        request.setOrderNo(orderNo);
        request.setOriginOrderNo(accountRecharge.getOrderNo());
        request.setPayCode(RechargeChannel.getCode(accountRecharge.getChannel()).getCode());
        request.setIdentifyingCode(input.getSmsCode());
        request.setRemark("用户快捷充值");

        //调用存管接口 QuickRechargeApplyRequest
        HashMap<String, String> returnMap = depositoryClient.quickRechargeConfirm(request.toHashMap());
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<String> entity = new HttpEntity<String>(JSON.toJSONString(input), httpHeaders);
//        HashMap<String, String> returnMap = restTemplate
//                .postForEntity("http：//BIZ-DEPOSITORY/depository/rechargeService/quickRechargeConfirm",
//                        entity, HashMap.class).getBody();

        QuickRechargeConfirmMqInfo info = new QuickRechargeConfirmMqInfo();
        info.fromHashMap(returnMap);

        RechargeConfirmOutput rechargeConfirmOutput = new RechargeConfirmOutput();
        rechargeConfirmOutput.setMsg(info.getRemsg());
        rechargeConfirmOutput.setSuccess(info.isSuccess());
        rechargeConfirmOutput.setCode(new Integer(info.getRecode()));
        int status = 0;
        if(info.getOrderStatus() == FastRechargeStatus.APPLY.getValue()
                || info.getOrderStatus() == FastRechargeStatus.PROCESS.getValue()) {

        } else if(info.getOrderStatus() == FastRechargeStatus.FAILED.getValue()) {
            status = RechargeStatus.FAILED.getValue();
        } else if(info.getOrderStatus() == FastRechargeStatus.SUCCESS.getValue()) {
            status = RechargeStatus.SUCCESS.getValue();
            AccountLog accountLog = new AccountLog();
            accountLog.setOrderNo(info.getOrderNo());
            accountLog.setUserId(input.getUserId());
            accountLog.setType(AccountLogType.RECHARGE.getType());
            accountLog.setChangeType(AccountLogType.RECHARGE.getCode());
            accountLog.setAccount(accountRecharge.getAccount());
            accountLog.setRemarks("快捷充值");
            accountLog.setRecharge(accountRecharge.getAccount());
            accountLog.setToAccount(accountRecharge.getUserId());
            accountLogService.updateAccountAndLogPro(accountLog);
        }

        this.update(info.getOrderNo(), status);
        return rechargeConfirmOutput;
//        mqRecharge.sendFastRechargeToDepository(request);


    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void update(AccountRecharge accountRecharge) {
        accountRechargeMapper.updateByPrimaryKeySelective(accountRecharge);
    }

    @Override
    public AccountRecharge getByOrderNo(String orderNo) {
        AccountRecharge accountRecharge = accountRechargeMapper.findByOrderNo(orderNo);
        return accountRecharge;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void update(String orderNo, Integer status) {
        AccountRecharge accountRecharge = accountRechargeMapper.findByOrderNo(orderNo);
        if(accountRecharge == null) {
            logger.error("can not find recharge order, orderNo:{}", orderNo);
        }
        accountRecharge.setStatus(status);
        accountRecharge.setUpdateTime(new Date());
        accountRechargeMapper.updateByPrimaryKeySelective(accountRecharge);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void asyn(RechargeOrderInput input) {
        AccountRecharge accountRecharge = accountRechargeMapper.findByOrderNo(input.getOrderNo());
        if(accountRecharge.getStatus() != RechargeStatus.SUCCESS.getValue()) {
            accountRecharge.setStatus(input.getStatus());
            accountRecharge.setUpdateTime(input.getPayTime());


            accountRechargeMapper.updateByPrimaryKeySelective(accountRecharge);

            AccountLog accountLog = new AccountLog();
            accountLog.setUserId(accountRecharge.getUserId());
            accountLog.setAddTime(new Date());
            accountLog.setAccount(accountRecharge.getAccount());//
            accountLog.setType(AccountLogType.RECHARGE.getCode());
            accountLog.setIsShow(1);
            accountLog.setOrderNo(input.getOrderNo());
            accountLog.setRecharge(accountRecharge.getAccount());
            accountLog.setChangeType(AccountLogType.RECHARGE.getType());
            accountLog.setBalance(accountRecharge.getAccount());
            accountLog.setRemarks("充值了" + accountRecharge.getAccount() + "元");
            accountLog.setToRemarks("用户充值");
            accountLogService.updateAccountAndLogPro(accountLog);

            //第一次充值，增加积分
            AccountRechargeExample example = new AccountRechargeExample();
            example.createCriteria().andUserIdEqualTo(input.getUserId());
            List<AccountRecharge> list = accountRechargeMapper.selectByExample(example);
            if(input.getStatus() == RechargeStatus.SUCCESS.getValue() && list.size() == 1) {
                UserAccountInfo accountInfo = userAccountCache.getUserAccoutInfo(accountRecharge.getUserId());

                mqRecharge.sendToUserScore(accountInfo.getUserId(), accountInfo.getMobile());
            }
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    private void save(RechargeGatewayInput rechargeInput, ChannelType channelType,
                      RechargeType rechargeType, RechargeChannel rechargeChannel) {

        AccountRecharge accountRecharge = new AccountRecharge();
        accountRecharge.setUserId(rechargeInput.getUserId());
        if(rechargeType != null) {
            accountRecharge.setType(rechargeType.getValue());
        }
        accountRecharge.setStatus(RechargeStatus.WAIT.getValue());
        accountRecharge.setAccount(rechargeInput.getMoney());
        accountRecharge.setFee(BigDecimal.ZERO);
        if(channelType != null ) {
            accountRecharge.setSource(channelType.getValue());
        }
        if(rechargeChannel != null) {
            accountRecharge.setChannel(rechargeChannel.getValue());
        }
        accountRecharge.setAddTime(new Date());
        accountRecharge.setRemarks("网关充值");

        accountRecharge.setOrderNo(rechargeInput.getOrderNo());

        accountRechargeMapper.insertSelective(accountRecharge);
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

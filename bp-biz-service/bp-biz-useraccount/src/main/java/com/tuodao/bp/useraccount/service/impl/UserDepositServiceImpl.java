package com.tuodao.bp.useraccount.service.impl;

import com.alibaba.fastjson.JSON;
import com.tuodao.bp.activemq.constant.DepositoryMqConstant;
import com.tuodao.bp.cache.basic.AccountBankCache;
import com.tuodao.bp.cache.basic.traningcenter.BankCache;
import com.tuodao.bp.cache.basic.traningcenter.OnlineBankInfo;
import com.tuodao.bp.db.model.basic.AccountBank;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositCallbackInput;
import com.tuodao.bp.model.business.useraccount.input.OpenDepositInput;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.facade.operation.input.FindBankInfoInput;
import com.tuodao.bp.model.facade.operation.output.FindBankInfoOutput;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.*;
import com.tuodao.bp.useraccount.persistence.model.basic.*;
import com.tuodao.bp.useraccount.service.IUserDepositService;
import com.tuodao.bp.useraccount.service.UserBaseService;
import com.tuodao.bp.utils.CommonUtils;
import com.tuodao.bp.utils.IdCardUtils;
import com.tuodao.bp.utils.MD5Utils;
import com.tuodao.bp.utils.TimeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Description: 用户存管信息
 * User: zkai
 * Date: 2017/10/16
 * Time: 16:35
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class UserDepositServiceImpl extends UserBaseService implements IUserDepositService {
    private final static Logger logger = LoggerFactory.getLogger(UserDepositServiceImpl.class);

    @Resource
    private UserDepositInfoMapper userDepositInfoMapper;

    @Resource
    private UserInfoDetailMapper userInfoDetailMapper;

    @Resource
    private AccountFinanceMapper accountFinanceMapper;

    @Resource
    private UserDepositResultMapper userDepositResultMapper;

    @Resource
    private UserDepositInfoHistoryMapper userDepositInfoHistoryMapper;

    @Resource
    private BankCache bankCache;

    @Resource
    private AccountBankCache accountBankCache;

    @Autowired
    private JmsMessagingTemplate jmsTemplate;

    @Resource(name = "deInUserRegist4Ele")
    private Queue deInUserRegist4Ele;

    private static  Map<String,AccountBank> accountBankMap = new HashMap<String,AccountBank>();

    @Override
    public void openDeposit(OpenDepositInput input) {
        logger.info("user open deposit,openDepositInput ={}", input);
        // 判断是否是存管结算时间
        Date nowTime  = new Date();
        Date beginTime = TimeUtils.format(TimeUtils.format(nowTime,"yyyy-MM-dd")+" 23:55:00","yyyy-MM-dd HH:mm:ss");
        Date endTime = TimeUtils.format(TimeUtils.format(TimeUtils.addDay(nowTime,1),"yyyy-MM-dd")+" 00:05:59","yyyy-MM-dd HH:mm:ss");
        boolean isSettlementTime = TimeUtils.between(nowTime,beginTime,endTime);
        if(isSettlementTime){
            throw new BizFeignException(UaRespExceptionConstant.STORAGE_SYSTEM_SETTLEMENT_TIME);
        }

        //判读是否已开通
        UserAccountInfo userAccountInfo = super.getUserAccountInfo(input.getUserId());
        if (PublicConstant.IF_YES == userAccountInfo.getIsOpenDeposit()) {
            // 用户已开通存管
            throw new BizFeignException(UaRespExceptionConstant.USER_HAS_OPENED_DEPOSIT);
        }
        if (PublicConstant.IF_ING == userAccountInfo.getIsOpenDeposit()) {
            // 存管开通中,请耐心等待
            throw new BizFeignException(UaRespExceptionConstant.OPENED_DEPOSIT_ING);
        }
        // 修改业务数据库
        doOpenDepositBiz(input);

        //调用激活存管 mq
        HashMap<String,Object> head = new HashMap<String,Object>();
        head.put(DepositoryMqConstant.DEPOSITORY_HEAD_KEY,DepositoryMqConstant.DE_SELECTOR_VALUE_USER_REGIST_4_ELE);
        jmsTemplate.convertAndSend(deInUserRegist4Ele, input.toHashMap(),head);
        logger.info("调用激活存管MQ,input:[{}]",input.toString());
    }

    /**
     * 开通存管回调
     *
     * @param input
     */
    @Override
    @Transactional
    public void openDepositCallback(OpenDepositCallbackInput input) {
        logger.info("开通存管的回调信息={}", JSON.toJSONString(input));

        // 修改用户信息
        UserInfo userInfo = new UserInfo();
        if (input.isSuccess()) {
            // 开通存管成功
            userInfo.setIsOpenDeposit(PublicConstant.IF_YES);
            userInfo.setIsBindBank(PublicConstant.IF_YES);
            // 修改存管信息
            updateAccountFinace(input.getUserId(), input.getDepositNo());
        } else {
            // 开通存管失败
            userInfo.setIsOpenDeposit(PublicConstant.IF_ERROR);
            userInfo.setIsBindBank(PublicConstant.IF_ERROR);
        }
        userInfo.setUserId(input.getUserId());
        updateUserInfo(userInfo);

        insertUserDepositResult(input);

        // TODO 消息推送
    }

    /**
     * 获取用户存管信息
     *
     * @param userId 用户Id
     * @return 存管信息
     */
    @Override
    public UserDepositOutput getUserDepositInfo(String userId) {
        UserAccountInfo userAccountInfo = super.getUserAccountInfo(userId);
        UserDepositInfo userDepositInfo = userDepositInfoMapper.selectByPrimaryKey(userId);
        if (userAccountInfo.getIsOpenDeposit() == PublicConstant.IF_NO ||
                null == userDepositInfo || userDepositInfo.getIsDel() == PublicConstant.DEL_YES) {
            throw new BizFeignException(UaRespExceptionConstant.USER_NOT_OPEN_DEPOSIT);
        }
        UserDepositOutput output = new UserDepositOutput();
        BeanUtils.copyProperties(userDepositInfo, output);
        List<OnlineBankInfo> onlineBankInfoList = bankCache.getOnlineBank();
        if (!CollectionUtils.isEmpty(onlineBankInfoList)) {
            List<OnlineBankInfo> collect = onlineBankInfoList.stream().
                    filter(onlineBankInfo -> Objects.equals(onlineBankInfo.getPaymentCode(), output.getBankCode())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(collect)) {
                output.setBankName(collect.get(0).getName());
            }
        }
        output.setDepositNo(userAccountInfo.getDepositNo());
        return output;
    }

    /**
     * 根据银行卡号，获取银行信息
     * @param input
     * @return
     */
    @Override
    public List<FindBankInfoOutput> findBanksByBankIds(FindBankInfoInput input) {
        getAccountBankMap();
        List<FindBankInfoOutput> outputs = new ArrayList<FindBankInfoOutput>();
        UserDepositInfoHistoryExample userDepositInfoHistoryExample = new UserDepositInfoHistoryExample();
        List<String> bankIds = input.getBankIds();
        for (int i = 0; i < bankIds.size(); i++) {
            FindBankInfoOutput output = new FindBankInfoOutput();
            userDepositInfoHistoryExample.clear();
            userDepositInfoHistoryExample.createCriteria()
                    .andBankNumEqualTo(bankIds.get(i))
                    .andUserIdEqualTo(input.getUserId());
            List<UserDepositInfoHistory> userDepositInfoHistories = userDepositInfoHistoryMapper.selectByExample(userDepositInfoHistoryExample);
            output.setBankCardId(bankIds.get(i));
            if(userDepositInfoHistories == null || userDepositInfoHistories.size() == 0 ){
                output.setRemark("此银行卡号没有绑定存管");
                outputs.add(output);
                continue;
            }
            output.setBankCode(userDepositInfoHistories.get(0).getBankCode());
            String bankName = null;
            try {
                bankName =  accountBankMap.get(userDepositInfoHistories.get(0).getBankCode()).getName();
            }catch (Exception e){
                logger.error("银行编号={},不存在",userDepositInfoHistories.get(0).getBankCode());
                output.setRemark("银行编号="+userDepositInfoHistories.get(0).getBankCode()+",在系统内部银行表不存在");
            }
            output.setBankName(bankName);
            outputs.add(output);
        }
        return outputs;
    }

    @Transactional
    private void doOpenDepositBiz(OpenDepositInput openDepositInput) {
        // 插入用户存管银行卡历史信息表
        UserDepositInfoHistory depositInfoHistory = new UserDepositInfoHistory();
        BeanUtils.copyProperties(openDepositInput, depositInfoHistory);
        insertUserDepositInfoHistory(depositInfoHistory);

        // 插入用户存管银行卡信息表
        UserDepositInfo depositInfo = new UserDepositInfo();
        BeanUtils.copyProperties(openDepositInput, depositInfo);
        insertUserDepositInfo(depositInfo);

        //update 用户信息（bp_user_info）
        UserInfo userInfo = new UserInfo();
        // 设置支付密码
        String payPwdKey = CommonUtils.generateVerifyCode(6);
        userInfo.setPayPwdKey(payPwdKey);
        userInfo.setPayPassword(generatePw(openDepositInput.getPayPassword(), payPwdKey));

        userInfo.setUserId(openDepositInput.getUserId());
        userInfo.setUserName(openDepositInput.getRealName());
        userInfo.setIsOpenDeposit(PublicConstant.IF_ING);
        userInfo.setIsBindBank(PublicConstant.IF_ING);
        userInfo.setGmtModifier(openDepositInput.getPayPassword());
        updateUserInfo(userInfo);

        //update 用户信息详情(user_info_detail）
        updateUserInfoDetail(openDepositInput);
    }

    /**
     * 插入用户存管银行卡历史记录biao
     */
    private void insertUserDepositInfoHistory(UserDepositInfoHistory depositInfoHistory) {
        // 先将先前历史的存管记录修改为弃用
        UserDepositInfoHistoryExample userDepositInfoHistoryExample = new UserDepositInfoHistoryExample();
        userDepositInfoHistoryExample.createCriteria()
                .andIsDelEqualTo(PublicConstant.DEL_NO)
                .andUserIdEqualTo(depositInfoHistory.getUserId());
        UserDepositInfoHistory userDepositInfoHistoryUpdate = new UserDepositInfoHistory();
        userDepositInfoHistoryUpdate.setIsDel(PublicConstant.DEL_YES);
        userDepositInfoHistoryMapper.updateByExampleSelective(userDepositInfoHistoryUpdate,userDepositInfoHistoryExample);

        // 插入次新记录
        userDepositInfoHistoryMapper.insertSelective(depositInfoHistory);
    }

    /**
     * 插入用户存管银行卡信息表
     *
     * @param depositInfo
     */
    private void insertUserDepositInfo(UserDepositInfo depositInfo) {
        // 查找用户存管银行卡信息表
        UserDepositInfo userDepositInfo = userDepositInfoMapper.selectByPrimaryKey(depositInfo.getUserId());
        if (userDepositInfo == null) {
            // 查找用户存管银行卡信息,如果不存在，插入数据
            int insertResult = userDepositInfoMapper.insertSelective(depositInfo);
            if (insertResult != 1) {
                logger.error("插入用户存管银行卡信息 depositInfo={}", JSON.toJSONString(depositInfo));
                throw new BizFeignException(UaRespExceptionConstant.UPDATE_ERROR);
            }
            return;
        }

        int updateResult = userDepositInfoMapper.updateByPrimaryKeySelective(depositInfo);
        if (updateResult != 1) {
            logger.error("更新用户存管银行卡信息 UserInfoDetail={}", JSON.toJSONString(depositInfo));
            throw new BizFeignException(UaRespExceptionConstant.UPDATE_ERROR);
        }
    }

    /**
     * 更新用户信息
     *
     * @param openDepositInput
     */
    private void updateUserInfoDetail(OpenDepositInput openDepositInput) {
        String idCard = openDepositInput.getIdCard();
        UserInfoDetail detail = new UserInfoDetail();
        detail.setUserId(openDepositInput.getUserId());
        detail.setSex(IdCardUtils.getSex(idCard));
        detail.setBirthday(IdCardUtils.getBirthday(idCard));//
        detail.setConstellation(IdCardUtils.getConstellation(idCard));
        detail.setGmtModifier(openDepositInput.getGmtCreator());

        int insertResult = userInfoDetailMapper.updateByPrimaryKeySelective(detail);
        if (insertResult != 1) {
            logger.error("更新用户信息异常 UserInfoDetail={}", JSON.toJSONString(detail));
            throw new BizFeignException(UaRespExceptionConstant.UPDATE_ERROR);
        }
    }

    /**
     * 修改用户信息
     *
     * @param userInfo 用户信息
     */
    private void updateUserInfo(UserInfo userInfo) {
        if (StringUtils.isBlank(userInfo.getUserId())) {
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_USER_ID_CAN_NOT_BE_NULL);
        }
        int insertResult = userInfoMapper.updateByPrimaryKeySelective(userInfo);
        if (insertResult != 1) {
            logger.error("修改用户信息异常 userInfo={}", JSON.toJSONString(userInfo));
            throw new BizFeignException(UaRespExceptionConstant.UPDATE_ERROR);
        }

    }

    /**
     * 生成password
     *
     * @param password 密码
     * @param key      密钥
     * @return
     */
    private String generatePw(String password, String key) {
        return MD5Utils.md5(password + key);
    }

    /**
     * 修改金融资金账户信息
     *
     * @param userId    用户编号
     * @param depositNo 存管编号
     */
    private void updateAccountFinace(String userId, String depositNo) {
        AccountFinance accountFinance = new AccountFinance();
        accountFinance.setUserId(userId);
        accountFinance.setDepositNo(depositNo);
        int updateResult = accountFinanceMapper.updateByPrimaryKeySelective(accountFinance);
        if (updateResult != 1) {
            logger.error("修改金融资金账户信息异常 accountFinance={}", JSON.toJSONString(accountFinance));
            throw new BizFeignException(UaRespExceptionConstant.UPDATE_ERROR);
        }
    }

    /**
     * 插入 用户存管开通结果信息
     *
     * @param input 开通存管返回结果
     */
    @Async
    private void insertUserDepositResult(OpenDepositCallbackInput input) {
        UserDepositResult userDepositResult = new UserDepositResult();
        userDepositResult.setUserId(input.getUserId());
        userDepositResult.setDepositNo(input.getDepositNo());
        userDepositResult.setPlatNo(input.getPlatNo());
        userDepositResult.setOrderNo(input.getOrderNo());
        userDepositResult.setCardNo(input.getCardNo());
        userDepositResult.setPreMobile(input.getPreMobile());
        if (input.isSuccess()) {
            userDepositResult.setIsSuccess("1");
        } else {
            // 开通存管失败
            userDepositResult.setIsSuccess("2");
        }
        userDepositResult.setRemark(input.getRemark());
        int insertResult = userDepositResultMapper.insertSelective(userDepositResult);
        if (insertResult != 1) {
            logger.error(" 插入用户存管开通结果信息异常 userDepositResult={}", JSON.toJSONString(userDepositResult));
            throw new BizFeignException(UaRespExceptionConstant.ADD_ERROR);
        }
    }

    /**
     * 初始化系统内部银行表
     * * @return
     */
    private Map<String,AccountBank>  getAccountBankMap(){
        if(accountBankMap == null || accountBankMap.size() == 0){
            List<AccountBank> accountBanks = accountBankCache.getAccountBank();
            for (AccountBank accountBank: accountBanks ) {
                accountBankMap.put(accountBank.getNid(),accountBank);
            }
        }
        return accountBankMap;
    }


}

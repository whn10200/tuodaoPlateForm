 package com.tuodao.bp.traningcenter.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.constant.traningcenter.CashConstant;
import com.tuodao.bp.model.facade.traningcenter.input.CashListParam;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.model.facade.traningcenter.output.AccountCashVo;
import com.tuodao.bp.model.traningcenter.input.AccountCashApplyInput;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.db.mapper.basic.AccountCashMapper;
import com.tuodao.bp.traningcenter.db.model.basic.AccountCash;
import com.tuodao.bp.traningcenter.service.AccountCashService;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


 /**
  * demo service 实现
  * @author 王艳兵
  * @author wuzf
  *
  * @created 2017年8月30日
  *
  * @since 1.0.0
  */
 @Service("accountCashService")
 public class AccountCashServiceImpl implements AccountCashService {


     private static final Logger LOGGER = LoggerFactory.getLogger(AccountCashServiceImpl.class);

     @Autowired
     private AccountCashMapper accountCashMapper;

     @Autowired
     private AccountLogService accountLogService;

     @Autowired
     private ProducerMq producerMQ;

     @Override
     public double getCashTotalByUserId(String userId) {
         return accountCashMapper.getTotalCashByUserId(userId).doubleValue();
     }

     @Override
     @Transactional(rollbackFor = Exception.class)
     public void addAccountCash(AccountCashApplyInput input) {
         AccountCash accountCash = new AccountCash();
         BeanUtils.copyProperties(input,accountCash);
         accountCash.setAddTime(new Date());
         //默认提现申请中
         accountCash.setStatus(CashConstant.CASH_STATUS_APPLY);
         accountCash.setRemarks("申请提现" + BigDecimalUtils.centToYuanFormat(input.getAccount()) + "元");
         accountCash.setFeeAccount(input.getBalanceCash());

         accountCashMapper.insertSelective(accountCash);
         accountLogService.insertAccountCashApplyLog(accountCash);
     }

     @Override
     @Transactional(rollbackFor = Exception.class)
     public void cashBankRequest(AccountCashApplyInput input) {
         this.addAccountCash(input);
         if (input.getUseFree()  == CashConstant.USE_FREE){
             producerMQ.updateUserCashNum(input.getUserId(),-1);
         }
         producerMQ.cashApplyBankRequest(input);
     }


     @Override
     @Transactional(rollbackFor = Exception.class)
     public void updateAccountCash(String orderNo,Integer status) {
         AccountCash accountCash = accountCashMapper.getByOrderNo(orderNo);
         if(accountCash == null){
             throw new BizFeignException(TransactError.CASH_ORDER_NOT_FOUND);
         }
         if(accountCash.getStatus() != CashConstant.CASH_STATUS_APPLY){
             LOGGER.error("该笔提现状态已更新,订单号:{},订单状态:{}",orderNo,accountCash.getStatus());
             return;
         }
         this.updateAccountCashStatus(accountCash,status);

         //提现成功
         if(status == CashConstant.CASH_STATUS_SUCCESS){
             String successRemarks = "提现" + BigDecimalUtils.centToYuanFormat(accountCash.getRealAccount()) + "元成功";
             accountLogService.insertAccountCashSuccessLog(accountCash,successRemarks);
             //手续费不为0
             if(accountCash.getFee().doubleValue() > 0){
                 String feeRemarks = "提现手续费" + BigDecimalUtils.centToYuanFormat(accountCash.getFee()) + "元";
                 accountLogService.insertAccountCashFeeLog(accountCash,feeRemarks);
             }
         }else{
             //提现失败
             accountLogService.insertAccountCashFailLog(accountCash);
             //使用了免费提现次数 需要回滚
             if(accountCash.getUseFree() == CashConstant.USE_FREE ){
                 producerMQ.updateUserCashNum(accountCash.getUserId(),1);
             }
         }
     }

     @Override
     public PageInfo<AccountCashVo> getUserCashListByPage(CashListParam param) {
         AccountCash cash = new AccountCash();
         cash.setUserId(param.getUserId());
         PageHelper.startPage(param.getCurrentPage(),param.getPageSize());
         List<AccountCash> list = accountCashMapper.getList(cash);

         PageInfo<AccountCash> pageInfo = new PageInfo<>(list);

         List<AccountCashVo>  cashList = new ArrayList<>();

         if(pageInfo.getList() != null && pageInfo.getList().size() > 0){
             AccountCashVo vo;
             for(AccountCash accountCash: pageInfo.getList()){
                vo = new AccountCashVo();
                vo.setBankNum(accountCash.getBankNum());
                 //提现申请金额 元->分
                vo.setCashAccount(BigDecimalUtils.centToYuanFormat(accountCash.getAccount().doubleValue()));
                vo.setCashTime(DateUtil.formatLong(accountCash.getAddTime()));
                vo.setRealAccount(BigDecimalUtils.centToYuanFormat(accountCash.getRealAccount().doubleValue()));
                vo.setFee(BigDecimalUtils.centToYuanFormat(accountCash.getFee().doubleValue()));
                cashList.add(vo);
             }
         }
         PageInfo<AccountCashVo> result = new PageInfo<>();
         result.setTotal(pageInfo.getTotal());
         result.setList(cashList);

         return result;
     }


     /**
      * 更新提现订单状态
      * @param cash    提现信息
      * @param orderStatus 提现状态
      */
     private void updateAccountCashStatus(AccountCash cash,int orderStatus){
         cash.setStatus(orderStatus);
         cash.setUpdateTime(new Date());
         cash.setVerifyRemarks("提现自动审核");
         //默认实际到账金额:提现金额-手续费
         cash.setRealAccount(cash.getAccount().subtract(cash.getFee()));
         int sum = accountCashMapper.updateAccountCashStatus(cash);
         if(sum != 1){
            throw new BizFeignException(TransactError.UPDATE_CASH_ERROR);
         }
     }

 }

 package com.tuodao.bp.traningcenter.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.ChoicenessTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.SelectTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderTraRecordInput;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.constant.common.UserConstant;
import com.tuodao.bp.model.constant.product.ProductConstant;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.enums.ChannelType;
import com.tuodao.bp.model.mq.TenderAccountMqInfo;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.client.UserClient;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowChoicenessTenderMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowCollectionMapper;
import com.tuodao.bp.traningcenter.db.mapper.basic.BorrowTenderMapper;
import com.tuodao.bp.traningcenter.db.model.basic.*;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.traningcenter.mq.provider.ProducerMq;
import com.tuodao.bp.traningcenter.service.AccountLogService;
import com.tuodao.bp.traningcenter.service.BorrowChicenessTenderService;
import com.tuodao.bp.traningcenter.until.BorrowUtils;
import com.tuodao.bp.utils.BigDecimalUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

 /**
  * @description:理财计划加入相关service
  * @author: wuzf
  * @date: 2017/9/8 0008.
  * @time: 14:00
  * @copyright: 拓道金服 Copyright (c) 2017
  */
 @Transactional
 @Service
 public class BorrowChicenessTenderServiceImpl implements BorrowChicenessTenderService {

     /** 日志 */
     private static final Logger logger = LoggerFactory.getLogger(BorrowChicenessTenderServiceImpl.class);

     @Resource
	 private BorrowChoicenessTenderMapper borrowChoicenessTenderMapper;

     @Resource
     private AccountLogService accountLogService;

     @Resource
     private BorrowTenderMapper borrowTenderMapper;

     @Resource
     private BorrowCollectionMapper borrowCollectionMapper;

     @Resource
     private ProducerMq producerMq;

     @Resource
     private UserClient userClient;

     @Resource
     private ScoreTaskCache scoreTaskCache;

	 @Override
	 public BigDecimal insertChoiceness(ChoicenessTenderInput choicenessTenderInput, String ip){

         //插入加入记录
         logger.info("choiceness insert ,input = {}", choicenessTenderInput);
         BorrowChoicenessTender borrowChoicenessTender = new BorrowChoicenessTender();
         BeanUtils.copyProperties(choicenessTenderInput, borrowChoicenessTender);
         BigDecimal preAccountIntrest=new BigDecimal(0);
         switch (choicenessTenderInput.getRecoverType()) {
             case 0:
                 preAccountIntrest= choicenessTenderInput.getPreAccount().multiply(choicenessTenderInput.getAllApr().divide(new BigDecimal(100))).divide(new BigDecimal(12)).multiply(new BigDecimal(choicenessTenderInput.getPeriod())).setScale(BigDecimal.ROUND_HALF_UP);
                 break;
             case 1:
                 preAccountIntrest= BorrowUtils.getPlan(choicenessTenderInput.getPreAccount().doubleValue(),choicenessTenderInput.getAllApr().doubleValue(),
                         choicenessTenderInput.getPeriod(),choicenessTenderInput.getPeriod());
                 break;
             case 2:
                 preAccountIntrest= choicenessTenderInput.getPreAccount().multiply(choicenessTenderInput.getAllApr().divide(new BigDecimal(100))).divide(new BigDecimal(365)).multiply(new BigDecimal(choicenessTenderInput.getPeriod())).setScale(BigDecimal.ROUND_HALF_UP);
                 break;
             default:
                 //抛出错误
                 break;
         }
         borrowChoicenessTender.setAccountInterest(preAccountIntrest);
         borrowChoicenessTender.setPlatformInterest(preAccountIntrest.multiply(choicenessTenderInput.getAwardApr()).divide(choicenessTenderInput.getAllApr()).setScale(BigDecimal.ROUND_HALF_UP));
         borrowChoicenessTender.setVoucherInterest(preAccountIntrest.multiply(choicenessTenderInput.getVoucherApr()).divide(choicenessTenderInput.getAllApr()).setScale(BigDecimal.ROUND_HALF_UP));
         borrowChoicenessTender.setAccountInterest(new BigDecimal(0));
         borrowChoicenessTender.setPlatformInterest(new BigDecimal(0));
         borrowChoicenessTender.setVoucherInterest(new BigDecimal(0));
         borrowChoicenessTender.setAddIp(ip);
         borrowChoicenessTender.setStatus(0);
         borrowChoicenessTender.setAccount(new BigDecimal(0));
         borrowChoicenessTender.setAddTime(new Date());
         borrowChoicenessTenderMapper.insert(borrowChoicenessTender);
         //资金修改
         AccountLog accountLog = new AccountLog();

         accountLog.setUserId(choicenessTenderInput.getUserId());
         accountLog.setType(AccountLogType.PLAN_TENDER_FROST.getCode());
         accountLog.setAccount(choicenessTenderInput.getPreAccount());
         accountLog.setTotal(new BigDecimal(0));
         accountLog.setCashFrost(new BigDecimal(0));
         accountLog.setTenderFrost(choicenessTenderInput.getPreAccount());
         accountLog.setBalance(choicenessTenderInput.getPreAccount().multiply(new BigDecimal(-1)));
         accountLog.setRecharge(new BigDecimal(0));
         accountLog.setAwaitInterest(new BigDecimal(0));
         accountLog.setAwaitCapital(new BigDecimal(0));
         accountLog.setFromAccount(choicenessTenderInput.getUserId());
         accountLog.setToAccount(choicenessTenderInput.getUserId());
         accountLog.setToRemarks(AccountLogType.PLAN_TENDER_FROST.getMsg());
         accountLog.setIsShow(1);
         if(choicenessTenderInput.getDkamount()!=null && !"".equals(choicenessTenderInput.getDkamount()))
         {
             accountLog.setIntrestAccount(new BigDecimal(choicenessTenderInput.getDkamount()).subtract(new BigDecimal(100)));
         }
         else
         {
             accountLog.setIntrestAccount(new BigDecimal(0));
         }
         accountLog.setFeeAccount(new BigDecimal(0));
         accountLog.setChangeType(2);
         accountLogService.updateAccountAndLogPro(accountLog);
         return  preAccountIntrest;
     }

     @Override
     public int updateChoiceness(ChoicenessTenderInput choicenessTenderInput) {
         logger.info("choiceness update ,input = {}", choicenessTenderInput);
         BorrowChoicenessTender borrowChoicenessTender = new BorrowChoicenessTender();
         BeanUtils.copyProperties(choicenessTenderInput, borrowChoicenessTender);
         borrowChoicenessTender.setUpdateTime(new Date());
         return borrowChoicenessTenderMapper.updateByPrimaryKey(borrowChoicenessTender);
     }



     @Override
     public List<SelectNoMateOutput> selectNoMate() {
         logger.info("choiceness selectNoMate");
         return borrowChoicenessTenderMapper.selectNoMate();
     }


     @Override
     public PageInfo<TenderRecord> getJoinLists(TenderTraRecordInput tenderTrRecordInput) {
         logger.info("choiceness getJoinLists  ,input = {}", tenderTrRecordInput);
         PageHelper.startPage(tenderTrRecordInput.getCurrentPage(), tenderTrRecordInput.getPageSize());
         //查询加入记录
         List<TenderRecord> list =borrowChoicenessTenderMapper.selectTenderListByBorrowId(tenderTrRecordInput.getBorrowId());
         PageInfo<TenderRecord> pageInfo = new PageInfo<>(list);
         pageInfo.setList(list);
         Page<TenderRecord> page = (Page<TenderRecord>)list;
         pageInfo.setTotal(page.getTotal());
         logger.info("choiceness selectTenderByBorrowId end");
         return pageInfo;
     }


     @Override
     public TenderTraRecordOutput getMaxAndLast(TenderTraRecordInput tenderTrRecordInput) {
        // 最后一笔
         TenderRecord picker = borrowChoicenessTenderMapper.selectLastTenderByBorrowId(tenderTrRecordInput.getBorrowId());
        //最多一笔
         TenderRecord maxEr = borrowChoicenessTenderMapper.selectMaxTenderByBorrowId(tenderTrRecordInput.getBorrowId());
         TenderTraRecordOutput tenderTrRecordOutput = new TenderTraRecordOutput();
         tenderTrRecordOutput.setPicker(picker.getTenderUser());
         tenderTrRecordOutput.setMaxEr(maxEr.getTenderUser());
         return tenderTrRecordOutput;
     }

     @Override
     public void updatePlanCollection(BorrowCollection collection, Integer choicenessTenderId) {
         BorrowChoicenessTender borrowChoicenessTender = borrowChoicenessTenderMapper.selectByPrimaryKey(choicenessTenderId);
         if(borrowChoicenessTender != null && borrowChoicenessTender.getBeforeTenderId() != null && borrowChoicenessTender.getBeforeTenderId() > 0){
             borrowChoicenessTender = borrowChoicenessTenderMapper.selectByPrimaryKey(borrowChoicenessTender.getBeforeTenderId());
         }
         if(borrowChoicenessTender == null){
             logger.error("精选计划投标信息未查询到,choicenessTenderId:{}",choicenessTenderId);
             throw new BizFeignException(TransactError.PLAN_TENDER_NOT_FOUND);
         }

         borrowChoicenessTender.setAccountInterest(borrowChoicenessTender.getAccountInterest().add(collection.getInterest()));
         borrowChoicenessTenderMapper.updateByPrimaryKeySelective(borrowChoicenessTender);

         //TODO 更新精选计划资金池
     }

     @Override
     public void maxAndLastTender(BorrowChoicenessTender max, BorrowChoicenessTender last, ProductOutput product) {
         //最多投标和最大投标为同一条记录
         if(max != null
                 && last != null
                 && max.getId().intValue() == last.getId().intValue()){
             int baseScore = getBaseScore(max, product);
             if(baseScore > 0){
                 producerMq.updateUserScore(PublicConstant.TASK_ID_MOST_AND_ROUND_OFF,max.getUserId(),max.getMobile(),max.getChannel()+"",product.getIntegralFold() * baseScore * 3);
             }
         }
     }

     @Override
     public BorrowChoicenessTender lastTender(ProductOutput product) {
         BorrowChoicenessTender lastBorrowTender = borrowChoicenessTenderMapper.selectLast(product.getId());
         if(lastBorrowTender != null){
             int baseScore = getBaseScore(lastBorrowTender, product);
             if(baseScore > 0){
                 producerMq.updateUserScore(PublicConstant.TASK_ID_ROUND_OFF,lastBorrowTender.getUserId(),lastBorrowTender.getMobile(),lastBorrowTender.getChannel()+"",product.getIntegralFold() * baseScore * 2);
             }
         }
         return lastBorrowTender;
     }

     @Override
     public BorrowChoicenessTender maxTender(ProductOutput product) {
         BorrowChoicenessTender maxBorrowTender = borrowChoicenessTenderMapper.selectMax(product.getId());
         if(maxBorrowTender != null){
             int baseScore = getBaseScore(maxBorrowTender, product);
             if(baseScore > 0){
                 producerMq.updateUserScore(PublicConstant.TASK_ID_MOST_INVEST,maxBorrowTender.getUserId(),maxBorrowTender.getMobile(),maxBorrowTender.getChannel()+"",product.getIntegralFold() * baseScore * 2);
             }
         }
         return maxBorrowTender;
     }

     @Override
     public int getBaseScore(BorrowChoicenessTender tender, ProductOutput product) {
         int baseScore = 0;
         if(product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_DAY){
             baseScore = (int)(BigDecimalUtils.centToYuan(tender.getPreAccount().doubleValue()) / 5000);
         }else if(product.getProductPeriod() >= TenderConstant.SCORE_AWARD_MONTH
                 && product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH){
             baseScore = (int)(BigDecimalUtils.centToYuan(tender.getPreAccount().doubleValue()) / 1000);
         }else if(product.getProductPeriod() < TenderConstant.SCORE_AWARD_MONTH
                 && product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH){
             baseScore = (int)(BigDecimalUtils.centToYuan(tender.getPreAccount().doubleValue()) / 2000);
         }
         return baseScore;
     }

     @Override
     public void dayTender(BorrowChoicenessTender tender, ProductOutput product) {
         int baseScore = getBaseScore(tender, product);
         if(baseScore > 0){
             producerMq.updateUserScore(PublicConstant.TASK_ID_INVEST_DAILY,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",product.getIntegralFold() * baseScore);
         }
     }

     @Override
     public void above12MonthTender(BorrowChoicenessTender tender, ProductOutput product) {
         int baseScore = getBaseScore(tender, product);
         if(baseScore > 0 && product.getProductPeriod() >= TenderConstant.SCORE_AWARD_MONTH
                 && product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH){
             producerMq.updateUserScore(PublicConstant.TASK_ID_INVEST_ABOVE_12_MONTH,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",product.getIntegralFold() * baseScore);
         }
     }

     @Override
     public void under12MonthTender(BorrowChoicenessTender tender, ProductOutput product) {
         int baseScore = getBaseScore(tender, product);
         if(baseScore > 0 && product.getProductPeriod() < TenderConstant.SCORE_AWARD_MONTH
                 && product.getPeriodUnit() == ProductConstant.PERIOD_UNIT_MONTH){
             producerMq.updateUserScore(PublicConstant.TASK_ID_INVEST_UNDER_12_MONTH,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",product.getIntegralFold() * baseScore);
         }
     }

     @Override
     public void appTender(BorrowChoicenessTender tender) {
         if (tender.getChannel() == ChannelType.APP.getValue()){
             OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_APP_INVEST);
             if(scoreTaskInfo != null){
                 producerMq.updateUserScore(PublicConstant.TASK_ID_APP_INVEST,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",scoreTaskInfo.getScore());
             }
         }
     }

     @Override
     public void firstOrSecondTender(BorrowChoicenessTender tender, ProductOutput product) {
         PagePojo pagePojo = new PagePojo();
         pagePojo.setUserId(tender.getUserId());
         UserAccountInfo userAccountInfo = userClient.getUserAccountInfo(pagePojo);
         //未投资过
         if(userAccountInfo != null && userAccountInfo.getInvestFlag() == UserConstant.INVEST_FLAG_0 ){
             OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_FIRST_INVEST);
             if(scoreTaskInfo != null){
                 producerMq.updateUserScore(PublicConstant.TASK_ID_FIRST_INVEST, tender.getUserId(), tender.getMobile(), tender.getChannel() + "", scoreTaskInfo.getScore());
                 TenderAccountMqInfo info = new TenderAccountMqInfo();
                 info.setIsFirstTender(true);
                 info.setFirstTenderAccount(tender.getAccount());
                 producerMq.updateUserAccount(info);
             }
         }else{
//             二次复投非债权标的
             long count = borrowTenderMapper.getUserTenderCount(tender.getUserId());
             if (count == 2){
                 OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_CHECK_IN);
                 if(scoreTaskInfo != null){
                     producerMq.updateUserScore(PublicConstant.TASK_ID_SECOND_INVEST,tender.getUserId(),tender.getMobile(),tender.getChannel()+"",scoreTaskInfo.getScore());
                 }
             }
         }
     }


     @Override
     public PageInfo<SelectTenderOutput> selectByStatusAndUserId(SelectTenderInput selectTenderInput)
     {
         logger.info("choiceness selectTenderByBorrowId start ,input = {}", selectTenderInput);
         PageHelper.startPage(selectTenderInput.getPageSize(), selectTenderInput.getCurrentPage());
         List<SelectTenderOutput> tenderList = borrowChoicenessTenderMapper.selectByStatusAndUserId(selectTenderInput);
         PageInfo<SelectTenderOutput> pageInfo = new PageInfo<>(tenderList);
         pageInfo.setList(tenderList);
         Page<SelectTenderOutput> page = (Page<SelectTenderOutput>)tenderList;
         pageInfo.setTotal(page.getTotal());
         logger.info("choiceness selectTenderByBorrowId end ,input = {}", selectTenderInput);
         return pageInfo;
     }

     @Override
     public TenderDetailsOutput selectTenderById(Integer tenderId)
     {
         TenderDetailsOutput tenderDetailsOutput =new TenderDetailsOutput();
         BorrowChoicenessTender borrowChoicenessTender =   borrowChoicenessTenderMapper.selectByPrimaryKey(tenderId);
         Double sumFrost=borrowChoicenessTenderMapper.selectSumJoinByTenderId(tenderId);
         Double sumDoTender=borrowTenderMapper.selectSumTenderByTenderId(tenderId);
         tenderDetailsOutput.setFrostAccount((sumFrost - sumDoTender) + "");
         tenderDetailsOutput.setBorrowId(borrowChoicenessTender.getBorrowId());
         tenderDetailsOutput.setPreAccount(borrowChoicenessTender.getPreAccount().divide(new BigDecimal(100)).toString());
         tenderDetailsOutput.setPlatformInterest(borrowChoicenessTender.getPlatformInterest().divide(new BigDecimal(100)).toString());
         tenderDetailsOutput.setPreAccountInterest(borrowChoicenessTender.getAccountInterest().divide(new BigDecimal(100)).toString());
         tenderDetailsOutput.setVoucherCouponId(borrowChoicenessTender.getVoucherCouponId());
         tenderDetailsOutput.setVoucherId(borrowChoicenessTender.getVoucherId());
         SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
         tenderDetailsOutput.setAddTime(format.format(borrowChoicenessTender.getAddTime()));
         Double sumRecover = borrowCollectionMapper.selectSumRecoverByTenderId(tenderId);
         //实际到账金额（已经还款金额+最后平账送的奖励或者手续费）
         tenderDetailsOutput.setArrivalAccount(sumRecover.toString());
         return tenderDetailsOutput;
     }

     @Override
     public List<ChicenessTenderOutput> selectUnmatchingList() {
         BorrowChoicenessTenderExample example = new BorrowChoicenessTenderExample();
         example.createCriteria().andStatusEqualTo(0);
         example.setOrderByClause("add_time asc");

         List<BorrowChoicenessTender> tenders = borrowChoicenessTenderMapper.selectByExample(example);

         ImmutableList<ChicenessTenderOutput> resultList = FluentIterable.<BorrowChoicenessTender>from(tenders)
                 .transform(new Function<BorrowChoicenessTender, ChicenessTenderOutput>() {
             @Override
             public ChicenessTenderOutput apply(BorrowChoicenessTender input) {
                 ChicenessTenderOutput out = new ChicenessTenderOutput();
                 BeanUtils.copyProperties(input, out);
                 out.setTenderAmount(input.getPreAccount());
                 out.setTenderAmountYes(input.getAccount());
                 out.setTenderAmountWait(input.getPreAccount().subtract(input.getAccount()));
                 out.setTenderDate(input.getAddTime());

                 return out;
             }
         }).toList();

         return resultList;
     }


     /**
      * 还款释放的本金重新加入理财计划
      * @param account
      * @param tenderId
      */
     @Override
     public void insertChoiceness(BigDecimal account,Integer tenderId)
     {
         BorrowChoicenessTender choicenessTender = borrowChoicenessTenderMapper.selectReleaseFunds(tenderId);
         ChoicenessTenderInput choicenessTenderInput =  new ChoicenessTenderInput();
         choicenessTenderInput.setAllApr(new BigDecimal(0));
         choicenessTenderInput.setUserId(choicenessTenderInput.getUserId());
         choicenessTenderInput.setVoucherId(choicenessTenderInput.getVoucherId());
         choicenessTenderInput.setAwardApr(new BigDecimal(0));
         choicenessTenderInput.setBorrowId(choicenessTender.getBorrowId());
         choicenessTenderInput.setChannel(ChannelType.PC.getValue());
         choicenessTenderInput.setBorrowType(0);
         choicenessTenderInput.setPeriod(0);
         choicenessTenderInput.setPreAccount(account);
         choicenessTenderInput.setRecoverType(0);
         choicenessTenderInput.setVoucherApr(new BigDecimal(0));
         if(choicenessTender.getBeforeTenderId()==null)
         {
             choicenessTender.setBeforeTenderId(tenderId);
         }
         else
         {
             choicenessTender.setBeforeTenderId(choicenessTender.getBeforeTenderId());
         }
         this.insertChoiceness(choicenessTenderInput,"");
     }

     /**
      * 根据理财计划产品id或者投资id查询释放的债权
      */
     @Override
     public  List<SelectClaimOutput> selectClaim(Integer tenderId,Integer borrowId){
         List<SelectClaimOutput> collections = borrowCollectionMapper.selectClaim(tenderId,borrowId);
         return collections;
     }

     @Override
     public List<BorrowCollection> getTendingByChoicenessTenderId(Integer tenderId, Integer borrowId) {
         return borrowCollectionMapper.getTendingByChoicenessTenderId(tenderId,borrowId);
     }

     /**
      * 根据理财计划产品id或者投资id查询释放的债权（内部账户）
      */
     @Override
     public  List<BorrowCollection> selectClaimInside(String userId){
         List<BorrowCollection> collections = borrowCollectionMapper.selectClaimInside(userId);
         return collections;
     }










 }

package com.tuodao.bp.api.facade.service.product.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tuodao.bp.api.facade.client.depository.DepositoryBiddingClient;
import com.tuodao.bp.api.facade.client.depository.DepositoryBorrowerClient;
import com.tuodao.bp.api.facade.client.depository.DepositorySeekClient;
import com.tuodao.bp.api.facade.client.product.BorrowMappingBankClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.product.RepayClient;
import com.tuodao.bp.api.facade.service.product.IFacadeBorrowMappingBank;
import com.tuodao.bp.model.business.product.input.BorrowMappingBankInput;
import com.tuodao.bp.model.business.product.output.BorrowMappingBankOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.product.output.RepayPlanOutput;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.CFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.product.ProductVerifyExceptionConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.ArithUtils;

/**
 * @Author wuchengjie
 * @Date 2017/11/6 0006 15:58
 * @Introduction
 */
@Service
public class FacadeBorrowMappingBankImpl implements IFacadeBorrowMappingBank{

    private static final Logger logger = LoggerFactory.getLogger(FacadeBorrowMappingBankImpl.class);


    @Autowired
    private DepositoryBiddingClient depositoryBiddingClient;

    @Autowired
    private BorrowMappingBankClient borrowMappingBankClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private RepayClient repayClient;

    @Autowired
    private DepositoryBorrowerClient depositoryBorrowerClient;

    @Autowired
    private DepositorySeekClient depositorySeekClient;
    /**
     *@link IFacadeBorrowMappingBank
     * @param borrowId
     * @return
     */
    @Override
    public Boolean reverifyDepositoryStatus(Integer borrowId) {

        //根据mapping 记录的数据来判断应该做到第几步了
        //获取记录
        BorrowMappingBankOutput output  =  borrowMappingBankClient.getRecordByBorrowId(borrowId);

       ProductOutput product = productClient.getProduct(borrowId);
        //执行第一步成标操作
        //不管是否垫付 都要执行成标出账
        if(output.getBorrowBankStatus() == 0){
        //成标接口
            HashMap<String ,String> borrowComplete = new HashMap<>(16);

            List<RepayPlanOutput> repayPlanOutputs = repayClient.getTemporaryRepayList(product.getId());

            //还款的记录
            for(int i=0;repayPlanOutputs.size()>i;i++){
                RepayPlanOutput planOutput = repayPlanOutputs.get(i);

                String repayAmt = BigDecimal.valueOf(planOutput.getPreInterest()+planOutput.getPreCapital()).toString();
                String repayNum = String.valueOf(planOutput.getPeriod());
                String repayDate = String.valueOf(planOutput.getPreTime().getTime());


                borrowComplete.put(BJFN.repayPlanList+CFN._eleln_(i)+BJFN.repayAmt, repayAmt);
                borrowComplete.put(BJFN.repayPlanList+CFN._eleln_(i)+BJFN.repayNum, repayNum);
                borrowComplete.put(BJFN.repayPlanList+CFN._eleln_(i)+BJFN.repayFee, "0");
                borrowComplete.put(BJFN.repayPlanList+CFN._eleln_(i)+BJFN.repayDate, repayDate);
            }

            if (product.getBorrowFee().longValue() > 0) {


                int feeType = 1;

                HashMap<String ,String> seekInfo = new HashMap<>(16);
                seekInfo.put(BJFN.prodId,product.getId().toString());
                seekInfo.put(TDFN.type,"01");
                HashMap<String ,String>  seekret =  depositorySeekClient.biddingBalance(seekInfo);
                Boolean infoBooelan  =  Boolean.getBoolean(seekret.get(TDFN.success));
                if(infoBooelan){
                    //北京银行 现金的金额 分
                    BigDecimal cashBalance = ArithUtils.toCent(new BigDecimal(seekret.get(BJFN.data).toString()));
                    if(cashBalance.longValue()>=product.getBorrowFee().longValue())
                    {
                        borrowComplete.put(BJFN.funddata + CFN._swb_ +BJFN.payoutPlatType,"01");
                    }
                    else
                    {
                        borrowComplete.put(BJFN.funddata + CFN._swb_ + BJFN.payoutPlatType,"11");
                        feeType = 2;
                    }
                }
                else
                {
                    logger.error("查询标的余额失败:nid[{}]", product.getProductCode());
                    throw new BizFeignException(ProductVerifyExceptionConstant.REVERIFY_DEPOSI_CLIENT_ERROR);
                }
                borrowComplete.put(BJFN.funddata + CFN._swb_ +BJFN.payoutAmt,product.getBorrowFee().toString());

            }
            borrowComplete.put(BJFN.prodId,product.getId().toString());
            borrowComplete.put(BJFN.flag,"2");
            //自动出账
            HashMap<String, String> ret =  depositoryBiddingClient.complate(borrowComplete);
            //查询标的状态
            biddingInfo(output,1);
            //如果是新增标 成标出账
            if(output.getIsCompensatory() == 0){
                chargeOff(output,2);
            }
        }else if(output.getBorrowBankStatus() == 1 && output.getIsCompensatory() == 0){
            //如果是新增标 成标出账
            biddingInfo(output,2);
        }else if(output.getBorrowBankStatus() == 2){
            //do nothing
        }

        return true;
    }

    /**
     * 4.3.4.	成标出账
     * @param output
     * @param status
     * @return
     */
    private Boolean chargeOff(BorrowMappingBankOutput output, Integer status)
    {

        logger.info(output.toString() +"成标出账");
        HashMap<String ,String> borrowInfo = new HashMap<>(16);
        HashMap<String, String> inforet = depositoryBiddingClient.chargeOff(borrowInfo);

        Boolean infoBooelan  =  Boolean.getBoolean(inforet.get(TDFN.success));

        BorrowMappingBankInput input = new BorrowMappingBankInput();
        input.setBorrowId(output.getBorrowId());

        if(infoBooelan){
            input.setBorrowBankStatus(status);
            input.setCompensatoryStatus(2);
        }else{
            input.setBorrowBankStatus(output.getBorrowBankStatus());
            input.setCompensatoryStatus(3);
        }
        borrowMappingBankClient.updateRecord(input);
        return true;
    }


    /**
     * 查询标的状态
     * @param output
     * @param status
     * @return
     */
    private Boolean biddingInfo(BorrowMappingBankOutput output, Integer status)
    {

        logger.info(output.toString() +"查询标的状态");

        HashMap<String ,String> borrowInfo = new HashMap<>(16);
        HashMap<String, String> inforet = depositorySeekClient.biddingInfo(borrowInfo);

       // Boolean infoBooelan  =  Boolean.getBoolean(inforet.get(TDFN.success));
        BorrowMappingBankInput input = new BorrowMappingBankInput();
        input.setBorrowId(output.getBorrowId());
        String prod_state = inforet.get("prod_state");
        if ("产品成立".equals(prod_state)) {
            input.setBorrowBankStatus(status);
            input.setCompensatoryStatus(2);
        }else{
            input.setBorrowBankStatus(output.getBorrowBankStatus());
            input.setCompensatoryStatus(3);
        }
        borrowMappingBankClient.updateRecord(input);
        return true;
    }

    /**
     * 调用 垫付资金的接口
     */
    @Override
    public Boolean borrowerCompensation(BorrowMappingBankOutput output, ProductOutput product)
    {

        //果然是垫付标 那就要把相对应垫付资金拿回来
        if(output.getIsCompensatory() == 1 && output.getBorrowBankStatus() == 2) {

            HashMap<String, String> detailInfo = new HashMap<>(16);
            //获取账号中的资金
            detailInfo.put(TDFN.userId, product.getBorrowUserId());
            detailInfo.put(BJFN.acctType, "13");
            detailInfo.put(BJFN.fundType, "01");

            HashMap<String, String> inforet = depositorySeekClient.accountBalanceDetail(detailInfo);
            Boolean b = Boolean.getBoolean(inforet.get(BJFN.success));
            if (b) {
                String balance = inforet.get(BJFN.data + CFN._swb_ + BJFN.balance);
                //转成分
                BigDecimal money = ArithUtils.toCent(new BigDecimal(balance).setScale(2, BigDecimal.ROUND_HALF_UP));
                if (money.longValue() <= 0) {
                    return true;
                }

                BigDecimal awaitMoney = output.getCompensatoryAmount().subtract(output.getCompensatoryAmountYes());
                //如果还有剩余的没拿回来 那么执行接口
                if (awaitMoney.longValue() > 0) {
                    BigDecimal sendMoney = null;

                    if (money.longValue() > awaitMoney.longValue()) {
                        sendMoney = awaitMoney;
                    } else if (money.longValue() <= awaitMoney.longValue()) {
                        sendMoney = money;
                    }
                    //调用接口
                    HashMap<String, String> compensationInfo = new HashMap<>(16);

                    compensationInfo.put(BJFN.prodId, product.getParentId().toString());
                    compensationInfo.put(TDFN.userId, product.getBorrowUserId());
                    compensationInfo.put(BJFN.compensation+CFN._swb_+BJFN.platCust, "01");  //委托人
                    compensationInfo.put(BJFN.repayAmt, sendMoney.toString());
                    HashMap<String, String> compensationret = depositoryBorrowerClient.borrowerCompensation(compensationInfo);
                    Boolean compeBoo = Boolean.getBoolean(compensationret.get(TDFN.success));

                    BorrowMappingBankInput input = new BorrowMappingBankInput();
                    input.setBorrowId(output.getBorrowId());

                    if (compeBoo) {
                        input.setCompensatoryAmountYes(sendMoney);
                        input.setCompensatoryStatus(2);
                    } else {
                        input.setCompensatoryStatus(3);
                    }
                    borrowMappingBankClient.updateRecord(input);
                    logger.info("标的委托垫付:nid[{}] 状态:{}", product.getProductCode(), compeBoo);

                }
            }
            return true;
            }
        return false;
    }
}

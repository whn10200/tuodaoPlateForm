package com.tuodao.bp.api.facade.controller.traningcenter;


import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.product.BorrowPlanTransferClient;
import com.tuodao.bp.api.facade.client.product.ProductAuditRecordClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.product.RepayClient;
import com.tuodao.bp.api.facade.client.transaction.ReverifyPlanClient;
import com.tuodao.bp.api.facade.service.product.impl.FacadeBorrowMappingBankImpl;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.model.ReturnCacheInfo;
import com.tuodao.bp.model.business.product.output.BorrowPlanTransferOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentInfoOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.product.output.ProductWithRecordOutput;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.PlanBorrowTenderOutput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import com.tuodao.bp.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 理财计划复审
 */
@RestController
@RequestMapping("/router/reverifyPlanController")
public class ReverifyPlanController {
    private static final Logger logger = LoggerFactory.getLogger(ReverifyPlanController.class);

    @Autowired
    private ReverifyPlanClient reverifyPlanClient;
    @Autowired
    private ProductClient productClient;
    @Autowired
    ReturnsCache returnsCache;
    @Autowired
    private ProductAuditRecordClient productAuditRecordClient;
    @Autowired
    private BorrowPlanTransferClient borrowPlanTransferClient;
    @Autowired
    private RepayClient repayClient;
    @Autowired
    private FacadeBorrowMappingBankImpl facadeBorrowMappingBank;

    /**
     *理财计划复审
     */
    @RequestMapping("/reverifyPlanHander")
    public RespResult reverifyPlan(JustKeyAndIdInput justIdInput,Boolean flag)
    {
        List<PlanListInput> planListInputList = new ArrayList<>();
        //查询理财计划
        ProductOutput product = productClient.getProduct(justIdInput.getJustId());
        //计算理财计划到期日
        Date repayLastTime =null;
        if(product.getRefundWay()==2)
        {
            repayLastTime = DateUtil.addDays(new Date(),product.getProductPeriod());
        }
        else
        {
            repayLastTime= DateUtil.addMonths(new Date(),product.getProductPeriod());
        }

        //查询所有投标记录
        JustIdInput inputs = new JustIdInput();
        inputs.setJustId(justIdInput.getJustId());
        List<PlanTenderInput>  list = new ArrayList<>();
//                reverifyPlanClient.selectTenderListOnOrgReverify(inputs);
        String key = "handerPlan" + "_" +justIdInput.getJustId()+ new Date().getTime();
        for(int i=0;i<list.size();i++)
        {
            PlanTenderInput planTenderInput = list.get(i);
            //查询该笔投资对于的底层标的
            ProductOutput productOutput = productClient.getProduct(planTenderInput.getBorrowId());
            //查询上期还款时间 以及剩余期数
            BorrowRepaymentInfoOutput borrowRepaymentInfoOutput = repayClient.getRepayInfoByBorrowId(planTenderInput.getBorrowId());

            PlanListInput planListInput=new PlanListInput();
            //普通标的
            if(list.get(i).getTenderType()==2)
            {
                planListInput.setType(0);
                planListInput.setRepayLastTime(repayLastTime);
            }
            //转让标
            else
            {
                planListInput.setType(1);
                planListInput.setRepayLastTime(repayLastTime);
                planListInput.setPeriods(borrowRepaymentInfoOutput.getPeriods());
                planListInput.setPeriod(borrowRepaymentInfoOutput.getPeriod());
                planListInput.setLastRepayTime(borrowRepaymentInfoOutput.getLastRepayTime());
                planListInput.setAccount(planTenderInput.getAccount());
                planListInput.setTenderId(planTenderInput.getId());
                planListInput.setAwardScale(product.getAwardScale());
                planListInput.setApr(product.getBorrowApr());
                if(planTenderInput.getVoucherCouponId()!=null)
                {
                    planListInput.setApr(product.getBorrowApr());
                }
                else
                {
                    planListInput.setApr(new BigDecimal(0));
                }
                planListInput.setVouchApr(planTenderInput.getVoucherMoney());
                planListInput.setId(product.getId());
                planListInput.setBorrowName("");
                planListInput.setLastProductId(productOutput.getId());
                planListInput.setProductId(productOutput.getId());
                planListInput.setOrderId(planTenderInput.getOrderId());
                planListInput.setRaymentType(productOutput.getRefundWay());
                //剩余数 是到期日-现在时间
                if(productOutput.getRefundWay()==2)
                {
                    int days= (int) DateUtil.diffDay(productOutput.getRepayLastTime(), new Date())+1;
                    planListInput.setLeftPeriod(days);
                    planListInput.setStartPeriod(1);
                }
                else
                {
                    planListInput.setLeftPeriod(borrowRepaymentInfoOutput.getPeriod());
                    planListInput.setStartPeriod(borrowRepaymentInfoOutput.getPeriods() - borrowRepaymentInfoOutput.getPeriod() + 1);

                }
                planListInput.setUserId(planTenderInput.getUserId());
                planListInput.setPreAccountInterest(new BigDecimal(0));
                if(flag)
                {
                    planListInput.setKey(key);
                    justIdInput.setKey(key);
                    //在缓存中存入正在处理中
                    ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
                    returnCacheInfo.setStatus(0);
                    returnCacheInfo.setKey(key);
                    returnsCache.putReturnInfo(returnCacheInfo);
                }
            }
            planListInputList.add(planListInput);
        }
        ReverifyPlanInput reverifyPlanInput = new ReverifyPlanInput();
        reverifyPlanInput.setList(planListInputList);
        reverifyPlanInput.setProductOutput(product);

        reverifyPlanClient.reverifyPlan(reverifyPlanInput);
        if(flag) {
            try {
                while (true) {
                    //读取缓存
                    //查询缓存信息 如果是还在处理中 sleep 100毫秒 继续查询 如果是失败 直接返回 并返回原因  如果是成功 直接返回
                    int status = returnsCache.getReturnInfo(key).getStatus();
                    if (status == 1) {
                        returnsCache.deleteReturnInfo(key);
                        return RespResult.create();
                    } else if (status == 2) {
                        RespResult respResult = RespResult.create();
                        respResult.setCode(returnsCache.getReturnInfo(key).getCode());
                        respResult.setMsg(returnsCache.getReturnInfo(key).getContent());
                        returnsCache.deleteReturnInfo(key);
                        return respResult;
                    } else {
                        TimeUnit.MILLISECONDS.sleep(200);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RespResult.create();
    }




    /**
     *手动底层标的复审
     */
    @RequestMapping("/reverifyOrignalBorrow")
    public RespResult reverifyOrignalBorrow(JustKeyAndIdInput justIdInput,Boolean flag)
    {
        // 调取北京银行接口
        Boolean bjStatus =facadeBorrowMappingBank.reverifyDepositoryStatus(justIdInput.getJustId());
        if(!bjStatus)
        {
            throw new MicroServiceException(TransactError.REVERIFY_RETRIEVE_ERROR);
        }

        //要传递出去的参数
        PlanNomalListInput planNomalListInput = new PlanNomalListInput();


        //查询该标的
        ProductOutput product = productClient.getProduct(justIdInput.getJustId());
        planNomalListInput.setBorrowPeriod(product.getProductPeriod());

        //查询底层标的投资记录
        JustIdInput inputs = new JustIdInput();
        inputs.setJustId(justIdInput.getJustId());
        List<PlanBorrowTenderOutput>  planBorrowTenderOutputsLists = reverifyPlanClient.selectTenderListOnOrgReverify(inputs);

        //查询每笔投资所属理财计划的信息
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<planBorrowTenderOutputsLists.size();i++)
        {
            list.add(planBorrowTenderOutputsLists.get(i).getBorrowId());
        }
        List<ProductWithRecordOutput> productOutputs = productClient.getListWithRecordeByIdList(list);


        List<PlanNomalInput> planNomalInputList= new ArrayList<>();
        for(int i=0;i<planBorrowTenderOutputsLists.size();i++)
        {
            //投资
            PlanBorrowTenderOutput borrowTender = planBorrowTenderOutputsLists.get(i);
            //理财计划信息
            ProductWithRecordOutput output = productOutputs.get(i);

            PlanNomalInput planNomalInput = new PlanNomalInput();
            //理财计划基本利率
            planNomalInput.setApr(output.getBorrowApr());
            //理财计划的奖励利率
            planNomalInput.setAwardScale(output.getAwardScale());
            //理财计划的加息劵利率
            planNomalInput.setVouchApr(borrowTender.getVoucherMoney());

            //
            planNomalInput.setLeftPeriod(output.getProductPeriod());
            planNomalInput.setStartPeriod(1);
            //查询上期还款时间
            planNomalInput.setRepayTime(new Date().getTime());
            //获取
            planNomalInput.setRepayLastTime(output.getRepayLastTime());
            //投资id
            planNomalInput.setTenderId(borrowTender.getId());
            //还款方式
            planNomalInput.setRaymentType(output.getRefundWay());

            planNomalInput.setAccount(borrowTender.getAccount());
            planNomalInput.setUserId(borrowTender.getUserId());
            planNomalInput.setBorrowName("");
            planNomalInput.setOrderId(borrowTender.getOrderId());
            planNomalInput.setProductId(output.getId());
            planNomalInput.setLastProductId(product.getId());
            planNomalInput.setId(borrowTender.getBorrowId());
            planNomalInputList.add(planNomalInput);
        }
        planNomalListInput.setInputs(planNomalInputList);
        //先查询存管资金  判断金额是否有异常  如果有异常 那么直接返回资金异常 请联系客服
        String key = "handerOrignal" + "_" +justIdInput.getJustId()+ new Date().getTime();
        if(flag)
        {
            justIdInput.setKey(key);
            //在缓存中存入正在处理中
            ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
            returnCacheInfo.setStatus(0);
            returnCacheInfo.setKey(key);
            returnsCache.putReturnInfo(returnCacheInfo);
            planNomalListInput.setKey(key);
        }
        reverifyPlanClient.reverifyOrignalBorrow(planNomalListInput);
        if(flag) {
            try {
                while (true) {
                    //读取缓存
                    //查询缓存信息 如果是还在处理中 sleep 100毫秒 继续查询 如果是失败 直接返回 并返回原因  如果是成功 直接返回
                    int status = returnsCache.getReturnInfo(key).getStatus();
                    if (status == 1) {
                        returnsCache.deleteReturnInfo(key);
                        return RespResult.create();
                    } else if (status == 2) {
                        RespResult respResult = RespResult.create();
                        respResult.setCode(returnsCache.getReturnInfo(key).getCode());
                        respResult.setMsg(returnsCache.getReturnInfo(key).getContent());
                        returnsCache.deleteReturnInfo(key);
                        return respResult;
                    } else {
                        TimeUnit.MILLISECONDS.sleep(200);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return RespResult.create();

    }

    /**
     * 复审理财计划下的转让标的复审（手动）
     */
    @RequestMapping("/reverifyDevelopBorrowHander")
    public RespResult reverifyDevelopBorrow(JustKeyAndIdInput justIdInput,Boolean flag)
    {
        PlanDevelopListInput planDevelopListInput =  new PlanDevelopListInput();

        //先查询存管资金  判断金额是否有异常  如果有异常 那么直接返回资金异常 请联系客服
        String key = "handerDevelop" + "_" +justIdInput.getJustId()+ new Date().getTime();
        justIdInput.setKey(key);

        //查询该转让标的
        BorrowPlanTransferOutput borrowPlanTransferOutput =borrowPlanTransferClient.getBorrowPlanTransfer(justIdInput.getJustId());

        //查询上期还款时间 以及剩余期数
        BorrowRepaymentInfoOutput borrowRepaymentInfoOutput = repayClient.getRepayInfoByBorrowId(borrowPlanTransferOutput.getPreBorrowId());
        //查询原始标的的最后还款时间
        ProductOutput productOutput = productClient.getProduct(borrowPlanTransferOutput.getPreBorrowId());
        if(borrowPlanTransferOutput.getRaymentType()==2)
        {
            int days= (int) DateUtil.diffDay(productOutput.getRepayLastTime(), new Date())+1;
            planDevelopListInput.setPeriod(days);
        }
        else
        {
            planDevelopListInput.setPeriod(borrowRepaymentInfoOutput.getPeriod());
        }
        planDevelopListInput.setLastRepayTime(borrowRepaymentInfoOutput.getLastRepayTime());
        planDevelopListInput.setPeriods(borrowRepaymentInfoOutput.getPeriods());

        //放入转让人的信息
        planDevelopListInput.setTenderId(borrowPlanTransferOutput.getTenderId());
        planDevelopListInput.setAccount(borrowPlanTransferOutput.getAccount());
        planDevelopListInput.setBorrowName(borrowPlanTransferOutput.getBorrowName());
        planDevelopListInput.setBorrowId(borrowPlanTransferOutput.getId());

        JustIdInput inputs = new JustIdInput();
        inputs.setJustId(justIdInput.getJustId());
        //遍历投资人的信息
        List<PlanBorrowTenderOutput>  planBorrowTenderOutputsLists = reverifyPlanClient.selectTenderListOnDevReverify(inputs);


//        PlanDevelopListInput planDevelopListInput1 = new PlanDevelopListInput();
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<planBorrowTenderOutputsLists.size();i++)
        {
            list.add(planBorrowTenderOutputsLists.get(i).getBorrowId());
        }
        //查询各个理财计划信息
        List<ProductWithRecordOutput> productOutputs = productAuditRecordClient.getBorrowPlanTransfer(list);

//        List<TenderTransferInput> tenderTransferInputs= new ArrayList<>();
        List<PlanNomalInput> planNomalInputList= new ArrayList<>();
        for(int i=0;i<planBorrowTenderOutputsLists.size();i++)
        {
            //投资
            PlanBorrowTenderOutput borrowTender = planBorrowTenderOutputsLists.get(i);
            //理财计划信息
            ProductWithRecordOutput output = productOutputs.get(i);

            PlanNomalInput planNomalInput = new PlanNomalInput();
            //理财计划基本利率
            planNomalInput.setApr(output.getBorrowApr());
            //理财计划的奖励利率
            planNomalInput.setAwardScale(output.getAwardScale());
            //理财计划的加息劵利率
            planNomalInput.setVouchApr(borrowTender.getVoucherMoney());

            //
            planNomalInput.setLeftPeriod(planDevelopListInput.getPeriod());
//            planNomalInput.setStartPeriod(1);
            //查询上期还款时间
            planNomalInput.setRepayTime(new Date().getTime());
            //获取
            planNomalInput.setRepayLastTime(output.getRepayLastTime());
            //投资id
            planNomalInput.setTenderId(borrowTender.getId());
            //还款方式
            planNomalInput.setRaymentType(productOutput.getRefundWay());

            planNomalInput.setAccount(borrowTender.getAccount());
            planNomalInput.setUserId(borrowTender.getUserId());
            planNomalInput.setBorrowName("");
            planNomalInput.setOrderId(borrowTender.getOrderId());
            planNomalInput.setProductId(output.getId());
            planNomalInput.setLastProductId(borrowPlanTransferOutput.getId());
            planNomalInput.setId(borrowTender.getBorrowId());
            planNomalInput.setPlayAccount(0);
            planNomalInputList.add(planNomalInput);
        }

        //遍历陪玩账户投资人的信息
        List<PlanBorrowTenderOutput>  planBorrowTenderOutputsListsPlay = reverifyPlanClient.selectTenderListOnDevReverifyPlay(inputs);

        for(int i=0;i<planBorrowTenderOutputsListsPlay.size();i++)
        {
            //投资
            PlanBorrowTenderOutput borrowTender = planBorrowTenderOutputsListsPlay.get(i);


            PlanNomalInput planNomalInput = new PlanNomalInput();
            //理财计划基本利率
            planNomalInput.setApr(borrowPlanTransferOutput.getApr());
            //理财计划的奖励利率
            planNomalInput.setAwardScale(new BigDecimal(0));
            //理财计划的加息劵利率
            planNomalInput.setVouchApr(borrowTender.getVoucherMoney());

            //
            planNomalInput.setLeftPeriod(planDevelopListInput.getPeriod());
//            planNomalInput.setStartPeriod(1);
            //查询上期还款时间
            planNomalInput.setRepayTime(new Date().getTime());
            //获取
            planNomalInput.setRepayLastTime(new Date());
            //投资id
            planNomalInput.setTenderId(borrowTender.getId());
            //还款方式
            planNomalInput.setRaymentType(productOutput.getRefundWay());

            planNomalInput.setAccount(borrowTender.getAccount());
            planNomalInput.setUserId(borrowTender.getUserId());
            planNomalInput.setBorrowName("");
            planNomalInput.setOrderId(borrowTender.getOrderId());
            planNomalInput.setProductId(0);
            planNomalInput.setLastProductId(0);
            planNomalInput.setId(borrowTender.getBorrowId());
            planNomalInput.setPlayAccount(1);
            planNomalInputList.add(planNomalInput);
        }
        planDevelopListInput.setList(planNomalInputList);
        if(flag==true)
        {
            planDevelopListInput.setKey(key);
            //在缓存中存入正在处理中
            ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
            returnCacheInfo.setStatus(0);
            returnCacheInfo.setKey(key);
            returnsCache.putReturnInfo(returnCacheInfo);
        }
        reverifyPlanClient.reverifyDevelopBorrow(planDevelopListInput);
        if(flag==true)
        {
            try {
                while (true) {
                    //读取缓存
                    //查询缓存信息 如果是还在处理中 sleep 100毫秒 继续查询 如果是失败 直接返回 并返回原因  如果是成功 直接返回
                    int status = returnsCache.getReturnInfo(key).getStatus();
                    if (status == 1) {
                        returnsCache.deleteReturnInfo(key);
                        return RespResult.create();
                    }else if (status==2)
                    {
                        RespResult respResult = RespResult.create();
                        respResult.setCode(returnsCache.getReturnInfo(key).getCode());
                        respResult.setMsg(returnsCache.getReturnInfo(key).getContent());
                        returnsCache.deleteReturnInfo(key);
                        return respResult;
                    }
                    else {
                        TimeUnit.MILLISECONDS.sleep(200);
                    }
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return RespResult.create();

    }

    /**
     *自动底层普通标的复审
     */
    @RequestMapping("/reverifyOrignalBorrowAuto")
    public void reverifyOrignalBorrow()
    {
        List<ProductOutput> productOutputArrayList = productClient.getPlanReverifyBorrowList();
        for(int i=0;i<productOutputArrayList.size();i++)
        {
            JustKeyAndIdInput justKeyAndIdInput = new JustKeyAndIdInput();
            justKeyAndIdInput.setJustId(productOutputArrayList.get(i).getId());
            this.reverifyOrignalBorrow(justKeyAndIdInput,false);
        }
    }


    /**
     *自动转让标的复审
     */
    @RequestMapping("/reverifyDevelopBorrow")
    public void reverifyDevelopBorrow()
    {
        List<BorrowPlanTransferOutput> borrowPlanTransferOutputList = borrowPlanTransferClient.getPlanReverifyTransferList();
        for(int i=0;i<borrowPlanTransferOutputList.size();i++)
        {
            JustKeyAndIdInput justKeyAndIdInput = new JustKeyAndIdInput();
            justKeyAndIdInput.setJustId(borrowPlanTransferOutputList.get(i).getId());
            this.reverifyDevelopBorrow(justKeyAndIdInput, false);
        }
    }

    /**
     *自动理财计划复审
     */
    @RequestMapping("/reverifyPlanAuto")
    public void reverifyPlanAuto()
    {
        List<ProductOutput> productOutputArrayList = productClient.getReverifyPlanList();
        for(int i=0;i<productOutputArrayList.size();i++)
        {
            JustKeyAndIdInput justKeyAndIdInput = new JustKeyAndIdInput();
            justKeyAndIdInput.setJustId(productOutputArrayList.get(i).getId());
            this.reverifyPlan(justKeyAndIdInput, false);
        }
    }


}

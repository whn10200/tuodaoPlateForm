package com.tuodao.bp.traningcenter.controller;

import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.*;
import com.tuodao.bp.model.business.traningcenter.output.PlanBorrowTenderOutput;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.traningcenter.constant.TraningCenterExceptionConstant;
import com.tuodao.bp.traningcenter.db.model.basic.AccountStatus;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.service.AccountStatusService;
import com.tuodao.bp.traningcenter.service.BorrowChicenessService;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.Queue;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/17 0017.
 */
@RestController
@RequestMapping("/TraningCenter")
public class ReverifyPlanController {

    @Resource
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource(name = "traningReverifyPlanQueue")
    private Queue traningReverifyPlanQueue;
    @Resource(name = "traningReverifyOrginalQueue")
    private Queue traningReverifyOrginalQueue;
    @Resource(name = "traningReverifyDevelopQueue")
    private Queue traningReverifyDevelopQueue;
    @Resource
    private AccountStatusService accountStatusService;
    @Resource
    private BorrowTenderService borrowTenderService;
    @Resource
    private BorrowChicenessService borrowChicenessService;

    /**
     * 理财计划复审的生产者
     */
    @RequestMapping(value = "/plan/reverifyPlan", method = RequestMethod.POST)
    public void reverifyPlan(ReverifyPlanInput reverifyPlanInput) {
        List<AccountStatus> list = new ArrayList<>();
        AccountStatus accountStatus = new AccountStatus();
        accountStatus.setType(2);
        accountStatus.setProductId(reverifyPlanInput.getList().get(0).getProductId());
        list.add(accountStatus);
        Boolean flag = accountStatusService.updateAccountStatus(list, 1);
        if(flag==true)
        {
            jmsMessagingTemplate.convertAndSend(traningReverifyPlanQueue, reverifyPlanInput);
        }
        else
        {
            throw new BizFeignException(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
        }
    }

    /**
     * 理财计划下底层标的的（产品表中）复审生产者
     */
    @RequestMapping(value = "/plan/reverifyOrignalBorrow", method = RequestMethod.POST)
    public void reverifyOrignalBorrow(PlanNomalListInput inputs) {
        if(inputs!=null)
        {
            List<AccountStatus> list = new ArrayList<>();
            List<PlanNomalInput> planNomalInputList = inputs.getInputs();
            for(int i=0;i<planNomalInputList.size();i++)
            {
                AccountStatus accountStatus = new AccountStatus();
                accountStatus.setProductId(planNomalInputList.get(i).getProductId());
                accountStatus.setLastProductId(planNomalInputList.get(i).getLastProductId());
                accountStatus.setType(0);
                list.add(accountStatus);
            }
            Boolean flag = accountStatusService.updateAccountStatus(list,2);
            if(flag==true)
            {

                jmsMessagingTemplate.convertAndSend(traningReverifyOrginalQueue, inputs);
            }
            else
            {
                throw new BizFeignException(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
            }
        }

    }

    /**
     * 理财计划下底层标的的（转让表中）复审生产者
     */
    @RequestMapping(value = "/plan/reverifyDevelopBorrow", method = RequestMethod.POST)
    public void reverifyDevelopBorrow(PlanDevelopListInput inputs) {
        if(inputs!=null)
        {
            List<AccountStatus> list = new ArrayList<>();
            List<PlanNomalInput> planNomalInputList = inputs.getList();
            for(int i=0;i<planNomalInputList.size();i++)
            {
                if(planNomalInputList.get(i).getProductId()!=0)
                {
                    AccountStatus accountStatus = new AccountStatus();
                    accountStatus.setProductId(planNomalInputList.get(i).getProductId());
                    accountStatus.setLastProductId(planNomalInputList.get(i).getLastProductId());
                    accountStatus.setType(1);
                    list.add(accountStatus);
                }
            }
            Boolean flag = accountStatusService.updateAccountStatus(list,2);
            if(flag==true)
            {
                jmsMessagingTemplate.convertAndSend(traningReverifyDevelopQueue, inputs);
            }
            else
            {
                throw new BizFeignException(TraningCenterExceptionConstant.REVERIFY_PLAN_FARIL);
            }
        }

    }



    /**
     * 查询投资该标的的所有投资
     */
    @RequestMapping(value = "/plan/selectTenderListOnOrgReverify", method = RequestMethod.POST)
    public  List<PlanBorrowTenderOutput>   selectTenderListOnOrgReverify(JustIdInput inputs) {
        List<PlanBorrowTenderOutput>  borrowTenderList = borrowTenderService.selectTenderListOnOrgReverify(inputs.getJustId());
        return borrowTenderList;
    }

    /**
     * 查询投资该标的的所有投资
     */
    @RequestMapping(value = "/plan/selectTenderListOnDevReverify", method = RequestMethod.POST)
    public  List<PlanBorrowTenderOutput>   selectTenderListOnDevReverify(JustIdInput inputs) {
        List<PlanBorrowTenderOutput>  borrowTenderList = borrowTenderService.selectTenderListOnDevReverify(inputs.getJustId());
        return borrowTenderList;
    }

    /**
     * 查询投资该标的的所有投资(陪玩账户)
     */
    @RequestMapping(value = "/plan/selectTenderListOnDevReverifyPlay", method = RequestMethod.POST)
    public  List<PlanBorrowTenderOutput>   selectTenderListOnDevReverifyPlay(JustIdInput inputs) {
        List<PlanBorrowTenderOutput>  borrowTenderList = borrowTenderService.selectTenderListOnDevReverifyPlay(inputs.getJustId());
        return borrowTenderList;
    }





//    @RequestMapping(value = "/borrowChiceness/reverifyPlanNomal", method = RequestMethod.POST)
//    public void reverifyPlanNomal(PlanNomalListInput inputs) {
//        //只需要将还款时间设置成理财计划复审时间即可
//        borrowChicenessService.reverifyPlanNomal(inputs);
//    }



}

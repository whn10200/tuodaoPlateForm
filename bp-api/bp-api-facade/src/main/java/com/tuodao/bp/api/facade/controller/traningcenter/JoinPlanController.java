package com.tuodao.bp.api.facade.controller.traningcenter;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.service.transaction.JoinPlanService;
import com.tuodao.bp.cache.basic.traningcenter.RedisLock;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.JoinReturnContent;
import com.tuodao.bp.model.ReturnCacheInfo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.traningcenter.input.JoinPlanInput;
import com.tuodao.bp.model.business.traningcenter.input.JustIdInput;
import com.tuodao.bp.model.business.traningcenter.input.SelectTenderInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderTraRecordInput;
import com.tuodao.bp.model.business.traningcenter.output.*;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.input.TenderParam;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;


/**
 *加入理财计划
 */
@RestController
@RequestMapping("/router/joinPlanController")
public class JoinPlanController {

    @Autowired
    JoinPlanService joinPlanService;
    @Autowired
    ReturnsCache returnsCache;

    /**
     * 加入理财计划
     * @param param 加入理财计划入参
     * @return
     */
    @RequestMapping("/joinPlan")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult joinPlan(TenderParam param,HttpServletRequest request) {

        RedisLock lock = new RedisLock(RedisConstans.JOIN_PLAN_PREFIX + param.getUserId());
        try {//加锁 防止其他更新
            if (lock.lock()) {
                String key = param.getUserId() + CommonUtils.getUUID();
                //在缓存中存入正在处理中
                ReturnCacheInfo returnCacheInfo = new ReturnCacheInfo();
                returnCacheInfo.setStatus(0);
                returnCacheInfo.setKey(key);
                returnsCache.putReturnInfo(returnCacheInfo);
                JoinReturnContent joinReturnContent = joinPlanService.joinPlan(param, request, key);
                try {
                    while (true) {
                        //读取缓存
                        //查询缓存信息 如果是还在处理中 sleep 100毫秒 继续查询 如果是失败 直接返回 并返回原因  如果是成功 直接返回
                        int status = returnsCache.getReturnInfo(key).getStatus();
                        if (status == 1) {
                            joinReturnContent = new JoinReturnContent();
                            joinReturnContent.setAmount(returnsCache.getReturnInfo(key).getAmount());
                            joinReturnContent.setIntrest(returnsCache.getReturnInfo(key).getPreInterest());
                            joinReturnContent.setType(returnsCache.getReturnInfo(key).getVoucherType() + "");
                            joinReturnContent.setTypeValue(returnsCache.getReturnInfo(key).getResidueAmount());
                            returnsCache.deleteReturnInfo(key);
                            return RespResult.create().setContent(joinReturnContent);
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
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        finally {
            lock.unlock();
        }
        return RespResult.create();
    }

    /**
     * 根据borrowId分页查询加入理财计划记录
     * @param tenderTrRecordInput
     * @return
     */
    @RequestMapping("/getJoinPlanList")
    public RespResult getJoinPlanList(TenderTraRecordInput tenderTrRecordInput) {
        PageInfo<TenderRecord>  recordPageInfo=joinPlanService.getJoinLists(tenderTrRecordInput);
        return RespResult.create().setContent(recordPageInfo);
    }


    /**
     * 根据userid和状态和起始时间分页查询理财计划投资记录（pc我的投资精选计划）
     * @param  tenderInput
     * @return PageInfo<SelectTenderOutput>
     */
    @RequestMapping(value = "/getTenderByUserId", method = RequestMethod.POST)
    public RespResult getTenderByUserId(SelectTenderInput tenderInput) {
        PageInfo<SelectTenderOutput> pageInfo= joinPlanService.getTenderByUserId(tenderInput);
        return RespResult.create().setContent(pageInfo);
    }

    /**
     * 根据tenderid查询投资详情（精选计划投资详情（1））
     * @param  justIdInput
     * @return RespResult
     */
    @RequestMapping(value = "/selectTenderById", method = RequestMethod.POST)
    public RespResult selectTenderById(JustIdInput justIdInput) {
        TenderDetailsOutput tenderDetailsOutput = joinPlanService.selectTenderById(justIdInput);
        return RespResult.create().setContent(tenderDetailsOutput);
    }


    /**
     * 根据标的id查询投资详情（精选计划投资详情（2）债权明细）
     * @param  justIdInput
     * @return RespResult
     */
    @RequestMapping(value = "/selectTenderListByChoicenessTenderId", method = RequestMethod.POST)
    public RespResult selectTenderListByChoicenessTenderId(JustIdInput justIdInput){
        PageInfo<UnderTenderOutput> underTenderOutputPageInfo = joinPlanService.selectTenderListByChoicenessTenderId(justIdInput);
        return RespResult.create().setContent(underTenderOutputPageInfo);
    }

    /**
     * 根据标的id查询投资详情（精选计划投资详情（3）回款计划）
     * @param  justIdInput
     * @return RespResult
     */
    @RequestMapping(value = "/selectRecoverListBychioId", method = RequestMethod.POST)
    public RespResult selectRecoverListBychioId(JustIdInput justIdInput){
        PageInfo<RecoverListOutput> recoverListOutputPageInfo = joinPlanService.selectRecoverListBychioId(justIdInput);
        return RespResult.create().setContent(recoverListOutputPageInfo);
    }

    /**
     * 查询加入理财计划最大投资 扫尾
     * @param tenderTrRecordInput
     * @return
     */
    @RequestMapping("/getMaxAndPic")
    public RespResult getMaxAndPic(TenderTraRecordInput tenderTrRecordInput) {
        TenderTraRecordOutput tenderTraRecordOutput=joinPlanService.getMaxAndLast(tenderTrRecordInput);
        return RespResult.create().setContent(tenderTraRecordOutput);
    }

}

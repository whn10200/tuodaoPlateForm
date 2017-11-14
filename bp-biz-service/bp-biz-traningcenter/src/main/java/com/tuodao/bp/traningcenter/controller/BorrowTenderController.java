package com.tuodao.bp.traningcenter.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.tuodao.bp.cache.basic.operation.ScoreTaskCache;
import com.tuodao.bp.model.OpScoreTaskCacheInfo;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.input.TenderInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderQueryInput;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderExtParam;
import com.tuodao.bp.model.facade.traningcenter.input.BorrowParam;
import com.tuodao.bp.model.facade.traningcenter.input.TenderDetailParam;
import com.tuodao.bp.model.facade.traningcenter.input.TenderListParam;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import com.tuodao.bp.model.facade.traningcenter.output.ProductTenderVo;
import com.tuodao.bp.model.traningcenter.input.RecheckInput;
import com.tuodao.bp.traningcenter.db.model.basic.BorrowTender;
import com.tuodao.bp.traningcenter.service.BorrowTenderService;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.StringUtil;
import com.tuodao.bp.utils.TransferUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @description: 投标
 * @author: 王艳兵
 * @date: 2017/9/14
 * @time: 14:47
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
public class BorrowTenderController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BorrowTenderController.class);

    @Autowired
    private BorrowTenderService borrowTenderService;

    @Autowired
    private ScoreTaskCache scoreTaskCache;

    /**
     * 将投标信息添加到投标队列列中 生产者
     * @param executor
     */
    @RequestMapping(value = "/tender/tenderProducer",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void tenderProducer(TenderExecutor executor){
        borrowTenderService.tenderProducer(executor);
    }


    /**
     * 投资信息
     * @param input
     * @return
     */
    @RequestMapping("/tender/info")
    public BorrowTenderOutput findById(TenderInput input) {
        BorrowTenderOutput borrowTenderOutput = new BorrowTenderOutput();
        BorrowTender borrowTender = borrowTenderService.getTenderInfo(input.getTenderId());
        TransferUtil.transfer(borrowTender, borrowTenderOutput);
        if(borrowTender.getAccount() != null) {
            borrowTenderOutput.setAmount(BigDecimalUtils.centToYuan(borrowTender.getAccount()));
        }

        if(borrowTender.getTenderTranAward() != null) {
            borrowTenderOutput.setReward(borrowTender.getTenderTranAward());
        }

        if(borrowTender.getAccountInterest() != null) {
            borrowTenderOutput.setInterest(BigDecimalUtils.centToYuan(borrowTender.getAccountInterest()));
        }

        if(borrowTender.getPlatformInterest() != null) {
            borrowTenderOutput.getInterest().add(BigDecimalUtils.centToYuan(borrowTender.getPlatformInterest()));
        }

        if(borrowTender.getVoucherCouponId() != null) {
            borrowTenderOutput.setVoucherCouponId(borrowTender.getVoucherCouponId());
        }

        return borrowTenderOutput;
    }

    /**
     * 分页查询用户的投标列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/tender/getUserTenderByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<BorrowTenderVo> getUserTenderByPage(TenderListParam param){
        LOGGER.debug("分页获取用户投标列表入参:{}",param);
        return borrowTenderService.getUserTenderByPage(param);
    }
    /**
     * 分页查询自动投标子列表
     * @param param 查询条件 分页参数 自动投标ID 用户id
     * @return
     */
    @RequestMapping(value = "/tender/getUserAutoTenderByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<BorrowTenderVo> getUserAutoTenderByPage(AutoTenderExtParam param){
        return borrowTenderService.getUserAutoTenderByPage(param);
    }
    /**
     * 根据主键获取用户投标信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/tender/getUserBorrowTenderById",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowTenderOutput getUserBorrowTenderById(TenderDetailParam param){
        LOGGER.debug("获取用户投标详情入参:{}",param);
        return borrowTenderService.getUserBorrowTenderById(param);
    }

    /**
     * 满标复审 添加回款记录 更新投资信息,更新资金记录
     */
    @RequestMapping(value = "/tender/updateBorrowRecheck",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void borrowRecheck(RecheckInput input){
       LOGGER.debug("满标复审 添加回款记录 更新投资信息,更新资金记录 回款列表入参:{} 标的入参:{}",input.getCollectionList(),input.getBorrowRecheckInput());
        borrowTenderService.borrowRecheck(input);
    }

    /**
     * 分页查询债转投资记录
     * @param input
     * @return
     */
    @RequestMapping("/tender/transfer/list")
    public PageInfo pageByTenderId(TenderQueryInput input) {
        return borrowTenderService.getTenderList(input);
    }

    /**
     * 根据标的ID获取投标列表
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/tender/getBorrowTenderList",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BorrowTenderOutput> getBorrowTenderList(@RequestParam("borrowId") Integer borrowId){
        return borrowTenderService.getTenderListByBorrowId(borrowId);
    }

    /**
     * 标的撤销 投标撤回 更新资金记录
     * @param product
     */
    @RequestMapping(value = "/tender/withdrawTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void withdrawTender(@RequestParam("product")ProductOutput product){
        borrowTenderService.withdrawTender(product);
    }

    /**
     * 获取投标列表
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/tender/getByBorrowIdAndType",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BorrowTenderOutput> getByBorrowIdAndType(@RequestParam("borrowId") Integer borrowId){
        return borrowTenderService.getByBorrowIdAndTypeId(borrowId,1);
    }



    /**
     * 获取标的最大的一笔投标
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/tender/getMaxBorrowTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowTenderOutput getMaxBorrowTender(@RequestParam("borrowId")Integer borrowId){
        return borrowTenderService.getMaxBorrowTender(borrowId);
    }
    /**
     * 获取标的最后的一笔投标
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/tender/getLastBorrowTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    public BorrowTenderOutput getLastBorrowTender(@RequestParam("borrowId")Integer borrowId){
        return borrowTenderService.getLastBorrowTender(borrowId);
    }


    /**
     * 根据状态查询投标信息
     * @param status
     * @return
     */
    @RequestMapping(value = "/tender/getListByStatus",consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<BorrowTenderOutput> getListByStatus(@RequestParam("status") Integer status){
        return borrowTenderService.getListByStatus(null,status);
    }


    /**
     * 投标失败
     * @param orderId 订单号 投标订单号
     * @param errorMsg 错误信息
     */
    @RequestMapping(value = "/tender/tenderFailTask",consumes = MediaType.APPLICATION_JSON_VALUE)
    public void tenderFailTask(@RequestParam("orderId") String orderId,@RequestParam("errorMsg") String errorMsg){
        borrowTenderService.tenderFailTask(orderId,errorMsg,null);
    }

    /**
     * 获取用户抵扣券收益
     * @param userId
     * @return
     */
    @RequestMapping(value = "/tender/getTenderVoucherCount",consumes = MediaType.APPLICATION_JSON_VALUE)
    public double getTenderVoucherCount(@RequestParam("userId")String userId){

        return borrowTenderService.getTenderVoucherCount(userId).doubleValue();
    }

    /**
     * 分页获取标的的投标列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/tender/getProductTenderByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    public PageInfo<ProductTenderVo> getProductTenderByPage(BorrowParam param){
        return borrowTenderService.getProductTenderByPage(param);
    }

    /**
     * 获取标的 投标最多 扫尾的投标信息
     * @param productId
     * @return
     */
    @RequestMapping(value = "/tender/getTenderMaxAndLast",consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String,String> getTenderMaxAndLast(@RequestParam("productId")Integer productId){
        BorrowTenderOutput maxBorrowTender = borrowTenderService.getMaxBorrowTender(productId);
        BorrowTenderOutput lastBorrowTender = borrowTenderService.getLastBorrowTender(productId);
        Map<String,String> map = Maps.newHashMap();
        if(maxBorrowTender != null){
            map.put("max", StringUtil.hideMobile(maxBorrowTender.getMobile()));
        }
        if(lastBorrowTender != null){
            map.put("last",StringUtil.hideMobile(lastBorrowTender.getMobile()));
        }
        OpScoreTaskCacheInfo scoreTaskInfo = scoreTaskCache.getScoreTaskInfo(PublicConstant.TASK_ID_ROUND_OFF);
        map.put("last_score",scoreTaskInfo == null ? "5":String.valueOf(scoreTaskInfo.getScore()));

        return map;
    }

    /**
     * 获取用户散标投标冻结金额
     * @param userId
     * @return
     */
    @RequestMapping(value = "/tender/getUserTenderFrost",consumes = MediaType.APPLICATION_JSON_VALUE)
    public double getUserTenderFrost(@RequestParam("userId")String userId){
        return borrowTenderService.getUserTenderFrost(userId).doubleValue();
    }

}

package com.tuodao.bp.api.facade.client.transaction;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.input.TenderInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderQueryInput;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderExtParam;
import com.tuodao.bp.model.facade.traningcenter.input.BorrowParam;
import com.tuodao.bp.model.facade.traningcenter.input.TenderDetailParam;
import com.tuodao.bp.model.facade.traningcenter.input.TenderListParam;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import com.tuodao.bp.model.facade.traningcenter.output.ProductTenderVo;
import com.tuodao.bp.model.traningcenter.input.RecheckInput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @description: 投标
 * @author: 王艳兵
 * @date: 2017/9/12
 * @time: 18:11
 * @copyright: 拓道金服 Copyright (c) 2017
 * @author tookbra
 * @date 2017/9/18
 * @description
 */
@FeignClient("BIZ-TRANING-CENTER")
public interface BorrowTenderClient {

    /**
     * 投资信息
     * @param tenderInput
     * @return
     */
    @RequestMapping(value = "/tender/info", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowTenderOutput findById(TenderInput tenderInput);

    /**
     * 获取对应标的的投标列表
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/tender/getBorrowTenderList",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BorrowTenderOutput> getBorrowTenderList(@RequestParam("borrowId") Integer borrowId);

    /**
     * 分页查询用户的投标列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/tender/getUserTenderByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<BorrowTenderVo> getUserTenderByPage(TenderListParam param);

    /**
     * 分页查询自动投标子列表
     * @param param 自动投标id 用户id　分页参数
     * @return
     */
    @RequestMapping(value = "/tender/getUserAutoTenderByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<BorrowTenderVo> getUserAutoTenderByPage(AutoTenderExtParam param);
    /**
     * 根据主键获取用户投标信息
     * @param param
     * @return
     */
    @RequestMapping(value = "/tender/getUserBorrowTenderById",consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowTenderOutput getUserBorrowTenderById(TenderDetailParam param);

    /**
     * 满标复审 保存回款计划,更新投资信息,更新资金记录
     * @param input 标的信息 回款列表
     */
    @RequestMapping(value = "/tender/updateBorrowRecheck",consumes = MediaType.APPLICATION_JSON_VALUE)
    void borrowRecheck(RecheckInput input);

    /**
     * 分页查询债转投资记录
     * @param input
     * @return
     */
    @RequestMapping(value = "/tender/transfer/list",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<BorrowTenderOutput> getTenderTransferList(TenderQueryInput input);


    /**
     * 标的撤销 投标撤回 更新资金记录
     * @param product
     * @return 投标列表 包含投标成功与投标冻结
     */
    @RequestMapping(value = "/tender/withdrawTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    void withdrawTender(@RequestParam("product")ProductOutput product);

    /**
     * 获取投标信息
     * @return
     */
    @RequestMapping(value = "/tender/getByBorrowIdAndType",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BorrowTenderOutput> getByBorrowIdAndType();


    /**
     * 获取标的最大的一笔投标
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/tender/getMaxBorrowTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowTenderOutput getMaxBorrowTender(@RequestParam("borrowId")Integer borrowId);

    /**
     * 获取标的最后的一笔投标
     * @param borrowId
     * @return
     */
    @RequestMapping(value = "/tender/getLastBorrowTender",consumes = MediaType.APPLICATION_JSON_VALUE)
    BorrowTenderOutput getLastBorrowTender(@RequestParam("borrowId")Integer borrowId);

    /**
     * 投标生产者
     * @param executor
     */
    @RequestMapping(value = "/tender/tenderProducer",consumes = MediaType.APPLICATION_JSON_VALUE)
    void tenderProducer(TenderExecutor executor);

    /**
     * 根据状态查询投标信息
     * @param status
     * @return
     */
    @RequestMapping(value = "/tender/getListByStatus",consumes = MediaType.APPLICATION_JSON_VALUE)
    List<BorrowTenderOutput> getListByStatus(@RequestParam("status") Integer status);


    /**
     * 投标失败
     * @param orderId 订单号 投标订单号
     * @param errorMsg 错误信息
     */
    @RequestMapping(value = "/tender/tenderFailTask",consumes = MediaType.APPLICATION_JSON_VALUE)
    void tenderFailTask(@RequestParam("orderId") String orderId,@RequestParam("errorMsg") String errorMsg);

    /**
     * 获取用户抵扣券收益
     * @param userId
     * @return
     */
    @RequestMapping(value = "/tender/getTenderVoucherCount",consumes = MediaType.APPLICATION_JSON_VALUE)
    double getTenderVoucherCount(@RequestParam("userId")String userId);

    /**
     * 分页获取标的的投标列表
     * @param param
     * @return
     */
    @RequestMapping(value = "/tender/getProductTenderByPage",consumes = MediaType.APPLICATION_JSON_VALUE)
    PageInfo<ProductTenderVo> getProductTenderByPage(BorrowParam param);

    /**
     * 获取标的 投标最多 扫尾的投标信息
     * @param productId
     * @return
     */
    @RequestMapping(value = "/tender/getTenderMaxAndLast",consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<String,String> getTenderMaxAndLast(@RequestParam("productId")Integer productId);

    /**
     * 获取用户散标投标冻结金额
     * @param userId
     * @return
     */
    @RequestMapping(value = "/tender/getUserTenderFrost",consumes = MediaType.APPLICATION_JSON_VALUE)
    double getUserTenderFrost(@RequestParam("userId")String userId);
}

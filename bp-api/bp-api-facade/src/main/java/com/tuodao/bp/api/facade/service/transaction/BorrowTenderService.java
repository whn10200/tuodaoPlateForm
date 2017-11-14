package com.tuodao.bp.api.facade.service.transaction;

import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.traningcenter.output.BorrowTenderOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowProtocolVo;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import com.tuodao.bp.model.facade.traningcenter.output.TenderTransferVo;
import com.tuodao.bp.model.facade.traningcenter.output.TransfereeVo;


/**
 * @description:
 * @author: 王艳兵
 * @date: 2017/9/13
 * @time: 19:52
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface BorrowTenderService {

    /**
     * 前台普通用户投标
     * @param param    投标参数
     * @param tenderAuthCode 本地存放的校验码
     * @return
     */
    String tender(TenderParam param, String tenderAuthCode);


    /**
     * 受让详情 产品信息
     * @param tenderId
     * @return
     */
    TransfereeVo tenderInfo(Integer tenderId);

    /**
     * 生成回款计划
     *
     * @param output
     * @return
     */
    void borrowFullCheck(ProductOutput output);

    /**
     * 校验约标密码
     * @param product
     * @param orderPassword
     * @return
     */
    boolean verifyBorrowOrderPassword(ProductOutput product,String orderPassword);

    /**
     * 根据条件分页获取用户投标记录
     * @param param
     * @return
     */
    PageInfo<BorrowTenderVo> getUserTenderByPage(TenderListParam param);


    /**
     * 根据条件分页获取自动投标子列表,即 一个自动投标会分配到多个标的上进行投资的列表
     * @param param
     * @return
     */
    PageInfo<BorrowTenderVo> getUserAutoTenderByPage(AutoTenderExtParam param);

    /**
     * 获取用户投标的详细信息,标的信息
     * @param param
     * @return
     */
    BorrowTenderVo getTenderDetail(TenderDetailParam param);

    /**
     * 分页查询债转投资记录
     * @param param
     * @return
     */
    PageInfo<TenderTransferVo> getTenderTransferList(TransferQueryInfoParam param);


    /**
     * 定时任务投标 只要用于处理异常投标
     */
    void tenderCorn();

    /**
     * 请求银行撤销投标
     * @param borrowTender
     */
    void tenderCancelTask(BorrowTenderOutput borrowTender);

    /**
     * 根据投标信息获取借款协议书等组装内容
     * @param param 投标id 用户id
     * @return
     */
    BorrowProtocolVo getBorrowProtocol(TenderDetailParam param);

    void verifyUser(UserAccountInfo user,String payPassword);

    void verifyBorrow(ProductOutput product, TenderParam param, String tenderAuthCode,UserAccountInfo user);
    }

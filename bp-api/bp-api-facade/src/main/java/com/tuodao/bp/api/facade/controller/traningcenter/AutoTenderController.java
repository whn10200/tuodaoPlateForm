package com.tuodao.bp.api.facade.controller.traningcenter;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.service.transaction.AutoTenderService;
import com.tuodao.bp.api.facade.service.transaction.BorrowTenderService;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderExtParam;
import com.tuodao.bp.model.facade.traningcenter.input.AutoTenderParam;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderAppVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderListVo;
import com.tuodao.bp.model.facade.traningcenter.output.AutoTenderVo;
import com.tuodao.bp.model.facade.traningcenter.output.BorrowTenderVo;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.result.exception.MicroServiceException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 自动投标
 * @author: 王艳兵
 * @date: 2017/9/26
 * @time: 13:59
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/router")
public class AutoTenderController {

    @Autowired
    private AutoTenderService autoTenderService;

    @Autowired
    private BorrowTenderService borrowTenderService;

    @Autowired
    private UserAccountCache userAccountCache;


    /**
     * @param param 添加投标记录
     * @return
     */
    @RequestMapping("/tc/tender/add_auto_tender")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<String> saveAutoTender(AutoTenderParam param){
        UserAccountInfo user = userAccountCache.getUserAccoutInfo(param.getUserId());

        if(StringUtils.isBlank(user.getDepositNo())){
            throw new MicroServiceException(TransactError.BANK_STATUS_ERROR);
        }
        //期限设置错误
        if(param.getMinPeriod() > param.getMaxPeriod()){
            throw new MicroServiceException(TransactError.AUTO_TENDER_PERIOD_ERROR);
        }
        //金额设置错误
        if(param.getMinAccount() > param.getMaxAccount()){
            throw new MicroServiceException(TransactError.AUTO_TENDER_MONEY_ERROR);
        }

        autoTenderService.saveAutoTender(param);

        return RespResult.<String>create();
    }

    /**
     * 获取用户的自动投标设置信息
     * @param pojo
     * @return
     */
    @RequestMapping("/tc/tender/get_auto_tender")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<AutoTenderVo> getUserAutoTender(BasePojo pojo){
        AutoTenderVo vo = autoTenderService.getUserAutoTender(pojo.getUserId());
        //是否开通存管 app需要使用
        UserAccountInfo user = userAccountCache.getUserAccoutInfo(pojo.getUserId());
        vo.setOpen(user == null ? false: StringUtils.isNotBlank(user.getDepositNo()));
        return RespResult.<AutoTenderVo>create().setContent(vo);
    }

    /**
     * 关闭自动投标
     * @param pojo
     * @return
     */
    @RequestMapping("/tc/tender/close_auto_tender")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<String> closeAutoTender(BasePojo pojo){
        autoTenderService.closeAutoTender(pojo.getUserId());
        return RespResult.<String>create().setContent("自动投标关闭成功");
    }

    /**
     * 查看用户是否已经绑定存管
     * @param pojo
     * @return
     */
    @RequestMapping("/tc/tender/check_bank")
    @AccessToken(access = {AccessType.PC})
    public RespResult<Boolean> getUserBank(BasePojo pojo){
        UserAccountInfo user = userAccountCache.getUserAccoutInfo(pojo.getUserId());
        return RespResult.<Boolean>create().setContent(user == null ? false: StringUtils.isNotBlank(user.getDepositNo()));
    }


    /**
     * 分页查询自动投标成功的列表
     * @param pagePojo
     * @return
     */
    @RequestMapping("/tc/tender/auto_tender_list")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<PageInfo<AutoTenderListVo>> getAutoTenderList(PagePojo pagePojo){
        PageInfo<AutoTenderListVo> paging =  autoTenderService.getAutoTenderListByPage(pagePojo);
        return RespResult.<PageInfo<AutoTenderListVo>>create().setContent(paging);
    }

    /**
     * 获取某个自动投标的详细信息
     * @param param
     * @return
     */
    @RequestMapping("/tc/tender/auto_tender_detail")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<AutoTenderListVo> getAutoTenderDetail(AutoTenderExtParam param){
        AutoTenderListVo vo = autoTenderService.getAutoTenderDetail(param.getUserId(),param.getAutoTenderId());
        return RespResult.<AutoTenderListVo>create().setContent(vo);
    }

    /**
     * 根据自动投标id 查询自动投标成功的投标列表
     * @param param
     * @return
     */
    @RequestMapping("/tc/tender/sub_auto_tender_list")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<PageInfo<BorrowTenderVo>> getSubAutoTenderList(AutoTenderExtParam param){

        PageInfo<BorrowTenderVo> paging = borrowTenderService.getUserAutoTenderByPage(param);

        return RespResult.<PageInfo<BorrowTenderVo>>create().setContent(paging);
    }

}

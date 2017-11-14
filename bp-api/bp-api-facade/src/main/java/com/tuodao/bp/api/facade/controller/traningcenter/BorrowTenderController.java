package com.tuodao.bp.api.facade.controller.traningcenter;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowTenderClient;
import com.tuodao.bp.api.facade.service.transaction.AccountService;
import com.tuodao.bp.cache.basic.traningcenter.ReturnsCache;
import com.tuodao.bp.cache.basic.traningcenter.SessionCache;
import com.tuodao.bp.model.ReturnCacheInfo;
import com.tuodao.bp.model.TenderResult;
import com.tuodao.bp.model.annotation.AccessToken;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.constant.traningcenter.TenderConstant;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.model.facade.traningcenter.input.*;
import com.tuodao.bp.model.facade.traningcenter.output.*;
import com.tuodao.bp.model.traningcenter.output.AccountOutput;
import com.tuodao.bp.result.error.TransactError;
import com.tuodao.bp.utils.BigDecimalUtils;
import com.tuodao.bp.utils.BorrowUtils;
import com.tuodao.bp.api.facade.service.transaction.BorrowTenderService;
import com.tuodao.bp.result.exception.MicroServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @description: 投标
 * @author: 王艳兵
 * @date: 2017/9/13
 * @time: 19:10
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@RestController
@RequestMapping("/router")
public class BorrowTenderController {


    @Autowired
    private BorrowTenderService borrowTenderService;

    @Autowired
    private ReturnsCache returnsCache;

    @Autowired
    private SessionCache sessionCache;

    @Autowired
    private BorrowTenderClient borrowTenderClient;

    @Autowired
    private ProductClient productClient;

    @Autowired
    private AccountService accountService;
    /**
     * 前台普通用户投标
     * @return
     */
    @RequestMapping("/tc/tender/add_tender")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<String> tender(HttpServletRequest request, TenderParam param)throws Exception{
        //是否为系统清算时间
        if(BorrowUtils.isClearingTime()){
            throw new MicroServiceException(TransactError.TENDER_TIME_ERROR);
        }

        String authCode = getAuthCode(request);

        //将元转换为分
        param.setTenderMoney(BigDecimalUtils.yuanToCent(param.getTenderMoney()));

        String tenderKey = borrowTenderService.tender(param,authCode);

        return RespResult.<String>create().setContent(tenderKey);
    }

    /**
     * 校验约标密码
     * @return
     */
    @RequestMapping("/tc/tender/verify_order_password")
    @AccessToken(access = AccessType.APP)
    public RespResult<Boolean> verifyOrderPassword(TenderOrderParam param){
        ProductOutput product = productClient.getProduct(param.getProductId());
        if(product == null){
            throw new MicroServiceException(TransactError.BORROW_NOT_FOUND);
        }
        boolean b = borrowTenderService.verifyBorrowOrderPassword(product, param.getOrderPassword());
        return RespResult.<Boolean>create().setContent(b);
    }

    /**
     * 校验图形验证码
     * @return
     */
    @RequestMapping("/tc/tender/verify_code")
    @AccessToken(access = AccessType.APP)
    public RespResult<Boolean> verifyImageCode(HttpServletRequest request,TenderCodeParam param){
        String authCode = getAuthCode(request);
        return RespResult.<Boolean>create().setContent(param.getCode().equalsIgnoreCase(authCode));
    }

    /**
     * 获取验证码
     * @param request
     * @return
     */
    private String getAuthCode(HttpServletRequest request){

        String accessKey = request.getHeader(TenderConstant.ACCESS_ID);

        return sessionCache.getSession(accessKey);
    }

    /**
     * 根据投标key查询投标结果 指定查询次数后不进行查询
     * @param param
     * @return
     */
    @RequestMapping("/tc/tender/app_tender_result")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<TenderResult> getAppTenderResult(TenderResultParam param){

        TenderResult result = new TenderResult();

        if(param.getNum() > TenderConstant.TENDER_RESULT_MAX_QUERY ){
            result.setStatus(TenderConstant.TENDER_RESULT_PROGRESS);
            //TODO 等待url拼接
            result.setUrl(TenderConstant.TENDER_PROGRESS_URL);
            return RespResult.<TenderResult>create().setContent(result);
        }
        ReturnCacheInfo returnInfo = returnsCache.getReturnInfo(param.getTenderKey());

        if(returnInfo == null){
            result.setStatus(TenderConstant.TENDER_RESULT_FAIL);
            result.setErrorMsg("投标结果未查询到");
            return RespResult.<TenderResult>create().setContent(result);
        }

        if (returnInfo.getStatus() == TenderConstant.TENDER_RESULT_SUCCESS){
            result.setStatus(TenderConstant.TENDER_RESULT_SUCCESS);
            result.setUrl(getTenderSuccessUrl(returnInfo));
            return RespResult.<TenderResult>create().setContent(result);
        }
        result.setStatus(returnInfo.getStatus());
        result.setErrorMsg(returnInfo.getContent());
        return RespResult.<TenderResult>create().setContent(result);

    }

    /**
     * 组装投标成功后跳转到h5的链接
     * @param returnInfo
     * @return
     */
    private String getTenderSuccessUrl(ReturnCacheInfo returnInfo){
        StringBuffer sb = new StringBuffer(TenderConstant.TENDER_SUCCESS_URL);
                sb.append("?preInterest=").append(returnInfo.getPreInterest());
                sb.append("&amount=").append(returnInfo.getAmount());
                sb.append("&voucherType=").append(returnInfo.getVoucherType());
                sb.append("&voucherMoney=").append(returnInfo.getVoucherMoney());
        return sb.toString();
    }
    /**
     * 根据投标key查询投标结果 指定查询次数后不进行查询
     * @param param
     * @return
     */
    @RequestMapping("/tc/tender/tender_result")
    @AccessToken(access = {AccessType.PC})
    public RespResult<ReturnCacheInfo> getTenderResult(TenderResultParam param){
        //最大查询次数
        if(param.getNum() > TenderConstant.TENDER_RESULT_MAX_QUERY){
            ReturnCacheInfo returnInfo = new ReturnCacheInfo();
            returnInfo.setStatus(TenderConstant.TENDER_RESULT_PROGRESS);
            returnInfo.setContent("投标处理中,请稍后查看投标结果");
            returnsCache.deleteReturnInfo(param.getTenderKey());
            return RespResult.<ReturnCacheInfo>create().setContent(returnInfo);
        }
        ReturnCacheInfo returnInfo = returnsCache.getReturnInfo(param.getTenderKey());

        //请求次数最大,或者处理中
        if(returnInfo == null ){
            returnInfo = new ReturnCacheInfo();
            returnInfo.setStatus(TenderConstant.TENDER_RESULT_PROGRESS);
            returnInfo.setContent("投标处理中,请稍后查看投标结果");
        }

        return RespResult.<ReturnCacheInfo>create().setContent(returnInfo);
    }



    /**
     * 分页获取用户投资列表
     * @param param
     * @return
     */
    @RequestMapping("/tc/tender/tender_list")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<PageInfo<BorrowTenderVo>> tenderList(TenderListParam param) {
        PageInfo<BorrowTenderVo> paging = borrowTenderService.getUserTenderByPage(param);

        return RespResult.<PageInfo<BorrowTenderVo>>create().setContent(paging);
    }

    /**
     * 获取投标详细信息
     * @param param
     * @return
     */
    @RequestMapping("/tc/tender/tender_detail")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<BorrowTenderVo> getTenderDetail(TenderDetailParam param){
        BorrowTenderVo tenderDetail = borrowTenderService.getTenderDetail(param);
        return RespResult.<BorrowTenderVo>create().setContent(tenderDetail);
    }

    /**
     * 分页查询债转投资记录
     * @param param
     * @return
     */
    @RequestMapping("/tender/transfer/list")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult getTenderList(TransferQueryInfoParam param) {
        PageInfo<TenderTransferVo> pageInfo =  borrowTenderService.getTenderTransferList(param);
        return RespResult.create().setContent(pageInfo);
    }


    /**
     * 获取借款协议书组装数据
     */
    @RequestMapping("/tc/tender/borrow_protocol")
    @AccessToken(access = {AccessType.APP,AccessType.PC})
    public RespResult<BorrowProtocolVo> getBorrowProtocol(TenderDetailParam param){

        BorrowProtocolVo borrowProtocol = borrowTenderService.getBorrowProtocol(param);

        return RespResult.<BorrowProtocolVo>create().setContent(borrowProtocol);
    }

    /**
     * 分页获取标的 投标列表
     * @param param
     * @return
     */
    @RequestMapping("/tc/product/tender_list")
    public RespResult<PageInfo<ProductTenderVo>> getTenderList(BorrowParam param){
        PageInfo<ProductTenderVo> productTenderByPage = borrowTenderClient.getProductTenderByPage(param);

        return RespResult.<PageInfo<ProductTenderVo>>create().setContent(productTenderByPage);
    }

    /**
     * 获取标的首投扫尾
     * @param param
     * @return
     */
    @RequestMapping("/tc/product/tender_max_last")
    public RespResult<Map<String,String>> getTenderMaxAndLast(BorrowParam param){
        Map<String, String> tenderMaxAndLast = borrowTenderClient.getTenderMaxAndLast(param.getProductId());
        return RespResult.<Map<String,String>>create().setContent(tenderMaxAndLast);
    }

    /**
     * 投标确认页面参数请求
     * @return
     */
    @RequestMapping("/tc/tender/tender_affirm")
    public RespResult<BorrowAffirmVo> getTenderAffirm(BorrowParam param){
        AccountOutput account = accountService.getUserAccount(param.getUserId());
        BorrowAffirmVo vo = new BorrowAffirmVo();
        double balance = BigDecimalUtils.centToYuan(account.getBalance()).doubleValue();
        vo.setBalanceValue(String.valueOf(balance));
        vo.setBalance(BigDecimalUtils.formatMoney(balance));

        ProductOutput product = productClient.getProduct(param.getProductId());
        if(product == null){
            throw new MicroServiceException(TransactError.BORROW_NOT_FOUND);
        }
        double minAccount = BigDecimalUtils.centToYuan(product.getMinAmount()).doubleValue();
        vo.setMinAccountValue(String.valueOf(minAccount));
        vo.setMinAccount(BigDecimalUtils.formatMoney(minAccount));

        double surplusAccount = BigDecimalUtils.centToYuan(product.getSurplusInvestAmount()).doubleValue();
        vo.setSurplusAccountValue(String.valueOf(surplusAccount));
        vo.setSurplusAccount(BigDecimalUtils.formatMoney(surplusAccount));
        return RespResult.<BorrowAffirmVo>create().setContent(vo);
    }

}

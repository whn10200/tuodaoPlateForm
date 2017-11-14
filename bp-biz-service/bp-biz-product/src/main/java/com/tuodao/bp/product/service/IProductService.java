package com.tuodao.bp.product.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.model.business.product.input.ProductQueryInput;
import com.tuodao.bp.model.business.product.input.ProductVerifyInput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.product.output.ProductWithRecordOutput;
import com.tuodao.bp.model.business.product.requset.ProductRequestData;
import com.tuodao.bp.model.business.traningcenter.output.SelectClaimOutput;
import com.tuodao.bp.model.mq.ProductTenderMqInfo;
import com.tuodao.bp.model.mq.ProductUpdateMqInfo;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.product.db.model.basic.ProductExample;
import com.tuodao.bp.result.exception.BizFeignException;


/**
 * @Author wuchengjie
 * @Date 2017/9/1 0001 16:57
 * @Introduction
 */
public interface IProductService {

    /**
     * 获取 产品
     * @param id
     * @return
     */
    ProductOutput getProductById(Integer id);

    /**
     *  通过产品的id获取  产品的详细信息
     */
    ProductOutput getFrontProductById(Integer id);

    /**
     * 获取 产品列表
     * @param input
     * @return
     */
    PageInfo<ProductOutput> getProductPageList(ProductQueryInput input);

    /**
     * 获取更新散标信息
     * @param input
     * @return
     */
    Boolean updateProduct(ProductInput input);

    /**
     * 获取前端散标列表
     * @param input
     * @return
     */
    PageInfo<ProductOutput> getFrontBorrowListByPage(ProductQueryInput input);

    List<ProductOutput> getExpiredList(Boolean contain);

    Boolean doFinancialProductsReleaseDebts(Product product, List<SelectClaimOutput> collections)
            throws BizFeignException;

    /**
     * com.tuodao.bp.product.service.IProductService#
     * @参数
     * @方法描述: 定时任务调用,如果接口需要使用需要转出接口出参对象
     * @作者 lujing
     * @时间 2017/9/13 0013
     * ======================修改==================
     * @修改者
     * @修改内容: TODO
     * @修改时间 yyyy-MM-dd HH:mm:ss
     * ============================================
     **/
    List<Product> getProductList(ProductExample bean);


    List<ProductOutput> getListByIdList(List<Integer> idList);

    Boolean updateProductPoling();

    /**
     * 前端理财计划列表
     * @param input
     * @return
     */
    PageInfo<ProductOutput> getFrontPlanListByPage(ProductQueryInput input);

    List<ProductWithRecordOutput> getListWithRecordeByIdList(List<Integer> idList);

    /**
     * 将投资用户的购买金额 和 理财中具体产品金额匹配
     * @param tenderInput
     * @param voucherMap
     */
    Boolean matchingProduct(LinkedHashMap<String, Object> tenderInput, Map voucherMap);

    Integer purchaseProductByMqInfo(ProductTenderMqInfo tenderMqInfo);

    Integer joinPlan(ProductTenderMqInfo tenderMqInfo);

    Boolean updateProductStatus(ProductUpdateMqInfo mqInfo);

    Boolean updateProductStatus(ProductVerifyInput verifyInput);

    /**
     * 标的的复审
     * @param mqInfo
     * @return
     */
    Boolean reverifyBorrow(ProductUpdateMqInfo mqInfo);

    /**
     * 获取可发的未满标的新增标 （标红）
     * @return
     */
    List<ProductOutput> getIssueNewBorrowList();


    /**
     *  撤回理财计划
     */
    Boolean withdrawPlan(Integer planId);

    /**
     *  撤回理财计划底层标的
     */
    Boolean withdrawPlanBorrow(ProductVerifyInput verifyInput);

    Boolean withdrawPlanBorrow(ProductUpdateMqInfo mqInfo);

    /**
     *  撤回普通标的
     */
    Boolean withdrawBorrow(ProductVerifyInput verifyInput);

    Boolean publishBorrow(ProductVerifyInput verifyInput);

    /**
     * 查询自动投标是所需要的标的列表（只是散标）
     */
    List<ProductOutput> getAutoTenderingBorrowList();
    
    /**
	 * 调用存管发标接口，更改产品状态，记录审核记录
	 */
   String publishProduct(ProductRequestData requestData);
   /**
	 * 调用存管废标标接口，更改产品状态，记录审核记录
	 */
   String discardProduct(ProductRequestData requestData);

    Boolean reverifyBorrow(ProductVerifyInput input);

    Boolean afterDiscardProduct(Integer id);

    /**
 	 * 通过iProduct获取产品对象
 	 */
   ProductOutput getProductEntity(ProductInput productInput);

    /**
     *  查询理财计划下需要复审的普通标的
     * @return
     */
    List<ProductOutput> getPlanReverifyBorrowList();
    /**
     * 查询	复审的理财计划
     */
    List<ProductOutput> getReverifyPlanList();
    /**
	 * 标的还款
	 * 
	 * @return
	 */
   Boolean repaymentProduct(ProductOutput productOutput ,BorrowRepaymentInput borrowRepaymentInput,Boolean advance);

    ProductOutput getFrontProductByPcode(String code);
}

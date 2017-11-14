package com.tuodao.bp.api.facade.client.product;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.operation.output.TenderVoucherOutput;
import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.model.business.product.input.ProductQueryInput;
import com.tuodao.bp.model.business.product.input.ProductVerifyInput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.product.output.ProductWithRecordOutput;
import com.tuodao.bp.model.business.product.requset.ProductRequestData;

@RequestMapping("/product")
@FeignClient(value = "biz-product")
public interface ProductClient {

	/**
	 * 获取可发的未满标的新增标
	 *
	 * @return
	 */
	@RequestMapping(value = "/getIssueNewBorrowList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<ProductOutput> getIssueNewBorrowList();

	/**
	 * 根据产品的id 获取产品的信息
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ProductOutput getProduct(Integer id);

	/**
	 * 前端详情页面信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/getFrontProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ProductOutput getFrontProductById(Integer id);

	@RequestMapping(value = "/getFrontProductByPcode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ProductOutput getFrontProductByPcode(String id);


	/**
	 * 产品更新
	 *
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	ProductOutput updateProduct(ProductInput input);

	/**
	 * 获取的产品分页列表
	 *
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getProductPageList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	PageInfo<ProductOutput> getProductPageList(ProductQueryInput input);

	/**
	 * 前端散标列表
	 *
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getFrontBorrowListByPage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	PageInfo<ProductOutput> getFrontBorrowListByPage(ProductQueryInput input);

	/**
	 * 通过id 队列 获取产品对象队列
	 * @param idList
	 * @return
	 */
	@RequestMapping(value = "/getListByIdList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<ProductOutput> getListByIdList(List<Integer> idList);

	/**
	 * 匹配
	 *
	 * @return
	 */
	@RequestMapping(value = "/matchingProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	Boolean matchingProduct(List<LinkedHashMap<String, Object>> tenderInputList,
			@RequestParam Map<String, TenderVoucherOutput> voucherMap);

	/**
	 * 查询自动投标是所需要的标的列表（只是散标）
	 */
	@RequestMapping(value = "/getAutoTenderingBorrowList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	List<ProductOutput> getAutoTenderingBorrowList();

	/**
	 * 获取产品对象队列（其中包含满标的审核信息）
	 *
	 * @param idList
	 * @return
	 */
	@RequestMapping(value = "/getListWithRecordeByIdList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<ProductWithRecordOutput> getListWithRecordeByIdList(List<Integer> idList);

	/**
	 * 调用存管发标接口，更改产品状态，记录审核记录
	 * @return
	 */
	@RequestMapping(value = "/publishProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	String publishProduct(@RequestParam("requestData") ProductRequestData requestData);

	/**
	 * 调用存管废标标接口，更改产品状态，记录审核记录
	 * @return
	 */
	@RequestMapping(value="/discardProduct",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	String discardProduct(ProductRequestData requestData);

	/**
	 *  废标完成后，更改产品状态
	 * @param  id  产品id
	 * @return
	 */
	@RequestMapping(value="/afterDiscardProduct",method=RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_VALUE)
	Boolean afterDiscardProduct(Integer id);


	/**
	 * 标的满标复审
	 *
	 * @return
	 */
	@RequestMapping(value = "/reverifyBorrow", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean reverifyBorrow(ProductVerifyInput input);



	/**
	 * 查询	到期的理财计划的债权\
	 */
	@RequestMapping(value = "/getExpiredList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<ProductOutput> getExpiredList();



	/**
	 * 查询	复审的理财计划
	 */
	@RequestMapping(value = "/getReverifyPlanList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<ProductOutput> getReverifyPlanList();



	/**
	 * 查询理财计划下需要复审的普通标的
	 */
	@RequestMapping(value = "/getPlanReverifyBorrowList", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List<ProductOutput> getPlanReverifyBorrowList();



	/**
	 * 获取标的信息
	 * @param productInput
	 * @return
	 */
	@RequestMapping(value = "/getProductEntity", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ProductOutput getProductEntity(@RequestParam("productInput") ProductInput productInput);
	/**
	 * 标的还款
	 *
	 * @param productInput
	 * @return
	 */
	@RequestMapping(value = "/repaymentProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	String repaymentProduct(@RequestParam("requestData") String reqData);
}

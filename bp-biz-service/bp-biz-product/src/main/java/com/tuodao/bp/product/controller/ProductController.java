package com.tuodao.bp.product.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.business.operation.output.TenderVoucherOutput;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.model.business.product.input.ProductQueryInput;
import com.tuodao.bp.model.business.product.input.ProductVerifyInput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.product.output.ProductWithRecordOutput;
import com.tuodao.bp.model.business.product.requset.BorrowRepaymentReq;
import com.tuodao.bp.model.business.product.requset.ProductRequestData;
import com.tuodao.bp.product.constants.ProductConstant;
import com.tuodao.bp.product.constants.ProductResponseExceptionConstant;
import com.tuodao.bp.product.service.IBorrowRepayService;
import com.tuodao.bp.product.service.IProductService;
import com.tuodao.bp.result.exception.BizFeignException;

@RestController
@RequestMapping("/product")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private IProductService productService;

	@Value("${server.port}")
	private String port;

	@Autowired
	private IBorrowRepayService borrowRepayService;

	/**
	 * 获取可发的未满标的新增标
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getIssueNewBorrowList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductOutput> getIssueNewBorrowList() {
		logger.info("获取可发的未满标的新增标");
		List<ProductOutput> list = productService.getIssueNewBorrowList();
		return list;
	}

	/**
	 * 通过id 获取产品对象
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductOutput getProduct(Integer id) {
		logger.info("productId = {}", id);
		ProductOutput product = productService.getProductById(id);
		logger.info("product = {}", product);
		return product;
	}

	/**
	 * 通过id 获取产品对象
	 * 
	 * @param id
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getFrontProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductOutput getFrontProductById(Integer id) {
		logger.info("productId = {}", id);
		ProductOutput product = productService.getFrontProductById(id);
		logger.info("product = {}", product);
		return product;
	}

	@RequestMapping(value = "/getFrontProductByPcode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	ProductOutput getFrontProductByPcode(String code) {
		logger.info("productId = {}", code);
		ProductOutput product = productService.getFrontProductByPcode(code);
		logger.info("product = {}", product);
		return product;
	}
	/**
	 * 通过id 队列 获取产品对象队列
	 * 
	 * @param idList
	 * @return
	 */
	@RequestMapping(value = "/getListByIdList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductOutput> getListByIdList(List<Integer> idList) {
		logger.info("idList = {}", idList.toArray());
		List<ProductOutput> product = productService.getListByIdList(idList);
		logger.info("product = {}", product);
		return product;
	}

	/**
	 * 获取产品对象队列（其中包含满标的审核信息）
	 * 
	 * @param idList
	 * @return
	 */
	@RequestMapping(value = "/getListWithRecordeByIdList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductWithRecordOutput> getListWithRecordeByIdList(List<Integer> idList) {
		logger.info("idList = {}", idList.toArray());
		List<ProductWithRecordOutput> product = productService.getListWithRecordeByIdList(idList);
		logger.info("RecordOutput = {}", product);
		return product;
	}

	@RequestMapping(value = "/updateProduct", method = RequestMethod.POST)
	public Boolean updateProduct(ProductInput input) {
		logger.debug("name = {}", input.toString());

		Boolean ret = productService.updateProduct(input);

		return ret;
	}

	@RequestMapping(value = "/getProductPageList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<ProductOutput> getProductPageList(ProductQueryInput input) {
		logger.info("param input = {}", input);
		PageInfo<ProductOutput> product = productService.getProductPageList(input);
		logger.info("product = {}", product);
		return product;
	}

	/**
	 * 前端散标分页列表
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getFrontBorrowListByPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<ProductOutput> getFrontBorrowListByPage(ProductQueryInput input) {
		logger.info("param input = {}", input);
		PageInfo<ProductOutput> product = productService.getFrontBorrowListByPage(input);
		return product;
	}

	/**
	 * 前端理财计划分页列表
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getFrontPlanListByPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public PageInfo<ProductOutput> getFrontPlanListByPage(ProductQueryInput input) {
		logger.info("param input = {}", input);
		PageInfo<ProductOutput> product = productService.getFrontPlanListByPage(input);
		return product;
	}

	/**
	 * 理财计划 匹配
	 * 
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/matchingProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean matchingProduct(List<LinkedHashMap<String, Object>> input,
			@RequestParam Map<String, TenderVoucherOutput> voucherMap) throws JsonProcessingException {
		logger.info("param input = {}", input);

		Boolean result = true;
		if (!CollectionUtils.isEmpty(input)) {
			ProductConstant.NO_DEBTS = false;
			for (LinkedHashMap<String, Object> tenderInput : input) {

				result = productService.matchingProduct(tenderInput, voucherMap);

				if (ProductConstant.NO_DEBTS) {
					break;
				}
			}
		}
		return result;
	}

	/**
	 * 撤回理财计划底层标的 定时任务不能跑
	 */
	@RequestMapping(value = "/withdrawPlanBorrow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean withdrawPlanBorrow(ProductVerifyInput input) {
		logger.info("param borrowId = {}", input);
		Boolean result;
		result = productService.withdrawPlanBorrow(input);
		return result;
	}

	/**
	 * 查询自动投标是所需要的标的列表（只是散标）
	 */
	@RequestMapping(value = "/getAutoTenderingBorrowList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductOutput> getAutoTenderingBorrowList() {
		logger.info("查询自动投标是所需要的标的列表");
		List<ProductOutput> list = productService.getAutoTenderingBorrowList();
		return list;
	}

	/**
	 * 调用存管发标接口，更改产品状态，记录审核记录
	 *
	 * @return
	 */
	@RequestMapping(value = "/publishProduct", method = RequestMethod.POST)
	public String publishProduct(ProductRequestData requestData) {
		logger.info("调用存管发标接口，更改产品状态，记录审核记录");

		return productService.publishProduct(requestData);
	}

	/**
	 * 调用存管废标标接口，更改产品状态，记录审核记录
	 *
	 * @return
	 */
	@RequestMapping(value = "/discardProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String discardProduct(ProductRequestData requestData) {
		logger.info("调用存管废标标接口，更改产品状态，记录审核记录");

		return productService.discardProduct(requestData);
	}

	/**
	 *  废标完成后，更改产品状态
	 * @param  id  产品id
	 * @return
	 */
	@RequestMapping(value = "/afterDiscardProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean afterDiscardProduct(Integer id) {
		logger.info("废标完成后，更改产品状态");

		return productService.afterDiscardProduct(id);
	}


	/**
	 * 标的满标复审
	 *
	 * @return
	 */
	@RequestMapping(value = "/reverifyBorrow", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Boolean reverifyBorrow(ProductVerifyInput input) {

		Boolean b = productService.reverifyBorrow(input);
		return b;
	}

	/**
	 * 查询 到期的理财计划的债权\
	 * 
	 * @param
	 */
	@RequestMapping(value = "/getExpiredList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductOutput> getExpiredList() {
		// contain 是否只是今天的
		return productService.getExpiredList(true);
	}

	/**
	 * 查询理财计划下需要复审的普通标的
	 */
	@RequestMapping(value = "/getPlanReverifyBorrowList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProductOutput> getPlanReverifyBorrowList() {
		return productService.getPlanReverifyBorrowList();
	}

	/**
	 * 查询 复审的理财计划
	 */
	@RequestMapping(value = "/getReverifyPlanList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	List<ProductOutput> getReverifyPlanList() {
		return productService.getReverifyPlanList();
	}

	/**
	 * 通过iProduct获取产品对象
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getProductEntity", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductOutput getProductEntity(ProductInput productInput) {
		logger.info("productId = {}", productInput);
		ProductOutput product = productService.getProductEntity(productInput);
		logger.info("product = {}", product);
		return product;
	}

	/**
	 * 标的还款
	 * 
	 * @return
	 */
	@RequestMapping(value = "/repaymentProduct", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ProductOutput repaymentProduct(String reqData) {
		logger.info("BorrowRepaymentReq = {}", reqData);

		BorrowRepaymentReq requestData = (BorrowRepaymentReq) JSON.parseObject(reqData, BorrowRepaymentReq.class);
		// 参数不能为空
		if (null == requestData) {
			throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150506);
		}
		if (null == requestData.getId()) {
			// 还款ID不能为空
			throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150507);
		}
		if (null == requestData.getProductId()) {
			// 产品ID不能为空
			throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150508);
		}
		if (null == requestData.getProductStatus()) {
			// 产品状态不能为空
			throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150509);
		}

		ProductInput productInput = new ProductInput();
		productInput.setId(requestData.getProductId());
		productInput.setProductStatus(requestData.getProductStatus());
		ProductOutput productOutput = productService.getProductEntity(productInput);
		if (null == productOutput) {
			// 获取不到当前状态的产品信息
			throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150510);
		}
		BorrowRepaymentInput borrowRepaymentInput = borrowRepayService.getRepayInfoById(requestData.getId());
		if (null == borrowRepaymentInput) {
			// 获取不到还款计划信息
			throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150511);
		}
		productService.repaymentProduct(productOutput, borrowRepaymentInput, requestData.getAdvance());

		return null;
	}
}

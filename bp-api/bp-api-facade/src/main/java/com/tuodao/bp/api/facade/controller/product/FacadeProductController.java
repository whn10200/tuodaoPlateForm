package com.tuodao.bp.api.facade.controller.product;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.tuodao.bp.api.facade.client.depository.DepositoryBiddingClient;
import com.tuodao.bp.api.facade.client.depository.DepositoryBorrowerClient;
import com.tuodao.bp.api.facade.client.product.BorrowExpandClient;
import com.tuodao.bp.api.facade.client.transaction.BorrowTenderClient;
import com.tuodao.bp.model.business.product.output.BorrowExpandOutput;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.constant.product.ProductConstant;
import com.tuodao.bp.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.product.BorrowPlanTransferClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.product.RepayClient;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.service.product.IFacadeProductService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.model.business.product.input.ProductQueryInput;
import com.tuodao.bp.model.business.product.input.ProductVerifyInput;
import com.tuodao.bp.model.business.product.input.RepayQueryInput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentInfoOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.product.requset.BorrowRepaymentReq;
import com.tuodao.bp.model.business.product.requset.ProductRequestData;
import com.tuodao.bp.model.facade.product.input.FacadeProductInput;
import com.tuodao.bp.result.exception.BizFeignException;

@RequestMapping("/router")
@RestController
public class FacadeProductController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(FacadeProductController.class);

	@Autowired
	private ProductClient productClient;

	@Autowired
	private IFacadeProductService productService;

	@Autowired
	private DepositoryBiddingClient depositoryBiddingClient;

	@Autowired
	private RepayClient repayClient;

	@Autowired
	private BorrowExpandClient borrowExpandClient;

	@Autowired
	private BorrowTenderClient borrowTenderClient;

	@ResponseBody
	@RequestMapping(value = "/getProduct", method = RequestMethod.POST)
	public RespResult<ProductOutput> getQueryResult(ProductInput input) {
		long start = System.currentTimeMillis();
		logger.info("input:{}", input);
		Integer id = input.getId();
		ProductOutput product = productClient.getProduct(id);
		System.out.println(System.currentTimeMillis() - start);
		if (product == null) {
			return RespResult.<ProductOutput> create();
		}
		return RespResult.<ProductOutput> create().setContent(product);
	}



	/**
	 *  标的详细信息
	 *
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getFrontBorrowExpand", method = RequestMethod.POST)
	public RespResult<BorrowExpandOutput> getFrontBorrowExpand(FacadeProductInput input) {
		logger.info("input:{}", input);
		BorrowExpandOutput out = borrowExpandClient.getBorrowExpandByPcode(input.getProductCode());
		return RespResult.<BorrowExpandOutput> create().setContent(out);
	}

	/**
	 * 前端详情页面信息
	 *
	 * @param input
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getFrontProduct", method = RequestMethod.POST)
	public RespResult<ProductOutput> getFrontProduct(FacadeProductInput input) {
		long start = System.currentTimeMillis();
		logger.info("input:{}", input);

		ProductOutput product = productClient.getFrontProductById(input.getId());

		System.out.println(System.currentTimeMillis() - start);
		return RespResult.<ProductOutput> create().setContent(product);
	}

	@ResponseBody
	@RequestMapping(value = "/getProductPageList", method = RequestMethod.POST)
	public RespResult<PageInfo<ProductOutput>> getProductPageList(ProductQueryInput input) {
		long start = System.currentTimeMillis();
		logger.info("input:{}", input);

		PageInfo<ProductOutput> product = productClient.getProductPageList(input);

		System.out.println(System.currentTimeMillis() - start);
		return RespResult.<PageInfo<ProductOutput>> create().setContent(product);
	}

	@ResponseBody
	@RequestMapping(value = "/getFrontBorrowListByPage", method = RequestMethod.POST)
	public RespResult<PageInfo<ProductOutput>> getFrontBorrowListByPage(ProductQueryInput input) {

		PageInfo<ProductOutput> product = productClient.getFrontBorrowListByPage(input);

		return RespResult.<PageInfo<ProductOutput>> create().setContent(product);
	}

	/**
	 * 匹配理财计划任务
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/excutePurchasedProudct", method = RequestMethod.POST)
	public RespResult<PageInfo<ProductOutput>> excutePurchasedProudct(BasePojo basePojo) {

		productService.excutePurchased();

		return RespResult.<PageInfo<ProductOutput>> create();
	}



	/**
	 * 匹配理财计划任务
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getRepayInfoByBorrowId", method = RequestMethod.POST)
	public RespResult<PageInfo<ProductOutput>> getRepayInfoByBorrowId(RepayQueryInput input) {

		BorrowRepaymentInfoOutput tt = repayClient.getRepayInfoByBorrowId(input.getBorrowId());

		return RespResult.<PageInfo<ProductOutput>> create();
	}



	/**
	 * 调用存管成标接口，更改产品状态，记录审核记录
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/publishProduct", method = RequestMethod.POST)
	public RespResult<ProductOutput> publishProduct(HttpServletRequest request) {
		String reqData = request.getParameter("reqData");
		logger.info("reqData:{}", reqData);

		if (StringUtil.isEmpty(reqData)) {
			// 参数不能为空
			throw new BizFeignException(null);
		}
		ProductRequestData requestData = (ProductRequestData) JSON.parseObject(reqData, ProductRequestData.class);
		// 参数不能为空
		if (null == requestData) {
			throw new BizFeignException(null);
		}
		productClient.publishProduct(requestData);
		return RespResult.<ProductOutput> create().setContent(null);
	}
	

	/**
	 * 调用存管废标标接口，更改产品状态，记录审核记录
	 *
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/discardProduct", method = RequestMethod.POST)
	public RespResult<ProductOutput> discardProduct(HttpServletRequest request) {
		String reqData = request.getParameter("reqData");
		logger.info("reqData:{}", reqData);

		if (StringUtil.isEmpty(reqData)) {
			// 参数不能为空
			throw new BizFeignException(null);
		}
		ProductRequestData requestData = (ProductRequestData) JSON.parseObject(reqData, ProductRequestData.class);
		// 参数不能为空
		if (null == requestData) {
			throw new BizFeignException(null);
		}
		//订单号
		String orderNo = productClient.discardProduct(requestData);

		ProductOutput entity = productClient.getProduct(requestData.getProductReq().getId());
		// 封装存管需要的参数
		HashMap<String, String> map = new HashMap<>();
		// 订单号
		map.put(TDFN.orderNo, orderNo);
		// 产品编号
		map.put(TDFN.productCode, entity.getProductCode());
		//执行北京银行  废标接口
		HashMap<String, String>  inforet = depositoryBiddingClient.cancel(map);
		//成功状态
		Boolean infoBooelan  =  Boolean.getBoolean(inforet.get(TDFN.success));

		if(infoBooelan)
		{
			//更新投资
			borrowTenderClient.withdrawTender(entity);
			//更新产品
			productClient.afterDiscardProduct(entity.getId());
		}

		return RespResult.<ProductOutput> create().setContent(null);
	}

	/**
	 * 标的满标复审（没有调用北京银行接口）
	 *
	 * @return
	 */
	@RequestMapping(value = "/reverifyBorrow", method = RequestMethod.POST)
	public RespResult<Boolean> reverifyBorrow(ProductVerifyInput input) {

		productClient.reverifyBorrow(input);
		return RespResult.<Boolean> create();
	}


	/**
	 *  查询到期的理财计划的债权\
	 *
	 * @return
	 */
	@RequestMapping(value = "/getExpiredList", method = RequestMethod.POST)
	public RespResult<List<ProductOutput>> getExpiredList(ProductVerifyInput input) {

		 List<ProductOutput> productOutputs = productClient.getExpiredList();
//		 List<ProductOutput> productOutputs = productClient.getPlanReverifyBorrowList();
//		List<BorrowPlanTransferOutput> borrowPlanTransferOutputs = borrowPlanTransferClient.getPlanReverifyTransferList();
		return RespResult.<List<ProductOutput>> create().setContent(productOutputs);
	}
	
	 
	/**
	 * 标的还款
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/repaymentProduct", method = RequestMethod.POST)
	public RespResult<ProductOutput> repaymentProduct(HttpServletRequest request) {
		String reqData = request.getParameter("reqData");
		logger.info("reqData:{}", reqData);
		if (StringUtil.isEmpty(reqData)) {
			// 参数不能为空
			throw new BizFeignException(null);
		}
		productClient.repaymentProduct(reqData);
		return RespResult.<ProductOutput> create().setContent(null);
	}
}

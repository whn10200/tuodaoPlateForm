package com.tuodao.bp.api.facade.controller.product;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.product.BorrowExpandClient;
import com.tuodao.bp.api.facade.client.product.BorrowPlanTransferClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.product.RepayClient;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.config.UrlProperties;
import com.tuodao.bp.api.facade.service.product.IFacadeProductService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.model.business.product.input.ProductQueryInput;
import com.tuodao.bp.model.business.product.input.ProductVerifyInput;
import com.tuodao.bp.model.business.product.input.RepayQueryInput;
import com.tuodao.bp.model.business.product.output.*;
import com.tuodao.bp.model.business.product.requset.ProductRequestData;
import com.tuodao.bp.model.facade.product.input.FacadeProductInput;
import com.tuodao.bp.result.exception.BizFeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/router/app")
@RestController
public class FacadeAppProductController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(FacadeAppProductController.class);

	@Autowired
	private ProductClient productClient;

	@Autowired
	private UrlProperties urlProperties;

	@Autowired
	private BorrowExpandClient borrowExpandClient;

	/**
	 * app详情页面信息
	 *
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getAppProductDetail", method = RequestMethod.POST)
	public RespResult<ProductAppDetailOutput> getAppProductDetail(FacadeProductInput input) {
		long start = System.currentTimeMillis();
		logger.info("input:{}", input);

		ProductOutput product = productClient.getFrontProductByPcode(input.getProductCode());

		ProductAppDetailOutput out = new ProductAppDetailOutput();
		BeanUtils.copyProperties(product,out);

		out.setTypeAppUrl(urlProperties.getStaticResource()+product.getTypeAppUrl());
		out.setDetailUrl(urlProperties.getStaticResource()+product.getTypeAppUrl());
		System.out.println(System.currentTimeMillis() - start);
		return RespResult.<ProductAppDetailOutput> create().setContent(out);
	}


	/**
	 * app标的详细信息
	 *
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getAppBorrowExpand", method = RequestMethod.POST)
	public RespResult<BorrowExpandOutput> getAppBorrowExpand(FacadeProductInput input) {
		long start = System.currentTimeMillis();
		logger.info("input:{}", input);

		BorrowExpandOutput out = borrowExpandClient.getBorrowExpandByPcode(input.getProductCode());

		System.out.println(System.currentTimeMillis() - start);
		return RespResult.<BorrowExpandOutput> create().setContent(out);
	}

	/**
	 * 获取app的获取产品列表
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getAppBorrowListByPage", method = RequestMethod.POST)
	public RespResult<PageInfo<ProductAppOutput>> getAppBorrowListByPage(ProductQueryInput input) {

		PageInfo<ProductOutput> productPage = productClient.getFrontBorrowListByPage(input);

		List<ProductOutput>  list  = productPage.getList();
		List<ProductAppOutput> outList =  toAppOut( list );

		PageInfo<ProductAppOutput> result = new PageInfo<ProductAppOutput>(outList);
		result.setTotal(productPage.getTotal());
		result.setPages(productPage.getPages());
		result.setPageNum(productPage.getPageNum());
		result.setPageSize(productPage.getPageSize());

		return RespResult.<PageInfo<ProductAppOutput>> create().setContent(result);
	}





	/**
	 * 获取app首页产品列表 （理财 和散标）
	 * @param input
	 * @return
	 */
	@RequestMapping(value = "/getAppFrontProductList", method = RequestMethod.POST)
	public RespResult<Map<String,List>> getAppFrontProductList(ProductQueryInput input) {

		input.setCurrentPage(1);
		input.setPageSize(2);
		input.setProductType(0);
		PageInfo<ProductOutput> borrowPage = productClient.getFrontBorrowListByPage(input);
		List<ProductOutput>  borrowlist  = borrowPage.getList();
		List<ProductAppOutput> borrowOut =  toAppOut( borrowlist );

		input.setCurrentPage(1);
		input.setPageSize(2);
		input.setProductType(1);
		PageInfo<ProductOutput> planPage = productClient.getFrontBorrowListByPage(input);
		List<ProductOutput>  planList  = planPage.getList();
		List<ProductAppOutput> planOut=  toAppOut( planList );

		Map<String,List> map = new HashMap<>(6);
		map.put("borrowOut",borrowOut);
		map.put("planOut",planOut);

		return RespResult.<Map<String,List>> create().setContent(map);
	}


	private List<ProductAppOutput>  toAppOut(List<ProductOutput> list )
	{
		List<ProductAppOutput> outList = new ArrayList<>();
		list.forEach(productOutput -> {
			ProductAppOutput out = new ProductAppOutput();
			BeanUtils.copyProperties(productOutput,out);

			out.setTypeAppUrl(urlProperties.getStaticResource()+productOutput.getTypeAppUrl());
			outList.add(out);
		});

		return outList;
	}


}

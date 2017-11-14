package com.tuodao.bp.api.facade.controller.product;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.google.common.collect.Lists;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.api.facade.client.product.BorrowMappingBankClient;
import com.tuodao.bp.api.facade.client.product.BorrowPlanTransferClient;
import com.tuodao.bp.api.facade.client.product.ProductClient;
import com.tuodao.bp.api.facade.client.product.RepayClient;
import com.tuodao.bp.api.facade.common.BaseController;
import com.tuodao.bp.api.facade.service.product.IFacadeBorrowMappingBank;
import com.tuodao.bp.api.facade.service.product.IFacadeProductService;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.model.business.product.input.ProductQueryInput;
import com.tuodao.bp.model.business.product.input.ProductVerifyInput;
import com.tuodao.bp.model.business.product.input.RepayQueryInput;
import com.tuodao.bp.model.business.product.output.BorrowMappingBankOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentInfoOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.product.requset.BorrowRepaymentReq;
import com.tuodao.bp.model.business.product.requset.ProductRequestData;
import com.tuodao.bp.model.facade.product.input.FacadeProductInput;
import com.tuodao.bp.result.exception.BizFeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequestMapping("/router")
@RestController
public class FacadeProductTaskController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(FacadeProductTaskController.class);

	@Autowired
	private ProductClient productClient;

	@Autowired
	private IFacadeProductService productService;

	@Autowired
	private IFacadeBorrowMappingBank facadeBorrowMappingBank;

	@Autowired
	private BorrowMappingBankClient borrowMappingBankClient;


	/**
	 * 调用 垫付资金的接口 （定时任务）
	 */
	@RequestMapping(value = "/borrowerCompensation", method = RequestMethod.POST)
	public RespResult<Boolean> borrowerCompensation(BasePojo pojo)
	{
		//查询所有需要调用资金的 垫付标的
		List<BorrowMappingBankOutput> borrowMappingBankOutputs = borrowMappingBankClient.getCompensationList();

		List<Integer>  idList  =  Lists.transform(borrowMappingBankOutputs, out -> out.getBorrowId());

		if(!CollectionUtils.isEmpty(idList)){
			List<ProductOutput> productOutputs = productClient.getListByIdList(idList);
			for(BorrowMappingBankOutput output : borrowMappingBankOutputs)
			{
				Integer id = output.getBorrowId();
				ProductOutput product = productOutputs.stream().filter(productOutput ->
						productOutput.getId().equals(id)).collect(Collectors.toList()).get(0);

				facadeBorrowMappingBank.borrowerCompensation(output, product);

			}
		}


		return RespResult.<Boolean> create();
	}


}

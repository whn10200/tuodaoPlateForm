package com.tuodao.bp.product.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.jms.Queue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.tuodao.bp.cache.basic.traningcenter.ProjectInfoCache;
import com.tuodao.bp.cache.basic.traningcenter.RedisLock;
import com.tuodao.bp.cache.basic.useraccount.UserAccountCache;
import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.ProjectInfoCacheInfo;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.product.CustRepay;
import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.model.business.product.input.ProductQueryInput;
import com.tuodao.bp.model.business.product.input.ProductVerifyInput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.model.business.product.output.ProductDetailOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.model.business.product.output.ProductWithRecordOutput;
import com.tuodao.bp.model.business.product.requset.ProductAuditRecordReq;
import com.tuodao.bp.model.business.product.requset.ProductReq;
import com.tuodao.bp.model.business.product.requset.ProductRequestData;
import com.tuodao.bp.model.business.traningcenter.input.AccountLogExtInput;
import com.tuodao.bp.model.business.traningcenter.input.TenderExecutor;
import com.tuodao.bp.model.business.traningcenter.output.BackMoneyPlanOutput;
import com.tuodao.bp.model.business.traningcenter.output.SelectClaimOutput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.output.UserDepositOutput;
import com.tuodao.bp.model.constant.depository.BJFN;
import com.tuodao.bp.model.constant.depository.TDFN;
import com.tuodao.bp.model.enums.AccountLogType;
import com.tuodao.bp.model.mq.ProductTenderMqInfo;
import com.tuodao.bp.model.mq.ProductUpdateMqInfo;
import com.tuodao.bp.product.client.AccountLogClient;
import com.tuodao.bp.product.client.BorrowCollectionClient;
import com.tuodao.bp.product.client.DepositoryBiddingClient;
import com.tuodao.bp.product.client.UserClient;
import com.tuodao.bp.product.constants.ProductConstant;
import com.tuodao.bp.product.constants.ProductEnumConstant;
import com.tuodao.bp.product.constants.ProductResponseExceptionConstant;
import com.tuodao.bp.product.db.mapper.basic.BorrowPlanTransferMapper;
import com.tuodao.bp.product.db.mapper.basic.BorrowRepaymentMapper;
import com.tuodao.bp.product.db.mapper.basic.ProductAuditRecordMapper;
import com.tuodao.bp.product.db.mapper.basic.ProductMapper;
import com.tuodao.bp.product.db.model.basic.BorrowDefineType;
import com.tuodao.bp.product.db.model.basic.BorrowMappingBank;
import com.tuodao.bp.product.db.model.basic.BorrowPlanTransfer;
import com.tuodao.bp.product.db.model.basic.BorrowPlanTransferExample;
import com.tuodao.bp.product.db.model.basic.BorrowRepayment;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.product.db.model.basic.ProductAuditRecord;
import com.tuodao.bp.product.db.model.basic.ProductExample;
import com.tuodao.bp.product.db.model.basic.ProductWithBLOBs;
import com.tuodao.bp.product.service.IBorrowDefineTypeService;
import com.tuodao.bp.product.service.IBorrowMappingBankService;
import com.tuodao.bp.product.service.IBorrowPlanTransferService;
import com.tuodao.bp.product.service.IBorrowRepayService;
import com.tuodao.bp.product.service.IProductAuditRecordService;
import com.tuodao.bp.product.service.IProductDetailService;
import com.tuodao.bp.product.service.IProductService;
import com.tuodao.bp.product.utils.CheckProductUtil;
import com.tuodao.bp.product.utils.ProductUtil;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.BDC;
import com.tuodao.bp.utils.BorrowPlan;
import com.tuodao.bp.utils.BorrowUtils;
import com.tuodao.bp.utils.DateUtil;
import com.tuodao.bp.utils.FinancingInfo;

/**
 * @Author wuchengjie
 * @Date 2017/9/1 0001 16:58
 * @Introduction 产品服务类
 */

@Transactional
@Service
public class ProductSerivceImpl implements IProductService {

	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(ProductSerivceImpl.class);

	@Autowired
	private ProductMapper productMapper;

	@Autowired
	private BorrowPlanTransferMapper borrowPlanTransferMapper;

	@Autowired
	private IBorrowDefineTypeService borrowDefineTypeService;

	@Autowired
	private IBorrowRepayService borrowRepayService;

	@Autowired
	private JmsMessagingTemplate jmsTemplate;

	@Autowired
	private IProductDetailService productDetailService;

	@Autowired
	private IBorrowPlanTransferService borrowPlanTransferService;

	@Autowired
	private ProductAuditRecordMapper productAuditRecordMapper; // 审核信息

	@Autowired
	private IProductAuditRecordService productAuditRecordService;

	@Resource(name = "productTenderQueue")
	private Queue productTenderQueue;

	@Resource(name = "productUpdateQueue")
	private Queue productUpdateQueue;

	@Resource
	protected ProjectInfoCache projectInfoCache;

	@Autowired
	private BorrowRepaymentMapper repaymentMapper;

	@Resource
	private RestTemplate restTemplate;

	@Autowired
	private UserAccountCache userAccountCache;

	private IBorrowMappingBankService borrowMappingBankService;
	@Autowired
	private UserClient userClient;
	@Autowired
	private BorrowCollectionClient borrowCollectionClient;
	@Autowired
	private AccountLogClient accountLogClient;
	@Autowired
	private DepositoryBiddingClient depositoryBiddingClient;

	@Autowired
	private BorrowRepaymentMapper borrowRepaymentMapper;

	@Override
	public ProductOutput getProductById(Integer id) {
		Product product = productMapper.selectByPrimaryKey(id);
		if (product == null) {
			return null;
		}
		ProductOutput out = new ProductOutput();
		BeanUtils.copyProperties(product, out);
		return out;
	}

	/**
	 * 通过产品的id获取 产品的详细信息
	 */
	@Override
	public ProductOutput getFrontProductById(Integer id) {
		ProductWithBLOBs product = productMapper.selectByPrimaryKey(id);
		List<BorrowDefineType> typeList = borrowDefineTypeService.getEnableBorrowType();
		ProductOutput out = ProductUtil.doTranfer(product, typeList);
		return out;
	}

	/**
	 * 通过产品的code获取 产品的详细信息
	 */
	@Override
	public ProductOutput getFrontProductByPcode(String code) {
		ProductExample example = new ProductExample();
		example.createCriteria().andProductCodeEqualTo(code);

		List<ProductWithBLOBs> productList = productMapper.selectByExampleWithBLOBs(example);

		if(CollectionUtils.isEmpty(productList)){
			return null;
		}
		ProductWithBLOBs product = productList.get(0);
		List<BorrowDefineType> typeList = borrowDefineTypeService.getEnableBorrowType();
		ProductOutput out = ProductUtil.doTranfer(product, typeList);
		return out;
	}

	/**
	 * 根据条件 分页显示产品的列表
	 *
	 * @param input
	 * @return
	 */
	@Override
	public PageInfo<ProductOutput> getProductPageList(ProductQueryInput input) {

		ProductExample example = new ProductExample();
		// 电话号码

		PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
		List<Product> demoList = productMapper.selectByExample(example);

		ImmutableList<ProductOutput> resultList = FluentIterable.<Product> from(demoList)
				.transform(new Function<Product, ProductOutput>() {
					@Override
					public ProductOutput apply(Product input) {
						ProductOutput out = new ProductOutput();
						BeanUtils.copyProperties(input, out);
						return out;
					}
				}).toList();

		PageInfo<ProductOutput> result = new PageInfo<ProductOutput>(resultList);
		Page<Product> page = (Page<Product>) demoList;
		result.setTotal(page.getTotal());
		result.setPages(page.getPages());
		return result;
	}

	/**
	 * 更新产品基本属性（大后台审核用）
	 *
	 * @param input
	 * @return
	 */
	@Override
	public Boolean updateProduct(ProductInput input) {

		try {
			ProductWithBLOBs product = new ProductWithBLOBs();
			BeanUtils.copyProperties(input, product);
			productMapper.updateByPrimaryKeySelective(product);
		} catch (Exception e) {
			logger.error("{}产品更新失败：{}", input.getId(), e.getCause());
		}

		return false;
	}

	/**
	 * 更新产品的轮询标志位 （每次自动投标后必把标志位置为1）
	 *
	 * @return
	 */
	@Override
	public Boolean updateProductPoling() {

		try {
			ProductWithBLOBs product = new ProductWithBLOBs();
			product.setIsTurn("1");
			ProductExample example = new ProductExample();
			example.createCriteria().andIsAutoInvestEqualTo(1); // 自动投标的标的
			productMapper.updateByExampleWithBLOBs(product, example);
		} catch (Exception e) {
			logger.error("{}更新产品的轮询标志位失败：{}", e.getCause());
		}

		return false;
	}

	/**
	 * 前端理财计划查询规则 （简单查询）
	 *
	 * @param input
	 * @return
	 */
	@Override
	@Cacheable(cacheNames = RedisConstans.CACHE_PLAN, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_PLAN+#p0.currentPage+'_'+#p0.isJunior", cacheManager = "cacheManagerSecond")
	public PageInfo<ProductOutput> getFrontPlanListByPage(ProductQueryInput input) {

		List<BorrowDefineType> typeList = borrowDefineTypeService.getEnableBorrowType();

		Integer currentPage = input.getCurrentPage();
		Integer sizeleft = input.getPageSize();

		PageHelper.startPage(currentPage, sizeleft);
		List<ProductWithBLOBs> tenderingList = productMapper.selectPlanByPage(input);

		ImmutableList<ProductOutput> resultList = FluentIterable.<ProductWithBLOBs> from(tenderingList)
				.transform(new Function<ProductWithBLOBs, ProductOutput>() {
					@Override
					public ProductOutput apply(ProductWithBLOBs input) {
						ProductOutput out = new ProductOutput();
						BeanUtils.copyProperties(input, out);
						return out;
					}
				}).toList();

		PageInfo<ProductOutput> result = new PageInfo<ProductOutput>(ProductUtil.doTranfer(tenderingList, typeList));
		Page<ProductWithBLOBs> page = (Page<ProductWithBLOBs>) tenderingList;
		result.setTotal(page.getTotal());

		return result;
	}

	/**
	 * 前端散标 和 理财计划 查询规则
	 *
	 * @param input
	 * @return
	 */
	@Override
	@Cacheable(cacheNames = RedisConstans.CACHE_BORROW, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_BORROW+#p0.productType+'_'+#p0.currentPage+'_'+#p0.isJunior", cacheManager = "cacheManagerSecond")
	public PageInfo<ProductOutput> getFrontBorrowListByPage(ProductQueryInput input) {

		List<BorrowDefineType> typeList = borrowDefineTypeService.getEnableBorrowType();

		List<ProductWithBLOBs> borrowList = new ArrayList<>();

		Integer currentPage = input.getCurrentPage();
		Integer pageSize = input.getPageSize();
		Integer offSizeBegin = (currentPage - 1) * pageSize;
		Integer offSizeEnd = (currentPage) * pageSize;
		Integer sizeleft = input.getPageSize();

		// PageHelper.startPage(currentPage, sizeleft);

		Integer tenderingTotal = productMapper.selectTenderingBorrowAmount(input);

		HashMap<String, Integer> pageMap = new HashMap<String, Integer>();
		pageMap.put("offSizeBegin", offSizeBegin);
		pageMap.put("total", tenderingTotal);
		pageMap.put("offSizeEnd", offSizeEnd);
		pageMap.put("sizeleft", sizeleft);

		pageMap = resetLimit(pageMap, input);

		if (input.getLimitSize() > 0) {
			List<ProductWithBLOBs> tenderingBorrow = productMapper.selectTenderingBorrow(input);
			// Page<ProductWithBLOBs> tenderpage =
			// (Page<ProductWithBLOBs>)tenderingBorrow;
			sizeleft = pageMap.get("sizeleft");
			borrowList.addAll(tenderingBorrow);
			if (sizeleft <= 0) {
				return wrapToPage(input, borrowList, typeList);
			}

			Integer timeTotal = productMapper.selectTimeBorrowAmount(input);
			if (timeTotal > 0) {
				pageMap.put("total", timeTotal);
				pageMap = resetLimit(pageMap, input);
				sizeleft = pageMap.get("sizeleft");
			}
			if (input.getLimitSize() > 0) {
				List<ProductWithBLOBs> timeBorrow = productMapper.selectTimeBorrow(input);
				borrowList.addAll(timeBorrow);
				if (sizeleft <= 0) {
					return wrapToPage(input, borrowList, typeList);
				}

				Integer fullTotal = productMapper.selectFullBorrowAmount(input);
				if (fullTotal > 0) {
					pageMap.put("total", fullTotal);
					pageMap = resetLimit(pageMap, input);
				}
				if (input.getLimitSize() > 0) {
					List<ProductWithBLOBs> fullBorrow = productMapper.selectFullBorrow(input);
					borrowList.addAll(fullBorrow);

					return wrapToPage(input, borrowList, typeList);
				}
			}
		}

		return null;
	}

	/**
	 * 重新设值
	 *
	 * @param pageMap
	 * @param input
	 * @return
	 */
	private HashMap resetLimit(HashMap pageMap, ProductQueryInput input) {

		Integer offSizeBegin = (Integer) pageMap.get("offSizeBegin");
		Integer tenderingTotal = (Integer) pageMap.get("total");
		Integer offSizeEnd = (Integer) pageMap.get("offSizeEnd");
		Integer sizeleft = (Integer) pageMap.get("sizeleft");

		Integer size = 0;
		// 偏移量大于于总数
		if (offSizeBegin > tenderingTotal) {
			offSizeBegin -= tenderingTotal.intValue();
			offSizeEnd -= tenderingTotal.intValue();
		} else if (offSizeBegin <= tenderingTotal && offSizeEnd > tenderingTotal) {
			offSizeBegin = 0;
			offSizeEnd -= tenderingTotal.intValue();
			size = tenderingTotal - offSizeBegin;
			sizeleft -= size;
		} else if (offSizeEnd <= tenderingTotal) {
			// offSizeBegin= tenderingTotal.intValue()-offSizeBegin;
			offSizeBegin = 0;
			offSizeEnd = 0;
			size = sizeleft;
			sizeleft = 0;
		}
		if (size > 0) {
			input.setLimitBegin(offSizeBegin);
			input.setLimitSize(size);
		} else {
			input.setLimitBegin(0);
			input.setLimitSize(0);
		}

		pageMap.put("offSizeBegin", offSizeBegin);
		pageMap.put("total", tenderingTotal);
		pageMap.put("offSizeEnd", offSizeEnd);
		pageMap.put("sizeleft", sizeleft);

		return pageMap;
	}

	/**
	 * 包装成 分页对象
	 *
	 * @param input
	 * @param tenderingBorrow
	 * @param typeList
	 * @return
	 */
	private PageInfo<ProductOutput> wrapToPage(ProductQueryInput input, List<ProductWithBLOBs> tenderingBorrow,
			List<BorrowDefineType> typeList) {
		// 获取已经发布散标的总数
		Integer amount = productMapper.getBorrowAmount(input);
		PageInfo<ProductOutput> result = new PageInfo<ProductOutput>(ProductUtil.doTranfer(tenderingBorrow, typeList));

		Integer pageSize = input.getPageSize(); // 每页的大小
		Integer plusPage = amount % pageSize > 1 ? 1 : 0; // 是否多一页
		Integer pages = plusPage + amount / pageSize;
		result.setTotal(amount);
		result.setPages(pages);
		result.setPageNum(input.getCurrentPage());
		result.setPageSize(input.getPageSize());

		return result;
	}

	/**
	 * 查询 到期的理财计划的债权
	 */
	@Override
	public List<ProductOutput> getExpiredList(Boolean contain) {

		List<ProductWithBLOBs> productWithBLOBsList = productMapper.selectExpiredList(contain);

		ImmutableList<ProductOutput> resultList = FluentIterable.<ProductWithBLOBs> from(productWithBLOBsList)
				.transform(new Function<ProductWithBLOBs, ProductOutput>() {
					@Override
					public ProductOutput apply(ProductWithBLOBs input) {
						ProductOutput out = new ProductOutput();
						BeanUtils.copyProperties(input, out);
						return out;
					}
				}).toList();

		return resultList;
	}

	/**
	 * 理财计划释放债权 （不允许还款） 20：30 根据理财计划到期 （用户不能主动退出理财计划） tenderList: 剩余没有还款的
	 */

	@Override
	public Boolean doFinancialProductsReleaseDebts(Product product, List<SelectClaimOutput> collections)
			throws BizFeignException {

		// 检验产品
		CheckProductUtil.checkProductDebts(product);
		Integer id = product.getId();
		logger.debug("理财产品{}进行释放：", id);
		// 将此理财计划的所有投资记录（底层标的的） 并且得到释放前所剩的本金
		if (collections != null && !collections.isEmpty()) {

			// 获取相应的标的记录
			List<HashMap<String, String>> borrowList = productMapper.getOrginalByPlanId(id);
			// 获取还款信息
			List<HashMap<String, String>> repayList = repaymentMapper.getMinUnpaidByPlanId(id);

			for (SelectClaimOutput tender : collections) {

				String borrowId = "1";

				Map borrowInfo = getMapByParam(borrowList, borrowId, "borrowId");
				Map repaynfo = getMapByParam(repayList, borrowId, "borrowId");

				// 生成债权记录
				BorrowPlanTransfer transfer = new BorrowPlanTransfer();
				transfer.setUserId(tender.getUserId());
				transfer.setTenderId(tender.getTenderId());
				transfer.setStatus(1);
				transfer.setType(1);
				transfer.setAccount(tender.getPreCapital());
				transfer.setAccountYes(new BigDecimal(0));
				transfer.setBorrowName(String.valueOf(borrowInfo.get("name")));
				transfer.setStartPeriod(Integer.valueOf(repaynfo.get("startPeriod").toString()));

				Integer left = Integer.valueOf(repaynfo.get("period").toString())
						- Integer.valueOf(borrowInfo.get("startPeriod").toString());
				transfer.setLeftPeriod(left);
				transfer.setPeriod(Integer.valueOf(repaynfo.get("period").toString()));

				transfer.setPeriodType(Integer.valueOf(borrowInfo.get("periodType").toString()));

				BigDecimal apr = new BigDecimal((Double) borrowInfo.get("apr"));
				transfer.setApr(apr);
				transfer.setRaymentType(Integer.valueOf(borrowInfo.get("raymentType").toString()));

				transfer.setAddTime(new Date());

			}
		}

		return true;
	}

	/**
	 * 获取想对应的Map值
	 */
	private Map getMapByParam(List<HashMap<String, String>> inList, String key, String property) {
		for (HashMap<String, String> map : inList) {
			if (key.equals(map.get(property))) {
				return map;
			}
		}
		return null;
	}

	@Override
	public List<Product> getProductList(ProductExample bean) {
		return productMapper.selectByExample(bean);
	}

	@Override
	public List<ProductOutput> getListByIdList(List<Integer> idList) {

		ProductExample example = new ProductExample();
		ProductExample.Criteria criteria = example.createCriteria();
		criteria.andIdIn(idList);

		List<ProductWithBLOBs> productWithBLOBsList = productMapper.selectByExampleWithBLOBs(example);

		ImmutableList<ProductOutput> resultList = FluentIterable.<ProductWithBLOBs> from(productWithBLOBsList)
				.transform(new Function<ProductWithBLOBs, ProductOutput>() {
					@Override
					public ProductOutput apply(ProductWithBLOBs input) {
						ProductOutput out = new ProductOutput();
						BeanUtils.copyProperties(input, out);
						return out;
					}
				}).toList();

		return resultList;
	}

	/**
	 * 通过id list 查询到了相对应的满标信息
	 *
	 * @param idList
	 * @return
	 */
	@Override
	public List<ProductWithRecordOutput> getListWithRecordeByIdList(List<Integer> idList) {

		List<ProductWithRecordOutput> retList = new ArrayList<>();
		// 满标的记录
		List<ProductAuditRecord> recordList = productAuditRecordService.getProductAuditRecord(idList);

		List<ProductOutput> resultList = getListByIdList(idList);

		resultList.forEach(s -> {
			ProductWithRecordOutput out = new ProductWithRecordOutput();
			BeanUtils.copyProperties(s, out);
			recordList.forEach(record -> {
				if (record.getProductId().equals(s.getId())) {
					out.setAuditName(record.getAuditName());
					out.setAuditRemark(record.getAuditRemark());
					out.setAuditResult(record.getAuditResult());
					out.setAuditTime(record.getAuditTime());
					out.setAuditTime(record.getAuditTime());
				}
			});
			retList.add(out);
		});

		return retList;
	}

	/**
	 * 将投资用户的购买金额 和 理财中具体产品金额匹配*
	 * 
	 * @param tenderInput
	 * @param voucherMap
	 */
	@Override
	public Boolean matchingProduct(LinkedHashMap<String, Object> tenderInput, Map voucherMap) {

		String userId = (String) tenderInput.get("userId");
		// 已经加入的资金
		BigDecimal tenderAmount = new BigDecimal((double) tenderInput.get("preAccount"));
		// 已经匹配的金额
		BigDecimal account = new BigDecimal((double) tenderInput.get("account"));

		// 投资的id
		Integer tenderId = (Integer) tenderInput.get("id");
		// 投资的理财产品id
		Integer productId = (Integer) tenderInput.get("productId");

		// 剩余未匹配金额
		BigDecimal leftAmount = tenderAmount.subtract(account);

		// 抵扣券
		Integer voucherId = (Integer) tenderInput.get("voucherId");

		// 加息券
		Integer voucherId2 = (Integer) tenderInput.get("voucherCouponId");

		try {
			// 1. 和新增借款进行匹配
			logger.info("用户id[{}],新增借款进行匹配，资金剩余{}", userId, leftAmount.longValue());
			List<Product> newBorrowingList = getBorrowingList(leftAmount, true);
			leftAmount = doBorrowMatching(newBorrowingList, leftAmount, userId, tenderId, productId, voucherMap);
			// 新曾结束

			// 2. 和续贷标进行匹配
			logger.info("续贷标进行匹配，资金剩余{}", leftAmount.longValue());
			List<Product> notNewBorrowingList = getBorrowingList(leftAmount, false);
			leftAmount = doBorrowMatching(notNewBorrowingList, leftAmount, userId, tenderId, productId, voucherMap);
			// 续贷标结束

			// 3. 和债转标进行匹配 从理财计划到期释放的
			logger.info("理财计划债转标进行匹配，资金剩余{}", leftAmount.longValue());
			List<BorrowPlanTransfer> debtsPlanList = getBebtsList(leftAmount, true);
			leftAmount = doBebtsMatching(debtsPlanList, leftAmount, userId, tenderId, productId, voucherMap);
			// 债转标结束

			// 3. 和债转标进行匹配 内部账号债权转让
			logger.info("内部账号债转标进行匹配，资金剩余{}", leftAmount.longValue());
			List<BorrowPlanTransfer> debtsInsideList = getBebtsList(leftAmount, false);
			leftAmount = doBebtsMatching(debtsInsideList, leftAmount, userId, tenderId, productId, voucherMap);
			// 债转标结束

			if (leftAmount.intValue() > 0) {
				logger.info("债权不足，资金剩余{}", leftAmount.longValue());
				ProductConstant.NO_DEBTS = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("用户id[{}],理财中具体产品金额匹配，error : {}", userId, e);
			throw new BizFeignException(ProductResponseExceptionConstant.MATCHING_PRODUCT_ERROR);
		}

		return true;
	}

	/**
	 * 通过消息队列信息（已经匹配过）
	 *
	 * @param tenderMqInfo
	 * @return
	 */
	@Override
	public Integer purchaseProductByMqInfo(ProductTenderMqInfo tenderMqInfo) {

		Integer borrowId = tenderMqInfo.getBorrowId();
		BigDecimal tenderAmount = tenderMqInfo.getTenderAmount();
		Integer productType = tenderMqInfo.getType();

		// 标的
		if (productType == 1) {
			Product product = productMapper.selectByPrimaryKey(borrowId);

			if (product == null) {
				logger.warn("购买理财计划返回  ,无此产品");
				return ProductResponseExceptionConstant.PRODUCT_NOT_MATCH;
			}

			BigDecimal mate = product.getMateAmount().add(tenderAmount); // 已经匹配到的金额
			BigDecimal left = product.getSurplusInvestAmount(); // 剩余可以加入的金额
																// .subtract(tenderAmount)
			if (mate.compareTo(product.getBorrowAmount()) <= 0 && left.longValue() >= 0) {
				product.setMateAmount(mate);
				// product.setSurplusInvestAmount(left);
				product.setIsJion(1);
				// 判断是否满标
				if (mate.compareTo(product.getBorrowAmount()) == 0 && left.longValue() == 0) {
					product.setBorrowFullStatus(1);
					product.setProductStatus(6);
				}

			} else {
				// 标的超募
				logger.warn("购买理财计划返回  标的总额：{}, 标的已匹配：{}, 标的余额：{},投资金额: {},投资用户:{}", product.getBorrowAmount(),
						product.getMateAmount(), product.getSurplusInvestAmount(), tenderMqInfo.getTenderAmount(),
						tenderMqInfo.getUserId());
				return ProductResponseExceptionConstant.PRODUCT_OVER_RAISED;
			}

		} else if (productType == 2) { // 债权
			BorrowPlanTransfer transfer = borrowPlanTransferMapper.selectByPrimaryKey(borrowId);

			if (transfer == null) {
				logger.warn("购买理财计划返回  ,无此债权");
				return ProductResponseExceptionConstant.TRANSFER_NOT_MATCH;
			}

			BigDecimal mate = transfer.getAccountSuccessYes().subtract(tenderAmount);

			if (mate.compareTo(transfer.getAccount()) <= 0) {
				transfer.setAccountSuccessYes(mate);
				transfer.setAccountYes(mate);

				if (mate.compareTo(transfer.getAccount()) == 0) {
					transfer.setStatus(2);
					transfer.setSuccessTime(new Date());
				}

			} else {
				logger.warn("购买理财计划返回  债权总额：{}, 债权已匹配：{} ,投资金额: {},投资用户:{}", transfer.getAccount(),
						transfer.getAccountSuccessYes(), tenderMqInfo.getTenderAmount(), tenderMqInfo.getUserId());
				return ProductResponseExceptionConstant.TRANSFER_OVER_RAISED;
			}
		}

		productDetailService.mergeProductDetailByMqInfo(tenderMqInfo);

		return ProductResponseExceptionConstant.OPERTION_SUCCESS;
	}

	/**
	 * 加入理财计划
	 *
	 * @param tenderMqInfo
	 * @return
	 */
	@Override
	public Integer joinPlan(ProductTenderMqInfo tenderMqInfo) {

		Integer borrowId = tenderMqInfo.getBorrowId();
		BigDecimal tenderAmount = tenderMqInfo.getTenderAmount();

		// 标的
		Product product = productMapper.selectByPrimaryKey(borrowId);
		// 新改的信息
		ProductWithBLOBs newProduct = new ProductWithBLOBs();
		newProduct.setId(borrowId);

		// 检查
		CheckProductUtil.checkJoin(product);

		BigDecimal left = product.getSurplusInvestAmount().subtract(tenderAmount); // 剩余可以加入的金额
		if (left.longValue() > 0 && tenderAmount.longValue() < left.longValue()) {
			newProduct.setSurplusInvestAmount(left.subtract(tenderAmount));
			// 判断是否募集完成 等待匹配
			if (left.longValue() == 0) {
				newProduct.setBorrowFullStatus(1);
				// product.setProductStatus(6);
			}
		} else {
			// 标的超募
			logger.warn("购买理财计划返回  标的总额：{}, 标的已匹配：{}, 标的余额：{},投资金额: {},投资用户:{}", product.getBorrowAmount(),
					product.getMateAmount(), product.getSurplusInvestAmount(), tenderMqInfo.getTenderAmount(),
					tenderMqInfo.getUserId());
			return ProductResponseExceptionConstant.PRODUCT_OVER_RAISED;
		}
		productMapper.updateByPrimaryKeySelective(newProduct);

		return ProductResponseExceptionConstant.OPERTION_SUCCESS;
	}

	/**
	 * 更新产品的状态 包括理财计划中的债权 （只是状态 不包含功能业务逻辑）
	 *
	 * @param mqInfo
	 * @return
	 */
	@Override
	public Boolean updateProductStatus(ProductUpdateMqInfo mqInfo) {
		try {
			/** 发标的时候先要确认一下产品是否正在进行理财计划匹配任务 */

			if (mqInfo.getType() == 1) {
				ProductWithBLOBs record = new ProductWithBLOBs();
				record.setId(mqInfo.getProductId());
				record.setProductStatus(mqInfo.getProductStatus());
				int ret = productMapper.updateByPrimaryKeySelective(record);
				if (ret > 0) {
					return true;
				}
			} else if (mqInfo.getType() == 2) {
				return borrowPlanTransferService.updateTransferStatus(mqInfo);
			}
		} catch (BeansException e) {
			logger.error("{}产品状态更新失败：{}", mqInfo.getProductId(), e.getCause());
		}

		return false;
	}

	/**
	 * 跟新对应的产品状态 以及审核信息
	 *
	 * @param borrowId
	 * @param borrowStatus
	 * @return
	 */
	@Override
	public Boolean updateProductStatus(ProductVerifyInput verifyInput) {
		// `audit_remark` '审核备注',
		// `audit_user_id` '审核人',
		// `audit_user_name` '审核人名称',
		// `audit_time` '审核时间',
		Integer borrowId = verifyInput.getBorrowId();
		Integer borrowStatus = verifyInput.getBorrowStatus();
		String auditRemark = verifyInput.getAuditRemark();
		String auditUserId = verifyInput.getAuditUserId();
		String auditUserName = verifyInput.getAuditUserName();
		Integer auditResult = verifyInput.getAuditResult();
		Integer type = verifyInput.getAuditType();
		Date auditTime = verifyInput.getAuditTime();
		try {
			ProductWithBLOBs record = new ProductWithBLOBs();
			record.setId(borrowId);
			record.setProductStatus(borrowStatus);
			record.setAuditRemark(auditRemark);
			record.setAuditUserId(auditUserId);
			record.setAuditUserName(auditUserName);
			record.setAuditTime(auditTime);
			int ret = productMapper.updateByPrimaryKeySelective(record);

			ProductAuditRecord productAuditRecord = new ProductAuditRecord();
			productAuditRecord.setProductId(borrowId);
			productAuditRecord.setAuditRemark(auditRemark);
			productAuditRecord.setAuditUserId(auditUserId);
			productAuditRecord.setAuditName(auditUserName);
			productAuditRecord.setAuditTime(auditTime);
			productAuditRecord.setAuditResult(auditResult); // 结果
			productAuditRecord.setAuditType(type);
			int retRe = productAuditRecordMapper.insertSelective(productAuditRecord);

			if (ret > 0 && retRe > 0) {
				return true;
			}
		} catch (BeansException e) {
			logger.error("{}产品状态更新失败：{}", borrowId, e.getCause());
		}
		return false;
	}

	/**
	 * 标的满标复审（接口 -》 mq）
	 * 
	 * @param borrowId
	 * @return
	 */
	@Override
	public Boolean reverifyBorrow(ProductVerifyInput input) {

		String userId = input.getUserId();

		String username = userAccountCache.getUserAccoutInfo(userId).getUserName();

		ProductUpdateMqInfo mqInfo = new ProductUpdateMqInfo();
		mqInfo.setProductId(input.getBorrowId());
		mqInfo.setBorrowId(input.getBorrowId());
		mqInfo.setOperation(3);
		mqInfo.setOperationUserId(userId);
		mqInfo.setOperationUserName(username);
		mqInfo.setOperationRemark(input.getBorrowId() + "满标审核");
		mqInfo.setAuditResult(input.getAuditResult());
		mqInfo.setType(1);
		// mqInfo.setProductStatus(input.getBorrowStatus());

		jmsTemplate.convertAndSend(productUpdateQueue, mqInfo);

		return true;
	}

	/**
	 * 标的满标复审 （没有调用北京银行接口）
	 *
	 * @param mqInfo
	 * @return
	 */
	@Override
	public Boolean reverifyBorrow(ProductUpdateMqInfo mqInfo) {

		Integer auditResult = mqInfo.getAuditResult();
		ProductVerifyInput verifyInput = mqInfo.toVerifyInput();

		// 审核结果 0：打回,1：通过
		if (auditResult == 0) {
			verifyInput.setBorrowStatus(ProductConstant.PRODUCT_STATUS_2);
		} else if (auditResult == 1) {
			verifyInput.setBorrowStatus(ProductConstant.PRODUCT_STATUS_7);
		} else {
			return false;
		}
		verifyInput.setAuditType(3);
		doReverify(verifyInput);

		return true;
	}

	/**
	 * 将相对应的标的进行满标复审操作 或者打回到
	 *
	 * @param borrowId
	 */
	private void doReverify(ProductVerifyInput verifyInput) {

		Integer borrowId = verifyInput.getBorrowId();
		Product product = productMapper.selectByPrimaryKey(borrowId);

		// 复审的时候先进行检查这个标的是否能验证
		CheckProductUtil.checkReverify(product);
		// 然后跟新标的的状态 未还款中
		this.updateProductStatus(verifyInput);

		// 如果是成功的 才进行操作
		if (verifyInput.getBorrowStatus().equals(ProductConstant.PRODUCT_STATUS_7)) {
			// 标的的还款表中插入还款计划信息
			Product retP = borrowRepayService.createRepayPlan(product);
			// 更新产品最后还款时间
			this.updateProductLastRepayDate(retP);
		}

	}

	/**
	 * 更新还款时间
	 * 
	 * @param product
	 * @return
	 */
	public Boolean updateProductLastRepayDate(Product product) {
		ProductWithBLOBs record = new ProductWithBLOBs();
		record.setId(product.getId());
		record.setRepayLastTime(product.getRepayLastTime());
		productMapper.updateByPrimaryKeySelective(record);

		return true;
	}

	/**
	 * 匹配债权
	 *
	 * @param debtsList
	 *            债权列表
	 * @param leftAmount
	 *            剩余金额
	 * @param userId
	 *            用户
	 * @param productId
	 * @param voucherMap
	 * @return 返回 剩余金额
	 */
	private BigDecimal doBebtsMatching(List<BorrowPlanTransfer> debtsList, BigDecimal leftAmount, String userId,
			Integer tenderId, Integer productId, Map voucherMap) {
		if (leftAmount.longValue() <= 0) {
			return new BigDecimal(0);
		}
		if (!CollectionUtils.isEmpty(debtsList)) {
			UserDiscountOutput output = (UserDiscountOutput) voucherMap.get(tenderId);

			BigDecimal voucherAmount = new BigDecimal(0);
			// 使用的券
			UserDiscountOutput useVoucher = null;
			if (output != null) {
				// 加息券类型 0:抵用券 1:加息券
				if (output.getDiscountType() == 0) {
					// 抵扣券金额 分
					voucherAmount = new BigDecimal(output.getDiscountAvailable());
				} else if (output.getDiscountType() == 1) {
					// 抵扣券金额 分
					useVoucher = output;
				}
			}

			for (BorrowPlanTransfer transfer : debtsList) {
				if (leftAmount.longValue() > 0) {
				}
			}
			// 和债转标进行匹配
		}

		return leftAmount;
	}

	/**
	 * 匹配 投资 和 标的
	 *
	 * @param userId,
	 *            String tenderId 用户
	 * @param borrowingList
	 *            标的列表
	 * @param leftAmount
	 *            剩余匹配金额
	 * @param productId
	 * @param voucherMap
	 * @return 返回 剩余金额
	 */
	private BigDecimal doBorrowMatching(List<Product> borrowingList, BigDecimal leftAmount, String userId,
			Integer tenderId, Integer productId, Map voucherMap)

	{
		if (leftAmount.longValue() <= 0) {
			return new BigDecimal(0);
		}
		if (!CollectionUtils.isEmpty(borrowingList)) {

			UserDiscountOutput output = (UserDiscountOutput) voucherMap.get(tenderId);

			BigDecimal voucherAmount = new BigDecimal(0);
			// 使用的券
			UserDiscountOutput useVoucher = null;
			// 加息券类型 0:抵用券 1:加息券
			if (output != null) {
				if (output.getDiscountType() == 0) {
					// 抵扣券金额 分
					voucherAmount = new BigDecimal(output.getDiscountAvailable());
				} else if (output.getDiscountType() == 1) {
					// 抵扣券金额 分
					useVoucher = output;
				}
			}

			for (Product newBorrowing : borrowingList) {
				if (leftAmount.longValue() > 0) {
					// 剩余金额
					BigDecimal amount = newBorrowing.getSurplusInvestAmount();

					// 标的未匹配的金额
					// BigDecimal amount =
					// newBorrowing.getBorrowAmount().subtract(newBorrowing.getMateAmount());

					if (voucherAmount.longValue() > 0 && voucherAmount.longValue() <= leftAmount.longValue()
							&& voucherAmount.longValue() <= amount.longValue()) {
						useVoucher = output;
						voucherAmount = new BigDecimal(0);
					}
					// 投资金额大于标的剩余未匹配金额
					if (leftAmount.compareTo(amount) > -1) {
						leftAmount = leftAmount.subtract(amount);
						// borrowLeft 0
						updateSurplusInvestAmount(newBorrowing.getId(), new BigDecimal(0));
						sendMessage(newBorrowing.getId(), amount, userId, tenderId, 1, newBorrowing, useVoucher);
						// 更新产品 剩余金额 并且改成加入理财计划

					} else {
						BigDecimal borrowLeft = amount.subtract(leftAmount);

						updateSurplusInvestAmount(newBorrowing.getId(), borrowLeft);
						sendMessage(newBorrowing.getId(), leftAmount, userId, tenderId, 1, newBorrowing, useVoucher);
						leftAmount = new BigDecimal(0);
						break;
					}
				}
			}
			// 和续贷标进行匹配
		}

		return leftAmount;
	}

	// 更新产品 剩余金额 并且改成加入理财计划
	private boolean updateSurplusInvestAmount(Integer borrowId, BigDecimal borrowLeft) {
		try {
			ProductWithBLOBs product = new ProductWithBLOBs();
			product.setIsJion(1);
			product.setId(borrowId);
			product.setSurplusInvestAmount(borrowLeft);
			productMapper.updateByPrimaryKeySelective(product);
			return true;
		} catch (Exception e) {
			logger.error("borrowId{}更新产品 剩余金额 并且改成加入理财计划失败：{}", borrowId, e.getCause());
			throw e;
		}
	}

	// 发送消息 投资的信息
	private void sendMessage(Integer borrowId, BigDecimal amount, String userId, Integer tenderId, int type,
			Product product, UserDiscountOutput useVoucher) {
		ProductOutput out = new ProductOutput();
		BeanUtils.copyProperties(product, out);

		TenderExecutor tenderExecutor = new TenderExecutor();
		tenderExecutor.setProduct(out);
		tenderExecutor.setDiscount(useVoucher);
		tenderExecutor.setTenderMoney(amount.doubleValue());
		tenderExecutor.setTenderMode(1);
		tenderExecutor.setChoicenessTenderId(tenderId);

		UserAccountInfo user = new UserAccountInfo();
		user.setUserId(userId);
		tenderExecutor.setUser(user);

		/** * 投标类型:0:散标 1:债权 2:精选计划普通标 3：精选计划转让标 */
		if (type == 1) {
			tenderExecutor.setTenderType(2);
		} else if (type == 2) {
			tenderExecutor.setTenderType(3);
		}

		jmsTemplate.convertAndSend(productTenderQueue, tenderExecutor);

	}

	/**
	 * 以金额加入先后顺序为纬度查询当天债权池中新增标中是否有一次性可满的标的 若有，则匹配该标的；若无，则匹配该债权列表最早的标
	 *
	 * @param newBoolean
	 *            是否是新增表
	 */
	private List<Product> getBorrowingList(BigDecimal tenderAmount, Boolean newBoolean) {
		List<Product> newList = new ArrayList<Product>();

		ProductExample example = new ProductExample();
		ProductExample.Criteria criteria = example.createCriteria();
		// 新增的未加入理财计划 已经可发 新增标 小于等于
		criteria.andIsJionEqualTo(0).andIsIssueEqualTo(1).andDefineTypeEqualTo(7).andIsAutoInvestEqualTo(0)
				.andBorrowAmountLessThanOrEqualTo(tenderAmount).andMateAmountEqualTo(new BigDecimal(0));
		if (newBoolean) {
			criteria.andParentIdIsNull();
		} else {
			criteria.andParentIdIsNotNull();
		}

		example.setOrderByClause("borrow_amount DESC Limit 1");
		List<Product> borrowList = productMapper.selectByExample(example);

		if (!CollectionUtils.isEmpty(borrowList)) {
			newList.add(borrowList.get(0));
		}

		// 查询剩余的新增标 不为投资过的在前
		ProductExample example2 = new ProductExample();
		ProductExample.Criteria criteria2 = example2.createCriteria();
		// 不一定要已经在理财计划内的散标 已经可发 新增标 剩余大于0 自定义类型选项中为抵押标的，已复审的新增标创建时间的先后排序
		criteria2.andIsIssueEqualTo(1).andSurplusInvestAmountGreaterThan(new BigDecimal(0)).andIsAutoInvestEqualTo(0)
				.andDefineTypeEqualTo(7);
		if (newBoolean) {
			criteria2.andParentIdIsNull();
		} else {
			criteria2.andParentIdIsNotNull();
		}
		example2.setOrderByClause("case when  mate_amount > 0 then 1 else 0 end desc,create_time asc ");
		List<Product> borrowList2 = productMapper.selectByExample(example2);

		if (!CollectionUtils.isEmpty(borrowList2)) {
			newList.addAll(borrowList2);
		}
		return newList;
	}

	/**
	 * 以金额加入先后顺序为纬度查询当天债权池中债权转让标中有没从理财计划到期释放的债权， 若有，再判断是否有可一次性满标的债权,有则优先匹配，
	 * 若无，则按理财计划到期释放时间先后顺序匹配，直到匹配完再匹配陪玩账户扫尾的债权； 陪玩账户散标扫尾的债权中是否有一次性可满标的债权，
	 * 若有，则匹配，若无，按照散标扫尾时间先后顺序匹配；
	 *
	 * @param tenderAmount
	 * @param debtsBoolean
	 *            true:精选计划底层标的债权转让,false:内部账号债权转让'
	 */
	private List<BorrowPlanTransfer> getBebtsList(BigDecimal tenderAmount, Boolean debtsBoolean) {
		List<BorrowPlanTransfer> debtsList = new ArrayList<BorrowPlanTransfer>();

		BorrowPlanTransferExample example = new BorrowPlanTransferExample();
		BorrowPlanTransferExample.Criteria criteria = example.createCriteria();
		// 转让中 小于等于
		criteria.andAccountYesEqualTo(new BigDecimal(0)).andAccountLessThanOrEqualTo(tenderAmount);
		if (debtsBoolean) {
			criteria.andStatusEqualTo(1);
		} else {
			criteria.andStatusEqualTo(2);
		}
		example.setOrderByClause("account DESC Limit 1");

		// 可一次性满标的债权
		List<BorrowPlanTransfer> fullList = borrowPlanTransferMapper.selectByExample(example);

		if (!CollectionUtils.isEmpty(fullList)) {
			debtsList.add(fullList.get(0));
		}

		// 查询剩余的新增标 不为投资过的在前
		BorrowPlanTransferExample example2 = new BorrowPlanTransferExample();
		BorrowPlanTransferExample.Criteria criteria2 = example.createCriteria();
		// 已经加入理财计划 已经可发 新增标 已经投标过的 自定义类型选项中为抵押标的，已复审的新增标创建时间的先后排序
		criteria2.andAccountYesGreaterThan(new BigDecimal(0));
		if (debtsBoolean) {
			criteria.andStatusEqualTo(1);
		} else {
			criteria.andStatusEqualTo(2);
		}
		example2.setOrderByClause("case when  account_yes > 0 then 1 else 0 end desc,transfe_start_time asc ");
		List<BorrowPlanTransfer> notfullList = borrowPlanTransferMapper.selectByExample(example);

		if (!CollectionUtils.isEmpty(notfullList)) {
			debtsList.addAll(notfullList);
		}
		return debtsList;
	}

	/**
	 * 发布理财计划的时候把 余额放到缓存中
	 *
	 * @return
	 */
	private Boolean initPlanLeftAmountCache(Product product) {
		ProjectInfoCacheInfo projectInfoCacheInfo = new ProjectInfoCacheInfo();
		projectInfoCacheInfo.setProjectId(product.getId());
		projectInfoCacheInfo.setLeftAccount(product.getBorrowAmount());
		projectInfoCache.putProjectInfo(projectInfoCacheInfo);

		return true;
	}

	/**
	 * 获取可发的未满标的新增标 （标红）
	 *
	 * @return
	 */
	@Override
	public List<ProductOutput> getIssueNewBorrowList() {
		// 查询剩余的新增标 不为投资过的在前
		ProductExample example = new ProductExample();
		ProductExample.Criteria criteria = example.createCriteria();
		// 已经可发 新增标 剩余可投>0 自定义类型选项中为抵押标的，已复审的新增标创建时间的先后排序
		criteria.andIsIssueEqualTo(1).andSurplusInvestAmountGreaterThan(new BigDecimal(0)).andParentIdIsNull();
		example.setOrderByClause(" create_time asc ");
		List<Product> borrowList = productMapper.selectByExample(example);

		if (!CollectionUtils.isEmpty(borrowList)) {
			ImmutableList<ProductOutput> resultList = FluentIterable.<Product> from(borrowList)
					.transform(new Function<Product, ProductOutput>() {
						@Override
						public ProductOutput apply(Product input) {
							ProductOutput out = new ProductOutput();
							BeanUtils.copyProperties(input, out);
							return out;
						}
					}).toList();

			return resultList;
		}

		return null;
	}

	/**
	 * 撤回理财计划
	 */
	@Override
	public Boolean withdrawPlan(Integer planId) {
		return false;
	}

	/**
	 * 撤回理财计划底层标的
	 */
	@Override
	public Boolean withdrawPlanBorrow(ProductVerifyInput verifyInput) {

		// 修改普通标的的信息
		withdrawBorrow(verifyInput);

		Integer borrowId = verifyInput.getBorrowId();
		// 将理财计划里面 Productdetail 表中的数据 进行修改
		productDetailService.withdrawDetailByBorrowId(borrowId);
		return true;
	}

	/**
	 * 撤回理财计划底层标的 mq
	 */
	@Override
	public Boolean withdrawPlanBorrow(ProductUpdateMqInfo mqInfo) {

		Integer auditResult = mqInfo.getAuditResult();
		ProductVerifyInput verifyInput = mqInfo.toVerifyInput();
		return withdrawPlanBorrow(verifyInput);
	}

	/**
	 * 撤回普通底层标的
	 */
	@Override
	public Boolean withdrawBorrow(ProductVerifyInput verifyInput) {

		Integer borrowId = verifyInput.getBorrowId();
		Product product = productMapper.selectByPrimaryKey(borrowId);

		CheckProductUtil.checkWithdraw(product);
		verifyInput.setBorrowStatus(ProductConstant.PRODUCT_STATUS_0); // 初始状态、
		verifyInput.setAuditType(4); // 审核类型 0：设置标的 1：发布标的 2：审核标的 3：手动满标审核标的
										// 4:撤标',
		// 先要将散标的状态置为撤标
		this.updateProductStatus(verifyInput);

		return true;
	}

	/**
	 *
	 * @param productOutput
	 * @return 1 理财计划 2 底层散标 3： 普通散标
	 */
	private Integer confirmProductType(ProductOutput productOutput) {

		if (productOutput.getProductType()
				.equals(com.tuodao.bp.model.constant.product.ProductConstant.PRODUCT_TYPE_PLAN)) {
			return 1;
		} else if (productOutput.getProductType()
				.equals(com.tuodao.bp.model.constant.product.ProductConstant.PRODUCT_TYPE_COMMON)
				&& productOutput.getIsJion().equals(com.tuodao.bp.model.constant.product.ProductConstant.BORROW_YES)) {
			return 2;
		} else {
			return 3;
		}
	}

	/***
	 * 撤销理财计划
	 * 
	 * @return
	 */
	public boolean withDrawPlan(ProductOutput productOutput) {
		// 判断 productOutput 是底层标的 还是 理财计划
		if (confirmProductType(productOutput) == 3) {
			// 普通的底层标的
			return true;
		} else if (confirmProductType(productOutput) == 1) {
			// 整个理财计划
			doWithDrawPlan(productOutput);

		}

		return true;
	}

	/**
	 * 整个理财计划撤销
	 * 
	 * @param productOutput
	 * @return
	 */
	private Boolean doWithDrawPlan(ProductOutput productOutput) {

		// 1.查询理财计划中所涉及到的底层散标
		List<ProductDetailOutput> list = productDetailService.getBorrowList(productOutput.getId());
		// 2.理财计划中投的加入记录

		return true;

	}

	/**
	 * 发布普通的标的 不包含理财计划
	 */
	@Override
	public Boolean publishBorrow(ProductVerifyInput verifyInput) {

		Integer borrowId = verifyInput.getBorrowId();
		Product product = productMapper.selectByPrimaryKey(borrowId);

		CheckProductUtil.checkIssuance(product);

		verifyInput.setBorrowStatus(ProductConstant.PRODUCT_STATUS_5); // 5：已发布（募集中或者定时）、
		verifyInput.setAuditType(1); // 审核类型 0：设置标的 1：发布标的 2：审核标的 3：手动满标审核标的
										// 4:撤标',
		// 先要将散标的状态置为撤标
		this.updateProductStatus(verifyInput);

		/** 发布理财计划的时候把 余额放到缓存中 */
		initPlanLeftAmountCache(product);

		return true;
	}

	/**
	 * 查询自动投标是所需要的标的列表（只是散标）
	 */
	@Override
	public List<ProductOutput> getAutoTenderingBorrowList() {
		ProductExample example = new ProductExample();
		// 加入自动投标 代发 并且有余额 （特殊标是不能进入自动投标队列的）
		example.createCriteria().andIsAutoInvestEqualTo(1).andProductStatusEqualTo(ProductConstant.PRODUCT_STATUS_4)
				.andSurplusInvestAmountGreaterThan(new BigDecimal(0));
		example.setOrderByClause(" audit_time asc ");

		List<Product> demoList = productMapper.selectByExample(example);

		ImmutableList<ProductOutput> resultList = FluentIterable.<Product> from(demoList)
				.transform(new Function<Product, ProductOutput>() {
					@Override
					public ProductOutput apply(Product input) {
						ProductOutput out = new ProductOutput();
						BeanUtils.copyProperties(input, out);
						return out;
					}
				}).toList();

		return resultList;
	}

	/**
	 * 产品审核,并存入审核记录
	 */
	@Override
	public String publishProduct(ProductRequestData requestData) {
		try {
			// ProductRequestData requestData = (ProductRequestData)
			// JSON.parseObject(reqData, ProductRequestData.class);
			// 参数不能为空
			if (null == requestData) {
				throw new BizFeignException(null);
			}
			// 标的信息
			ProductReq productReq = requestData.getProductReq();

			// 产品参数不能为空
			if (null == productReq) {
				throw new BizFeignException(null);
			}
			// 审核信息
			ProductAuditRecordReq productAuditRecordReq = requestData.getProductAuditRecordReq();

			// 审核信息不能为空
			if (null == productAuditRecordReq) {
				throw new BizFeignException(null);
			}
			ProductQueryInput productQueryInput = new ProductQueryInput();
			productQueryInput.setId(productReq.getId());
			productQueryInput.setProductStatus(productReq.getProductStatus());

			ProductWithBLOBs entity = productMapper.selectProduct(productQueryInput);
			// 当前审核状态的产品不存在抛出异常
			if (null == entity) {
				throw new BizFeignException(null);
			}

			entity.setAuditRemark(productReq.getAuditRemark());
			entity.setAuditUserId(productReq.getAuditUserId());
			entity.setAuditUserName(productReq.getAuditUserName());
			entity.setAuditTime(productReq.getAuditTime());
			String orderNo = ProductConstant.ORDER_NO + entity.getProductCode() + "_" + DateUtil.dateStr(new Date());
			List<BorrowPlan> planList = null;
			// 等额本息
			if (ProductEnumConstant.RefundWay.PRINCIPAL.code == entity.getRefundWay()) {
				planList = BorrowUtils.repaymentFixedPaymentMortgage(entity.getProductPeriod(),
						entity.getBorrowAmount().doubleValue(), entity.getBorrowApr().doubleValue());
			} else {
				planList = BorrowUtils.repaymentPerMonth(entity.getProductPeriod(),
						entity.getBorrowAmount().doubleValue(), entity.getBorrowApr().doubleValue());
			}
			// 调用接口前先将标的状态更新为处理中
			entity.setProductStatus(ProductConstant.PRODUCT_STATUS_10);
			entity.setOrderNo(orderNo);
			productMapper.updateByPrimaryKeySelective(entity);
			// 插入标的审核记录
			ProductAuditRecord productAuditRecord = new ProductAuditRecord();
			BeanUtils.copyProperties(productAuditRecordReq, productAuditRecord);
			productAuditRecordMapper.insertSelective(productAuditRecord);
			// 循环插入还款计划到数据库
			this.saveFinancingInfo(planList, entity);
			// 标的流转生命周期记录
			this.insertBorrowMappingBank(entity);

			HashMap<String, String> map = capsulationMap(entity, planList, orderNo);
			// TODO 调用北京银行4.3.1接口 /Gateway_client/publishAction_publish
			// borrowMappingBankService传入 map参数

			HashMap<String, String> retust = depositoryBiddingClient.publish(map);
			if (true) {
				entity.setProductStatus(ProductConstant.PRODUCT_STATUS_4);
				productMapper.updateByPrimaryKeySelective(entity);
				//加入缓存
				projectInfoCache.putProjectInfo(null);
			} else {
				// 北京银行发标失败
				throw new BizFeignException(null);
			}

		} catch (BeansException e) {
			logger.error("{}产品审核失败：{}", e.getCause());
		}
		return "";
	}

	// 组装北京银行需要的还款计划列表
	private List<FinancingInfo> getFinancingInfo(List<BorrowPlan> list, ProductWithBLOBs product,
			UserDepositOutput user) {

		List<FinancingInfo> infoList = new ArrayList<>();

		double total = 0;
		for (BorrowPlan entity : list) {
			total = total + entity.getPreInterest();
		}
		FinancingInfo info = new FinancingInfo();
		/** 借款人客户编号 （借款人userid） */
		info.setCustNo(user.getUserId());//
		/** 申请日期（YYYY-MM-DD） */
		info.setRegDate(DateUtil.formatShort(product.getCreateTime()));
		/** 申请时间（HH:mm:ss） */
		info.setRegTime(DateUtil.formatHms(product.getCreateTime()));
		/** 融资利息（eg：0.12） */
		info.setFinancInt(String.valueOf(total));
		/** 手续费（eg：0.01） */
		info.setFeeInt("0");
		/** 借款用途[限制100字符以内] */
		info.setFinancPurpose(product.getBorrowUse());
		/** 用款日期（YYYY-MM-DD） */
		info.setUseDate(null);
		/** 借款人开户行 */
		info.setOpenBranch(user.getBankName());
		/** 借款人银行卡号 */
		info.setWithdrawAccount(user.getBankCode());
		/** 卡类型(1-个人 2-企业) */
		info.setAccountType("1");
		/** 借款人姓名 */
		info.setPayeeName(user.getRealName());
		/** 融资金额[N(19,2)] */
		info.setFinancAmt(String.valueOf(product.getBorrowAmount()));
		infoList.add(info);
		return infoList;
	}

	// 循环插入还款计划到数据库
	private void saveFinancingInfo(List<BorrowPlan> list, ProductWithBLOBs product) {
		BorrowRepayment borrowRepayment = null;
		for (BorrowPlan entity : list) {
			borrowRepayment = new BorrowRepayment();
			borrowRepayment.setUserId(product.getBorrowUserId());
			borrowRepayment.setBorrowId(product.getId());
			borrowRepayment.setStatus(0);
			borrowRepayment.setPeriod(entity.getPeriod());
			borrowRepayment.setFee(BigDecimal.ZERO);
			borrowRepayment.setPreInterest(new BigDecimal(entity.getPreInterest()));
			borrowRepayment.setPreRepayTime(entity.getPreTime());
			borrowRepayment.setRepayMode(0);
			repaymentMapper.insertSelective(borrowRepayment);
		}
	}

	/**
	 * 根据用户ID获取用户银行卡信息
	 *
	 * @param userId
	 * @return
	 */
	public UserDepositOutput getUserDepositOutput(String userId) {

		BasePojo input = new BasePojo();
		input.setUserId(userId);
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
		HttpEntity<String> entity = new HttpEntity<String>(JSON.toJSONString(input), httpHeaders);
		return restTemplate
				.postForEntity("http：//BIZ-USER-ACCOUNT/ua/account/getUserDepositInfo", entity, UserDepositOutput.class)
				.getBody();
	}

	/**
	 * 调用存管废标标接口，更改产品状态，记录审核记录
	 * @return  订单号
	 */
	@Override
	public String discardProduct(ProductRequestData requestData) {


		String orderNo = null;
		try {
			// 标的信息
			ProductReq productReq = requestData.getProductReq();
			// 产品参数不能为空
			if (null == productReq) {
				throw new BizFeignException(null);
			}
			// 审核信息
			ProductAuditRecordReq productAuditRecordReq = requestData.getProductAuditRecordReq();

			// 审核信息不能为空
			if (null == productAuditRecordReq) {
				throw new BizFeignException(null);
			}
			ProductQueryInput productQueryInput = new ProductQueryInput();
			productQueryInput.setId(productReq.getId());
			productQueryInput.setProductStatus(productReq.getProductStatus());

			ProductWithBLOBs entity = productMapper.selectProduct(productQueryInput);
			// 当前审核状态的产品不存在抛出异常
			if (null == entity) {
				throw new BizFeignException(null);
			}
			entity.setAuditRemark(productReq.getAuditRemark());
			entity.setAuditUserId(productReq.getAuditUserId());
			entity.setAuditUserName(productReq.getAuditUserName());
			entity.setAuditTime(productReq.getAuditTime());

			orderNo = ProductConstant.ORDER_NO + entity.getProductCode() + "_" + DateUtil.dateStr(new Date());

			entity.setProductStatus(ProductConstant.PRODUCT_STATUS_11);
			entity.setOrderNo(orderNo);
			productMapper.updateByPrimaryKeySelective(entity);
			ProductAuditRecord productAuditRecord = new ProductAuditRecord();
			BeanUtils.copyProperties(productAuditRecordReq, productAuditRecord);
			productAuditRecordMapper.insertSelective(productAuditRecord);

		} catch (BeansException e) {
			logger.error("{}产品撤标失败：{}", e.getCause());
		}
		return orderNo;
	}


	/**
	 * 当产品撤销以后更新状态
	 * @param id
	 * @return
	 */
	@Override
	public Boolean afterDiscardProduct(Integer productId)
	{
		ProductWithBLOBs entity = productMapper.selectByPrimaryKey(productId);
		entity.setProductStatus(ProductConstant.PRODUCT_STATUS_1);
		productMapper.updateByPrimaryKeySelective(entity);
		//加入缓存
		projectInfoCache.deleteProjectInfo(null);

		return true;
	}



	/**
	 * 标的流转生命周期记录
	 * 
	 * @param entity
	 */
	public void insertBorrowMappingBank(ProductWithBLOBs entity) {
		// 先根据产品ID删除，以防有重复记录
		borrowMappingBankService.deleteBorrowMappingBankByProductId(entity.getId());

		BorrowMappingBank borrowMappingBank = new BorrowMappingBank();
		borrowMappingBank.setBorrowId(entity.getId());
		Integer borrowStatus = entity.getBorrowStatus();
		// 是否要代偿 1:是 0 否'
		if (borrowStatus == ProductEnumConstant.BorrowStatus.BorrowStatus2.code) {
			borrowMappingBank.setIsCompensatory(ProductEnumConstant.IsCompensatory.YES.code);
		} else {
			borrowMappingBank.setIsCompensatory(ProductEnumConstant.IsCompensatory.NO.code);
		}
		// 借款存管状态 0：初始 1：成标 2： 出账
		borrowMappingBank.setBorrowBankStatus(ProductEnumConstant.BorrowBankStatus.BorrowBankStatus0.code);
		borrowMappingBank.setCompensatoryAmount(entity.getBorrowAmount());
		borrowMappingBank.setCompensatoryAmountYes(BigDecimal.ZERO);
		borrowMappingBank.setCompensatoryStatus(ProductEnumConstant.CompensatoryStatus.CompensatoryStatus0.code);
		borrowMappingBankService.insertRecord(borrowMappingBank);
	}

	private HashMap<String, String> capsulationMap(ProductWithBLOBs entity, List<BorrowPlan> planList, String orderNo) {
		// 封装存管需要的参数
		HashMap<String, String> map = new HashMap<>();
		// 订单号
		map.put(BJFN.orderNo, orderNo);
		// 产品编号
		map.put(BJFN.prodId, entity.getProductCode());
		// 产品名称
		map.put(BJFN.prodName, entity.getProductTitle());
		// 发行总额度
		map.put(BJFN.totalLimit, new BDC(entity.getBorrowAmount()).toS2Double().toString());
		// 产品发行日(YYYY-MM-DD HH:mm:ss)
		map.put(BJFN.sellDate, DateUtil.dateStr(entity.getCreateTime()));
		// 起息日如起息方式为成立起息，为必选项(YYYY-MM-DD HH:mm:ss)
		map.put(BJFN.valueDate, DateUtil.dateStr(entity.getAuditTime()));
		// 周期，例如：3个月的标的传 3
		map.put(BJFN.cycle, String.valueOf(entity.getProductPeriod()));
		// 周期单位 1日 2周 3月 4季 5年
		map.put(BJFN.cycleUnit, String.valueOf(entity.getPeriodUnit()));
		// 年化收益率例如：8.9% 传 0.089
		map.put(BJFN.istYear, new BDC(entity.getBorrowApr()).toS2Double().toString());
		// 到期日(YYYY-MM-DD HH:mm:ss)(还款计划最后一次还款日期)
		map.put(BJFN.expireDate, DateUtil.dateStr(planList.get(planList.size() - 1).getPreCollectionTime()));
		// 获取用户信息
		BasePojo pojo = new BasePojo();
		pojo.setUserId(entity.getBorrowUserId());
		UserDepositOutput user = userClient.getUserDepositInfo(pojo);
		// 融资信息列表
		List<FinancingInfo> infoList = this.getFinancingInfo(planList, entity, user);
		// 融资信息列表,仅允许一个融资人
		map.put(TDFN.financingInfoList, JSON.toJSONString(infoList));
		// 代偿（委托）账户列表
		map.put(BJFN.compensationPlatcust, "12345678");
		return map;
	}

	@Override
	public ProductOutput getProductEntity(ProductInput productInput) {

		return null;
	}

	/**
	 * {@link IProductService#getPlanReverifyBorrowList(Integer productId)}
	 */
	@Override
	public List<ProductOutput> getPlanReverifyBorrowList() {
		List<ProductWithBLOBs> productWithBLOBsList = productMapper.getPlanReverifyBorrowList();

		List<ProductOutput> productOutputs = toOutput(productWithBLOBsList);

		return productOutputs;
	}

	/**
	 * {@link IProductService#getReverifyPlanList()}
	 */
	@Override
	public List<ProductOutput> getReverifyPlanList() {
		List<ProductWithBLOBs> productWithBLOBsList = productMapper.getReverifyPlanList();

		List<ProductOutput> productOutputs = toOutput(productWithBLOBsList);

		return productOutputs;
	}

	private List<ProductOutput> toOutput(List<ProductWithBLOBs> productWithBLOBsList) {
		ImmutableList<ProductOutput> resultList = FluentIterable.<ProductWithBLOBs> from(productWithBLOBsList)
				.transform(new Function<ProductWithBLOBs, ProductOutput>() {
					@Override
					public ProductOutput apply(ProductWithBLOBs input) {
						ProductOutput out = new ProductOutput();
						BeanUtils.copyProperties(input, out);
						return out;
					}
				}).toList();

		return resultList;
	}

	@Override
	public Boolean repaymentProduct(ProductOutput productOutput, BorrowRepaymentInput repayment, Boolean advance) {
		logger.info("还款开始[{}]", repayment.getId());
		RedisLock lock = new RedisLock(RedisConstans.PRODUCT_REPAYMENT_LOCK + productOutput.getProductCode());
		try {
			if (repayment.getStatus() != ProductEnumConstant.RepaymentStatus.Status1.code) {
				logger.info("该还款计划已还款[{}]", repayment.getId());
				throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150512);
			}
			BasePojo pojo = new BasePojo();
			pojo.setUserId(repayment.getUserId());
			UserAccountInfo user = userClient.getUserAccountInfo(pojo);
			if (null == user) {
				logger.info("借款用户不存在[{}]", repayment.getId());
				throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150513);
			}
			UserDepositOutput userDepositOutput = userClient.getUserDepositInfo(pojo);
			if (null == userDepositOutput) {
				logger.info("借款用户未开通存管账户[{}]", repayment.getId());
				throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150514);
			}
			// 判断是否还有前面的期数未还款
			BorrowRepaymentOutput output = this.getBorrowRepayment(repayment);
			if (null != output) {
				logger.info("请先偿还前几期借款[{}]", repayment.getId());
				throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150515);
			}
			// 调用回款计划接口获取
			// 调用接口根据标的repayment.getBorrowId()，当前期数 repayment.getPeriod()查询回款表
			List<BackMoneyPlanOutput> collectionList = getBackMoneyPlanOutput(repayment);
			Map<String, Object> rRetMap = this.getRepayAccountByRecover(repayment, advance, collectionList);
			// 借款人实际需要还款的金额
			Integer repayMoney = ((BigDecimal) rRetMap.get("repayMoney")).intValue();
			if (repayMoney <= 0) {
				logger.info("还款金额不正确[{}]", repayment.getId());
				throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150516);
			}

			String orderNo = ProductConstant.ORDER_NO + productOutput.getProductCode() + "_"
					+ DateUtil.dateStr(new Date());
			boolean isPayoff = false;// 整个标的是否已还清
			if (repayment.getPeriods() == productOutput.getProductPeriod()) {// 提前还款或者还的是最后一期的情况下,
				isPayoff = true;
			}
			int step = repayment.getRepayStep();
			if (step == 0) {
				Map<String, Object> balanceMap = new HashMap<>();
				BigDecimal prodBalance = new BigDecimal(0);
				try { // 4.6.7.标的账户余额查询，查询标的账户余额是否足够
						/// Gateway_client/publishAction_getProductN_balance
					balanceMap.put(BJFN.prodId, productOutput.getProductCode());// 账户编号(标的编码)
					balanceMap.put(BJFN.type, "01");// 现金01,在途02
					// TODO 调用银行接口获取到账户余额
					prodBalance = new BigDecimal(0);
				} catch (Exception e) {
					logger.info("标的账户余额查询失败[{}]", repayment.getId());
					throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150517);
				}
				logger.info("prodBalance: {}, repayMoney: {}", prodBalance, repayMoney);
				if (prodBalance.compareTo(BigDecimal.valueOf(repayMoney)) >= 0) {// 标的余额足够,
					step = 1; // 执行下一步
					BorrowRepaymentInput borrowRepaymentInput = new BorrowRepaymentInput();
					borrowRepaymentInput.setId(repayment.getId());
					borrowRepaymentInput.setRepayStep(1);
					borrowRepayService.updateRepayment(borrowRepaymentInput);
				} else {// 标的余额不足, 补足余额
					BigDecimal notEnoughBalance = BigDecimal.valueOf(repayMoney).subtract(prodBalance);
					logger.info("notEnoughBalance: {}", notEnoughBalance);
					// 封装查询代偿人账户map对象
					balanceMap.clear();
					balanceMap.put(BJFN.account, "account");// 账户编号(代偿人即平台账户)
					balanceMap.put(BJFN.acctType, "13");// 投资账户 12、融资 13
					balanceMap.put(BJFN.type, "01");// 现金01,在途02
					// 4.6.6.账户余额明细查询,调用接口查询带承认账户资金
					/// Gateway_client/ accountAction_getAccountN_balace
					// TODO 调用银行接口
					BigDecimal dataBalance = new BigDecimal(0);
					if (dataBalance == null) {
						logger.info("代偿人现金融资账户查询失败[{}]", repayment.getId());
						throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150518);
					}
					logger.info("balance: {}, notEnoughBalance: {}", dataBalance, notEnoughBalance);
					if (notEnoughBalance.compareTo(dataBalance) > 0) {
						logger.info("代偿人现金融资账户余额不足[{}]", repayment.getId());
						throw new BizFeignException(ProductResponseExceptionConstant.PRODUCT_EXCEPTION_CODE_150519);
					}
					// 标的代偿（委托）还款
					this.doCompensation(repayment, user, productOutput, notEnoughBalance, orderNo);
				}
			}
			if (step <= 1) {
				Map<String, Object> retMap = getRecoverMap(advance, repayment, orderNo, isPayoff, collectionList);
				boolean repayFlag = true;// 本期是否已还清, 默认都是true（不存在一期多次还）
				// 标的还款
				this.doBorrowRepay(productOutput, repayment, retMap, repayFlag, isPayoff, orderNo);
			}
			if (step <= 2) {
				doRepayAndRecover(productOutput, repayment, rRetMap, orderNo, isPayoff, advance);
			}
		} catch (BizFeignException e) {
			e.printStackTrace();
			logger.error(productOutput.getProductCode() + "的项目抛出的异常为：" + e.getMessage());
			logger.error("errmsg", productOutput.getProductCode() + "系统异常:" + e.getMessage());
		} catch (Exception e) {
			logger.error(productOutput.getProductCode() + "的项目抛出的异常为：" + e.getMessage());
			logger.error("errmsg", productOutput.getProductCode() + "系统异常:" + e.getMessage());
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return null;
	}

	/**
	 * @param repayId回款表主键ID
	 * @param repayment回款表信息
	 * @param user投资用户信息
	 * @param productOutput标的信息
	 * @param notEnoughBalance不足余额
	 * @param orderNo订单号
	 * 
	 */
	public void doCompensation(BorrowRepaymentInput repayment, UserAccountInfo user, ProductOutput productOutput,
			BigDecimal notEnoughBalance, String orderNo) {

		// 封装调用4.2.2. 标的代偿（委托）还款Map参数
		// ../Gateway_client/repayAction_compensate
		Map<String, Object> map = new HashMap<>();
		map.put(BJFN.orderNo, orderNo);// 订单号
		map.put(BJFN.prodId, productOutput.getProductCode());// 产品编号
		// 计划还款日期（yyyyMMdd）
		map.put(BJFN.repayDate, DateUtil.dateStr(repayment.getPreRepayTime(), "yyyyMMdd"));
		map.put(BJFN.repayAmt, repayment.getPreInterest());// 计划还款金额
		map.put(BJFN.realRepayDate, DateUtil.dateStr(repayment.getRepayTime(), "yyyyMMdd"));// 实际还款时间
		map.put(BJFN.realRepayAmt, repayment.getInterest());// 实际还款金额
		map.put(BJFN.platCust, user.getDepositNo());// 平台客户编号（借款人）
		map.put(BJFN.compensationPlatcust, null);// 代偿人平台客户编号
		map.put(BJFN.feeAmt, null);// 手续费金额
		map.put(BJFN.repayType, 1);// 还款类型 0-代偿还款 1-委托还款
		map.put(BJFN.transAmt, notEnoughBalance);// 交易金额（实际还款金额+手续费金额）

		// TODO 调用4.2.2. 标的代偿（委托）接口
		// TODO 接口调用后处理
		// 将还款步骤repayStep 状态修改为1

		BorrowRepayment borrowRepayment = new BorrowRepayment();
		borrowRepayment.setId(repayment.getId());
		borrowRepayment.setRepayStep(1);
		borrowRepaymentMapper.updateByPrimaryKeySelective(borrowRepayment);

		// TODO 写代偿人资金记录,更改账户金额
		AccountLogExtInput input = new AccountLogExtInput();
		input.setUserId(user.getUserId());
		input.setOrderNo(orderNo);
		input.setType(AccountLogType.RECHARGE.getCode());
		input.setAccount(notEnoughBalance);// 本次资金变动发生的金额

		input.setRemarks("标的代偿（委托）还款");
		// 账户备份:用户账户总额,总资产=cash_frost+tender_frosh+balance+await_capital+recharge+await_interest
		// 投资可用余额=balance+recharge 可提现金额:balance'
		input.setTotal(BigDecimal.ZERO);
		input.setCashFrost(BigDecimal.ZERO);// 账户备份:提现冻结金额
		input.setTenderFrost(BigDecimal.ZERO);// 账户备份:投标冻结(散标,精选计划)
		input.setBalance(BigDecimal.ZERO);// 账户备份:可用余额(可投金额+充值金额)
		input.setRecharge(BigDecimal.ZERO);// 账户备份:充值金额(可投资不可提现金额)
		input.setAwaitCapital(BigDecimal.ZERO);// 账户备份:总待收本金
		// fromAccount 为平台在系统中的账户，目前数据库没有该账户信息
		input.setFromAccount(null);// 资金来源账户(如果为平台账户该字段有值)
		input.setFromRemarks("平台子账户");// 账户名称备注(例如平台子账户,清算账户,加息券子账户等)
		input.setToAccount(user.getUserId());// 资金去往账户(如果为平台账户该字段有值)
		input.setToRemarks("平台借款人账户");// 账户名称备注(例如平台子账户,加息券子账户等)
		input.setIsShow(1);// 是否显示资金日志,0:不显示(精选计划底层标的资金记录) 1:显示
		input.setIntrestAccount(BigDecimal.ZERO);// 收益发生变动的资金
		input.setFeeAccount(BigDecimal.ZERO);// 手续费发生变动的资金
		input.setChangeType(0);// 0收入1支出2冻结3:解冻
		accountLogClient.addAccountLog(input);
	}

	/**
	 * 根据回款计划计算还款金额
	 *
	 * @param BorrowRepaymentOutput
	 * @param advance
	 * @param List<BackMoneyPlanOutput>
	 * @return
	 */
	public Map<String, Object> getRepayAccountByRecover(BorrowRepaymentInput repayment, Boolean advance,
			List<BackMoneyPlanOutput> collectionList) {
		Map<String, Object> retMap = new HashMap<>();
		BigDecimal repayMoney = new BigDecimal(0);// 借款人实际需要还款的金额
		BigDecimal repayCap = new BigDecimal(0);// 借款人实际需要还款的本金
		BigDecimal repayInterest = new BigDecimal(0);// 借款人实际还款的利息
		// br.getRecoverCapital() 本金 preCapital
		// br.getCouponVoucherAccount() 加息券利息 couponAccount
		// br.getAwardAccount() 平台加息利息 platformAccount
		// br.getRecoverInterest() 总利息 interest

		if (advance) {
			for (BackMoneyPlanOutput br : collectionList) {
				BasePojo pojo = new BasePojo();
				pojo.setUserId(br.getUserId());
				// 判断用户北京银行开户是否成功
				UserAccountInfo user = userClient.getUserAccountInfo(pojo);
				if (user != null && user.getIsOpenDeposit() == 1) {
					BigDecimal ratesAmt = new BigDecimal(0);// 加息金（包含加息和奖励）
					BigDecimal realRepayVal = new BigDecimal(0);// 实际还款利息(不包含加息和奖励)
					BigDecimal realRepayAmount = new BigDecimal(0);// 实际还款本金

					// 调用接口根据标的repayment.getBorrowId()，投资人br.getUserId()，当前期数
					// repayment.getPeriod() 查询回款表
					BorrowRepaymentInput repaymentInput = new BorrowRepaymentInput();
					repaymentInput.setBorrowId(repayment.getBorrowId());
					repaymentInput.setPeriod(repayment.getPeriod());
					repaymentInput.setUserId(br.getUserId());
					List<BackMoneyPlanOutput> collectionTendList = borrowCollectionClient
							.getTenderCollection(repaymentInput);

					for (BackMoneyPlanOutput sbr : collectionTendList) {
						realRepayAmount = realRepayAmount.add(sbr.getPreCapital());// 本金
						if (repayment.getPeriod() == sbr.getPeriod()) {
							int thisRatesAmt;
							if (isAllFee(repayment.getRepayTime())) {
								// 加息券利息 +平台加息利息
								thisRatesAmt = sbr.getCouponAccount().add(sbr.getPlatformAccount()).intValue();
								// 总利息
								realRepayVal = realRepayVal.add(sbr.getInterest())
										.subtract(BigDecimal.valueOf(thisRatesAmt));
							} else {
								// 加息券利息 +平台加息利息
								thisRatesAmt = sbr.getCouponAccount().add(sbr.getPlatformAccount())
										.divide(new BigDecimal(2)).intValue();
								// 总利息
								realRepayVal = realRepayVal.add(BigDecimal.valueOf(sbr.getInterest().intValue() / 2))
										.subtract(BigDecimal.valueOf(thisRatesAmt));
							}
							ratesAmt = ratesAmt.add(BigDecimal.valueOf(thisRatesAmt));
						}
					}
					repayMoney = repayMoney.add(realRepayAmount).add(realRepayVal);
					repayCap = repayCap.add(realRepayAmount);
					repayInterest = repayInterest.add(realRepayVal);
				}
			}
		} else {
			for (BackMoneyPlanOutput br : collectionList) {
				BasePojo pojo = new BasePojo();
				pojo.setUserId(repayment.getUserId());
				UserAccountInfo user = userClient.getUserAccountInfo(pojo);
				if (user != null && user.getIsOpenDeposit() == 1) {
					BigDecimal realRepayAmount = br.getPreCapital();// 本金
					BigDecimal ratesAmt = br.getCouponAccount().add(br.getPlatformAccount());// 加息券利息+平台加息利息
					BigDecimal realRepayVal = br.getInterest().subtract(ratesAmt);// 总利息-（加息券利息+平台加息利息）
					repayMoney = repayMoney.add(realRepayAmount).add(realRepayVal);
					repayCap = repayCap.add(realRepayAmount);
					repayInterest = repayInterest.add(realRepayVal);
				}
			}
		}
		retMap.put("repayMoney", repayMoney);
		retMap.put("repayCap", repayCap);
		retMap.put("repayInterest", repayInterest);
		return retMap;
	}

	/**
	 * 
	 * 根据回款计划计算实际还款金额,投资人还款列表
	 * 
	 * @param advance
	 * @param repayment
	 * @param repayOrderNo
	 * @param isPayoff
	 * @param collectionList
	 * @return
	 */
	public Map<String, Object> getRecoverMap(boolean advance, BorrowRepaymentInput repayment, String repayOrderNo,
			boolean isPayoff, List<BackMoneyPlanOutput> collectionList) {
		Map<String, Object> retMap = new HashMap<>();
		List<CustRepay> custRepayList = new ArrayList<>();
		BigDecimal recoverMoney = new BigDecimal(0);

		for (BackMoneyPlanOutput br : collectionList) {
			BasePojo pojo = new BasePojo();
			pojo.setUserId(repayment.getUserId());
			UserAccountInfo user = userClient.getUserAccountInfo(pojo);
			if (user != null && user.getIsOpenDeposit() == 1) {
				CustRepay custRepay = new CustRepay();
				custRepay.setCustNo(user.getDepositNo());
				custRepay.setExperienceAmt(BigDecimal.ZERO);
				custRepay.setRealRepayDate(DateUtil.getDate("yyyyMMdd"));// 实际还款日期
				custRepay.setRepayDate(DateUtil.formatShort(br.getPreCollectionTime()));// 还款日期
				custRepay.setRepayFee(BigDecimal.ZERO);// 手续费
				custRepay.setRepayFlag("0");// 本期已还清：0-是，1-否
				custRepay.setRepayNum(String.valueOf(br.getPeriod()));// 还款期数

				BigDecimal ratesAmt = new BigDecimal(0);// 加息金
				BigDecimal realRepayVal = new BigDecimal(0);// 实际还款利息
				BigDecimal realRepayAmount = new BigDecimal(0);// 实际还款本金

				if (advance) {
					// 调用接口根据标的repayment.getBorrowId()，投资人br.getUserId()，当前期数
					// repayment.getPeriod() 查询回款表
					BorrowRepaymentInput repaymentInput = new BorrowRepaymentInput();
					repaymentInput.setBorrowId(repayment.getBorrowId());
					repaymentInput.setUserId(br.getUserId());
					repaymentInput.setPeriod(repayment.getPeriod());
					List<BackMoneyPlanOutput> collectionTendList = borrowCollectionClient
							.getTenderCollection(repaymentInput);
					for (BackMoneyPlanOutput sbr : collectionTendList) {
						realRepayAmount = realRepayAmount.add(sbr.getCapital());
						if (repayment.getPeriod() == sbr.getPeriod()) {
							int thisRatesAmt;
							if (isAllFee(repayment.getRepayTime())) {
								thisRatesAmt = sbr.getCouponAccount().add(sbr.getPlatformAccount()).intValue();
								realRepayVal = sbr.getInterest().subtract(BigDecimal.valueOf(thisRatesAmt));
							} else {
								thisRatesAmt = sbr.getCouponAccount().add(sbr.getPlatformAccount())
										.divide(new BigDecimal(2)).intValue();
								realRepayVal = realRepayVal.add(BigDecimal.valueOf(sbr.getInterest().intValue() / 2))
										.subtract(BigDecimal.valueOf(thisRatesAmt));
							}
							ratesAmt = ratesAmt.add(BigDecimal.valueOf(thisRatesAmt));
						}
					}
				} else {
					realRepayAmount = br.getPreCapital();
					realRepayVal = br.getInterest().subtract(br.getCouponAccount()).subtract(br.getPlatformAccount());
					ratesAmt = br.getCouponAccount().add(br.getPlatformAccount());
				}
				BigDecimal realRepayAmt = realRepayAmount.add(ratesAmt).add(realRepayVal);// 实际还款金额（实际还款本金+体验金+加息金+利息+手续费）
				custRepay.setRatesAmt(ratesAmt);// 加息金
				custRepay.setRealRepayVal(realRepayVal);// 实际还款利息
				custRepay.setRealRepayAmount(realRepayAmount);// 实际还款本金
				custRepay.setRealRepayAmt(realRepayAmt);// 实际还款金额（实际还款本金+体验金+加息金+利息+手续费）
				custRepayList.add(custRepay);
				recoverMoney = recoverMoney.add(realRepayAmt);
			}
		}
		retMap.put("recoverMoney", recoverMoney);
		retMap.put("custRepayList", custRepayList);

		return retMap;
	}

	/**
	 * 4.3.6标的还款
	 * 
	 * @param productOutput
	 * @param repayment
	 * @param recoverAll
	 * @param custRepayList
	 * @param repayFlag
	 * @param isPayoff
	 */
	@SuppressWarnings("unchecked")
	public void doBorrowRepay(ProductOutput productOutput, BorrowRepaymentInput repayment, Map<String, Object> retMap,
			boolean repayFlag, boolean isPayoff, String orderNo) {

		BigDecimal recoverAll = (BigDecimal) retMap.get("recoverMoney");// 实际还款金额
		List<CustRepay> custRepayList = (List<CustRepay>) retMap.get("custRepayList");
		/// Gateway_client/ accountAction_getAccountN_balace
		// 封装调用银行接口所需参数map对象
		Map<String, Object> map = new HashMap<>();
		map.put(TDFN.orderNo, orderNo);// 订单号
		map.put(BJFN.prodId, productOutput.getProductCode());// 产品编号
		map.put(BJFN.repayNum, repayment.getPeriod());// 还款期数，如果一次性还款，repay_num为1
		map.put(BJFN.isPayoff, isPayoff ? "0" : "1");// 是否整个标的还清：0-是，1-否；
		map.put(BJFN.transAmt, recoverAll);// 交易金额（所有实际还款金额之和）
		map.put(BJFN.repayFlag, repayFlag ? "0" : "1");// 本期已还清：0-是，1-否
		map.put(BJFN.funddata, JSON.toJSONString(custRepayList));// 资金数据，json格式记录还款金额

		// 调用银行还款接口
		// TODO 调用接口返回处理

		// 将还款步骤乐观锁repayStep字段状态改为2
		BorrowRepayment borrowRepayment = new BorrowRepayment();
		borrowRepayment.setId(repayment.getId());
		borrowRepayment.setRepayStep(2);
		borrowRepaymentMapper.updateByPrimaryKeySelective(borrowRepayment);
	}

	public void doRepayAndRecover(ProductOutput productOutput, BorrowRepaymentInput repayment,
			Map<String, Object> rRetMap, String orderNo, boolean isPayoff, boolean advance) {
		// 借款人实际需要还款的金额
		BigDecimal repayMoney = (BigDecimal) rRetMap.get("repayMoney");
		// 借款人实际需要还款的本金
		BigDecimal repayCap = (BigDecimal) rRetMap.get("repayCap");
		// 借款人实际还款的利息
		BigDecimal repayInterest = (BigDecimal) rRetMap.get("repayInterest");

		Integer repayStep = null;
		// 更新还款计划
		updateRepay(advance, repayment, repayMoney, repayCap, repayInterest, repayStep);
		// 调用交易中心 接口，处理投资人相关信息
		BorrowRepaymentInput repaymentInput = new BorrowRepaymentInput();
		repaymentInput.setBorrowId(productOutput.getId());
		repaymentInput.setPeriod(productOutput.getProductPeriod());
		repaymentInput.setOrderId(orderNo);
		repaymentInput.setAdvance(advance);
		borrowCollectionClient.collectionRepayment(repaymentInput);

		ProductInput input = new ProductInput();
		// 更新标的信息
		// isPayoff 为 true 则表示已还清 ,advance 为true表示提前还款
		if (isPayoff) {
			input.setProductStatus(8);
		} else if (advance) {
			input.setProductStatus(9);
		} else {
			input.setProductStatus(7);
		}
		input.setRepayLastTime(new Date());
		input.setId(productOutput.getId());
		this.updateProduct(input);
		repayStep = 3;
		updateRepay(advance, repayment, repayMoney, repayCap, repayInterest, repayStep);

	}

	private void updateRepay(boolean advance, BorrowRepaymentInput repayment, BigDecimal repayMoney,
			BigDecimal repayCap, BigDecimal repayInterest, Integer repayStep) {
		BorrowRepayment borrowRepayment = new BorrowRepayment();
		BeanUtils.copyProperties(repayment, borrowRepayment);
		borrowRepayment.setCapital(repayCap);
		borrowRepayment.setInterest(repayInterest);
		// 还款状态:0:未还1:已还,2:提前还款
		borrowRepayment.setStatus(1);
		borrowRepayment.setRepayTime(new Date());
		// 还款方式 0：平台代付 1:个人还款
		borrowRepayment.setRepayMode(0);
		borrowRepayment.setRepayStep(repayStep);
		borrowRepaymentMapper.updateByPrimaryKeySelective(borrowRepayment);
	}

	private boolean isAllFee(Date repayTime) {
		long fifteen = 1296000L;// 十五天
		long nowTime = DateUtil.getTimeStampSeconds();
		return nowTime + fifteen > repayTime.getDate();
	}

	private BorrowRepaymentOutput getBorrowRepayment(BorrowRepaymentInput repayment) {
		BorrowRepaymentInput input = new BorrowRepaymentInput();
		input.setBorrowId(repayment.getBorrowId());
		input.setUserId(repayment.getUserId());
		input.setPeriod(repayment.getPeriod());
		input.setStatus(ProductEnumConstant.RepaymentStatus.Status1.code);
		return borrowRepaymentMapper.getRepayInfoByparam(input);

	}

	private List<BackMoneyPlanOutput> getBackMoneyPlanOutput(BorrowRepaymentInput repayment) {
		BorrowRepaymentInput repaymentInput = new BorrowRepaymentInput();
		repaymentInput.setBorrowId(repayment.getBorrowId());
		repaymentInput.setPeriod(repayment.getPeriod());
		List<BackMoneyPlanOutput> collectionList = borrowCollectionClient
				.getTenderCollection(repaymentInput);
		return collectionList;
	}
}

package com.tuodao.bp.product.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tuodao.bp.model.business.product.output.RepayPlanOutput;
import com.tuodao.bp.product.db.mapper.basic.ProductMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.tuodao.bp.model.business.product.input.BorrowRepaymentInput;
import com.tuodao.bp.model.business.product.input.TransferInput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentInfoOutput;
import com.tuodao.bp.model.business.product.output.BorrowRepaymentOutput;
import com.tuodao.bp.product.constants.ProductResponseExceptionConstant;
import com.tuodao.bp.product.db.mapper.basic.BorrowRepaymentMapper;
import com.tuodao.bp.product.db.model.basic.BorrowRepayment;
import com.tuodao.bp.product.db.model.basic.BorrowRepaymentExample;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.product.service.IBorrowPlanTransferService;
import com.tuodao.bp.product.service.IBorrowRepayService;
import com.tuodao.bp.product.utils.ProductUtil;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.BorrowPlan;

/**
 * @Author wuchengjie
 * @Date 2017/9/19 0019 15:53
 * @Introduction
 */
@Transactional
@Service
public class BorrowRepayServiceImpl implements IBorrowRepayService {


    private static Logger logger = LoggerFactory.getLogger(BorrowRepayServiceImpl.class);

    @Autowired
    private BorrowRepaymentMapper borrowRepaymentMapper;

    @Autowired
    private IBorrowPlanTransferService borrowPlanTransferService;

    @Autowired
    private ProductMapper productMapper;
    /**
     * 插入还款记录（散标）
     * @param input
     * @return
     */
    @Override
    public Boolean insertBorrowRepayment(List<BorrowRepaymentInput> input) {

        Boolean result  = true;
        if(CollectionUtils.isEmpty(input))
        {
            throw new BizFeignException(ProductResponseExceptionConstant.REPAYMENT_IS_NULL);
        }
        logger.info("创建还款记录 标的号：｛｝",input.get(0).getBorrowId());

        for(BorrowRepaymentInput repaymentInput: input){

            BorrowRepayment repayment = new BorrowRepayment();
            BeanUtils.copyProperties(input,repayment);
            repayment.setStatus(0);
            repayment.setPreRepayTime(repaymentInput.getPreRepayTime());
            borrowRepaymentMapper.insert(repayment);
        }

        return result;
    }


    /**
     *  执行标的还款计划   （理财计划的 标的回款的时候，不能进行债权匹配）
     *
     * @param borrowId  标的id
     * @param period  还款期数
     * @param list  投资这个标的债权记录 （内部账号 或者 债权转让）
     * @param advance  是否提前还款
     * @return
     */
    @Override
    public Boolean doBorrowRepay(Integer borrowId, Integer period, List<TransferInput> list, Boolean advance) {

        BorrowRepaymentExample example = new BorrowRepaymentExample();
        BorrowRepaymentExample.Criteria cc = example.createCriteria();
        cc.andBorrowIdEqualTo(borrowId).andPeriodEqualTo(period);

        BorrowRepayment record = new BorrowRepayment();
        record.setStatus(1);
        record.setRepayTime(new Date());
        record.setRepayMode(0);

        //更新标 还款计划
        int i  = borrowRepaymentMapper.updateDoRepay(record,example);
        logger.info("borrowId{} 期数{} 更新条数{}",borrowId,period,i);

        //将相关的债权 继续重新计算
        Boolean b  = borrowPlanTransferService.refreshTransfer(list);

        //提前还款

        return b;
    }



    /**
     *  执行标的还款计划 （散标的还款）
     *
     * @param borrowId  标的id
     * @param period  还款期数
     * @param advance  是否提前还款
     * @return
     */
    @Override
    public Boolean doRepay(Integer borrowId, Integer period, Boolean advance) {

        if(!advance){
            BorrowRepaymentExample example = new BorrowRepaymentExample();
            BorrowRepaymentExample.Criteria cc = example.createCriteria();
            cc.andBorrowIdEqualTo(borrowId).andPeriodEqualTo(period);

            BorrowRepayment record = new BorrowRepayment();
            record.setStatus(1);
            record.setRepayTime(new Date());
            record.setRepayMode(0);
            record.setRemarks("borrowId"+borrowId+" 期数"+period);

            //更新标 还款计划 单期还款
            int i  = borrowRepaymentMapper.updateDoRepay(record,example);
            logger.info("borrowId{} 期数{} 更新条数{}",borrowId,period,i);


        }else {
            //提前还款
            BorrowRepaymentExample example = new BorrowRepaymentExample();
            BorrowRepaymentExample.Criteria cc = example.createCriteria();
            cc.andBorrowIdEqualTo(borrowId).andStatusEqualTo(0);

            BorrowRepayment record = new BorrowRepayment();
            record.setStatus(2);
            record.setRepayTime(new Date());
            record.setRepayMode(0);
            record.setRemarks("borrowId"+borrowId+" 提前还款");
            int i  = borrowRepaymentMapper.updateByExampleSelective(record,example);
            borrowRepaymentMapper.doAdvanceRepay(borrowId);
            logger.info("borrowId{} 期数{} 更新条数{}",borrowId,period,i);
        }


        return true;
    }

    /**
     * 获取标的的还款计划
     * @param borrowId 标的id
     * @param status 状态
     * @return
     */
    @Override
    public List<BorrowRepayment> getRepayList(Integer borrowId, Integer status) {
        BorrowRepaymentExample example = new BorrowRepaymentExample();
        example.createCriteria().andBorrowIdEqualTo(borrowId).andStatusEqualTo(status);
        return borrowRepaymentMapper.selectByExample(example);
    }


    /**
     * 查询某一个标的未还款期数 上期还款日期  如果没还款过 那么就取复审时间
     */
    @Override
    public BorrowRepaymentInfoOutput getRepayInfoByBorrowId(Integer borrowId) {

        BorrowRepaymentInfoOutput  list = borrowRepaymentMapper.getRepayInfoByBorrowId(borrowId);

        return list;
    }

    /**
     * 创建还款计划
     * @param product
     */
    @Override
    public Product createRepayPlan(Product product) {

        //通过数据生成计划
        List<BorrowPlan> repayList = ProductUtil.getPlan(product);
        Date lasDat = null;
        for (int i = 0; i < repayList.size(); i++) {
            BorrowPlan p = repayList.get(i);

            BorrowRepayment repay = new BorrowRepayment();
            repay.setUserId(product.getBorrowUserId());
            repay.setBorrowId(product.getId());
            repay.setPeriod(p.getPeriod());
            repay.setPeriods(p.getPeriods());
            repay.setFee(new BigDecimal(0));
            repay.setPreInterest(new BigDecimal(p.getPreInterest()));
            repay.setPreCapital(new BigDecimal(p.getPreCapital()));
            repay.setPreRepayTime(p.getPreTime());
            repay.setStatus(0);
            repay.setRepayMode(0);
            repay.setRemarks("自动生成");

            lasDat = p.getPreTime();
            borrowRepaymentMapper.insert(repay);
        }
        product.setRepayLastTime(lasDat);
        return product;
    }
    
    @Override
	public BorrowRepaymentInput getRepayInfoById(Integer id) {
		 
		return borrowRepaymentMapper.getRepayInfoById(id);
	}


	@Override
	public BorrowRepaymentOutput getRepayInfoByparam(BorrowRepaymentInput input) {
		return borrowRepaymentMapper.getRepayInfoByparam(input);
	}


	@Override
	public Boolean updateRepayment(BorrowRepaymentInput borrowRepaymentInput) {
		Boolean  falg = true;
		BorrowRepayment borrowRepayment= new BorrowRepayment();
		BeanUtils.copyProperties(borrowRepaymentInput, borrowRepayment);
		int count = borrowRepaymentMapper.updateByPrimaryKeySelective(borrowRepayment);
		if(count!=1){
			falg = false;
		}
		return falg;
	}

    @Override
    public List<RepayPlanOutput> getTemporaryRepayList(Integer borrowId) {

        Product product = productMapper.selectByPrimaryKey(borrowId);
        //通过数据生成计划
        List<BorrowPlan> repayList = ProductUtil.getPlan(product);
        List<RepayPlanOutput> repayPlanOutputs = new ArrayList<>();
        Date lasDat = null;
        for (int i = 0; i < repayList.size(); i++) {
            BorrowPlan p = repayList.get(i);
            RepayPlanOutput repay = new RepayPlanOutput();

            BeanUtils.copyProperties(p,repay);
            repayPlanOutputs.add(repay);
        }
        return repayPlanOutputs;
    }
}

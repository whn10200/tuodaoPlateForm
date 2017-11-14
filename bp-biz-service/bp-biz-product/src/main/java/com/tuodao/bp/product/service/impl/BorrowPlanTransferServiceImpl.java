package com.tuodao.bp.product.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.tuodao.bp.model.annotation.In;
import com.tuodao.bp.model.business.product.input.BorrowPlanTransferInput;
import com.tuodao.bp.model.business.product.input.TransferInput;
import com.tuodao.bp.model.business.product.output.BorrowPlanTransferOutput;
import com.tuodao.bp.model.mq.ProductUpdateMqInfo;
import com.tuodao.bp.product.db.mapper.basic.BorrowPlanTransferMapper;
import com.tuodao.bp.product.db.model.basic.BorrowCollection;
import com.tuodao.bp.product.db.model.basic.BorrowPlanTransfer;
import com.tuodao.bp.product.db.model.basic.BorrowPlanTransferExample;
import com.tuodao.bp.product.service.IBorrowPlanTransferService;
import com.tuodao.bp.result.exception.BizFeignException;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Transactional
@Service
public class BorrowPlanTransferServiceImpl implements IBorrowPlanTransferService{



    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(BorrowPlanTransferServiceImpl.class);

    @Override
    public PageInfo<BorrowPlanTransferOutput> getBorrowPlanTransferPage(BorrowPlanTransferInput input){
        BorrowPlanTransferExample example= new BorrowPlanTransferExample();
        BorrowPlanTransferExample.Criteria cnd= example.createCriteria();


        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
        List<BorrowPlanTransfer> list= borrowPlanTransferMapper.selectByExample(example);
        ImmutableList<BorrowPlanTransferOutput> resultList = FluentIterable.<BorrowPlanTransfer>from(list).transform(new Function<BorrowPlanTransfer, BorrowPlanTransferOutput>() {
            @Override
            public BorrowPlanTransferOutput apply(BorrowPlanTransfer input) {
                BorrowPlanTransferOutput out = new BorrowPlanTransferOutput();
                BeanUtils.copyProperties(input, out);
                return out;
            }
        }).toList();

        PageInfo<BorrowPlanTransferOutput> result = new PageInfo<>(resultList);
        Page<BorrowPlanTransfer> page = (Page<BorrowPlanTransfer>)list;
        result.setTotal(page.getTotal());
        return result;
    }


    @Override
    public List<BorrowPlanTransferOutput> getBorrowPlanTransferList(BorrowPlanTransferInput input){
        BorrowPlanTransferExample example= new BorrowPlanTransferExample();
        BorrowPlanTransferExample.Criteria cnd= example.createCriteria();

        List<BorrowPlanTransfer> list= borrowPlanTransferMapper.selectByExample(example);
        ImmutableList<BorrowPlanTransferOutput> resultList = FluentIterable.<BorrowPlanTransfer>from(list).transform(new Function<BorrowPlanTransfer, BorrowPlanTransferOutput>() {
            @Override
            public BorrowPlanTransferOutput apply(BorrowPlanTransfer input) {
                BorrowPlanTransferOutput out = new BorrowPlanTransferOutput();
                BeanUtils.copyProperties(input, out);
                return out;
            }
        }).toList();
        return resultList;
    }
    @Autowired
    private BorrowPlanTransferMapper borrowPlanTransferMapper;


    /**
     * 更新理财计划中债权的状态
     * @param mqInfo
     * @return
     */
    @Override
    public Boolean updateTransferStatus(ProductUpdateMqInfo mqInfo)
    {
        BorrowPlanTransfer record = new BorrowPlanTransfer();
        record.setId(mqInfo.getProductId());
        record.setStatus(mqInfo.getProductStatus());
        int ret =  borrowPlanTransferMapper.updateByPrimaryKeySelective(record);

        if(ret>0){
            return true;
        }
        return false;

    }

    /**
     *   重新刷债权数据数据  废除过时的债权
     *
     * tenderList: 剩余没有还款的债权
     */
    @Override
    public Boolean refreshTransfer(List<TransferInput> collections) throws BizFeignException {

        if(!CollectionUtils.isEmpty(collections))
        {
            /**
             * 通过tenderid 获取相应的债权
             */
            BorrowPlanTransferExample example = new BorrowPlanTransferExample();
            BorrowPlanTransferExample.Criteria criteria =  example.createCriteria();


            List<Integer> idList = new ArrayList<Integer>();
            for(TransferInput collection : collections){
                idList.add(collection.getTenderId());
            }
            //进行中  并且投资id 相同
            criteria.andTenderIdIn(idList).andStatusNotEqualTo(2);
            //得到债权
            List<BorrowPlanTransfer>  borrowPlanTransferList = borrowPlanTransferMapper.selectByExample(example);

            List<BorrowPlanTransfer>  changedList = new ArrayList<>();  //需要修改的列表

            for(TransferInput collection : collections){
                for(BorrowPlanTransfer borrowPlanTransfer : borrowPlanTransferList){
                    if(collection.getTenderId().equals( borrowPlanTransfer.getTenderId())){
                        if(collection.getAmount().equals( borrowPlanTransfer.getAccount()))
                        {
                            idList.remove(borrowPlanTransfer.getTenderId());
                        }else{
                           // changedList.add(borrowPlanTransfer);
                            borrowPlanTransfer.setAccount(collection.getAmount());
                            borrowPlanTransfer.setId(null);
                            borrowPlanTransferMapper.insert(borrowPlanTransfer);
                        }
                    }
                }
            }

          BorrowPlanTransfer record = new BorrowPlanTransfer();
          record.setStatus(2);
          borrowPlanTransferMapper.updateByExampleSelective(record,example);

        }

        return true;
    }


    /**
     * 查询超过48小时的理财计划债权 用于内部账号购买
     */
    @Override
    public List<BorrowPlanTransferOutput> getOvertimeTransferList()
    {
        BorrowPlanTransferExample example= new BorrowPlanTransferExample();
        BorrowPlanTransferExample.Criteria cnd= example.createCriteria();
        Date date = new Date();

        Date ago =  DateUtils.addHours(date,-48);

        // 时间大于48小时 还在转让中
        cnd.andStatusEqualTo(1).andTransfeStartTimeLessThan(ago);

        List<BorrowPlanTransfer> list= borrowPlanTransferMapper.selectByExample(example);
        ImmutableList<BorrowPlanTransferOutput> resultList = FluentIterable.<BorrowPlanTransfer>from(list).transform(new Function<BorrowPlanTransfer, BorrowPlanTransferOutput>() {
            @Override
            public BorrowPlanTransferOutput apply(BorrowPlanTransfer input) {
                BorrowPlanTransferOutput out = new BorrowPlanTransferOutput();
                BeanUtils.copyProperties(input, out);
                return out;
            }
        }).toList();
        return resultList;
    }


    /**
     * 更新产品 剩余金额 并且改成 已经转让的金额  account_yes
     */
    @Override
    public boolean updateTransferAmount(Integer transferId, BigDecimal accountYes)
    {
        try {
                BorrowPlanTransfer record = new BorrowPlanTransfer();
                record.setId(transferId);
                record.setAccountYes(accountYes);
                borrowPlanTransferMapper.updateByPrimaryKeySelective(record);

                return true;
        } catch (Exception e) {
            logger.error("transferId{}更新产品 剩余金额 并且改成加入理财计划失败：{}",transferId,e.getCause());
            throw e;
        }
    }


    /**
     * 根据id 获取 债权信息
     * @param id
     * @return
     */
    @Override
    public BorrowPlanTransferOutput getBorrowPlanTransfer( Integer id){
        BorrowPlanTransfer input= borrowPlanTransferMapper.selectByPrimaryKey(id);
        BorrowPlanTransferOutput out = new BorrowPlanTransferOutput();
        BeanUtils.copyProperties(input, out);
        return out;
    }

    /**
     * {@link IBorrowPlanTransferService#getBorrowPlanTransferList(BorrowPlanTransferInput)}
     * @return
     */
    @Override
    public List<BorrowPlanTransferOutput> getPlanReverifyTransferList() {

        List<BorrowPlanTransfer> list= borrowPlanTransferMapper.getPlanReverifyTransferList();
        return toOutput(list);
    }

    private  List<BorrowPlanTransferOutput> toOutput(List<BorrowPlanTransfer> list)
    {
        ImmutableList<BorrowPlanTransferOutput> resultList = FluentIterable.<BorrowPlanTransfer>from(list).transform(new Function<BorrowPlanTransfer, BorrowPlanTransferOutput>() {
            @Override
            public BorrowPlanTransferOutput apply(BorrowPlanTransfer input) {
                BorrowPlanTransferOutput out = new BorrowPlanTransferOutput();
                BeanUtils.copyProperties(input, out);
                return out;
            }
        }).toList();
        return resultList;
    }

}

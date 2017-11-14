package com.tuodao.bp.product.service.impl;

import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableList;
import com.tuodao.bp.model.business.product.input.BorrowMappingBankInput;
import com.tuodao.bp.model.business.product.output.BorrowMappingBankOutput;
import com.tuodao.bp.model.business.product.output.ProductOutput;
import com.tuodao.bp.product.db.mapper.basic.BorrowMappingBankMapper;
import com.tuodao.bp.product.db.model.basic.BorrowMappingBank;
import com.tuodao.bp.product.db.model.basic.BorrowMappingBankExample;
import com.tuodao.bp.product.db.model.basic.ProductWithBLOBs;
import com.tuodao.bp.product.service.IBorrowMappingBankService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/11/6 0006 14:56
 * @Introduction  标的北京银行的满标复审状态服务类
 */
@Service
@Transactional
public class BorrowMappingBankServiceImpl implements IBorrowMappingBankService {


    @Autowired
    private BorrowMappingBankMapper mappingBankMapper;


    /**
     * 更新记录的状态
     */
    @Override
    public Boolean updateRecord(BorrowMappingBankInput input)
    {
        BorrowMappingBankExample example = new BorrowMappingBankExample();
        example.createCriteria().andBorrowIdEqualTo(input.getBorrowId());
        BorrowMappingBank record = mappingBankMapper.selectByExample(example).get(0);

        record.setBorrowBankStatus(input.getBorrowBankStatus());
        record.setCompensatoryStatus(input.getCompensatoryStatus());

        if(input.getCompensatoryAmountYes() != null && input.getCompensatoryAmountYes()!=null){
            record.setCompensatoryAmountYes(input.getCompensatoryAmountYes().add(record.getCompensatoryAmountYes()));

            if(record.getCompensatoryAmount().compareTo(record.getCompensatoryAmountYes())==1){
                record.setCompensatoryStatus(1);
            }else{
                record.setCompensatoryStatus(2);
            }
        }
        mappingBankMapper.updateByPrimaryKey(record);

        return true;
    }


	@Override
	public Boolean insertRecord(BorrowMappingBank record) {
		mappingBankMapper.insertSelective(record);
		return true;
	}


	@Override
	public void deleteBorrowMappingBankByProductId(Integer productId) {
		mappingBankMapper.deleteBorrowMappingBankByProductId(productId);
	}

    @Override
    public BorrowMappingBankOutput getRecordByBorrowId(Integer borrowId) {

        BorrowMappingBankExample example = new BorrowMappingBankExample();
        example.createCriteria().andBorrowIdEqualTo(borrowId);
        BorrowMappingBank record = mappingBankMapper.selectByExample(example).get(0);
        BorrowMappingBankOutput output = new BorrowMappingBankOutput();
        BeanUtils.copyProperties(record,output);

        return output;
    }

    @Override
    public List<BorrowMappingBankOutput> getCompensationList() {

        List<BorrowMappingBank> recordList = mappingBankMapper.getCompensationList();

        ImmutableList<BorrowMappingBankOutput> resultList = FluentIterable.<BorrowMappingBank> from(recordList)
                .transform(new Function<BorrowMappingBank, BorrowMappingBankOutput>() {
                    @Override
                    public BorrowMappingBankOutput apply(BorrowMappingBank input) {
                        BorrowMappingBankOutput out = new BorrowMappingBankOutput();
                        BeanUtils.copyProperties(input, out);
                        return out;
                    }
                }).toList();

        return resultList;
    }


}

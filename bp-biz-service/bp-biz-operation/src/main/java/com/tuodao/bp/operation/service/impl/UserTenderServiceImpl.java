package com.tuodao.bp.operation.service.impl;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tuodao.bp.model.business.operation.output.TenderVoucherOutput;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserDiscountMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpUserDiscount;
import com.tuodao.bp.operation.persistence.model.basic.OpUserDiscountExample;
import com.tuodao.bp.operation.service.IUserTenderService;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * author hechuan
 * <p>
 * created on 2017/10/18
 * <p>
 * since V1.0.0
 */
@Service
public class UserTenderServiceImpl implements IUserTenderService {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserTenderServiceImpl.class);


    @Autowired
    private OpUserDiscountMapper opUserDiscountMapper;

    /**
     * 用户投资-使用优惠券
     *
     * @param inputList
     * @return
     */
    @Override
    public Map<String, TenderVoucherOutput> getUserTender(List<Map<String,Integer>> inputList) {


        Map<String,TenderVoucherOutput> map = Maps.newConcurrentMap();

        OpUserDiscountExample example = new OpUserDiscountExample();
        OpUserDiscountExample.Criteria cc = example.createCriteria();

        if(CollectionUtils.isEmpty(inputList)){
            logger.info("inputList 为空,返回空map");
            return map;
        }

        List<Map<String, Integer>> collect = inputList.stream().filter(input -> input.get("voucherId") != null).collect(Collectors.toList());

        if(CollectionUtils.isEmpty(collect)){
            logger.info("过滤掉券id(voucherId)为空的集合 collect 为空,返回空map");
            return map;
        }

        cc.andIdIn(Lists.transform(collect, input -> input.get("voucherId").longValue()));

        List<OpUserDiscount> queryList = opUserDiscountMapper.selectByExample(example);

        if(CollectionUtils.isEmpty(queryList)){
            logger.info("queryList 为空,返回空map");
            return map;
        }

        collect.stream().forEach(c -> queryList.stream().forEach(q -> {
                if(Objects.equal(c.get("voucherId").longValue(),q.getId())){
                    if (Objects.equal(q.getDisStatus(), OperationBizConstant.DISCOUNT_STATUS_USABLE)
                            && isRightDate(q.getEffectDate(),q.getInvalidDate())){

                        TenderVoucherOutput out = new TenderVoucherOutput();
                        out.setDiscountAvailable(q.getDiscountAvailable());
                        out.setDiscountTitle(q.getDiscountTitle());
                        out.setDiscountType(q.getDiscountType());
                        out.setTenderId(c.get("tenderId"));
                        out.setVoucherId(c.get("voucherId"));

                        map.put(c.get("tenderId").toString(),out);

                    }
                }
        }));

        logger.info("map : [{}]",map);

        return map;
    }

    private boolean isRightDate(Date effectDate,Date invalidDate){
        return TimeUtils.between(new Date(),effectDate,invalidDate);
    }
}

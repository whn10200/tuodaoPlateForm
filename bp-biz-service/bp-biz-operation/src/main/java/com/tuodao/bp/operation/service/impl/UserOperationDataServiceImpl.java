package com.tuodao.bp.operation.service.impl;

import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.operation.output.UserOperationStatisOutput;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserOperationDataMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationData;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationDataExample;
import com.tuodao.bp.operation.service.IUserOperationDataService;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户运营数据
 * author hechuan
 * <p>
 * created on 2017/9/25
 * <p>
 * since V1.0.0
 */
@Service
public class UserOperationDataServiceImpl implements IUserOperationDataService {

    // 日志
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(UserOperationDataServiceImpl.class);

    @Autowired
    private OpUserOperationDataMapper opUserOperationDataMapper;

    /**
     * @see IUserOperationDataService#getUserOperationDataList(BasePojo)
     * @param input 基础用户信息
     * @return
     */
    @Override
    public List<UserOperationStatisOutput> getUserOperationDataList(BasePojo input) {
        logger.debug("获取 用户运营数据 input : [{}]",input);

        OpUserOperationDataExample example = new OpUserOperationDataExample();
        OpUserOperationDataExample.Criteria cc = example.createCriteria();
        cc.andUserIdEqualTo(input.getUserId())
                .andUserMobileEqualTo(input.getMobile());

        List<OpUserOperationData> queryList = opUserOperationDataMapper.selectByExample(example);
        List<UserOperationStatisOutput> resultList = queryList.stream().map(opUserOperationData -> {
            UserOperationStatisOutput out = new UserOperationStatisOutput();
            BeanUtils.copyProperties(opUserOperationData, out);
            return out;
        }).collect(Collectors.toList());

        logger.debug("获取 用户运营数据 userId:[{}]，resultList.size :[{}]",input.getUserId(),resultList.size());
        return resultList;
    }
}

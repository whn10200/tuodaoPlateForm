package com.tuodao.bp.operation.service.impl;

import com.tuodao.bp.model.facade.operation.output.OpBannerManagelListOutput;
import com.tuodao.bp.operation.persistence.mapper.basic.OpBannerManagerMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpBannerManager;
import com.tuodao.bp.operation.service.IOpBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 查询banner serviceImpl
 * User: yinping
 * Date: 2017/10/22
 * Time: 16:31
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class OpBannerServiceImpl implements IOpBannerService {
    private static Logger logger = LoggerFactory.getLogger(OpBannerServiceImpl.class);

    @Autowired
    private OpBannerManagerMapper opBannerManagerMapper;

    @Override
    public List<OpBannerManagelListOutput> selectBanner(OpBannerManager input) {
        List<OpBannerManagelListOutput> outList = new ArrayList<OpBannerManagelListOutput>();
        List<OpBannerManager> list = opBannerManagerMapper.selectBannerByParams(input);
        logger.info("查询的banner list 为："+list);
        if(list.size()>0){
            for (int i=0;i<list.size();i++){
                OpBannerManagelListOutput out = new OpBannerManagelListOutput();
                BeanUtils.copyProperties(list.get(i), out);
                outList.add(out);
            }
        }
        return outList;
    }
}

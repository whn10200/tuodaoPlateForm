package com.tuodao.bp.operation.service.impl;

import com.tuodao.bp.model.business.operation.input.OpContentInput;
import com.tuodao.bp.model.business.operation.input.OpContentTitleInput;
import com.tuodao.bp.model.facade.operation.output.OpContentManagelListOutput;
import com.tuodao.bp.operation.persistence.mapper.basic.OpContentManagerMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpContentManager;
import com.tuodao.bp.operation.service.IOpContentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: 查询内容管理service
 * User: yinping
 * Date: 2017/10/22
 * Time: 16:31
 * Copyright:拓道金服 Copyright (c) 2017
 */
@Service
public class OpContentServiceImpl implements IOpContentService {
    private static Logger logger = LoggerFactory.getLogger(OpContentServiceImpl.class);

    @Autowired
    private OpContentManagerMapper opContentManagerMapper;
    /**
     * 查询内容管理标题列表
     * @return
     */
    @Override
    public List<OpContentManagelListOutput> selectContentTitle(OpContentTitleInput input) {
        List<OpContentManagelListOutput> outList = new ArrayList<OpContentManagelListOutput>();
        List<OpContentManager> titlelist = opContentManagerMapper.selectByContentRemark(input);
        logger.info("查询的banner list 为："+titlelist);
        if(titlelist.size()>0){
            for (int i=0;i<titlelist.size();i++){
                OpContentManagelListOutput out = new OpContentManagelListOutput();
                BeanUtils.copyProperties(titlelist.get(i), out);
                outList.add(out);
            }
        }
        return outList;
    }

    /**
     * 查询内容管理
     * @return
     */
    @Override
    public List<OpContentManagelListOutput> selectContentManager(OpContentInput input) {
        List<OpContentManagelListOutput> outList = new ArrayList<OpContentManagelListOutput>();
        List<OpContentManager> contentlist = opContentManagerMapper.selectByContentId(input);
        logger.info("查询的banner list 为："+contentlist);
        if( contentlist.size() >0){
            for (int i=0;i<contentlist.size();i++){
                OpContentManagelListOutput output = new OpContentManagelListOutput();
                BeanUtils.copyProperties(contentlist.get(i), output);
                outList.add(output);
            }
        }
        return outList;
    }
}

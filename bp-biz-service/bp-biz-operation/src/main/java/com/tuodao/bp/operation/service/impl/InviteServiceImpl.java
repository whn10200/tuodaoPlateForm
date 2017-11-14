package com.tuodao.bp.operation.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.PagePojo;
import com.tuodao.bp.model.annotation.Md5;
import com.tuodao.bp.model.business.operation.output.InviteRecordOutput;
import com.tuodao.bp.operation.persistence.mapper.basic.OpInviteRecordMapper;
import com.tuodao.bp.operation.persistence.mapper.biz.BizOpInviteMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRecord;
import com.tuodao.bp.operation.persistence.model.basic.OpInviteRecordExample;
import com.tuodao.bp.operation.service.IInviteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @description: 用户邀请相关接口实现
 * @author: mif
 * @date: 2017/9/15
 * @time: 10:57
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class InviteServiceImpl implements IInviteService {

    private static Logger logger = LoggerFactory.getLogger(InviteServiceImpl.class);

    @Resource
    private OpInviteRecordMapper opInviteRecordMapper;

    @Resource
    private BizOpInviteMapper bizOpInviteMapper;

    @Override
    public PageInfo<InviteRecordOutput> getInviteRecord(PagePojo pagePojo) {
        logger.info("get invite records ,pagePojo={}", pagePojo);

        PageHelper.startPage(pagePojo.getCurrentPage(), pagePojo.getPageSize());
        OpInviteRecordExample example = new OpInviteRecordExample();
        example.createCriteria().andUserIdEqualTo(pagePojo.getUserId());
        example.setOrderByClause("gmt_create DESC");
        List<OpInviteRecord> opInviteRecords = opInviteRecordMapper.selectByExample(example);

        // 组装返回
        List<InviteRecordOutput> resultList = opInviteRecords.stream().map(record -> {
            InviteRecordOutput out = new InviteRecordOutput();
            BeanUtils.copyProperties(record, out);
            return out;
        }).collect(Collectors.toList());
        PageInfo<InviteRecordOutput> pageInfo = new PageInfo<>(resultList);
        Page<OpInviteRecord> page = (Page<OpInviteRecord>) opInviteRecords;
        pageInfo.setPages(page.getPages());
        pageInfo.setTotal(page.getTotal());

        return pageInfo;
    }

    /**
     * 统计用户邀请奖励
     *
     * @param userId 用户ID
     * @return 奖励总额
     */
    @Override
    public BigDecimal getTotalInviteAward(String userId) {
        return bizOpInviteMapper.getTotalInviteAward(userId);
    }
}

package com.tuodao.bp.operation.persistence.mapper.biz;

import com.tuodao.bp.operation.persistence.model.biz.BizOpWelfareActivity;

import java.util.List;

/**
 * @description: 自定义运营活动Mapper
 * @author: mif
 * @date: 2017/9/28
 * @time: 18:26
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public interface BizOpWelfareActivityMapper {

    /**
     * 根据ID获取福利活动信息
     *
     * @param welfareActivityCode 福利活动CODE
     * @return
     */
    List<BizOpWelfareActivity> selectOpWelfareActivityListByCode(String welfareActivityCode);
}


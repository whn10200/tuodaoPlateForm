package com.tuodao.bp.useraccount.service.impl;

import com.tuodao.bp.model.business.useraccount.input.ConsigneeInfoInput;
import com.tuodao.bp.model.business.useraccount.output.UserInfoDetailOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserInfoDetailMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfoDetail;
import com.tuodao.bp.useraccount.service.IUserInfoDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 用户详情实现
 * @author: mif
 * @date: 2017/10/12
 * @time: 14:50
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class UserInfoDetailServiceImpl implements IUserInfoDetailService {

    private static Logger logger = LoggerFactory.getLogger(UserInfoDetailServiceImpl.class);

    @Resource
    private UserInfoDetailMapper userInfoDetailMapper;

    /**
     * @param userId 用户ID
     * @return 用户详情
     * @see IUserInfoDetailService#getUserInfoDetail(String)
     */
    @Override
    public UserInfoDetailOutput getUserInfoDetail(String userId) {
        UserInfoDetail userInfoDetail = userInfoDetailMapper.selectByPrimaryKey(userId);
        if (null == userInfoDetail || userInfoDetail.getIsDel() == PublicConstant.DEL_YES) {
            logger.info("用户详情不存在或用户已删除，userId", userId);
            throw new BizFeignException(UaRespExceptionConstant.USER_DETAIL_NOT_EXIST);
        }
        UserInfoDetailOutput output = new UserInfoDetailOutput();
        BeanUtils.copyProperties(userInfoDetail, output);
        return output;
    }

    /**
     * @param consigneeInfoInput 收件人信息
     * @see IUserInfoDetailService#updateConsigneeInfo(ConsigneeInfoInput)
     */
    @Override
    public void updateConsigneeInfo(ConsigneeInfoInput consigneeInfoInput) {
        logger.info("修改收件人信息,consigneeInfoInput={}", consigneeInfoInput);
        UserInfoDetail userInfoDetail = new UserInfoDetail();
        userInfoDetail.setUserId(consigneeInfoInput.getUserId());
        BeanUtils.copyProperties(consigneeInfoInput, userInfoDetail);
        userInfoDetailMapper.updateByPrimaryKeySelective(userInfoDetail);
    }
}

package com.tuodao.bp.useraccount.service.impl;

import com.google.common.base.Joiner;
import com.tuodao.bp.cache.basic.AccessCache;
import com.tuodao.bp.model.AccessInfo;
import com.tuodao.bp.model.admin.UserModifyMobileInput;
import com.tuodao.bp.model.business.useraccount.UserAccountInfo;
import com.tuodao.bp.model.business.useraccount.input.*;
import com.tuodao.bp.model.business.useraccount.output.UserAccountInfoOutput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.enums.AccessType;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.useraccount.constant.UaRespExceptionConstant;
import com.tuodao.bp.useraccount.constant.UserAccountBizConstant;
import com.tuodao.bp.useraccount.persistence.mapper.basic.UserVipInfoMapper;
import com.tuodao.bp.useraccount.persistence.model.basic.UserInfo;
import com.tuodao.bp.useraccount.persistence.model.basic.UserVipInfo;
import com.tuodao.bp.useraccount.service.IUserService;
import com.tuodao.bp.useraccount.service.UserBaseService;
import com.tuodao.bp.useraccount.service.async.AsyncUserService;
import com.tuodao.bp.utils.CommonUtils;
import com.tuodao.bp.utils.HmacSha1Utils;
import com.tuodao.bp.utils.MD5Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Objects;

/**
 * @description: 用户相关service implement
 * @author: mif
 * @date: 2017/8/28
 * @time: 10:45
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@Service
public class UserServiceImpl extends UserBaseService implements IUserService {

    private final static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Resource
    private AsyncUserService asyncUserService;

    @Resource
    private AccessCache accessCache;

    @Resource
    private UserVipInfoMapper userVipInfoMapper;

    @Override
    public void register(RegisterInput input) {
        logger.info("用户注册 ,input = {}", input);

        checkMobileExist(input.getMobile());

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(Joiner.on("-").join(input.getMobile(), CommonUtils.getRandRandom(20)));
        BeanUtils.copyProperties(input, userInfo);
        userInfo.setRegisterIp(input.getRegisterIp());
        userInfo.setGmtCreator(input.getMobile());
        //设置密码
        String loginPwdKey = CommonUtils.generateVerifyCode(6);
        userInfo.setLoginPwdKey(loginPwdKey);
        userInfo.setLoginPassword(generatePw(input.getLoginPassword(), loginPwdKey));
        userInfo.setInviteCode(CommonUtils.generateVerifyCode(5));
        userInfo.setUserStatus(UserAccountBizConstant.USER_STATUS_NOMAL);
        userInfo.setUserType(UserAccountBizConstant.USER_TYPE_INVEST);
        userInfo.setInvestFlag(PublicConstant.IF_NO);
        userInfo.setInvestUserType(UserAccountBizConstant.INVEST_USER_TYPE_NOMAL);
        userInfo.setIsNewbie(PublicConstant.IF_YES);
        userInfo.setIsOpenDeposit(PublicConstant.IF_NO);
        userInfo.setIsBindBank(PublicConstant.IF_NO);
        userInfo.setSourceChannel(input.getSourceChannel());
        userInfo.setGmtCreate(new Date());

        //间接邀请人
        UserInfo directInviteUser = null;
        if (!StringUtils.isEmpty(input.getInviterCode())
                && !Objects.equals(input.getMobile(), input.getInviterCode())) { //注册号码与邀请号码不一致
            directInviteUser = setInvitation(userInfo, input.getInviterCode());
        }

        //保存用户数据
        userInfoMapper.insertSelective(userInfo);

        putUserAccountInfoCache(userInfo);

        // 异步初始化相关数据
        asyncUserService.initRegister(userInfo);

        //新人首次邀请任务
        if (null != directInviteUser) {
            asyncUserService.send2FirstInvite(userInfo.getUserId(), directInviteUser);
        }
    }

    /**
     * 初始化数据到缓存
     *
     * @param userInfo
     */
    private void putUserAccountInfoCache(UserInfo userInfo) {
        UserAccountInfo accountInfo = new UserAccountInfo();
        BeanUtils.copyProperties(userInfo, accountInfo);
        userAccountCache.putCache(accountInfo);
    }

    @Override
    public UserAccountInfoOutput getUserAccountInfo(String userId) {
        UserAccountInfoOutput output = new UserAccountInfoOutput();

        UserAccountInfo userAccountInfo = super.getUserAccountInfo(userId);
        BeanUtils.copyProperties(userAccountInfo, output);

        UserVipInfo userVipInfo = userVipInfoMapper.selectByPrimaryKey(userId);
        output.setVipLevel(userVipInfo.getVipLevel());

        return output;
    }

    @Override
    public UserLoginOutput login(LoginInput input) {
        UserInfo userInfo = super.selectUserByMobile(input.getMobile());
        if (null == userInfo) {
            throw new BizFeignException(UaRespExceptionConstant.USER_NAME_OR_PW_ERROR);
        }

        if (!Objects.equals(userInfo.getLoginPassword(), generatePw(input.getLoginPassword(), userInfo.getLoginPwdKey()))) {
            throw new BizFeignException(UaRespExceptionConstant.USER_NAME_OR_PW_ERROR);
        }

        // 生成accessId,accessKey 存入redis中
        String accessId = HmacSha1Utils.creatAccessId(input.getMobile());
        String accessKey = HmacSha1Utils.creatAccessKey(accessId);

        //将数据存入缓存
        if (isApp(input.getRequestType())) {
            accessCache.putAccessInfoApp(new AccessInfo(accessId, accessKey, userInfo.getUserId(), userInfo.getMobile()));
        } else {
            accessCache.putAccessInfo(new AccessInfo(accessId, accessKey, userInfo.getUserId(), userInfo.getMobile()));
        }

        //异步插入登录日志
        asyncUserService.insertLoginLog(input, userInfo.getUserId());

        UserLoginOutput output = new UserLoginOutput();
        BeanUtils.copyProperties(this.getUserAccountInfo(userInfo.getUserId()), output);
        output.setAccessId(accessId);
        output.setAccessKey(accessKey);

        //异步刷新缓存
        super.refreshUserAccountCache(userInfo.getUserId());
        return output;
    }

    /**
     * 是否是APP请求
     *
     * @param requestSource 登录来源
     * @return 是否APP请求
     */
    private boolean isApp(String requestSource) {
        return com.google.common.base.Objects.equal(requestSource, AccessType.APP.name());
    }

    /**
     * 生成password
     *
     * @param password 密码
     * @param key      密钥
     * @return 密码
     */
    private String generatePw(String password, String key) {
        return MD5Utils.md5(password + key);
    }

    @Override
    public void forgetLoginPw(ForgetLoginPwInput input) {

        UserInfo userInfo = super.selectUserByMobile(input.getMobile());

        if (null == userInfo) {
            throw new BizFeignException(UaRespExceptionConstant.USER_NOT_REGISTERED);
        }

        UserInfo temp = new UserInfo();
        String loginPwKey = CommonUtils.generateVerifyCode(6);
        temp.setUserId(userInfo.getUserId());
        temp.setLoginPassword(generatePw(input.getLoginPw(), loginPwKey));
        temp.setLoginPwdKey(loginPwKey);
        temp.setPwSecurityLevel(input.getPwSecurityLevel());
        temp.setGmtCreator(input.getMobile());

        userInfoMapper.updateByPrimaryKeySelective(temp);
    }

    @Override
    public void forgetPayPw(ForgetPayPwInput input) {
        UserInfo userInfo = super.selectUserByMobile(input.getMobile());

        if (null == userInfo) {
            throw new BizFeignException(UaRespExceptionConstant.USER_NOT_REGISTERED);
        }

        UserInfo temp = new UserInfo();
        temp.setUserId(userInfo.getUserId());
        String payPwKey = CommonUtils.generateVerifyCode(6);
        temp.setPayPassword(generatePw(input.getPayPw(), payPwKey));
        temp.setPayPwdKey(payPwKey);
        temp.setGmtCreator(input.getMobile());

        userInfoMapper.updateByPrimaryKeySelective(temp);
    }

    @Override
    public void updateLoginPw(UpdateLoginPwInput input) {
        UserInfo userInfo = super.selectUserByUserId(input.getUserId());

        UserInfo temp = new UserInfo();
        temp.setUserId(input.getUserId());

        if (!Objects.equals(userInfo.getLoginPassword(), generatePw(input.getOldLoginPw(), userInfo.getLoginPwdKey()))) {
            throw new BizFeignException(UaRespExceptionConstant.OLD_PW_ERROR);
        }
        String loginPwKey = CommonUtils.generateVerifyCode(6);
        temp.setLoginPwdKey(loginPwKey);
        temp.setLoginPassword(generatePw(input.getNewLoginPw(), loginPwKey));
        userInfoMapper.updateByPrimaryKeySelective(temp);
    }

    @Override
    public void updatePayPw(UpdatePayPwInput input) {
        UserInfo userInfo = super.selectUserByUserId(input.getUserId());

        UserInfo temp = new UserInfo();
        temp.setUserId(input.getUserId());

        // 支付密码
        if (!Objects.equals(userInfo.getPayPassword(), generatePw(input.getOldPayPw(), userInfo.getPayPwdKey()))) {
            throw new BizFeignException(UaRespExceptionConstant.OLD_PW_ERROR);
        }
        String payPwKey = CommonUtils.generateVerifyCode(6);
        temp.setPayPassword(generatePw(input.getNewPayPw(), payPwKey));
        temp.setPayPwdKey(payPwKey);

        userInfoMapper.updateByPrimaryKeySelective(temp);
    }

    /**
     * @param input 头像地址
     * @see IUserService#uploadAvatar(UserAvatarInput)
     */
    @Override
    public void uploadAvatar(UserAvatarInput input) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(input.getUserId());
        userInfo.setAvatarUrl(input.getAvatarUrl());
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }

    /**
     * 验证手机号码是否已注册
     *
     * @param mobile 手机号码
     * @return 是否注册过
     */
    @Override
    public Boolean validateMobileRegistered(String mobile) {
        UserInfo userInfo = super.selectUserByMobile(mobile);
        return null != userInfo;
    }

    /**
     * 检查用户是否存在
     *
     * @param mobile 手机号码
     */
    private void checkMobileExist(String mobile) {
        UserInfo userInfo = super.selectUserByMobile(mobile);
        if (null != userInfo) {
            throw new BizFeignException(UaRespExceptionConstant.USER_ACCOUNT_MOBILE_REGISTERED);
        }
    }

    /**
     * 设置邀请信息
     *
     * @param userInfo    用户信息
     * @param inviterCode 邀请码或号码
     */

    UserInfo setInvitation(UserInfo userInfo, String inviterCode) {
        UserInfo directInviteUser = super.selectUserByInviteCode(inviterCode);
        if (null == directInviteUser) {
            return null;
        }
        //直接邀请人编号
        userInfo.setDirectInviterNo(directInviteUser.getUserId());
        //间接邀请人
        userInfo.setIndirectInviterNo(directInviteUser.getDirectInviterNo());

        return directInviteUser;
    }

    /**
     * 修改手机号码
     *
     * @param userModifyMobileInput 修改内容
     */
    @Override
    public void modifyMobile(UserModifyMobileInput userModifyMobileInput) {

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userModifyMobileInput.getUserId());
        userInfo.setMobile(userModifyMobileInput.getMobile());
        userInfo.setGmtModifier(userModifyMobileInput.getGmtModifier());
        userInfoMapper.updateByPrimaryKeySelective(userInfo);
    }
}

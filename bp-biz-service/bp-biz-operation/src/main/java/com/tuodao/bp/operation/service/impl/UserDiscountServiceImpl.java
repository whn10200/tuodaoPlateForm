package com.tuodao.bp.operation.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tuodao.bp.model.BasePojo;
import com.tuodao.bp.model.business.IdInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountTenderInput;
import com.tuodao.bp.model.business.operation.input.UserDiscountsInput;
import com.tuodao.bp.model.business.operation.output.UserDiscountOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountQueryOutput;
import com.tuodao.bp.model.business.operation.output.UserDiscountTenderOutput;
import com.tuodao.bp.model.business.operation.output.UserFreeNumberOutput;
import com.tuodao.bp.model.constant.PublicConstant;
import com.tuodao.bp.model.mq.CouponGrantMqInfo;
import com.tuodao.bp.model.mq.WithDrawTimesMqInfo;
import com.tuodao.bp.operation.constant.OperationBizConstant;
import com.tuodao.bp.operation.constant.OperationRespExceptionConstant;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserDiscountMapper;
import com.tuodao.bp.operation.persistence.mapper.basic.OpUserOperationDataMapper;
import com.tuodao.bp.operation.persistence.mapper.biz.BizOpUserDiscountMapper;
import com.tuodao.bp.operation.persistence.mapper.biz.BizOpWelfareActivityMapper;
import com.tuodao.bp.operation.persistence.model.basic.OpUserDiscount;
import com.tuodao.bp.operation.persistence.model.basic.OpUserDiscountExample;
import com.tuodao.bp.operation.persistence.model.basic.OpUserOperationData;
import com.tuodao.bp.operation.persistence.model.biz.BizOpWelfareActivity;
import com.tuodao.bp.operation.service.IUserDiscountService;
import com.tuodao.bp.result.exception.BizFeignException;
import com.tuodao.bp.utils.NumberUtils;
import com.tuodao.bp.utils.TimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * 我的优惠券实现
 * author hechuan
 * <p>
 * created on 2017/9/21
 * <p>
 * since V1.0.0
 */
@Service
public class UserDiscountServiceImpl implements IUserDiscountService {

    @Autowired
    private BizOpUserDiscountMapper bizOpUserDiscountMapper;

    @Autowired
    private OpUserOperationDataMapper opUserOperationDataMapper;

    @Autowired
    private BizOpWelfareActivityMapper bizOpWelfareActivityMapper;

    @Autowired
    private OpUserDiscountMapper opUserDiscountMapper;

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(UserDiscountServiceImpl.class);

    /**
     * @param input
     * @return
     * @see IUserDiscountService#getUserDiscountPagedList(UserDiscountsInput)
     */
    @Override
    public PageInfo<UserDiscountOutput> getUserDiscountPagedList(UserDiscountsInput input) {

        logger.debug("disCountType:[{}],disCountStatus:[{}],disCountLock:[{}]", input.getDiscountType(), input.getDiscountStatus(), input.getDiscountLock());

        // 分页
        PageHelper.startPage(input.getCurrentPage(), input.getPageSize());
        List<UserDiscountOutput> queryList = bizOpUserDiscountMapper.getBizUserDiscountPagedList(input);

        // 分页组装
        PageInfo<UserDiscountOutput> result = new PageInfo<>(queryList);

        return result;
    }

    /**
     * @param input
     * @return
     * @see IUserDiscountService#getUserDiscountList(UserDiscountsInput)
     */
    @Override
    public List<UserDiscountOutput> getUserDiscountList(UserDiscountsInput input) {

        logger.debug("disCountType:[{}],disCountStatus:[{}],disCountLock:[{}]", input.getDiscountType(), input.getDiscountStatus(), input.getDiscountLock());

        List<UserDiscountOutput> queryList = bizOpUserDiscountMapper.getBizUserDiscountPagedList(input);

        return queryList;
    }

    /**
     * 优惠券发放
     *
     * @param couponGrantMqInfo 消息内容体
     * @see IUserDiscountService#couponGrant(CouponGrantMqInfo, boolean)
     */
    @Override
    @Transactional
    public void couponGrant(CouponGrantMqInfo couponGrantMqInfo, boolean grantCouponFlag) {

        final String welfareActivityCode = couponGrantMqInfo.getWelfareActivityCode();
        final String userId = couponGrantMqInfo.getUserId();
        final String mobile = couponGrantMqInfo.getMobile();

        List<BizOpWelfareActivity> bizOpWelfareActivityList = bizOpWelfareActivityMapper.selectOpWelfareActivityListByCode(welfareActivityCode);

        if (CollectionUtils.isEmpty(bizOpWelfareActivityList)) {
            logger.warn("此福利活动不存在或未配置优惠券,couponGrantMqInfo = {}", couponGrantMqInfo);
            return;
        }

        BizOpWelfareActivity temp0 = bizOpWelfareActivityList.get(0);
        Long currentDate = new Date().getTime();
        Date effectDate = temp0.getEffectDate();
        if (null != effectDate && currentDate < effectDate.getTime()) {
            logger.warn("此福利活动还未开始,活动名称={},开始时间 ={}", temp0.getWelfareActivityName(), TimeUtils.formatNomal(effectDate));
            return;
        }

        Date invalidate = temp0.getEffectDate();
        if (null != invalidate && currentDate > invalidate.getTime()) {
            logger.warn("此福利活动已结束,活动名称={},结束时间 ={}", temp0.getWelfareActivityName(), TimeUtils.formatNomal(invalidate));
            return;
        }

        //更新提现次数(有则更新)
        updateUserWithDrawTimes(userId, mobile, bizOpWelfareActivityList);

        if (!grantCouponFlag) {
            return;
        }
        //发送用户福利
        grantUserCoupons(userId, mobile, bizOpWelfareActivityList);
    }

    /**
     * @param idInput 请求信息
     * @return 优惠券信息
     */
    @Override
    public UserDiscountOutput getUserDiscountById(IdInput idInput) {
        logger.info("获取优惠券详情，idInput={}", idInput);
        OpUserDiscount opUserDiscount = opUserDiscountMapper.selectByPrimaryKey(idInput.getId());
        if (null == opUserDiscount || !Objects.equals(idInput.getUserId(), opUserDiscount.getUserId())) {
            throw new BizFeignException(OperationRespExceptionConstant.USER_DISCOUNT_NOT_EXIST);
        }
        UserDiscountOutput output = new UserDiscountOutput();
        BeanUtils.copyProperties(opUserDiscount, output);
        return output;
    }

    @Override
    public List<UserDiscountOutput> getRegisterWelfareDiscounts(BasePojo basePojo) {
        return bizOpUserDiscountMapper.getUserDiscountListByWelfareActivityCode(basePojo.getUserId(), PublicConstant.WELFARE_ACTIVITY_CODE_REGISTER);
    }

    /**
     * 消费免费提现次数
     *
     * @param info
     */
    @Override
    @Transactional
    public void consumerWithDrawTimes(WithDrawTimesMqInfo info) {

        OpUserOperationData bean = new OpUserOperationData();

        // 查询用户运营数据
        OpUserOperationData queryData = opUserOperationDataMapper.selectByPrimaryKey(info.getUserId());
        int times = Math.abs(info.getTimes());
        if(info.getTimes() < 0 ){
            if(queryData.getFreeGetNumber() < times){
                logger.info("用户免费提现次数为：[{}],现在需要使用次数：[{}]，剩余可用次数小于需要使用次数",queryData.getFreeGetNumber(),times);
                throw new RuntimeException("剩余可用次数小于需要使用次数");
            }
            if(queryData.getFreeGetNumberExpire() + info.getTimes() >=0){
                bean.setFreeGetNumber(queryData.getFreeGetNumber() + info.getTimes());
                bean.setFreeGetNumberExpire(queryData.getFreeGetNumberExpire() + info.getTimes());
            }else{
                bean.setFreeGetNumber(queryData.getFreeGetNumber() + info.getTimes());
                bean.setFreeGetNumberExpire(0);
                bean.setFreeGetNumberPerpetual(queryData.getFreeGetNumber() + info.getTimes() + queryData.getFreeGetNumberPerpetual());
            }
        }else {
            bean.setFreeGetNumber(queryData.getFreeGetNumber() + info.getTimes());
            bean.setFreeGetNumberPerpetual(info.getTimes() + queryData.getFreeGetNumberPerpetual());
        }

        // 更新
        opUserOperationDataMapper.updateByPrimaryKeySelective(bean);
    }

    /**
     * 根据用户ID查询 用户优惠券
     *
     * @param input 用户信息
     * @return 用户优惠券列表
     */
    @Override
    public List<UserDiscountQueryOutput> getUserDiscountQueryList(BasePojo input) {
        OpUserDiscountExample example = new OpUserDiscountExample();
        example.createCriteria()
                // userId
                .andUserIdEqualTo(input.getUserId())
                // 可使用状态
                .andDisStatusEqualTo(OperationBizConstant.DISCOUNT_STATUS_USABLE)
                // 正常未锁定状态
                .andDisLockEqualTo(OperationBizConstant.DISCOUNT_LOCK_1)
                // 未删除状态
                .andIsDelEqualTo(PublicConstant.DEL_NO);
        List<OpUserDiscount> queryList = opUserDiscountMapper.selectByExample(example);
        List<UserDiscountQueryOutput> resultList = queryList.stream().map(in ->{
            UserDiscountQueryOutput out = new UserDiscountQueryOutput();
            BeanUtils.copyProperties(in,out);
            return out;
        }).collect(Collectors.toList());

        logger.debug("根据用户ID查询 用户优惠券,userId :[{}],resultList.size :[{}]",input.getUserId(),resultList.size());
        return resultList;
    }

    /**
     * 根据用户ID查询用户免费提现次数
     *
     * @param input 用户信息
     * @return 用户免费提现次数
     */
    @Override
    public UserFreeNumberOutput getUserFreeNumber(BasePojo input) {
        OpUserOperationData queryResult = opUserOperationDataMapper.selectByPrimaryKey(input.getUserId());
        UserFreeNumberOutput out = new UserFreeNumberOutput();
        out.setUserId(input.getUserId());
        out.setFreeNumber(null == queryResult ? 0 : queryResult.getFreeGetNumber());
        logger.info("根据用户ID：[{}],查询用户免费提现次数：[{}]",input.getUserId(),out.getFreeNumber());
        return out;
    }

    /**
     * 用户投资
     *
     * @param input 投资输入参数
     * @return 投资返回参数
     */
    @Override
    public List<UserDiscountTenderOutput> getUserDiscountTenderList(UserDiscountTenderInput input) {
        OpUserDiscountExample example = new OpUserDiscountExample();
        OpUserDiscountExample.Criteria cc = example.createCriteria();
        cc
                // userId
                .andUserIdEqualTo(input.getUserId())
                // 可使用状态
                .andDisStatusEqualTo(OperationBizConstant.DISCOUNT_STATUS_USABLE)
                // 正常未锁定状态
                .andDisLockEqualTo(OperationBizConstant.DISCOUNT_LOCK_1)
                // 未删除状态
                .andIsDelEqualTo(PublicConstant.DEL_NO)
                // 期限大于等于
                .andDateLimitGreaterThanOrEqualTo(input.getScaleTimeLimit())
                // 金额大于等于
                .andMoneyLimitGreaterThanOrEqualTo(input.getScaleMoney());
        switch (input.getNeedType()){
            case 2 : cc.andDiscountTypeEqualTo(OperationBizConstant.DISCOUNT_TYPE_COUPON);
            break;
            case 3 : cc.andDiscountTypeEqualTo(OperationBizConstant.DISCOUNT_TYPE_VOUCHER);
            default:break;
        }
        List<OpUserDiscount> queryList = opUserDiscountMapper.selectByExample(example);

        return queryList.stream().map(q -> {
            UserDiscountTenderOutput out = new UserDiscountTenderOutput();
            BeanUtils.copyProperties(q, out);
            return out;
        }).collect(Collectors.toList());
    }

    /**
     * 给用户优惠券
     *
     * @param userId                   用户编号
     * @param mobile                   手机号码
     * @param bizOpWelfareActivityList 福利内容列表
     */
    private void grantUserCoupons(String userId, String mobile, List<BizOpWelfareActivity> bizOpWelfareActivityList) {
        Date beginDate = new Date();

        bizOpWelfareActivityList.forEach(bizOpWelfareActivity -> {
            if (Objects.equals(bizOpWelfareActivity.getDiscountType(), OperationBizConstant.DISCOUNT_TYPE_WITHDRAW)) {
                //免费提现次数
                return;
            }

            OpUserDiscount opUserDiscount = new OpUserDiscount();
            opUserDiscount.setUserId(userId);
            opUserDiscount.setUserMobile(mobile);
            opUserDiscount.setWelfareActivityCode(bizOpWelfareActivity.getWelfareActivityCode());
            opUserDiscount.setDiscountTitle(bizOpWelfareActivity.getDiscountTitle());
            opUserDiscount.setDiscountType(bizOpWelfareActivity.getDiscountType());
            opUserDiscount.setDiscountAvailable(bizOpWelfareActivity.getDiscountAvailable());
            opUserDiscount.setEffectDate(beginDate);
            opUserDiscount.setEffectDay(bizOpWelfareActivity.getEffectDay());
            opUserDiscount.setSource("来自" + bizOpWelfareActivity.getWelfareActivityName());
            opUserDiscount.setMoneyLimit(bizOpWelfareActivity.getMoneyLimit());
            opUserDiscount.setDateLimit(bizOpWelfareActivity.getDateLimit());
            opUserDiscount.setDiscountStyle(OperationBizConstant.DISCOUNT_LOCK_FROUNT);
            opUserDiscount.setRemark(bizOpWelfareActivity.getRemark());
            opUserDiscount.setGmtCreator(userId);
            // 结束日期
            opUserDiscount.setInvalidDate(TimeUtils.addDay(beginDate, bizOpWelfareActivity.getEffectDay()));
            for (int i = 1; i <= bizOpWelfareActivity.getQuantity(); i++) {
                opUserDiscountMapper.insertSelective(opUserDiscount);
            }
        });

        //更新用户运营数据
        saveOrUpdateUserOperationData(userId, mobile, bizOpWelfareActivityList);

    }


    /**
     * 更新用户提现次数
     *
     * @param userId                   用户编码
     * @param mobile                   手机号码
     * @param bizOpWelfareActivityList 福利活动内容列表
     */
    private void updateUserWithDrawTimes(String userId, String mobile, List<BizOpWelfareActivity> bizOpWelfareActivityList) {

        Stream<BizOpWelfareActivity> bizOpWelfareActivityStream = bizOpWelfareActivityList.stream().filter(
                temp -> Objects.equals(temp.getDiscountType(), OperationBizConstant.DISCOUNT_TYPE_WITHDRAW));
        if (null == bizOpWelfareActivityStream) {
            return;
        }

        BizOpWelfareActivity bizOpWelfareActivity = bizOpWelfareActivityStream.findFirst().orElse(null);

        if (null == bizOpWelfareActivity) {
            logger.info("福利活动奖励列表中无提现次数");
            return;
        }

        int withDrawTimes = NumberUtils.str2Int(bizOpWelfareActivity.getDiscountAvailable()) * bizOpWelfareActivity.getQuantity();

        if (0 == withDrawTimes) {
            logger.info("此福利未配置免费提现次数");
            return;
        }
        OpUserOperationData opUserOperationData = opUserOperationDataMapper.selectByPrimaryKey(userId);
        if (null == opUserOperationData) {
            opUserOperationData = new OpUserOperationData();
            opUserOperationData.setUserId(userId);
            opUserOperationData.setUserMobile(mobile);
            opUserOperationData.setFreeGetNumber(withDrawTimes);
            opUserOperationData.setFreeGetNumberExpire(withDrawTimes);
            opUserOperationDataMapper.insertSelective(opUserOperationData);
        } else {
            opUserOperationData.setFreeGetNumberExpire(withDrawTimes);
            opUserOperationData.setFreeGetNumber(withDrawTimes + opUserOperationData.getFreeGetNumberPerpetual());
            opUserOperationDataMapper.updateByPrimaryKey(opUserOperationData);
        }

    }

    /**
     * 保存或更新用户运营数据
     *
     * @param userId                   用户编码
     * @param mobile                   游湖号码
     * @param bizOpWelfareActivityList 福利内容
     */
    private void saveOrUpdateUserOperationData(String userId, String mobile, List<BizOpWelfareActivity> bizOpWelfareActivityList) {
        logger.info("saveOrUpdateUserOperationData USER ID:[{}],MOBILE:[{}]", userId, mobile);

        // 计算抵用券多少钱
        Long money = bizOpWelfareActivityList.stream()
                .filter(bizOpWelfareActivity -> Objects.equals(bizOpWelfareActivity.getDiscountType(), OperationBizConstant.DISCOUNT_TYPE_VOUCHER))
                .mapToLong(c -> NumberUtils.str2Long(c.getDiscountAvailable()) * c.getQuantity()).sum();

        // 计算加息券数量,抵用券数量
        //抵用券数量
        int voucherCount = bizOpWelfareActivityList.stream()
                .filter(bizOpWelfareActivity -> Objects.equals(bizOpWelfareActivity.getDiscountType(), OperationBizConstant.DISCOUNT_TYPE_VOUCHER))
                .mapToInt(BizOpWelfareActivity::getQuantity).sum();
        //加息券数量
        int couponCount = bizOpWelfareActivityList.stream()
                .filter(bizOpWelfareActivity -> Objects.equals(bizOpWelfareActivity.getDiscountType(), OperationBizConstant.DISCOUNT_TYPE_COUPON))
                .mapToInt(BizOpWelfareActivity::getQuantity).sum();

        OpUserOperationData data = opUserOperationDataMapper.selectByPrimaryKey(userId);
        if (null == data) {
            //insert
            logger.info("该用户，USER ID:[{}],MOBILE:[{}]没有运营数据相关统计，进行INSERT", userId, mobile);
            data = new OpUserOperationData();
            data.setUserId(userId);
            data.setUserMobile(mobile);
            data.setVoucherAmount(BigDecimal.valueOf(money));
            data.setUsablePateCoupon(voucherCount);
            data.setUsableVoucher(couponCount);
            opUserOperationDataMapper.insertSelective(data);
        } else {
            //update
            data.setVoucherAmount(data.getVoucherAmount().add(BigDecimal.valueOf(money)));
            data.setUsablePateCoupon(data.getUsablePateCoupon() + voucherCount);
            data.setUsableVoucher(data.getUsableVoucher() + couponCount);
            logger.info("该用户，USER ID:[{}],MOBILE:[{}]有运营数据相关统计，进行UPDATE", userId, mobile);
            opUserOperationDataMapper.updateByPrimaryKey(data);
        }

    }


}

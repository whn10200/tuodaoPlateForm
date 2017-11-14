package com.tuodao.bp.product.service.impl;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.product.db.mapper.basic.BorrowDefineTypeMapper;
import com.tuodao.bp.product.db.model.basic.BorrowDefineType;
import com.tuodao.bp.product.db.model.basic.BorrowDefineTypeExample;
import com.tuodao.bp.product.service.IBorrowDefineTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/9/15 0015 14:31
 * @Introduction
 */


@Transactional
@Service
public class BorrowDefineTypeServiceImpl implements IBorrowDefineTypeService {

    private static Logger logger = LoggerFactory.getLogger(BorrowDefineTypeServiceImpl.class);

    @Autowired
    private BorrowDefineTypeMapper borrowDefineTypeMapper;

    /**
     * 获取标的类型 作为缓存存放
     * @return
     */
    @Override
    @Cacheable(cacheNames = RedisConstans.CACHE_BORROW_DEFINE_TYPE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_BORROW_DEFINE_TYPE+'_ENABLE'")
    public List<BorrowDefineType> getEnableBorrowType() {

        return getList();
    }


    /**
     * 获取标的类型 作为缓存存放
     * @return
     */
    @Override
    @CachePut(cacheNames = RedisConstans.CACHE_BORROW_DEFINE_TYPE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_BORROW_DEFINE_TYPE+'_ENABLE'")
    public List<BorrowDefineType> initEnableBorrowType() {
        return getList();
    }

    @Override
    @CacheEvict(cacheNames = RedisConstans.CACHE_BORROW_DEFINE_TYPE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_BORROW_DEFINE_TYPE+'_ENABLE'")
    public void deleteEnableBorrowType(){

    };


    private List<BorrowDefineType> getList()
    {
        BorrowDefineTypeExample example = new BorrowDefineTypeExample();
        example.createCriteria().andEnableEqualTo(1);

        List<BorrowDefineType> borrowDefineTypeList = borrowDefineTypeMapper.selectByExample(example);

        logger.debug("获取类型正常。。。");

        return borrowDefineTypeList;
    }


}

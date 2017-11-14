package com.tuodao.bp.product.service;

import com.tuodao.bp.cache.constant.RedisConstans;
import com.tuodao.bp.product.db.model.basic.BorrowDefineType;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/9/15 0015 14:31
 * @Introduction
 */



public interface IBorrowDefineTypeService {

     public List<BorrowDefineType> getEnableBorrowType();

    @CachePut(cacheNames = RedisConstans.CACHE_BORROW_DEFINE_TYPE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_BORROW_DEFINE_TYPE+'_ENABLE'")
    List<BorrowDefineType> initEnableBorrowType();

    @CacheEvict(cacheNames = RedisConstans.CACHE_BORROW_DEFINE_TYPE, key = "T(com.tuodao.bp.cache.constant.RedisConstans).CACHE_BORROW_DEFINE_TYPE+'_ENABLE'")
    void deleteEnableBorrowType();
}

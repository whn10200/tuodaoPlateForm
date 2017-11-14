package com.tuodao.bp.product.cache;

import com.tuodao.bp.product.db.mapper.basic.BorrowDefineTypeMapper;
import com.tuodao.bp.product.db.model.basic.BorrowDefineType;
import com.tuodao.bp.product.db.model.basic.BorrowDefineTypeExample;
import com.tuodao.bp.product.service.IBorrowDefineTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author wuchengjie
 * @Date 2017/9/15 0015 12:22
 * @Introduction 产品中心用到的缓存初始化
 */

@Component
public class ProductCacheInit implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(ProductCacheInit.class);

    @Autowired
    private IBorrowDefineTypeService borrowDefineTypeService;

    @Override
    public void run(String... args) throws Exception {
        logger.info("ProductCacheInit产品缓存初始化.....");

        BorrowDefineTypeExample example = new BorrowDefineTypeExample();
        example.createCriteria().andEnableEqualTo(1);

        borrowDefineTypeService.deleteEnableBorrowType();
        borrowDefineTypeService.initEnableBorrowType();

        logger.info("ProductCacheInit产品缓存完成.....");
    }
}

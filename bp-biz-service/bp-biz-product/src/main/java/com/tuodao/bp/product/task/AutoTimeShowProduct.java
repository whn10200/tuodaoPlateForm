package com.tuodao.bp.product.task;

import com.tuodao.bp.model.business.product.input.ProductInput;
import com.tuodao.bp.product.constants.ProductConstant;
import com.tuodao.bp.product.db.model.basic.Product;
import com.tuodao.bp.product.db.model.basic.ProductExample;
import com.tuodao.bp.product.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
  * com.tuodao.bp.product.task.AutoTimeShowProduct
  * @类描述: 产品自动上架任务,自动上架条件: 产品状态为4.待发布 到可投时间 自动发布为1
  * @作者 lujing
  * @时间 2017/9/13 0013
  * ======================修改==================
  * @修改者
  * @修改内容: TODO
  * @修改时间 yyyy-MM-dd HH:mm:ss
  * ============================================
  **/
@Component
public class AutoTimeShowProduct {
    private static final Logger logger = LoggerFactory.getLogger(AutoTimeShowProduct.class);
    // 每分钟执行一次
    @Scheduled(cron="0 */1 * * * ?")
    public void showProductTask() {
        logger.info("beggin AutoTimeShowProduct task ");
        ProductExample bean= new ProductExample();
        ProductExample.Criteria cnd = bean.createCriteria();

        cnd.andProductStatusEqualTo(ProductConstant.PRODUCT_STATUS_4);
        cnd.andAvailableInvestTimeLessThanOrEqualTo(new Date());//<=当前时间
        cnd.andIsAutoPublishEqualTo(1);//自动发布
        List<Product> list= productService.getProductList(bean);
        if(null== list || list.isEmpty()){
            return;
        }
        //直接遍历修改,优化则写新的mapper批量更新
        list.forEach((Product p) ->{
            ProductInput pIn= new ProductInput();
            pIn.setId(p.getId());
            pIn.setProductStatus(ProductConstant.PRODUCT_STATUS_5);//5募集中
            pIn.setPublishTime(new Date());//更新发布时间
            pIn.setIsYetIssue(1);//已发布
            productService.updateProduct(pIn);
        });

        logger.info("AutoTimeShowProduct task end");
    }

    @Autowired
    private IProductService productService;
}

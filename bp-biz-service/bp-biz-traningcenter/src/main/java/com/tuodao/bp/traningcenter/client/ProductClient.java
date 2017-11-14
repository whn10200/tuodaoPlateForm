package com.tuodao.bp.traningcenter.client;

import com.tuodao.bp.model.business.product.output.ProductOutput;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(value = "biz-product")
public interface ProductClient {

    /**
     * 根据产品的id 获取产品的信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/product/getProduct", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    ProductOutput getProduct(Integer id);
}

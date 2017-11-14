package test.controller;

import com.tuodao.bp.model.business.product.input.ProductInput;
import org.junit.Test;
import org.springframework.http.MediaType;
import test.BizTestApi;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/4
 * @time: 16:29
 * @copyright: 拓道金服 Copyright (c) 2017
 */
public class ProductControllerTest extends BizTestApi {
    @Test
    public void openDeposit() throws Exception {
        url = "product/getProductPageList";
        ProductInput input = new ProductInput();
        //input.setUserId("8a7b76396c444cf9b181a2e9e296d86b");

        doPost(url, MediaType.APPLICATION_JSON_UTF8, input);
    }



}
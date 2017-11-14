package com.tuodao.bp.api.facade;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.tuodao.bp.api.core.response.RespResult;
import com.tuodao.bp.model.business.useraccount.input.LoginInput;
import com.tuodao.bp.model.business.useraccount.output.UserLoginOutput;
import com.tuodao.bp.utils.MD5Utils;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @description: 业务基础测试信息
 * @author: mif
 * @date: 2017/9/4
 * @time: 14:56
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = ApiFacadeApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class FacadeTestApi {

    private static Logger logger = LoggerFactory.getLogger(FacadeTestApi.class);

    private MockMvc mvc;

    protected String url;

    public String ACCESS_ID = "accessId";
    public String ACCESS_KEY = "accessKey";
    public String SIGN = "NO";
    public String ROUTER = "/api/router";
    public String REQUEST_TYPE = "PC";

    @Autowired
    protected WebApplicationContext wac;

    @Before
    public void setUp() throws Exception {
        //  初始化MockMvc对象
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        // 初始化Mock
        MockitoAnnotations.initMocks(this);
//        before();
    }


    /**
     * 发送请求
     *
     * @param format  格式
     * @param content 内容
     * @throws Exception
     */
    public void doPost(MediaType format, Object content) throws Exception {
        String contentStr = JSON.toJSONString(content);
        logger.info("request contentStr ={}", contentStr);
        RequestBuilder request = post("/" + url).content(contentStr)
                .accept(null == format ? MediaType.APPLICATION_JSON_UTF8 : format)
                .header("accessId", ACCESS_ID)
                .header("accessKey", ACCESS_KEY)
                .header("sign", SIGN)
                .header("x-forwarded-prefix", ROUTER);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print());
    }

    /**
     * 发送请求
     *
     * @param format  格式
     * @param content 内容
     * @throws Exception
     */
    public String doPost2(MediaType format, Object content) throws Exception {
        String contentStr = JSON.toJSONString(content);
        logger.info("request contentStr ={}", contentStr);
        RequestBuilder request = post("/" + url).content(contentStr)
                .accept(null == format ? MediaType.APPLICATION_JSON_UTF8 : format)
                .header("accessId", ACCESS_ID)
                .header("accessKey", ACCESS_KEY)
//                .header("requestType","APP")
                .header("requestType", REQUEST_TYPE)
                .header("sign", SIGN)
                .header("x-forwarded-prefix", ROUTER);

        String resp =  mvc.perform(request)
                .andExpect(status().isOk())
                .andDo(print()).andReturn().getResponse().getContentAsString();
        return resp;
    }

    public void before()throws Exception{
        url = "router/user/login";
        LoginInput input = new LoginInput();
        input.setMobile("13012345678");
        input.setLoginPassword(MD5Utils.md5("123456"));
        input.setLoginSource(1);
        String resp = doPost2(MediaType.APPLICATION_JSON_UTF8, input);
        RespResult<UserLoginOutput> respResult = JSON.parseObject(resp, new TypeReference<RespResult<UserLoginOutput>>(){});
        UserLoginOutput output = respResult.getContent();
        this.ACCESS_ID = output.getAccessId();
        this.ACCESS_KEY = output.getAccessKey();
    }
}

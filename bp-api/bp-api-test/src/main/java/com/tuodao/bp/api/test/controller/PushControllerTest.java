package com.tuodao.bp.api.test.controller;


import com.tuodao.bp.model.business.common.input.PushInput;
import com.tuodao.bp.unit.test.BaseAPI;
import com.tuodao.bp.unit.test.enums.ContentType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * * 请求：{"request":{"common":{"action":"demoDetail","reqtime":"20170328104456"},"content":{"accessId":"accessId","requestType":"1","userNo":"hc","ip":null,"pageSize":20,"currentPage":1,"phone":"13666666667"}}}
 *  响应：{"response":{"info":{"code":100011,"msg":""},"content":null}}
 *  消息推送测试类
 */
public class PushControllerTest extends BaseAPI {
	
	@Before
    public void setUp(){
        this.url = "http://localhost:80/api/router/";
        this.accessId = "0ac6ac7b73e09ed28412ee389f9ed05f";
        this.accessKey = "accessKey";
    }

    /**
     * 用户详细查询
     */
    @Test
    public void pushMessage(){
    	try{
			PushInput input = new PushInput();
			input.setPushContent("推送content");
			input.setPushObject(3);
			input.setPushAlias("test");

			Map<String,String> map = new HashMap<>();
			map.put("name","张三");
			map.put("sex","男");
    		doService("/push/pushMessage", ContentType.JSON, input);
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }

    
    @After
    public void complete() {
    	responseJSON();
    }
}

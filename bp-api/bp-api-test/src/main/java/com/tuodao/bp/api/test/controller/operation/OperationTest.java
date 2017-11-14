package com.tuodao.bp.api.test.controller.operation;

import com.tuodao.bp.unit.test.BaseAPI;
import org.junit.After;
import org.junit.Before;

/**
 * author hechuan
 * <p>
 * created on 2017/10/9
 * <p>
 * since V1.0.0
 */
public class OperationTest extends BaseAPI {

    public static final String USER_ID = "15988888925-caqdfgojsrcgna8oi8b8";
    public static final String MOBILE = "15988888925";
    @Before
    public void setUp(){
        this.url = "http://localhost/api/router";
        this.accessId = "0ac6ac7b73e09ed28412ee389f9ed05f";
        this.accessKey = "accessKey0ac6ac7b73e09ed28412ee389f9ed05f";
    }

    @After
    public void complete() {
        responseJSON();
    }
}

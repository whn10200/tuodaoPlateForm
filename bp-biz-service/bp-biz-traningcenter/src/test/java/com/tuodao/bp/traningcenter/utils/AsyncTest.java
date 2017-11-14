package com.tuodao.bp.traningcenter.utils;

import com.tuodao.bp.model.business.common.input.EmailInput;
import com.tuodao.bp.traningcenter.TraningCenterApplication;
import com.tuodao.bp.traningcenter.client.CommonClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author qingli.chen
 * @date 2017/11/8
 * @description
 */
@SpringBootTest(classes = TraningCenterApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class AsyncTest {

    @Autowired
    CommonClient commonClient;

    @Test
    public void sendEmail() {
        EmailInput emailInput = new EmailInput();
        emailInput.setSubject("test");
        emailInput.setContent("test1");
        emailInput.setReceivers("chenqingli@51tuodao.com");
        commonClient.sendEmail(emailInput);
        System.out.println(1);
    }
}

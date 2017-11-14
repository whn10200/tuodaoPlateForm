package com.tuodao.bp.useraccount.task.daily;

import com.google.common.collect.Maps;
import com.tuodao.bp.useraccount.UserAccountApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @description: ${Description}
 * @author: mif
 * @date: 2017/9/25
 * @time: 14:01
 * @copyright: 拓道金服 Copyright (c) 2017
 */
@SpringBootTest(classes = UserAccountApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class UserNewbieTaskTest {

    @Resource
    private UserNewbieTask userNewbieTask;

    @Test
    public void userNewbieTask() throws Exception {
        userNewbieTask.execute(Maps.newHashMap());
    }

}
package com.klsw.klswWebService.test;

import com.klsw.apiCommon.api.model.Ret;
import com.klsw.apiCommon.api.model.TbCwk;
import com.klsw.klswWebService.controller.LiveRoomController;
import com.klsw.klswWebService.service.TbCwkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * Created with IntelliJ IDEA.
 * User: liukun
 * Date: 2017/6/16
 * Time: 16:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WKTest {

    @Autowired
    private TbCwkService tbCwkService;

    @Test
    public void test1() {
        TbCwk tbCwk= tbCwkService.findByName("li");
        System.out.println(tbCwk.getId());
    }
}

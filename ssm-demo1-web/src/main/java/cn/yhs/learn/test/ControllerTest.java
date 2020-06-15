package cn.yhs.learn.test;

import cn.yhs.learn.controller.UserController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.test.ControllerTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/13 17:20
 * @Description: todo
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ControllerTest {

    @Autowired
    private UserController userController;

    @Test
    public void test1() {
        ModelAndView modelAndView = userController.findAll(1, 2);
        System.out.println(modelAndView);
    }
}

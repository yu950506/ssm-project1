package cn.yhs.learn.test;

import cn.yhs.learn.dao.SysLogDao;
import cn.yhs.learn.dao.UserDao;
import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.test.DaoTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 17:03
 * @Description: todo
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DaoTest {
    @Autowired
    private SysLogDao sysLogDao;

    @Autowired
    private UserDao userDao;

    @Test
    public void testUser() {
        UserInfo user = userDao.findByUsername("yhs");
        System.out.println("user = " + user);

    }
    @Test
    public void testSysLog() {
        List<SysLog> logList = sysLogDao.findAll();
        for (SysLog sysLog : logList) {
            System.out.println(sysLog);
        }

    }
}

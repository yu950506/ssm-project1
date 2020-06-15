package cn.yhs.learn.test;

import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.test.ServiceTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 17:07
 * @Description: todo
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ServiceTest {
    @Autowired
    private SysLogService sysLogService;

    @Test
    public void testSyslog() {
        PageInfo<SysLog> sysLogPageInfo = sysLogService.findAll(1, 2);
        System.out.println(sysLogPageInfo);

    }
}

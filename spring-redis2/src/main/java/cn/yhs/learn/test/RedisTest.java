package cn.yhs.learn.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.Set;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.test.RedisTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/22 22:02
 * @Description: todo
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class RedisTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private JedisPool jedisPool;
    @Test
    public void testRedis() {
        Jedis jedis = jedisPool.getResource();
        Set<String> keys = jedis.keys("*");
        System.out.println("keys = " + keys);


        jedis.close();


    }
}

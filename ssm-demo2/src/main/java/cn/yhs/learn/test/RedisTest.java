package cn.yhs.learn.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.test.RedisTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/22 13:17
 * @Description: todo
 **/

public class RedisTest {


    @Test
    public void test1() {
        Jedis jedis = new Jedis("192.168.1.101", 6379);
        System.out.println(jedis);
        String username = jedis.get("username");
        System.out.println("username = " + username);

        jedis.close();

    }
}

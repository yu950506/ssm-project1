package cn.yhs.learn.redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.redis.RedisTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/22 20:11
 * @Description: todo
 **/
public class RedisTest {
    RedisPoolUtils redisPool = new RedisPoolUtils();

    @Test
    public void testPool() {

        Jedis client = RedisPoolUtils.getClient();
        Set<String> keys = client.keys("*");
        System.out.println("keys = " + keys);


    }

}

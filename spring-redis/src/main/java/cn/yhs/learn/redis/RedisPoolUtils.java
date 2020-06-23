package cn.yhs.learn.redis;

import cn.yhs.learn.utils.PropertiesUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.redis.RedisPool
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/22 20:26
 * @Description: todo
 **/
public class RedisPoolUtils {
    private static JedisPoolConfig config;
    private static JedisPool jedisPool;
    private static Jedis jedis;

    static {
        config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(PropertiesUtils.get("redis.maxTotal")));
        config.setMaxIdle(Integer.parseInt(PropertiesUtils.get("redis.maxIdle")));
        config.setMaxWaitMillis(Integer.parseInt(PropertiesUtils.get("redis.maxWaitMillis")));
        jedisPool = new JedisPool(config, PropertiesUtils.get("redis.hostName"), Integer.parseInt(PropertiesUtils.get("redis.port")));
    }

    /**
     * 创建一个Redis客户端对象
     *
     * @return
     */
    public static Jedis getClient() {
        jedis = jedisPool.getResource();
        return jedis;
    }

    /**
     * 释放资源
     */
    public static void release() {
        if (jedis != null) {
            jedis.close();
        }
    }


}

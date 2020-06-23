package cn.yhs.learn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.RedisService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/23 10:24
 * @Description: todo
 **/
@Service
public class RedisService {

    @Autowired
    private JedisPool jedisPool;

    public Jedis getJedis() {
        return jedisPool.getResource();
    }



}

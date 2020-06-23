package cn.yhs.learn.controller;

import cn.yhs.learn.domian.UserInfo;
import cn.yhs.learn.service.Userservice;
import cn.yhs.learn.service.impl.RedisService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.UserController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/23 10:31
 * @Description: todo
 **/
@RequestMapping("/user")
@Controller
@Slf4j
public class UserController {

    @Autowired
    private Userservice userservice;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll() {
        Jedis jedis = redisService.getJedis();
        // 1. 从redis中获取数据
        String userList = jedis.get("userList");
        // 2. 判断redis中是否有数据
        try {
            if (null != userList && userList.length() > 0) {
                // 3. redis中有数据就从redis中返回
                log.info("redis中有数据{}，从redis获取", userList);
                return userList;
            } else {
                // 4. redis中没有数据，从数据库种查询，将查询的结果存到redis中再进行返回
                log.info("redis中没有数据，从数据库中进行查询");
                List<UserInfo> userInfoList = userservice.findAll();
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    String userDbList = objectMapper.writeValueAsString(userInfoList);
                    jedis.set("userList", userDbList);
                    return userDbList;
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        } finally {
            jedis.close();
        }

    }

    @RequestMapping("/findAll2")
    @ResponseBody
    public Object findAll2() {
        return userservice.findAll();
    }
}

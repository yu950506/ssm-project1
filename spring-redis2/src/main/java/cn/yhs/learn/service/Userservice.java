package cn.yhs.learn.service;

import cn.yhs.learn.domian.UserInfo;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.Userservice
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/23 10:29
 * @Description: todo
 **/
public interface Userservice {
    List<UserInfo> findAll();
}

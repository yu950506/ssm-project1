package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.UserDao;
import cn.yhs.learn.domian.UserInfo;
import cn.yhs.learn.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.UserServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/23 10:30
 * @Description: todo
 **/
@Service("userService")
public class UserServiceImpl implements Userservice {

    @Autowired
    private UserDao userDao;

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }


}

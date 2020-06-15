package cn.yhs.learn.service;

import cn.yhs.learn.domain.UserInfo;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.UserService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 19:30
 * @Description: todo
 **/
// 必须继承UserDetailsService，才能使用SpringSecurity提供的登录验证的方法
public interface UserService extends UserDetailsService {

    PageInfo<UserInfo> findAll(Integer pageNum, Integer pageSize);


    void save(UserInfo userInfo);
}

package cn.yhs.learn.service;

import cn.yhs.learn.domain.UserInfo;
import cn.yhs.learn.domain.UserRole;
import com.github.pagehelper.PageInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.UserService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/11 13:00
 * @Description: todo
 **/
public interface UserService extends UserDetailsService {
    PageInfo<UserInfo> findAll(int pageNum, int pageSize);

    void save(UserInfo userInfo);

    UserInfo findById(Integer id);

    void updateUser(UserInfo userInfo);

    UserInfo findAllInfoById(Integer id);

    UserInfo findNoRoleById(Integer roleId);

    void addRoleBatch(List<UserRole> userRoleList);

    void deleteById(Integer id);
}

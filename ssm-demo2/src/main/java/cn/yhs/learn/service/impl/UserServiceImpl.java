package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.UserDao;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.domain.UserInfo;
import cn.yhs.learn.service.UserService;
import cn.yhs.learn.util.PasswordEncoderUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.UserServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 19:31
 * @Description: todo
 **/
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


    @Override
    public PageInfo<UserInfo> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userInfoList = userDao.findAll();
        PageInfo<UserInfo> userInfoPageInfo = PageInfo.of(userInfoList);
        return userInfoPageInfo;
    }

    @Override
    public void save(UserInfo userInfo) {
        // 保存到数据库需要对用户的密码进行加密
        userInfo.setPassword(PasswordEncoderUtils.encoder(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    /**
     * SpringSecurity提供的根据用户进行用户的登录判断
     * UserDetailsService 提供的方法loadUserByUsername
     *
     * @param username
     * @return SpringSecurity 提供的User对象
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. 从数据库中查询用户名和密码
        UserInfo userInfo = userDao.findByUsername(username);
        // 2. SpringSecurity提供的User对象进行判断，用户名，密码，角色是否都是正确
        User user = new User(userInfo.getUsername(),
                userInfo.getPassword(),
                getGrantedAuthority(userInfo.getRoles()));
        return user;
    }

    /**
     * @param roleList 角色列表
     * @return GrantedAuthority的具体实现类对象的集合
     */
    List<SimpleGrantedAuthority> getGrantedAuthority(List<Role> roleList) {
        List<SimpleGrantedAuthority> simpleGrantedAuthorityList = new ArrayList<>();
        if (roleList != null)
            for (Role role : roleList) {
                /**
                 *  todo 1.一定要记得再角色前面加ROLE_
                 * Access is denied (user is not anonymous); delegating to AccessDeniedHandler
                 * new SimpleGrantedAuthority("ROLE_"+role.getRoleName())
                 */
                simpleGrantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
            }
        return simpleGrantedAuthorityList;
    }


}

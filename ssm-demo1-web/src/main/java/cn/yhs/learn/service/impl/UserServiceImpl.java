package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.UserDao;
import cn.yhs.learn.dao.UserRoleDao;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.domain.UserInfo;
import cn.yhs.learn.domain.UserRole;
import cn.yhs.learn.service.UserService;
import cn.yhs.learn.util.PasswordUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
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
 * @Time: 2020/6/11 13:01
 * @Description: todo
 **/
@Service("userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 用户登录和权限操作的方法
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("前端传递的username：{}", username);
        UserInfo userInfo = userDao.findByUsername(username);
        log.info("数据库查询的用户信息：{}", userInfo);
        /**
         * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
         */
        // User user = new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), this.getAuthorities(userInfo));
        // 用了加密器之后的写法，用户在登录的时候自动会加密
        User user = new User(userInfo.getUsername(), userInfo.getPassword(), this.getAuthorities(userInfo));
        log.info("权限认证返回的用户信息：{}", user);
        return user;
    }

    /**
     * 查询角色信息的方法
     *
     * @param user
     * @return
     */
    public List<SimpleGrantedAuthority> getAuthorities(UserInfo user) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = user.getRoles();
        for (Role role : roles) {
            String roleName = role.getRoleName();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + roleName));
        }
        return authorities;
    }

    /**
     * 查询所用用户信息，并分页
     *
     * @return
     */
    @Override
    public PageInfo<UserInfo> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserInfo> userList = userDao.findAll();
        return PageInfo.of(userList);
    }

    @Override
    public void save(UserInfo userInfo) {
        //todo 对密码进行加密处理
        userInfo.setPassword(PasswordUtils.encoding(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(Integer id) {
        return userDao.findById(id);
    }

    @Override
    public UserInfo findAllInfoById(Integer id) {
        return userDao.findAllInfoById(id);
    }

    @Override
    public void updateUser(UserInfo userInfo) {
        userInfo.setPassword(PasswordUtils.encoding(userInfo.getPassword()));
        userDao.update(userInfo);
    }

    @Override
    public UserInfo findNoRoleById(Integer roleId) {
        return userDao.findNoRoleById(roleId);
    }

    @Override
    public void addRoleBatch(List<UserRole> userRoleList) {
        for (UserRole userRole : userRoleList) {
            userRoleDao.save(userRole);
        }
    }

    @Override
    public void deleteById(Integer id) {
        userRoleDao.deleteByUserId(id);
        userDao.deleteById(id);
    }
}

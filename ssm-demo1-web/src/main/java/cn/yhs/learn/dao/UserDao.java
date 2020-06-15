package cn.yhs.learn.dao;

import cn.yhs.learn.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.UserDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/11 11:37
 * @Description: todo
 **/

@Repository("userDao")
public interface UserDao {

    /**
     * 根据用户名查询用户的相关信息
     *
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    @Results(id = "userMap", value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNum", column = "phone_num"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", many = @Many(select = "cn.yhs.learn.dao.RoleDao.findAllRoleByUserId"))

    })
    UserInfo findByUsername(String username);

    /**
     * 查询所有用户信息
     *
     * @return
     */
    @Select("select * from user")
    @Results(id = "userInfoMap", value = {
            //todo 可以写列名不一样的，那些列名一样的就不用写
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "phone_num", property = "phoneNum")
    })
    List<UserInfo> findAll();

    /**
     * 新增用户的方法
     *
     * @param userInfo
     */
    @Insert("insert into user(username,password,email,phone_num,status)values(#{username},#{password},#{email},#{phoneNum},${status})")
    void save(UserInfo userInfo);

    /**
     * 根据id查询用户的基本信息
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    @ResultMap("userInfoMap")
    UserInfo findById(Integer id);

    /**
     * 更新用户的方法，单个表的更新
     *
     * @param userInfo
     */
    @Update("update user set username = #{username},password = #{password},email = #{email},phone_num = #{phoneNum},status = #{status} where id = #{id}")
    void update(UserInfo userInfo);

    /**
     * 根据用户id查询用户的详细信息（包含角色以及角色在内的权限信息）
     *
     * @param id
     * @return
     */
    @Select("select * from user where id = #{id}")
    @Results(id = "userAllInfoMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "phone_num", property = "phoneNum"),
            @Result(column = "id", property = "roles", many = @Many(select = "cn.yhs.learn.dao.RoleDao.findAllRoleByUserId")),

    })
    UserInfo findAllInfoById(Integer id);

    /**
     * 根据用户id 查询该用户信息以及该用户没有的角色
     *
     * @param roleId
     * @return
     */
    @Select("select * from user where id = #{roleId}")
    @Results(id = "userInfoNoRoleMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "phone_num", property = "phoneNum"),
            @Result(column = "id", property = "roles", many = @Many(select = "cn.yhs.learn.dao.RoleDao.findNoRoleByUserId")),

    })
    UserInfo findNoRoleById(Integer roleId);

    @Delete("delete from user where id = #{id}")
    void deleteById(Integer id);
}

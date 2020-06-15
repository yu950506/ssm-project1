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
 * @Time: 2020/6/14 19:24
 * @Description: todo
 **/
@Repository
public interface UserDao {
    /**
     * 根据用户名称来查找用户
     * todo BUG 这里可能用户名不唯一，应该返回一个集合，在service层中对集合进行唯一的一条数据判断
     *
     * @param username
     * @return
     */
    @Select("select * from user where username = #{username}")
    @Results(id = "userAllInfo", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "phone_num", property = "phoneNum"),
            @Result(column = "id",property = "roles",many = @Many(select = "cn.yhs.learn.dao.RoleDao.findAllRoleByUserId"))
    })
    UserInfo findByUsername(String username);

    @Select("select * from user")
    @ResultMap("userAllInfo")
    List<UserInfo> findAll();


    @Insert("insert into user(username,password,email,phone_num,status)values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);
}

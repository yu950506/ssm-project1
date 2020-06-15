package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Role;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.RoleDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 20:26
 * @Description: todo
 **/
public interface RoleDao {
    /**
     * 根据用户id查询该用户所有的角色，需要借助中间表
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select role_id from user_role where user_id = #{userId})")
    @Results(id = "roleAllInfoMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc")
    })
    List<Role> findAllRoleByUserId(Integer userId);

}

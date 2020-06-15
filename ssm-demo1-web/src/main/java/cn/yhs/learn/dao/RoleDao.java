package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.RoleDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/11 11:38
 * @Description:
 **/
@Repository("roleDao")
public interface RoleDao {
    /**
     * 根据用户的id查询出用户的所有角色(包含该角色的权限信息)
     * 也可以学左外连接的写法
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select role_id from user_role where user_id = #{userId})")
    @Results(id = "roleMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", many = @Many(select = "cn.yhs.learn.dao.PermissionDao.findAllPermissionByRoleId")),
    })
    List<Role> findAllRoleByUserId(Integer userId);

    /**
     * 根据用户id查询该用户没有的角色
     *
     * @param userId
     * @return
     */
    @Select("select * from role where id not in (select role_id from user_role where user_id = #{userId})")
    @Results(id = "noRoleMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", many = @Many(select = "cn.yhs.learn.dao.PermissionDao.findAllPermissionByRoleId")),
    })
    List<Role> findNoRoleByUserId(Integer userId);

    /**
     * 根据角色id 查询该角色的所有信息包含该角色的所有权限
     *
     * @return
     */
    @Select("select * from role where id = #{roleId}")
    @Results(id = "rolePermissionMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", many = @Many(select = "cn.yhs.learn.dao.PermissionDao.findAllPermissionByRoleId")),
    })
    Role findAllPermissionByRoleId(Integer roleId);

    /**
     * 查询所有角色
     *
     * @return
     */
    @Select("select * from role")
    @Results(id = "roleInfoMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc"),
    })
    List<Role> findAllRole();


    /**
     * @param role
     */
    @Insert("insert into role(role_name,role_desc)values(#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * 根据角色id查询该用户没有的权限
     *
     * @param roleId
     * @return
     */
    @Select("select * from role where id = #{roleId}")
    @Results(id = "roleNoPermissionMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName"),
            @Result(column = "role_desc", property = "roleDesc"),
            @Result(column = "id", property = "permissions", many = @Many(select = "cn.yhs.learn.dao.PermissionDao.findNoPermissionByRoleId")),
    })
    Role findNoPermissionById(Integer roleId);

    @Delete("delete from role where id = #{roleId}")
    void deleteById(Integer roleId);
}

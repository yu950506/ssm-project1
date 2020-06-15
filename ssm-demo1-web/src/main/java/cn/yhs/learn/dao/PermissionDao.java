package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.PermissionDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/11 22:26
 * @Description: todo
 **/
@Repository("permissionDao")
public interface PermissionDao {
    /**
     * 根据权限id 查询该权限的所有信息
     *
     * @param id
     * @return
     */
    @Select("select * from permission where id = #{id}")
    @Results(id = "permissionMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "permission_name", property = "permissionName"),
            @Result(column = "url", property = "url")
    })
    Permission findById(Integer id);

    /**
     * 根据角色id 查询该角色所对应的所有权限
     *
     * @param roleId
     * @return
     */
    @Select("select * from permission where id in(select permission_id from role_permission where role_id = #{roleId})")
    @ResultMap("permissionMap")
    List<Permission> findAllPermissionByRoleId(Integer roleId);

    @Select("select * from permission")
    @ResultMap("permissionMap")
    List<Permission> findAll();

    /**
     * 根据角色id 查询该角色没有的权限
     *
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permission_id from role_permission where role_id = #{roleId})")
    @ResultMap("permissionMap")
    List<Permission> findNoPermissionByRoleId(Integer roleId);


    /**
     * @param permission
     */
    @Insert("insert into permission(permission_name,url)values(#{permissionName},#{url})")
    void save(Permission permission);

    @Delete("delete from permission where id = #{permissionId}")
    void deleteById(Integer permissionId);
}

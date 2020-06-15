package cn.yhs.learn.dao;

import cn.yhs.learn.domain.RolePermission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.RolePermissionDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 16:19
 * @Description: todo
 **/
@Repository("rolePermissionDao")
public interface RolePermissionDao {

    @Insert("insert into role_permission(role_id,permission_id) values(#{roleId},#{permissionId})")
    void save(RolePermission rolePermission);

    /**
     * 根据权限id删除
     *
     * @param permissionId
     */
    @Delete("delete from role_permission where permission_id = #{permissionId}")
    void deleteByPermissionId(Integer permissionId);

    @Delete("delete from role_permission where role_id = #{roleId}")
    void deleteByRoleId(Integer roleId);


}

package cn.yhs.learn.service;

import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.domain.RolePermission;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.RoleService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 8:44
 * @Description: todo
 **/
public interface RoleService {
    PageInfo<Role> findAll(int pageNum, int pageSize);

    void save(Role role);

    Role findById(Integer roleId);

    Role findNoPermissionById(Integer roleId);

    void addPermissionBatch(List<RolePermission> rolePermissionList);

    void deleteById(Integer id);
}

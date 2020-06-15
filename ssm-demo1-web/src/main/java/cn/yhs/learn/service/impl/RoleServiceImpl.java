package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.RoleDao;
import cn.yhs.learn.dao.RolePermissionDao;
import cn.yhs.learn.dao.UserRoleDao;
import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.domain.RolePermission;
import cn.yhs.learn.domain.UserRole;
import cn.yhs.learn.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.RoleServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 8:44
 * @Description: todo
 **/
@Service("roleService")
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Override
    public PageInfo<Role> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> roleList = roleDao.findAllRole();
        PageInfo<Role> rolePageInfo = PageInfo.of(roleList);
        return rolePageInfo;
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public Role findById(Integer roleId) {
        return roleDao.findAllPermissionByRoleId(roleId);
    }

    @Override
    public Role findNoPermissionById(Integer roleId) {
        return roleDao.findNoPermissionById(roleId);
    }

    @Override
    public void addPermissionBatch(List<RolePermission> rolePermissionList) {
        for (RolePermission rolePermission : rolePermissionList) {
            rolePermissionDao.save(rolePermission);
        }
    }

    /**
     * 权限表 角色表 用户表
     * N: N: N
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        // 先删除中间表的数据
        userRoleDao.deleteByRoleId(id);
        // 先删除中间表的数据
        rolePermissionDao.deleteByRoleId(id);
        roleDao.deleteById(id);
    }
}

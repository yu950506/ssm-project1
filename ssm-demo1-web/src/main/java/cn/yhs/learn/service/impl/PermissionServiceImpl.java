package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.PermissionDao;
import cn.yhs.learn.dao.RolePermissionDao;
import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.service.PermissionService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.PermissionServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 8:54
 * @Description: todo
 **/
@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;
    @Autowired
    private RolePermissionDao rolePermissionDao;

    @Override
    public PageInfo<Permission> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Permission> permissionList = permissionDao.findAll();
        PageInfo<Permission> permissionPageInfo = PageInfo.of(permissionList);
        return permissionPageInfo;
    }

    @Override
    public void save(Permission permission) {
        permissionDao.save(permission);
    }

    /**
     * 根据 权限id 删除权限信息，是一个级联删除
     * 事务控制已经配置文件中进行配置
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        // 1. 先删除该角色权限中间表的所有该权限id的信息
        rolePermissionDao.deleteByPermissionId(id);
        // 制造异常测试事务
        // int i = 1 / 0;
        // 2. 再删除权限表中的数据
        permissionDao.deleteById(id);
    }
}

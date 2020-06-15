package cn.yhs.learn.service;

import cn.yhs.learn.domain.Permission;
import com.github.pagehelper.PageInfo;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.PernissionService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 8:52
 * @Description: todo
 **/
public interface PermissionService {

    PageInfo<Permission> findAll(int pageNum,int pageSize);


    void save(Permission permission);

    void deleteById(Integer id);
}

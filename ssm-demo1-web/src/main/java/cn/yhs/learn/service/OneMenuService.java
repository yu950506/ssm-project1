package cn.yhs.learn.service;

import cn.yhs.learn.domain.OneMenu;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.OneMenuService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 21:08
 * @Description: todo
 **/

public interface OneMenuService {
    /**
     * 查询所有目录
     *
     * @return
     */
    List<OneMenu> findAllMenu();
}

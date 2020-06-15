package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.OneMenuDao;
import cn.yhs.learn.domain.OneMenu;
import cn.yhs.learn.service.OneMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.OneMenuServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 21:09
 * @Description: todo
 **/
@Service("oneMenuService")
public class OneMenuServiceImpl implements OneMenuService {
    @Autowired
    private OneMenuDao oneMenuDao;


    public List<OneMenu> findAllMenu() {

        List<OneMenu> list = oneMenuDao.findAllWithTwo();
        for (int i = 0; i < list.size(); i++) {
            OneMenu menu = list.get(i);
            if (menu.getList().size() > 0) {
                menu.setUrl("");
            }
        }
        return list;
    }
}

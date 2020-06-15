package cn.yhs.learn.controller;

import cn.yhs.learn.domain.OneMenu;
import cn.yhs.learn.service.OneMenuService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.MenuController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 21:14
 * @Description: todo
 **/
@Controller
@RequestMapping("/menu")
@Log4j
public class MenuController {
    @Autowired
    private OneMenuService oneMenuService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAllMenu() {
        ModelAndView modelAndView = new ModelAndView();
        log.info("findAllMenu()方法执行了。。。");
        List<OneMenu> allMenu = oneMenuService.findAllMenu();
        modelAndView.addObject("allMenu", allMenu);
        modelAndView.setViewName("admin_index");
        return modelAndView;
    }

}

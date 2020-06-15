package cn.yhs.learn.controller;

import cn.yhs.learn.domain.UserInfo;
import cn.yhs.learn.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.UserController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 19:38
 * @Description: todo
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(required = false, defaultValue = "6") Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        PageInfo<UserInfo> userInfoPageInfo = userService.findAll(pageNum, pageSize);
        mv.addObject("userInfoPageInfo", userInfoPageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(UserInfo userInfo) {
        ModelAndView mv = new ModelAndView();
        userService.save(userInfo);
        return "redirect:/user/findAll.do";
    }

}

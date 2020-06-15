package cn.yhs.learn.controller;

import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.service.PermissionService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.PermissionController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 9:07
 * @Description: todo
 **/
@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(required = false, name = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(required = false, name = "pageSize", defaultValue = "6") Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        PageInfo<Permission> permissionPageInfo = permissionService.findAll(pageNum, pageSize);
        mv.addObject("permissionPageInfo", permissionPageInfo);
        mv.setViewName("permission_list");
        return mv;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) {
        permissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(name = "id", required = true) Integer id) {
        permissionService.deleteById(id);
        return "redirect:/permission/findAll.do";
    }

}
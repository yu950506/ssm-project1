package cn.yhs.learn.controller;

import cn.yhs.learn.domain.Permission;
import cn.yhs.learn.domain.Role;
import cn.yhs.learn.domain.RolePermission;
import cn.yhs.learn.service.RoleService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.RoleController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 8:40
 * @Description: todo
 **/
@Controller
@Slf4j
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(required = false, name = "pageNum", defaultValue = "1") Integer pageNum,
                                @RequestParam(required = false, name = "pageSize", defaultValue = "3") Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        PageInfo<Role> rolePageInfo = roleService.findAll(pageNum, pageSize);
        mv.addObject("rolePageInfo", rolePageInfo);
        mv.setViewName("role_list");
        return mv;
    }

    /**
     * 新增角色
     *
     * @param role
     * @return
     */
    @RequestMapping("/save.do")
    public String save(Role role) {
        roleService.save(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(required = true, name = "id") Integer roleId) {
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        mv.addObject("role", role);
        mv.setViewName("role_info");
        return mv;

    }

    /**
     * 根据角色id查询该角色没有的权限
     *
     * @param roleId 角色id
     * @return
     */
    @RequestMapping("/findNoPermissionById")
    public ModelAndView findNoPermissionById(@RequestParam(name = "id") Integer roleId) {
        ModelAndView modelAndView = new ModelAndView();
        // 查询的是该角色没有的权限信息
        Role role = roleService.findNoPermissionById(roleId);
        modelAndView.setViewName("role_add_permission");
        modelAndView.addObject("role", role);
        return modelAndView;
    }

    @RequestMapping("/addPermission.do")
    public String addPermission(@RequestParam(name = "roleId") Integer roleId, @RequestParam(name = "permissionId") Integer[] permissionIds) {
        log.info("角色id:{},权限列表：{}", roleId, Arrays.toString(permissionIds));
        List<RolePermission> rolePermissionList = new ArrayList<>();
        for (Integer permissionId : permissionIds) {
            rolePermissionList.add(new RolePermission(roleId, permissionId));
        }
        roleService.addPermissionBatch(rolePermissionList);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(required = true, name = "id") Integer id) {
        roleService.deleteById(id);
        return "redirect:/role/findAll.do";
    }
}

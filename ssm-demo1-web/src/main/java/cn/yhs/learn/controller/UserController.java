package cn.yhs.learn.controller;

import cn.yhs.learn.domain.UserInfo;
import cn.yhs.learn.domain.UserRole;
import cn.yhs.learn.service.UserService;
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
 * @Name: cn.yhs.learn.controller.UserController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/11 20:00
 * @Description: todo
 **/
@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 返回所有用户信息，带分页
     *
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(required = false, defaultValue = "1", name = "pageNum") Integer pageNum,
                                @RequestParam(required = false, defaultValue = "3", name = "pageSize") Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        PageInfo<UserInfo> userInfoPageInfo = userService.findAll(pageNum, pageSize);
        mv.addObject("userInfoPageInfo", userInfoPageInfo);
        mv.setViewName("user_list");
        return mv;
    }

    /**
     * 新增用户的方法
     *
     * @param userInfo
     */
    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo) {
        userService.save(userInfo);
        return "redirect:/user/findAll.do";
    }

    /**
     * 查询用户详情,包含用户信息，用户的角色信息，用户的权限信息
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(required = true, name = "id") Integer id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findAllInfoById(id);
        mv.addObject("userInfo", userInfo);
        mv.setViewName("user_info");// 跳转到用户详情页
        return mv;
    }

    /**
     * 更新用户信息之前的查询用户信息进行回显
     *
     * @param id
     * @return
     */
    @RequestMapping("/updateForSelectById.do")
    public ModelAndView updateById(@RequestParam(required = true, name = "id") Integer id) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("userInfo", userInfo);
        mv.setViewName("user_update");
        return mv;
    }

    /**
     * 更新用户信息
     *
     * @param userInfo
     * @return
     */
    @RequestMapping("/update.do")
    public String updateById(UserInfo userInfo) {
        ModelAndView mv = new ModelAndView();
        log.info("更新的用户信息=>{}", userInfo);
        userService.updateUser(userInfo);
        return "redirect:/user/findAll.do";
    }

    /**
     * 根据当前角色id查询他没有的角色进行显示
     *
     * @param roleId 角色id
     * @return
     */
    @RequestMapping("/findNoRoleById.do")
    public ModelAndView findNoRoleById(@RequestParam(required = true, name = "id") Integer roleId) {
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findNoRoleById(roleId);
        mv.addObject("userInfo", userInfo);
        mv.setViewName("user_add_role");
        return mv;
    }

    /**
     *  给用户添加角色
     * @return
     */
    @RequestMapping("/addRole.do")
    // todo  获取复选框的中的多个值
    public String addRole(@RequestParam(name = "userId") String userId,@RequestParam(required = true,name = "roleId") String[] roleIds){
        log.info("传递过来的用户id:{},角色列表：{}", userId,Arrays.toString(roleIds));
        List<UserRole> userRoleList = new ArrayList<>();
        for (String roleId : roleIds) {
            userRoleList.add(new UserRole(Integer.parseInt(userId),Integer.parseInt(roleId)));
        }
        // todo 使用批量的方式
        userService.addRoleBatch(userRoleList);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/deleteById")
    public String deleteById(@RequestParam(name="id") Integer id){
        userService.deleteById(id);
        return "redirect:/user/findAll.do";
    }

}

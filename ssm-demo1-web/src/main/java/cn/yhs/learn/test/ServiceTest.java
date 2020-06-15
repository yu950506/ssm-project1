package cn.yhs.learn.test;

import cn.yhs.learn.domain.*;
import cn.yhs.learn.service.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.test.ServiceTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 21:11
 * @Description: todo
 **/
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ServiceTest {
    @Autowired
    private OneMenuService oneMenuService;
    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Test
    public void testPermission() {
          //  log.info("Helllppppppppppppppppp");
        PageInfo<Permission> permissionPageInfo = permissionService.findAll(1, 1);
        System.out.println("permissionPageInfo = " + permissionPageInfo);
    }

    @Test
    public void testRole() {
        PageInfo<Role> pageInfo = roleService.findAll(1, 1);
        System.out.println("pageInfo = " + pageInfo);
    }

    @Test
    public void testUser() {
      /*  PageInfo<UserInfo> pageInfo = userService.findAll(1, 2);
        System.out.println("pageInfo = " + pageInfo);*/
        /*if (userService instanceof UserDetailsService) {
            UserDetailsService userDetailsService = (UserDetailsService) userService;
            UserDetails user = userDetailsService.loadUserByUsername("user");
            System.out.println("user = " + user);
        }*/
        UserInfo userInfo = userService.findNoRoleById(1);
        List<Role> roles = userInfo.getRoles();
        for (Role role : roles) {
            System.out.println("没有角色role => " + role);
        }

    }

    @Test
    public void testOrder() {
        PageInfo<Orders> ordersPageInfo = orderService.findAll(1, 3);
        System.out.println("ordersPageInfo = " + ordersPageInfo);
    }

    @Test
    public void test() throws JsonProcessingException {
        List<OneMenu> allMenu = oneMenuService.findAllMenu();
        for (OneMenu menu : allMenu) {
            System.out.println("menu = " + menu);
        }

    }

    @Test
    public void testProducts() {
     /*
     List<Product> all = productService.findAll();
        for (Product product : all) {
            System.out.println(product);
        }
        */

        PageInfo<Product> pageInfo = productService.findAll(1, 2);

        System.out.println(pageInfo);
    }
}

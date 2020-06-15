package cn.yhs.learn.test;

import cn.yhs.learn.dao.*;
import cn.yhs.learn.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.DaoTest
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 20:25
 * @Description: todo
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class DaoTest {

    @Autowired
    private TwoMenuDao twoMenuDao;
    @Autowired
    private OneMenuDao oneMenuDao;
    @Autowired
    private ProductDao productDao;
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderTravellerDao orderTravellerDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDao roleDao;
    @Autowired
    private PermissionDao permissionDao;

    @Test
    public void testPermission(){
        List<Permission> list = permissionDao.findAllPermissionByRoleId(1);
        for (Permission permission : list) {
            System.out.println("permission = " + permission);
        }
    }

    @Test
    public void testRole(){
      /*  List<Role> allRole = roleDao.findAllRole();
        for (Role role : allRole) {
            System.out.println("role = " + role);
        }*/

        Role role= roleDao.findAllPermissionByRoleId(2);
        System.out.println("role = " + role);
    }
    @Test
    public void testUser(){
      /*  UserInfo yhs = userDao.findByUsername("yhs");
        System.out.println("yhs = " + yhs);*/
        UserInfo us = userDao.findAllInfoById(3);
        System.out.println("us = " + us);
    }

    @Test
    public void testOrder() {
        Orders orders = orderDao.findById(1);
        System.out.println(orders);
/*
        List<Orders> all = orderDao.findAll();
        for (Orders orders : all) {
            System.out.println(orders);
        }
*/

    }

    @Test
    public void testOrderTraveller() {
      /*  List<Integer> list = orderTravellerDao.findByOrderId(1);
        for (Integer integer : list) {
            System.out.println("integer = " + integer);
        }*/

        List<Traveller> travellerList = orderTravellerDao.findAllTravellerByOrderId(1);
        for (Traveller traveller : travellerList) {
            System.out.println("traveller = " + traveller);
        }
    }

    @Test
    public void testMember() {
        Member member = memberDao.findById(1);
        System.out.println("member = " + member);
    }

    @Test
    public void test() {
        Product product = productDao.findById(1);
        System.out.println("product = " + product);
    }

    @Test
    public void testTwo() {
        List<TwoMenu> all = twoMenuDao.findAll();
        for (TwoMenu twoMenu : all) {
            System.out.println("twoMenu = " + twoMenu);
        }
    }

    @Test
    public void testTwo2() {
        List<TwoMenu> all = twoMenuDao.findAllByOneId(2);
        for (TwoMenu twoMenu : all) {
            System.out.println("twoMenu = " + twoMenu);
        }
    }

    @Test
    public void testTwo3() {
        TwoMenu oneByid = twoMenuDao.findOneByid(1);
        System.out.println("oneByid = " + oneByid);
    }

    @Test
    public void testOne() {
        List<OneMenu> allWithTwo = oneMenuDao.findAllWithTwo();

        for (OneMenu oneMenu : allWithTwo) {
            System.out.println("oneMenu = " + oneMenu);
        }
    }

}

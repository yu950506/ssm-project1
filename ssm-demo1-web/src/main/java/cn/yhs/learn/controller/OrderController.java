package cn.yhs.learn.controller;

import cn.yhs.learn.domain.Orders;
import cn.yhs.learn.service.OrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.OrderController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/10 9:13
 * @Description: todo
 **/
@Controller
@RequestMapping(path = "/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * 查询所有订单带分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(path = "/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(name = "pageSize", required = false, defaultValue = "4") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<Orders> ordersPageInfo = orderService.findAll(pageNum, pageSize);
        modelAndView.setViewName("order_list");
        modelAndView.addObject("ordersPageInfo", ordersPageInfo);
        return modelAndView;
    }

    /**
     * 根据id查询订单详情
     *
     * @param id
     * @return
     */
    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(required = true, name = "id") Integer id) {
        ModelAndView mv = new ModelAndView();
        Orders orders = orderService.findById(id);
        mv.addObject("orders", orders);
        mv.setViewName("order_info");
        return mv;
    }

}

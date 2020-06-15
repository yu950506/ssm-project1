package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.OrderDao;
import cn.yhs.learn.domain.Orders;
import cn.yhs.learn.service.OrderService;
import cn.yhs.learn.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.OrderServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/10 9:18
 * @Description: todo
 **/
@Service("orderService")
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Override
    public PageInfo<Orders> findAll(int pageNum, int PageSize) {
        log.info("pageNum => {},pageSize => {}", pageNum, PageSize);
        PageHelper.startPage(pageNum, PageSize);
        List<Orders> ordersList = orderDao.findAll();
        PageInfo<Orders> ordersPageInfo = PageInfo.of(ordersList);
        return ordersPageInfo;
    }

    @Override
    public Orders findById(Integer id) {
        return orderDao.findById(id);
    }
}

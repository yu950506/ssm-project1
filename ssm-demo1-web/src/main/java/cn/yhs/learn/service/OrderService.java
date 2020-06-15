package cn.yhs.learn.service;

import cn.yhs.learn.domain.Orders;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.OrderService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/10 9:17
 * @Description: todo
 **/
public interface OrderService {
    PageInfo<Orders> findAll(int pageNum, int pageSize);

    Orders findById(Integer id);
}

package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Orders;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.OrderDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/10 9:20
 * @Description: todo
 **/
@Repository("orderDao")
public interface OrderDao {
    /**
     * 查询所有订单
     *
     * @return
     */
    @Select("select * from orders")
    @Results(id = "orderMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "order_num", property = "orderNum"),
            @Result(column = "order_time", property = "orderTime"),
            @Result(column = "people_count", property = "peopleCount"),
            @Result(column = "order_desc", property = "orderDesc"),
            @Result(column = "pay_type", property = "payType"),
            @Result(column = "order_status", property = "orderStatus"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "member_id", property = "memberId"),
    })
    List<Orders> findAll();

    /**
     * 根据订单id,查询订单详情
     *
     * @param id
     * @return
     */
    @Select("select * from orders where id = #{id}")
    @Results(id = "orderInfoMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "order_num", property = "orderNum"),
            @Result(column = "order_time", property = "orderTime"),
            @Result(column = "people_count", property = "peopleCount"),
            @Result(column = "order_desc", property = "orderDesc"),
            @Result(column = "pay_type", property = "payType"),
            @Result(column = "order_status", property = "orderStatus"),
            @Result(column = "product_id", property = "productId"),
            @Result(column = "member_id", property = "memberId"),
            @Result(property = "travellers", column = "id", many = @Many(select = "cn.yhs.learn.dao.OrderTravellerDao.findAllTravellerByOrderId")),
            @Result(property = "product", column = "product_id", one = @One(select = "cn.yhs.learn.dao.ProductDao.findById")),
            @Result(property = "member", column = "member_id", one = @One(select = "cn.yhs.learn.dao.MemberDao.findById"))
    })
    Orders findById(Integer id);
}

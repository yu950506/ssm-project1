package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Product;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.ProductDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 11:38
 * @Description: todo
 **/
@Repository("productDao")
public interface ProductDao {

    /**
     * 查询所有产品
     *
     * @return
     */
    @Select("select * from product")
    @Results(id = "productMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "product_num", property = "productNum"),
            @Result(column = "product_name", property = "productName"),
            @Result(column = "city_name", property = "cityName"),
            @Result(column = "departure_time", property = "departureTime"),
            @Result(column = "product_price", property = "productPrice"),
            @Result(column = "product_desc", property = "productDesc"),
            @Result(column = "product_status", property = "productStatus"),
    })
    List<Product> findAll();

    /**
     * 根据产品id 查询产品信息
     *
     * @param id
     * @return
     */
    @Select("select * from product where id = #{id}")
    @ResultMap("productMap")
    Product findById(Integer id);


    /**
     * @param product
     */
    @Insert("insert into product(product_num,product_name,city_name,departure_time,product_price,product_status,product_desc)" +
            " values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productStatus},#{productDesc})")
    void save(Product product);
}

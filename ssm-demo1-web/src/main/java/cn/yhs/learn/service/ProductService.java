package cn.yhs.learn.service;

import cn.yhs.learn.domain.Product;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.ProductService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 11:44
 * @Description: todo
 **/
public interface ProductService {

    List<Product> findAll();
    PageInfo<Product> findAll(int pageNum, int pageSize);

    void save(Product product);
}

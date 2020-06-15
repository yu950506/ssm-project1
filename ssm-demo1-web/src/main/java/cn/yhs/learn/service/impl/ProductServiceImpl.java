package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.ProductDao;
import cn.yhs.learn.domain.Product;
import cn.yhs.learn.service.ProductService;
import cn.yhs.learn.util.DateUtils;
import cn.yhs.learn.util.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.ProductSeriviceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 11:44
 * @Description: todo
 **/
@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<Product> findAll() {
        List<Product> resultList = new ArrayList<>();
        List<Product> productList = productDao.findAll();
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            // 设置前端显示出发时间的样式
            product.setDepartureTimeStr(DateUtils.parseString(product.getDepartureTime(), "yyyy-MM-dd HH:mm:ss"));
            // 设置前端显示状态的样式
            product.setProductStatusStr(product.getProductStatus() == true ? "开启" : "关闭");
            resultList.add(product);
        }
        return resultList;
    }

    /**
     * 带分页的方法
     *
     * @param pageNum  当前页
     * @param pageSize 每页的数量
     * @return
     */
    @Override
    public PageInfo<Product> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Product> productList = productDao.findAll();
        PageInfo<Product> pageInfo = PageInfo.of(productList);
        return pageInfo;
    }

    public void save(Product product) {
        // 为商品设置商品号
        product.setProductNum(UUIDUtils.getUUID());
        productDao.save(product);
    }
}

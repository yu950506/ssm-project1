package cn.yhs.learn.controller;

import cn.yhs.learn.domain.Product;
import cn.yhs.learn.service.ProductService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.ProductController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 11:49
 * @Description: todo
 **/
@Controller
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

/*    @RequestMapping("/findAllNOPage.do")
    public ModelAndView findAll() {
        ModelAndView mv = new ModelAndView();
        List<Product> productList = productService.findAll();
        mv.setViewName("product_list");
        mv.addObject("productList", productList);
        return mv;
    }*/

    /**
     * @param pageNum  默认值 1页
     * @param pageSize 默认值 4 条数据
     * @return
     */
    // 方法级别的权限控制3. 开启角色控制
    @RolesAllowed("ADMIN") // 只有管理员这个角色才能有这个权限
    @RequestMapping("/findAll.do")
    public ModelAndView findAllWithPage(@RequestParam(required = false, defaultValue = "1", name = "pageNum") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "4", name = "pageSize") Integer pageSize) {
        log.info("pageNum => {},pageSize => {}", pageNum, pageSize);
        ModelAndView mv = new ModelAndView();
        PageInfo<Product> productPageInfo = productService.findAll(pageNum, pageSize);
        mv.setViewName("product_list");
        mv.addObject("productPageInfo", productPageInfo);
        return mv;
    }

    @RequestMapping(path = "/save.do", method = RequestMethod.POST)
    public String saveProduct(Product product) {
        log.info("service传入的参数：{}", product);
        productService.save(product);
        // 保存之后重定向到查询请求
        return "redirect:/product/findAll.do";
    }

}

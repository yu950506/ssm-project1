package cn.yhs.learn.controller;

import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.service.SysLogService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.SysLogController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 17:42
 * @Description: todo
 **/
@Controller
@RequestMapping(value = "/syslog")
public class SysLogController {


    @Autowired
    private SysLogService sysLogService;

    /**
     * 查询所有日志信息，带分页
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/findAll.do")
    public ModelAndView findAll(@RequestParam(required = false, defaultValue = "1") Integer pageNum, @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        PageInfo<SysLog> sysLogPageInfo = sysLogService.findAll(pageNum, pageSize);
        modelAndView.addObject("sysLogPageInfo", sysLogPageInfo);
        modelAndView.setViewName("syslog_list");
        return modelAndView;
    }

}

package cn.yhs.learn.controller;

import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.controller.SysLogController
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/13 17:37
 * @Description: todo
 **/
@Controller
@RequestMapping("/syslog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(required = false, defaultValue = "1", name = "pageNum") Integer pageNum,
                                @RequestParam(required = false, defaultValue = "10", name = "pageSize") Integer pageSize) {
        ModelAndView mv = new ModelAndView();
        PageInfo<SysLog> sysLogPageInfo = sysLogService.findAll(pageNum, pageSize);
        mv.addObject("sysLogPageInfo", sysLogPageInfo);
        mv.setViewName("syslog_list");
        return mv;

    }
}

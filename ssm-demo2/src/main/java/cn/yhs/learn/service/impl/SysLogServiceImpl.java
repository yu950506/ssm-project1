package cn.yhs.learn.service.impl;

import cn.yhs.learn.dao.SysLogDao;
import cn.yhs.learn.domain.SysLog;
import cn.yhs.learn.service.SysLogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.impl.SysLogServiceImpl
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 16:57
 * @Description: todo
 **/
@Service("sysLogService")
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;

    /**
     * 查询所有系统日志带分页
     *
     * @param pageNum  当前页码
     * @param pageSize 每页显示的条数
     * @return
     */
    @Override
    public PageInfo<SysLog> findAll(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysLog> sysLogList = sysLogDao.findAll();
        PageInfo<SysLog> sysLogPageInfo = PageInfo.of(sysLogList);
        return sysLogPageInfo;
    }

    @Override
    public void save(SysLog sysLog) {
        sysLogDao.save(sysLog);

    }
}

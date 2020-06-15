package cn.yhs.learn.service;

import cn.yhs.learn.domain.SysLog;
import com.github.pagehelper.PageInfo;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.SysLogServiceService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 16:56
 * @Description: todo
 **/
public interface SysLogService {
    PageInfo<SysLog> findAll(Integer pageNum, Integer pageSize);

    void save(SysLog sysLog);
}

package cn.yhs.learn.service;

import cn.yhs.learn.domain.SysLog;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.service.SysLogService
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/13 15:28
 * @Description: todo
 **/
public interface SysLogService {

    void save(SysLog sysLog);

    PageInfo<SysLog> findAll(int pageNum, int pageSize);
}

package cn.yhs.learn.dao;

import cn.yhs.learn.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.SysLogDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/13 15:20
 * @Description: todo
 **/

@Repository("sysLogDao")
public interface SysLogDao {

    @Insert("insert into sys_log(username,ip,url,clazz,method,visit_time,execution_time) values(#{username},#{ip},#{url},#{clazz},#{method},#{visitTime},#{executionTime})")
    void save(SysLog sysLog);

    @Select("select * from sys_log")
    @Results(id = "sysLogMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "visit_time", property = "visitTime"),
            @Result(column = "execution_time", property = "executionTime")
    })
    List<SysLog> findAll();
}

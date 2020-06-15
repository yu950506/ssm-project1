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
 * @Time: 2020/6/14 16:49
 * @Description: todo
 **/
@Repository
public interface SysLogDao {
    /**
     * 查询所有日志,按照最新的日期,因为id是主键递增的，所有主键数越大，说明这条数据越是最新的
     *
     * @return
     */
    @Select("select * from sys_log order by id desc")
    @Results(id = "sysLogAllInfoMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "visit_time", property = "visitTime"),
            @Result(column = "execution_time", property = "executionTime"),
    })
    List<SysLog> findAll();

    /**
     * 插入一条日志信息
     *
     * @param sysLog
     */
    @Insert("insert into sys_log(username,ip,url,clazz,method,visit_time,execution_time) values(#{username},#{ip},#{url},#{clazz},#{method},#{visitTime},#{executionTime})")
    void save(SysLog sysLog);


}

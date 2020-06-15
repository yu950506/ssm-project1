package cn.yhs.learn.dao;

import cn.yhs.learn.domain.TwoMenu;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.TwoMeanDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 20:13
 * @Description: todo
 **/
@Repository("twoMenuDao")
public interface TwoMenuDao {

    /**
     * 查询所有二级目录
     *
     * @return
     */
    @Select("select * from two_menu")
    @Results(id = "twoMenu", value = {
            @Result(id = true, column = "id", property = "id"),// 主键列，用于提高性能
            @Result(column = "one_id", property = "oneId"),
            @Result(column = "url", property = "url"),
            @Result(column = "name", property = "name"),
    })
    // todo 1 问题：oneId one_id 数据库和实体类对象别名不一样问题的解决
    List<TwoMenu> findAll();


    /**
     * 根据二级id查新二级目录
     *
     * @param id 二级id
     * @return
     */
    @Select("select * from two_menu where id =#{id}")
    @ResultMap("twoMenu")
    // 引用上面的结果集
    TwoMenu findOneByid(Integer id);

    /**
     * 根据1级id查询所有二级目录
     *
     * @param oneId 一级id
     * @return
     */
    @Select("select * from two_menu where one_id = #{oneId}")
    @ResultMap("twoMenu")
    // 引用上面的结果集
    List<TwoMenu> findAllByOneId(Integer oneId);
}

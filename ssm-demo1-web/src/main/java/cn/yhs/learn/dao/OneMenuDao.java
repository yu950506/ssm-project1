package cn.yhs.learn.dao;

import cn.yhs.learn.domain.OneMenu;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.OneMenuDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/8 20:09
 * @Description: todo
 **/
@Repository("oneMenuDao")
public interface OneMenuDao {
    // todo 2 查询所有一级目录并附带二级目录
    @Select("select * from one_menu")
    @Results(
            id = "oneMenu:N", value = {
            @Result(id = true, column = "id", property = "id"),// 主键id
            @Result(column = "name", property = "name"),
            @Result(column = "icon", property = "icon"),
            @Result(column = "url", property = "url"),
            @Result(column = "hidden", property = "hidden"),
            // todo 4  1:N的查询,建议使用懒加载fetchType = FetchType.LAZY，当把查询的对象序列化成json时要关闭懒加载
            @Result(property = "list", column = "id", many = @Many(select = "cn.yhs.learn.dao.TwoMenuDao.findAllByOneId"))
    }
    )
    List<OneMenu> findAllWithTwo();
}

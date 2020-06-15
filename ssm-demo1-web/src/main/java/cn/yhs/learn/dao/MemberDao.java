package cn.yhs.learn.dao;

import cn.yhs.learn.domain.Member;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.MemberDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/10 18:10
 * @Description: todo
 **/
@Repository("memberDao")
public interface MemberDao {

    @Select("select * from members")
    @Results(id = "memberMap", value = {
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "name", property = "name"),
            @Result(column = "nick_name", property = "nickName"),
            @Result(column = "phone_num", property = "phoneNum"),
            @Result(column = "email", property = "email"),
    })
    List<Member> findAll();

    @Select("select * from member where id = #{id}")
    @ResultMap("memberMap")
    Member findById(Integer id);
}

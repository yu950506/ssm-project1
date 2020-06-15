package cn.yhs.learn.dao;

import cn.yhs.learn.domain.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.springframework.stereotype.Repository;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.dao.UserRoleDao
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 15:34
 * @Description: todo
 **/
@Repository("userRoleDao")
public interface UserRoleDao {
    @Insert("insert into user_role(user_id,role_id) values(#{userId},#{roleId})")
    void save(UserRole userRole);

    @Delete("delete from user_role where user_id = #{userId}")
    void deleteByUserId(Integer userId);

    @Delete("delete from user_role where role_id = #{roleId}")
    void deleteByRoleId(Integer roleId);
}

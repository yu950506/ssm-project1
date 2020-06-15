package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.UserRole
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/12 15:33
 * @Description: 用户角色中间表
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRole {
    private Integer userId;
    private Integer roleId;
}

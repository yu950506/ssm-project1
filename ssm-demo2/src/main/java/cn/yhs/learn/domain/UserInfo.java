package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.UserInfo
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/14 19:21
 * @Description: 之所以叫UserInfo 是因为SpringSecurity中也有个User对象
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserInfo {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String phoneNum;
    private Boolean status;
    private String statusStr;
    private List<Role> roles;// 1:N 一个用户可以有多种角色

    public String getStatusStr() {
        return status ? "开启" : "未开启";
    }
}

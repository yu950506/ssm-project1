package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.User
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/11 11:32
 * @Description: 用户表
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

    private List<Role> roles; // 一个用户可以有多个角色



    public String getStatusStr() {
        return this.getStatus() ? "可用" : "不可用";
    }

}

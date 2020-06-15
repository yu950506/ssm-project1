package cn.yhs.learn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @ProjectName: ssm-demo1
 * @Name: cn.yhs.learn.domain.Member
 * @Author: Splendor -- 加油,你是最棒的 ~_~
 * @Email: 15617577080@163.com
 * @Time: 2020/6/9 23:41
 * @Description: 会员表
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Member {
    private Integer id;
    private String name;
    private String nickName;
    private String phoneNum;
    private String email;
}
